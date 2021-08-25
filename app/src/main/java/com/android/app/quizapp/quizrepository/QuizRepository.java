package com.android.app.quizapp.quizrepository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.android.app.quizapp.quizdb.Category;
import com.android.app.quizapp.quizdb.CategoryDao;
import com.android.app.quizapp.quizdb.Quiz;
import com.android.app.quizapp.quizdb.QuizDB;
import com.android.app.quizapp.quizdb.QuizDao;

import java.util.List;

public class QuizRepository {
    QuizDB quizDB;
    QuizDao quizDao;

    LiveData<List<Quiz>> allQuizze;
    public QuizRepository(Application application) {
        quizDB = QuizDB.getInstance(application);
        quizDao = quizDB.getQuizDao();

    }

    public LiveData<List<Quiz>> getAllQuizze(String title){
        allQuizze = quizDao.getAll(title);
        return allQuizze;
    }

    public void insertQuiz(Quiz quiz){
        new InsertQuizAsynTask(quizDao).execute(quiz);
    }

    public void updateQuiz(Quiz quiz){
        new UpdateQuizAsynTask(quizDao).execute(quiz);
    }

    public void deleteQuiz(Quiz quiz){
        new DeleteQuizAsynTask(quizDao).execute(quiz);
    }

    public void deleteAllQuiz(String title){
        new DeleteAllQuizAsynTask(quizDao).execute(title);
    }


    private class InsertQuizAsynTask extends AsyncTask<Quiz,Void,Void>{
        QuizDao quizDao;
        public InsertQuizAsynTask(QuizDao quizDao){
            this.quizDao = quizDao;
        }
        @Override
        protected Void doInBackground(Quiz... quizzes) {
            quizDao.insert(quizzes[0]);
            return null;
        }
    }

    private class UpdateQuizAsynTask extends AsyncTask<Quiz,Void,Void>{
        QuizDao quizDao;
        public UpdateQuizAsynTask(QuizDao quizDao){
            this.quizDao = quizDao;
        }
        @Override
        protected Void doInBackground(Quiz... quizzes) {
            quizDao.update(quizzes[0]);
            return null;
        }
    }

    private class DeleteQuizAsynTask extends AsyncTask<Quiz,Void,Void>{
        QuizDao quizDao;
        public DeleteQuizAsynTask(QuizDao quizDao){
            this.quizDao = quizDao;
        }
        @Override
        protected Void doInBackground(Quiz... quizzes) {
            quizDao.delete(quizzes[0]);
            return null;
        }
    }
    private class DeleteAllQuizAsynTask extends AsyncTask<String,Void,Void>{
        QuizDao quizDao;
        public DeleteAllQuizAsynTask(QuizDao quizDao){
            this.quizDao = quizDao;
        }

        @Override
        protected Void doInBackground(String... strings) {
            quizDao.deleteAll(strings[0]);
            return null;
        }
    }
}
