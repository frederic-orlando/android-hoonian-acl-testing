package com.example.testmvp.presenter.manager;

import android.app.Activity;
import android.content.Intent;

import com.example.testmvp.view.activity.login.LoginActivity_;
import com.example.testmvp.view.activity.main.MainActivity_;
import com.example.testmvp.view.activity.more.FromMoreActivity_;

import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;

@EBean
public class IntentManager {
    @RootContext
    protected Activity activity;

    public void moveToLogin() {
        LoginActivity_.intent(activity).flags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK)
                .start();
        activity.finish();
    }

    public void moveToMain() {
        MainActivity_.intent(activity).flags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK)
                .start();
        activity.finish();
    }

//    public void moveToMain(boolean isFromMore) {
//        MainActivity_.intent(activity).isFromMore(isFromMore).flags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK).start();
//        activity.finish();
//    }

    public void moveToNext(String title, int idFragment) {
        FromMoreActivity_.intent(activity).title(title).fragmentToAttach(idFragment)
                .flags(Intent.FLAG_ACTIVITY_CLEAR_TASK).start();
    }
}
