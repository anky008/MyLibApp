package com.example.mylibrary;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.PopupMenu;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import static com.example.mylibrary.R.menu.menu_main;

public class MainActivity extends AppCompatActivity implements myAdapter.ListItemClickListener,NavigationView.OnNavigationItemSelectedListener {

    private boolean HaveReadResult = true;
    private boolean StarIconResult = true;
    private boolean ClockIconResult = true;
    private boolean NewCollectionResult = true;

    static final int NUM_LIST_ITEMS = 100;
    private myAdapter mAdapter;
    private RecyclerView RecyclerView_books;
    private Toolbar mainToolbar;
    private DrawerLayout mDrawerLayout;
    private Toast viewHolderIconsToast;
    private Toast navToast;
    private NavigationView navigationView;


    private ImageView clockIcon;
    private ImageView starIcon;
    private ImageView newCollectionIcon;
    private ImageView haveReadIcon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.book_layout);
        findviews();
        initToolBar();
        initRecyclerView();
    }


    void findviews()
    {
        mainToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(mainToolbar);
        mDrawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        haveReadIcon = (ImageView) findViewById(R.id.have_read);
        clockIcon = (ImageView) findViewById(R.id.clock_icon);
        newCollectionIcon = (ImageView) findViewById(R.id.new_collection);
        starIcon = (ImageView) findViewById(R.id.star_icon);
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
        mAdapter=new myAdapter(NUM_LIST_ITEMS,this);
        RecyclerView_books.setAdapter(mAdapter);
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
        intent.putExtra(Intent.EXTRA_TEXT,clickedItemIndex);
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

        viewHolderIconsToast.makeText(this,"New Collection Icon Clicked",Toast.LENGTH_SHORT).show();
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

        viewHolderIconsToast.makeText(this,"Have Read Icon Clicked",Toast.LENGTH_SHORT).show();
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

        viewHolderIconsToast.makeText(this,"Clock Icon Clicked",Toast.LENGTH_SHORT).show();
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
        viewHolderIconsToast.makeText(this,"Star Icon Clicked",Toast.LENGTH_SHORT).show();
    }
}
