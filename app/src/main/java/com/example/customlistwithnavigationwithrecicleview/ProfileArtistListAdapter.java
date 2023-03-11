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

public class ProfileArtistListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    ArrayList<String> arrayList;
    int pos;

    public ArrayList<String> getList() {
        return arrayList;
    }

    public ProfileArtistListAdapter(ArrayList<Person> list, int position){
        this.pos = position;
        this.arrayList = list.get(pos).getArtistPlaylist();

    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        return new ArtistItemHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.song_artist_holder,parent,false));


    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof ArtistItemHolder){

            ((ArtistItemHolder) holder).artist_name.setText(arrayList.get(position));
            String res = ""+arrayList.get(position).toLowerCase().replace(" ","_");



            if(getDrawablePath(res)!=0){
                ((ArtistItemHolder) holder).artist_image.setImageResource(getDrawablePath(res));
            }

        }
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }


    class ArtistItemHolder extends RecyclerView.ViewHolder{

        TextView artist_name;
        ImageView artist_image;

        public ArtistItemHolder(@NonNull View itemView) {
            super(itemView);

            artist_name = itemView.findViewById(R.id.tv_artist_name);
            artist_image = itemView.findViewById(R.id.iv_artist_image);

        }



    }

    public int getDrawablePath(String path){
        System.out.println(path);
        if(path.equals("lady_gaga")){
            return R.drawable.lady_gaga;
        }
        if(path.equals("foo_fighters")){
            return R.drawable.foo_fighters;
        }
        if(path.equals("radiohead")){
            return R.drawable.radiohead;
        }
        return 0;
    }


}

