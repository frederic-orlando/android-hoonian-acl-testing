package com.example.testmvp.view.viewholder;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.testmvp.R;
import com.example.testmvp.model.content.ModelMore;
import com.example.testmvp.presenter.callback.RecyclerListener;
import com.example.testmvp.view.adapter.recycler.AdapterChildMore;

public class VHMore extends BaseViewHolder<ModelMore> implements RecyclerListener {
    private TextView title;
    private RecyclerView child;

    public VHMore(View itemView, RecyclerListener recyclerListener) {
        super(itemView, recyclerListener);
        title = findView(R.id.title);
        child = findView(R.id.child);
        child.setLayoutManager(new GridLayoutManager(itemView.getContext(), 4));
    }

    @Override
    public void setData(ModelMore data) {
        super.setData(data);
        title.setText(data.getTitle());
        child.setAdapter(new AdapterChildMore(data.getChild(), this));
    }

    @Override
    public void onItemClick(Object o) {
        getRecyclerListener().onItemClick(o);
    }
}
