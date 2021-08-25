package com.android.app.quizapp.quizdb;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Quiz.class,Category.class, User.class},version = 1)
public abstract class QuizDB extends RoomDatabase{
    private static QuizDB instance = null;
    public abstract CategoryDao getCategoryDao();
    public abstract QuizDao getQuizDao();
    public abstract UserDao getUserDao();
    public static QuizDB getInstance(Context context){
        if(instance == null){
            instance = Room.databaseBuilder(context,QuizDB.class,"Quiz_DB").build();
        }
        return instance;
    }
}