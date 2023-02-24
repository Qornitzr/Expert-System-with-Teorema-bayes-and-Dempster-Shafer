package com.example.sistempakarteodemp.SplashScreen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.sistempakarteodemp.Activity.DasboardUserActivitiy;
import com.example.sistempakarteodemp.R;
import com.example.sistempakarteodemp.admin.DasboardAdminActivity;
import com.example.sistempakarteodemp.entities.Account;

public class SplashScreenAdmin extends AppCompatActivity {

    ImageView imgLogo;
    ProgressBar progressBar;
    TextView txtPercent,txtNamaAdmin;
    int status = 0;
    Animation animationImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen_admin);

        imgLogo = findViewById(R.id.imgViewLogo);
        progressBar = findViewById(R.id.progresbar);
        txtPercent = findViewById(R.id.txt_percent);
        txtNamaAdmin = findViewById(R.id.txt_namaAdmin);

        Intent intent = getIntent();
        Account account = (Account) intent.getSerializableExtra("account");

        Handler handler = new Handler();
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (status < 100) {

                    status += 1;

                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            txtPercent.setText(String.valueOf(status)+"%");
                            progressBar.setProgress(status);
                            if (status == 100) {

                                animationImg = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.zoom_in);
                                imgLogo.startAnimation(animationImg);
                                animationImg.setAnimationListener(new Animation.AnimationListener() {
                                    @Override
                                    public void onAnimationStart(Animation animation) {
                                        if (animation == animationImg) {
                                            progressBar.setVisibility(View.GONE);
                                            txtPercent.setVisibility(View.GONE);
                                        }
                                    }

                                    @Override
                                    public void onAnimationEnd(Animation animation) {
                                        Intent intent = new Intent(SplashScreenAdmin.this, DasboardAdminActivity.class);
                                        intent.putExtra("account", account);
                                        startActivity(intent);
                                        finish();
                                        SplashScreenAdmin.this.finish();
                                    }

                                    @Override
                                    public void onAnimationRepeat(Animation animation) {

                                    }
                                });
                            }
                        }
                    });
                }
            }
        }).start();
    }
}