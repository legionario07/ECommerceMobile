package com.example.paulinho.ecommercemobile.model;

import java.io.Serializable;
import java.math.BigDecimal;

public class Review implements Serializable {

    private BigDecimal ratingAverage;
    private BigDecimal total;

    public BigDecimal getRatingAverage() {
        return ratingAverage;
    }

    public void setRatingAverage(BigDecimal ratingAverage) {
        this.ratingAverage = ratingAverage;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }
}
