package com.example.paulinho.ecommercemobile.converters;

import com.example.paulinho.ecommercemobile.model.Atributo;
import com.example.paulinho.ecommercemobile.model.DataForUser;
import com.example.paulinho.ecommercemobile.model.Endereco;
import com.example.paulinho.ecommercemobile.model.Item;
import com.example.paulinho.ecommercemobile.model.Parcela;
import com.example.paulinho.ecommercemobile.model.Produto;
import com.example.paulinho.ecommercemobile.model.Remessa;
import com.example.paulinho.ecommercemobile.model.Review;
import com.example.paulinho.ecommercemobile.model.Tag;
import com.example.paulinho.ecommercemobile.model.Vendedor;
import com.example.paulinho.ecommercemobile.utils.ConstraintUtils;
import com.google.gson.internal.LinkedTreeMap;
import com.google.gson.internal.bind.ObjectTypeAdapter;

import java.io.InputStream;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Set;

public class ConverterToProduto {

    private static Vendedor getVendedor(LinkedTreeMap<String, String> object) {
        Vendedor vendedor = new Vendedor();
        String valor = String.valueOf(object.get("id"));
        vendedor.setId(new Double(valor));
        try {
            vendedor.setPowerSellerStatus(object.get("power_seller_status"));
            vendedor.setRealEstateAgency(Boolean.valueOf(object.get("id")));
            vendedor.setCarDealer(Boolean.valueOf(object.get("car_dealer")));
        } catch (Exception e) {

        }
        vendedor.setTags(new ArrayList<Tag>());

        return vendedor;
    }

    private static Parcela getParcela(LinkedTreeMap<String, String> object) {
        Parcela parcela = new Parcela();

        if(object==null){
            return new Parcela();
        }

        String quantity = String.valueOf(object.get("quantity"));
        if(quantity==null){
            quantity = "0";
        }
        parcela.setAmount(new BigDecimal(quantity));

        String rate = String.valueOf(object.get("rate"));
        parcela.setRate(new BigDecimal(rate));

        parcela.setCurrencyId(object.get("currency_id"));

        return parcela;
    }

    private static Endereco getEndereco(LinkedTreeMap<String, String> object) {

        Endereco endereco = new Endereco();
        endereco.setStateId(object.get("state_id"));
        endereco.setStateName(object.get("state_name"));
        endereco.setCityId(object.get("city_id"));
        endereco.setCityName(object.get("city_name"));

        return endereco;
    }

    private static Remessa getRemessa(LinkedTreeMap<String, String> object) {

        Remessa remessa = new Remessa();
        String freeShipping = String.valueOf(object.get("free_shipping"));
        remessa.setFreeShipping(new Boolean(freeShipping));

        remessa.setMode(object.get("mode"));
        remessa.setDropOff(object.get("drop_off"));

        String storePickUp = String.valueOf(object.get("store_pick_up"));
        remessa.setStorePickUp(new Boolean(storePickUp));

        return remessa;
    }

    private static List<Atributo> getAtributo(List<Object> objects) {


        List<Atributo> atributos  = new ArrayList<>();


        for(Object object : objects){
            LinkedTreeMap<String, Object> mapObject = (LinkedTreeMap<String, Object>) object;
            Set<String> keys = mapObject.keySet();
            Atributo atributo = new Atributo();
            for(String key : keys){

                LinkedTreeMap<String, Object> mapObjectInterno = (LinkedTreeMap<String, Object>) object;
                Set<String> keysInterno = mapObjectInterno.keySet();
                for(String keyInterno : keysInterno){
                    atributo.getMapAtributos().put(keyInterno, mapObjectInterno.get(keyInterno));

                }

            }

            atributos.add(atributo);

        }


        return atributos;
    }

    private static Review getReview(LinkedTreeMap<String, String> object) {

        Review review = new Review();

        try {
            String ratingAverage = String.valueOf(object.get("rating_average"));
            review.setRatingAverage(new BigDecimal(ratingAverage));
            String total = String.valueOf(object.get("total"));
            review.setTotal(new BigDecimal(total));
        }catch (Exception e){
            String teste;
        }




        return review;
    }


