package com.example.mvvm.retrofitclass;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitInstance {
    private static final Retrofit retrofit = null;
    public static Retrofit getClient(Context context) {
        SharedPreferences read_preferences = context.getSharedPreferences("MySharedPref", Activity.MODE_PRIVATE);
      //  String token = read_preferences.getString("token", "");

     //  String token  = "lmZ2hvdXNAZ21haWwuY29tIiwiQVBJX1RJTUUiOjE2MzI0Njk4OTZ9._ICp2zWfX6tBaA41CfkS3OpfoGS1ITnJ34oIiHxAgYE";
        String token  = "";


      // String BASE_URL =  "https://admin.greenprofile.me/api/";
        String BASE_URL =  "https://medisol.pk/api/";
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        OkHttpClient   okHttpClient = new OkHttpClient.Builder()

                .addInterceptor(new Interceptor() {

                    @Override
                    public Response intercept(Chain chain) throws IOException {


                        Request request = chain.request();
                        Request newrequest = request.newBuilder()
                                .header("Authorization", token)
                                .build();
                        return chain.proceed(newrequest);
                    }
                }).retryOnConnectionFailure(true)
                .addInterceptor(loggingInterceptor
                .setLevel(HttpLoggingInterceptor.Level.BODY)).build();


        Retrofit retrofit = new Retrofit.Builder() //build url
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();
        return retrofit;
    }
}