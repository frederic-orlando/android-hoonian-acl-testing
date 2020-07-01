package com.example.testmvp.view.activity.main;

import android.content.Intent;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import com.example.testmvp.model.content.ModelMenu;
import com.example.testmvp.presenter.base.impl.BaseImpl;
import com.example.testmvp.presenter.session.SessionMenu;
import com.example.testmvp.view.adapter.pager.AdapterPagerTabMenu;

import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.EBean;

import java.util.ArrayList;

import lombok.Setter;

@EBean
public class MainImpl extends BaseImpl<MainView> implements MainPres, ViewPager.OnPageChangeListener {
    @Setter
    private boolean isFromMore;

    @Override
    public void init() {
        getMenu();
    }

    @Background(delay = 100)
    protected void getMenu() {
        SessionMenu sessionMenu = new SessionMenu(activity);
        if (sessionMenu.getDataNavbar().size() > 0) {
            activity.runOnUiThread(() -> setData(sessionMenu.getDataNavbar()));
        } else {
            getMenu();
        }
    }

    protected void setData(ArrayList<ModelMenu> dataNavbar) {
        viewAct.pager().setAdapter(new AdapterPagerTabMenu(getFragmentManagerUtils().getFragmentManager(),
                dataNavbar, activity, this));
        viewAct.menu().setupWithViewPager(viewAct.pager());
        try {
            viewAct.pager().setOffscreenPageLimit(dataNavbar.size());
        } catch (IllegalStateException ignored) {

        }
        setIcon(dataNavbar);

        viewAct.pager().addOnPageChangeListener(this);
        if (isFromMore) {
            viewAct.pager().setCurrentItem(4, true);
        }
    }

    @Override
    public void showHideBack(boolean show) {
        viewAct.showHideBackPressed(show);
    }

    private void setIcon(ArrayList<ModelMenu> dataNavbar) {
        for (int pos = 0; pos < viewAct.menu().getTabCount(); pos++) {
            viewAct.menu().getTabAt(pos).setIcon(dataNavbar.get(pos).
                    getIconNav());
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        Fragment fragment = ((AdapterPagerTabMenu) viewAct.pager().getAdapter())
                .getItem(viewAct.pager().getCurrentItem());
        if (fragment != null) {
            fragment.onActivityResult(requestCode, resultCode, data);
        }
    }

    public void logOutUsers() {
        super.logoutUser();
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
