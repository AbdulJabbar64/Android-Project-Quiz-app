package com.android.app.quizapp.quizdb;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface CategoryDao {
    @Insert
    void insertCategory(Category category);

    @Query("Select * from Category")
    LiveData<List<Category>> getAllCategory();

    @Delete
    void deleteCategory(Category category);

}
