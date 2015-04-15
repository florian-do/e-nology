package com.enology.eip.e_nology.api.json;

/**
 * Created by Lolo on 27/02/2015.
 */
public class LoginResponse
{
    private String login;

    public LoginResponse(String login) {
        this.login = login;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

}
