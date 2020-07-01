package com.example.testmvp.presenter.connection;

import com.example.testmvp.BuildConfig;

public class URL {
    private static final String BASE_URL = BuildConfig.API_BASE_URL;
    public static final String LOGIN = BASE_URL + "/login";
    public static final String USERS = BASE_URL + "/users";
}
