package com.avyay.homora.responses;

public class LoginResponse extends BaseResponse {

    public String token;

    public String getToken() {
        return this.token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public LoginResponse(int statusCode, String message, String token) {
        super(statusCode, message);
        this.token = token;
    }
}
