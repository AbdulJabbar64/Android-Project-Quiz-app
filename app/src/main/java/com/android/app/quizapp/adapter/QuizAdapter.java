package com.android.app.quizapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.android.app.quizapp.R;
import com.android.app.quizapp.quizdb.Quiz;

import java.util.List;

public class QuizAdapter extends ArrayAdapter {
    List<Quiz> list;

    public QuizAdapter(@NonNull Context context, int resource) {
        super(context, resource);
    }

    public void setQuiz(List<Quiz> quiz){
        this.list = quiz;
        notifyDataSetChanged();
    }
}
