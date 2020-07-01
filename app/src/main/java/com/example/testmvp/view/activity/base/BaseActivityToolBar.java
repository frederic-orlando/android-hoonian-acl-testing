package com.example.testmvp.view.activity.base;

import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;

import com.example.testmvp.R;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.res.StringRes;

@EActivity
public class BaseActivityToolBar extends BaseActivity {

    @ViewById
    protected Toolbar toolbar;
    @ViewById(R.id.title_app)
    protected TextView titleApp;
    @ViewById(R.id.icon)
    protected ImageView icon;

    @StringRes(R.string.app_name)
    protected String appName;

    @AfterViews
    protected void afterViews() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(display());
//        getSupportActionBar().setHomeAsUpIndicator(getResources().getDrawable(R.drawable.ic_arrow));
        titleApp.setText(titleApp());
    }

    protected String titleApp() {
        return appName;
    }


    protected boolean display() {
        return true;
    }

    @AfterViews
    protected void init() {
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                onBackPressed();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

}
