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
import com.example.sistempakarteodemp.adapter.RiwayatAdapter;
import com.example.sistempakarteodemp.entities.Riwayat;

import java.util.ArrayList;
import java.util.List;

public class RiwayatDiagnosaActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    List<Riwayat> riwayatList = new ArrayList<Riwayat>();
    RiwayatAdapter riwayatAdapter;
    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_riwayat_diagnosa);
        Toolbar toolbar = findViewById(R.id.toolbar);
        recyclerView = findViewById(R.id.recycler_view);
        dbHelper = new DBHelper(this);

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Data Riwayat");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        riwayatList.addAll(dbHelper.allRiwayat());
        riwayatAdapter = new RiwayatAdapter(riwayatList, this);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(riwayatAdapter);

        riwayatAdapter.setOnItemClickListenerRiwayat(new RiwayatAdapter.OnClickListenerRiwayat() {
            @Override
            public void onItemClickRiwayat(long id) {
                Intent intentEdit =  new Intent(RiwayatDiagnosaActivity.this, HasilRiwayatDiagnosaActivity.class);
                intentEdit.putExtra("idRiwayat", dbHelper.findRiwayat(id));
                startActivity(intentEdit);
            }
        });
    }
}