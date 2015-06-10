package com.example.weblib;

public interface LoginInterface {

    String URL = "https://httpbin.org/basic-auth/user/pass";

    void execute(final String username, final String password);

    void login(final String username, final String password);

    void onLoginFailed(int code, String message);

    void onLoginSuccess();
}
