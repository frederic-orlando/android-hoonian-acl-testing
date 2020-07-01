package com.example.testmvp.view.activity.login;

import com.example.testmvp.model.request.RequestLogin;
import com.example.testmvp.model.response.ResponseLogin;
import com.example.testmvp.presenter.base.impl.BaseImpl;

import org.androidannotations.annotations.EBean;

import connection.rxconnection.connection.HttpRequest;

@EBean
public class LoginImpl extends BaseImpl<LoginView> implements LoginPres {

    @Override
    public void onValidationSucceeded() {
        super.onValidationSucceeded();
        login();
    }

    @Override
    public void login() {
        RequestLogin requestLogin = new RequestLogin();
        requestLogin.setPassword(utilsLayout.getBodyText(viewAct.password()));
        if (utilsLayout.checkLength(viewAct.email())) {
            String username = utilsLayout.getBodyText(viewAct.email());
            if (username.startsWith("0")) {
                if (username.length() > 1) {
                    username = "+62" + username.substring(1);
                }
                requestLogin.setPhoneNo(username);
            } else if (username.startsWith("+")) {
                requestLogin.setPhoneNo(username);
            } else if (utilsLayout.checkEmail(viewAct.email(), "email not valid")) {
                requestLogin.setEmail(username);
            }
        }
        serviceManager.login(requestLogin);
    }

    @Override
    public void unAuthorized(HttpRequest httpRequest, String message) {
        dialogManager.errorDialog(message);
    }

    @Override
    public void onSuccessWithData(Object o) {
        if (o instanceof ResponseLogin) {
            checkIsLogin();
        }
    }
}
