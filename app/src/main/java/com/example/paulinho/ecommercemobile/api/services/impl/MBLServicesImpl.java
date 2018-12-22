package com.example.paulinho.ecommercemobile.api.services.impl;

import com.example.paulinho.ecommercemobile.api.RetrofitConfig;
import com.example.paulinho.ecommercemobile.api.services.MBLServices;
import com.example.paulinho.ecommercemobile.model.DataForUser;
import com.example.paulinho.ecommercemobile.model.Item;
import com.example.paulinho.ecommercemobile.utils.ConstraintUtils;
import com.example.paulinho.ecommercemobile.utils.ImagemUtils;
import com.google.gson.internal.LinkedTreeMap;

import retrofit2.Retrofit;

public class MBLServicesImpl {

    private String dados;
    private MBLServices services;
    private Retrofit retrofit;
    private DataForUser dataForUser;

    public DataForUser getDataForUser(final Integer sellerId){
        Thread t  = new Thread(new Runnable() {
            @Override
            public void run() {

                retrofit = RetrofitConfig.getBuilder();
                services = retrofit.create(MBLServices.class);


                try {
                    dataForUser = services.getDataForUser(sellerId).execute().body();
                } catch (Exception e) {
                    e.printStackTrace();
                }

                if(dataForUser!=null){
                    for(Object o : dataForUser.getResults()){
                        LinkedTreeMap<String,Object> map = (LinkedTreeMap<String, Object>) o;
                        map.put(ConstraintUtils.THUMBNAIL_IS, ImagemUtils.getInputFromString((String) map.get("thumbnail")));
                    }
                }


            }
        });
        t.start();
        try {
            t.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return dataForUser;
    }
}
