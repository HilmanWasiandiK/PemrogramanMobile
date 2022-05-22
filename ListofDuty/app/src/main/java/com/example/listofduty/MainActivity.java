package com.example.listofduty;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView bottomnavigation_Main;
        bottomnavigation_Main = findViewById(R.id.bottomnavigationMain);
        bottomnavigation_Main.setItemIconTintList(null);

        openFragment(new HomeFragment());
        bottomnavigation_Main.setOnItemSelectedListener(item -> {
            if(item.getItemId()==R.id.itemHome) {
                openFragment(new HomeFragment());
                item.setChecked(true);
            } else if(item.getItemId()==R.id.itemProfile) {
                openFragment(new ProfileFragment());
                item.setChecked(true);
            }
            return false;
        });
    }

    void openFragment(Fragment fragment) {
        if(fragment != null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.layoutBotNav, fragment).commit();
        }
    }
}