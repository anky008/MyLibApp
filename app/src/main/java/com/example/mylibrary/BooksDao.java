package com.example.mylibrary;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;


@Dao
public interface BooksDao {

    @Query("SELECT * FROM book")
    List<Book> getAll();

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    long insert(Book book);

    @Delete
    void delete(Book book);

    @Update(onConflict = OnConflictStrategy.IGNORE)
    void update(Book book);

    @Query("SELECT * FROM book")
    LiveData<List<Book>> getAllBooks();

}