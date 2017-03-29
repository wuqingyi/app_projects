package com.wqy.apps.dataprocesstest;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

/**
 * Created by wuqingyi on 2017/3/29.
 */

public class MyDatabaseHelper extends SQLiteOpenHelper {
	private final String CREATE_BOOK = "create table Book("
			+ "id integer primary key autoincrement,"
			+ "author text,"
			+ "price real,"
			+ "pages integer,"
			+ "name text)";

	private final String CREATE_CATEGORY = "create table Category("
			+ "id integer primary key autoincrement,"
			+ "category_name text,"
			+ "category_code integer)";

	private Context context;

	public MyDatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
		super(context, name, factory, version);
		this.context = context;
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(CREATE_BOOK);
		db.execSQL(CREATE_CATEGORY);
		Toast.makeText(this.context, "Create tables succeeded", Toast.LENGTH_SHORT).show();
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("drop table if exist Book");
		db.execSQL("drop table if exist Category");
		onCreate(db);
	}
}
