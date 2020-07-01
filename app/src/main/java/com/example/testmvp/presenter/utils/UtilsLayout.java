package com.example.testmvp.presenter.utils;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.Point;
import android.os.Build;
import android.text.Editable;
import android.text.InputType;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Patterns;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;

import com.example.testmvp.R;
import com.google.android.material.textfield.TextInputLayout;

import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import connection.rxconnection.utils.Utils;

@EBean
public class UtilsLayout {
    @RootContext
    Activity context;



    public String getBodyText(TextView textView) {
        if (textView != null)
            return textView.getText().toString();
        return null;
    }

    public Long getBodyTextToLong(TextView textView) {
        return checkLength(textView) ? Long.valueOf(textView.getText().toString()) : 0;
    }


    public boolean checkLength(TextView textView) {
        if (getBodyText(textView).length() > 0) {
            return true;
        } else {
            textView.requestFocus();
            return false;
        }
    }

    public boolean checkLength(TextView textView, String error) {
        if (getBodyText(textView).length() > 0) {
            textView.setError(null);
            return true;
        } else {
            textView.setError(error);
            textView.requestFocus();
            return false;
        }
    }

    public boolean checkLength(TextView textView, String error, int length) {
        if (getBodyText(textView).length() >= length) {
            textView.setError(null);
            return true;
        } else {
            textView.setError(error);
            textView.requestFocus();
            return false;
        }
    }

    public boolean checkLength(TextView textView, String error, int min, int max) {
        int length = getBodyText(textView).length();
        if (length >= min && length <= max) {
            textView.setError(null);
            return true;
        } else {
            textView.setError(error);
            textView.requestFocus();
            return false;
        }
    }

    public boolean checkLength(TextView textView, String error, String regex, int min, int max) {
        String body = getBodyText(textView);
        int length = body.length();
        if (length >= min && length <= max && Utils.regex(body, regex)) {
            textView.setError(null);
            return true;
        } else {
            textView.setError(error);
            textView.requestFocus();
            return false;
        }
    }


    public boolean checkEmail(TextView textView, String error) {
        if (checkLength(textView, error)) {
            Pattern pattern = Patterns.EMAIL_ADDRESS;
            Matcher matcher = pattern.matcher(getBodyText(textView));
            if (matcher.matches()) {
                return true;
            } else {
                textView.setError(error);
                return false;
            }
        }
        return false;
    }


    public boolean checkPassMatch(TextView pass, TextView confirmation, String error) {
        if (checkLength(pass, error)) {
            if (getBodyText(pass).equals(getBodyText(confirmation)))
                return true;
            else {
                confirmation.setError(error);
                return false;
            }
        }
        return false;
    }

    public int widthPhone() {
        Display display = context.getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        return size.x;
    }

    public int heightPhone() {
        Display display = context.getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        return size.y;
    }

    public void showHidePass(TextView textView) {
        if (textView.getInputType() == (InputType.TYPE_CLASS_TEXT |
                InputType.TYPE_TEXT_VARIATION_PASSWORD)) {
            textView.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_icon_open_eye, 0);
            textView.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
        } else {
            textView.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_icon_close_eye, 0);
            textView.setInputType(InputType.TYPE_CLASS_TEXT |
                    InputType.TYPE_TEXT_VARIATION_PASSWORD);
        }
    }

    public FrameLayout.LayoutParams getParamImage() {
        return new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, widthPhone() / 4 * 3);
    }

    public static void wordsCapitalize(final EditText targetET) {
        targetET.addTextChangedListener(new TextWatcher() {
            int mStart = 0;

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mStart = start + count;
            }

            @Override
            public void afterTextChanged(Editable s) {
                String input = s.toString();
                String capitalizedText;

                if (input.length() < 1)
                    capitalizedText = input;
                else {
                    String[] words = input.split(" ");
                    boolean containSpace = input.charAt(input.length() - 1) == ' ';
                    for (int i = 0; i < words.length; i++) {
                        String capitalizedWord = words[i].substring(0, 1).toUpperCase() + words[i].substring(1);
                        words[i] = capitalizedWord;
                    }
                    capitalizedText = TextUtils.join(" ", words);
                    if (containSpace)
                        capitalizedText += " ";
                }
                if (!capitalizedText.equals(targetET.getText().toString())) {
                    targetET.addTextChangedListener(new TextWatcher() {
                        @Override
                        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                        }

                        @Override
                        public void onTextChanged(CharSequence s, int start, int before, int count) {

                        }

                        @Override
                        public void afterTextChanged(Editable s) {
                            targetET.setSelection(mStart);
                            targetET.removeTextChangedListener(this);
                        }
                    });
                    targetET.setText(capitalizedText);
                }
            }
        });
    }

    public void setError(View view, String message) {
        if (view instanceof TextView) {
            if (view.getParent() instanceof TextInputLayout) {
                TextInputLayout textInputLayout = (TextInputLayout) view.getParent();
                setEnableError(textInputLayout, message);
                textInputLayout.setError(message);
            } else if (view.getParent().getParent() instanceof TextInputLayout) {
                TextInputLayout textInputLayout = (TextInputLayout) view.getParent().getParent();
                setEnableError(textInputLayout, message);
                textInputLayout.setError(message);
            } else {
                ((TextView) view).setError(message);
            }
        } else {
            Toast.makeText(context, message, Toast.LENGTH_LONG).show();
        }

    }

    private void setEnableError(TextInputLayout textInputLayout, String message) {
        textInputLayout.setErrorEnabled(message != null ? true : false);
    }

    public static void setColorStatus(TextView textView, String status) {
        switch (status) {
            case "available":
//                textView.setBackgroundColor(R.color.lime);
                textView.setBackgroundColor(Color.GREEN);
                break;
            case "on lease":
//                textView.setBackgroundColor(R.color.redlime);
                textView.setBackgroundColor(Color.RED);
                break;
            case "assigned":
//                textView.setBackgroundColor(R.color.liightblue);
                textView.setBackgroundColor(Color.BLUE);
                break;
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public static <T> ArrayList<T> dummydata(Class<T> tClass, int size) {
        ArrayList<T> models = new ArrayList<>();
        T model;
        try {
            model = tClass.newInstance();
            for (int i = 0; i < size; i++) {
                models.add(model);
            }
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return models;
    }
}
