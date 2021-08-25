package com.android.app.quizapp.quizdb;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface UserDao {

    @Insert
    void insert(User user);

    @Query("Select * from User where email = :email")
    User getAllUser(String email) ;
}
