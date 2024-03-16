package com.example.myapplication.data.remote.retrofit;

import com.example.myapplication.BuildConfig;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiConfig {

    public static ApiService getApiService() {
        Interceptor authInterceptor = chain -> {
            okhttp3.Request req = chain.request();
            okhttp3.Request requesHeaders = req.newBuilder()
                    .addHeader("Authorization", BuildConfig.API_TOKEN)
                    .build();
            return chain.proceed(requesHeaders);
        };

        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(authInterceptor)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();

        return retrofit.create(ApiService.class);
    }
}
