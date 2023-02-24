package com.example.sistempakarteodemp.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sistempakarteodemp.Activity.InfoJumlahDataGejalaActivity;
import com.example.sistempakarteodemp.Activity.InfoJumlahDataPengetahuanActivity;
import com.example.sistempakarteodemp.Activity.InfoJumlahDataPenyakitActivity;
import com.example.sistempakarteodemp.Activity.KeteranganPenyakitActivity;
import com.example.sistempakarteodemp.DBHelper;
import com.example.sistempakarteodemp.R;
import com.example.sistempakarteodemp.admin.DataAdminActivity;
import com.example.sistempakarteodemp.admin.InfoJumlahDataGejalaAdminActivity;
import com.example.sistempakarteodemp.admin.InfoJumlahDataPengetahuanAdminActivity;
import com.example.sistempakarteodemp.admin.InfoJumlahDataPenyakitAdminActivity;
import com.example.sistempakarteodemp.entities.Account;

import java.util.Timer;
import java.util.TimerTask;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

public class HomeAdminFragment extends Fragment {
    TextView jumPen, jumGej, jumPeng, namaAdmin;
    CardView cardViewDatPen, cardViewDatGej, cardViewDatPeng;
    DBHelper dbHelper;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home_admin, container, false);
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivity().getApplicationContext();

        OnBackPressedCallback callback = new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {

                AlertDialog.Builder builder
                        = new AlertDialog
                        .Builder(getActivity());
                builder.setMessage("Apa Ingin Keluar?");
                builder.setTitle("Keluar");
                builder.setCancelable(false);
                builder.setPositiveButton("Iya", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        getActivity().finish();
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
        };
        requireActivity().getOnBackPressedDispatcher().addCallback(this, callback);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        jumPen = getActivity().findViewById(R.id.txt_jumlahPenAdmin);
        jumGej = getActivity().findViewById(R.id.txt_jumlahGejAdmin);
        jumPeng = getActivity().findViewById(R.id.txt_jumlahPengAdmin);
        namaAdmin = getActivity().findViewById(R.id.txt_Hello);
        cardViewDatPen = getActivity().findViewById(R.id.cardDatPenAdmin);
        cardViewDatGej = getActivity().findViewById(R.id.cardDatGejAdmin);
        cardViewDatPeng = getActivity().findViewById(R.id.cardDatPengAdmin);


        dbHelper = new DBHelper(getContext());

        Intent intent = getActivity().getIntent();
        Account account = (Account) intent.getSerializableExtra("account");

        namaAdmin.setText("Hello, "+ account.getNama_admin());
        int penyakitCount = dbHelper.getPenyakitCount();
        int gejalaCount = dbHelper.getGejalaCount();
        int pengetahuanCount = dbHelper.getPengetahuanCount();
        dbHelper.close();

        jumPen.setText(String.valueOf(penyakitCount));
        jumGej.setText(String.valueOf(gejalaCount));
        jumPeng.setText(String.valueOf(pengetahuanCount));
        cardViewDatPen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentHome = new Intent(getActivity(), InfoJumlahDataPenyakitAdminActivity.class);
                intentHome.putExtra("account", account);
                startActivity(intentHome);
            }
        });
        cardViewDatGej.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentHome = new Intent(getActivity(), InfoJumlahDataGejalaAdminActivity.class);
                intentHome.putExtra("account", account);
                startActivity(intentHome);
            }
        });
        cardViewDatPeng.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentHome = new Intent(getActivity(), InfoJumlahDataPengetahuanAdminActivity.class);
                intentHome.putExtra("account", account);
                startActivity(intentHome);
            }
        });
    }

}