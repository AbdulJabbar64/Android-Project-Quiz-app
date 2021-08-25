package com.android.app.quizapp.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.app.quizapp.MainActivity;
import com.android.app.quizapp.R;
import com.android.app.quizapp.quizdb.Quiz;
import com.android.app.quizapp.user.UserQuiz;
import com.android.app.quizapp.viewModel.QuizViewModel;

import java.util.List;

public class QuizActivity extends AppCompatActivity implements View.OnClickListener {

    TextView answeredQuestion, totalQuestions, question, option1, option2, option3, option4;
    ProgressBar questions_askedProgress;
    ImageButton next;
    QuizViewModel quiz_model;
    LiveData<List<Quiz>> quiz;
    int count = 0;
    int progressCount = 1;
    String correct = "";
    int correctCount = 0;
    Dialog quiz_end_dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        Intent intent = getIntent();
        String title = intent.getStringExtra("Title");
        initUI();

        quiz_model = ViewModelProviders.of(this).get(QuizViewModel.class);
        quiz = quiz_model.getAllQuiz(title);
        quiz.observe(this, new Observer<List<Quiz>>() {
            @Override
            public void onChanged(List<Quiz> quizzes) {
                if (quizzes.size() == 0) {
                    setOptionSEmpty();
                    Toast.makeText(getApplicationContext(), "NO Quiz For this Category", Toast.LENGTH_SHORT).show();
                } else
                    setQuiz(quizzes);


            }
        });

        option1.setOnClickListener(this);
        option2.setOnClickListener(this);
        option3.setOnClickListener(this);
        option4.setOnClickListener(this);

    }

    private void setOptionSEmpty() {
        option1.setText(". . . ");
        option2.setText(". . . ");
        option3.setText(". . . ");
        option4.setText(". . . ");
        question.setText(". . . ");
        totalQuestions.setText("" + 0);
        answeredQuestion.setText("" + 0);
        questions_askedProgress.setProgress(0);
        option1.setClickable(false);
        option2.setClickable(false);
        option3.setClickable(false);
        option4.setClickable(false);
        next.setEnabled(false);
    }


    private void SetOptions() {
        question.setText("");
        option1.setText("");
        option2.setText("");
        option3.setText("");
        option4.setText("");

        option1.setBackgroundResource(R.drawable.options_drawable);
        option1.setTextColor(Color.WHITE);

        option2.setBackgroundResource(R.drawable.options_drawable);
        option2.setTextColor(Color.WHITE);

        option3.setBackgroundResource(R.drawable.options_drawable);
        option3.setTextColor(Color.WHITE);

        option4.setBackgroundResource(R.drawable.options_drawable);
        option4.setTextColor(Color.WHITE);


    }


    private void initUI() {
        answeredQuestion = findViewById(R.id.answeredQuestion_txt);
        totalQuestions = findViewById(R.id.totalQuestion_txt);
        questions_askedProgress = findViewById(R.id.questions_asked);
        question = findViewById(R.id.question);
        option1 = findViewById(R.id.option1);
        option2 = findViewById(R.id.option2);
        option3 = findViewById(R.id.option3);
        option4 = findViewById(R.id.option4);
        next = findViewById(R.id.next);
        quiz_end_dialog = new Dialog(QuizActivity.this);
        quiz_end_dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        quiz_end_dialog.setCancelable(true);
    }



    private void setQuiz(List<Quiz> quiz) {
        totalQuestions.setText("" + quiz.size());
        answeredQuestion.setText("" + progressCount);
        questions_askedProgress.setMax(quiz.size());
        questions_askedProgress.setProgress(progressCount);

        String questionTxt = quiz.get(count).getQuestion();
        String option1Txt = quiz.get(count).getOption1();
        String option2Txt = quiz.get(count).getOption2();
        String option3Txt = quiz.get(count).getOption3();
        String option4Txt = quiz.get(count).getOption4();
        correct = quiz.get(count).getCorrect();

        question.setText(questionTxt);
        option1.setText(option1Txt);
        option2.setText(option2Txt);
        option3.setText(option3Txt);
        option4.setText(option4Txt);

        // On Next Click
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (progressCount == quiz.size()) {
                    double correctCountPercent = (correctCount / quiz.size());
                    correctCountPercent = correctCountPercent * 100;
                    if (correctCountPercent > 50) {
                        highQuiz(quiz);
                    } else {
                        lowQuiz(quiz);
                    }
                    Toast.makeText(QuizActivity.this, "Reached at Limit", Toast.LENGTH_SHORT).show();
                } else {
                    progressCount = progressCount + 1;
                    count = count + 1;
                    questions_askedProgress.setProgress(progressCount);
                    answeredQuestion.setText("" + progressCount);
                    SetOptions();
                    question.setText(quiz.get(count).getQuestion());
                    option1.setText(quiz.get(count).getOption1());
                    option2.setText(quiz.get(count).getOption2());
                    option3.setText(quiz.get(count).getOption3());
                    option4.setText(quiz.get(count).getOption4());
                    correct = quiz.get(count).getCorrect();
                }

            }
        });
    }

    private void highQuiz(List<Quiz> quiz) {
        quiz_end_dialog.setContentView(R.layout.high_marks_dialog);
        TextView score = quiz_end_dialog.findViewById(R.id.highScores);
        score.setText("Your total Score is " + correctCount + " out of " + quiz.size());
        quiz_end_dialog.show();
        quiz_end_dialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                startActivity(new Intent(QuizActivity.this, UserQuiz.class));
            }
        });
    }

    private void lowQuiz(List<Quiz> quiz) {
        quiz_end_dialog.setContentView(R.layout.low_marks_dialog);
        TextView score = quiz_end_dialog.findViewById(R.id.lowMarks);
        score.setText("Your total Score is " + correctCount + " out of " + quiz.size());
        quiz_end_dialog.show();
        quiz_end_dialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                startActivity(new Intent(QuizActivity.this, UserQuiz.class));
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.option1:
                option1.setTextColor(Color.BLACK);
                if (option1.getText().toString().equals(correct)) {
                    correctCount++;
                    option1.setBackgroundResource(R.drawable.correct_selection_drawable);
                } else {
                    option1.setBackgroundResource(R.drawable.wrong_selection_drawable);
                }
                break;

            case R.id.option2:
                option2.setTextColor(Color.BLACK);
                if (option2.getText().toString().equals(correct)) {
                    correctCount++;
                    option2.setBackgroundResource(R.drawable.correct_selection_drawable);
                } else {
                    option2.setBackgroundResource(R.drawable.wrong_selection_drawable);
                }
                break;

            case R.id.option3:
                option3.setTextColor(Color.BLACK);
                if (option3.getText().toString().equals(correct)) {
                    correctCount++;
                    option3.setBackgroundResource(R.drawable.correct_selection_drawable);
                } else {
                    option3.setBackgroundResource(R.drawable.wrong_selection_drawable);
                }
                break;

            case R.id.option4:
                option4.setTextColor(Color.BLACK);
                if (option4.getText().toString().equals(correct)) {
                    correctCount++;
                    option4.setBackgroundResource(R.drawable.correct_selection_drawable);
                } else
                    option4.setBackgroundResource(R.drawable.wrong_selection_drawable);
                break;
        }
    }
}