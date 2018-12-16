package com.example.paulinho.ecommercemobile.api.services.impl;

import android.util.Log;

import com.example.paulinho.ecommercemobile.api.RetrofitConfig;
import com.example.paulinho.ecommercemobile.api.services.ItemServices;
import com.example.paulinho.ecommercemobile.api.services.MBLServices;
import com.example.paulinho.ecommercemobile.model.DataForUser;
import com.example.paulinho.ecommercemobile.model.Item;
import com.example.paulinho.ecommercemobile.utils.ConstraintUtils;
import com.example.paulinho.ecommercemobile.utils.ImagemUtils;
import com.google.gson.internal.LinkedTreeMap;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ItemServicesImpl {

    private String dados;
    private ItemServices services;
    private Retrofit retrofit;
    private Item item;

    public void findById(final String id, final LinkedTreeMap<String,Object> map){
        Thread t  = new Thread(new Runnable() {
            @Override
            public void run() {

                retrofit = RetrofitConfig.getBuilder();
                services = retrofit.create(ItemServices.class);


                try {
                    item = services.findById(id).execute().body();
                    map.put(ConstraintUtils.ITEM, item);
                    map.put(ConstraintUtils.FLAG_AVAILABLE_QUANTITY, item.getInitial_quantity());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        t.start();

    }
}
