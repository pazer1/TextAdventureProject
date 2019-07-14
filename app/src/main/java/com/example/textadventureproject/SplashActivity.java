package com.example.textadventureproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

import android.content.Intent;
import android.graphics.PixelFormat;
import android.media.Image;
import android.os.Bundle;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;

public class SplashActivity extends AppCompatActivity {

    Thread splashThread;
    ImageView splashText,mainSplash,backdrop;
    CoordinatorLayout relativeLayout;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_splash);
        backdrop = findViewById(R.id.backdrop);

        Glide.with(this).load(R.drawable.giphy).into(backdrop);
        StartAnimation();

    }


    //나는 너를 바다로 데려갈거야

    private void StartAnimation(){
        Animation anim = AnimationUtils.loadAnimation(this,R.anim.alpha);
        anim.reset();
        ImageView i = findViewById(R.id.backdrop);
        i.clearAnimation();
        i.startAnimation(anim);

        anim = AnimationUtils.loadAnimation(this, R.anim.translate);
        anim.reset();
        ImageView iv = findViewById(R.id.icon_image);
        iv.clearAnimation();
        iv.startAnimation(anim);

        Animation backanim = AnimationUtils.loadAnimation(this,R.anim.background_translate);
        anim.reset();
        backdrop.clearAnimation();
        backdrop.startAnimation(backanim);

        splashThread = new Thread(){
            @Override
            public void run() {
                int waited  = 0;
                //Splash screen pause time;
                while(waited < 6000){
                    try {
                        sleep(100);
                        waited += 100;

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
//                    Intent intent = new Intent(SplashActivity.this,MainActivity.class);
//                    intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
//                    startActivity(intent);
//                    SplashActivity.this.finish();
                }
            }
        };
        splashThread.start();
    }
}
