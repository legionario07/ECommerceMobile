package com.example.paulinho.ecommercemobile.api.services.impl;

import android.util.Log;

import com.example.paulinho.ecommercemobile.api.RetrofitConfig;
import com.example.paulinho.ecommercemobile.api.services.ConfiguracaoServices;
import com.example.paulinho.ecommercemobile.model.Configuracao;
import com.example.paulinho.ecommercemobile.utils.ConstraintUtils;
import com.example.paulinho.ecommercemobile.utils.SessionUtil;

import java.io.IOException;
import java.util.Calendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ConfiguracaoServicesImpl {

    private Retrofit retrofit;
    private List<Configuracao> configuracoes;
    private ConfiguracaoServices services;
    private Configuracao configuracao;


    public Configuracao save(final Configuracao configuracaoBody){
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                retrofit = RetrofitConfig.getBuilderWS();
                services = retrofit.create(ConfiguracaoServices.class);

                    services.save(configuracaoBody).enqueue(new Callback<Configuracao>() {
                        @Override
                        public void onResponse(Call<Configuracao> call, Response<Configuracao> response) {
                            configuracao = response.body();

                            if(configuracao!=null){
                                SessionUtil.getInstance().getMapConfiguraces().put(configuracao.getPropriedade(), configuracao.getValor());
                            }
                        }

                        @Override
                        public void onFailure(Call<Configuracao> call, Throwable t) {
                        }
                    });


            }
        });
        t.start();

        return configuracao;
    }


}
