package com.example.paulinho.ecommercemobile.model;

import java.io.Serializable;
import java.util.List;

public class Vendedor implements Serializable
{

    private Double id;
    private String powerSellerStatus;
    private Boolean carDealer;
    private Boolean realEstateAgency;
    private List<Tag> tags;

    public Double getId() {
        return id;
    }

    public void setId(Double id) {
        this.id = id;
    }

    public String getPowerSellerStatus() {
        return powerSellerStatus;
    }

    public void setPowerSellerStatus(String powerSellerStatus) {
        this.powerSellerStatus = powerSellerStatus;
    }

    public Boolean getCarDealer() {
        return carDealer;
    }

    public void setCarDealer(Boolean carDealer) {
        this.carDealer = carDealer;
    }

    public Boolean getRealEstateAgency() {
        return realEstateAgency;
    }

    public void setRealEstateAgency(Boolean realEstateAgency) {
        this.realEstateAgency = realEstateAgency;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }
}
