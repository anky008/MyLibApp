package com.example.mylibrary;

import java.util.Date;

public class StoreDataPdf {

    private Date closingDate;
    private int percentageRead;

    StoreDataPdf()
    {
        closingDate=null;
        percentageRead=-1;

    }


    StoreDataPdf(Date date,int percentageread)
    {
        closingDate=date;
        percentageRead=percentageread;
    }

    Date getClosingDate()
    {
        return closingDate;
    }

    int getPercentageRead()
    {
        return percentageRead;
    }
}
