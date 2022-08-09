package com.example.onlineretailers.network_retrofit;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

    public class RetrofitClient {

    private static volatile RetrofitClient retrofitClient;
    private Retrofit retrofit;
    private static String BASE_URL =
            "https://console-mock.apipost.cn/app/mock/project/2c33fb2f-f36c-4e0e-e09c-d03a18f0e372/";



    private RetrofitClient() {
    }

    public static RetrofitClient getInstance() {
        if (retrofitClient == null) {
            synchronized (RetrofitClient.class) {
                if (retrofitClient == null) {
                    retrofitClient = new RetrofitClient();
                }
            }
        }

        return retrofitClient;
    }


    public <T> T getService(Class<T> cls) {
        return getRetrofit().create(cls);
    }


    public synchronized Retrofit getRetrofit() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
