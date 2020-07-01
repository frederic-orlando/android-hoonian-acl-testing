package com.example.testmvp.view.viewholder;

import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

import com.example.testmvp.presenter.callback.RecyclerListener;

import lombok.Getter;

/**
 * Created by AndreHF on 6/19/2017.
 */

public class BaseViewHolder<T> extends RecyclerView.ViewHolder {
    @Getter
    private final RecyclerListener recyclerListener;

    public BaseViewHolder(View itemView, RecyclerListener recyclerListener) {
        super(itemView);
        this.recyclerListener = recyclerListener;
        itemView.setOnClickListener((View view) -> this.recyclerListener.onItemClick(itemView.getTag()));
    }

    public void setData(T data) {
        itemView.setTag(data);
    }


    protected <K extends View> K findView(int id) {
        return (K) itemView.findViewById(id);
    }

    protected T getData(){
        return (T) itemView.getTag();
    }


}
