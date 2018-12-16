package com.example.paulinho.ecommercemobile.model;

import java.io.Serializable;
import java.util.ArrayList;

public class Item implements Serializable {

    private String id;
    private String site_id;
    private String title;
    private String subtitle = null;
    private float seller_id;
    private String category_id;
    private String official_store_id = null;
    private float price;
    private float base_price;
    private String original_price = null;
    private String currency_id;
    private float initial_quantity;
    private float available_quantity;
    private float sold_quantity;
    ArrayList<Object> sale_terms = new ArrayList<Object>();
    private String buying_mode;
    private String listing_type_id;
    private String start_time;
    private String stop_time;
    private String condition;
    private String permalink;
    private String thumbnail;
    private String secure_thumbnail;
    ArrayList<Object> pictures = new ArrayList<Object>();
    private String video_id = null;
    ArrayList<Object> descriptions = new ArrayList<Object>();
    private boolean accepts_mercadopago;
    ArrayList<Object> non_mercado_pago_payment_methods = new ArrayList<Object>();
    Shipping ShippingObject;
    private String international_delivery_mode;
    Seller_address Seller_addressObject;
    private String seller_contact = null;
    Location LocationObject;
    Geolocation GeolocationObject;
    ArrayList<Object> coverage_areas = new ArrayList<Object>();
    ArrayList<Object> attributes = new ArrayList<Object>();
    ArrayList<Object> warnings = new ArrayList<Object>();
    private String listing_source;
    ArrayList<Object> variations = new ArrayList<Object>();
    private String status;
    ArrayList<Object> sub_status = new ArrayList<Object>();
    ArrayList<Object> tags = new ArrayList<Object>();
    private String warranty;
    private String catalog_product_id = null;
    private String domain_id;
    private String parent_item_id = null;
    private String differential_pricing = null;
    ArrayList<Object> deal_ids = new ArrayList<Object>();
    private boolean automatic_relist;
    private String date_created;
    private String last_updated;
    private float health;

    public String getId() {
        return id;
    }

    public String getSite_id() {
        return site_id;
    }

