package com.example.testmvp.view.fragment.settings;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.testmvp.R;
import com.example.testmvp.view.fragment.base.BaseFragment;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

@EFragment(R.layout.fragment_settings)
public class SettingsFragment extends BaseFragment implements SettingsView {
    @Bean
    protected SettingsImpl impl;

    @AfterViews
    protected void init() {
        impl.setViewAct(this);
        impl.setFragmentManager(getFragmentManager(), getClass().getName());
        impl.init();
    }
}
