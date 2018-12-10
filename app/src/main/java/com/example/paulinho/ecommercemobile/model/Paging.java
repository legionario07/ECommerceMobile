package com.example.paulinho.ecommercemobile.model;


public class Paging {
    private float total;
    private float offset;
    private float limit;
    private float primary_results;


    // Getter Methods

    public float getTotal() {
        return total;
    }

    public float getOffset() {
        return offset;
    }

    public float getLimit() {
        return limit;
    }

    public float getPrimary_results() {
        return primary_results;
    }

    // Setter Methods

    public void setTotal(float total) {
        this.total = total;
    }

    public void setOffset(float offset) {
        this.offset = offset;
    }

    public void setLimit(float limit) {
        this.limit = limit;
    }

    public void setPrimary_results(float primary_results) {
        this.primary_results = primary_results;
    }
}

