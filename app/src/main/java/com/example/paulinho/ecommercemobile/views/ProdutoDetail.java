package com.example.paulinho.ecommercemobile.views;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.paulinho.ecommercemobile.R;
import com.example.paulinho.ecommercemobile.api.services.impl.ProdutoServicesImpl;
import com.example.paulinho.ecommercemobile.model.Produto;
import com.example.paulinho.ecommercemobile.utils.ConstraintUtils;
import com.example.paulinho.ecommercemobile.utils.SessionUtil;

import java.math.BigDecimal;

public class ProdutoDetail extends AppCompatActivity {

    private TextView txtTitle;
    private EditText inpQtdeDisponivel;
    private EditText inpStockIdeal;
    private EditText inpQtdeVendidos;
    private EditText inpPrecoCompra;
    private EditText inpPrecoVenda;

    private Produto produto;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_produto_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fabSave);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                salvar();
            }
        });

        txtTitle = findViewById(R.id.txtTitle);
        inpQtdeDisponivel = findViewById(R.id.inpQtdeDisponivel);
        inpStockIdeal = findViewById(R.id.inpStockIdeal);
        inpQtdeVendidos = findViewById(R.id.inpQtdeVendidos);
        inpPrecoCompra = findViewById(R.id.inpPrecoCompra);
        inpPrecoVenda = findViewById(R.id.inpPrecoVenda);

        produto = SessionUtil.getInstance().getProduto();

        if (produto != null) {
            getSupportActionBar().setTitle(produto.getIdentificacao());
            setDataInView();
        }

        if(savedInstanceState!=null){
            inpStockIdeal.setText(savedInstanceState.getString("inpStockIdeal"));
            inpPrecoCompra.setText(savedInstanceState.getString("inpPrecoCompra"));
        }

    }

    private void salvar() {

        getDataInView();

        ProdutoServicesImpl produtoServices = new ProdutoServicesImpl();
        produto = produtoServices.save(produto);

        if(produto!=null){
            Toast.makeText(this, "Salvo com sucesso",Toast.LENGTH_LONG).show();
            finish();
        }else{
            Toast.makeText(this, "Erro ao salvar produto",Toast.LENGTH_LONG).show();

        }

    }

    private void getDataInView(){
        produto.setStockIdeal(Integer.valueOf(inpStockIdeal.getText().toString()));
        produto.setValorDeCompra(new BigDecimal(inpPrecoCompra.getText().toString()));
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putString("inpStockIdeal", inpStockIdeal.getText().toString());
        outState.putString("inpPrecoCompra", inpPrecoCompra.getText().toString());

    }

    private void setDataInView() {

        txtTitle.setText(produto.getTitle());
        inpQtdeDisponivel.setText(String.valueOf(produto.getAvailableQuantity().intValue()));
        Integer stockMin = produto.getStockMin();

        Integer stockIdeal = produto.getStockIdeal();
        if (stockIdeal != null) {
            inpStockIdeal.setText(String.valueOf(stockIdeal.intValue()));
        } else {
            inpStockIdeal.setText(BigDecimal.ZERO.toString());
        }

        BigDecimal precoCompra = produto.getValorDeCompra();
        if (precoCompra != null) {
            inpPrecoCompra.setText(precoCompra.toString());
        } else {
            inpPrecoCompra.setText(BigDecimal.ZERO.toString());
        }
        inpQtdeVendidos.setText(String.valueOf(produto.getSoldQuantity().intValue()));
        inpPrecoVenda.setText(produto.getPrice().toString());
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    @Override
    public void finish() {
        super.finish();
        SessionUtil.getInstance().clear();
    }
}
