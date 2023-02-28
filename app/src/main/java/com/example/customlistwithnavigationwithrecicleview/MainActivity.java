package com.example.customlistwithnavigationwithrecicleview;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.customlistwithnavigationwithrecicleview.Fragments.AdvancedJsonFragment;
import com.example.customlistwithnavigationwithrecicleview.Fragments.HomeFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;


public class MainActivity extends AppCompatActivity {

    public static String url = "https://sinka.lv/android_end_work.html";

    BottomNavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        navigationView = findViewById(R.id.navigation_view);
        navigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if(item.isChecked()){
                    return false;
                }
                switch (item.getItemId()){

                    case R.id.nav_home: {
                        startHomeFragment();
                        return true;
                    }

                    case R.id.nav_wallpaper:{
                        startWallpaperFragment();
                        return true;
                    }

                    case R.id.nav_json_homework:{
                        startJsonHomeworkFragment();
                        return true;
                    }

                    case R.id.nav_json_advanced:{
                        startJsonAdvancedFragment();
                        return true;
                    }

                    case R.id.nav_lucky_choice:{
                        startLuckyChoiceFragment();
                        return true;
                    }


                }
                return false;
            }
        });

    }

    private void  startFragment(Fragment fragment){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fl_fragment_container,fragment);
        transaction.commit();
    }



    private void startHomeFragment() {
        startFragment(new HomeFragment());
    }

    private void startJsonAdvancedFragment() {
        startFragment(new AdvancedJsonFragment());
    }

    private void startJsonHomeworkFragment() {

    }

    private void startWallpaperFragment() {

    }

    private void startLuckyChoiceFragment() {

    }

}