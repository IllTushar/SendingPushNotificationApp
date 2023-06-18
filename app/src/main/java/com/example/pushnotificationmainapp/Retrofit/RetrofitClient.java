package com.example.pushnotificationmainapp.Retrofit;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient
{
private static String BASEURL="https://fcm.googleapis.com/";
private static Retrofit retrofit;
public Retrofit getRetrofit(){
    if (retrofit==null){
        retrofit = new Retrofit.Builder().baseUrl(BASEURL).addConverterFactory(GsonConverterFactory.create())
                .build();
    }
    return retrofit;
}
}
