package com.enology.eip.e_nology.api;

import android.util.Log;

import com.squareup.okhttp.OkHttpClient;

import retrofit.RequestInterceptor;
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
        setupRestClient(null);
    }

    private RestClient()
    {

    }

    public static EnologyService get()
    {
        return restClient;
    }

    public static EnologyService getToken(String token)
    {
        setupRestClient(token);
        return restClient;
    }

    private static void setupRestClient(final String token)
    {
        RestAdapter.Builder builder = new RestAdapter.Builder()
                .setEndpoint(WEBSERVICE_HOST)
                .setClient(new OkClient(new OkHttpClient()));

        if (token != null) {
            builder.setRequestInterceptor(new RequestInterceptor() {
                @Override
                public void intercept(RequestFacade request) {
                    request.addHeader("Content-Type", "application/json");
                    request.addHeader("x-access-token", token);
                }
            });
        }

        RestAdapter adapter = builder.build();
        restClient = adapter.create(EnologyService.class);


    }
}