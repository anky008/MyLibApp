package com.example.mylibrary;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

public class book_info_handler extends AppCompatActivity {
    private Toast mToast;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.book_info);

        Intent intent=getIntent();
        if (intent.hasExtra(Intent.EXTRA_TEXT)) {

        }

    }
}
