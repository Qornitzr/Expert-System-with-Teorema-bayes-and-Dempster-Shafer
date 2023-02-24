package com.example.sistempakarteodemp.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import com.example.sistempakarteodemp.R;
import com.example.sistempakarteodemp.fragment.HomeFragment;
import com.example.sistempakarteodemp.fragment.MenuFragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.ismaeldivita.chipnavigation.ChipNavigationBar;

public class DasboardUserActivitiy extends AppCompatActivity {

    private ChipNavigationBar chipNavigationBar;
    private FloatingActionButton fAbsen;
    HomeFragment homeFragment;
    MenuFragment menuFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dasboard_user);


        fAbsen = findViewById(R.id.fabAbsen);
        chipNavigationBar = findViewById(R.id.bottom_nav_menu);
        chipNavigationBar.setItemSelected(R.id.bottom_nav_home_users,true);
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout_users,new HomeFragment()).commit();
        bottomMenu();
        fAbsen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DasboardUserActivitiy.this, DiagnosaActivity.class));
                return;
            }
        });
    }

    private void bottomMenu() {
        chipNavigationBar.setOnItemSelectedListener(new ChipNavigationBar.OnItemSelectedListener() {
            @Override
            public void onItemSelected(int i) {
                Fragment fragment = null;
                switch (i){
                    case R.id.bottom_nav_home_users:
                        fragment = new HomeFragment();
                        break;
                        
                    case R.id.bottom_nav_menu_users:
                        fragment = new MenuFragment();
                        break;
                }
                assert fragment != null;
                getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout_users,fragment).commit();
            }
        });
    }

}