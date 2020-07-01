package com.example.testmvp.presenter.session;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.testmvp.model.content.ModelMenu;
import com.example.testmvp.model.content.ModelMore;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;

public class SessionMenu {
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private final String sessionName = "menu";
    private final String keyNavbar = "navbar";
    private final String keyMore = "more";

    public SessionMenu (Context context) {
        sharedPreferences = context.getSharedPreferences(sessionName, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    public void setDataNavBar(ArrayList<ModelMenu> data) {
        editor.putString(keyNavbar, new Gson().toJson(data));
        editor.commit();
    }

    public void setDataMore(ArrayList<ModelMore> data) {
        editor.putString(keyMore, new Gson().toJson(data));
        editor.commit();
    }

    public ArrayList<ModelMenu> getDataNavbar() {
        String json = sharedPreferences.getString(keyNavbar, null);
        ModelMenu[] data = new Gson().fromJson(json, ModelMenu[].class);
        return data != null ? new ArrayList(Arrays.asList(data)) : new ArrayList<>();
    }

    public ArrayList<ModelMore> getDataMore() {
        String json = sharedPreferences.getString(keyMore, null);
        System.out.println(json);
        ModelMore[] data = new Gson().fromJson(json, ModelMore[].class);
        return data != null ? new ArrayList(Arrays.asList(data)) : new ArrayList<>();
    }

    public void clearData() {
        editor.clear();
        editor.commit();
    }
}
