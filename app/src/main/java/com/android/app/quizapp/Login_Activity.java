package com.android.app.quizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.app.quizapp.admin.Admin_Login;
import com.android.app.quizapp.quizdb.User;
import com.android.app.quizapp.viewModel.UserViewModel;

public class Login_Activity extends AppCompatActivity {
    Button register;
    TextView name,eamil,password;
    UserViewModel model1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        name = findViewById(R.id.name);
        eamil = findViewById(R.id.email);
        password = findViewById(R.id.password);
        register =findViewById(R.id.register);
        model1 = new UserViewModel(getApplication());


        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                model1.insertUser(new User(eamil.getText().toString(),password.getText().toString(),null,name.getText().toString()));
                Toast.makeText(Login_Activity.this, "You have registered", Toast.LENGTH_SHORT).show();
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                startActivity(new Intent(getApplicationContext(), Admin_Login.class));
            }
        });
    }
}