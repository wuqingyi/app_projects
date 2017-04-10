package com.wqy.apps.servicetest;

import android.Manifest;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
	private static final int PMS_WRITE_EXTERNAL_STORAGE = 1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);


		Button btn_pre = (Button) findViewById(R.id.btn_pre);
		Button btn_play = (Button) findViewById(R.id.btn_play);
		Button btn_stop = (Button) findViewById(R.id.btn_stop);
		Button btn_next = (Button) findViewById(R.id.btn_next);
		btn_pre.setOnClickListener(this);
		btn_play.setOnClickListener(this);
		btn_stop.setOnClickListener(this);
		btn_next.setOnClickListener(this);

		if (ActivityCompat.checkSelfPermission(this,
				Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
			ActivityCompat.requestPermissions(this,
					new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, PMS_WRITE_EXTERNAL_STORAGE);
		}else {
			initMediaPlayer();
		}
	}

	@Override
	public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
		switch (requestCode){
			case PMS_WRITE_EXTERNAL_STORAGE:
				if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
					initMediaPlayer();
				}else{
					Toast.makeText(this, "You Denied the Permission!", Toast.LENGTH_SHORT).show();
				}
		}
	}

	private void initMediaPlayer() {

	}

	@Override
	public void onClick(View v) {
		Intent intent = new Intent(this, MyService.class);
		switch (v.getId()) {
			case R.id.btn_pre:
				intent.putExtra("method", 0);
				startService(intent);
				break;
			case R.id.btn_play:
				intent.putExtra("method", 1);
				startService(intent);
				break;
			case R.id.btn_stop:
				intent.putExtra("method", 2);
				startService(intent);
				break;
			case R.id.btn_next:
				intent.putExtra("method", 3);
				startService(intent);
				break;
			default:
				break;
		}
	}
}
