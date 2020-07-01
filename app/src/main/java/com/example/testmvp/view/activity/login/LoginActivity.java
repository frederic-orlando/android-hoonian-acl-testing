package com.example.testmvp.view.activity.login;

import android.widget.EditText;

import com.example.testmvp.R;
import com.example.testmvp.presenter.session.SessionMenu;
import com.example.testmvp.view.activity.base.BaseActivity;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

@EActivity(R.layout.activity_login)
public class LoginActivity extends BaseActivity implements LoginView{
    @NotEmpty
    @ViewById(R.id.email_textView)
    protected EditText email;

    @NotEmpty
    @ViewById(R.id.password_textView)
    protected EditText password;

    @Bean
    protected LoginImpl login;

    @AfterViews
    protected void init() {
        //new SessionMenu(this).clearData();
        login.setViewAct(this);
        login.checkIsLogin();
    }

    @Override
    public EditText email() { return email; }

    @Override
    public EditText password() { return password; }

    @Click(R.id.login_button)
    protected void login() {login.getValidator().validate();}

    // CRASH
//    @Touch(R.id.password_textView)
//    protected boolean showPassword(View v, MotionEvent event) {
//        if (event.getAction() == MotionEvent.ACTION_UP && v instanceof EditText) {
//            if (event.getRawX() >= (v.getRight() - ((EditText) v).getCompoundDrawables()[2]
//                    .getBounds().width())) {
//                login.showHidePass((TextView) v);
//                return true;
//            }
//        }
//        return false;
//    }
}