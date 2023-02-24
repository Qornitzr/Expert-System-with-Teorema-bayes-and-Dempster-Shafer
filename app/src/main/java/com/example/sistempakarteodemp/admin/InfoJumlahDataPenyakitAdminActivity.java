package com.example.sistempakarteodemp.admin;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import com.example.sistempakarteodemp.DBHelper;
import com.example.sistempakarteodemp.R;
import com.example.sistempakarteodemp.adapter.PenyakitAdapter;
import com.example.sistempakarteodemp.entities.Account;
import com.example.sistempakarteodemp.entities.Penyakit;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class InfoJumlahDataPenyakitAdminActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    List<Penyakit> penyakitList = new ArrayList<Penyakit>();
    PenyakitAdapter penyakitAdapter;
    DBHelper dbHelper;
    CardView cardViewHapus, cardViewEdit;
    FloatingActionButton fbTambah;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_jumlah_data_penyakit_admin);
        Toolbar toolbar = findViewById(R.id.toolbar);

        recyclerView = findViewById(R.id.recycler_view);
        fbTambah = findViewById(R.id.fb_TambahPenyakit);


        dbHelper = new DBHelper(this);

        Intent intent = getIntent();
        Account account1 = (Account) intent.getSerializableExtra("account");

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Data Penyakit");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });



        penyakitList.addAll(dbHelper.allPenyakit());
        penyakitAdapter = new PenyakitAdapter(penyakitList, this);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(penyakitAdapter);

        fbTambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(InfoJumlahDataPenyakitAdminActivity.this, TambahEditPenyakitActivity.class);
                intent.putExtra("account", account1);
                startActivity(intent);
                finish();
            }
        });
        penyakitAdapter.setOnItemClickListenerPenyakit(new PenyakitAdapter.OnClickListenerPenyakit() {
            @Override
            public void onItemClickPenyakit(long id) {
                LayoutInflater layoutInflater = LayoutInflater.from(InfoJumlahDataPenyakitAdminActivity.this);
                View view = layoutInflater.inflate(R.layout.input_form_penyakit_gejala_pengetahuan, null);

                cardViewEdit = view.findViewById(R.id.cardView_EditPenyakit);
                cardViewHapus = view.findViewById(R.id.cardView_HapusPenyakit);

                Dialog dialogForm = new Dialog(InfoJumlahDataPenyakitAdminActivity.this);
                dialogForm.setContentView(view);
                dialogForm.setCancelable(true);
                dialogForm.setOnShowListener(new DialogInterface.OnShowListener() {
                    @Override
                    public void onShow(DialogInterface dialog) {
                        cardViewEdit.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intentEdit =  new Intent(InfoJumlahDataPenyakitAdminActivity.this, TambahEditPenyakitActivity.class);
                                intentEdit.putExtra("account", account1);
                                intentEdit.putExtra("id", dbHelper.findPenyakit(id));
                                startActivity(intentEdit);
                                dialogForm.dismiss();
                            }
                        });
                        cardViewHapus.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                AlertDialog.Builder builder = new AlertDialog.Builder(InfoJumlahDataPenyakitAdminActivity.this);
                                builder.setTitle("Konfirmasi");
                                builder.setMessage("Apa Anda Yakin Untuk Menghapus?");
                                builder.setPositiveButton("OK!", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        Boolean hapus = dbHelper.deletePeyakit(id);
                                        if (hapus==true){
                                            dialogForm.dismiss();
                                            refresh();
                                        }
                                    }
                                });
                                builder.setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {

                                    }
                                });
                                builder.setCancelable(true);
                                AlertDialog alertDialog = builder.create();
                                alertDialog.show();

                            }
                        });
                    }
                });
                dialogForm.show();
            }
        });


    }
    public void refresh() {
        Intent intent = getIntent();
        overridePendingTransition(0, 0);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        finish();
        overridePendingTransition(0, 0);
        startActivity(intent);
    }
}