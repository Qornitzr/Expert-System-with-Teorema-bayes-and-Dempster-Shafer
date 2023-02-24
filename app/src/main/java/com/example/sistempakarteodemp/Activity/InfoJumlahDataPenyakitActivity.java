package com.example.sistempakarteodemp.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.sistempakarteodemp.DBHelper;
import com.example.sistempakarteodemp.R;
import com.example.sistempakarteodemp.adapter.PenyakitAdapter;
import com.example.sistempakarteodemp.admin.DasboardAdminActivity;
import com.example.sistempakarteodemp.admin.InfoJumlahDataPenyakitAdminActivity;
import com.example.sistempakarteodemp.entities.Account;
import com.example.sistempakarteodemp.entities.Penyakit;

import java.util.ArrayList;
import java.util.List;

public class InfoJumlahDataPenyakitActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    List<Penyakit> penyakitList = new ArrayList<Penyakit>();
    PenyakitAdapter penyakitAdapter;
    DBHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_jumlah_data_penyakit);

        Toolbar toolbar = findViewById(R.id.toolbar);
        recyclerView = findViewById(R.id.recycler_view);

        dbHelper = new DBHelper(this);

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Data Penyakit");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(InfoJumlahDataPenyakitActivity.this, DasboardUserActivitiy.class);
                startActivity(intent);
                finish();
            }
        });

        penyakitList.addAll(dbHelper.allPenyakit());
        penyakitAdapter = new PenyakitAdapter(penyakitList, this);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(penyakitAdapter);

        penyakitAdapter.setOnItemClickListenerPenyakit(new PenyakitAdapter.OnClickListenerPenyakit() {
            @Override
            public void onItemClickPenyakit(long id) {

            }
        });
    }
}