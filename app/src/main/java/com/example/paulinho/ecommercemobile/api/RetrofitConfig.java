package com.example.paulinho.ecommercemobile.api;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitConfig {

    public static final String BASE_URL_MBL = "https://api.mercadolibre.com/";
    public static final String BASE_URL_WS = "http://omniatechnology.com.br/ECommerceWS/";
    //public static final String BASE_URL_WS = "http://192.168.1.85:8081/";

    public static Retrofit getBuilder(){
        return new Retrofit.Builder()
                .baseUrl(BASE_URL_MBL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static Retrofit getBuilderWS(){
        return new Retrofit.Builder()
                .baseUrl(BASE_URL_WS)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static Retrofit getBuilder(boolean isRxJava){
        return new Retrofit.Builder()
                .baseUrl(BASE_URL_MBL)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static Retrofit getBuilderWS(boolean isRxJava){
        return new Retrofit.Builder()
                .baseUrl(BASE_URL_MBL)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

}
