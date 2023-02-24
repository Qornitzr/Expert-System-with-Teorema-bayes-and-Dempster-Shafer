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
import com.example.sistempakarteodemp.adapter.GejalaAdapter;
import com.example.sistempakarteodemp.entities.Account;
import com.example.sistempakarteodemp.entities.Gejala;

import java.util.ArrayList;
import java.util.List;

public class InfoJumlahDataGejalaActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    List<Gejala> gejalaList = new ArrayList<Gejala>();
    GejalaAdapter gejalaAdapter;
    DBHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_jumlah_data_gejala);
        Toolbar toolbar = findViewById(R.id.toolbar);

        recyclerView = findViewById(R.id.recycler_view);

        dbHelper = new DBHelper(this);

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Data Gejala");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        gejalaList.addAll(dbHelper.allGejala());
        gejalaAdapter = new GejalaAdapter(gejalaList, this);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(gejalaAdapter);
    }
}