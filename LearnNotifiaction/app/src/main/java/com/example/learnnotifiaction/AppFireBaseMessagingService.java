package com.example.learnnotifiaction;

import android.app.PendingIntent;
import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class AppFireBaseMessagingService extends FirebaseMessagingService {
    public static int HANDLING_CLICK = 50;

    @Override
    public void onMessageReceived(@NonNull RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);

        if(remoteMessage.getNotification() != null){
            String title = remoteMessage.getNotification().getTitle();
            String text = remoteMessage.getNotification().getBody();
            displayNotification(title, text);
        }
    }

    private void displayNotification(String title, String text){
        Intent intent = new Intent(this, ProfileActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, HANDLING_CLICK, intent, PendingIntent.FLAG_CANCEL_CURRENT);

        // Notification Builder
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this, MainActivity.CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_baseline_event_note_24)
                .setContentText(text)
                .setContentTitle(title)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        // Notification Manager
        NotificationManagerCompat mNotificationMgr = NotificationManagerCompat.from(this);
        mNotificationMgr.notify(1, mBuilder.build());
    }
}


