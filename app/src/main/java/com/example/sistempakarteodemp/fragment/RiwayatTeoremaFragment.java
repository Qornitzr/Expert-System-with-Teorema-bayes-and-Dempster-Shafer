package com.example.sistempakarteodemp.fragment;

import android.annotation.SuppressLint;
import android.content.ContentValues;
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
import android.widget.RelativeLayout;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.example.sistempakarteodemp.DBHelper;
import com.example.sistempakarteodemp.ExpandableHeightListView;
import com.example.sistempakarteodemp.R;
import com.example.sistempakarteodemp.entities.Riwayat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class RiwayatTeoremaFragment extends Fragment {

    protected ListAdapter adapter;
    protected Cursor cursor1; // get nama penyakit
    protected Cursor cursor2; // get sum mb
    protected Cursor cursor3; // get nilai mb
    protected Cursor cursor4; // insert nilai phi dan bayes
    protected Cursor cursor5; // get sum phi
    protected Cursor cursor6; //  get sum bayes
    protected Cursor cursorC; //  get sum bayes
    RelativeLayout relativeLayout;
    DBHelper dbHelper;
    protected ExpandableHeightListView list_view;
    String namapenyakit_terbesar;
    String namaSaran_terbesar;
    String namaDesk_terbesar;
    String[] penyakit_terpilih;
    String[] deskripsi_terpilih;
    String[] saran_terpilih;
    double[] prob_pHiHasil;
    double[] prob_bayes;
    double[] probabilitas_terpilih;
    int gejala_terpilih;
    double prob_aturan;
    double prob_baru;
    double prob_dariPembagi;
    double prob_pHi;
    double prob_bayesHasil;
    double total_pHi = 0;
    double total_bayes = 0;
    double prob_pembagi1;
    double prob_pembagi2;
    double prob_terbesar;
    TextView text1, text2, textSaran, textDesk;
    int i;
    Intent intent;
    String intentGejala;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @SuppressLint("WrongViewCast")
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        intent = getActivity().getIntent();
        Riwayat riwayat = (Riwayat) intent.getSerializableExtra("idRiwayat");

        intentGejala = riwayat.getRiwayat_nama_gejala();

        dbHelper = new DBHelper(getContext());
        SQLiteDatabase db = this.dbHelper.getReadableDatabase();

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
            cursorC = db.rawQuery("SELECT p.nama_penyakit, p.saran, p.deskripsi from rule r inner join penyakit p on r.id_pen = p.id_penyakit where p.id_penyakit ='"+ hasilRule1 +"' group by p.id_penyakit order by count(p.nama_penyakit) ASC", null);
            for (int u = 0; u < cursorC.getCount(); u++){
                cursorC.moveToPosition(u);
                namapenyakit_terbesar = cursorC.getString(0);
                namaSaran_terbesar = cursorC.getString(1);
                namaDesk_terbesar = cursorC.getString(2);
            }
            text1 = (TextView) getView().findViewById(R.id.textView2);
            text2 = (TextView) getView().findViewById(R.id.textView3);
            textSaran = (TextView) getView().findViewById(R.id.tv_SaranPenyakit);
            textDesk = (TextView) getView().findViewById(R.id.tv_DeskripsiPenyakit);
            list_view =  getView().findViewById(R.id.lv_viewpenyakit);
            relativeLayout =  getView().findViewById(R.id.relative4);
            list_view.setVisibility(View.GONE);
            relativeLayout.setVisibility(View.GONE);
            prob_terbesar = 100.0;
            text1.setText(String.format("%.0f%%", prob_terbesar));
            text2.setText(namapenyakit_terbesar);
            textSaran.setText(namaSaran_terbesar);
            textDesk.setText(namaDesk_terbesar);
        }else if (intentGejala.equals(rule2)){
            cursorC = db.rawQuery("SELECT p.nama_penyakit, p.saran, p.deskripsi from rule r inner join penyakit p on r.id_pen = p.id_penyakit where p.id_penyakit ='"+ hasilRule2 +"' group by p.id_penyakit order by count(p.nama_penyakit) ASC", null);
            for (int u = 0; u < cursorC.getCount(); u++){
                cursorC.moveToPosition(u);
                namapenyakit_terbesar = cursorC.getString(0);
                namaSaran_terbesar = cursorC.getString(1);
                namaDesk_terbesar = cursorC.getString(2);
            }
            text1 = (TextView) getView().findViewById(R.id.textView2);
            text2 = (TextView) getView().findViewById(R.id.textView3);
            textSaran = (TextView) getView().findViewById(R.id.tv_SaranPenyakit);
            textDesk = (TextView) getView().findViewById(R.id.tv_DeskripsiPenyakit);
            list_view =  getView().findViewById(R.id.lv_viewpenyakit);
            relativeLayout =  getView().findViewById(R.id.relative4);
            list_view.setVisibility(View.GONE);
            relativeLayout.setVisibility(View.GONE);
            prob_terbesar = 100.0;
            text1.setText(String.format("%.0f%%", prob_terbesar));
            text2.setText(namapenyakit_terbesar);
            textSaran.setText(namaSaran_terbesar);
            textDesk.setText(namaDesk_terbesar);
        }else if (intentGejala.equals(rule3)){
            cursorC = db.rawQuery("SELECT p.nama_penyakit, p.saran, p.deskripsi from rule r inner join penyakit p on r.id_pen = p.id_penyakit where p.id_penyakit ='"+ hasilRule3 +"' group by p.id_penyakit order by count(p.nama_penyakit) ASC", null);
            for (int u = 0; u < cursorC.getCount(); u++){
                cursorC.moveToPosition(u);
                namapenyakit_terbesar = cursorC.getString(0);
                namaSaran_terbesar = cursorC.getString(1);
                namaDesk_terbesar = cursorC.getString(2);
            }
            text1 = (TextView) getView().findViewById(R.id.textView2);
            text2 = (TextView) getView().findViewById(R.id.textView3);
            textSaran = (TextView) getView().findViewById(R.id.tv_SaranPenyakit);
            textDesk = (TextView) getView().findViewById(R.id.tv_DeskripsiPenyakit);
            list_view =  getView().findViewById(R.id.lv_viewpenyakit);
            relativeLayout =  getView().findViewById(R.id.relative4);
            list_view.setVisibility(View.GONE);
            relativeLayout.setVisibility(View.GONE);
            prob_terbesar = 100.0;
            text1.setText(String.format("%.0f%%", prob_terbesar));
            text2.setText(namapenyakit_terbesar);
            textSaran.setText(namaSaran_terbesar);
            textDesk.setText(namaDesk_terbesar);
        }else if (intentGejala.equals(rule4)){
            cursorC = db.rawQuery("SELECT p.nama_penyakit, p.saran, p.deskripsi from rule r inner join penyakit p on r.id_pen = p.id_penyakit where p.id_penyakit ='"+ hasilRule4 +"' group by p.id_penyakit order by count(p.nama_penyakit) ASC", null);
            for (int u = 0; u < cursorC.getCount(); u++){
                cursorC.moveToPosition(u);
                namapenyakit_terbesar = cursorC.getString(0);
                namaSaran_terbesar = cursorC.getString(1);
                namaDesk_terbesar = cursorC.getString(2);
            }
            text1 = (TextView) getView().findViewById(R.id.textView2);
            text2 = (TextView) getView().findViewById(R.id.textView3);
            textSaran = (TextView) getView().findViewById(R.id.tv_SaranPenyakit);
            textDesk = (TextView) getView().findViewById(R.id.tv_DeskripsiPenyakit);
            list_view =  getView().findViewById(R.id.lv_viewpenyakit);
            relativeLayout =  getView().findViewById(R.id.relative4);
            list_view.setVisibility(View.GONE);
            relativeLayout.setVisibility(View.GONE);
            prob_terbesar = 100.0;
            text1.setText(String.format("%.0f%%", prob_terbesar));
            text2.setText(namapenyakit_terbesar);
            textSaran.setText(namaSaran_terbesar);
            textDesk.setText(namaDesk_terbesar);
        }else if (intentGejala.equals(rule5)){
            cursorC = db.rawQuery("SELECT p.nama_penyakit, p.saran, p.deskripsi from rule r inner join penyakit p on r.id_pen = p.id_penyakit where p.id_penyakit ='"+ hasilRule5 +"' group by p.id_penyakit order by count(p.nama_penyakit) ASC", null);
            for (int u = 0; u < cursorC.getCount(); u++){
                cursorC.moveToPosition(u);
                namapenyakit_terbesar = cursorC.getString(0);
                namaSaran_terbesar = cursorC.getString(1);
                namaDesk_terbesar = cursorC.getString(2);
            }
            text1 = (TextView) getView().findViewById(R.id.textView2);
            text2 = (TextView) getView().findViewById(R.id.textView3);
            textSaran = (TextView) getView().findViewById(R.id.tv_SaranPenyakit);
            textDesk = (TextView) getView().findViewById(R.id.tv_DeskripsiPenyakit);
            list_view =  getView().findViewById(R.id.lv_viewpenyakit);
            relativeLayout =  getView().findViewById(R.id.relative4);
            list_view.setVisibility(View.GONE);
            relativeLayout.setVisibility(View.GONE);
            prob_terbesar = 100.0;
            text1.setText(String.format("%.0f%%", prob_terbesar));
            text2.setText(namapenyakit_terbesar);
            textSaran.setText(namaSaran_terbesar);
            textDesk.setText(namaDesk_terbesar);
        }else if (intentGejala.equals(rule6)){
            cursorC = db.rawQuery("SELECT p.nama_penyakit, p.saran, p.deskripsi from rule r inner join penyakit p on r.id_pen = p.id_penyakit where p.id_penyakit ='"+ hasilRule6 +"' group by p.id_penyakit order by count(p.nama_penyakit) ASC", null);
            for (int u = 0; u < cursorC.getCount(); u++){
                cursorC.moveToPosition(u);
                namapenyakit_terbesar = cursorC.getString(0);
                namaSaran_terbesar = cursorC.getString(1);
                namaDesk_terbesar = cursorC.getString(2);
            }
            text1 = (TextView) getView().findViewById(R.id.textView2);
            text2 = (TextView) getView().findViewById(R.id.textView3);
            textSaran = (TextView) getView().findViewById(R.id.tv_SaranPenyakit);
            textDesk = (TextView) getView().findViewById(R.id.tv_DeskripsiPenyakit);
            list_view =  getView().findViewById(R.id.lv_viewpenyakit);
            relativeLayout =  getView().findViewById(R.id.relative4);
            list_view.setVisibility(View.GONE);
            relativeLayout.setVisibility(View.GONE);
            prob_terbesar = 100.0;
            text1.setText(String.format("%.0f%%", prob_terbesar));
            text2.setText(namapenyakit_terbesar);
            textSaran.setText(namaSaran_terbesar);
            textDesk.setText(namaDesk_terbesar);
        }else if (intentGejala.equals(rule7)){
            cursorC = db.rawQuery("SELECT p.nama_penyakit, p.saran, p.deskripsi from rule r inner join penyakit p on r.id_pen = p.id_penyakit where p.id_penyakit ='"+ hasilRule7 +"' group by p.id_penyakit order by count(p.nama_penyakit) ASC", null);
            for (int u = 0; u < cursorC.getCount(); u++){
                cursorC.moveToPosition(u);
                namapenyakit_terbesar = cursorC.getString(0);
                namaSaran_terbesar = cursorC.getString(1);
                namaDesk_terbesar = cursorC.getString(2);
            }
            text1 = (TextView) getView().findViewById(R.id.textView2);
            text2 = (TextView) getView().findViewById(R.id.textView3);
            textSaran = (TextView) getView().findViewById(R.id.tv_SaranPenyakit);
            textDesk = (TextView) getView().findViewById(R.id.tv_DeskripsiPenyakit);
            list_view =  getView().findViewById(R.id.lv_viewpenyakit);
            relativeLayout =  getView().findViewById(R.id.relative4);
            list_view.setVisibility(View.GONE);
            relativeLayout.setVisibility(View.GONE);
            prob_terbesar = 100.0;
            text1.setText(String.format("%.0f%%", prob_terbesar));
            text2.setText(namapenyakit_terbesar);
            textSaran.setText(namaSaran_terbesar);
            textDesk.setText(namaDesk_terbesar);
        }else if (intentGejala.equals(rule8)){
            cursorC = db.rawQuery("SELECT p.nama_penyakit, p.saran, p.deskripsi from rule r inner join penyakit p on r.id_pen = p.id_penyakit where p.id_penyakit ='"+ hasilRule8 +"' group by p.id_penyakit order by count(p.nama_penyakit) ASC", null);
            for (int u = 0; u < cursorC.getCount(); u++){
                cursorC.moveToPosition(u);
                namapenyakit_terbesar = cursorC.getString(0);
                namaSaran_terbesar = cursorC.getString(1);
                namaDesk_terbesar = cursorC.getString(2);
            }
            text1 = (TextView) getView().findViewById(R.id.textView2);
            text2 = (TextView) getView().findViewById(R.id.textView3);
            textSaran = (TextView) getView().findViewById(R.id.tv_SaranPenyakit);
            textDesk = (TextView) getView().findViewById(R.id.tv_DeskripsiPenyakit);
            list_view =  getView().findViewById(R.id.lv_viewpenyakit);
            relativeLayout =  getView().findViewById(R.id.relative4);
            list_view.setVisibility(View.GONE);
            relativeLayout.setVisibility(View.GONE);
            prob_terbesar = 100.0;
            text1.setText(String.format("%.0f%%", prob_terbesar));
            text2.setText(namapenyakit_terbesar);
            textSaran.setText(namaSaran_terbesar);
            textDesk.setText(namaDesk_terbesar);
        }else if (intentGejala.equals(rule9)){
            cursorC = db.rawQuery("SELECT p.nama_penyakit, p.saran, p.deskripsi from rule r inner join penyakit p on r.id_pen = p.id_penyakit where p.id_penyakit ='"+ hasilRule9 +"' group by p.id_penyakit order by count(p.nama_penyakit) ASC", null);
            for (int u = 0; u < cursorC.getCount(); u++){
                cursorC.moveToPosition(u);
                namapenyakit_terbesar = cursorC.getString(0);
                namaSaran_terbesar = cursorC.getString(1);
                namaDesk_terbesar = cursorC.getString(2);
            }
            text1 = (TextView) getView().findViewById(R.id.textView2);
            text2 = (TextView) getView().findViewById(R.id.textView3);
            textSaran = (TextView) getView().findViewById(R.id.tv_SaranPenyakit);
            textDesk = (TextView) getView().findViewById(R.id.tv_DeskripsiPenyakit);
            list_view =  getView().findViewById(R.id.lv_viewpenyakit);
            relativeLayout =  getView().findViewById(R.id.relative4);
            list_view.setVisibility(View.GONE);
            relativeLayout.setVisibility(View.GONE);
            prob_terbesar = 100.0;
            text1.setText(String.format("%.0f%%", prob_terbesar));
            text2.setText(namapenyakit_terbesar);
            textSaran.setText(namaSaran_terbesar);
            textDesk.setText(namaDesk_terbesar);
        }else{
            cursor1 = db.rawQuery("SELECT p.nama_penyakit, count(g.nama_gejala), p.deskripsi, p.saran from rule r inner join penyakit p on r.id_pen = p.id_penyakit inner join gejala g on r.id_gej = g.id_gejala where g.nama_gejala in (" + intentGejala + ") group by p.nama_penyakit", null);
            cursor1.moveToFirst();

            penyakit_terpilih = new String[cursor1.getCount()];
            saran_terpilih = new String[cursor1.getCount()];
            deskripsi_terpilih = new String[cursor1.getCount()];
            probabilitas_terpilih = new double[cursor1.getCount()];

            for (i = 0; i < this.cursor1.getCount(); i++) {
                int j;
                int jj;
                int jjj;
                cursor1.moveToPosition(i);
                penyakit_terpilih[i] = cursor1.getString(0);
                deskripsi_terpilih[i] = cursor1.getString(2);
                saran_terpilih[i] = cursor1.getString(3);
                cursor2 = db.rawQuery("SELECT rule.id_pen As id_penyakit, " +
                        "penyakit.nama_penyakit AS penyakit_nama_penyakit, " +
                        "rule.id_gej As id_gejala, " +
                        "gejala.nama_gejala AS gejala_nama_gejala, " +
                        "SUM(rule.probabilitas_mb) As rule_probabilitas_mb " +
                        "FROM rule " +
                        "LEFT JOIN penyakit ON rule.id_pen = penyakit.id_penyakit " +
                        "LEFT JOIN gejala ON rule.id_gej = gejala.id_gejala " +
                        "WHERE gejala_nama_gejala in (" + intentGejala + ") " +
                        "AND penyakit_nama_penyakit = '" + penyakit_terpilih[i] + "' " +
                        "order by nama_penyakit ASC", null);
                cursor3 = db.rawQuery("SELECT r.probabilitas_mb from rule r inner join penyakit p on r.id_pen  = p.id_penyakit inner join gejala g on r.id_gej = g.id_gejala where g.nama_gejala  in(" + intentGejala + ") AND p.nama_penyakit = '" + this.penyakit_terpilih[i] + "' order by nama_penyakit", null);




                //GET SUM GEJALA
                for(j = 0; j < cursor2.getCount(); j++){
                    cursor2.moveToPosition(j);
                    prob_baru = cursor2.getDouble(4);
                }

                prob_pHiHasil = new double[cursor3.getCount()];
                prob_bayes = new double[cursor3.getCount()];
                //CARI P(Hi)
                for (jj = 0; jj < cursor3.getCount(); jj++){

                    cursor3.moveToPosition(jj);
                    prob_aturan = cursor3.getDouble(0);

                    prob_pembagi1 =  prob_aturan / prob_baru;
                    prob_dariPembagi = prob_aturan * prob_pembagi1;
                    prob_pHiHasil[jj] =  prob_dariPembagi;
                }


                this.cursor4 = db.rawQuery("SELECT r.id_rule from rule r inner join penyakit p on r.id_pen  = p.id_penyakit inner join gejala g on r.id_gej = g.id_gejala where g.nama_gejala in (" + intentGejala + ") AND p.nama_penyakit = '" + this.penyakit_terpilih[i] + "' group by g.nama_gejala order by nama_gejala ASC", null);

                for (int g = 0; g < cursor4.getCount(); g++){
                    cursor4.moveToPosition(g);
                    gejala_terpilih = cursor4.getInt(0);
                    ContentValues v = new ContentValues();
                    v.put("probabilitas_total", prob_pHiHasil[g]);
                    db.update("rule", v, "id_rule=?" , new String[]{String.valueOf(gejala_terpilih)});

                }


                cursor5 = db.rawQuery("SELECT rule.id_pen As id_penyakit, " +
                        "penyakit.nama_penyakit AS penyakit_nama_penyakit, " +
                        "rule.id_gej As id_gejala, " +
                        "gejala.nama_gejala AS gejala_nama_gejala, " +
                        "SUM(rule.probabilitas_total) As total " +
                        "FROM rule " +
                        "LEFT JOIN penyakit ON rule.id_pen = penyakit.id_penyakit " +
                        "LEFT JOIN gejala ON rule.id_gej = gejala.id_gejala " +
                        "WHERE gejala_nama_gejala in (" + intentGejala + ") " +
                        "AND penyakit_nama_penyakit = '" + penyakit_terpilih[i] + "' " +
                        "group by penyakit_nama_penyakit order by nama_penyakit ASC", null);

                for (int jjjj = 0; jjjj < cursor5.getCount(); jjjj++){
                    cursor5.moveToFirst();
                    total_pHi = cursor5.getDouble(4);

                }



                for (jjj = 0; jjj < cursor3.getCount(); jjj++){
                    cursor3.moveToPosition(jjj);
                    prob_aturan = cursor3.getDouble(0);
                    prob_pembagi2 =  prob_aturan / prob_baru;
                    prob_pHi =  (prob_aturan * prob_pembagi2) / total_pHi;

                    prob_bayes[jjj] = prob_aturan * prob_pHi;

                }


                for (int g = 0; g < cursor4.getCount(); g++){
                    cursor4.moveToPosition(g);
                    gejala_terpilih = cursor4.getInt(0);
                    ContentValues v = new ContentValues();
                    v.put("probabilitas_bayes", prob_bayes[g]);
                    db.update("rule", v, "id_rule=?" , new String[]{String.valueOf(gejala_terpilih)});
                }

                cursor6 = db.rawQuery("SELECT rule.id_pen As id_penyakit, " +
                        "penyakit.nama_penyakit AS penyakit_nama_penyakit, " +
                        "rule.id_gej As id_gejala, " +
                        "gejala.nama_gejala AS gejala_nama_gejala, " +
                        "SUM(rule.probabilitas_bayes) As total " +
                        "FROM rule " +
                        "LEFT JOIN penyakit ON rule.id_pen = penyakit.id_penyakit " +
                        "LEFT JOIN gejala ON rule.id_gej = gejala.id_gejala " +
                        "WHERE gejala_nama_gejala in (" + intentGejala + ") " +
                        "AND penyakit_nama_penyakit = '" + penyakit_terpilih[i] + "' " +
                        "group by penyakit_nama_penyakit order by nama_penyakit ASC", null);

                for (int jjjj = 0; jjjj < cursor6.getCount(); jjjj++){
                    cursor6.moveToFirst();
                    total_bayes = cursor6.getDouble(4);

                }
                prob_bayesHasil = total_bayes;
                probabilitas_terpilih[i] = prob_bayesHasil;

                double max = probabilitas_terpilih[0];
                String namMax = penyakit_terpilih[0];
                String saranMax = saran_terpilih[0];
                String DeskMax = deskripsi_terpilih[0];

                for (int maxPen = 1; maxPen < penyakit_terpilih.length; maxPen ++){
                    for (int maxSar = 1; maxSar < penyakit_terpilih.length; maxSar ++){
                        for (int maxDes = 1; maxDes < penyakit_terpilih.length; maxDes ++){
                            for (int mx = 1; mx < probabilitas_terpilih.length; mx ++){
                                if (probabilitas_terpilih[mx] > max) {
                                    max = probabilitas_terpilih[mx];
                                    namMax = penyakit_terpilih[mx];
                                    saranMax = saran_terpilih[mx];
                                    DeskMax = deskripsi_terpilih[mx];
                                }
                            }
                        }
                    }
                }



                prob_terbesar = max;
                namapenyakit_terbesar = namMax;
                namaSaran_terbesar = saranMax;
                namaDesk_terbesar = DeskMax;


//                int jmlh = this.cursor3.getCount();
//                Log.i(penyakit_terpilih[i], String.valueOf(jmlh));
//
//                if (jmlh==8){
//                    this.prob_terbesar = this.prob_bayesHasil;
//                    this.namapenyakit_terbesar = this.penyakit_terpilih[i];
//                    this.namaSaran_terbesar = this.saran_terpilih[i];
//                    this.namaDesk_terbesar = this.deskripsi_terpilih[i];
//                } else if (jmlh==7){
//                    this.prob_terbesar = this.prob_bayesHasil;
//                    this.namapenyakit_terbesar = this.penyakit_terpilih[i];
//                    this.namaSaran_terbesar = this.saran_terpilih[i];
//                    this.namaDesk_terbesar = this.deskripsi_terpilih[i];
//                } else if (jmlh==6){
//                    this.prob_terbesar = this.prob_bayesHasil;
//                    this.namapenyakit_terbesar = this.penyakit_terpilih[i];
//                    this.namaSaran_terbesar = this.saran_terpilih[i];
//                    this.namaDesk_terbesar = this.deskripsi_terpilih[i];
//                } else if (jmlh==5){
//                    this.prob_terbesar = this.prob_bayesHasil;
//                    this.namapenyakit_terbesar = this.penyakit_terpilih[i];
//                    this.namaSaran_terbesar = this.saran_terpilih[i];
//                    this.namaDesk_terbesar = this.deskripsi_terpilih[i];
//                } else if (jmlh==4){
//                    this.prob_terbesar = this.prob_bayesHasil;
//                    this.namapenyakit_terbesar = this.penyakit_terpilih[i];
//                    this.namaSaran_terbesar = this.saran_terpilih[i];
//                    this.namaDesk_terbesar = this.deskripsi_terpilih[i];
//                } else if (jmlh==3){
//                    this.prob_terbesar = this.prob_bayesHasil;
//                    this.namapenyakit_terbesar = this.penyakit_terpilih[i];
//                    this.namaSaran_terbesar = this.saran_terpilih[i];
//                    this.namaDesk_terbesar = this.deskripsi_terpilih[i];
//                } else if (jmlh==2){
//                    this.prob_terbesar = this.prob_bayesHasil;
//                    this.namapenyakit_terbesar = this.penyakit_terpilih[i];
//                    this.namaSaran_terbesar = this.saran_terpilih[i];
//                    this.namaDesk_terbesar = this.deskripsi_terpilih[i];
//                } else if (jmlh==1){
//                    this.prob_terbesar = this.prob_bayesHasil;
//                    this.namapenyakit_terbesar = this.penyakit_terpilih[i];
//                    this.namaSaran_terbesar = this.saran_terpilih[i];
//                    this.namaDesk_terbesar = this.deskripsi_terpilih[i];
//                }


            }



            List<HashMap<String, String>> ListHsl = new ArrayList<>();
            for (i = 0; i < this.penyakit_terpilih.length; i++) {
                int j;
                for (j = i + 1; j < this.penyakit_terpilih.length; j++) {
                    if (this.probabilitas_terpilih[j] > this.probabilitas_terpilih[i]) {
                        double t = this.probabilitas_terpilih[i];
                        this.probabilitas_terpilih[i] = this.probabilitas_terpilih[j];
                        this.probabilitas_terpilih[j] = t;
                        String tmp = this.penyakit_terpilih[i];
                        this.penyakit_terpilih[i] = this.penyakit_terpilih[j];
                        this.penyakit_terpilih[j] = tmp;
                    }
                }
                HashMap<String, String> hm = new HashMap();
                hm.put("lv_namapnyakit", this.penyakit_terpilih[i]);
                hm.put("listview_jumlah", String.format("%.0f%%", new Object[]{Double.valueOf((this.probabilitas_terpilih[i]) * 100.0d)}));
                ListHsl.add(hm);
            }
            String[] from = new String[]{"lv_namapnyakit", "listview_jumlah"};
            int[] to = new int[]{R.id.lv_namapenyakit, R.id.listview_jumlah};
            this.list_view =  getView().findViewById(R.id.lv_viewpenyakit);
            this.adapter = new SimpleAdapter(getContext(), ListHsl, R.layout.listview_hasil, from, to);
            this.list_view.setAdapter(this.adapter);
            this.list_view.setTextFilterEnabled(true);
            list_view.setExpanded(true);
            // this.list_view.setOnItemClickListener(new C02211());
            this.text1 = (TextView) getView().findViewById(R.id.textView2);
            this.text2 = (TextView) getView().findViewById(R.id.textView3);
            this.textSaran = (TextView) getView().findViewById(R.id.tv_SaranPenyakit);
            this.textDesk = (TextView) getView().findViewById(R.id.tv_DeskripsiPenyakit);
            this.text1.setText(String.format("%.0f%%", new Object[]{Double.valueOf((this.prob_terbesar) * 100.0d)}));
            this.text2.setText(this.namapenyakit_terbesar);
            textSaran.setText(namaSaran_terbesar);
            textDesk.setText(namaDesk_terbesar);
        }


    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_riwayat_teorema, container, false);
    }
}