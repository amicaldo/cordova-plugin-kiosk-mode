package de.amicaldo.cordova.plugin;

import de.amicaldo.cordova.plugin.KioskModeActivity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;


public class MyPackageReplacedEventReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Intent newIntent = new Intent(context, KioskModeActivity.class);

        newIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        context.startActivity(newIntent);
    }
}
