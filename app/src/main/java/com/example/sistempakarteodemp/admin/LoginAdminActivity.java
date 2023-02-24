package com.example.sistempakarteodemp.admin;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sistempakarteodemp.Activity.DasboardUserActivitiy;
import com.example.sistempakarteodemp.DBHelper;
import com.example.sistempakarteodemp.R;
import com.example.sistempakarteodemp.SplashScreen.SplashScreenAdmin;
import com.example.sistempakarteodemp.entities.Account;

public class LoginAdminActivity extends AppCompatActivity {

    TextView txtUsername, txtPassword;
    Button btnLogin;
    DBHelper myDB;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_admin);

        txtUsername = findViewById(R.id.txt_Username);
        txtPassword = findViewById(R.id.txt_Password);
        btnLogin = findViewById(R.id.btn_Login);
        myDB = new DBHelper(this);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = txtUsername.getText().toString();
                String password = txtPassword.getText().toString();
                Account account = myDB.login(username, password);

                if (username.equals("") ){
                    txtUsername.setError("Tolong Masukan Username");
//                    Toast.makeText(LoginAdminActivity.this, "Tolong Masukan Username dan Password!", Toast.LENGTH_LONG).show();
                }else if (password.equals("")){
                    txtPassword.setError("Tolong Masukan Password");
                }else{
                   // Boolean checkUsernamePass = myDB.checkUsernamePassword(username, password);
                    if (account == null){
                        AlertDialog.Builder builder
                                = new AlertDialog
                                .Builder(LoginAdminActivity.this);
                        builder.setMessage("Password Salah Silahkan Login Kembali");
                        builder.setPositiveButton("OK!", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                                txtUsername.setText("");
                                txtPassword.setText("");
                            }
                        });
                        builder.setCancelable(false);
                        AlertDialog alertDialog = builder.create();
                        alertDialog.show();
                    }else{
                        Boolean updateSession = myDB.upgradeSession("ada", 1);
                        if (updateSession == true){
                            Intent intentHome = new Intent(LoginAdminActivity.this, SplashScreenAdmin.class);
                            intentHome.putExtra("account", account);
                            startActivity(intentHome);
                            finish();
                        } else {
                            Toast.makeText(LoginAdminActivity.this, "Invalid login", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }
        });
    }

    @Override
    public void onBackPressed() {

        AlertDialog.Builder builder
                = new AlertDialog
                .Builder(LoginAdminActivity.this);
        builder.setMessage("Apa Ingin Kembali Ke Halaman Users?");
        builder.setTitle("Keluar");
        builder.setCancelable(false);
        builder.setPositiveButton("Iya", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(LoginAdminActivity.this, DasboardUserActivitiy.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                startActivity(intent);
                finish();
            }
        });
        builder.setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}