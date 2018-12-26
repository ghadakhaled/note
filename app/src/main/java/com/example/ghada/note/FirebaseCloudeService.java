package com.example.ghada.note;


import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.util.Log;
import android.widget.Toast;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class FirebaseCloudeService extends FirebaseMessagingService
{

    @Override
    public void onNewToken(String s)
    {
        super.onNewToken(s);
        // Get updated InstanceID token.
        Log.i("First", "Refreshed token: " + s);

        // If you want to send messages to this application instance or
        // manage this apps subscriptions on the server side, send the
        // Instance ID token to your app server.
        sendRegistrationToServer(s);
    }

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage)
    {
        if (remoteMessage.getData()!=null &&remoteMessage.getData().size()>0)
        {
            Log.i("secound", "Datapayload size: " +remoteMessage.getData().size());
            String title = remoteMessage.getData().get("title");
            String message = remoteMessage.getData().get("message");
            String rideId = remoteMessage.getData().get("rideId");
            Log.i("secound", message );
            Log.i("secound", title );
            Log.i("secound", rideId );
            createNotificationChannel(rideId,title,message);
            Intent intent = new Intent(this, MainNote.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);
            NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this, rideId)
                    .setSmallIcon(R.drawable.notification_icon)
                    .setContentTitle(title)
                    .setContentText(message)
                    .setContentIntent(pendingIntent)
                    .setAutoCancel(true)
                    .setPriority(NotificationCompat.PRIORITY_HIGH);
            NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
            // notificationId is a unique int for each notification that you must define
            notificationManager.notify(Integer.parseInt(rideId), mBuilder.build());
        }
        super.onMessageReceived(remoteMessage);
    }

    private void createNotificationChannel(String rideId,String title,String message)
    {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
        {
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel channel = new NotificationChannel(rideId, title, importance);
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

    private void sendRegistrationToServer(String refreshedToken)
    {
    }


}