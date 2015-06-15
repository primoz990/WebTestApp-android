package com.example.weblib.login;

import android.util.Base64;

public abstract class Login implements LoginInterface {

    public String getAuthHeader(String username, String password) {
        byte[] base64bytes = Base64.encode(
                (username + ":" + password).getBytes(),
                Base64.NO_WRAP
        );
        String credentials = new String(base64bytes);
        return "basic" + " " + credentials;
    }

    @Override
    public void execute(String username, String password) {
        if (username == null || password == null || username.length() <= 0 || password.length() <= 0) {
            onLoginFailed("No credentials!");
        } else {
            login(username, password);
        }
    }
}
