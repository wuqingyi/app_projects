package com.wqy.apps.servicetest;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.util.Log;

public class MyService extends Service {

	private static final String TAG = "player";
	private MediaPlayer player = new MediaPlayer();

	public MyService() {
	}

	@Override
	public void onCreate() {
		super.onCreate();
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		int method = intent.getIntExtra("method", -1);
		switch (method) {
			case 0:
				Log.d(TAG, "pre");
				break;
			case 1:
				Log.d(TAG, "play");
				break;
			case 2:
				Log.d(TAG, "stop");
				break;
			case 3:
				Log.d(TAG, "next");
				break;
			default:
				Log.d(TAG, "error");
				break;
		}
		return super.onStartCommand(intent, flags, startId);
	}

	@Override
	public IBinder onBind(Intent intent) {
		// TODO: Return the communication channel to the service.
		throw new UnsupportedOperationException("Not yet implemented");
	}
}
