package com.enology.eip.e_nology.api.json;

/**
 * Created by Lolo on 17/04/2015.
 */
public class CreateUserResponse {

    private String  email;
    private String  firstName;
    private String  lastName;
    private String  username;
    private String  password;
    private int     code;
    private String  fields;
    private String  message;

    public CreateUserResponse(String email, String firstName, String lastName, String username, String password) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.username = username;
    }

    public CreateUserResponse(String message, int code, String fields) {
        this.message = message;
        this.code = code;
        this.fields = fields;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public void setFields(String fields) {
        this.fields = fields;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getEmail() {
        return email;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

    public int getCode() {
        return code;
    }

    public String getFields() {
        return fields;
    }

    public String getMessage() {
        return message;
    }
}
