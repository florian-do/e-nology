package com.enology.eip.e_nology.api;

import com.enology.eip.e_nology.api.json.CreateUserResponse;
import com.enology.eip.e_nology.api.json.LoginResponse;
import com.enology.eip.e_nology.api.json.SommelierResponse;
import com.enology.eip.e_nology.api.json.getBottleByIdResponse;
import com.enology.eip.e_nology.api.json.getBottlesResponse;
import com.enology.eip.e_nology.api.json.getCaveResponse;
import com.enology.eip.e_nology.api.json.getResearchResponse;
import com.enology.eip.e_nology.api.json.object.Sommelier.SommelierBody;

import java.util.List;

import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.Header;
import retrofit.http.PATCH;
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

    @GET("/bottles")
    void getBottles(Callback<List<getBottlesResponse>> callback);

    @GET("/bottles/{id}")
    void getBottleById(@Path("id") String id, Callback<getBottleByIdResponse> callback);

    @GET("/caves/{id}/myBottles")
    void getCave(@Path("id") String id, Callback<List<getCaveResponse>> callback);

    @GET("/recipes/search/{research}")
    void getResearch(@Path("research") String research, Callback<List<getResearchResponse>> callback);

    @POST("/sommelier/wine")
    void getSommelier(@Body SommelierBody body, Callback<SommelierResponse> callback);
}
