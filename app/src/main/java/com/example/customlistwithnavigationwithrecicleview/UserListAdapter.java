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

public class UserListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{


    ArrayList<Person> list;

    UserListAdapter(ArrayList<Person> list){
        this.list = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(viewType == 0){
            return new ItemPersonOneViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_person_one, parent, false));
        } else if (viewType==1) {
            return new ItemPersonOneViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_person_two, parent, false));
        }else{
            return new ItemPersonOneViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_person_one, parent, false));
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof ItemPersonOneViewHolder){

        }
        if(holder instanceof ItemPersonTwoViewHolder){

        }
    }

    @Override
    public int getItemViewType(int position) {
        //return list.get(position).getItemViewType();

        return position;
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ItemPersonOneViewHolder extends RecyclerView.ViewHolder{
        TextView name,phone,song;
        ImageView icon;

        public ItemPersonOneViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.tv_name);
            phone = itemView.findViewById(R.id.tv_phone);
            song = itemView.findViewById(R.id.tv_best_song);
            icon = itemView.findViewById(R.id.iv_icon);

        }


    }

    class ItemPersonTwoViewHolder extends  RecyclerView.ViewHolder{

        TextView name,phone,song;
        ImageView icon;

        public ItemPersonTwoViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.tv_names);
            phone = itemView.findViewById(R.id.tv_phones);
            song = itemView.findViewById(R.id.tv_best_songs);
            icon = itemView.findViewById(R.id.iv_icons);

        }

    }

}
