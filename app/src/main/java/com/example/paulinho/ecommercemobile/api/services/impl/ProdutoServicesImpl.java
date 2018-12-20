package com.example.paulinho.ecommercemobile.api.services.impl;

import com.example.paulinho.ecommercemobile.api.RetrofitConfig;
import com.example.paulinho.ecommercemobile.api.services.ProdutoServices;
import com.example.paulinho.ecommercemobile.model.Produto;

import java.io.IOException;
import java.util.List;

import retrofit2.Retrofit;

public class ProdutoServicesImpl{

    private Retrofit retrofit;
    private List<Produto> produtos;
    private ProdutoServices services;
    private Produto produto;

    public List<Produto> findAll(){
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                retrofit = RetrofitConfig.getBuilderWS();
                services = retrofit.create(ProdutoServices.class);

                try {
                    produtos = services.findAll().execute().body();
                } catch (IOException e) {
                    e.printStackTrace();
                }



            }
        });
        t.start();
        try {
            t.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return produtos;
    }

    public Produto save(final Produto produtoBody){
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                retrofit = RetrofitConfig.getBuilderWS();
                services = retrofit.create(ProdutoServices.class);

                try {
                    produto = services.save(produtoBody).execute().body();
                } catch (IOException e) {
                    e.printStackTrace();
                }



            }
        });
        t.start();
        try {
            t.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return produto;
    }

    public Produto findBySku(final Produto produtoBody, final String sku){
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                retrofit = RetrofitConfig.getBuilderWS();
                services = retrofit.create(ProdutoServices.class);

                try {
                    produto = services.findBySku(sku).execute().body();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                if(produto!=null){
                    produtoBody.setId(produto.getId());
                    produtoBody.setValorDeCompra(produto.getValorDeCompra());
                    produtoBody.setStockIdeal(produto.getStockIdeal());
                    produtoBody.setStockMin(produto.getStockMin());
                }


            }
        });
        t.start();
        try {
            t.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return produtoBody;
    }

}
