package com.serviceback.serviceback.domain.dtos;

import java.util.Date;

// ! ESTO ES UN POJO
public class ErrorCustom {
    private String message;
    private String error;
    private int status;
    private Date date;

    public ErrorCustom() {
    }

    public ErrorCustom(String message, String error, int status, Date date) {
        this.message = message;
        this.error = error;
        this.status = status;
        this.date = date;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getError() {
        return this.error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public int getStatus() {
        return this.status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Date getDate() {
        return this.date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

}
