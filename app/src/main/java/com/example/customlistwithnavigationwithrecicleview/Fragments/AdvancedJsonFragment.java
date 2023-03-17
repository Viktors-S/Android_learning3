package com.example.customlistwithnavigationwithrecicleview.Fragments;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.fragment.app.ListFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.customlistwithnavigationwithrecicleview.R;
import com.example.customlistwithnavigationwithrecicleview.RecyclerViewInterface;
import com.example.customlistwithnavigationwithrecicleview.RequestPersonalData;
import com.example.customlistwithnavigationwithrecicleview.SpacingItemDecorator;
import com.example.customlistwithnavigationwithrecicleview.UserListAdapter;
import com.example.customlistwithnavigationwithrecicleview.model.Person;

import java.util.ArrayList;

public class AdvancedJsonFragment extends Fragment implements RecyclerViewInterface{

    View view;

    int pos;
    ArrayList<Person> arrayList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_advanced,container,false);

        startFragmentList();

        return view;
    }

    private void  startFragment(Fragment fragment){
        FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
        transaction.setCustomAnimations(R.anim.pop_up,R.anim.pop_exit_up);
        transaction.replace(R.id.fl_advanced_frame,fragment);
        transaction.commit();
    }

    private void startFragmentList(){
        FragmentList fl = new FragmentList(this);
        startFragment(fl);
    }

    private void startFragmentProfile(){
        startFragment(new FragmentProfile(this));
    }

    @Override
    public void onItemClick(int position) {
        this.pos = position;
        startFragmentProfile();

    }
}
