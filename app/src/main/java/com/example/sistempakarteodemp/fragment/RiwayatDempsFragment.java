package com.example.sistempakarteodemp.fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sistempakarteodemp.Activity.HasilDiagnosaActivity;
import com.example.sistempakarteodemp.DBHelper;
import com.example.sistempakarteodemp.R;
import com.example.sistempakarteodemp.entities.Riwayat;

import java.util.ArrayList;
import java.util.List;


public class RiwayatDempsFragment extends Fragment {

    protected ListAdapter adapter;
    protected Cursor cursor1; // get nama penyakit
    protected Cursor cursor2; // get sum mb

    DBHelper dbHelper;

    protected ListView list_view;
    List<Double> listMValueSebelumnya = new ArrayList<Double>();
    List<String> listMTextSebelumnya = new ArrayList<String>();

    String  namaPenyakitTerpilih, namaPenyakit, namaSaran_terbesar, namaDesk_terbesar;
    String[] penyakit_terpilih;
    String[] gejala_terpilih;

    double[] probabilitas_terpilih;
    Double sumTeta = 0.0;
    String hasilPenyakit = "", hasilPersen = "", hasilGejala = "";
    int gejalaKe = 0;
    Double maxValue, presentase;
    double nilai_mb;
    int i;
    Intent intent;
    String intentGejala;
    TextView text1, text2, textSaran, textDesk;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @SuppressLint("WrongViewCast")
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {

        super.onActivityCreated(savedInstanceState);

        intent = getActivity().getIntent();
        Riwayat riwayat = (Riwayat) intent.getSerializableExtra("idRiwayat");

        intentGejala = riwayat.getRiwayat_nama_gejala();
        dbHelper = new DBHelper(getContext());
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        list_view = getView().findViewById(R.id.lv_viewpenyakit);

        String rule1 = "\"Apakah hasil konsepsi masih baik dalam kandungan?\", " +
                "\"Apakah terasa mulas sedikit?\", \"Apakah mulut rahim/cervix masih dalam kondisi menutup?\", " +
                "\"Apakah terasa nyeri perut bagian bawah?\", " +
                "\"Apakah terjadi perdarahan pervaginam?\", " +
                "\"Apakah tes kehamilan/hcg masih positif?\"";
        String rule2 = "\"Apakah hasil konsepsi masih berada dalam kavum uteri dan dalam proses pengeluaran?\", " +
                "\"Apakah kehamilan tidak dapat dipertahankan?\", " +
                "\"Apakah terasa mulas karena kontraksi yang sering dan kuat?\", " +
                "\"Apakah mulut rahim/cervix terbuka?\", " +
                "\"Apakah terasa nyeri lebih kuat?\", " +
                "\"Apakah terjadi perdarahan lebih banyak/sesuai umur kehamilan?\", " +
                "\"Apakah teraba jaringan kehamilan di mulut rahim?\", " +
                "\"Apakah tes kehamilan/hcg masih positif?\"";
        String rule3 = "\" Apakah mulut rahim/cervix terbuka?\", " +
                "\"Apakah terasa nyeri lebih kuat?\", " +
                "\"Apakah terjadi perdarahan hebat?\", " +
                "\"Apakah terjadi sebagian hasil konsepsi telah keluar dan masih ada yang tertinggal?\", " +
                "\"Apakah sering menyebabkan syok?\", " +
                "\"Apakah teraba jaringan kehamilan di mulut rahim?\", " +
                "\"Apakah tes kehamilan/hcg masih positif?\"";
        String rule4 = "\"Apakah mulut rahim/cervix tertutup?\", " +
                "\"Apakah terasa nyeri perut berkurang?\", " +
                "\"Apakah terjadi perdarahan sedikit-sedikit lalu berkurang?\", " +
                "\"Apakah terjadi seluruh hasil konsepsi telah dikeluarkan?\", " +
                "\"Apakah tes kehamilan/hcg masih positif sampai 7-10 hari setelah abortus?\", " +
                "\"Apakah uterus/rahim mengecil?\"";
        String rule5 = "\"Apakah cairan/jaringan berbau busuk?\", " +
                "\"Apakah terasa nyeri tekan menggigil?\", " +
                "\"Apakah terjadi perdarahan pervaginam?\", " +
                "\"Apakah sering menyebabkan syok?\", " +
                "\"Apakah terjadi panas tinggi?\", " +
                "\"Apakah terjadi takikardi/jantung berdebar-debar/denyut jantung cepat?\", " +
                "\"Apakah tampak sakit dan lelah?\", " +
                "\"Apakah tes kehamilan/hcg masih positif?\", ";
        String rule6 = "\"Apakah besar rahim tidak sesuai dengan umur kehamilan (tidak berkembang)?\", " +
                "\"Apakah janin telah mati dalam kandungan selama 6-8 minggu tapi belum keluar?\", " +
                "\"Apakah terasa nyeri perut berkurang?\", " +
                "\"Apakah terjadi perdarahan sedikit-sedikit lalu berkurang?\", " +
                "\"Apakah tes kehamilan/hcg negatif?\", ";

        String rule7 = "\"Apakah gejala perdarahan antara rata-rata usia kehamilan 12-14 minggu?\", " +
                "\"Apakah keluarnya gelembung-gelembung putih/bening dari vagina?\", " +
                "\"Apakah terasa mual hebat atau muntah hebat?\", " +
                "\"Apakah terjadi perdarahan sedikit-sedikit lalu sekaligus banyak?\", " +
                "\"Apakah terjadi perkembangan lebih pesat, sehingga umumnya uterus/rahim lebih besar dari usia kehamilan?\", " +
                "\"Apakah terjadi pusing hebat?\", " +
                "\"Apakah sering menyebabkan syok?\", ";

        String rule8 = "\"Apakah terasa nyeri perut bagian bawah?\", " +
                "\"Apakah terjadi perdarahan tidak banyak dan berwarna coklat tua?\", " +
                "\"Apakah terjadi sakit perut/nyeri mendadak yang kemudian dususul syok atau pingsan?\", " +
                "\"Apakah tes kehamilan/hcg masih positif?\", ";

        String rule9 = "\"Apakah ketika hasil pemeriksaan usg adalah janin tidak berkembang?\", " +
                "\"Apakah terasa mual atau muntah yang tidak begitu dirasakan?\", " +
                "\"Apakah terasa nyeri perut bagian bawah?\", " +
                "\"Apakah terjadi perdarahan pervaginam?\", " +
                "\"Apakah tes kehamilan/hcg masih positif?\", ";

        String hasilRule1 = "1";
        String hasilRule2 = "2";
        String hasilRule3 = "3";
        String hasilRule4 = "4";
        String hasilRule5 = "5";
        String hasilRule6 = "6";
        String hasilRule7 = "7";
        String hasilRule8 = "8";
        String hasilRule9 = "9";


        if (intentGejala.equals(rule1)){
            cursor2 = db.rawQuery("SELECT p.nama_penyakit, p.saran, p.deskripsi from rule r inner join penyakit p on r.id_pen = p.id_penyakit where p.id_penyakit ='"+ hasilRule1 +"' group by p.id_penyakit order by count(p.nama_penyakit) ASC", null);
            for (int u = 0; u < cursor2.getCount(); u++){
                cursor2.moveToPosition(u);
                namaPenyakit = cursor2.getString(0);
                namaSaran_terbesar = cursor2.getString(1);
                namaDesk_terbesar = cursor2.getString(2);
            }
            presentase = 100.0;
            text1 = (TextView) getView().findViewById(R.id.textView2);
            text2 = (TextView) getView().findViewById(R.id.textView3);
            textSaran = (TextView) getView().findViewById(R.id.tv_SaranPenyakit);
            textDesk = (TextView) getView().findViewById(R.id.tv_DeskripsiPenyakit);
            text1.setText(String.format("%.0f%%", presentase));
            text2.setText(namaPenyakit);
            textSaran.setText(namaSaran_terbesar);
            textDesk.setText(namaDesk_terbesar);
//            System.out.println("Max Final "+ namaPenyakit +" - "+hasilPersen+" %");


        } else if (intentGejala.equals(rule2)) {
            cursor2 = db.rawQuery("SELECT p.nama_penyakit, p.saran, p.deskripsi from rule r inner join penyakit p on r.id_pen = p.id_penyakit where p.id_penyakit ='"+ hasilRule2 +"' group by p.id_penyakit order by count(p.nama_penyakit) ASC", null);
            for (int u = 0; u < cursor2.getCount(); u++){
                cursor2.moveToPosition(u);
                namaPenyakit = cursor2.getString(0);
                namaSaran_terbesar = cursor2.getString(1);
                namaDesk_terbesar = cursor2.getString(2);
            }
            presentase = 100.0;
            text1 = (TextView) getView().findViewById(R.id.textView2);
            text2 = (TextView) getView().findViewById(R.id.textView3);
            textSaran = (TextView) getView().findViewById(R.id.tv_SaranPenyakit);
            textDesk = (TextView) getView().findViewById(R.id.tv_DeskripsiPenyakit);
            text1.setText(String.format("%.0f%%", presentase));
            text2.setText(namaPenyakit);
            textSaran.setText(namaSaran_terbesar);
            textDesk.setText(namaDesk_terbesar);
//            System.out.println("Max Final "+ namaPenyakit +" - "+hasilPersen+" %");


        }else if (intentGejala.equals(rule3)) {
            cursor2 = db.rawQuery("SELECT p.nama_penyakit, p.saran, p.deskripsi from rule r inner join penyakit p on r.id_pen = p.id_penyakit where p.id_penyakit ='"+ hasilRule3 +"' group by p.id_penyakit order by count(p.nama_penyakit) ASC", null);
            for (int u = 0; u < cursor2.getCount(); u++){
                cursor2.moveToPosition(u);
                namaPenyakit = cursor2.getString(0);
                namaSaran_terbesar = cursor2.getString(1);
                namaDesk_terbesar = cursor2.getString(2);
            }
            presentase = 100.0;
            text1 = (TextView) getView().findViewById(R.id.textView2);
            text2 = (TextView) getView().findViewById(R.id.textView3);
            textSaran = (TextView) getView().findViewById(R.id.tv_SaranPenyakit);
            textDesk = (TextView) getView().findViewById(R.id.tv_DeskripsiPenyakit);
            text1.setText(String.format("%.0f%%", presentase));
            text2.setText(namaPenyakit);
            textSaran.setText(namaSaran_terbesar);
            textDesk.setText(namaDesk_terbesar);
//            System.out.println("Max Final "+ namaPenyakit +" - "+hasilPersen+" %");


        }else if (intentGejala.equals(rule4)) {
            cursor2 = db.rawQuery("SELECT p.nama_penyakit, p.saran, p.deskripsi from rule r inner join penyakit p on r.id_pen = p.id_penyakit where p.id_penyakit ='"+ hasilRule4 +"' group by p.id_penyakit order by count(p.nama_penyakit) ASC", null);
            for (int u = 0; u < cursor2.getCount(); u++){
                cursor2.moveToPosition(u);
                namaPenyakit = cursor2.getString(0);
                namaSaran_terbesar = cursor2.getString(1);
                namaDesk_terbesar = cursor2.getString(2);
            }
            presentase = 100.0;
            text1 = (TextView) getView().findViewById(R.id.textView2);
            text2 = (TextView) getView().findViewById(R.id.textView3);
            textSaran = (TextView) getView().findViewById(R.id.tv_SaranPenyakit);
            textDesk = (TextView) getView().findViewById(R.id.tv_DeskripsiPenyakit);
            text1.setText(String.format("%.0f%%", presentase));
            text2.setText(namaPenyakit);
            textSaran.setText(namaSaran_terbesar);
            textDesk.setText(namaDesk_terbesar);
//            System.out.println("Max Final "+ namaPenyakit +" - "+hasilPersen+" %");


        }else if (intentGejala.equals(rule5)) {
            cursor2 = db.rawQuery("SELECT p.nama_penyakit, p.saran, p.deskripsi from rule r inner join penyakit p on r.id_pen = p.id_penyakit where p.id_penyakit ='"+ hasilRule5 +"' group by p.id_penyakit order by count(p.nama_penyakit) ASC", null);
            for (int u = 0; u < cursor2.getCount(); u++){
                cursor2.moveToPosition(u);
                namaPenyakit = cursor2.getString(0);
                namaSaran_terbesar = cursor2.getString(1);
                namaDesk_terbesar = cursor2.getString(2);
            }
            presentase = 100.0;
            text1 = (TextView) getView().findViewById(R.id.textView2);
            text2 = (TextView) getView().findViewById(R.id.textView3);
            textSaran = (TextView) getView().findViewById(R.id.tv_SaranPenyakit);
            textDesk = (TextView) getView().findViewById(R.id.tv_DeskripsiPenyakit);
            text1.setText(String.format("%.0f%%", presentase));
            text2.setText(namaPenyakit);
            textSaran.setText(namaSaran_terbesar);
            textDesk.setText(namaDesk_terbesar);
//            System.out.println("Max Final "+ namaPenyakit +" - "+hasilPersen+" %");


        }else if (intentGejala.equals(rule6)) {
            cursor2 = db.rawQuery("SELECT p.nama_penyakit, p.saran, p.deskripsi from rule r inner join penyakit p on r.id_pen = p.id_penyakit where p.id_penyakit ='"+ hasilRule6 +"' group by p.id_penyakit order by count(p.nama_penyakit) ASC", null);
            for (int u = 0; u < cursor2.getCount(); u++){
                cursor2.moveToPosition(u);
                namaPenyakit = cursor2.getString(0);
                namaSaran_terbesar = cursor2.getString(1);
                namaDesk_terbesar = cursor2.getString(2);
            }
            presentase = 100.0;
            text1 = (TextView) getView().findViewById(R.id.textView2);
            text2 = (TextView) getView().findViewById(R.id.textView3);
            textSaran = (TextView) getView().findViewById(R.id.tv_SaranPenyakit);
            textDesk = (TextView) getView().findViewById(R.id.tv_DeskripsiPenyakit);
            text1.setText(String.format("%.0f%%", presentase));
            text2.setText(namaPenyakit);
            textSaran.setText(namaSaran_terbesar);
            textDesk.setText(namaDesk_terbesar);
//            System.out.println("Max Final "+ namaPenyakit +" - "+hasilPersen+" %");


        }else if (intentGejala.equals(rule7)) {
            cursor2 = db.rawQuery("SELECT p.nama_penyakit, p.saran, p.deskripsi from rule r inner join penyakit p on r.id_pen = p.id_penyakit where p.id_penyakit ='"+ hasilRule7 +"' group by p.id_penyakit order by count(p.nama_penyakit) ASC", null);
            for (int u = 0; u < cursor2.getCount(); u++){
                cursor2.moveToPosition(u);
                namaPenyakit = cursor2.getString(0);
                namaSaran_terbesar = cursor2.getString(1);
                namaDesk_terbesar = cursor2.getString(2);
            }
            presentase = 100.0;
            text1 = (TextView) getView().findViewById(R.id.textView2);
            text2 = (TextView) getView().findViewById(R.id.textView3);
            textSaran = (TextView) getView().findViewById(R.id.tv_SaranPenyakit);
            textDesk = (TextView) getView().findViewById(R.id.tv_DeskripsiPenyakit);
            text1.setText(String.format("%.0f%%", presentase));
            text2.setText(namaPenyakit);
            textSaran.setText(namaSaran_terbesar);
            textDesk.setText(namaDesk_terbesar);
//            System.out.println("Max Final "+ namaPenyakit +" - "+hasilPersen+" %");


        }else if (intentGejala.equals(rule8)) {
            cursor2 = db.rawQuery("SELECT p.nama_penyakit, p.saran, p.deskripsi from rule r inner join penyakit p on r.id_pen = p.id_penyakit where p.id_penyakit ='"+ hasilRule8 +"' group by p.id_penyakit order by count(p.nama_penyakit) ASC", null);
            for (int u = 0; u < cursor2.getCount(); u++){
                cursor2.moveToPosition(u);
                namaPenyakit = cursor2.getString(0);
                namaSaran_terbesar = cursor2.getString(1);
                namaDesk_terbesar = cursor2.getString(2);
            }
            presentase = 100.0;
            text1 = (TextView) getView().findViewById(R.id.textView2);
            text2 = (TextView) getView().findViewById(R.id.textView3);
            textSaran = (TextView) getView().findViewById(R.id.tv_SaranPenyakit);
            textDesk = (TextView) getView().findViewById(R.id.tv_DeskripsiPenyakit);
            text1.setText(String.format("%.0f%%", presentase));
            text2.setText(namaPenyakit);
            textSaran.setText(namaSaran_terbesar);
            textDesk.setText(namaDesk_terbesar);
//            System.out.println("Max Final "+ namaPenyakit +" - "+hasilPersen+" %");


        }else if (intentGejala.equals(rule9)) {
            cursor2 = db.rawQuery("SELECT p.nama_penyakit, p.saran, p.deskripsi from rule r inner join penyakit p on r.id_pen = p.id_penyakit where p.id_penyakit ='"+ hasilRule9 +"' group by p.id_penyakit order by count(p.nama_penyakit) ASC", null);
            for (int u = 0; u < cursor2.getCount(); u++){
                cursor2.moveToPosition(u);
                namaPenyakit = cursor2.getString(0);
                namaSaran_terbesar = cursor2.getString(1);
                namaDesk_terbesar = cursor2.getString(2);
            }
            presentase = 100.0;
            text1 = (TextView) getView().findViewById(R.id.textView2);
            text2 = (TextView) getView().findViewById(R.id.textView3);
            textSaran = (TextView) getView().findViewById(R.id.tv_SaranPenyakit);
            textDesk = (TextView) getView().findViewById(R.id.tv_DeskripsiPenyakit);
            text1.setText(String.format("%.0f%%", presentase));
            text2.setText(namaPenyakit);
            textSaran.setText(namaSaran_terbesar);
            textDesk.setText(namaDesk_terbesar);
//            System.out.println("Max Final "+ namaPenyakit +" - "+hasilPersen+" %");


        }else {
            cursor1 = db.rawQuery("SELECT r.probabilitas_mb, g.nama_gejala, g.penyakit_terpilih from rule r inner join penyakit p on r.id_pen = p.id_penyakit inner join gejala g on r.id_gej = g.id_gejala where g.nama_gejala in (" + intentGejala + ") group by g.nama_gejala order by g.id_gejala ASC", null, null);

            hasilPenyakit = "";
            hasilPersen = "";
            gejalaKe = 0;

            gejala_terpilih = new String[cursor1.getCount()];
            probabilitas_terpilih = new double[cursor1.getCount()];
            penyakit_terpilih = new String[cursor1.getCount()];

            for (i = 0; i < cursor1.getCount(); i++) {
                cursor1.moveToPosition(i);
                probabilitas_terpilih[i] = cursor1.getDouble(0);
                gejala_terpilih[i] = cursor1.getString(1);
                penyakit_terpilih[i] = cursor1.getString(2);

                namaPenyakitTerpilih = penyakit_terpilih[i];
                nilai_mb = probabilitas_terpilih[i];
                if (nilai_mb == 1.0){
                    nilai_mb = nilai_mb - 0.001;
                }
                List<Double> listMValuePerkalian = new ArrayList<Double>();
                List<String> listMTextPerkalian = new ArrayList<String>();
                gejalaKe = gejalaKe + 1;
                sumTeta = 0.0;
                if(gejalaKe==1){
                    double mPenyakit = nilai_mb;
                    double mTeta = 1 - mPenyakit;
                    listMValueSebelumnya.add(mPenyakit);
                    listMTextSebelumnya.add(namaPenyakitTerpilih);
                    listMValueSebelumnya.add(mTeta);
                    listMTextSebelumnya.add("Teta");
                    for(int j=0;j<listMValueSebelumnya.size();j++){
                        double mValueSebelumnya = listMValueSebelumnya.get(j);
                        String mTextSebelumnya = listMTextSebelumnya.get(j);
                        Log.i("Gejala = 1", "Gejala ke-"+gejalaKe+" ("+mTextSebelumnya+" "+mValueSebelumnya+")");
                    }
                }else if (gejalaKe>1){
                    for(int j=0;j<listMValueSebelumnya.size();j++) {
                        String mPenyakitText = namaPenyakitTerpilih;
                        double mPenyakit = nilai_mb;

                        double mValueSebelumnya = listMValueSebelumnya.get(j);
                        String mTextSebelumnya = listMTextSebelumnya.get(j);

                        double hasilPerkalian = mValueSebelumnya * mPenyakit;
                        listMValuePerkalian.add(hasilPerkalian);
                        listMTextPerkalian.add(cekSameStringPenyakit(mPenyakitText,mTextSebelumnya));

                        if(cekSameStringPenyakit(mPenyakitText,mTextSebelumnya).equals("Teta")){
                            sumTeta = sumTeta + hasilPerkalian;
                        }


                        Log.i("Gejala > 1", "Gejala ke-"+gejalaKe+" "+mPenyakitText+" X "+mTextSebelumnya+" -> "+cekSameStringPenyakit(mPenyakitText,mTextSebelumnya)+" = "+hasilPerkalian);
                    }
                    for(int j=0;j<listMValueSebelumnya.size();j++){
                        double mPenyakit = nilai_mb;
                        double mTeta = 1 - mPenyakit;
                        double mValueSebelumnya = listMValueSebelumnya.get(j);
                        String mTextSebelumnya = listMTextSebelumnya.get(j);
                        double hasilPerkalian = mValueSebelumnya * mTeta;
                        listMValuePerkalian.add(hasilPerkalian);
                        listMTextPerkalian.add(mTextSebelumnya);
                        Log.i("teta", "Gejala ke-"+gejalaKe+" "+mTextSebelumnya+" = "+hasilPerkalian);
                    }
                    cekMax(listMTextPerkalian,listMValuePerkalian);
                }
            }
            cursor2 = db.rawQuery("SELECT p.nama_penyakit, p.saran, p.deskripsi from rule r inner join penyakit p on r.id_pen = p.id_penyakit where p.id_penyakit ='"+ hasilPenyakit +"' group by p.id_penyakit order by count(p.nama_penyakit) ASC", null);
            for (int u = 0; u < cursor2.getCount(); u++){
                cursor2.moveToPosition(u);
                namaPenyakit = cursor2.getString(0);
                namaSaran_terbesar = cursor2.getString(1);
                namaDesk_terbesar = cursor2.getString(2);
            }
            text1 = (TextView) getView().findViewById(R.id.textView2);
            text2 = (TextView) getView().findViewById(R.id.textView3);
            textSaran = (TextView) getView().findViewById(R.id.tv_SaranPenyakit);
            textDesk = (TextView) getView().findViewById(R.id.tv_DeskripsiPenyakit);
            text1.setText(String.format("%.0f%%", presentase));
            text2.setText(namaPenyakit);
            textSaran.setText(namaSaran_terbesar);
            textDesk.setText(namaDesk_terbesar);
            System.out.println("Max Final "+ namaPenyakit +" - "+hasilPersen+" %");
        }
    }


