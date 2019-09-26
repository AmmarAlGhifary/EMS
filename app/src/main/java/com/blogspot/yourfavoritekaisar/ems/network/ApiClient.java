package com.blogspot.yourfavoritekaisar.ems.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
    private static  Retrofit retrofit = null;

    public static Retrofit getClient() {
        retrofit = new Retrofit.Builder()
        .baseUrl("172.16.1.3")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit;
    }
}
