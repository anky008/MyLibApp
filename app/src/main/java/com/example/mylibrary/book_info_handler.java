package com.example.mylibrary;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;

import static com.example.mylibrary.R.menu.book_info_menu;
import static com.example.mylibrary.R.menu.menu_main;

public class book_info_handler extends AppCompatActivity {
    TextView bookTitleTextView;
    TextView docTypeSizeTextView;

    String bookTitle;
    String docTypeSize;
    private int ClickedItemIndex;

    final String  SEPERATOR=".";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.book_info);
        bookTitleTextView=(TextView) findViewById(R.id.book_title);
        docTypeSizeTextView=(TextView) findViewById(R.id.doc_type_size);

        Intent intent=getIntent();
        if (intent.hasExtra("book_title"))
        {
         bookTitle=intent.getStringExtra("book_title");
         ClickedItemIndex=intent.getIntExtra("clickedItemIndex",-1);
        }

        bookTitleTextView.setText(bookTitle);
        docTypeSizeTextView.setText("pdf");
        setActionBar("About Document");

    }

    public void setActionBar(String heading) {

        Toolbar myToolbar = (Toolbar) findViewById(R.id.book_info_toolbar);
        setSupportActionBar(myToolbar);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle(heading);
        actionBar.show();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(book_info_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int itemThatWasClickedId = item.getItemId();
        if (itemThatWasClickedId == R.id.read_now) {
            return true;
        }
        else if(itemThatWasClickedId == android.R.id.home){
            onBackPressed();
        }

        return true;
    }


    public void bookImageClicked(View view) {
        Intent intent = new Intent(getApplicationContext(), PDFActivity.class);
        intent.putExtra("position", ClickedItemIndex);
        startActivity(intent);

        Log.e("Position", ClickedItemIndex + "");
    }
}
