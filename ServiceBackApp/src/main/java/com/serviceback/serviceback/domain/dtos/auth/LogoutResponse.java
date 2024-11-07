package com.serviceback.serviceback.domain.dtos.auth;

import java.io.Serializable;

public class LogoutResponse implements Serializable {
    private String message;

    public LogoutResponse() {
    }

    public LogoutResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
