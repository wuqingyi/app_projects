package com.wqy.apps.notificationtest;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.NotificationCompat;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		Button button = (Button) findViewById(R.id.button);
		button.setOnClickListener(this);

	}


	@Override
	public void onClick(View v) {
		NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
		Intent intent = new Intent(this, NoticeActivity.class);
		PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);
		Notification notification = new NotificationCompat.Builder(this)
				.setContentTitle("Notification Test")
				.setStyle(new NotificationCompat.BigTextStyle()
						.bigText("This is a very very very very very very very very " +
								"very very very very very very very very very very very " +
								"very very very very very very  BIG text"))
				.setWhen(System.currentTimeMillis())
				.setSmallIcon(R.mipmap.ic_launcher)
				.setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher))
				.setContentIntent(pendingIntent)
				.setAutoCancel(true)
//				.setVibrate(new long[]{0, 1000, 1000, 1000})
//				.setLights(Color.GREEN, 1000, 1000)
				.setDefaults(NotificationCompat.DEFAULT_ALL)
				.build();
		manager.notify(1, notification);
	}
}
