package com.example.sistempakarteodemp.admin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;

import com.example.sistempakarteodemp.DBHelper;
import com.example.sistempakarteodemp.R;
import com.example.sistempakarteodemp.fragment.HomeAdminFragment;
import com.example.sistempakarteodemp.fragment.HomeFragment;
import com.example.sistempakarteodemp.fragment.MenuFragment;
import com.example.sistempakarteodemp.fragment.ProfileAdminFragment;
import com.ismaeldivita.chipnavigation.ChipNavigationBar;

public class DasboardAdminActivity extends AppCompatActivity {
    ChipNavigationBar chipNavigationBar;
    DBHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dasboard_admin);

        chipNavigationBar = findViewById(R.id.bottom_nav_menu_admin);
        chipNavigationBar.setItemSelected(R.id.bottom_nav_home_admin,true);
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout_admin,new HomeAdminFragment()).commit();
        bottomMenu();


        dbHelper = new DBHelper(this);
        Boolean checkSession = dbHelper.checkSession("ada");
        if (checkSession == false){
            Intent intent = new Intent(DasboardAdminActivity.this, LoginAdminActivity.class);
            startActivity(intent);
            finish();
        }
    }

    private void bottomMenu() {
        chipNavigationBar.setOnItemSelectedListener(new ChipNavigationBar.OnItemSelectedListener() {
            @Override
            public void onItemSelected(int i) {
                Fragment fragment = null;
                switch (i){
                    case R.id.bottom_nav_home_admin:
                        fragment = new HomeAdminFragment();
                        break;

                    case R.id.bottom_nav_profile_admin:
                        fragment = new ProfileAdminFragment();
                        break;
                }
                assert fragment != null;
                getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout_admin,fragment).commit();
            }
        });
    }
}