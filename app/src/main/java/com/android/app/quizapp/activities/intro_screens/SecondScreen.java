package com.android.app.quizapp.activities.intro_screens;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.app.quizapp.R;
public class SecondScreen extends Fragment {
    TextView secondNext, secondBack;
    ViewPager viewPager;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_second_screen, container, false);
        secondNext = view.findViewById(R.id.second_next);
        secondBack = view.findViewById(R.id.second_back);
        viewPager = getActivity().findViewById(R.id.viewPager);

        secondNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewPager.setCurrentItem(2);
            }
        });

        secondBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewPager.setCurrentItem(1);
            }
        });
        return view;
    }
}