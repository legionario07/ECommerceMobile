package com.example.paulinho.ecommercemobile.notification;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import com.example.paulinho.ecommercemobile.api.services.impl.MBLServicesImpl;
import com.example.paulinho.ecommercemobile.api.services.impl.ProdutoServicesImpl;
import com.example.paulinho.ecommercemobile.converters.ConverterToProduto;
import com.example.paulinho.ecommercemobile.model.DataForUser;
import com.example.paulinho.ecommercemobile.model.Produto;
import com.example.paulinho.ecommercemobile.utils.ConstraintUtils;
import com.example.paulinho.ecommercemobile.utils.SessionUtil;
import com.example.paulinho.ecommercemobile.utils.VerificaConexaoStrategy;

import java.util.List;

public class ProdutoNotificacaoService extends Service {

    private int startId;
    public static boolean ativo = false;
    private Context context;

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        Log.i(ConstraintUtils.LOG, "Criando service NotificacaoService");
        super.onCreate();


        startId = -1;
        if (ativo) {
            return;
        }
        ativo = true;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        this.startId = startId;

        context = getBaseContext();

        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {

                while(ativo){

                    try {
                        Thread.sleep(9000l);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    if(VerificaConexaoStrategy.verificarConexao(context)) {

                        Log.i(ConstraintUtils.LOG, "Verificando Produtos");

                        MBLServicesImpl services = new MBLServicesImpl();
                        DataForUser dados = services.getDataForUser(ConstraintUtils.CODIGO_CLIENTE);

                        List<Produto> produtos = ConverterToProduto.getProductsForDataForUser(dados);
                        SessionUtil.getInstance().setProdutos(produtos);
                        for(Produto p : produtos){
                            p = new ProdutoServicesImpl().findBySku(p, p.getSku());
                        }

                        for(Produto p : produtos) {
                            if((p.getQteDisponivel() !=null && p.getStockIdeal() !=null) && p.getQteDisponivel()<=p.getStockIdeal()) {
                                Log.i(ConstraintUtils.LOG, "Criando notificacao: "+ p.getIdentificacao());
                                Notification.criarNotificacao(context, "Produto com estoque baixo", p);
                            }
                        }
                    }

                    try {
                        Thread.sleep(3_600_000);
                    } catch (InterruptedException e) {
                        Log.e(ConstraintUtils.LOG,e.getMessage());
                        e.printStackTrace();
                    }

                }

            }
        });
        t.start();

        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        stopSelf(startId);
        ativo = false;

    }
}
