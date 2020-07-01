package com.example.testmvp.presenter.utils;

import android.app.Activity;

import com.example.testmvp.view.fragment.alarm.AlarmFragment;
import com.example.testmvp.view.fragment.alarm.AlarmFragment_;
import com.example.testmvp.view.fragment.base.BaseFragment;
import com.example.testmvp.view.fragment.camera.CameraFragment;
import com.example.testmvp.view.fragment.camera.CameraFragment_;
import com.example.testmvp.view.fragment.chat.ChatFragment;
import com.example.testmvp.view.fragment.chat.ChatFragment_;
import com.example.testmvp.view.fragment.favorite.FavoriteFragment;
import com.example.testmvp.view.fragment.favorite.FavoriteFragment_;
import com.example.testmvp.view.fragment.feed.FeedFragment;
import com.example.testmvp.view.fragment.feed.FeedFragment_;
import com.example.testmvp.view.fragment.more.MoreFragment_;
import com.example.testmvp.view.fragment.settings.SettingsFragment;
import com.example.testmvp.view.fragment.settings.SettingsFragment_;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;

import java.util.ArrayList;

@EBean
public class UtilsMenuFragment {
    @RootContext
    protected Activity activity;
    private ArrayList<BaseFragment> listFragment = new ArrayList();

    @AfterInject
    protected void generateList() {
        listFragment.add(MoreFragment_.builder().build());
        listFragment.add(SettingsFragment_.builder().build());
        listFragment.add(FavoriteFragment_.builder().build());
        listFragment.add(FeedFragment_.builder().build());
        listFragment.add(CameraFragment_.builder().build());
        listFragment.add(AlarmFragment_.builder().build());
        listFragment.add(ChatFragment_.builder().build());
    }

    public BaseFragment getFragment(int position) {
        BaseFragment bf;
        int index = position;
        if (index >= listFragment.size()) {
            bf = new BaseFragment();
        } else {
            bf = listFragment.get(index);
        }
        return bf;
    }
}
