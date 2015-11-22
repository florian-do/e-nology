package com.enology.eip.e_nology.api.json;

/**
 * Created by Lolo on 27/02/2015.
 */
public class LoginResponse
{
    private String  username;
    private String  password;

    private boolean succes;
    private String  message;
    private String  token;

    public LoginResponse(boolean succes, String message, String token) {
        this.succes = succes;
        this.message = message;
        this.token = token;
    }

    public LoginResponse(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }


    public boolean isSucces() {
        return succes;
    }

    public String getMessage() {
        return message;
    }

    public String getToken() {
        return token;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setSucces(boolean succes) {
        this.succes = succes;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
