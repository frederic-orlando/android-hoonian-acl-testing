package com.example.testmvp.presenter.session;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.testmvp.model.content.login.ModelDataLogin;
import com.google.gson.Gson;

public class SessionUser {
    private final String sessionName = "user";
    private final String key = "data";
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    public SessionUser(Context context) {
        sharedPreferences = context.getSharedPreferences(sessionName, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    public ModelDataLogin getData() {
        String json = sharedPreferences.getString(key, null);
        ModelDataLogin data = new Gson().fromJson(json, ModelDataLogin.class);
        return data;
    }

    public void setData(ModelDataLogin data) {
        editor.putString(key, new Gson().toJson(data));
        editor.commit();
    }

    public void clearData() {
        editor.clear();
        editor.commit();
    }
}
