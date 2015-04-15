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
    private static String WEBSERVICE_HOST = "http://62-210-36-42.rev.poneytelecom.eu/eip/client/";

    static {
        setupRestClient();
    }

    private RestClient()
    {

    }

    public static EnologyService get()
    {
        Log.d("REST_CLIENT", "get");
        return restClient;
    }

    private static void setupRestClient()
    {
        Log.d("REST_CLIENT", "setupRestClient");
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint(WEBSERVICE_HOST)
                .setClient(new OkClient(new OkHttpClient()))
                .build();

        restClient = restAdapter.create(EnologyService.class);
    }
}
