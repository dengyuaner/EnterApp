package com.dy.enter.viewmodel;

import android.text.TextUtils;

/**
 * Created by dy on 2017/3/10 14:05.
 */

public class UserModel {
    private String username;
    private String password;

    public UserModel(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int checkUserValidity(String username, String password) {
        if (TextUtils.isEmpty(username)
                || TextUtils.isEmpty(password)) {
            return -1;
        }
        return 0;
    }
}
