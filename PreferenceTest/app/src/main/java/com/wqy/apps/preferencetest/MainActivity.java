package com.wqy.apps.preferencetest;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

	EditText et_txt;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		et_txt = (EditText) findViewById(R.id.et_txt);
		SharedPreferences sharedPreferences = getPreferences(Context.MODE_PRIVATE);
		et_txt.setText(sharedPreferences.getString("txt", "Hello World!"));
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		String txt = et_txt.getText().toString();
		if (txt != null){
			SharedPreferences.Editor editor = getPreferences(Context.MODE_PRIVATE).edit();
			editor.putString("txt", txt);
			editor.apply();
		}
	}
}
