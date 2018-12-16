package com.example.paulinho.ecommercemobile.model;

import java.io.InputStream;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


public class Produto implements Serializable {

    private Integer id;
    private Item item;
    private String identificacao;
    private String siteId;
    private String title;
    private Vendedor seller;
    private BigDecimal price;
    private String currencyId;
    private BigDecimal availableQuantity;
    private BigDecimal soldQuantity;
    private String buyingMode;
    private String listingTypeId;
    private Calendar stopTime;
    private String condition;
    private String permalink;
    private String thumbnail;
    private InputStream thumbnailIS;
    private Boolean acceptsMercadoPago;
    private Parcela installments;
    private Endereco address;
    private Remessa shipping;
    private EnderecoVendedor sellerAddress;
    private List<Atributo> attributes;
    private Double differentialPrincing;
    private BigDecimal originalPrice;
    private String categoryId;
    private String officialStoreId;
    private String catalogProductId;
    private Review review;
    private Tag tags;

    private String sku;
    private Integer stockMin;
    private Integer stockIdeal;
    private BigDecimal lucroEsperado;
    private BigDecimal valorDeCompra;
    private BigDecimal valorDeVenda;
    private Integer qteDisponivel;
    private Integer totalVendido;

    public Produto(Integer id){
        this();
        this.id = id;
    }

    public Produto(String sku){
        this();
        this.sku = sku;
    }

    public Produto(){
        attributes = new ArrayList<Atributo>();
    }


    public String getSiteId() {
        return siteId;
    }

    public void setSiteId(String siteId) {
        this.siteId = siteId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
        this.valorDeVenda = price;
    }

    public String getCurrencyId() {
        return currencyId;
    }

    public void setCurrencyId(String currencyId) {
        this.currencyId = currencyId;
    }

    public BigDecimal getAvailableQuantity() {
        return availableQuantity;
    }

    public void setAvailableQuantity(BigDecimal availableQuantity) {
        this.availableQuantity = availableQuantity;
        this.qteDisponivel = availableQuantity.intValue();
    }

    public BigDecimal getSoldQuantity() {
        return soldQuantity;
    }

    public void setSoldQuantity(BigDecimal soldQuantity) {
        this.soldQuantity = soldQuantity;
        this.totalVendido = soldQuantity.intValue();
    }

    public String getBuyingMode() {
        return buyingMode;
    }

    public void setBuyingMode(String buyingMode) {
        this.buyingMode = buyingMode;
    }

    public String getListingTypeId() {
        return listingTypeId;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getOfficialStoreId() {
        return officialStoreId;
    }

    public void setOfficialStoreId(String officialStoreId) {
        this.officialStoreId = officialStoreId;
    }

    public String getCatalogProductId() {
        return catalogProductId;
    }

    public void setCatalogProductId(String catalogProductId) {
        this.catalogProductId = catalogProductId;
    }

    public void setListingTypeId(String listingTypeId) {
        this.listingTypeId = listingTypeId;
    }

    public Calendar getStopTime() {
        return stopTime;
    }

    public void setStopTime(Calendar stopTime) {
        this.stopTime = stopTime;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public String getPermalink() {
        return permalink;
    }



    public void setPermalink(String permalink) {
        this.permalink = permalink;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public Boolean getAcceptsMercadoPago() {
        return acceptsMercadoPago;
    }

    public void setAcceptsMercadoPago(Boolean acceptsMercadoPago) {
        this.acceptsMercadoPago = acceptsMercadoPago;
    }


    public Remessa getShipping() {
        return shipping;
    }

    public void setShipping(Remessa shipping) {
        this.shipping = shipping;
    }


    public Double getDifferentialPrincing() {
        return differentialPrincing;
    }

    public void setDifferentialPrincing(Double differentialPrincing) {
        this.differentialPrincing = differentialPrincing;
    }

    public BigDecimal getOriginalPrice() {
        return originalPrice;
    }

    public void setOriginalPrice(BigDecimal originalPrice) {
        this.originalPrice = originalPrice;
    }



    @Override
    public String toString() {

        return title;
    }

    public Vendedor getSeller() {
        return seller;
    }

    public void setSeller(Vendedor seller) {
        this.seller = seller;
    }

    public Parcela getInstallments() {
        return installments;
    }

    public void setInstallments(Parcela installments) {
        this.installments = installments;
    }

    public Endereco getAddress() {
        return address;
    }

    public void setAddress(Endereco address) {
        this.address = address;
    }

    public EnderecoVendedor getSellerAddress() {
        return sellerAddress;
    }

    public void setSellerAddress(EnderecoVendedor sellerAddress) {
        this.sellerAddress = sellerAddress;
    }

    public List<Atributo> getAttributes() {
        return attributes;
    }

    public void setAttributes(List<Atributo> attributes) {
        this.attributes = attributes;
    }

    public Review getReview() {
        return review;
    }

    public void setReview(Review review) {
        this.review = review;
    }

    public Tag getTags() {
        return tags;
    }

    public void setTags(Tag tags) {
        this.tags = tags;
    }

    public InputStream getThumbnailIS() {
        return thumbnailIS;
    }

    public void setThumbnailIS(InputStream thumbnailIS) {
        this.thumbnailIS = thumbnailIS;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public Integer getStockMin() {
        return stockMin;
    }

    public void setStockMin(Integer stockMin) {
        this.stockMin = stockMin;
    }

    public Integer getStockIdeal() {
        return stockIdeal;
    }

    public void setStockIdeal(Integer stockIdeal) {
        this.stockIdeal = stockIdeal;
    }

    public BigDecimal getLucroEsperado() {
        return lucroEsperado;
    }

    public void setLucroEsperado(BigDecimal lucroEsperado) {
        this.lucroEsperado = lucroEsperado;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIdentificacao() {
        return identificacao;
    }

    public void setIdentificacao(String identificacao) {
        this.identificacao = identificacao;
        this.sku = identificacao;
    }

    public Integer getId() {
        return id;
    }

    public BigDecimal getValorDeCompra() {
        return valorDeCompra;
    }

    public void setValorDeCompra(BigDecimal valorDeCompra) {
        this.valorDeCompra = valorDeCompra;
    }

    public BigDecimal getValorDeVenda() {
        return valorDeVenda;
    }

    public void setValorDeVenda(BigDecimal valorDeVenda) {
        this.valorDeVenda = valorDeVenda;
    }

    public Integer getQteDisponivel() {
        return qteDisponivel;
    }

    public void setQteDisponivel(Integer qteDisponivel) {
        this.qteDisponivel = qteDisponivel;
    }

    public Integer getTotalVendido() {
        return totalVendido;
    }

    public void setTotalVendido(Integer totalVendido) {
        this.totalVendido = totalVendido;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }
}
