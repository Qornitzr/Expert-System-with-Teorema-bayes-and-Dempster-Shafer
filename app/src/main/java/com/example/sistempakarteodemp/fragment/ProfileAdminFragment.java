package com.example.sistempakarteodemp.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.sistempakarteodemp.DBHelper;
import com.example.sistempakarteodemp.admin.DataAdminActivity;
import com.example.sistempakarteodemp.SplashScreen.SplashScreenUser;
import com.example.sistempakarteodemp.admin.EditProfileAdminActivity;
import com.example.sistempakarteodemp.R;
import com.example.sistempakarteodemp.admin.LoginAdminActivity;
import com.example.sistempakarteodemp.admin.TambahAdminActivity;
import com.example.sistempakarteodemp.entities.Account;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

public class ProfileAdminFragment extends Fragment {
    CardView cardViewEditP, cardViewDataAdmin, cardViewLogout;
    DBHelper dbHelper;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile_admin, container, false);
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
        cardViewEditP = getActivity().findViewById(R.id.cardView_EditP);
        cardViewDataAdmin = getActivity().findViewById(R.id.cardView_DataAdmin);
        cardViewLogout = getActivity().findViewById(R.id.cardView_Logout);

        dbHelper = new DBHelper(getContext());
        Intent intent = getActivity().getIntent();
        Account account = (Account) intent.getSerializableExtra("account");

        cardViewDataAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentHome = new Intent(getActivity(), DataAdminActivity.class);
                intentHome.putExtra("account", account);
                startActivity(intentHome);
            }
        });
        cardViewEditP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentHome = new Intent(getActivity(), EditProfileAdminActivity.class);
                intentHome.putExtra("account", account);
                startActivity(intentHome);
            }
        });
        cardViewLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder
                        = new AlertDialog
                        .Builder(getActivity());
                builder.setMessage("Apakah Anda ingin Keluar Sebagai Admin?");
                builder.setTitle("Logout");
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
                                        Boolean updateSession = dbHelper.upgradeSession("kosong", 1);
                                        if (updateSession == true) {
                                            Intent intent = new Intent(getActivity(), LoginAdminActivity.class);
                                            intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                                            getActivity().finish();
                                            startActivity(intent);
                                        }
                                    }
                                });
                builder
                        .setNegativeButton(
                                "Tidak",
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