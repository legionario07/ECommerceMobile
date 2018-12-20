package com.example.paulinho.ecommercemobile.enuns;

import com.example.paulinho.ecommercemobile.utils.ConstraintUtils;

public enum AnuncioType {

    CLASSICO (ConstraintUtils.CLASSICO),
    PREMIUM (ConstraintUtils.PREMIUM);

    private String value;
    private AnuncioType(String value){
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}

