package com.example.testmvp.view.adapter.pager;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import java.util.ArrayList;

/**
 * Created by AndreHF on 8/9/2017.
 */

public class AdapterPager extends FragmentStatePagerAdapter {
    private final ArrayList<Fragment> fragments;
    public AdapterPager(FragmentManager fm, ArrayList<Fragment> fragments) {
        super(fm);
        this.fragments = fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }
}
