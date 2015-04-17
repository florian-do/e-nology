package com.enology.eip.e_nology.api;

import com.enology.eip.e_nology.api.json.CreateUserResponse;
import com.enology.eip.e_nology.api.json.LoginResponse;

import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Path;

/**
 * Created by Lolo on 27/02/2015.
 */
public interface EnologyService
{
    @POST("/auth/signup")
    void createUser(@Body CreateUserResponse user, Callback<CreateUserResponse> callback);

    @POST("/auth/signin")
    void login(@Body LoginResponse user, Callback<LoginResponse> callback);
}
