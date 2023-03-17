package com.example.customlistwithnavigationwithrecicleview.Fragments;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.customlistwithnavigationwithrecicleview.GV;
import com.example.customlistwithnavigationwithrecicleview.R;
import com.example.customlistwithnavigationwithrecicleview.ServiceHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class FragmentBlogList extends Fragment {

    String url = "https://jsonplaceholder.typicode.com/posts";

    JSONArray blogposts = null;

    private ProgressDialog progressDialog;

    SimpleJsonFragment parent;

    public FragmentBlogList(SimpleJsonFragment parent){
        this.parent = parent;
    }

    ArrayList<HashMap<String,String>> blogPostList;

    String[] icons = {String.valueOf(R.drawable.baseline_hive_24),String.valueOf(R.drawable.baseline_spa_24),
            String.valueOf(R.drawable.baseline_skateboarding_24),String.valueOf(R.drawable.baseline_subway_24),
            String.valueOf(R.drawable.baseline_workspace_premium_24),String.valueOf(R.drawable.baseline_whatshot_24),
            String.valueOf(R.drawable.baseline_water_24),String.valueOf(R.drawable.baseline_visibility_24),
            String.valueOf(R.drawable.baseline_verified_24),String.valueOf(R.drawable.baseline_update_24)};

    ListView lv;

    View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_bloglist,container,false);

        blogPostList = new ArrayList<>();
        lv = view.findViewById(R.id.lv_blog);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                parent.startSingleBlogFragment(blogPostList.get(i).get("title"),
                        blogPostList.get(i).get("body"),
                        blogPostList.get(i).get("iv_image"),
                        blogPostList.get(i).get("userId"),
                        blogPostList.get(i).get("id"));
            }
        });

        new GetPostList().execute();

        return view;
    }

    private class GetPostList extends AsyncTask<Void,Void,Void>{
        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            progressDialog = new ProgressDialog(getContext());
            progressDialog.setMessage("downloading data, please wait!");
            progressDialog.setCancelable(false);
            progressDialog.show();

        }

        @Override
        protected Void doInBackground(Void... voids) {

            ServiceHandler sh = new ServiceHandler(url);
            String jsonString = sh.makeServiceCall();

            if(jsonString!=null){
                try {
                    blogposts = new JSONArray(jsonString);

                    for(int i= 0; i<blogposts.length();i++){
                        JSONObject b = blogposts.getJSONObject(i);
                        String title = b.getString(GV.TITLE);

                        HashMap<String,String> oneBlogPost = new HashMap<>();
                        oneBlogPost.put(GV.TITLE,title);
                        oneBlogPost.put(GV.BODY,b.getString(GV.BODY));
                        oneBlogPost.put(GV.ID,b.getString(GV.ID));
                        oneBlogPost.put(GV.USER_ID,b.getString(GV.USER_ID));
                        int userToImg = Integer.parseInt(b.getString(GV.USER_ID))-1;
                        oneBlogPost.put(GV.IMAGE, icons[userToImg]);

                        blogPostList.add(oneBlogPost);

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void unused) {
            super.onPostExecute(unused);

            if(progressDialog.isShowing()){
                progressDialog.dismiss();
            }

            SimpleAdapter listAdapter = new SimpleAdapter(
                    getContext(),
                    blogPostList,
                    R.layout.item_single_post,
                    new String[]{GV.TITLE,GV.BODY,GV.IMAGE,GV.ID,GV.USER_ID,},
                    new int[]{R.id.tv_blog_title,R.id.tv_blog_body,R.id.iv_blog_image});

            lv.setAdapter(listAdapter);
            //setListAdapter(listAdapter);

        }


    }
}
