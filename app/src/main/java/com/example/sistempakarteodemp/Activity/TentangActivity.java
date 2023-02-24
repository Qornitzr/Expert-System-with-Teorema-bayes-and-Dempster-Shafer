package com.example.sistempakarteodemp.Activity;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.sistempakarteodemp.R;

import static android.graphics.text.LineBreaker.JUSTIFICATION_MODE_INTER_WORD;

public class TentangActivity extends AppCompatActivity {

    TextView isiTeo, isiDemp;

    @RequiresApi(api = Build.VERSION_CODES.Q)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tentang);
        isiTeo = findViewById(R.id.tv_IsiTeo);
        isiDemp = findViewById(R.id.tv_IsiDemp);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Tentang");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            isiTeo.setJustificationMode(JUSTIFICATION_MODE_INTER_WORD);
            isiDemp.setJustificationMode(JUSTIFICATION_MODE_INTER_WORD);
        }

        String teo= "\t\t\t\tTeori probabilitas Bayesian merupakan satu dari cabang teori  iagnose  matematik yang memungkinkan kita untuk membuat satu model ketidakpastian dari suatu kejadian yang terjadi dengan menggabungkan pengetahuan umum dengan fakta dari hasil pengamatan (Purba, 2020).\n\n" +
                "\t\t\t\tTeorema Bayes menerangkan hubungan antara probabilitas terjadinya peristiwa A dengan syarat peristiwa B telah terjadi dan probabilitas terjadinya peristiwa B dengan syarat peristiwa A telah terjadi. Teorema Bayes ini bermanfaat untuk mengubah atau memutakhirkan (meng-update) probabilitas yang dihitung dengan tersedianya data dan informasi tambahan (Purba, 2020).\n\n" +
                "Secara umum, Teorema Bayes dinyatakan sebagai: \n\n" +
                "P(A│B)=(P (B│A)P(A))/P(B) \t(1)\n\n" +
                "Dalam notasi ini P(A|B) berarti peluang kejadian A bila B terjadi dan P(B|A) peluang kejadian B bila A terjadi.";
        String demp = "\t\t\t\tMetode Dempster-Shafer pertama kali diperkenalkan oleh Dempster, yang melakukan percobaan model ketidakpastian dengan range probabilities dari pada sebagai probabilitas tunggal. Kemudian pada tahun 1976 Shafer mempublikasikan teori Dempster itu pada sebuah buku yang berjudul Mathematical Theory of Evident (Kirman et al., 2019).\n\n" +
                "\t\t\t\tSecara umum teori Dempster-Shafer ditulis dalam suatu interval: [Belief,Plausibility]. Belief (Bel) adalah ukuran kekuatan evidence dalam mendukung suatu himpunan proposisi. Jika bernilai 0 maka mengindikasikan bahwa tidak ada evidence, dan jika bernilai 1 menunjukkan adanya kepastian. Plausibility (Pls) akan mengurangi tingkat kepastian dari evidence. Plausibility bernilai 0 sampai 1. Jika yakin akan X’, maka dapat dikatakan bahwa Bel(X’) = 1, sehingga rumus di atas nilai dari Pls(X) = 0 (Kirman et al., 2019).\n\n" +
                "\t\t\t\tApabila diketahui X adalah subset dari θ, dengan m1 sebagai fungsi densitasnya, dan Y juga merupakan subset dari θ dengan m2 sebagai fungsi densitasnya, maka dapat dibentuk fungsi kombinasi m1 dan m2 sebagai m3, yaitu ditunjukkan pada persamaan (1) (Kirman et al., 2019)\n";

        isiTeo.setText(teo);
        isiDemp.setText(demp);
    }
}