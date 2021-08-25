package com.android.app.quizapp.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.android.app.quizapp.quizdb.Quiz;
import com.android.app.quizapp.quizdb.User;
import com.android.app.quizapp.quizrepository.QuizRepository;
import com.android.app.quizapp.quizrepository.UserRepository;

import java.util.List;

public class UserViewModel extends AndroidViewModel {
    UserRepository repository;
    User currentUser;


    public UserViewModel(@NonNull Application application) {
        super(application);
        repository = new UserRepository(application);

    }

    public User getCurrentUser(String email){
        currentUser = repository.getCurrentUser(email);
        return currentUser;
    }

    public void insertUser(User user){
        repository.insertUser(user);
    }

}
