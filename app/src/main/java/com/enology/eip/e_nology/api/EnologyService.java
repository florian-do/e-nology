package com.enology.eip.e_nology.api;

import com.enology.eip.e_nology.api.json.LoginResponse;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Path;

/**
 * Created by Lolo on 27/02/2015.
 */
public interface EnologyService
{
    @GET("/login/{user}/{password}")
    void login(@Path("user") String user, @Path("password") String password, Callback<LoginResponse> callback);
}
