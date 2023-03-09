package com.example.customlistwithnavigationwithrecicleview;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.customlistwithnavigationwithrecicleview.Fragments.AdvancedJsonFragment;
import com.example.customlistwithnavigationwithrecicleview.Fragments.HomeFragment;
import com.example.customlistwithnavigationwithrecicleview.Fragments.WallpaperFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;


public class MainActivity extends AppCompatActivity {

    public static String url = "https://sinka.lv/android_end_work.html";

    BottomNavigationView navigationView;

    TextView headerText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        navigationView = findViewById(R.id.navigation_view);
        headerText = findViewById(R.id.tv_header_name);

        navigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if(item.isChecked()){
                    return false;
                }
                switch (item.getItemId()){

                    case R.id.nav_home: {
                        startHomeFragment();
                        headerText.setText("HOME");
                        return true;
                    }

                    case R.id.nav_wallpaper:{
                        startWallpaperFragment();
                        headerText.setText("CHANGE WALLPAPER");
                        return true;
                    }

                    case R.id.nav_json_homework:{
                        startJsonHomeworkFragment();
                        headerText.setText("SIMPLE JSON BLOG");
                        return true;
                    }

                    case R.id.nav_json_advanced:{
                        startJsonAdvancedFragment();
                        headerText.setText("ADVANCED JSON");
                        return true;
                    }

                    case R.id.nav_lucky_choice:{
                        startLuckyChoiceFragment();
                        headerText.setText("EXAM");
                        return true;
                    }


                }

                return false;
            }
        });

        if(savedInstanceState == null) {
            headerText.setText("HOME");
            startHomeFragment();
            navigationView.getMenu().getItem(0).setChecked(true);
        }


    }

    private void  startFragment(Fragment fragment){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.setCustomAnimations(R.anim.enter,R.anim.exit,R.anim.pop_enter,R.anim.pop_exit);
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
        startFragment(new WallpaperFragment());
    }

    private void startLuckyChoiceFragment() {

    }

}