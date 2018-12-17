package com.example.paulinho.ecommercemobile.utils;

import com.example.paulinho.ecommercemobile.model.Produto;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.firebase.auth.FirebaseAuth;

import java.util.List;

public class SessionUtil {

    private static SessionUtil instance = null;
    private Produto produto;

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

}
