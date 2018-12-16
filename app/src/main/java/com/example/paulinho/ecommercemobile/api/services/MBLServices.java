package com.example.paulinho.ecommercemobile.api.services;

import com.example.paulinho.ecommercemobile.model.DataForUser;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MBLServices {

    String SEARCH = "sites/MLB/search/";

    @GET(SEARCH)
    Call<DataForUser> getDataForUser(@Query("seller_id") Integer sellerID);

}
