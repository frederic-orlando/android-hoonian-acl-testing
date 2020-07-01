package com.example.testmvp.view.fragment.camera;

import com.example.testmvp.R;
import com.example.testmvp.view.fragment.base.BaseFragment;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EFragment;

@EFragment(R.layout.fragment_camera)
public class CameraFragment extends BaseFragment implements CameraView {
    @Bean
    protected CameraImpl impl;

    @AfterViews
    protected void init() {
        impl.setViewAct(this);
        impl.setFragmentManager(getFragmentManager(), getClass().getName());
        impl.init();
    }
}
