package com.example.sistempakarteodemp.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.sistempakarteodemp.admin.LoginAdminActivity;
import com.example.sistempakarteodemp.Activity.RiwayatDiagnosaActivity;
import com.example.sistempakarteodemp.Activity.TentangActivity;
import com.example.sistempakarteodemp.R;
import com.example.sistempakarteodemp.admin.TambahAdminActivity;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

public class MenuFragment extends Fragment {
    CardView cardViewRiwayat, cardViewTentang, cardViewLogin;
    TextView textViewTambah;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false);
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

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        cardViewRiwayat = getActivity().findViewById(R.id.cardRiwayatHasilDiagnosa);
        cardViewTentang = getActivity().findViewById(R.id.cardTentang);
        cardViewLogin = getActivity().findViewById(R.id.cardLoginAdmin);
        textViewTambah = getActivity().findViewById(R.id.txt_Tambah);

        textViewTambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), TambahAdminActivity.class));
            }
        });
        cardViewRiwayat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), RiwayatDiagnosaActivity.class));
            }
        });
        cardViewTentang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), TentangActivity.class));
            }
        });
        cardViewLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder
                        = new AlertDialog
                        .Builder(getActivity());
                builder.setMessage("Apakah Anda Admin?");
                builder.setTitle("Login");
                builder.setCancelable(false);
                builder
                        .setPositiveButton(
                                "Iya",
                                new DialogInterface
                                        .OnClickListener() {

                                    @Override
                                    public void onClick(DialogInterface dialog,
                                                        int which)
                                    {

                                        startActivity(new Intent(getActivity(), LoginAdminActivity.class));
                                        getActivity().finish();
                                    }
                                });
                builder
                        .setNegativeButton(
                                "Bukan",
                                new DialogInterface
                                        .OnClickListener() {

                                    @Override
                                    public void onClick(DialogInterface dialog,
                                                        int which)
                                    {

                                        // If user click no
                                        // then dialog box is canceled.
                                        dialog.cancel();
                                    }
                                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });

    }

}