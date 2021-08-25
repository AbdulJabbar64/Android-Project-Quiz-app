package com.android.app.quizapp.quizrepository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.android.app.quizapp.quizdb.Quiz;
import com.android.app.quizapp.quizdb.QuizDB;
import com.android.app.quizapp.quizdb.QuizDao;
import com.android.app.quizapp.quizdb.User;
import com.android.app.quizapp.quizdb.UserDao;

import java.util.List;

public class UserRepository {
    QuizDB quizDB;
    UserDao userDao;

    User user;

    public UserRepository(Application application) {
        quizDB = QuizDB.getInstance(application);
        userDao = quizDB.getUserDao();
    }

    public User getCurrentUser(String email){
        user = userDao.getAllUser(email);
        return user;
    }

    public void insertUser(User user){
        new UserRepository.InsertUserAsyncTask(userDao).execute(user);
    }

    private class InsertUserAsyncTask extends AsyncTask<User,Void,Void> {
        UserDao userDao;
        public InsertUserAsyncTask(UserDao userDao){
            this.userDao = userDao;
        }

        @Override
        protected Void doInBackground(User... users) {
            userDao.insert(users[0]);
            return null;
        }
    }
}
