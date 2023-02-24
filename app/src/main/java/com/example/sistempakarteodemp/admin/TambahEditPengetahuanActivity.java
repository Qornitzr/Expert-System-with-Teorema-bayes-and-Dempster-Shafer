package com.example.sistempakarteodemp.admin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteCursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.sistempakarteodemp.DBHelper;
import com.example.sistempakarteodemp.R;
import com.example.sistempakarteodemp.entities.Account;
import com.example.sistempakarteodemp.entities.Gejala;
import com.example.sistempakarteodemp.entities.Pengetahuan;
import com.example.sistempakarteodemp.entities.Penyakit;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TambahEditPengetahuanActivity extends AppCompatActivity {
    Spinner spinnerNamaPenyakit, spinnerNamaGejala;
    CardView cardViewTambah, cardViewEdit;
    EditText txtProbabilitasMb;
    Double probabilitasMd;
    DBHelper dbHelper;
    List<Penyakit> penyakitList;
    List<Gejala> gejalaList;
    int idPenyakit, idGejala;
    int idPenyakitEdit, idGejalaEdit;
    Intent intent;
    long id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_edit_pengetahuan);
        Toolbar toolbar = findViewById(R.id.toolbar);
        spinnerNamaPenyakit = findViewById(R.id.spinnerNamaPenyakit);
        spinnerNamaGejala = findViewById(R.id.spinnerNamaGejala);
        txtProbabilitasMb = findViewById(R.id.txt_ProbabilitasMB);
        cardViewTambah = findViewById(R.id.cardView_TambahPengetahuan);
        cardViewEdit = findViewById(R.id.cardView_EditPengetahuan);
        dbHelper = new DBHelper(this);

        intent = getIntent();
        Account account1 = (Account) intent.getSerializableExtra("account");
        Pengetahuan pengetahuan1 = (Pengetahuan) intent.getSerializableExtra("idPeng");

        id = intent.getLongExtra("idPeng", 0);

        if (intent.hasExtra("idPeng")){
            setSupportActionBar(toolbar);
            getSupportActionBar().setTitle("Edit Pengetahuan");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onBackPressed();
                }
            });

            String namaPen = pengetahuan1.getNama_penyakit();
            String namaGej = pengetahuan1.getNama_gejala();
            String nilaiMb = String.valueOf(pengetahuan1.getProbabilitas_mb());

            penyakitList = dbHelper.allPenyakit();
            ArrayAdapter dataAdapterPenyakit = new ArrayAdapter<>(this,
                    R.layout.style_spinner, penyakitList);
            spinnerNamaPenyakit.setAdapter( dataAdapterPenyakit);
            spinnerNamaPenyakit.setSelection(getIndex(spinnerNamaPenyakit, namaPen));
            spinnerNamaPenyakit.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    int SpinnerData1 = penyakitList.get(spinnerNamaPenyakit.getSelectedItemPosition()).getId_penyakit();
                    idPenyakitEdit = SpinnerData1;
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {
                    Log.i("Message", "Nothing is selected");

                }
            });

            gejalaList = dbHelper.allGejala();
            ArrayAdapter dataAdapterGejala = new ArrayAdapter<>(this,
                    R.layout.style_spinner, gejalaList);
            spinnerNamaGejala.setAdapter(dataAdapterGejala);
            spinnerNamaGejala.setSelection(getIndexGejala(spinnerNamaGejala, namaGej));
            spinnerNamaGejala.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    int SpinnerData1 = gejalaList.get(spinnerNamaGejala.getSelectedItemPosition()).getId_gejala();
                    idGejalaEdit = SpinnerData1;
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {
                    Log.i("Message", "Nothing is selected");

                }
            });

            txtProbabilitasMb.setText(nilaiMb);

            cardViewTambah.setVisibility(View.GONE);
            cardViewEdit.setVisibility(View.VISIBLE);
        }
        else{
            setSupportActionBar(toolbar);
            getSupportActionBar().setTitle("Tambah Pengetahuan");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onBackPressed();
                }
            });
            penyakitList = dbHelper.allPenyakit();
            ArrayAdapter dataAdapterPenyakit = new ArrayAdapter<>(this,
                    R.layout.style_spinner, penyakitList);
            spinnerNamaPenyakit.setAdapter(dataAdapterPenyakit);

            spinnerNamaPenyakit.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    int SpinnerData1 = penyakitList.get(spinnerNamaPenyakit.getSelectedItemPosition()).getId_penyakit();
                    idPenyakit = SpinnerData1;
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {
                    Log.i("Message", "Nothing is selected");

                }
            });

            gejalaList = dbHelper.allGejala();
            ArrayAdapter dataAdapterGejala = new ArrayAdapter<>(this,
                    R.layout.style_spinner, gejalaList);
            spinnerNamaGejala.setAdapter(dataAdapterGejala);

            spinnerNamaGejala.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    int SpinnerData1 = gejalaList.get(spinnerNamaGejala.getSelectedItemPosition()).getId_gejala();
                    idGejala = SpinnerData1;
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {
                    Log.i("Message", "Nothing is selected");

                }
            });

            cardViewTambah.setVisibility(View.VISIBLE);
            cardViewEdit.setVisibility(View.GONE);
        }



        cardViewEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double nilaiProbabilitasMb = Double.parseDouble(txtProbabilitasMb.getText().toString());
                probabilitasMd = 1.0 - nilaiProbabilitasMb;
                Pengetahuan currentPengetahuan = dbHelper.findPengetahuan(pengetahuan1.getId_pengetahuan());

                currentPengetahuan.setId_penyakit(idPenyakitEdit);
                currentPengetahuan.setId_gejala(idGejalaEdit);
                currentPengetahuan.setProbabilitas_mb(nilaiProbabilitasMb);
                currentPengetahuan.setProbabilitas_md(probabilitasMd);

                if (dbHelper.updatePengetahuan(currentPengetahuan)){
                    Toast.makeText(TambahEditPengetahuanActivity.this, "Berhasil Di Edit", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(TambahEditPengetahuanActivity.this, InfoJumlahDataPengetahuanAdminActivity.class);
                    intent.putExtra("account", account1);
                    startActivity(intent);
                    finish();
                }else{
                    Toast.makeText(TambahEditPengetahuanActivity.this, "Gagal", Toast.LENGTH_SHORT).show();
                }
            }
        });

        cardViewTambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double nilaiProbabilitasMb = Double.parseDouble(txtProbabilitasMb.getText().toString());
                probabilitasMd = 1.0 - nilaiProbabilitasMb;
                Pengetahuan pengetahuan = new Pengetahuan();

                pengetahuan.setId_penyakit(idPenyakit);
                pengetahuan.setId_gejala(idGejala);
                pengetahuan.setProbabilitas_mb(nilaiProbabilitasMb);
                pengetahuan.setProbabilitas_md(probabilitasMd);

                if (nilaiProbabilitasMb > 1.0){
                    txtProbabilitasMb.setError("Nilai Tidak Boleh Lebih Dari 1.0");
                }else if (dbHelper.insertPengetahuan(pengetahuan)){
                    Toast.makeText(TambahEditPengetahuanActivity.this, "Data Berhasil di Tambahkan", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(TambahEditPengetahuanActivity.this, InfoJumlahDataPengetahuanAdminActivity.class);
                    intent.putExtra("account", account1);
                    startActivity(intent);
                    finish();
                }
            }
        });




    }

    private int getIndexGejala(Spinner spinnerNamaGejala, String namaGej) {
        for (int i=0;i<spinnerNamaGejala.getCount();i++){
            if (spinnerNamaGejala.getItemAtPosition(i).toString().equalsIgnoreCase(namaGej)){
                return i;
            }
        }
        return 0;
    }

    private int getIndex(Spinner spinnerNamaPenyakit, String namaPen) {
        for (int i=0;i<spinnerNamaPenyakit.getCount();i++){
            if (spinnerNamaPenyakit.getItemAtPosition(i).toString().equalsIgnoreCase(namaPen)){
                return i;
            }
        }
        return 0;
    }
}