    public static Produto converterToProduto(LinkedTreeMap<String, Object> object) {
        Produto produto = new Produto();
        produto.setIdentificacao(object.get("id").toString());
        produto.setSiteId(object.get("site_id").toString());
        produto.setTitle(object.get("title").toString());
        produto.setSeller(getVendedor((LinkedTreeMap<String, String>) (Object) object.get("seller")));
        String price = String.valueOf(object.get("price")).trim();
        produto.setPrice(new BigDecimal(price.trim()));
        produto.setCurrencyId(object.get("currency_id").toString().trim());

        String availableQuantity = String.valueOf(object.get("available_quantity")).trim();
        BigDecimal availableQuantityDecimal = new BigDecimal(availableQuantity);
        produto.setAvailableQuantity(availableQuantityDecimal);

        String soldQuantity = String.valueOf(object.get("sold_quantity")).trim();
        produto.setSoldQuantity(new BigDecimal(soldQuantity));

        produto.setBuyingMode(object.get("buying_mode").toString());
        produto.setListingTypeId(object.get("listing_type_id").toString());

        Calendar stopTime = Calendar.getInstance();
        String stopTimeStr = object.get("stop_time").toString();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        try {
            stopTime.setTime(sdf.parse(stopTimeStr));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        produto.setStopTime(stopTime);
        produto.setCondition(object.get("condition").toString());
        produto.setPermalink(object.get("permalink").toString());
        produto.setThumbnail(object.get("thumbnail").toString());
        produto.setThumbnailIS((InputStream) object.get(ConstraintUtils.THUMBNAIL_IS));
       // produto.setItem((Item) object.get(ConstraintUtils.ITEM));

        String acceptsMercadoPago = String.valueOf(object.get("accepts_mercadopago"));
        produto.setAcceptsMercadoPago(new Boolean(acceptsMercadoPago));
        produto.setInstallments(getParcela((LinkedTreeMap<String, String>) (Object) object.get("installments")));

        produto.setAddress(getEndereco((LinkedTreeMap<String, String>) (Object) object.get("address")));

        produto.setShipping(getRemessa((LinkedTreeMap<String, String>) (Object) object.get("shipping")));
//        produto.setSellerAddress(Integer.valueOf(object.get("seller_address").replace("size = ","").trim()));
        produto.setAttributes(getAtributo((List<Object>) (Object) object.get("attributes")));

        String differentalPrincing = "";
        try {
            differentalPrincing = String.valueOf(((LinkedTreeMap<String, String>) (Object) object.get("differential_pricing")).get("id"));

        }catch (Exception e){

        }
        if(differentalPrincing!=null && !differentalPrincing.isEmpty()){
            produto.setDifferentialPrincing(new Double(differentalPrincing));
        }


        String originalPrice = String.valueOf(object.get("original_price"));
        if (originalPrice == null || originalPrice.equals("null")) {
            produto.setOriginalPrice(BigDecimal.ZERO);
        } else {
            produto.setOriginalPrice(new BigDecimal(originalPrice));
        }

        produto.setCategoryId(object.get("category_id").toString());
        produto.setOfficialStoreId((String) object.get("official_store_id") != null  ? (String) object.get("official_store_id").toString() : "");
        produto.setCatalogProductId((String) object.get("catalog_product_id"));
        produto.setReview(getReview((LinkedTreeMap<String, String>) (Object) object.get("reviews")));
//      produto.setTags(Integer.valueOf(object.get("tags").replace("size = ","").trim()));


        return produto;
    }

    public static List<Produto> getProductsForDataForUser(DataForUser dataForUser) {
        List<Produto> produtos = new ArrayList<>();
        for (Object object : dataForUser.getResults()) {
            LinkedTreeMap<String, Object> mapObject = (LinkedTreeMap<String, Object>) object;
            produtos.add(converterToProduto(mapObject));
        }

        return produtos;
    }
}
