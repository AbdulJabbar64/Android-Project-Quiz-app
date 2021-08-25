package com.android.app.quizapp.quizdb;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(foreignKeys = {@ForeignKey(entity = Category.class,
        parentColumns = "title",
        childColumns = "title",
        onDelete = ForeignKey.CASCADE)})
public class Quiz {
    @PrimaryKey()
    @NonNull
    private String question;
    private String option1;
    private String option2;
    private String option3;
    private String option4;
    private String correct;
    private String title;

    public Quiz(String question, String option1, String option2, String option3, String option4, String correct, String title) {
        this.question = question;
        this.option1 = option1;
        this.option2 = option2;
        this.option3 = option3;
        this.option4 = option4;
        this.correct = correct;
        this.title = title;
    }

    public String getQuestion() {
        return question;
    }

    public String getOption1() {
        return option1;
    }

    public String getOption2() {
        return option2;
    }

    public String getOption3() {
        return option3;
    }

    public String getOption4() {
        return option4;
    }

    public String getCorrect() {
        return correct;
    }

    public String getTitle() {
        return title;
    }
}
