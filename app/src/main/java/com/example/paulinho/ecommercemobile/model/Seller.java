package com.example.paulinho.ecommercemobile.model;

import java.util.ArrayList;

public class Seller {
    private float id;
    SellerReputation seller_reputationObject;
    private boolean real_estate_agency;
    private boolean car_dealer;
    ArrayList<Object> tags = new ArrayList<Object>();


    // Getter Methods

    public float getId() {
        return id;
    }

    public SellerReputation getSeller_reputation() {
        return seller_reputationObject;
    }

    public boolean getReal_estate_agency() {
        return real_estate_agency;
    }

    public boolean getCar_dealer() {
        return car_dealer;
    }

    // Setter Methods

    public void setId(float id) {
        this.id = id;
    }

    public void setSeller_reputation(SellerReputation seller_reputationObject) {
        this.seller_reputationObject = seller_reputationObject;
    }

    public void setReal_estate_agency(boolean real_estate_agency) {
        this.real_estate_agency = real_estate_agency;
    }

    public void setCar_dealer(boolean car_dealer) {
        this.car_dealer = car_dealer;
    }
}