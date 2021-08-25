package com.android.app.quizapp.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.android.app.quizapp.quizdb.Category;
import com.android.app.quizapp.quizrepository.CategoryRepository;
import com.android.app.quizapp.quizrepository.QuizRepository;

import java.util.List;

public class CategoryViewModel extends AndroidViewModel {
    CategoryRepository repository;
    LiveData<List<Category>> getAllCategories;
    public CategoryViewModel(@NonNull Application application) {
        super(application);

        repository = new CategoryRepository(application);
        getAllCategories = repository.getAllCategories();
    }

    public LiveData<List<Category>> getAllCategories(){
        return getAllCategories;
    }

    public void insertCategory(Category category){
        repository.insertCategory(category);
    }

    public void deleteCategory(Category category){
        repository.deleteCategory(category);
    }

}
