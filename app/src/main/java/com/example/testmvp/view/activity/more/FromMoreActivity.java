package com.example.testmvp.view.activity.more;

import com.example.testmvp.R;
import com.example.testmvp.view.activity.base.BaseActivity;

import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;

@EActivity(R.layout.activity_from_more)
public class FromMoreActivity extends BaseActivity implements FromMoreView {
    @Extra
    protected String title;
}
