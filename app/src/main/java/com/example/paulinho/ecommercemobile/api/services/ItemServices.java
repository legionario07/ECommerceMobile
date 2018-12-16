package com.example.paulinho.ecommercemobile.api.services;


import com.example.paulinho.ecommercemobile.model.Item;
import com.example.paulinho.ecommercemobile.model.Produto;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ItemServices {

    String BASE_URL_ITEMS = "items/";

    @GET(BASE_URL_ITEMS+"{Item_id}")
    Call<Item> findById(@Path("Item_id") String itemId);

}

