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
import com.example.sistempakarteodemp.adapter.PengetahuanAdapter;
import com.example.sistempakarteodemp.admin.DasboardAdminActivity;
import com.example.sistempakarteodemp.admin.InfoJumlahDataPengetahuanAdminActivity;
import com.example.sistempakarteodemp.entities.Pengetahuan;

import java.util.ArrayList;
import java.util.List;

public class InfoJumlahDataPengetahuanActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    List<Pengetahuan> pengetahuanList = new ArrayList<Pengetahuan>();
    PengetahuanAdapter pengetahuanAdapter;
    DBHelper dbHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_jumlah_data_pengetahuan);
        Toolbar toolbar = findViewById(R.id.toolbar);

        recyclerView = findViewById(R.id.recycler_view);
        dbHelper = new DBHelper(this);

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Data Pengetahuan");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(InfoJumlahDataPengetahuanActivity.this, DasboardUserActivitiy.class);
                startActivity(intent);
                finish();
            }
        });

        pengetahuanList.addAll(dbHelper.allPengetahuan());
        pengetahuanAdapter = new PengetahuanAdapter(pengetahuanList, this);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(pengetahuanAdapter);

        pengetahuanAdapter.setOnItemClickListenerPengetahuan(new PengetahuanAdapter.OnClickListenerPengetahuan() {
            @Override
            public void onItemClickPengetahuan(long id) {

            }
        });
    }
}