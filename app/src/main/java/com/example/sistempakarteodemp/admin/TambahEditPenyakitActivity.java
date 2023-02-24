package com.example.sistempakarteodemp.admin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.sistempakarteodemp.DBHelper;
import com.example.sistempakarteodemp.R;
import com.example.sistempakarteodemp.entities.Account;
import com.example.sistempakarteodemp.entities.Penyakit;

import java.util.ArrayList;
import java.util.List;

public class TambahEditPenyakitActivity extends AppCompatActivity {
    EditText txtNamaPenyakit, txtDeskripsi, txtSaran;
    CardView cardViewTambah, cardViewEdit;
    DBHelper dbHelper;
    long id;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_edit_penyakit);
        Toolbar toolbar = findViewById(R.id.toolbar);
        txtNamaPenyakit = findViewById(R.id.txt_NamaPenyakit);
        txtDeskripsi = findViewById(R.id.txt_Deskripsi);
        txtSaran = findViewById(R.id.txt_Saran);
        cardViewTambah = findViewById(R.id.cardView_TambahPenyakit);
        cardViewEdit = findViewById(R.id.cardView_EditPenyakitA);

        dbHelper = new DBHelper(this);

        intent = getIntent();
        Account account1 = (Account) intent.getSerializableExtra("account");
        Penyakit penyakit1 = (Penyakit) intent.getSerializableExtra("id");



        id = intent.getLongExtra("id", 0);

        if (intent.hasExtra("id")){
            setSupportActionBar(toolbar);
            getSupportActionBar().setTitle("Edit Penyakit");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onBackPressed();
                }
            });
            String namaP = penyakit1.getNama_penyakit();
            String deskripsiP = penyakit1.getDeskripsi();
            String saranP = penyakit1.getSaran();

            txtNamaPenyakit.setText(namaP);
            txtDeskripsi.setText(deskripsiP);
            txtSaran.setText(saranP);

            cardViewTambah.setVisibility(View.GONE);
            cardViewEdit.setVisibility(View.VISIBLE);
        }
        else{
            setSupportActionBar(toolbar);
            getSupportActionBar().setTitle("Tambah Penyakit");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onBackPressed();
                }
            });
            cardViewTambah.setVisibility(View.VISIBLE);
            cardViewEdit.setVisibility(View.GONE);
        }

        cardViewEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Penyakit currentPenyakit = dbHelper.findPenyakit(penyakit1.getId_penyakit());
                currentPenyakit.setNama_penyakit(txtNamaPenyakit.getText().toString());
                currentPenyakit.setDeskripsi(txtDeskripsi.getText().toString());
                currentPenyakit.setSaran(txtSaran.getText().toString());
                if (dbHelper.updatePenyakit(currentPenyakit)){
                    Toast.makeText(TambahEditPenyakitActivity.this, "Berhasil Di Edit", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(TambahEditPenyakitActivity.this, InfoJumlahDataPenyakitAdminActivity.class);
                    intent.putExtra("account", account1);
                    startActivity(intent);
                    finish();
                }else{
                    Toast.makeText(TambahEditPenyakitActivity.this, "Gagal", Toast.LENGTH_SHORT).show();
                }
            }
        });

        cardViewTambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Penyakit penyakit1 = new Penyakit();
                String strNamaPenyakit = txtNamaPenyakit.getText().toString();
                String strDeskripsi = txtDeskripsi.getText().toString();
                String strSaran = txtSaran.getText().toString();

                penyakit1.setNama_penyakit(strNamaPenyakit);
                penyakit1.setDeskripsi(strDeskripsi);
                penyakit1.setSaran(strSaran);

                if (strNamaPenyakit.isEmpty() || strDeskripsi.isEmpty() || strSaran.isEmpty()){
                    Toast.makeText(TambahEditPenyakitActivity.this, "Data Harus di Isi Semua!", Toast.LENGTH_SHORT).show();
                }else{
                    if (dbHelper.insertPenyakit(penyakit1)){
                        Toast.makeText(TambahEditPenyakitActivity.this, "Data Berhasil di Tambahkan", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(TambahEditPenyakitActivity.this, InfoJumlahDataPenyakitAdminActivity.class);
                        intent.putExtra("account", account1);
                        startActivity(intent);
                        finish();
                    }
                }
            }
        });
    }
}