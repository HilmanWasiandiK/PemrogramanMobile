package com.example.listofduty;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import android.os.Bundle;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView navigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        navigation = findViewById(R.id.bottom_nav);
        navigation.setItemIconTintList(null);

        openFragment(new HomeFragment());
        navigation.setOnItemSelectedListener(item -> {
            if(item.getItemId()==R.id.home) {
                openFragment(new HomeFragment());
                item.setChecked(true);
            } else if(item.getItemId()==R.id.profile) {
                openFragment(new ProfileFragment());
                item.setChecked(true);
            }
            return false;
        });
    }

    void openFragment(Fragment fragment) {
        if(fragment != null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.nav_content, fragment).commit();
        }
    }
}