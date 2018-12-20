package com.example.paulinho.ecommercemobile.utils;

import com.example.paulinho.ecommercemobile.model.Configuracao;
import com.example.paulinho.ecommercemobile.model.Produto;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SessionUtil {

    private static SessionUtil instance = null;
    private Produto produto;
    private List<Configuracao> configuracoes;
    private Map<String, String> mapConfiguraces;

    public Map<String, String> getMapConfiguraces() {
        return mapConfiguraces;
    }

    public void setMapConfiguraces(Map<String, String> mapConfiguraces) {
        this.mapConfiguraces = mapConfiguraces;
    }

    public List<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
    }

    private List<Produto> produtos;

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    private SessionUtil(){

        mapConfiguraces = new HashMap<>();
        produtos = new ArrayList<>();
        configuracoes = new ArrayList<>();
    }

    public static SessionUtil getInstance(){

        if(instance==null){
            instance = new SessionUtil();
        }

        return instance;
    }

    public void clear(){
        produto = null;
    }

    public List<Configuracao> getConfiguracoes() {
        return configuracoes;
    }

    public void setConfiguracoes(List<Configuracao> configuracoes) {
        this.configuracoes = configuracoes;
    }
}
