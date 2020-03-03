package com.example.badmintonapi.domain;

import java.util.Map;

public class Response {
    private int code;

    private Map message;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Map getMessage() {
        return message;
    }

    public void setMessage(Map message) {
        this.message = message;
    }
}
