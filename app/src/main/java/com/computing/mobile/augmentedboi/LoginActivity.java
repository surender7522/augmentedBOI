package com.computing.mobile.augmentedboi;

import android.app.Activity;
import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.Window;
import android.view.WindowManager;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import shem.com.materiallogin.DefaultLoginView;
import shem.com.materiallogin.DefaultRegisterView;
import shem.com.materiallogin.MaterialLoginView;

public class LoginActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);
        final MaterialLoginView login = (MaterialLoginView) findViewById(R.id.login);
        ((DefaultLoginView)login.getLoginView()).setListener(new DefaultLoginView.DefaultLoginViewListener() {
            @Override
            public void onLogin(TextInputLayout loginUser, TextInputLayout loginPass) {
                //Handle login
                String email = loginUser.getEditText().getText().toString();
                String pass = loginPass.getEditText().getText().toString();
                Log.d("tag",email);
                if (!emailValidator(email)){
                    loginUser.setError("Invalid Email");
                    loginUser.setErrorEnabled(true);
                }else{
                    loginUser.setErrorEnabled(false);
                }
                if(pass.equals("")){
                    loginPass.setError("Enter Password");
                    loginPass.setErrorEnabled(true);
                }else{
                    loginPass.setErrorEnabled(false);
                }
                //TODO implement login http
            }
        });

        ((DefaultRegisterView)login.getRegisterView()).setListener(new DefaultRegisterView.DefaultRegisterViewListener() {
            @Override
            public void onRegister(TextInputLayout registerUser, TextInputLayout registerPass, TextInputLayout registerPassRep) {
                //Handle register
                String email = registerUser.getEditText().getText().toString();
                String pass = registerPass.getEditText().getText().toString();
                String repPass = registerPassRep.getEditText().getText().toString();
                if (!emailValidator(email)){
                    registerUser.setError("Invalid Email");
                    registerUser.setErrorEnabled(true);
                }else{
                    registerUser.setErrorEnabled(false);
                }
                if(!pass.equals(repPass)){
                    registerPassRep.setError("Password doesn't match");
                    registerPassRep.setErrorEnabled(true);
                }else{
                    registerPassRep.setErrorEnabled(false);
                }
                //TODO implement register http
            }
        });
    }
    public boolean emailValidator(String email)
    {
        Pattern pattern;
        Matcher matcher;
        final String EMAIL_PATTERN = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        pattern = Pattern.compile(EMAIL_PATTERN);
        matcher = pattern.matcher(email);
        return matcher.matches();
    }
}
