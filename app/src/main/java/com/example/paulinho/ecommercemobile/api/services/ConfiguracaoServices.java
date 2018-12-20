package com.example.paulinho.ecommercemobile.api.services;


import com.example.paulinho.ecommercemobile.model.Configuracao;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

public interface ConfiguracaoServices {

    String BASE_URL_CONFIGURACOES = "configuracoes/";
    String SAVE = "save";
    String FIND_BY_SKU = "findByPropriedade";
    String FIND_BY_ID = "findById";
    String DELETE = "delete";

    @GET(BASE_URL_CONFIGURACOES)
    Observable<List<Configuracao>> findAll();

    @GET(BASE_URL_CONFIGURACOES+FIND_BY_SKU)
    Observable<Configuracao> findBySku(@Query("propriedade") String propriedade);

    @GET(BASE_URL_CONFIGURACOES+FIND_BY_ID)
    Observable<Configuracao> findById(@Query("id") Integer id);

    @POST(BASE_URL_CONFIGURACOES+SAVE)
    Call<Configuracao> save(@Body Configuracao configuracao);


    @retrofit2.http.DELETE(BASE_URL_CONFIGURACOES+DELETE)
    Observable<Boolean> delete(@Query("id")Integer id);

}

