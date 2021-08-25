package com.android.app.quizapp.admin;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.android.app.quizapp.MainActivity;
import com.android.app.quizapp.R;
import com.android.app.quizapp.quizdb.User;
import com.android.app.quizapp.viewModel.UserViewModel;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class Admin_Login extends AppCompatActivity {

    UserViewModel user_viewModel;
    LiveData<List<User>> allUsers;

    CircleImageView img;
    EditText email;
    EditText password;
    Button signIn;
//    Uri imgUri;
    private static final String adminEmail ="abdjabbar110@gmail.com";
    public static final String adminPassword ="abdul123";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin__login);

        img = findViewById(R.id.admin_img);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        signIn = findViewById(R.id.signIn);


        user_viewModel = ViewModelProviders.of(this).get(UserViewModel.class);

    }

    private void setUpImage() {
        Intent setupImage = new Intent();
        setupImage.setAction(Intent.ACTION_GET_CONTENT);
        setupImage.setType("image/*");
        startActivityForResult(setupImage, 111);
    }
    public void onSignInClick(View view){
        String emailText = email.getText().toString();
        String passwordText = password.getText().toString();

        if(emailText.trim().equals("")){
            Toast.makeText(this, "Empty Email Not Accepted", Toast.LENGTH_SHORT).show();
        }
        else if(passwordText.trim().equals("")){
            Toast.makeText(this, "Empty Password Not Accepted", Toast.LENGTH_SHORT).show();
        }else{

            if(emailText.equals(adminEmail) && passwordText.equals(adminPassword)){
                startActivity(new Intent(Admin_Login.this, MainActivity.class));
                Toast.makeText(this, "Welcome !", Toast.LENGTH_SHORT).show();
            }else{
                if(emailText.trim().equals(adminEmail) && passwordText.trim().equals(adminPassword)){
                    startActivity(new Intent(getApplicationContext(),MainActivity.class));
                }
                else
                    Toast.makeText(this, "Wrong Email and Password", Toast.LENGTH_SHORT).show();
            }

        }
    }
}