    public void cekMax(List<String>arr1, List<Double>arr2){
        List<String> listTextUnik = new ArrayList<String>(arr1);
        List<Double> listValueUnik = new ArrayList<Double>();
        double tetaValue = 0;
        Object[] st = listTextUnik.toArray();
        for (Object s : st) {
            if (listTextUnik.indexOf(s) != listTextUnik.lastIndexOf(s)) {
                listTextUnik.remove(listTextUnik.lastIndexOf(s));
            }
        }
        //SUM
        for(int i=0;i<listTextUnik.size();i++){
            double sum = 0;
            for(int j=0;j<arr1.size();j++){
                if(listTextUnik.get(i).equals(arr1.get(j))){
                    sum = sum + arr2.get(j);
                }
            }
            listValueUnik.add(sum);
        }

        //Hasil Bukan Teta
        for(int j=0;j<listTextUnik.size();j++){
            if(!listTextUnik.get(j).equals("Teta")){
                listValueUnik.set(j,listValueUnik.get(j)/(1-sumTeta));
                tetaValue = tetaValue + (listValueUnik.get(j));
            }
        }

        //Hasil Teta
        for(int j=0;j<listTextUnik.size();j++){
            if(listTextUnik.get(j).equals("Teta")){
                listValueUnik.set(j,1-tetaValue);
            }
        }

        //Find Max
        double max = 0;
        String maxText = "";
        for(int j=0;j<listValueUnik.size();j++){
            if(listValueUnik.get(j)>max){
                max = listValueUnik.get(j);
                maxText = listTextUnik.get(j);
            }
        }

        maxValue = max;
        presentase = maxValue * 100;
        hasilPenyakit = maxText;
        hasilPersen = String.valueOf(presentase);
        listMValueSebelumnya = new ArrayList<Double>(listValueUnik);
        listMTextSebelumnya = new ArrayList<String>(listTextUnik);
    }

