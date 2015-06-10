package com.example.weblib;

public class WebResult {
    int code;
    String message;
    boolean success;

    public WebResult() {
        this.code = -1;
        this.message = null;
        this.success = false;
    }

    public WebResult(int code, String message, boolean success) {
        this.code = code;
        this.message = message;
        this.success = success;
    }
}
