package com.example.paulinho.ecommercemobile.views;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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
import com.example.paulinho.ecommercemobile.api.services.ConfiguracaoServices;
import com.example.paulinho.ecommercemobile.api.services.impl.ProdutoServicesImpl;
import com.example.paulinho.ecommercemobile.model.Configuracao;
import com.example.paulinho.ecommercemobile.model.Produto;
import com.example.paulinho.ecommercemobile.utils.ConstraintUtils;
import com.example.paulinho.ecommercemobile.utils.SessionUtil;

import java.util.List;

public class ConfiguracaoActivity extends AppCompatActivity {

    private List<Configuracao> configuracoes;
    private TextView txtNoData;
    private ConfiguracaoServices configuracaoServices;
    private RecyclerView recViewConfiguracao;
    private ConfiguracoesAdapter configuracoesAdapter;
    private Configuracao configuracao;

    private LayoutInflater inflater;
    private AlertDialog alert;
    private AlertDialog dialog;
    private AlertDialog.Builder dialogBuilder;

    private EditText inpPropriedade;
    private EditText inpValor;

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

    }


    private void atualizarDados() {


        if (configuracoesAdapter == null) {
            configuracoesAdapter = new ConfiguracoesAdapter(this, configuracoes);
        } else {
            configuracoesAdapter.updateList(configuracoes);
        }


    }

    private void showDialog(String opcao){

//        final AlertDialog.Builder dialogCrud = new AlertDialog.Builder(this);
//
//        LayoutInflater inflater = getLayoutInflater();
//        final View dialogView = inflater.inflate(R.layout.dialog_nova_configuracao, null);
//        dialogCrud.setView(dialogView);
//        dialogCrud.setTitle(opcao);
//
//        final View.OnClickListener criarDialog = new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
////                if (imgButtonNewCrud.getId() == view.getId()) {
////                    furo = new Furo();
////                    criarDialogCRUD(IFlagment.CADASTRAR);
////                } else if (imgButtonDeleteCrud.getId() == view.getId()) {
////                    criarDialogCRUD(IFlagment.DELETAR);
////                } else {
////                    criarDialogCRUD(IFlagment.EDITAR);
////                }
////
////                alert.dismiss();
//            }
//
//        };
//
//        imgButtonNewCrud =  dialogView.findViewById(R.id.imgButtonNewCrud);
//        imgButtonDeleteCrud =  dialogView.findViewById(R.id.imgButtonDeleteCrud);
//        imgButtonEditCrud =  dialogView.findViewById(R.id.imgButtonEditCrud);
//
//        imgButtonNewCrud.setOnClickListener(criarDialog);
//        imgButtonDeleteCrud.setOnClickListener(criarDialog);
//        imgButtonEditCrud.setOnClickListener(criarDialog);
//
//        dialogCrud.setNegativeButton("CANCELAR", new DialogInterface.OnClickListener()
//
//        {
//            @Override
//            public void onClick(DialogInterface dialogInterface, int i) {
//                dialogInterface.cancel();
//                return;
//            }
//        });
//
//        alert = dialogCrud.create();
//        alert.show();
//


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

//
//                if (operacao.equals(ConstraintUtils.EXCLUIR)) {
//                    foiPersistido = ServicesImpl.delete(new FuroServiceImpl(), furo.getId());
//                } else {
//
//                    furo = (Furo) ServicesImpl.save(new FuroServiceImpl(), furo);
//
//                    if(furo!=null){
//                        foiPersistido = true;
//                    }
//
//                }
//
//                if (foiPersistido) {
//                    ServicesImpl.findAll(new FuroServiceImpl());
//                    try {
//                        Thread.sleep(TEMPO_SLEEP);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//
//                    atualizarListView();
//                    Toast.makeText(getApplicationContext(), "Operação realizada com sucesso", Toast.LENGTH_LONG).show();
//                } else {
//                    Toast.makeText(getApplicationContext(), "Operação não pode ser realizada", Toast.LENGTH_LONG).show();
//                }

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
        return "";
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
