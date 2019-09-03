package com.example.mylibrary;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ActionBar;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    private static final int NUM_LIST_ITEMS=100;
    private myAdapter mAdapter;
    private RecyclerView RecyclerView_books;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.book_layout);

        RecyclerView_books=(RecyclerView) findViewById(R.id.rv_books);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        RecyclerView_books.setLayoutManager(linearLayoutManager);
        RecyclerView_books.setHasFixedSize(true);

        mAdapter=new myAdapter(NUM_LIST_ITEMS);
        RecyclerView_books.setAdapter(mAdapter);



    }
}
