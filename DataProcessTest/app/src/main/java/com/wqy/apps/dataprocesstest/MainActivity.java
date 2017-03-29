package com.wqy.apps.dataprocesstest;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

	private Button btn_create_table;
	private Button btn_get_values;
	private MyDatabaseHelper helper;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		helper = new MyDatabaseHelper(this, "BookStore", null, 1);
		btn_create_table = (Button) findViewById(R.id.btn_create_table);
		btn_create_table.setOnClickListener(this);
		btn_get_values = (Button) findViewById(R.id.btn_get_values);
		btn_get_values.setOnClickListener(this);

	}

	@Override
	public void onClick(View v) {
		SQLiteDatabase db = helper.getWritableDatabase();
		switch (v.getId()) {
			case R.id.btn_create_table:
				ContentValues value = new ContentValues();
				value.put("author", "Tom");
				value.put("price", 23);
				value.put("pages", 230);
				value.put("name", "Android");
				db.insert("Book", null, value);
				value.clear();
				value.put("author", "Jane");
				value.put("price", 33);
				value.put("pages", 430);
				value.put("name", "Java");
				db.insert("Book", null, value);
				break;
			case R.id.btn_get_values:
				Cursor cursor = db.query("Book", null, null, null, null, null, null, null);
				if (cursor.moveToFirst()) {
					do {
						Toast.makeText(this, cursor.getString(cursor.getColumnIndex("name"))
										+ ":" + cursor.getString(cursor.getColumnIndex("author")),
								Toast.LENGTH_SHORT).show();
					} while (cursor.moveToNext());
				}
				break;
		}
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		helper.close();
	}
}
