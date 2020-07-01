package com.example.testmvp.presenter.session;

import android.content.Context;
import android.content.SharedPreferences;

public class SessionVersion {
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private final String sessionName = "version";
    private final String key = "version";

    public SessionVersion(Context context) {
        sharedPreferences = context.getSharedPreferences(sessionName, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    public void setVersion(int data) {
        editor.putInt(key, data);
        editor.commit();
    }

    public int getVersion() {
        return sharedPreferences.getInt(key, 0);
    }

    public void clearData() {
        editor.clear();
        editor.commit();
    }
}
