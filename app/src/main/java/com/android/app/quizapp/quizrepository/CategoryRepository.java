package com.android.app.quizapp.quizrepository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.android.app.quizapp.quizdb.Category;
import com.android.app.quizapp.quizdb.CategoryDao;
import com.android.app.quizapp.quizdb.Quiz;
import com.android.app.quizapp.quizdb.QuizDB;

import java.util.List;

public class CategoryRepository {
    QuizDB quizDB;
    CategoryDao categoryDao;
    LiveData<List<Category>> allCategories;
    public CategoryRepository(Application application){
        quizDB = QuizDB.getInstance(application);
        categoryDao = quizDB.getCategoryDao();
        allCategories = categoryDao.getAllCategory();

    }

    public LiveData<List<Category>> getAllCategories(){
        return allCategories;
    }


    public void insertCategory(Category category){
     new InsertCategoryAsynTask(categoryDao).execute(category);
    }

    public void deleteCategory(Category category){
        new DeleteCategoryAsyncTask(categoryDao).execute(category);
    }

    private class InsertCategoryAsynTask extends AsyncTask<Category,Void,Void> {
        CategoryDao catDao;
        public InsertCategoryAsynTask(CategoryDao catDao){
            this.catDao = catDao;
        }
        @Override
        protected Void doInBackground(Category... categories) {
            catDao.insertCategory(categories[0]);
            return null;
        }
    }

    private class DeleteCategoryAsyncTask extends AsyncTask<Category,Void,Void>{

        CategoryDao catDao;
        public DeleteCategoryAsyncTask(CategoryDao catDao){
            this.catDao = catDao;
        }

        @Override
        protected Void doInBackground(Category... categories) {
            catDao.deleteCategory(categories[0]);
            return null;
        }
    }
}
