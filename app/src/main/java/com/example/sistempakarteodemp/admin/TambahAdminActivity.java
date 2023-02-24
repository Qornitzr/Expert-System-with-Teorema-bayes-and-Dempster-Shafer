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
import com.example.sistempakarteodemp.fragment.HomeAdminFragment;
import com.example.sistempakarteodemp.fragment.ProfileAdminFragment;

public class TambahAdminActivity extends AppCompatActivity {
    EditText txtNamaAdmin, txtUsername, txtPassword, txtConfPassword;
    CardView cardViewTambah;
    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_admin);

        txtNamaAdmin = findViewById(R.id.txt_namaAdmin);
        txtUsername = findViewById(R.id.txt_Username);
        txtPassword = findViewById(R.id.txt_PasswordAdmin);
        txtConfPassword = findViewById(R.id.txt_ConfirmPasswordAdmin);
        cardViewTambah = findViewById(R.id.cardView_Tambah);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Tambah Admin");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        Intent intent = getIntent();
        Account account = (Account) intent.getSerializableExtra("account");

        dbHelper = new DBHelper(this);
        
        cardViewTambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                try {
                    Account account1 = new Account();
                    String strNamaAdmin = txtNamaAdmin.getText().toString();
                    String strUsername = txtUsername.getText().toString();
                    String strPassword = txtPassword.getText().toString();
                    String strConfPassword = txtConfPassword.getText().toString();
                    account1.setNama_admin(strNamaAdmin);
                    account1.setUsername(strUsername);
                    account1.setPassword(strPassword);
                    Account temp = dbHelper.checkUsername(strUsername);
                    if (strNamaAdmin.isEmpty() || strUsername.isEmpty() || strPassword.isEmpty() || strConfPassword.isEmpty()){
                        Toast.makeText(TambahAdminActivity.this, "Data Harus di Isi Semua!", Toast.LENGTH_SHORT).show();
                    }else {
                        if (temp == null){
                            if (strPassword.equals(strConfPassword)){
                                if (dbHelper.insertAdmin(account1)){
                                    Toast.makeText(TambahAdminActivity.this, "Data Berhasil di Tambahkan", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(TambahAdminActivity.this, DataAdminActivity.class);
                                    intent.putExtra("account", account);
                                    startActivity(intent);
                                    finish();
                                }
                            }else{
                                Toast.makeText(TambahAdminActivity.this, "Password Tidak Sama!", Toast.LENGTH_SHORT).show();
                            }
                        }else{
                            Toast.makeText(TambahAdminActivity.this, "Username Sudah Di Gunakan", Toast.LENGTH_SHORT).show();
                        }
                    }

                    
//                }catch (Exception e){
//                    Toast.makeText(TambahAdminActivity.this, "Error", Toast.LENGTH_SHORT).show();
//                }
                
            }
        });
    }
}

