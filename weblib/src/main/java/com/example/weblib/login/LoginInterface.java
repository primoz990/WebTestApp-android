package com.example.weblib.login;

public interface LoginInterface {

    String URL = "https://httpbin.org/basic-auth/user/pass";

    void execute(final String username, final String password);

    void login(final String username, final String password);

    void onLoginFailed(String message);

    void onLoginSuccess();
}
