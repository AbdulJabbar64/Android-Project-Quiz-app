package com.android.app.quizapp.activities.intro_screens;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.android.app.quizapp.R;

public class FirstActivity extends AppCompatActivity {

    ViewPager viewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);

        viewPager = findViewById(R.id.viewPager);

        ViewPagerAdapterClass adapter = new ViewPagerAdapterClass(getSupportFragmentManager(),3);
        viewPager.setAdapter(adapter);

    }
}