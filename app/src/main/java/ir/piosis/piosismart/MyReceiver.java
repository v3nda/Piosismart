package ir.piosis.piosismart;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.NotificationCompat;
import android.widget.Toast;

/**
 * Created by PIOSISLP on 6/2/2017.
 */

public class MyReceiver extends BroadcastReceiver {
    public MyReceiver() {
    }
    String message,sender;
    @Override
    public void onReceive(Context context, Intent intent) {
//        intent.getExtras().getString(UDPListenerService.UDP_BROADCAST);
        message=intent.getExtras().getString("message");
        sender=intent.getExtras().getString("sender");
        Toast.makeText(context, "boradcast receiver" +"\r\n"+intent.getExtras().getString("sender")+"\r\n"+ intent.getExtras().getString("message"), Toast.LENGTH_SHORT).show();
MainActivity.button.setText(intent.getExtras().getString("message"));
        Intent i = new Intent(context, MainActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

//        if (!MainActivity.activeMainActivityOrNot) {
//            context.startActivity(i);
//        }

        showNotification(context);

    }



    private void showNotification(Context context) {
        PendingIntent contentIntent = PendingIntent.getActivity(context, 0,
                new Intent(context, MainActivity.class), 0);

        Bitmap icon = BitmapFactory.decodeResource(context.getResources(),
                R.mipmap.ic_launcher);
        Bitmap scaledBitmap = Bitmap.createScaledBitmap(icon, 320, 320, true);

        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(context)
                        .setLargeIcon(scaledBitmap)
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setContentTitle(sender)
                        .setContentText(message);
        mBuilder.setContentIntent(contentIntent);
        mBuilder.setDefaults(Notification.DEFAULT_SOUND);
        mBuilder.setAutoCancel(true);
        NotificationManager mNotificationManager =
                (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        mNotificationManager.notify(1, mBuilder.build());

    }




}
