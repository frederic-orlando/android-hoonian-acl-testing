package com.example.testmvp.presenter.manager;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;

import com.example.testmvp.R;
import com.example.testmvp.model.content.ModelStatus;
import com.example.testmvp.model.content.login.ModelDataLogin;
import com.example.testmvp.model.request.RequestLogin;
import com.example.testmvp.model.response.BaseResponse;
import com.example.testmvp.model.response.ResponseGetUsers;
import com.example.testmvp.model.response.ResponseLogin;
import com.example.testmvp.presenter.connection.ConGetUsers;
import com.example.testmvp.presenter.connection.ConLogin;
import com.example.testmvp.presenter.session.SessionUser;
import com.example.testmvp.presenter.utils.UtilsCon;
import com.example.testmvp.presenter.utils.UtilsUser;
import com.google.gson.Gson;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.res.StringRes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import connection.rxconnection.connection.CallBackForLog;
import connection.rxconnection.connection.ConnectionListener;
import connection.rxconnection.connection.ConnectionManager;
import connection.rxconnection.connection.HttpRequest;
import connection.rxconnection.model.ModelLog;
import connection.rxconnection.session.SessionLogin;

@EBean
public class ServiceManager extends ConnectionManager implements ConnectionListener, CallBackForLog {
    @RootContext
    protected Context context;

    @AfterInject
    protected void init() { setContext(context); }

    private ConnectionListener listener;
    private boolean refreshToken;
    @Bean
    protected DialogManager dialogmanager;
    @Bean
    protected UtilsCon utilsCon;
    private boolean showError = true;
    private List<HttpRequest> listRequestHold = new ArrayList<>();
    @Bean
    protected UtilsUser utilsUser;

    @StringRes(R.string.waiting_download)
    protected String waitingDownload;
    @StringRes(R.string.waiting)
    protected String waiting;
    @StringRes(R.string.waiting_login)
    protected String waitingLogin;
    @StringRes(R.string.no_internet)
    protected String noInternet;
    @StringRes(R.string.internal_server_error)
    protected String internalServerError;

    private ModelDataLogin modelDataLogin;

    @AfterInject
    protected void inject() {
        modelDataLogin = new SessionUser(context).getData();
    }

    @Override
    public ConnectionManager setContext(Context context) {
        return super.setContext(context);
    }

    public ServiceManager setShowError(boolean showError) {
        this.showError = showError;
        return this;
    }

    @Override
    public ConnectionManager setConnectionListener(ConnectionListener connectionListener) {
        listener = connectionListener;
        return super.setConnectionListener(this);
    }

    private void initSubscribe(HttpRequest httpRequest, String message, boolean checkConnection) {
        initCon(httpRequest);
        checkConnectionSubscribe(httpRequest, message, checkConnection);
    }

    private void checkConnectionSubscribe(HttpRequest httpRequest, String message,
                                          boolean checkConnection) {
        try {
            if (checkConnection)
                if (utilsCon.isInternetAvailable(getContext())) {
                    subscribe(httpRequest, message);
                } else {
                    showDialog(noInternet);
                    listener.onError(null, httpRequest);
                }
            else
                subscribe(httpRequest, message);
        } catch (Exception e) {
            e.printStackTrace();
            listener.onError(e.getMessage(), httpRequest);
        }
    }

    private void checkConnectionSubscribe(HttpRequest httpRequest, boolean checkConnection) {
        try {
            if (checkConnection)
                if (utilsCon.isInternetAvailable(getContext())) {
                    subscribe(httpRequest);
                } else {
                    showDialog(noInternet);
                    listener.onError(null, httpRequest);
                }
            else
                subscribe(httpRequest);
        } catch (Exception e) {
            e.printStackTrace();
            listener.onError(e.getMessage(), httpRequest);
        }
    }

    private void initCon(HttpRequest httpRequest) {
        httpRequest.setCustomHeader(header());
        httpRequest.setLogInfoRequestResponse(true);
        httpRequest.setCallBackForLog(this);
    }

    private void initSubscribe(HttpRequest httpRequest, boolean checkConnection) {
        initCon(httpRequest);
        checkConnectionSubscribe(httpRequest, checkConnection);
    }

    @Override
    public void onSuccessWithData(Object o) {
//        if (!refreshToken) {
            if (o instanceof BaseResponse) {
                ModelStatus modelStatus = ((BaseResponse) o).getStatus();
                String httpCode = String.valueOf(modelStatus.getStatus());
                if (httpCode.startsWith("2")) {
                    if (o instanceof ResponseLogin) {
                        utilsUser.setData((ResponseLogin) o);
                    }
                    else if (o instanceof ResponseGetUsers) {
                        String json = new Gson().toJson(o);
                        showDialog(json);
                    }
                    else if (o instanceof String) {
                        showDialog((String) o);
                    }
                    listener.onSuccessWithData(o);
                    return;
                }
                if (modelStatus.getStatus() == 401) {
                    listener.unAuthorized(null, modelStatus.getMessage());
                    return;
                } else {
                    showDialog(modelStatus.getMessage());
                }
            } else {
                listener.onSuccessWithData(o);
            }

//        }
//        refreshToken = false;
    }


    @Override
    public void onSuccessNull() {
        listener.onSuccessNull();
    }

    @Override
    public void onMessageSuccess(String s) {
        listener.onMessageSuccess(s);
        showDialog(s);
    }

    public void showDialog(final String s) {
        if (!s.toLowerCase().contains("attempt to invoke virtual method"))
            new Handler().post(new Runnable() {
                @Override
                public void run() {
                    if (dialogmanager == null) {
                        dialogmanager = new DialogManager();
                    }
                    dialogmanager.errorDialog(s);
                }
            });
    }

    @UiThread
    @Override
    public void onError(final Object o, HttpRequest httpRequest) {
        if (showError) {
            String message = getErrorMessage(o);
            if (message != null)
                showDialog(message.contains("timed out") ? internalServerError : message);

        }

        listener.onError(o, httpRequest);
    }

    public String getErrorMessage(Object o) {
        String message = (String) o;
        return message;
    }

    @Override
    public void unAuthorized(HttpRequest httpRequest, String errorMessage) {
        listener.unAuthorized(httpRequest, errorMessage);
    }

    private Map<String, String> header() {
        Map<String, String> header = new HashMap<String, String>();
        String token = new SessionLogin(getContext()).getToken();
        if (token != null)
            header.put("Authorization", new SessionLogin(getContext()).getToken());
        header.put("Accept", "application/json");
        return header;
    }


    @Override
    public void log(ModelLog modelLog) {

    }

    public void login(RequestLogin requestLogin) {
        initSubscribe(new ConLogin(requestLogin, getContext()),
                waitingLogin, true);
    }

    public void getData() {
        initSubscribe(new ConGetUsers(getContext()),
                waiting, true);
    }
}
