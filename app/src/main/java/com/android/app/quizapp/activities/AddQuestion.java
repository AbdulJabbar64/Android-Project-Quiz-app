package com.android.app.quizapp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.app.quizapp.R;
import com.android.app.quizapp.quizdb.Quiz;
import com.android.app.quizapp.viewModel.QuizViewModel;

public class AddQuestion extends AppCompatActivity {

    EditText question, option1, option2, option3, option4;
    Spinner correctoption;
    String[] options = {"Option 1", "Option 2", "Option 3", "Option 4"};
    Button addQuestion;
    Intent intent;
    TextView category_title;
    QuizViewModel model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_question);

        intent = getIntent();
        model = new QuizViewModel(getApplication());

        String title = intent.getStringExtra("Title");

        question = findViewById(R.id.add_question);
        option1 = findViewById(R.id.add_option1);
        option2 = findViewById(R.id.add_option2);
        option3 = findViewById(R.id.add_option3);
        option4 = findViewById(R.id.add_option4);
        category_title = findViewById(R.id.add_title);
        correctoption = findViewById(R.id.correct_option);
        addQuestion = findViewById(R.id.addQuestion);
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1,options);
        correctoption.setAdapter(adapter);

        category_title.setText(title);
        addQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String questionText = question.getText().toString();
                String option1Text = option1.getText().toString();
                String option2Text = option2.getText().toString();
                String option3Text = option3.getText().toString();
                String option4Text = option4.getText().toString();

                String answerSelected = "";
                switch (correctoption.getSelectedItemPosition()){
                    case 0:
                        answerSelected= option1Text;
                        break;

                    case 1:
                        answerSelected= option2Text;
                        break;

                    case 2:
                        answerSelected= option3Text;
                        break;

                    case 3:
                        answerSelected= option4Text;
                        break;

                }
                Toast.makeText(AddQuestion.this, "Answer: "+answerSelected, Toast.LENGTH_SHORT).show();
                Quiz quiz = new Quiz(questionText,option1Text,option2Text,option3Text,option4Text,answerSelected,title);
                model.insertQuiz(quiz);
                Intent goBack = new Intent();
                setResult(RESULT_OK,goBack);
                finish();
                Toast.makeText(AddQuestion.this, "Question added Successfully !", Toast.LENGTH_SHORT).show();
            }
        });
    }
}