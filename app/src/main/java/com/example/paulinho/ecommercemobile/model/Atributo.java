package com.example.paulinho.ecommercemobile.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Atributo implements Serializable {
    private Map<String, Object> mapAtributos;

    public Atributo(){
        setMapAtributos(new HashMap<String, Object>());
    }

    public Map<String, Object> getMapAtributos() {
        return mapAtributos;
    }

    public void setMapAtributos(Map<String, Object> mapAtributos) {
        this.mapAtributos = mapAtributos;
    }
}
