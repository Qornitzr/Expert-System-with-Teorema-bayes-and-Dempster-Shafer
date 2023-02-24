package com.example.sistempakarteodemp.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckedTextView;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.sistempakarteodemp.DBHelper;
import com.example.sistempakarteodemp.R;
//import com.example.sistempakarteodemp.adapter.DiagnosaAdapter;
//import com.example.sistempakarteodemp.adapter.DiagnosaAdapter2;
import com.example.sistempakarteodemp.entities.Gejala;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class DiagnosaActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    ListView listView;
    ListAdapter adapter;
    List<Gejala> gejalaList = new ArrayList<Gejala>();
    ArrayList<Gejala> arrayList = new ArrayList<>();
//    DiagnosaAdapter2 diagnosaAdapter2;
    DBHelper dbHelper;
    FloatingActionButton fbDiagnosa;
    ImageView imgSelect;
    CheckedTextView checkedTextView;
    String[] id;
    String[] daftar;
    CardView cardView;
    static int check = 0;
    @Override
    @SuppressLint("WrongConstant")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diagnosa);
        Toolbar toolbar = findViewById(R.id.toolbar_diagnosa);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        imgSelect = findViewById(R.id.img_select);
        listView = findViewById(R.id.listView_Diagnosa);

        dbHelper = new DBHelper(this);
        arrayList.addAll(dbHelper.allGejala());




        dbHelper = new DBHelper(this);

        Cursor cursor = dbHelper.getReadableDatabase().rawQuery("SELECT * FROM gejala ORDER BY id_gejala ASC", null);
        id = new String[cursor.getCount()];
        daftar = new String[cursor.getCount()];

        for (int cc = 0; cc < cursor.getCount(); cc++){

            cursor.moveToPosition(cc);
            daftar[cc] = cursor.getString(1);

        }

        adapter = new ArrayAdapter(this, R.layout.diagnosa_layout2, R.id.ckbx_NamaGejala2, daftar);
        listView.setAdapter(adapter);
        listView.setTextFilterEnabled(true);
        LayoutInflater inflater = getLayoutInflater();
        View dialogLayout = inflater.inflate(R.layout.diagnosa_layout2, null);
        checkedTextView = dialogLayout.findViewById(R.id.ckbx_NamaGejala2);



        imgSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String namagejala ="";
                int c = 0;
                SparseBooleanArray sparseBooleanArray  = listView.getCheckedItemPositions();

                for (int i = 0; i < listView.getCount(); i++) {
                    if (sparseBooleanArray.get(i)){
                        if (namagejala =="") {
                            namagejala =  '\"' + listView.getItemAtPosition(i).toString() + '\"';
                            c = 1;
                        }
                        else{
                            namagejala = namagejala +  ", \"" + listView.getItemAtPosition(i).toString() + '\"';
                            c = c +1;
                        }
                    }
                }

                if(c<2){
                    Toast.makeText(DiagnosaActivity.this, "Pastikan anda memilih 2 gejala lebih", Toast.LENGTH_SHORT).show();
                }else {
                    Intent intent = new Intent(DiagnosaActivity.this, HasilDiagnosaActivity.class);
                    intent.putExtra("namagejala", namagejala);
                    startActivity(intent);
                }
                Log.i("s", namagejala);

            }
        });

    }


}