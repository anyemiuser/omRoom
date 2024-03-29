package com.anyemi.omrooms.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    private static Retrofit retrofit = null;



    public static Retrofit getClient(String baseUrl){
        OkHttpClient.Builder client = new OkHttpClient.Builder();
        client.connectTimeout(25, TimeUnit.SECONDS);
        client.readTimeout(25, TimeUnit.SECONDS);
        client.writeTimeout(25, TimeUnit.SECONDS);
        if(retrofit == null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(client.build())
                    .build();
        }
        return retrofit;
    }
}
