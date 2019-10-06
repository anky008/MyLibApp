package com.example.mylibrary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import java.util.Timer;
import java.util.TimerTask;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);


        if (SharedPrefManager.getInstance(SplashActivity.this).isLoggedIn()) {
            new Timer().schedule(new TimerTask(){
                public void run() {
                    Log.e("Log_test","home screen open");
                    Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                    finish();
                }
            }, 2000 );
        }
        else {
            /*startActivity(new Intent(SplashActivity.this, HomeActivity.class));*/
            new Timer().schedule(new TimerTask(){
                public void run() {
                    Log.e("Log_test","Login screen open");
                    Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                    finish();
                }
            }, 2000 );
        }
    }

    }

