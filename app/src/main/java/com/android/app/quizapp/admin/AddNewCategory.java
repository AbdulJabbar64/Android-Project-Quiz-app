package com.android.app.quizapp.admin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.app.quizapp.R;
import com.android.app.quizapp.quizdb.Category;
import com.android.app.quizapp.viewModel.CategoryViewModel;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class AddNewCategory extends AppCompatActivity {

    TextInputLayout inputLayout;
    TextInputLayout subtitleLayout;
    Button createCategory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_category);

        inputLayout = findViewById(R.id.title_layout);
        subtitleLayout = findViewById(R.id.subtitle_layout);



        createCategory = findViewById(R.id.create_category);

        createCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = inputLayout.getEditText().getText().toString().trim();
                String subtitle = subtitleLayout.getEditText().getText().toString();
                Intent intent = new Intent();
                intent.putExtra("Title",title);
                intent.putExtra("Subtitle",subtitle);
                setResult(RESULT_OK,intent);
                finish();
            }
        });

    }
}