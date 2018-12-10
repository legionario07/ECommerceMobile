package com.example.paulinho.ecommercemobile.model;

import java.io.InputStream;
import java.util.ArrayList;

public class DataForUser {
    private String site_id;
    Seller SellerObject;
    Paging PagingObject;
    ArrayList<Object> results = new ArrayList<Object>();
    ArrayList<Object> secondary_results = new ArrayList<Object>();
    ArrayList<Object> related_results = new ArrayList<Object>();
    Sort SortObject;
    ArrayList<Object> available_sorts = new ArrayList<Object>();

    public Seller getSellerObject() {
        return SellerObject;
    }

    public void setSellerObject(Seller sellerObject) {
        SellerObject = sellerObject;
    }

    public Paging getPagingObject() {
        return PagingObject;
    }

    public void setPagingObject(Paging pagingObject) {
        PagingObject = pagingObject;
    }

    public ArrayList<Object> getResults() {
        return results;
    }

    public void setResults(ArrayList<Object> results) {
        this.results = results;
    }

    public ArrayList<Object> getSecondary_results() {
        return secondary_results;
    }

    public void setSecondary_results(ArrayList<Object> secondary_results) {
        this.secondary_results = secondary_results;
    }

    public ArrayList<Object> getRelated_results() {
        return related_results;
    }

    public void setRelated_results(ArrayList<Object> related_results) {
        this.related_results = related_results;
    }

    public Sort getSortObject() {
        return SortObject;
    }

    public void setSortObject(Sort sortObject) {
        SortObject = sortObject;
    }

    public ArrayList<Object> getAvailable_sorts() {
        return available_sorts;
    }

    public void setAvailable_sorts(ArrayList<Object> available_sorts) {
        this.available_sorts = available_sorts;
    }

    public ArrayList<Object> getFilters() {
        return filters;
    }

    public void setFilters(ArrayList<Object> filters) {
        this.filters = filters;
    }

    public ArrayList<Object> getAvailable_filters() {
        return available_filters;
    }

    public void setAvailable_filters(ArrayList<Object> available_filters) {
        this.available_filters = available_filters;
    }

    ArrayList<Object> filters = new ArrayList<Object>();
    ArrayList<Object> available_filters = new ArrayList<Object>();


    // Getter Methods

    public String getSite_id() {
        return site_id;
    }

    public Seller getSeller() {
        return SellerObject;
    }

    public Paging getPaging() {
        return PagingObject;
    }

    public Sort getSort() {
        return SortObject;
    }

    // Setter Methods

    public void setSite_id(String site_id) {
        this.site_id = site_id;
    }

    public void setSeller(Seller sellerObject) {
        this.SellerObject = sellerObject;
    }

    public void setPaging(Paging pagingObject) {
        this.PagingObject = pagingObject;
    }

    public void setSort(Sort sortObject) {
        this.SortObject = sortObject;
    }

}



