package com.example.customlistwithnavigationwithrecicleview.Fragments;

import android.app.WallpaperManager;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.customlistwithnavigationwithrecicleview.BottomSheetDialog;
import com.example.customlistwithnavigationwithrecicleview.MainActivity;
import com.example.customlistwithnavigationwithrecicleview.R;

import org.w3c.dom.Text;
import org.w3c.dom.ls.LSOutput;

import java.io.IOException;
import java.io.InputStream;

public class WallpaperFragment extends Fragment {

    View view;

    ImageView big_picture;
    Button set_wallpaper;
    LinearLayout linear_layout;
    TextView text_view;
    View.OnClickListener changeWallpaperListener, openDialogListener;
    int to_phone = 0;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_wallpaper,container,false);

        big_picture = view.findViewById(R.id.iv_wallpaper_image);
        set_wallpaper = view.findViewById(R.id.btn_wallpaper_change);
        linear_layout = view.findViewById(R.id.ll_wallpaper_view);
        text_view = view.findViewById(R.id.tv_wallpaper_test_text);

        text_view.setText("TEST");

        setupImageList();



        //set_wallpaper.setOnClickListener(changeWallpaperListener);

        openDialogListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(new BottomSheetDialog.OnCalculationListener() {
                    @Override
                    public void onCalculationClicked(String clicked) {

                        text_view.setText("TEXT CHANGED::"+clicked);

                        if(clicked.equals("CORRECT")){
                            if(to_phone == 0){
                                to_phone = getResources().getIdentifier("pic1","drawable",getActivity().getPackageName());
                            }


                            WallpaperManager myWallpaper = WallpaperManager.getInstance(getActivity().getApplicationContext());


                            try {
                                myWallpaper.setResource(to_phone);
                            }catch (IOException e){
                                for(int i = 0; i<100;i++){
                                    System.out.println(e);
                                }
                            }

                        }

                    }

                });

                bottomSheetDialog.setCancelable(false);
                bottomSheetDialog.show(getActivity().getSupportFragmentManager(),"TAG");

            }

        };


        set_wallpaper.setOnClickListener(openDialogListener);






        return view;
    }

    private void setupImageList() {

        for(int i=1;i<=13;i++){
            final ImageView iv = new ImageView(getActivity());
            String a = "spic"+Integer.toString(i);
            int id = getResources().getIdentifier(a,"drawable",getActivity().getPackageName());
            iv.setImageResource(id);
            iv.setId(i);

            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(500,ViewGroup.LayoutParams.WRAP_CONTENT);
            lp.setMargins(10,20,20,30);
            iv.setLayoutParams(lp);
            linear_layout.addView(iv);

            iv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int ide = iv.getId();
                    String b = "pic"+Integer.toString(ide);
                    int id = getResources().getIdentifier(b,"drawable",getActivity().getPackageName());
                    big_picture.setImageResource(id);

                    to_phone=id;
                }
            });
        }

    }
}
