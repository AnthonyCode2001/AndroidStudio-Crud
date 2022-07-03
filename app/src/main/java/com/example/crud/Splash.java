package com.example.crud;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Pair;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.crud.login.Login;

public class Splash extends AppCompatActivity {

    private static int SPLASH_SCREEN = 5000;

    //Variables
    Animation act1_topAnim, act1_bottomAnim;
    ImageView act1_imgLogo, act1_imgLogo2;
    TextView act1_txtLogo, act1_txtLogo2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        getWindow().setStatusBarColor(ContextCompat.getColor(Splash.this, R.color.act1_background));

        //Animations
        act1_topAnim = AnimationUtils.loadAnimation(this, R.anim.top_animation);
        act1_bottomAnim = AnimationUtils.loadAnimation(this, R.anim.bottom_animation);

        act1_imgLogo = findViewById(R.id.act1_imgLogo);
        act1_imgLogo2 = findViewById(R.id.act1_imgLogo2);
        act1_txtLogo = findViewById(R.id.act1_txtLogo);
        act1_txtLogo2 = findViewById(R.id.act1_txtLogo2);

        act1_imgLogo.setAnimation(act1_topAnim);
        act1_txtLogo.setAnimation(act1_topAnim);
        act1_imgLogo2.setAnimation(act1_bottomAnim);
        act1_txtLogo2.setAnimation(act1_bottomAnim);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(Splash.this, Login.class);

                Pair[] pairs = new Pair[2];
                pairs[0] = new Pair<View, String>(act1_imgLogo, "logo_image");
                pairs[1] = new Pair<View, String>(act1_txtLogo, "logo_text");

                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(Splash.this, pairs);
                startActivity(intent, options.toBundle());
                finish();
            }
        }, SPLASH_SCREEN);
    }
}