    public static String cekSameStringPenyakit(String str1, String str2){
        String result = "";
        int jml = 0;



        String[] array1 = str1.split("");
        String[] array2 = str2.split(",");
        for(int i=0;i<array1.length;i++){
            for(int j=0;j<array2.length;j++){
                if(array1[i].equals(array2[j])){
                    result = result + array1[i];
                    jml = jml + 1;
                }
            }
        }


        if(jml>0){
            if(jml==2){
                String resultAdd = result.substring(0,1) +","+result.substring(1,2);
                result = resultAdd;
            }else if(jml==3){
                String resultAdd = result.substring(0,1) +","+result.substring(1,2) +","+result.substring(2,3);
                result = resultAdd;
            }else if(jml==4){
                String resultAdd = result.substring(0,1) +","+result.substring(1,2) +","+result.substring(2,3) +","+result.substring(3,4);
                result = resultAdd;
            }else if(jml==5){
                String resultAdd = result.substring(0,1) +","+result.substring(1,2)+","+result.substring(2,3)+","+result.substring(3,4)+","+result.substring(4,5);
                result = resultAdd;
            }else if(jml==6){
                String resultAdd = result.substring(0,1) +","+result.substring(1,2)+","+result.substring(2,3)+","+result.substring(3,4)+","+result.substring(4,5)+","+result.substring(5,6);
                result = resultAdd;
            }else if(jml==7){
                String resultAdd = result.substring(0,1) +","+result.substring(1,2)+","+result.substring(2,3)+","+result.substring(3,4)+","+result.substring(4,5)+","+result.substring(5,6)+","+result.substring(6,7);
                result =  resultAdd;
            }else if(jml==8){
                String resultAdd = result.substring(0,1) +","+result.substring(1,2)+","+result.substring(2,3)+","+result.substring(3,4)+","+result.substring(4,5)+","+result.substring(5,6)+","+result.substring(6,7)+","+result.substring(7,8);
                result = resultAdd;
            }else if(jml==9) {
                String resultAdd = result.substring(0,1) +","+result.substring(1,2)+","+result.substring(2,3)+","+result.substring(3,4)+","+result.substring(4,5)+","+result.substring(5,6)+","+result.substring(6,7)+","+result.substring(7,8)+","+result.substring(8, 9);
                result = resultAdd;
            }else if(jml==10) {
                String resultAdd = result.substring(0,1) +","+result.substring(1,2)+","+result.substring(2,3)+","+result.substring(3,4)+","+result.substring(4,5)+","+result.substring(5,6)+","+result.substring(6,7)+","+result.substring(7,8)+","+result.substring(8, 9)+","+result.substring(9, 10);
                result = resultAdd;
            }else if(jml==11) {
                String resultAdd = result.substring(0,1) +","+result.substring(1,2)+","+result.substring(2,3)+","+result.substring(3,4)+","+result.substring(4,5)+","+result.substring(5,6)+","+result.substring(6,7)+","+result.substring(7,8)+","+result.substring(8, 9)+","+result.substring(9, 10)+","+result.substring(10, 11);
                result = resultAdd;
            }
        }

        if(result.equals("")){
            if(str2.equals("Teta")){
                result = str1;
            }else{
                result = "Teta";
            }
        }
        return result;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_riwayat_demps, container, false);
    }
}