package com.example.splashscreen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

import com.airbnb.lottie.LottieAnimationView;

public class SplashScreen extends AppCompatActivity {
    // Variable
    private static int SPLASH_TIME_OUT = 20000;
    private ImageView logo;
    LottieAnimationView lottieAnimationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        logo = findViewById(R.id.logo);
        lottieAnimationView = findViewById(R.id.lottie);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                lottieAnimationView.animate().translationY(-1600).setDuration(500).setStartDelay(2000);
                logo.animate().translationY(1800).setDuration(500).setStartDelay(2000);
                // This method will be executed once the timer is over
                // Start your app main activity
                Intent i = new Intent(SplashScreen.this,
                        MainActivity.class);
                startActivity(i);
                // close this activity
                finish();
            }
        }, SPLASH_TIME_OUT);
    }
}