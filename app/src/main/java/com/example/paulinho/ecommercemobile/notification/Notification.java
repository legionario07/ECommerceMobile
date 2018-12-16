package com.example.paulinho.ecommercemobile.notification;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Vibrator;
import android.support.v4.app.NotificationCompat;

import com.example.paulinho.ecommercemobile.R;
import com.example.paulinho.ecommercemobile.model.Produto;
import com.example.paulinho.ecommercemobile.utils.ConstraintUtils;
import com.example.paulinho.ecommercemobile.views.MainActivity;

import static android.content.Context.VIBRATOR_SERVICE;

public class Notification {

    private static NotificationManager mNotificationManager;

    public static void criarNotificacao(Context context, String mensagem, Produto produto) {

        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, new Intent(context, MainActivity.class), 0);

        NotificationCompat.Builder builder =  new NotificationCompat.Builder(context, "notify_001")
                .setTicker(ConstraintUtils.ECOMMERCE_MOBILE)
                .setContentTitle(produto.getTitle())
                .setContentText(mensagem)
                .setAutoCancel(true)
                .setContentIntent(pendingIntent);

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            builder.setSmallIcon(R.mipmap.ic_launcher);
            builder.setColor(NotificationCompat.COLOR_DEFAULT);
        } else {
            builder.setSmallIcon(R.mipmap.ic_launcher);
        }

        mNotificationManager =
                (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            String channelId = "YOUR_CHANNEL_ID";
            NotificationChannel channel = new NotificationChannel(channelId,
                    "Channel human readable title",
                    NotificationManager.IMPORTANCE_DEFAULT);
            mNotificationManager.createNotificationChannel(channel);
            builder.setChannelId(channelId);
        }


        android.app.Notification not = builder.build();
        not.flags = android.app.Notification.FLAG_AUTO_CANCEL;

        Vibrator vibrator = (Vibrator) context.getSystemService(VIBRATOR_SERVICE);
        long milliseconds = 500;
        vibrator.vibrate(milliseconds);

        mNotificationManager.notify(1, not);

    }


}
