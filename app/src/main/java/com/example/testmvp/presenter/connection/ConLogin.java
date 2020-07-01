package com.example.testmvp.presenter.connection;

import android.content.Context;

import com.example.testmvp.model.request.RequestLogin;
import com.example.testmvp.model.response.ResponseLogin;

import connection.rxconnection.connection.HttpMethod;
import connection.rxconnection.connection.HttpRequest;

public class ConLogin extends HttpRequest<RequestLogin, ResponseLogin> {
    public ConLogin(RequestLogin requestLogin, Context context) {
        super(requestLogin, context, ResponseLogin.class, URL.LOGIN, HttpMethod.POST);
    }
}
