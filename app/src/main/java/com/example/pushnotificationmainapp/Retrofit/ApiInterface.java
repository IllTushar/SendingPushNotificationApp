package com.example.pushnotificationmainapp.Retrofit;

import com.example.pushnotificationmainapp.RequestBody;
import com.example.pushnotificationmainapp.ResponseClass;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface ApiInterface
{
 @Headers("Accept: application/json")
 @POST("fcm/send")
    Call<ResponseClass>getMessage(
         @Header("Authorization")String serverKey,
         @Body RequestBody requestBody
         );

}
