package com.example.sistempakarteodemp.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.View;

import com.example.sistempakarteodemp.DBHelper;
import com.example.sistempakarteodemp.R;
import com.example.sistempakarteodemp.fragment.DempsterShaferFragment;
import com.example.sistempakarteodemp.fragment.RiwayatDempsFragment;
import com.example.sistempakarteodemp.fragment.RiwayatTeoremaFragment;
import com.example.sistempakarteodemp.fragment.TeoremaBayesFragment;
import com.google.android.material.tabs.TabLayout;

public class HasilRiwayatDiagnosaActivity extends AppCompatActivity {
    TabLayout tabLayout;
    ViewPager viewPager;
    Toolbar toolbar;
    String dataNamaPenyakit, currentDateandTime, namaGejala;
    double dataPresentase;
    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hasil_riwayat_diagnosa);

        toolbar = findViewById(R.id.toolbar);
        tabLayout = findViewById(R.id.tabLayout);
        viewPager = findViewById(R.id.viewPage);
        dbHelper = new DBHelper(this);

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Riwayat Diagnosa");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        tabLayout.addTab(tabLayout.newTab().setText("Teorema Bayes"));
        tabLayout.addTab(tabLayout.newTab().setText("Demspter Shafer"));

        viewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager(),FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
            @NonNull
            @Override
            public Fragment getItem(int position) {
                switch (position){
                    case 0:
                        RiwayatTeoremaFragment riwayatTeoremaFragment = new RiwayatTeoremaFragment();
                        return riwayatTeoremaFragment;
                    case 1:
                        RiwayatDempsFragment riwayatDempsFragment = new RiwayatDempsFragment();
                        return riwayatDempsFragment;
                    default:
                        return null;
                }
            }

            @Override
            public int getCount() {
                return tabLayout.getTabCount();
            }
        });

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }
}