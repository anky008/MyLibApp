package com.example.mylibrary;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.github.barteksc.pdfviewer.PDFView;
import com.github.barteksc.pdfviewer.listener.OnLoadCompleteListener;
import com.github.barteksc.pdfviewer.listener.OnPageChangeListener;
import com.github.barteksc.pdfviewer.scroll.DefaultScrollHandle;
import com.shockwave.pdfium.PdfDocument;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class PDFActivity extends AppCompatActivity implements OnPageChangeListener,OnLoadCompleteListener {

    PDFView pdfView;
    Integer pageNumber = 0;
    String pdfFileName;
    String TAG="PdfActivity";
    int position=-1;
    int getpageCount;
    int closingPage=0;
    String filelocation;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdf);
        init();
    }

    private void init(){
        pdfView= findViewById(R.id.pdfView);
        position = getIntent().getIntExtra("position",-1);
        displayFromSdcard();
    }

    private void displayFromSdcard() {
        pdfFileName = MainActivity.fileList.get(position).getName();


        pdfView.fromFile(MainActivity.fileList.get(position))
                .defaultPage(pageNumber)
                .enableSwipe(true)

                .swipeHorizontal(false)
                .onPageChange(this)
                .enableAnnotationRendering(true)
                .onLoad(this)
                .scrollHandle(new DefaultScrollHandle(this))
                .load();

        Log.e("filelocation",MainActivity.fileList.get(position).toString());
        filelocation=MainActivity.fileList.get(position).toString();
    }
    @Override
    public void onPageChanged(int page, int pageCount) {
        pageNumber = page;
        setTitle(String.format("%s %s / %s", pdfFileName, page + 1, pageCount));
        getpageCount=pageCount;
        closingPage=page;
    }
    @Override
    public void loadComplete(int nbPages) {
        PdfDocument.Meta meta = pdfView.getDocumentMeta();
        printBookmarksTree(pdfView.getTableOfContents(), "-");

        Log.e("possible meta", String.valueOf(meta));
    }

    public void printBookmarksTree(List<PdfDocument.Bookmark> tree, String sep) {
        for (PdfDocument.Bookmark b : tree) {

            if (b.hasChildren()) {
                printBookmarksTree(b.getChildren(), sep + "-");
            }
        }
    }

    @Override
    public void onBackPressed()
    {
        Intent data=new Intent(this,book_info_handler.class);
        int percentage=0;
        try {
            percentage=(closingPage*100)/getpageCount;
        }

        catch(ArithmeticException e)
        {
            percentage=0;
        }


        data.putExtra("percentage",Integer.toString(percentage));
        String sdf = new SimpleDateFormat("LLL dd, yyyy", Locale.getDefault()).format(new Date());
        String time = new SimpleDateFormat("hh : mm a", Locale.getDefault()).format(Calendar.getInstance().getTime());
        String currentDateandTime=sdf+" " +" "+time;
        data.putExtra("date",currentDateandTime);
        data.putExtra("filelocation",filelocation);
        setResult(RESULT_OK,data);
        Log.e("datepdf",currentDateandTime);
        Log.e("PageCount",Integer.toString(getpageCount));
        finish();
    }
}
