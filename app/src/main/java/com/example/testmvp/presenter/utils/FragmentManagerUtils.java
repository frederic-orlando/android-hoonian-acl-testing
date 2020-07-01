package com.example.testmvp.presenter.utils;

import android.app.Activity;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.testmvp.view.activity.base.BaseActivity;
import com.example.testmvp.view.activity.main.MainActivity;
import com.example.testmvp.view.activity.main.MainActivity_;
import com.example.testmvp.view.fragment.base.BaseFragment;

import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;

import lombok.Getter;
import lombok.Setter;

@EBean
public class FragmentManagerUtils<T> {
    @RootContext
    protected Activity activity;
    @Setter
    @Getter
    private int layoutFragment;

    @Setter
    @Getter
    protected FragmentManager fragmentManager;

    @Setter
    private String className;

    @Setter
    private T callback;


    private FragmentTransaction fragmentTransaction() {
        // Fragment Manager Utils -> fragment manager NULL
//        System.out.println(()activity.);
        return fragmentManager.beginTransaction();
    }

    public void showFragment(int layout, Fragment fragment, boolean stateloss, String tag,
                             boolean backstack) {
        FragmentTransaction fragmentTransaction = fragmentTransaction().
                replace(layout, fragment, tag);
        if (backstack)
            fragmentTransaction.addToBackStack(className);
        if (stateloss) {
            fragmentTransaction.commitAllowingStateLoss();
        } else {
            fragmentTransaction.commit();
        }
    }

    public void showFragment(Fragment fragment, boolean backstack) {
        showFragment(getLayoutFragment(), fragment, true, null, backstack);
    }

    public void showFragmentUsingCallBack(BaseFragment fragment) {
        showFragment(getLayoutFragment(), setCallBackFragment(fragment, callback), true,
                fragment.getClass().getName(), true);
    }

    public void showFragmentUsingCallBackNoBackStack(BaseFragment fragment) {
        showFragment(getLayoutFragment(), setCallBackFragment(fragment, callback),
                true, null, false);
    }

    public <T> BaseFragment setCallBackFragment(BaseFragment baseFragment, T t) {
        baseFragment.setCallback(t);
        return baseFragment;
    }

    public void backpressed() {
        BaseFragment baseFragment = (BaseFragment) fragmentManager.findFragmentById
                (getLayoutFragment());
        back(baseFragment);
    }

    private BaseActivity getBaseActivity() {
        return (BaseActivity) activity;
    }

    public void back(BaseFragment baseFragment) {
        if (baseFragment != null) {
            baseFragment.backpressed();
        } else {
            baseFragmentNull();
        }
    }

    public void baseFragmentNull() {
        activity.finish();
    }


    public Fragment findFragmentByLayout() {
        return fragmentManager.findFragmentById(layoutFragment);
    }

    public void remove(Fragment fragment) {
        getFragmentManager().beginTransaction().remove(fragment).commitAllowingStateLoss();
    }

    public Fragment getExistFragment() {
        return getFragmentManager().findFragmentById(layoutFragment);
    }
}
