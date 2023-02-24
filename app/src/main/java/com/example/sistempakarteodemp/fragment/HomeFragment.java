package com.example.sistempakarteodemp.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.sistempakarteodemp.Activity.InfoJumlahDataGejalaActivity;
import com.example.sistempakarteodemp.Activity.InfoJumlahDataPengetahuanActivity;
import com.example.sistempakarteodemp.Activity.InfoJumlahDataPenyakitActivity;
import com.example.sistempakarteodemp.Activity.KeteranganPenyakitActivity;
import com.example.sistempakarteodemp.DBHelper;
import com.example.sistempakarteodemp.R;


import androidx.activity.OnBackPressedCallback;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

public class HomeFragment extends Fragment {
    private TextView jumPen, jumGej, jumPeng, jumInfoPen;
    private CardView cardViewDatPen, cardViewDatGej, cardViewDatPeng;
    DBHelper dbHelper;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
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
        jumPen = getActivity().findViewById(R.id.txt_JumlahPen);
        jumGej = getActivity().findViewById(R.id.txt_jumlahGej);
        jumPeng = getActivity().findViewById(R.id.txt_jumlahPeng);
        cardViewDatPen = getActivity().findViewById(R.id.cardDatPen);
        cardViewDatGej = getActivity().findViewById(R.id.cardDatGej);
        cardViewDatPeng = getActivity().findViewById(R.id.cardDatPeng);

        dbHelper = new DBHelper(getContext());

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
                startActivity(new Intent(getActivity(), InfoJumlahDataPenyakitActivity.class));

            }
        });
        cardViewDatGej.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), InfoJumlahDataGejalaActivity.class));
            }
        });
        cardViewDatPeng.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), InfoJumlahDataPengetahuanActivity.class));
            }
        });
    }

//
//    @Override
//    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
//        super.onViewCreated(view, savedInstanceState);
//
//    }


}