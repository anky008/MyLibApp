package com.example.mylibrary;

public class EachViewHolderItem {
    String bookName;
    String bookAuthor;
    int imageResource;

    EachViewHolderItem(String bookName,String bookAuthor,int imageResource)
    {
        this.bookName=bookName;
        this.bookAuthor=bookAuthor;
        this.imageResource=imageResource;
    }

    public int getImageResource() {
        return imageResource;
    }

    public String getBookAuthor() {
        return bookAuthor;
    }

    public String getBookName() {
        return bookName;
    }
}
