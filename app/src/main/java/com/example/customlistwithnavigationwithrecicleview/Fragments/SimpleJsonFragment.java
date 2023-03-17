package com.example.customlistwithnavigationwithrecicleview.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.customlistwithnavigationwithrecicleview.R;
import com.example.customlistwithnavigationwithrecicleview.model.Person;

import java.util.ArrayList;

public class SimpleJsonFragment extends Fragment {

    View view;








    int pos;
    ArrayList<Person> arrayList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_simplejson,container,false);

        startBlogFragment();

        return view;
    }

    private void  startFragment(Fragment fragment){
        FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
        transaction.setCustomAnimations(R.anim.pop_up,R.anim.pop_exit_up);
        transaction.replace(R.id.fl_simple_json,fragment);
        transaction.commit();
    }

    public void startBlogFragment(){startFragment(new FragmentBlogList(this));}

    public void startSingleBlogFragment(String title,String body,String iv_image,String userId,String id){
        startFragment(new FragmentBlog(this,title,body,iv_image,userId,id));
    }



}
