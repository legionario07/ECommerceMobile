package com.example.paulinho.ecommercemobile.views;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.paulinho.ecommercemobile.R;
import com.example.paulinho.ecommercemobile.adapters.ConfiguracoesAdapter;
import com.example.paulinho.ecommercemobile.adapters.ProdutosAdapter;
import com.example.paulinho.ecommercemobile.api.RetrofitConfig;
import com.example.paulinho.ecommercemobile.api.services.ConfiguracaoServices;
import com.example.paulinho.ecommercemobile.api.services.impl.ConfiguracaoServicesImpl;
import com.example.paulinho.ecommercemobile.api.services.impl.ProdutoServicesImpl;
import com.example.paulinho.ecommercemobile.model.Configuracao;
import com.example.paulinho.ecommercemobile.model.Produto;
import com.example.paulinho.ecommercemobile.utils.ConstraintUtils;
import com.example.paulinho.ecommercemobile.utils.SessionUtil;

import java.util.List;

import retrofit2.Retrofit;
import rx.Observer;
import rx.schedulers.Schedulers;

public class ConfiguracaoActivity extends AppCompatActivity {

    private List<Configuracao> configuracoes;
    private TextView txtNoData;
    private RecyclerView recViewConfiguracao;
    private ConfiguracoesAdapter configuracoesAdapter;
    private Configuracao configuracao;

    private LayoutInflater inflater;
    private AlertDialog alert;
    private AlertDialog dialog;
    private AlertDialog.Builder dialogBuilder;

    private EditText inpPropriedade;
    private EditText inpValor;

    private ConfiguracaoServicesImpl configuracaoServices;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuracao);

        recViewConfiguracao = findViewById(R.id.recViewConfiguracoes);
        txtNoData = findViewById(R.id.txtNoData);

        FloatingActionButton fabConfiguracao = findViewById(R.id.fabNovaConfiguracao);
        fabConfiguracao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                criarDialogCRUD(ConstraintUtils.CADASTRAR);
            }
        });

        configuracoes = SessionUtil.getInstance().getConfiguracoes();

        atualizarDados();

        if (configuracoes.isEmpty()) {
            recViewConfiguracao.setVisibility(View.GONE);
            txtNoData.setVisibility(View.VISIBLE);
        }
        else {
            recViewConfiguracao.setVisibility(View.VISIBLE);
            txtNoData.setVisibility(View.GONE);
        }

        LinearLayoutManager layoutManager
                = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        recViewConfiguracao.setLayoutManager(layoutManager);
        recViewConfiguracao.setAdapter(configuracoesAdapter);

        configuracoesAdapter.setOnItemClickListener(new ConfiguracoesAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                configuracao = configuracoes.get(position);

            }
        });

        registerForContextMenu(recViewConfiguracao);
        configuracaoServices = new ConfiguracaoServicesImpl();

    }

    private void getConfiguracoes(){

        Retrofit retrofit = RetrofitConfig.getBuilderWS(true);
        ConfiguracaoServices services = retrofit.create(ConfiguracaoServices.class);


        services.findAll().subscribeOn(Schedulers.newThread())
                .subscribe(new Observer<List<Configuracao>>() {
                    @Override
                    public void onCompleted() {
                        Log.i(ConstraintUtils.LOG, "completed");
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(List<Configuracao> configs) {
                        SessionUtil.getInstance().setConfiguracoes(configs);
                        for (Configuracao c : configs) {
                            SessionUtil.getInstance().getMapConfiguraces().put(c.getPropriedade(), c.getValor());
                        }

                        configuracoes = SessionUtil.getInstance().getConfiguracoes();
                        atualizarDados();
                    }
                });


    }

    private void atualizarDados() {


        if (configuracoesAdapter == null) {
            configuracoesAdapter = new ConfiguracoesAdapter(this, configuracoes);
        } else {
            configuracoesAdapter.updateList(configuracoes);
        }


    }


    private void criarDialogCRUD(final String operacao) {

        dialogBuilder = new AlertDialog.Builder(this);

        inflater = getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.dialog_nova_configuracao, null);
        dialogBuilder.setView(dialogView);
        dialogBuilder.setTitle(operacao.toUpperCase());

        inpPropriedade =  dialogView.findViewById(R.id.inpPropriedade);
        inpValor = dialogView.findViewById(R.id.inpValor);

        //Se não for CADASTRAR preenche o Dialog
        if (!operacao.equals(ConstraintUtils.CADASTRAR)) {

            inpValor.setText(configuracao.getValor());
            inpPropriedade.setText(configuracao.getPropriedade());

        }

        dialogBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {


                //Foi validado?
                String mensagem = validar();

                if (mensagem.length() > 0) {
                    Toast.makeText(getApplicationContext(), mensagem, Toast.LENGTH_SHORT).show();
                    return;
                }

                configuracao = new Configuracao();
                configuracao.setValor(inpValor.getText().toString());
                configuracao.setPropriedade(inpPropriedade.getText().toString());

                if(operacao.equalsIgnoreCase(ConstraintUtils.CADASTRAR)){
                    configuracaoServices.save(configuracao);
                }

                try {
                    Thread.sleep(500l);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                atualizarDados();

            }
        })


                .setNegativeButton("CANCELAR", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                        return;

                    }
                });

        dialog = dialogBuilder.create();
        dialog.show();

    }

    private String validar(){
        StringBuilder erro = new StringBuilder();

        if(inpPropriedade.getText().toString().isEmpty()){
            erro.append("Propriedade não pode ser vazia");
        }else if(inpValor.getText().toString().isEmpty()){
            erro.append("Valor não pode ser vazio");
        }

        return erro.toString();
    }


    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        getMenuInflater().inflate(R.menu.menu_contextual, menu);
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_editar:

                break;

            case R.id.menu_excluir:
                break;
        }
        return super.onContextItemSelected(item);
    }

}
