package com.example.testmvp.view.fragment.more;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.testmvp.model.content.ModelMenu;
import com.example.testmvp.model.content.ModelMore;
import com.example.testmvp.presenter.base.impl.BaseImpl;
import com.example.testmvp.presenter.callback.RecyclerListener;
import com.example.testmvp.presenter.session.SessionMenu;
import com.example.testmvp.view.adapter.recycler.AdapterChildMore;
import com.example.testmvp.view.adapter.recycler.AdapterMore;

import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.UiThread;

import java.util.ArrayList;

@EBean
public class MoreImpl extends BaseImpl<MoreView> implements MorePres, RecyclerListener {

    @Override
    public void init() {
        setData(new SessionMenu(activity).getDataMore());
    }

    @UiThread
    protected void setData(ArrayList<ModelMore> dataMore) {
        if (viewAct.more() != null) {
            AdapterMore adapter = new AdapterMore(dataMore, this);
            viewAct.more().setAdapter(adapter);
            viewAct.more().setLayoutManager(new LinearLayoutManager(activity));
            System.out.println("ATTACH ADAPTER");
        }
    }

    @Override
    public void onItemClick(Object o) {
        if (o instanceof ModelMenu) {
            ModelMenu modelMenu = (ModelMenu) o;
            intentManager.moveToNext(modelMenu.getTitle(), ((int) modelMenu.getPositionFragment()));
        }
    }
}
