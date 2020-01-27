package com.example.stress_meter;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Created by varun on 1/20/15.
 */

public class EMAAlarmReceiver extends BroadcastReceiver {
    //Receive broadcast
    @Override
    public void onReceive(final Context context, Intent intent) {
        Log.d("jmacdonald", "Got information");
        startPSM(context);
    }

    // start the stress meter
    private void startPSM(Context context) {
        Log.d("jmacdonald", "Received information");
        Intent emaIntent = new Intent(context, MainActivity.class); //The activity you  want to start.
        emaIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(emaIntent);
    }
}