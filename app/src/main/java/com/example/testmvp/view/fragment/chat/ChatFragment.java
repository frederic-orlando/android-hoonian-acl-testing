package com.example.testmvp.view.fragment.chat;

import com.example.testmvp.R;
import com.example.testmvp.view.fragment.base.BaseFragment;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EFragment;

@EFragment(R.layout.fragment_chat)
public class ChatFragment extends BaseFragment implements ChatView {
    @Bean
    protected ChatImpl impl;

    @AfterViews
    protected void init() {
        impl.setViewAct(this);
        impl.setFragmentManager(getFragmentManager(), getClass().getName());
        impl.init();
    }
}
