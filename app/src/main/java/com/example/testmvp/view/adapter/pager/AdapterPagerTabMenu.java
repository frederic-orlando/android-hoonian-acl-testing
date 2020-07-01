package com.example.testmvp.view.adapter.pager;

import android.content.Context;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.testmvp.model.content.ModelMenu;
import com.example.testmvp.presenter.utils.UtilsMenuFragment;
import com.example.testmvp.presenter.utils.UtilsMenuFragment_;
import com.example.testmvp.view.activity.main.MainImpl;

import java.util.ArrayList;


/**
 * Created by AndreHF on 10/28/2016.
 */

public class AdapterPagerTabMenu extends FragmentStatePagerAdapter {
    private final ArrayList<ModelMenu> listTab;
    private UtilsMenuFragment getNext;
    private MainImpl mainImpl;

    public AdapterPagerTabMenu(FragmentManager fm, ArrayList<ModelMenu> modelTabs, Context context,
                               MainImpl mainImpl) {
        super(fm);
        this.listTab = modelTabs;
        getNext = UtilsMenuFragment_.getInstance_(context);
        this.mainImpl = mainImpl;
    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = mainImpl.getFragmentManagerUtils()
                .setCallBackFragment(getNext.getFragment((int) listTab.get(position).getPositionFragment()), mainImpl);

        return fragment;
    }

    @Override
    public int getCount() {
        return listTab.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return listTab.get(position).getTitle();
    }

    public ModelMenu getMenu(int position) {
        return listTab.get(position);
    }

}
