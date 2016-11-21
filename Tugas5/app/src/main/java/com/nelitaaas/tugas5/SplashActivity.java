package com.nelitaaas.tugas5;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;

/**
 * Created by nelitaaas on 21/11/16.
 */

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);
        int SPLASH_TIME_OUT = 3000;
        new Handler().postDelayed(new Runnable() {
            /*
            * Showing splash screen with a timer. This will be useful when you
            * want to show case your app logo / company
            */
            @Override
            public void run() {
// This method will be executed once the timer is over
// Start your app main activity
                Intent i = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(i);
// close this activity
                finish();
            }
        }, SPLASH_TIME_OUT);
    }
}
