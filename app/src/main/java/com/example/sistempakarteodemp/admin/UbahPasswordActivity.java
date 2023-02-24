package com.example.sistempakarteodemp.admin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sistempakarteodemp.DBHelper;
import com.example.sistempakarteodemp.R;
import com.example.sistempakarteodemp.entities.Account;

public class UbahPasswordActivity extends AppCompatActivity {

    EditText txtPasswordLama, txtPasswordBaru, txtPasswordConf;
    CardView cardViewSimpan;
    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ubah_password);
        txtPasswordLama = findViewById(R.id.txt_CurrentPassword);
        txtPasswordBaru = findViewById(R.id.txt_PasswordBaru);
        txtPasswordConf = findViewById(R.id.txt_ConfPassword);
        cardViewSimpan = findViewById(R.id.cardView_SimpanPassword);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Ubah Password");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        dbHelper = new DBHelper(this);

        Intent intent = getIntent();
        Account account = (Account) intent.getSerializableExtra("account");
        cardViewSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {

                String strPwLama = txtPasswordLama.getText().toString();
                String strPwBaru = txtPasswordBaru.getText().toString();
                String strPwConf = txtPasswordConf.getText().toString();
                String pw = account.getPassword();
                if (strPwLama.isEmpty() || strPwBaru.isEmpty() || strPwConf.isEmpty()){
                    Toast.makeText(UbahPasswordActivity.this, "Isi Semua Data", Toast.LENGTH_SHORT).show();
                }else{
                    Account currentAccount = dbHelper.find(account.getId());
                        if (strPwLama.equals(currentAccount.getPassword())){
                            if (strPwBaru.equals(strPwConf)){
                                if (strPwBaru.equals(currentAccount.getPassword())){
                                    Toast.makeText(UbahPasswordActivity.this, "Password Tidak Boleh Sama", Toast.LENGTH_SHORT).show();
                                }else{
                                    currentAccount.setPassword(strPwBaru);
                                    if (dbHelper.updateAdmin(currentAccount)){
                                        Toast.makeText(UbahPasswordActivity.this, "Berhasil Silahkan Login Kembali", Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(UbahPasswordActivity.this, LoginAdminActivity.class);
                                        startActivity(intent);
                                        finish();
                                    }
                                }
                            }else{
                                Toast.makeText(UbahPasswordActivity.this, "Password Baru dan Konfirmasi Password Tidak Sama!", Toast.LENGTH_SHORT).show();
                            }
                        }else{
                            Toast.makeText(UbahPasswordActivity.this, "Password Lama Salah!", Toast.LENGTH_SHORT).show();
                        }
                    }

                }catch (Exception e){
                    Toast.makeText(UbahPasswordActivity.this, "Error", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}