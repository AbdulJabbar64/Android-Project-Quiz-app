package com.android.app.quizapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.app.quizapp.activities.AddQuestion;
import com.android.app.quizapp.activities.QuizActivity;
import com.android.app.quizapp.adapter.CategoriesAdapter;
import com.android.app.quizapp.admin.AddNewCategory;
import com.android.app.quizapp.quizdb.Category;
import com.android.app.quizapp.user.UserQuiz;
import com.android.app.quizapp.viewModel.CategoryViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class MainActivity extends AppCompatActivity {


    LiveData<List<Category>> allCategories;
    ListView categoriesList;
    FloatingActionButton fab;
    public static final int ADD_NEW_CATEGORY = 1;
    private static final int ADD_QUESTION = 2;
    CategoryViewModel viewModel;
    List<Category> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        categoriesList = findViewById(R.id.categories);
        fab = findViewById(R.id.addCategory);

        viewModel = ViewModelProviders.of(this).get(CategoryViewModel.class);
        allCategories = viewModel.getAllCategories();
        allCategories.observe(this, new Observer<List<Category>>() {
            @Override
            public void onChanged(List<Category> categories) {
                CategoriesAdapter adapter = new CategoriesAdapter();
                adapter.setCategories(categories);
                list = categories;
                categoriesList.setAdapter(adapter);
            }
        });

        categoriesList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent moveTo = new Intent(MainActivity.this, AddQuestion.class);
                moveTo.putExtra("Title", list.get(position).getTitle());
                startActivityForResult(moveTo,ADD_QUESTION);
            }
        });
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent moveTO = new Intent(MainActivity.this, AddNewCategory.class);
                startActivityForResult(moveTO, ADD_NEW_CATEGORY);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.user_mode, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()){
            case R.id.user_mode:
                startActivity(new Intent(MainActivity.this, UserQuiz.class));
                return true;

            default:
                return false;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case ADD_NEW_CATEGORY:
                if (data == null) {
                    Toast.makeText(this, "Null", Toast.LENGTH_SHORT).show();
                }
                if (resultCode == RESULT_OK) {
                    String title = data.getStringExtra("Title");
                    String subtitle = data.getStringExtra("Subtitle");
                    viewModel.insertCategory(new Category(title, subtitle));
                } else {
                    Toast.makeText(this, "Error: " + resultCode, Toast.LENGTH_SHORT).show();
                }
                break;

            case ADD_QUESTION:
                if (data == null) {
                    Toast.makeText(this, "Null", Toast.LENGTH_SHORT).show();
                }
                if (resultCode == RESULT_OK) {
                    Toast.makeText(this, "Added Succesfully !", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "Error: " + resultCode, Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }
}