package com.example.mylibrary;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.PopupMenu;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.github.barteksc.pdfviewer.PDFView;
import com.google.android.material.navigation.NavigationView;

import java.io.File;
import java.util.ArrayList;

import static com.example.mylibrary.R.menu.menu_main;

public class MainActivity extends AppCompatActivity implements myAdapter.ListItemClickListener,NavigationView.OnNavigationItemSelectedListener {

    private boolean HaveReadResult = true;
    private boolean StarIconResult = true;
    private boolean ClockIconResult = true;
    private boolean NewCollectionResult = true;
    private boolean isBackPressed=true;

    private myAdapter mAdapter;
    private RecyclerView RecyclerView_books;
    private Toolbar mainToolbar;
    private DrawerLayout mDrawerLayout;
    private Toast viewHolderIconsToast;
    private Toast navToast;
    private Toast isBackPressedToast;
    private NavigationView navigationView;


    private ImageView clockIcon;
    private ImageView starIcon;
    private ImageView newCollectionIcon;
    private ImageView haveReadIcon;




    public static ArrayList<File> fileList = new ArrayList<File>();
    public static int REQUEST_PERMISSIONS = 1;
    boolean boolean_permission;
    File dir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.book_layout);
        findviews();
        initToolBar();
        initRecyclerView();
        sharedPrefsforSplash();
    }

    private void sharedPrefsforSplash() {

        SharedPreferences settings = getSharedPreferences("prefs", 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putBoolean("firstRun", false);
        editor.commit();

        boolean firstRun = settings.getBoolean("firstRun", true);
        Log.d("TAG1", "firstRun: " + Boolean.valueOf(firstRun).toString());
    }

    void findviews()
    {
        mainToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(mainToolbar);
        mDrawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        haveReadIcon = findViewById(R.id.have_read);
        clockIcon = findViewById(R.id.clock_icon);
        newCollectionIcon = findViewById(R.id.new_collection);
        starIcon = findViewById(R.id.star_icon);

        dir = new File(Environment.getExternalStorageDirectory().getAbsolutePath());
        fn_permission();
    }



    void initToolBar()
    {
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, mDrawerLayout, mainToolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);

        mDrawerLayout.addDrawerListener(toggle);
        toggle.syncState();
    }




    void initRecyclerView() {

    RecyclerView_books=findViewById(R.id.rv_books);
    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        RecyclerView_books.setLayoutManager(linearLayoutManager);

        RecyclerView_books.setHasFixedSize(true);
        mAdapter=new myAdapter(fileList,fileList.size(),this);
        RecyclerView_books.setAdapter(mAdapter);
}

    public ArrayList<File> getfile(File dir) {
        File[] listFile = dir.listFiles();
        if (listFile != null && listFile.length > 0) {
            for (int i = 0; i < listFile.length; i++) {

                if (listFile[i].isDirectory()) {
                    getfile(listFile[i]);

                } else {

                    boolean booleanpdf = false;
                    if (listFile[i].getName().endsWith(".pdf")) {

                        for (int j = 0; j < fileList.size(); j++) {
                            if (fileList.get(j).getName().equals(listFile[i].getName())) {
                                booleanpdf = true;
                            } else {

                            }
                        }

                        if (booleanpdf) {
                            booleanpdf = false;
                        } else {
                            fileList.add(listFile[i]);

                        }
                    }
                }
            }
        }
        return fileList;
    }
    private void fn_permission() {
        if ((ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)) {

            if ((ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this, android.Manifest.permission.READ_EXTERNAL_STORAGE))) {
            } else {
                ActivityCompat.requestPermissions(MainActivity.this, new String[]{android.Manifest.permission.READ_EXTERNAL_STORAGE},
                        REQUEST_PERMISSIONS);

            }
        } else {
            boolean_permission = true;

            getfile(dir);
            initRecyclerView();

        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_PERMISSIONS) {

            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                boolean_permission = true;
                getfile(dir);
                initRecyclerView();

            } else {
                Toast.makeText(getApplicationContext(), "Please allow the permission", Toast.LENGTH_LONG).show();

            }
        }
    }




    @Override
    public void onBackPressed()
    {
        if (mDrawerLayout.isDrawerOpen(GravityCompat.START))
        {
            mDrawerLayout.closeDrawer(GravityCompat.START);
        }

        else {
            super.onBackPressed();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(menu_main,menu);
        return true;
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemThatWasClickedId = item.getItemId();
        if (itemThatWasClickedId == R.id.action_search) {
            Context context = MainActivity.this;
            String textToShow = "Search clicked";
            Toast.makeText(context, textToShow, Toast.LENGTH_SHORT).show();
            return true;
        }


        if (itemThatWasClickedId==R.id.action_supportUs)
        {
            Context context = MainActivity.this;
            String textToShow = "Support Us clicked";
            Toast.makeText(context, textToShow, Toast.LENGTH_SHORT).show();
            return true;
        }


        if (itemThatWasClickedId==R.id.action_clearlist)
        {
            Context context = MainActivity.this;
            String textToShow = "Clear List Clicked";
            Toast.makeText(context, textToShow, Toast.LENGTH_SHORT).show();
            return true;
        }


        if (itemThatWasClickedId==R.id.action_sortby)
        {
            return true;
        }


        if (itemThatWasClickedId==R.id.action_filter)
        {
            Context context = MainActivity.this;
            String textToShow = "filter clicked";
            Toast.makeText(context, textToShow, Toast.LENGTH_SHORT).show();
            return true;
        }


        if (itemThatWasClickedId==R.id.action_share_the_reader)
        {
            Context context = MainActivity.this;
            String textToShow = "Share the reader clicked";
            Toast.makeText(context, textToShow, Toast.LENGTH_SHORT).show();
            return true;
        }






        return super.onOptionsItemSelected(item);
    }



    public void SettingsIconClicked(View view) {

        if (viewHolderIconsToast!=null)
        {
            viewHolderIconsToast.cancel();
        }

        PopupMenu popup = new PopupMenu(this, view);
        MenuInflater inflater = popup.getMenuInflater();
        inflater.inflate(R.menu.settings_icon_menu, popup.getMenu());
        popup.show();
    }



    @Override
    public void onListItemClick(int clickedItemIndex) {
        Intent intent=new Intent(this,book_info_handler.class);
        intent.putExtra("book_title",fileList.get(clickedItemIndex).getName());
        intent.putExtra("clickedItemIndex",clickedItemIndex);
        startActivity(intent);
    }

    ItemTouchHelper.SimpleCallback itemTouchHelperCallback=new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.RIGHT | ItemTouchHelper.LEFT) {
        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
            return false;
        }

        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
        }
    };



    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        int idofitem=menuItem.getItemId();
        switch (idofitem)
        {
            case R.id.nav_reading_now:
                if (navToast!=null)
                {
                    navToast.cancel();
                }
                navToast=Toast.makeText(this,"Reading Now clicked",Toast.LENGTH_SHORT);
                navToast.show();
                break;

            case R.id.nav_books_and_documents:
                if (navToast!=null)
                {
                    navToast.cancel();
                }
                navToast=Toast.makeText(this,"Books & documents clicked",Toast.LENGTH_SHORT);
                navToast.show();
                break;

            case R.id.nav_favourites:
                if (navToast!=null)
                {
                    navToast.cancel();
                }
                navToast=Toast.makeText(this,"Favourites clicked",Toast.LENGTH_SHORT);
                navToast.show();
                break;

            case R.id.nav_to_read:
                if (navToast!=null)
                {
                    navToast.cancel();
                }
                navToast=Toast.makeText(this,"To Read clicked",Toast.LENGTH_SHORT);
                navToast.show();
                break;

            case R.id.nav_nav_have_read:
                if (navToast!=null)
                {
                    navToast.cancel();
                }
                navToast=Toast.makeText(this,"Have Read clicked",Toast.LENGTH_SHORT);
                navToast.show();
                break;

            case R.id.nav_authors:
                if (navToast!=null)
                {
                    navToast.cancel();
                }
                navToast=Toast.makeText(this,"Authors clicked",Toast.LENGTH_SHORT);
                navToast.show();
                break;

            case R.id.nav_series:
                if (navToast!=null)
                {
                    navToast.cancel();
                }
                navToast=Toast.makeText(this,"Series clicked",Toast.LENGTH_SHORT);
                navToast.show();
                break;

            case R.id.nav_collections:
                if (navToast!=null)
                {
                    navToast.cancel();
                }
                navToast=Toast.makeText(this,"Collections clicked",Toast.LENGTH_SHORT);
                navToast.show();
                break;

            case R.id.nav_formats:
                if (navToast!=null)
                {
                    navToast.cancel();
                }
                navToast=Toast.makeText(this,"Formats clicked",Toast.LENGTH_SHORT);
                navToast.show();
                break;

            case R.id.nav_folders:
                Toast.makeText(this,"Folders clicked",Toast.LENGTH_SHORT).show();
                break;

            case R.id.nav_downloads:
                if (navToast!=null)
                {
                    navToast.cancel();
                }
                navToast=Toast.makeText(this,"Downloads clicked",Toast.LENGTH_SHORT);
                navToast.show();
                break;

            case R.id.nav_trash:
                if (navToast!=null)
                {
                    navToast.cancel();
                }
                navToast=Toast.makeText(this,"Trash clicked",Toast.LENGTH_SHORT);
                navToast.show();
                break;

            case R.id.nav_settings:
                if (navToast!=null)
                {
                    navToast.cancel();
                }
                navToast=Toast.makeText(this,"Settings clicked",Toast.LENGTH_SHORT);
                navToast.show();
                break;

            case R.id.nav_send_feedback:
                if (navToast!=null)
                {
                    navToast.cancel();
                }
                navToast=Toast.makeText(this,"Send Feedback clicked",Toast.LENGTH_SHORT);
                navToast.show();
                break;

        }

        mDrawerLayout.closeDrawer(GravityCompat.START);
        return true;

    }



    public void NewCollectionIconClicked(View view) {

        if (NewCollectionResult)
        {
            newCollectionIcon.setImageResource(R.drawable.ic_action_new_collection_icon_yellow);
            NewCollectionResult=false;
        }

        else
        {
            newCollectionIcon.setImageResource(R.drawable.ic_action_new_collection_icon_black);
            NewCollectionResult=true;
        }
        if (viewHolderIconsToast!=null)
        {
            viewHolderIconsToast.cancel();
        }

        Toast.makeText(this,"New Collection Icon Clicked",Toast.LENGTH_SHORT).show();
    }



    public void HaveReadIconClicked(View view) {

        if (HaveReadResult==true) {
            haveReadIcon.setImageResource(R.drawable.ic_action_have_read_icon_yellow);
            HaveReadResult=false;
        }
        else {
            haveReadIcon.setImageResource(R.drawable.ic_action_have_read_icon_black);
            HaveReadResult=true;
        }
        if (viewHolderIconsToast!=null)
        {
            viewHolderIconsToast.cancel();
        }

        Toast.makeText(this,"Have Read Icon Clicked",Toast.LENGTH_SHORT).show();
    }



    public void ClockIconClicked(View view) {

        if (ClockIconResult==true) {
            clockIcon.setImageResource(R.drawable.ic_action_clock_icon_yellow);
            ClockIconResult=false;
        }
        else {
            clockIcon.setImageResource(R.drawable.ic_action_clock_icon_black);
            ClockIconResult=true;
        }

        if (viewHolderIconsToast!=null)
        {
            viewHolderIconsToast.cancel();
        }

        Toast.makeText(this,"Clock Icon Clicked",Toast.LENGTH_SHORT).show();
    }



    public void StarIconClicked(View view) {

        if (StarIconResult==true) {
            starIcon.setImageResource(R.drawable.ic_action_star_icon_yellow);
            StarIconResult=false;
        }
        else {
            starIcon.setImageResource(R.drawable.ic_action_star_icon_black);
            StarIconResult=true;
        }
    if (viewHolderIconsToast!=null)
    {
        viewHolderIconsToast.cancel();
    }
        Toast.makeText(this,"Star Icon Clicked",Toast.LENGTH_SHORT).show();
    }
}
