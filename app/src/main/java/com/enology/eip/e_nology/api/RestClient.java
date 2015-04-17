package com.enology.eip.e_nology.api;

import android.util.Log;

import com.squareup.okhttp.OkHttpClient;

import retrofit.RestAdapter;
import retrofit.client.OkClient;

/**
 * Created by Lolo on 27/02/2015.
 */
public class RestClient
{
    private static EnologyService restClient;
    private static String WEBSERVICE_HOST = "https://salty-gorge-2041.herokuapp.com/";

    static {
        setupRestClient();
    }

    private RestClient()
    {

    }

    public static EnologyService get()
    {
        return restClient;
    }

    private static void setupRestClient()
    {
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint(WEBSERVICE_HOST)
                .setClient(new OkClient(new OkHttpClient()))
                .build();

        restClient = restAdapter.create(EnologyService.class);
    }
}