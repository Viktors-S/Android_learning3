package com.example.customlistwithnavigationwithrecicleview;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.util.Log;

import androidx.loader.content.AsyncTaskLoader;

import com.example.customlistwithnavigationwithrecicleview.Fragments.AdvancedJsonFragment;
import com.example.customlistwithnavigationwithrecicleview.model.Person;
import com.example.customlistwithnavigationwithrecicleview.model.Phone;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class RequestPersonalData extends AsyncTask<Void,Void,Void> {


    ProgressDialog progressDialog;
    ArrayList<Person> arrayList;
    OnPersonListDataReceived listener;

    public RequestPersonalData(ProgressDialog progressDialog, OnPersonListDataReceived listener){
        this.progressDialog = progressDialog;
        this.listener = listener;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        progressDialog.show();
    }

    @Override
    protected Void doInBackground(Void... voids) {

        ServiceHandler sh = new ServiceHandler(MainActivity.url);
        String jsonString = sh.makeServiceCall();

        if(jsonString!= null){
            jsonString = jsonString.replace("<pre>", "").replace("</pre>","");
            arrayList = new ArrayList<>();

            JSONObject jsonObject = null;

            try {
                jsonObject = new JSONObject(jsonString);
                JSONArray jsonArray = jsonObject.getJSONArray("contacts");

                for (int i =0;i<jsonArray.length();i++){
                    JSONObject c = jsonArray.getJSONObject(i);
                    String name = c.getString("name");
                    String id = c.getString("id");
                    String address = c.getString("address");
                    String gender = c.getString("gender");
                    Phone phone = new Phone(c.getJSONObject("phone").getString("mobile"),
                            c.getJSONObject("phone").getString("home"),
                            c.getJSONObject("phone").getString("office"));

                    ArrayList<String> song = new ArrayList<>();
                    JSONArray songJson = c.getJSONObject("song_play_list").getJSONArray("song");
                    for(int j = 0;j<songJson.length();j++){
                        song.add(songJson.getString(j));
                    }

                    Person person = new Person.Builder()
                            .withName(name)
                            .withPhoneList(phone)
                            .withGender(gender)
                            .withAddress(address)
                            .withSongPlaylist(song)
                            .withId(id)
                            .build();

                    arrayList.add(person);

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
        listener.onDataReceived(arrayList);

    }

    public interface OnPersonListDataReceived{
        void onDataReceived(ArrayList<Person> list);
    }

}
