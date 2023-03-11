package com.example.customlistwithnavigationwithrecicleview.Fragments;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.customlistwithnavigationwithrecicleview.R;
import com.example.customlistwithnavigationwithrecicleview.RecyclerViewInterface;
import com.example.customlistwithnavigationwithrecicleview.RequestPersonalData;
import com.example.customlistwithnavigationwithrecicleview.SpacingItemDecorator;
import com.example.customlistwithnavigationwithrecicleview.UserListAdapter;
import com.example.customlistwithnavigationwithrecicleview.model.Person;

import java.util.ArrayList;

public class FragmentList extends Fragment implements RecyclerViewInterface {

    View view;

    private ProgressDialog progressDialog;
    UserListAdapter userListAdapter;
    RecyclerView recyclerView;
    ArrayList<Person> personArrayList;

    AdvancedJsonFragment parent;

    public FragmentList(AdvancedJsonFragment parent){
        this.parent = parent;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_list,container,false);

        progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("Please wait, downloading data....");
        progressDialog.setCancelable(false);


        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        new RequestPersonalData(progressDialog, new RequestPersonalData.OnPersonListDataReceived() {
            @Override
            public void onDataReceived(ArrayList<Person> list) {
                setAdapter(view,list);
            }
        }).execute();



    }
    private void setAdapter(View view, ArrayList<Person> list){
        recyclerView = (RecyclerView) view.findViewById(R.id.rv_list);
        userListAdapter = new UserListAdapter(list,parent);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,false);
        SpacingItemDecorator itemDecorator = new SpacingItemDecorator(20,0);
        recyclerView.addItemDecoration(itemDecorator);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(userListAdapter);

        personArrayList = userListAdapter.getList();
        parent.arrayList=personArrayList;
    }

    @Override
    public void onItemClick(int position) {



    }

    public ArrayList<Person> getPersonArrayList(){
        return this.personArrayList;
    }

}
