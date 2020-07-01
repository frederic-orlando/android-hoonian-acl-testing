package com.example.testmvp.presenter.connection;

import android.content.Context;

import com.example.testmvp.model.response.ResponseGetUsers;

import connection.rxconnection.connection.HttpMethod;
import connection.rxconnection.connection.HttpRequest;

public class ConGetUsers extends HttpRequest<Object, ResponseGetUsers> {
    public ConGetUsers(Context context) {
        super(context, ResponseGetUsers.class, URL.USERS, HttpMethod.GET);
    }
}
