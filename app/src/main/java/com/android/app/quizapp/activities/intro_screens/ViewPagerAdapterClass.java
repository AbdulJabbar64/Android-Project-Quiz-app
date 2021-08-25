package com.android.app.quizapp.activities.intro_screens;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class ViewPagerAdapterClass extends FragmentPagerAdapter {
    int screentCount;
    public ViewPagerAdapterClass(@NonNull FragmentManager fm, int screentCount) {
        super(fm);
        this.screentCount =screentCount;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                FirstScreen first = new FirstScreen();
                return first;

            case 1:
                SecondScreen second = new SecondScreen();
                return second;

            case 2:
                ThirdScreen third = new ThirdScreen();
                return third;

            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return screentCount;
    }
}
