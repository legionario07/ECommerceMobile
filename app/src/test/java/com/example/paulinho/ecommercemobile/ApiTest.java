package com.example.paulinho.ecommercemobile;

import com.example.paulinho.ecommercemobile.converters.ConverterToProduto;
import com.example.paulinho.ecommercemobile.api.services.impl.MBLServicesImpl;
import com.example.paulinho.ecommercemobile.model.DataForUser;
import com.example.paulinho.ecommercemobile.model.Produto;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.List;

@RunWith(JUnit4.class)
public class ApiTest {

    @Test
    public void shouldReturnsApi(){

        MBLServicesImpl services = new MBLServicesImpl();
        DataForUser dados = services.getDataForUser(169517012);

        List<Produto> produtos = ConverterToProduto.getProductsForDataForUser(dados);

        Assert.assertNotNull(dados);


    }

}
