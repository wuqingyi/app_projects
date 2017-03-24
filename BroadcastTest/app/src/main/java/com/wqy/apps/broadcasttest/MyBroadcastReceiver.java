package com.wqy.apps.broadcasttest;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class MyBroadcastReceiver extends BroadcastReceiver {
	public MyBroadcastReceiver() {
	}

	@Override
	public void onReceive(Context context, Intent intent) {
		Toast.makeText(context, "Received my Broadcast!", Toast.LENGTH_SHORT).show();
		abortBroadcast();
	}
}
