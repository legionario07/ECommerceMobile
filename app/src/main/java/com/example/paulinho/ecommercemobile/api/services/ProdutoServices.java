package com.example.paulinho.ecommercemobile.api.services;


import com.example.paulinho.ecommercemobile.model.Produto;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ProdutoServices {

    String BASE_URL_PRODUTOS = "produtos/";
    String SAVE = "save";
    String FIND_BY_SKU = "findBySku";
    String FIND_BY_ID = "findById";
    String DELETE = "delete";

    @GET(BASE_URL_PRODUTOS)
    Call<List<Produto>> findAll();

    @GET(BASE_URL_PRODUTOS+FIND_BY_SKU)
    Call<Produto> findBySku(@Query("sku") String sku);

    @GET(BASE_URL_PRODUTOS+FIND_BY_ID)
    Call<Produto> findById(@Query("id") Integer id);

    @POST(BASE_URL_PRODUTOS+SAVE)
    Call<Produto> save(@Body Produto produto);


    @retrofit2.http.DELETE(BASE_URL_PRODUTOS+DELETE)
    Call<Boolean> delete(@Query("id")Integer id);

}

