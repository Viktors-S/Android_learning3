package com.example.customlistwithnavigationwithrecicleview.Fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.customlistwithnavigationwithrecicleview.R;

public class FragmentBlog extends Fragment {

    View view;

    SimpleJsonFragment parent;

    String title,body,iv_image,userId,id;


    public FragmentBlog(SimpleJsonFragment parent,String title,String body,String iv_image,String userId,String id){
        this.parent=parent;this.title=title;this.body=body;this.iv_image=iv_image;this.userId=userId;this.id=id;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_blog,container,false);

        TextView title1= view.findViewById(R.id.tv_blog_title);
        TextView body1 = view.findViewById(R.id.tv_body_blog);
        TextView userId1 = view.findViewById(R.id.tv_userId_blog);
        TextView id1 = view.findViewById(R.id.tv_id_blog);
        ImageView iv_image1 = view.findViewById(R.id.iv_image_blog);

        ImageButton back = view.findViewById(R.id.ib_arrow_back);
        Button show_full_blog = view.findViewById(R.id.btn_show_full_blog);
        ImageButton minimize_blog = view.findViewById(R.id.ib_minimize_blog);


        title1.setText((CharSequence) title);
        body1.setText((CharSequence) body.toString().substring(0,body.toString().length()/2));
        userId1.setText("USER ID: "+ userId);
        id1.setText("ID: "+id);
        iv_image1.setImageResource(Integer.parseInt(iv_image));


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                parent.startBlogFragment();
            }
        });

        show_full_blog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                body1.setText((CharSequence) body);
                minimize_blog.setVisibility(View.VISIBLE);
                show_full_blog.setVisibility(View.GONE);
            }
        });

        minimize_blog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                body1.setText(body.toString().substring(0,body.toString().length()/2));
                show_full_blog.setVisibility(View.VISIBLE);
                minimize_blog.setVisibility(View.GONE);
            }
        });


        return view;
    }
}
