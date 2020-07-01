package com.example.testmvp.view.fragment.favorite;

import com.example.testmvp.R;
import com.example.testmvp.view.fragment.base.BaseFragment;
import com.example.testmvp.view.fragment.settings.SettingsImpl;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EFragment;

@EFragment(R.layout.fragment_favorite)
public class FavoriteFragment extends BaseFragment implements FavoriteView {
    @Bean
    protected FavoriteImpl impl;

    @AfterViews
    protected void init() {
        impl.setViewAct(this);
        impl.setFragmentManager(getFragmentManager(), getClass().getName());
        impl.init();
    }
}
