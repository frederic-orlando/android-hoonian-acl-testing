package com.example.testmvp.view.adapter.recycler;

import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.example.testmvp.R;
import com.example.testmvp.model.content.ModelMore;
import com.example.testmvp.presenter.callback.RecyclerListener;
import com.example.testmvp.view.viewholder.VHMore;

import java.util.ArrayList;

public class AdapterMore extends BaseAdapter<ModelMore>{
    public AdapterMore(ArrayList<ModelMore> listItem, RecyclerListener recyclerListener) {
        super(listItem, recyclerListener);
    }

    @Override
    protected RecyclerView.ViewHolder createHolder(ViewGroup parent, int viewType) {
        return new VHMore(createView(parent, R.layout.adapter_more), recyclerListener);
    }
}
