package com.example.testmvp.view.fragment.feed;

import android.widget.Button;

import com.example.testmvp.R;
import com.example.testmvp.view.fragment.base.BaseFragment;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

@EFragment(R.layout.fragment_feed)
public class FeedFragment extends BaseFragment implements FeedView {
    @Bean
    protected FeedImpl impl;

    @ViewById
    protected Button button;

    @AfterViews
    protected void init() {
        impl.setViewAct(this);
        impl.setFragmentManager(getFragmentManager(), getClass().getName());
        impl.init();
    }

    @Override
    public Button button() { return button; }

    @Click(R.id.button)
    protected void getData() {
        impl.getData();
    }
}
