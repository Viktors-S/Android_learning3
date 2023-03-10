package com.example.customlistwithnavigationwithrecicleview.model;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class Person  {
    @Nullable
    String id, name, address, gender;
    @Nullable
    ArrayList<String> songPlaylist;
    @Nullable
    ArrayList<String> artistPlaylist;
    @Nullable
    Phone phoneList;

    private Person(){

    }

    public Person(String id, String name, String address, String gender, ArrayList<String> songPlaylist, ArrayList<String> artistPlaylist, Phone phones){
        this.id = id;
        this.name= name;
        this.address = address;
        this.gender = gender;
        this.songPlaylist = songPlaylist;
        this.artistPlaylist = artistPlaylist;
        this.phoneList = phones;

    }

    @Nullable
    public String getId() {
        return id;
    }

    @Nullable
    public String getName() {
        return name;
    }

    @Nullable
    public String getAddress() {
        return address;
    }

    @Nullable
    public String getGender() {
        return gender;
    }

    @Nullable
    public ArrayList<String> getSongPlaylist() {
        return songPlaylist;
    }

    @Nullable
    public ArrayList<String> getArtistPlaylist() {
        return artistPlaylist;
    }

    @Nullable
    public Phone getPhoneList() {
        return phoneList;
    }

    @NonNull
    public int getItemViewType(){
        return Integer.valueOf(id)%2;
    }

    public static class Builder{
        String id,name,address,gender;
        ArrayList<String> songPlaylist;
        ArrayList<String> artistPlaylist;
        Phone phoneList;

        public Person.Builder withId(String id){
            this.id = id;

            return this;
        }

        public Person.Builder withName(String name){
            this.name = name;

            return  this;
        }

        public Person.Builder withAddress(String address){
            this.address = address;

            return  this;
        }

        public Person.Builder withGender(String gender){
            this.gender = gender;

            return  this;
        }

        public Person.Builder withSongPlaylist(ArrayList<String> songPlaylist){
            this.songPlaylist = songPlaylist;

            return  this;
        }

        public Person.Builder withArtistPlaylist(ArrayList<String> artistPlaylist){
            this.artistPlaylist = artistPlaylist;

            return  this;
        }

        public Person.Builder withPhoneList(Phone phoneList){
            this.phoneList = phoneList;

            return  this;
        }

        public Person build(){
            Person person = new Person();
            person.id = id;
            person.name = name;
            person.address = address;
            person.gender = gender;
            person.songPlaylist = songPlaylist;
            person.artistPlaylist = artistPlaylist;
            person.phoneList = phoneList;

            return person;
        }

    }
}
