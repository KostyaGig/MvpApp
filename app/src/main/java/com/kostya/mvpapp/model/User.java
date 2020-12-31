package com.kostya.mvpapp.model;

import android.text.TextUtils;
import android.util.Log;

public class User {

    private String email;
    private String password;

    public User(){

    }

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean validateData(){

        if (!TextUtils.isEmpty(email) && !TextUtils.isEmpty(password)){
            return true;
        } else {
            return false;
        }

    }

}
