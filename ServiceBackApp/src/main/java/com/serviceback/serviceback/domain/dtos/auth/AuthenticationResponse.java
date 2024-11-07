package com.serviceback.serviceback.domain.dtos.auth;

import java.io.Serializable;

public class AuthenticationResponse implements Serializable {
    private String jwt;

    public AuthenticationResponse() {
    }

    public AuthenticationResponse(String jwt) {
        this.jwt = jwt;
    }

    public String getJwt() {
        return this.jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }

}
