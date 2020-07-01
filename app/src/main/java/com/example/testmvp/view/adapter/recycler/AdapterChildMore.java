package com.example.testmvp.view.adapter.recycler;

import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.example.testmvp.R;
import com.example.testmvp.model.content.ModelMenu;
import com.example.testmvp.presenter.callback.RecyclerListener;
import com.example.testmvp.view.viewholder.VHChildMore;

import java.util.ArrayList;

public class AdapterChildMore extends BaseAdapter<ModelMenu> {
    public AdapterChildMore(ArrayList<ModelMenu> listItem, RecyclerListener recyclerListener) {
        super(listItem, recyclerListener);
    }

    @Override
    protected RecyclerView.ViewHolder createHolder(ViewGroup parent, int viewType) {
        return new VHChildMore(createView(parent, R.layout.adapter_child_more), recyclerListener);
    }
}
