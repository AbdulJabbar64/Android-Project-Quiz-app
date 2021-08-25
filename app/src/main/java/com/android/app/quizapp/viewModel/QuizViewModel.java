package com.android.app.quizapp.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.android.app.quizapp.quizdb.Quiz;
import com.android.app.quizapp.quizrepository.QuizRepository;

import java.util.List;

public class QuizViewModel extends AndroidViewModel {
    QuizRepository repository;
    LiveData<List<Quiz>> allQuiz;
    public QuizViewModel(@NonNull Application application ) {
        super(application);
        repository = new QuizRepository(application);

    }

    public LiveData<List<Quiz>> getAllQuiz(String title){
        allQuiz = repository.getAllQuizze(title);
        return allQuiz;
    }
    public void insertQuiz(Quiz quiz){
        repository.insertQuiz(quiz);
    }

    public void updateQuiz(Quiz quiz){
        repository.updateQuiz(quiz);
    }

    public void deleteQuiz(Quiz quiz){
        repository.deleteQuiz(quiz);
    }

    public void deleteAll(String title){
        repository.deleteAllQuiz(title);
    }
}
