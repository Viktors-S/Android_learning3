package com.example.customlistwithnavigationwithrecicleview;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.customlistwithnavigationwithrecicleview.model.Person;

import java.util.ArrayList;

public class ProfileSongListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    ArrayList<String> arrayList;
    int pos;


    public ArrayList<String> getList() {
        return arrayList;
    }

    public ProfileSongListAdapter(ArrayList<Person> list, int position){
        this.pos = position;
        this.arrayList = list.get(pos).getSongPlaylist();
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        return new SongItemHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.song_item_holder,parent,false));


    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof SongItemHolder){

            ((SongItemHolder) holder).song_name.setText(arrayList.get(position));
            String res = ""+arrayList.get(position).toLowerCase().replace(" ","_");



            if(getDrawablePath(res)!=0){
                ((SongItemHolder) holder).song_image.setImageResource(getDrawablePath(res));
            }

        }
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    class SongItemHolder extends RecyclerView.ViewHolder{

        TextView song_name;
        ImageView song_image;

        public SongItemHolder(@NonNull View itemView) {
            super(itemView);

            song_name = itemView.findViewById(R.id.tv_song_name);
            song_image = itemView.findViewById(R.id.iv_song_icon);

        }



    }

    public int getDrawablePath(String path){
        if(path.equals("thats_what_i_want")){
            return R.drawable.thats_what_i_want;
        }
        if(path.equals("alejandro")){
            return R.drawable.alejandro;
        }
        return 0;
    }


}
