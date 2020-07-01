package com.example.testmvp.view.activity.main;

import android.widget.Button;
import android.widget.FrameLayout;

import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

public interface MainView {
    ViewPager pager();
    TabLayout menu();
    void setTitleToolbar(String title);
    void showHideBackPressed(boolean show);
    void backDefault();

//    FrameLayout fragmentHolder();
    Button logOutBtn();
}
