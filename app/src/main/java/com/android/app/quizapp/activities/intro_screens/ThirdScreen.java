package com.android.app.quizapp.activities.intro_screens;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.app.quizapp.MainActivity;
import com.android.app.quizapp.R;
import com.android.app.quizapp.admin.Admin_Login;
import com.android.app.quizapp.user.UserQuiz;

public class ThirdScreen extends Fragment {
    TextView getStarted, thirdBack;
    ViewPager viewPager;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_third_screen, container, false);
        getStarted = view.findViewById(R.id.third_next);
        thirdBack = view.findViewById(R.id.third_back);
        viewPager = getActivity().findViewById(R.id.viewPager);

        getStarted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                startActivity(new Intent(getActivity(), UserQuiz.class));
                startActivity(new Intent(getActivity(), MainActivity.class));
            }
        });

        thirdBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewPager.setCurrentItem(1);
            }
        });
        return view;
    }
}