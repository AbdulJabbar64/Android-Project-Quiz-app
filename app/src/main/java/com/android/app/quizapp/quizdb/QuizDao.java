package com.android.app.quizapp.quizdb;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface QuizDao {

    @Insert
    void insert(Quiz quiz);

    @Update
    void update(Quiz quiz);

    @Delete
    void delete(Quiz quiz);

    @Query("Select * from Quiz WHERE title = :title")
    LiveData<List<Quiz>> getAll(String title);

    @Query("Delete from Quiz WHERE title = :title")
    void deleteAll(String title);
}
