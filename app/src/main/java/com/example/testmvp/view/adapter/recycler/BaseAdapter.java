package com.example.testmvp.view.adapter.recycler;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.example.testmvp.R;
import com.example.testmvp.model.content.DataLoadMore;
import com.example.testmvp.presenter.callback.RecyclerListener;
import com.example.testmvp.view.viewholder.BaseViewHolder;
import com.example.testmvp.view.viewholder.VHEmpty;
import com.example.testmvp.view.viewholder.VHLoad;

import java.util.ArrayList;

public class BaseAdapter<K> extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    protected final ArrayList<K> listItem;

    protected final int EMPTY = 0;
    protected final int NON_EMPTY = 1;
    protected final int LOAD_MORE = 2;
    protected final RecyclerListener recyclerListener;


    public BaseAdapter(ArrayList<K> listItem, RecyclerListener recyclerListener) {
        this.listItem = listItem;
        this.recyclerListener = recyclerListener;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case EMPTY:
                return createEmptyHolder(parent);
            case NON_EMPTY:
                return createHolder(parent, viewType);
            case LOAD_MORE:
                return loadMore(parent);
        }
        return null;

    }

    private VHLoad loadMore(ViewGroup parent) {
        return new VHLoad(createView(parent, R.layout.adapter_load_more), recyclerListener);
    }


    protected View createView(ViewGroup parent, int layout) {
        LayoutInflater layoutInflater = (LayoutInflater) parent.getContext().getSystemService
                (Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(layout, parent, false);
        return view;
    }

    protected RecyclerView.ViewHolder createHolder(ViewGroup parent, int viewType) {
        return null;
    }

    protected RecyclerView.ViewHolder createEmptyHolder(ViewGroup parent) {
        return new VHEmpty(createView(parent, R.layout.adapter_empty));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof BaseViewHolder) {
            try {
                ((BaseViewHolder) holder).setData(listItem.get(position));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    @Override
    public int getItemCount() {
        if (listItem != null && listItem.size() > 0) {
            return listItem.size();
        } else {
            return 1;
        }
    }


    public ArrayList<K> getListItem() {
        return listItem;
    }

    @Override
    public int getItemViewType(int position) {
        if (listItem != null && listItem.size() != 0) {
            if (listItem.get(position) != null &&
                    !(listItem.get(position) instanceof DataLoadMore))
                return NON_EMPTY;
            else
                return LOAD_MORE;
        } else {
            return EMPTY;
        }
    }

}
