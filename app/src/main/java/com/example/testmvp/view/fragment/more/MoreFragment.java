package com.example.testmvp.view.fragment.more;

import androidx.recyclerview.widget.RecyclerView;

import com.example.testmvp.R;
import com.example.testmvp.view.fragment.base.BaseFragment;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

@EFragment(R.layout.fragment_more)
public class MoreFragment extends BaseFragment implements MoreView {
    @Bean
    protected MoreImpl impl;

    @ViewById
    protected RecyclerView more;

    @AfterViews
    protected void init() {
        impl.setViewAct(this);
        impl.setFragmentManager(getFragmentManager(), getClass().getName());
        impl.init();
    }

    @Override
    public RecyclerView more() { return more; }
}