    public String getTitle() {
        return title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public float getSeller_id() {
        return seller_id;
    }

    public String getCategory_id() {
        return category_id;
    }

    public String getOfficial_store_id() {
        return official_store_id;
    }

    public float getPrice() {
        return price;
    }

    public float getBase_price() {
        return base_price;
    }

    public String getOriginal_price() {
        return original_price;
    }

    public String getCurrency_id() {
        return currency_id;
    }

    public float getInitial_quantity() {
        return initial_quantity;
    }

    public float getAvailable_quantity() {
        return available_quantity;
    }

    public float getSold_quantity() {
        return sold_quantity;
    }

    public String getBuying_mode() {
        return buying_mode;
    }

    public String getListing_type_id() {
        return listing_type_id;
    }

    public String getStart_time() {
        return start_time;
    }

    public String getStop_time() {
        return stop_time;
    }

    public String getCondition() {
        return condition;
    }

    public String getPermalink() {
        return permalink;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public String getSecure_thumbnail() {
        return secure_thumbnail;
    }

    public String getVideo_id() {
        return video_id;
    }

    public boolean getAccepts_mercadopago() {
        return accepts_mercadopago;
    }

    public Shipping getShipping() {
        return ShippingObject;
    }

    public String getInternational_delivery_mode() {
        return international_delivery_mode;
    }

    public Seller_address getSeller_address() {
        return Seller_addressObject;
    }

    public String getSeller_contact() {
        return seller_contact;
    }

    public Location getLocation() {
        return LocationObject;
    }

    public Geolocation getGeolocation() {
        return GeolocationObject;
    }

    public String getListing_source() {
        return listing_source;
    }

    public String getStatus() {
        return status;
    }

    public String getWarranty() {
        return warranty;
    }

    public String getCatalog_product_id() {
        return catalog_product_id;
    }

    public String getDomain_id() {
        return domain_id;
    }

    public String getParent_item_id() {
        return parent_item_id;
    }

    public String getDifferential_pricing() {
        return differential_pricing;
    }

    public boolean getAutomatic_relist() {
        return automatic_relist;
    }

    public String getDate_created() {
        return date_created;
    }

    public String getLast_updated() {
        return last_updated;
    }

    public float getHealth() {
        return health;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setSite_id(String site_id) {
        this.site_id = site_id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public void setSeller_id(float seller_id) {
        this.seller_id = seller_id;
    }

    public void setCategory_id(String category_id) {
        this.category_id = category_id;
    }

    public void setOfficial_store_id(String official_store_id) {
        this.official_store_id = official_store_id;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public void setBase_price(float base_price) {
        this.base_price = base_price;
    }

    public void setOriginal_price(String original_price) {
        this.original_price = original_price;
    }

    public void setCurrency_id(String currency_id) {
        this.currency_id = currency_id;
    }

    public void setInitial_quantity(float initial_quantity) {
        this.initial_quantity = initial_quantity;
    }

    public void setAvailable_quantity(float available_quantity) {
        this.available_quantity = available_quantity;
    }

    public void setSold_quantity(float sold_quantity) {
        this.sold_quantity = sold_quantity;
    }

    public void setBuying_mode(String buying_mode) {
        this.buying_mode = buying_mode;
    }

    public void setListing_type_id(String listing_type_id) {
        this.listing_type_id = listing_type_id;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public void setStop_time(String stop_time) {
        this.stop_time = stop_time;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public void setPermalink(String permalink) {
        this.permalink = permalink;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public void setSecure_thumbnail(String secure_thumbnail) {
        this.secure_thumbnail = secure_thumbnail;
    }

    public void setVideo_id(String video_id) {
        this.video_id = video_id;
    }

    public void setAccepts_mercadopago(boolean accepts_mercadopago) {
        this.accepts_mercadopago = accepts_mercadopago;
    }

    public void setShipping(Shipping shippingObject) {
        this.ShippingObject = shippingObject;
    }

    public void setInternational_delivery_mode(String international_delivery_mode) {
        this.international_delivery_mode = international_delivery_mode;
    }

    public void setSeller_address(Seller_address seller_addressObject) {
        this.Seller_addressObject = seller_addressObject;
    }

    public void setSeller_contact(String seller_contact) {
        this.seller_contact = seller_contact;
    }

    public void setLocation(Location locationObject) {
        this.LocationObject = locationObject;
    }

    public void setGeolocation(Geolocation geolocationObject) {
        this.GeolocationObject = geolocationObject;
    }

    public void setListing_source(String listing_source) {
        this.listing_source = listing_source;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setWarranty(String warranty) {
        this.warranty = warranty;
    }

    public void setCatalog_product_id(String catalog_product_id) {
        this.catalog_product_id = catalog_product_id;
    }

    public void setDomain_id(String domain_id) {
        this.domain_id = domain_id;
    }

    public void setParent_item_id(String parent_item_id) {
        this.parent_item_id = parent_item_id;
    }

    public void setDifferential_pricing(String differential_pricing) {
        this.differential_pricing = differential_pricing;
    }

    public void setAutomatic_relist(boolean automatic_relist) {
        this.automatic_relist = automatic_relist;
    }

    public void setDate_created(String date_created) {
        this.date_created = date_created;
    }

    public void setLast_updated(String last_updated) {
        this.last_updated = last_updated;
    }

    public void setHealth(float health) {
        this.health = health;
    }

    public class Geolocation {


        // Getter Methods


        // Setter Methods


    }

    public class Location {


        // Getter Methods


        // Setter Methods


    }

    public class Seller_address {


        // Getter Methods


        // Setter Methods


    }

    public class Shipping {


        // Getter Methods


        // Setter Methods


    }

}
