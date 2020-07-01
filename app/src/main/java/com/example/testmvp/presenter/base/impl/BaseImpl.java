package com.example.testmvp.presenter.base.impl;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.view.MenuItem;
import android.view.View;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentManager;

import com.example.testmvp.model.content.ModelTab;
import com.example.testmvp.model.content.login.ModelDataLogin;
import com.example.testmvp.presenter.callback.CallBackDialog;
import com.example.testmvp.presenter.manager.DialogManager;
import com.example.testmvp.presenter.manager.IntentManager;
import com.example.testmvp.presenter.manager.ServiceManager;
import com.example.testmvp.presenter.session.SessionMenu;
import com.example.testmvp.presenter.session.SessionUser;
import com.example.testmvp.presenter.utils.FragmentManagerUtils;
import com.example.testmvp.presenter.utils.UtilsLayout;
import com.example.testmvp.view.activity.base.BaseActivity;
import com.example.testmvp.view.activity.login.LoginActivity;
import com.example.testmvp.view.activity.login.LoginActivity_;
import com.example.testmvp.view.fragment.base.BaseFragment;
import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;
import org.androidannotations.annotations.UiThread;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import connection.rxconnection.connection.ConnectionListener;
import connection.rxconnection.connection.HttpRequest;
import connection.rxconnection.session.SessionLogin;
import lombok.Getter;
import lombok.Setter;

@EBean
public class BaseImpl<T> implements ConnectionListener, CallBackDialog, Validator.ValidationListener{
    @RootContext
    protected Activity activity;
    @Bean
    protected IntentManager intentManager;
    @Setter
    protected T viewAct;
    @Getter
    protected Validator validator;
    @Bean
    protected UtilsLayout utilsLayout;
    @Bean
    protected ServiceManager serviceManager;
    @Getter
    @Bean
    protected FragmentManagerUtils fragmentManagerUtils;
    @Bean
    protected DialogManager dialogManager;
//    @Bean
//    protected PermissionMarshmallow permissionMarshmallow;

    @AfterInject
    protected void inject() {
        serviceManager.setConnectionListener(this);
        try {
            validator = new Validator(activity);
            validator.setValidationListener(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public <T> void initValidator(T target) {
        validator = new Validator(target);
        validator.setValidationListener(this);
    }

    public void showHidePass(TextView textView) {
        utilsLayout.showHidePass(textView);
    }

    public void setFragmentManager(FragmentManager fragmentManager, String className) {
        // Base Impl -> fragment Manager != NULL
        fragmentManagerUtils.setFragmentManager(fragmentManager);
        fragmentManagerUtils.setCallback(this);
        fragmentManagerUtils.setClassName(className);
        dialogManager.setFragmentManager(fragmentManager);
    }

    public void setLayoutFragment(int layoutFragment) {
        fragmentManagerUtils.setLayoutFragment(layoutFragment);
    }

    private BaseActivity getBaseActivity() {
        return (BaseActivity) activity;
    }


    @Override
    public void onSuccessWithData(Object o) {
    }

    @Override
    public void onSuccessNull() {
    }

    @Override
    public void onMessageSuccess(String s) {
        dialogManager.errorNonAuth(this, s);
    }

    @UiThread
    @Override
    public void onError(Object o, HttpRequest httpRequest) {

    }

    @UiThread
    @Override
    public void unAuthorized(HttpRequest httpRequest, String message) {
        logoutUser();
    }


    public void checkIsLogin() {
        if (getToken() == null) {
            if (!activity.getClass().getName().equals(LoginActivity_.class.getName())) {
                intentManager.moveToLogin();
            }
        } else {
            //intentManager.moveToMain(false);
            intentManager.moveToMain();
        }
    }

    public boolean isAlreadyLogin() {
        return getToken() != null;
    }


    private String getToken() {
        return new SessionLogin(activity).getToken();
    }

    protected ModelTab makeTab(BaseFragment fragment, String title, int imageId) {
        fragment.setCallback(this);
        return new ModelTab().setFragment(fragment).setImageId(imageId).setTitle(title);
    }

    protected ModelTab makeTab(BaseFragment fragment, String title, int imageId, Object callback) {
        fragment.setCallback(callback);
        return new ModelTab().setFragment(fragment).setImageId(imageId).setTitle(title);
    }

    public void onOptionsItemSelected(MenuItem menuItem) {

    }

    protected ArrayList arrayToArrayList(Object[] array) {
        if (array != null && array.length > 0) {
            return new ArrayList(Arrays.asList(array));
        } else {
            return new ArrayList();
        }
    }


    @Override
    public void ok() {
        activity.finish();
    }


//    protected <T extends BaseSpinner> void setSpinner(Spinner spinner, T[] data) {
//        AdapterGenericGetName adapter = new AdapterGenericGetName<>(activity, data);
//        adapter.setDropDownViewResource(R.layout.spinner_list_item_dropdown);
//        spinner.setAdapter(adapter);
//    }


    @Override
    public void onValidationSucceeded() {

    }

    @Override
    public void onValidationFailed(List<ValidationError> errors) {
        for (ValidationError error : errors) {
            View view = error.getView();
            String message = error.getCollatedErrorMessage(activity);
            utilsLayout.setError(view, message);
        }
    }

    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        if (fragmentManagerUtils.getLayoutFragment() != -1) {
            BaseFragment baseFragment = (BaseFragment) fragmentManagerUtils.getFragmentManager().
                    findFragmentById(fragmentManagerUtils.getLayoutFragment());
            if (baseFragment != null)
                baseFragment.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (fragmentManagerUtils.getLayoutFragment() != -1) {
            BaseFragment baseFragment = (BaseFragment) fragmentManagerUtils.getFragmentManager().
                    findFragmentById(fragmentManagerUtils.getLayoutFragment());
            if (baseFragment != null)
                baseFragment.onActivityResult(requestCode, resultCode, data);
        }
    }


    public void backPressed() {

    }

    protected void logoutUser() {
        new SessionLogin(activity).clearToken();
        new SessionUser(activity).clearData();
        new SessionMenu(activity).clearData();
        checkIsLogin();
    }


    protected ArrayList<Object> transformToListObject(ArrayList arrayList) {
        return arrayList;
    }

    public void resume() {
        if (getFragmentManagerUtils().getExistFragment() != null) {
            getFragmentManagerUtils().getExistFragment().onResume();
        }
    }

    public ModelDataLogin getDataUser() {
        return new SessionUser(activity).getData();
    }

    public String getRealPathImageGallery(Intent data, boolean video) {
        String filePath = null;
//        if (Build.VERSION.SDK_INT < 11) {
//            filePath = RealPathUtil.getRealPathFromURIbelowAPI11(activity, data.getData(), video);
//        } // SDK >= 11 && SDK < 19
//        else if (Build.VERSION.SDK_INT < 19) {
//            filePath = RealPathUtil.getRealPathFromURIaPI11to18(activity, data.getData(), video);
//        } // SDK > 19 (Android 4.4)
//        else {
//            filePath = RealPathUtil.getRealPathFromURIaPI19(activity, data.getData(), video);
//        }
        return filePath;
    }
}
