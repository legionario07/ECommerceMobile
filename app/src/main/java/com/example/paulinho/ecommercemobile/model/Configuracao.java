package com.example.paulinho.ecommercemobile.model;

import java.io.Serializable;

public class Configuracao implements Serializable {

    private Integer id;
    private String propriedade;
    private String valor;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPropriedade() {
        return propriedade.toUpperCase();
    }

    public void setPropriedade(String propriedade) {
        this.propriedade = propriedade;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }
}
