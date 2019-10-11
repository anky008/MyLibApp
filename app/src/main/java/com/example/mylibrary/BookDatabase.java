package com.example.mylibrary;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Book.class}, version = 1)
public abstract class BookDatabase extends RoomDatabase {

    private static BookDatabase INSTANCE;
    private static final String DB_NAME = "books.db";

}
