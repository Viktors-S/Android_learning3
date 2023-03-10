package com.example.customlistwithnavigationwithrecicleview.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.customlistwithnavigationwithrecicleview.R;
import com.example.customlistwithnavigationwithrecicleview.model.Person;

import java.util.ArrayList;

public class FragmentProfile extends Fragment {


    AdvancedJsonFragment parent;

    public FragmentProfile(AdvancedJsonFragment parent){
        this.parent = parent;
    }


    View view;

    ArrayList<Person> personArrayList;
    int position;

    ImageButton close;

    TextView name,phone,gender,address;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_profile,container,false);

        close = view.findViewById(R.id.ib_close_profile);

        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
                transaction.setCustomAnimations(R.anim.pop_up,R.anim.pop_exit_up);
                transaction.replace(R.id.fl_advanced_frame,new FragmentList(parent));
                transaction.commit();
            }
        });

        name=view.findViewById(R.id.tv_profile_name);
        phone = view.findViewById(R.id.tv_profile_number);
        gender = view.findViewById(R.id.tv_profile_gender);
        address = view.findViewById(R.id.tv_profile_address);

        personArrayList = parent.arrayList;
        position=parent.pos;

        name.setText(personArrayList.get(position).getName());

        String phones = "";

        if(personArrayList.get(position).getPhoneList().getMobile()!=null){
            phones+="MOBILE: "+personArrayList.get(position).getPhoneList().getMobile()+"\n";
        }
        if(personArrayList.get(position).getPhoneList().getHome()!=null){
            phones+="HOME: "+personArrayList.get(position).getPhoneList().getHome()+"\n";
        }
        if(personArrayList.get(position).getPhoneList().getOffice()!=null){
            phones+="OFFICE: "+personArrayList.get(position).getPhoneList().getOffice();
        }

        //phone
        phone.setText(phones);
        gender.setText(personArrayList.get(position).getGender());
        address.setText(personArrayList.get(position).getAddress());




        return view;
    }
}
