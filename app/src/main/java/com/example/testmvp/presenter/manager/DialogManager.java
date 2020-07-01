package com.example.testmvp.presenter.manager;

import android.app.Activity;
import android.content.DialogInterface;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.FragmentManager;

import com.example.testmvp.presenter.callback.CallBackDialog;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;

import lombok.Setter;

@EBean
public class DialogManager {

    @RootContext
    protected Activity activity;
    @Setter
    private FragmentManager fragmentManager;
    @Bean
    protected IntentManager intentManager;

    public void errorDialog(String message) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setMessage(message)
                .setCancelable(false)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(@SuppressWarnings("unused") final DialogInterface dialog, @SuppressWarnings("unused") final int id) {

                    }
                });

        final AlertDialog alert = builder.create();
        alert.show();
    }

    public void errorNonAuth(final CallBackDialog callBackDialog, String message) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setMessage(message)
                .setCancelable(false)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(@SuppressWarnings("unused") final DialogInterface dialog, @SuppressWarnings("unused") final int id) {
                        callBackDialog.ok();
                    }
                });

        final AlertDialog alert = builder.create();
        alert.show();
    }


}
