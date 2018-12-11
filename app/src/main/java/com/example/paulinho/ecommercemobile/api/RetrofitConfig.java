package com.example.paulinho.ecommercemobile.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitConfig {

    public static final String BASE_URL_MBL = "https://api.mercadolibre.com/sites/MLB/";
    public static final String BASE_URL_PRODUTOS = "http://192.168.1.85:8081/";

    public static Retrofit getBuilder(){
        return new Retrofit.Builder()
                .baseUrl(BASE_URL_MBL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static Retrofit getBuilderProduto(){
        return new Retrofit.Builder()
                .baseUrl(BASE_URL_PRODUTOS)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

}
