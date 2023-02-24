package com.example.sistempakarteodemp.admin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.sistempakarteodemp.DBHelper;
import com.example.sistempakarteodemp.R;
import com.example.sistempakarteodemp.entities.Account;
import com.example.sistempakarteodemp.entities.Gejala;
import com.example.sistempakarteodemp.entities.Penyakit;

import java.util.ArrayList;
import java.util.List;

public class TambahEditGejalaActivity extends AppCompatActivity {
    EditText txtNamaGejala;
    CardView cardViewTambah, cardViewEdit;
    DBHelper dbHelper;
    List<Gejala> gejalaList = new ArrayList<Gejala>();
    long id;
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_edit_gejala);
        Toolbar toolbar = findViewById(R.id.toolbar);
        txtNamaGejala = findViewById(R.id.txt_NamaGejala);
        cardViewTambah = findViewById(R.id.cardView_TambahGejala);
        cardViewEdit = findViewById(R.id.cardView_EditGejala);

        dbHelper = new DBHelper(this);

        intent = getIntent();
        Account account1 = (Account) intent.getSerializableExtra("account");
        Gejala gejala = (Gejala) intent.getSerializableExtra("id");

        id = intent.getLongExtra("id", 0);

        if (intent.hasExtra("id")){
            setSupportActionBar(toolbar);
            getSupportActionBar().setTitle("Edit Gejala");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onBackPressed();
                }
            });
            String namaP = gejala.getNama_gejala();

            txtNamaGejala.setText(namaP);

            cardViewTambah.setVisibility(View.GONE);
            cardViewEdit.setVisibility(View.VISIBLE);
        }
        else{
            setSupportActionBar(toolbar);
            getSupportActionBar().setTitle("Data Gejala");
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
                Gejala currentGejala = dbHelper.findGejala(gejala.getId_gejala());
                currentGejala.setNama_gejala(txtNamaGejala.getText().toString());
                if (dbHelper.updateGejala(currentGejala)){
                    Toast.makeText(TambahEditGejalaActivity.this, "Berhasil Di Edit", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(TambahEditGejalaActivity.this, InfoJumlahDataGejalaAdminActivity.class);
                    intent.putExtra("account", account1);
                    startActivity(intent);
                    finish();
                }else{
                    Toast.makeText(TambahEditGejalaActivity.this, "Gagal", Toast.LENGTH_SHORT).show();
                }
            }
        });

        cardViewTambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Gejala gejala1 = new Gejala();
                String strNamaGejala = txtNamaGejala.getText().toString();

                gejala1.setNama_gejala(strNamaGejala);

                if (strNamaGejala.isEmpty()){
                   txtNamaGejala.setError("Harus Di Isi!");
                }else{
                    if (dbHelper.insertGejala(gejala1)){
                        Toast.makeText(TambahEditGejalaActivity.this, "Data Berhasil di Tambahkan", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(TambahEditGejalaActivity.this, InfoJumlahDataGejalaAdminActivity.class);
                        intent.putExtra("account", account1);
                        startActivity(intent);
                        finish();
                    }
                }
            }
        });
    }
}