package com.example.listofduty;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    BottomNavigationView botnav_Main;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        botnav_Main = findViewById(R.id.botnavMain);
        botnav_Main.setItemIconTintList(null);
        TextView showName = findViewById(R.id.textName);

        openFragment(HomeFragment.newInstance("Adolfo"));
        botnav_Main.setOnItemSelectedListener(item -> {
            if(item.getItemId()==R.id.itemHome) {
                openFragment(HomeFragment.newInstance("Adolfo"));
                item.setChecked(true);
            } else if(item.getItemId()==R.id.itemProfile) {
                openFragment(ProfileFragment.newInstance("Adolfo"));
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