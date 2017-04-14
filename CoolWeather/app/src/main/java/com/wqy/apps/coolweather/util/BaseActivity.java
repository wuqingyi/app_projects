package com.wqy.apps.coolweather.util;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

/**
 * Created by wuqingyi on 2017/4/14.
 */

public class BaseActivity extends AppCompatActivity {
	private static final String TAG = "activity";
	@Override
	public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
		Log.d(TAG, "onCreate: ");
		super.onCreate(savedInstanceState, persistentState);
	}

	@Override
	protected void onStart() {
		Log.d(TAG, "onStart: ");
		super.onStart();
	}

	@Override
	protected void onResume() {
		Log.d(TAG, "onResume: ");
		super.onResume();
	}

	@Override
	protected void onPause() {
		Log.d(TAG, "onPause: ");
		super.onPause();
	}

	@Override
	protected void onStop() {
		Log.d(TAG, "onStop: ");
		super.onStop();
	}

	@Override
	protected void onRestart() {
		Log.d(TAG, "onRestart: ");
		super.onRestart();
	}

	@Override
	protected void onDestroy() {
		Log.d(TAG, "onDestroy: ");
		super.onDestroy();
	}
}
