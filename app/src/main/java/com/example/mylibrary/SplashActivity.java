package com.example.mylibrary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class SplashActivity extends AppCompatActivity {

    Animation animationappIcon,animationappName;
    ImageView appIconTextView;
    TextView appNameTextView;
    Context context;
    Intent intent;
    LinearLayout linearLayout;
    SharedPreferences settings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        context= this;
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getBaseContext());

        appIconTextView=(ImageView) findViewById(R.id.app_icon);
        appNameTextView=(TextView) findViewById(R.id.application_name);
        linearLayout = (LinearLayout)findViewById(R.id.splash);

        boolean previouslyStarted = prefs.getBoolean("firstRun", false);
        if(!previouslyStarted) {


            SharedPreferences.Editor edit = prefs.edit();
            edit.putBoolean("firstRun", Boolean.TRUE);
            edit.commit();

          //  loadAnimations();

            Log.e("Danger","first");

            linearLayout.setBackground(getDrawable(R.drawable.appbackground));
            appIconTextView.setImageResource(R.drawable.ic_launcher_foreground);
            appNameTextView.setText("MY LIBRARY");
            new Timer().schedule(new TimerTask(){
                public void run() {
                    Log.d("Log_test","home screen open");
                    intent = new Intent(context, MainActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                    finish();
                }
            }, 1000);
        }
        else{
            Log.e("Danger","not first");
            intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }

    }
    }





