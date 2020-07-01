package com.example.testmvp.view.fragment.base;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import lombok.Setter;

public class BaseFragment<T> extends Fragment {
    @Setter
    protected T callback;
    private FragmentTransaction fragmentTransaction;

    public void backpressed() {
        getActivity().finish();
    }

}
