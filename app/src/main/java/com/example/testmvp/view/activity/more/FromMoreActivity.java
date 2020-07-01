package com.example.testmvp.view.activity.more;

import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.testmvp.R;
import com.example.testmvp.view.activity.base.BaseActivity;
import com.example.testmvp.view.activity.base.BaseActivityToolBar;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;

@EActivity(R.layout.activity_from_more)
public class FromMoreActivity extends BaseActivityToolBar implements FromMoreView {
    @Bean
    protected FromMoreImpl impl;
    @Extra
    protected int fragmentToAttach;
    @Extra
    protected String title;

    @AfterViews
    protected void init() {
        impl.setViewAct(this);
        impl.setTitle(title);
        impl.setLayoutFragment(R.id.frame_next);
        impl.setFragmentId(fragmentToAttach);
        impl.setFragmentManager(getSupportFragmentManager(), getClass().getName());
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        impl.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        impl.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    public void onBackPressed() {
        impl.getFragmentManagerUtils().backpressed();
    }

    @Override
    protected String titleApp() {
        return title;
    }


    @Override
    protected void onResume() {
        super.onResume();
        impl.init();
    }
}
