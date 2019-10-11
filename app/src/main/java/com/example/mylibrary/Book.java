package com.example.mylibrary;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "book")
public class Book implements Serializable {
    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "uri")
    private String uri;

    @ColumnInfo(name = "percentage")
    private Float percent;

    @ColumnInfo(name = "rating")
    private Float rating;

    @ColumnInfo(name = "last_read")
    private boolean lastread;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public Float getPercent() {
        return percent;
    }

    public void setPercent(Float percent) {
        this.percent = percent;
    }

    public Float getRating() {
        return rating;
    }

    public void setRating(Float rating) {
        this.rating = rating;
    }

    public boolean isLastread() {
        return lastread;
    }

    public void setLastread(boolean lastread) {
        this.lastread = lastread;
    }


}
