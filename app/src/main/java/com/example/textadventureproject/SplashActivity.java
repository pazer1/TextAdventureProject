package com.example.textadventureproject;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.widget.NestedScrollView;

import android.graphics.Canvas;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.textadventureproject.Listener.AppbarLayoutListener;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;

public class SplashActivity extends AppCompatActivity  {

    Thread splashThread;
    ImageView splashText,mainSplash,backdrop;
    CoordinatorLayout coordinatorLayout;
    Toolbar toolbar;
    NestedScrollView nestedScrollView;
    boolean isUp = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_splash);
        backdrop = findViewById(R.id.backdrop);
        coordinatorLayout = findViewById(R.id.main_content);
        toolbar = findViewById(R.id.splash_toolbar);
        nestedScrollView = findViewById(R.id.splash_nested);
        nestedScrollView.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                if(scrollY >=1000){
                    CollapsingToolbarLayout.LayoutParams layoutParams = (CollapsingToolbarLayout.LayoutParams) toolbar.getLayoutParams();
                    layoutParams.height = 1;
                    toolbar.setLayoutParams(layoutParams);
                    isUp = true;
                }else if(isUp = true && scrollY<1000){
                    CollapsingToolbarLayout.LayoutParams layoutParams = (CollapsingToolbarLayout.LayoutParams) toolbar.getLayoutParams();
                    layoutParams.height = 100;
                    toolbar.setLayoutParams(layoutParams);
                }
            }
        });
        AppBarLayout appBarLayout = findViewById(R.id.splash_appbar);
        AppbarLayoutListener appbarLayoutListener = new AppbarLayoutListener() {
            @Override
            public void onStateChanged(AppBarLayout appBarLayout, State state) {
                if(state.equals(State.EXPANDED)){

                }else if(state.equals(State.COLLAPSED)){
                    Log.d("STATE",state+"");

                }
            }
        };
        appBarLayout.addOnOffsetChangedListener(appbarLayoutListener);
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
