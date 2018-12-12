package com.example.paulinho.ecommercemobile.views;

import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.paulinho.ecommercemobile.R;
import com.example.paulinho.ecommercemobile.model.Produto;
import com.example.paulinho.ecommercemobile.utils.ConstraintUtils;
import com.example.paulinho.ecommercemobile.utils.SessionUtil;

import java.math.BigDecimal;

public class ProdutoDetail extends AppCompatActivity {

    private TextView txtTitle;
    private EditText inpQtdeDisponivel;
    private EditText inpStockMin;
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


        txtTitle = findViewById(R.id.txtTitle);
        inpQtdeDisponivel = findViewById(R.id.inpQtdeDisponivel);
        inpStockMin = findViewById(R.id.inpStockMin);
        inpStockIdeal = findViewById(R.id.inpStockIdeal);
        inpQtdeVendidos = findViewById(R.id.inpQtdeVendidos);
        inpPrecoCompra = findViewById(R.id.inpPrecoCompra);
        inpPrecoVenda = findViewById(R.id.inpPrecoVenda);

        produto = SessionUtil.getInstance().getProduto();

        if (produto != null) {
            setDataInView();
        }

    }

    private void setDataInView() {
        txtTitle.setText(produto.getIdentificacao());
        inpQtdeDisponivel.setText(String.valueOf(produto.getAvailableQuantity().intValue()));
        Integer stockMin = produto.getStockMin();
        if (stockMin != null) {
            inpStockMin.setText(String.valueOf(stockMin.intValue()));
        } else {
            inpStockMin.setText(BigDecimal.ZERO.toString());
        }

        Integer stockIdeal = produto.getStockIdeal();
        if (stockIdeal != null) {
            inpStockIdeal.setText(String.valueOf(stockIdeal.intValue()));
        } else {
            inpStockIdeal.setText(BigDecimal.ZERO.toString());
        }

        BigDecimal precoCompra = produto.getValorDeCompra();
        if (precoCompra != null) {
            inpStockIdeal.setText(precoCompra.toString());
        } else {
            inpStockIdeal.setText(BigDecimal.ZERO.toString());
        }
        inpQtdeVendidos.setText(String.valueOf(produto.getSoldQuantity().intValue()));
        inpPrecoVenda.setText(produto.getPrice().toString());
    }

}
