package com.example.sistempakarteodemp.admin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.sistempakarteodemp.DBHelper;
import com.example.sistempakarteodemp.R;
import com.example.sistempakarteodemp.adapter.AccountAdapter;
import com.example.sistempakarteodemp.entities.Account;
import com.example.sistempakarteodemp.fragment.HomeAdminFragment;
import com.example.sistempakarteodemp.fragment.ProfileAdminFragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import static android.icu.lang.UCharacter.DecompositionType.VERTICAL;

public class DataAdminActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    List<Account> listAccount = new ArrayList<Account>();
    AccountAdapter accountAdapter;
    View dasboardAdminActivity;
    DBHelper dbHelper;
    Account account;
    CardView cardViewHapus;
    FloatingActionButton fbTambahAdmin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_admin);
        Toolbar toolbar = findViewById(R.id.toolbar);
        fbTambahAdmin = findViewById(R.id.fb_TambahAdmin);
        dasboardAdminActivity = findViewById(R.id.bottom_nav_profile_admin);
        Intent intent = getIntent();
        Account account1 = (Account) intent.getSerializableExtra("account");

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Data Admin");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        dbHelper = new DBHelper(this);
        recyclerView = findViewById(R.id.recycler_view);

        listAccount.addAll(dbHelper.allAdmin());
        accountAdapter = new AccountAdapter(listAccount, this);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(accountAdapter);

        fbTambahAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DataAdminActivity.this, TambahAdminActivity.class);
                intent.putExtra("account", account1);
                startActivity(intent);
                finish();
            }
        });

        accountAdapter.setOnItemClickListenerAccount(new AccountAdapter.OnClickListenerAccount() {
            @Override
            public void onItemClickAccount(long id) {
                LayoutInflater layoutInflater = LayoutInflater.from(DataAdminActivity.this);
                View view = layoutInflater.inflate(R.layout.input_form_data_admin, null);
                cardViewHapus = view.findViewById(R.id.cardView_Hapus);

                Dialog dialogForm = new Dialog(DataAdminActivity.this);
                dialogForm.setContentView(view);
                dialogForm.setCancelable(true);
                dialogForm.setOnShowListener(new DialogInterface.OnShowListener() {
                    @Override
                    public void onShow(DialogInterface dialog) {
                        cardViewHapus.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                AlertDialog.Builder builder = new AlertDialog.Builder(DataAdminActivity.this);
                                builder.setTitle("Konfirmasi");
                                builder.setMessage("Apa Anda Yakin Untuk Menghapus?");
                                builder.setPositiveButton("OK!", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        Boolean hapus = dbHelper.deleteAdmin(id);
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