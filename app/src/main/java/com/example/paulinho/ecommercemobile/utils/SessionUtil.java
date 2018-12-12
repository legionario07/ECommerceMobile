package com.example.paulinho.ecommercemobile.utils;

import com.example.paulinho.ecommercemobile.model.Produto;

public class SessionUtil {

    private static SessionUtil instance = null;
    private Produto produto;

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
