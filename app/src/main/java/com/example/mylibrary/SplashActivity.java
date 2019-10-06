package com.example.mylibrary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class SplashActivity extends AppCompatActivity {

    Animation animationappName;
    Animation animationappIcon;
    ImageView appIconTextView;
    TextView appNameTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        findViews();
        loadAnimations();
    }


    private void loadAnimations() {
        animationappName = AnimationUtils.loadAnimation(this, R.anim.fade_in);
        animationappIcon=AnimationUtils.loadAnimation(this,R.anim.blink);

        appIconTextView.setAnimation(animationappIcon);
        appIconTextView.startAnimation(animationappIcon);
        animationappName.setDuration(5000);

        appNameTextView.setAnimation(animationappName);
        appNameTextView.startAnimation(animationappName);
        animationappName.setDuration(5000);

        animationappName.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
             sharedPrefs();

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

    private void sharedPrefs() {
        SharedPreferences settings = getSharedPreferences("prefs", 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putBoolean("firstRun", true);
        editor.commit();

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    private void findViews() {
        appIconTextView=(ImageView) findViewById(R.id.app_icon);
        appNameTextView=(TextView) findViewById(R.id.application_name);
    }


    @Override
    public void onResume() {
        super.onResume();
        SharedPreferences settings = getSharedPreferences("prefs", 0);
        boolean firstRun = settings.getBoolean("firstRun", true);
        if (!firstRun) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            Log.d("TAG1", "firstRun(false): " + Boolean.valueOf(firstRun).toString());
        } else {
            Log.d("TAG1", "firstRun(true): " + Boolean.valueOf(firstRun).toString());
        }
    }
}


