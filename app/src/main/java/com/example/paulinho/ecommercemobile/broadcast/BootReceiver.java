package com.example.paulinho.ecommercemobile.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

import com.example.paulinho.ecommercemobile.notification.ProdutoNotificacaoService;
import com.example.paulinho.ecommercemobile.utils.ConstraintUtils;
import com.example.paulinho.ecommercemobile.utils.ServiceUtil;

public class BootReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        /**
         * Verifica se o Service já esta iniciado
         */
        if(!ServiceUtil.isRunningService(context, ConstraintUtils.CLASS_NAME_PARTIDA_NOTIFICACAO)){
            Intent i = new Intent(context, ProdutoNotificacaoService.class);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                context.startForegroundService(i);
            }else{
                context.startService(i);
            }

        }

    }
}
