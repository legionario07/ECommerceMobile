package com.example.paulinho.ecommercemobile.views;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.example.paulinho.ecommercemobile.R;
import com.example.paulinho.ecommercemobile.adapters.ProdutosAdapter;
import com.example.paulinho.ecommercemobile.api.services.impl.MBLServicesImpl;
import com.example.paulinho.ecommercemobile.api.services.impl.ProdutoServicesImpl;
import com.example.paulinho.ecommercemobile.converters.ConverterToProduto;
import com.example.paulinho.ecommercemobile.model.DataForUser;
import com.example.paulinho.ecommercemobile.model.Produto;
import com.example.paulinho.ecommercemobile.notification.ProdutoNotificacaoService;
import com.example.paulinho.ecommercemobile.utils.ConstraintUtils;
import com.example.paulinho.ecommercemobile.utils.ServiceUtil;
import com.example.paulinho.ecommercemobile.utils.SessionUtil;
import com.example.paulinho.ecommercemobile.utils.VerificaConexaoStrategy;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private List<Produto> produtos;
    private ProdutosAdapter adapterProdutos;
    private ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

        StrictMode.setThreadPolicy(policy);

        recyclerView = findViewById(R.id.recyclerView);

        if(!VerificaConexaoStrategy.verificarConexao(this)){
            Toast.makeText(this, "Verifique sua conexão com a Internet",Toast.LENGTH_LONG).show();
        }

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        atualizarDados();

        LinearLayoutManager layoutManager
                = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setAdapter(adapterProdutos);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                atualizarDados();
            }
        });

        adapterProdutos.setOnItemClickListener(new ProdutosAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Produto produto = produtos.get(position);

                produto = new ProdutoServicesImpl().findBySku(produto, produto.getSku());

                SessionUtil.getInstance().clear();
                SessionUtil.getInstance().setProduto(produto);

                Intent it = new Intent(getApplicationContext(), ProdutoDetail.class);
                startActivity(it);

            }
        });

        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                if(!ServiceUtil.isRunningService(getApplicationContext(), ConstraintUtils.ECOMMERCE_MOBILE)){
                    Intent i = new Intent(getApplicationContext(), ProdutoNotificacaoService.class);
                    startService(i);
                }
            }
        });
        t.start();




    }


    private void atualizarDados(){
        MBLServicesImpl services = new MBLServicesImpl();
        DataForUser dados = services.getDataForUser(ConstraintUtils.CODIGO_CLIENTE);

        produtos = ConverterToProduto.getProductsForDataForUser(dados);

        if(adapterProdutos ==null){
            adapterProdutos = new ProdutosAdapter(this, produtos);
        }else{
            adapterProdutos.updateList(produtos);
        }


    }

}
