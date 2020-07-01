package com.example.testmvp.view.activity.main;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.FrameLayout;

import com.example.testmvp.R;
import com.example.testmvp.view.activity.base.BaseActivity;
import com.example.testmvp.view.activity.base.BaseActivityToolBar;
import com.google.android.material.tabs.TabLayout;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.ViewById;

import lombok.Getter;

@EActivity(R.layout.activity_main)
public class MainActivity extends BaseActivityToolBar implements MainView {
    @ViewById(R.id.logout_button)
    protected Button logOut;

    @ViewById
    protected ViewPager pager;
    @ViewById
    protected TabLayout menu;
    @Bean
    protected MainImpl main;
    @Extra
    protected boolean isFromMore;

    @AfterViews
    protected void init() {
        main.setViewAct(this);
        main.setFragmentManager(getSupportFragmentManager(), getClass().getName());
        main.setFromMore(isFromMore);
        main.init();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        main.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected boolean display() {
        return false;
    }

    @Override
    public ViewPager pager() { return pager; }

    @Override
    public TabLayout menu() {
        return menu;
    }

    @Override
    public void setTitleToolbar(String title) {
        titleApp.setText(title);
    }

    @Override
    public void showHideBackPressed(boolean show) {

    }

    @Override
    public void backDefault() {

    }

    @Override
    public Button logOutBtn() {
        return logOut;
    }


    @Click(R.id.logout_button)
    protected void logOut() {main.logOutUsers();}
}