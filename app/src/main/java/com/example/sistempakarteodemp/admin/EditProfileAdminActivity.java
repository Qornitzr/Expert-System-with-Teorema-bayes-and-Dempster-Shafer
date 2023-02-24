package com.example.sistempakarteodemp.admin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sistempakarteodemp.DBHelper;
import com.example.sistempakarteodemp.R;
import com.example.sistempakarteodemp.entities.Account;

public class EditProfileAdminActivity extends AppCompatActivity {
    EditText txtNamaAdmin, txtUsername, txtPassword;
    TextView txtUbahPassword;
    CardView cardViewSimpan;
    DBHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile_admin);

        txtNamaAdmin = findViewById(R.id.txt_namaAdmin);
        txtUsername = findViewById(R.id.txt_Username);
        txtPassword = findViewById(R.id.txt_PasswordAdmin);
        txtUbahPassword = findViewById(R.id.txt_UbahPassword);
        cardViewSimpan = findViewById(R.id.cardView_Simpan);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Edit Profile");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        dbHelper = new DBHelper(this);

        Intent intent = getIntent();
        Account account = (Account) intent.getSerializableExtra("account");
        txtNamaAdmin.setText(account.getNama_admin());
        txtUsername.setText(account.getUsername());
        txtPassword.setText(account.getPassword());

        txtUbahPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Account currentAccount = dbHelper.find(account.getId());
                Intent intent = new Intent(EditProfileAdminActivity.this, UbahPasswordActivity.class);
                intent.putExtra("account", currentAccount);
                startActivity(intent);
                finish();
            }
        });
        cardViewSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Account currentAccount = dbHelper.find(account.getId());
                    currentAccount.setNama_admin(txtNamaAdmin.getText().toString());
                    currentAccount.setUsername(txtUsername.getText().toString());
                    currentAccount.setPassword(txtPassword.getText().toString());
                    if (dbHelper.updateAdmin(currentAccount)){
                        Toast.makeText(EditProfileAdminActivity.this, "Berhasil", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(EditProfileAdminActivity.this, DasboardAdminActivity.class);
                        intent.putExtra("account", currentAccount);
                        startActivity(intent);
                        finish();
                    }else{
                        Toast.makeText(EditProfileAdminActivity.this, "Gagal", Toast.LENGTH_SHORT).show();
                    }
                }catch (Exception e){
                    Toast.makeText(EditProfileAdminActivity.this, "Error", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}