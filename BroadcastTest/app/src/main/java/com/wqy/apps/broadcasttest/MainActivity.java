package com.wqy.apps.broadcasttest;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
	private ConnChargeReceiver connChargeReceiver;
	private Button btn_broadcast;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		btn_broadcast = (Button)findViewById(R.id.btn_broadcast);
		btn_broadcast.setOnClickListener(this);
		IntentFilter intentFilter = new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE");
		connChargeReceiver = new ConnChargeReceiver();
		registerReceiver(connChargeReceiver, intentFilter);
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		unregisterReceiver(connChargeReceiver);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()){
			case R.id.btn_broadcast:
				Intent intent = new Intent("com.wqy.apps.broadcasttest.MY_BROADCAST");
				//sendBroadcast(intent);
				sendOrderedBroadcast(intent, null);
				break;
		}
	}
}


class ConnChargeReceiver extends BroadcastReceiver {
	@Override
	public void onReceive(Context context, Intent intent) {
		ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
		if (networkInfo != null && networkInfo.isAvailable())
			Toast.makeText(context, "Network is Available!", Toast.LENGTH_SHORT).show();
		else
			Toast.makeText(context, "Network is NOT Available!", Toast.LENGTH_SHORT).show();
	}
}