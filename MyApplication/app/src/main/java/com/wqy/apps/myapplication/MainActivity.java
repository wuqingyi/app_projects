package com.wqy.apps.myapplication;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		if (ActivityCompat.checkSelfPermission(this, Intent.ACTION_CALL) != PackageManager.PERMISSION_GRANTED) {
			ActivityCompat.requestPermissions(this, new String[]{Intent.ACTION_CALL}, 1);
		} else {
			NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
			Notification.Builder builder = new Notification.Builder(this);
			builder.setContentTitle("hello").setContentText("world").setSmallIcon(R.mipmap.ic_launcher)
					.setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher));
			notificationManager.notify(1, builder.build());
		}
	}

	@Override
	public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
		super.onRequestPermissionsResult(requestCode, permissions, grantResults);
	}
}
