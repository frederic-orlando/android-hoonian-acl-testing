package com.example.testmvp.view.viewholder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.testmvp.R;
import com.example.testmvp.model.content.ModelMenu;
import com.example.testmvp.presenter.callback.RecyclerListener;

public class VHChildMore extends BaseViewHolder<ModelMenu> {
    private ImageView icon;
    private TextView name;

    public VHChildMore(View itemView, RecyclerListener recyclerListener) {
        super(itemView, recyclerListener);
        icon = findView(R.id.icon);
        name = findView(R.id.name);
    }

    @Override
    public void setData(ModelMenu data) {
        super.setData(data);
        icon.setImageResource(data.getIconMore());
        name.setText(data.getTitle().replace(" ", "\n"));
    }
}
