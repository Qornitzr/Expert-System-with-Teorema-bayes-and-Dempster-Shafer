package com.example.sistempakarteodemp.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.sistempakarteodemp.DBHelper;
import com.example.sistempakarteodemp.R;
import com.example.sistempakarteodemp.entities.Riwayat;
import com.example.sistempakarteodemp.fragment.DempsterShaferFragment;
import com.example.sistempakarteodemp.fragment.TeoremaBayesFragment;
import com.google.android.material.tabs.TabLayout;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class HasilDiagnosaActivity extends AppCompatActivity {
    ImageView imgSelect;
    TabLayout tabLayout;
    ViewPager viewPager;
    Toolbar toolbar;
    String dataNamaPenyakit, currentDateandTime, namaGejala;
    double dataPresentase;
    DBHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hasil_diagnosa);
        toolbar = findViewById(R.id.toolbar);
        tabLayout = findViewById(R.id.tabLayout);
        viewPager = findViewById(R.id.viewPage);
        dbHelper = new DBHelper(this);

        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss", Locale.getDefault());

        currentDateandTime = sdf.format(new Date());
        namaGejala = getIntent().getStringExtra("namagejala");



        setSupportActionBar(toolbar);
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
                        TeoremaBayesFragment teoremaBayesFragment = new TeoremaBayesFragment();
                        return teoremaBayesFragment;
                    case 1:
                        DempsterShaferFragment dempsterShaferFragment = new DempsterShaferFragment();
                        return dempsterShaferFragment;
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


    public void data(String penyakit, double presentase){
//        tvPenyakit = findViewById(R.id.tv_penyakit);
//        tvPresentase = findViewById(R.id.tv_presentase);
//        tvPenyakit.setText(penyakit);
//        tvPresentase.setText(String.format("%.2f%%", presentase));

        Log.i("data",  dataNamaPenyakit+" "+dataPresentase);
        Log.i("gejala",  namaGejala);
        Log.i("date", currentDateandTime);
        imgSelect = findViewById(R.id.img_select);
        imgSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dataNamaPenyakit = penyakit;
                dataPresentase = presentase;

                Riwayat riwayat = new Riwayat();
                riwayat.setRiwayat_nama_gejala(namaGejala);
                riwayat.setRiwayat_nama_penyakit(dataNamaPenyakit);
                riwayat.setRiwayat_nilai(String.format("%.0f%%", presentase));
                riwayat.setTanggal(currentDateandTime);

                if (dbHelper.insertRiwayat(riwayat)){
                    Toast.makeText(HasilDiagnosaActivity.this, "Hasil Diagnosa di Simpan pada Menu Riwayat", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(HasilDiagnosaActivity.this, DasboardUserActivitiy.class);
                    startActivity(intent);
                    finish();
                }
            }
        });
    }
}