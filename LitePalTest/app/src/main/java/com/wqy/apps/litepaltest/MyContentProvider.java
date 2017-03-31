package com.wqy.apps.litepaltest;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

import org.litepal.tablemanager.Connector;

public class MyContentProvider extends ContentProvider {

	public static final int BOOK_DIR = 0;
	public static final int BOOK_ITEM = 1;
	public static final int CATEGORY_DIR = 2;
	public static final int CATEGORY_ITEM = 3;

	public static final String AUTHORITY = "com.wqy.apps.litepaltest";
	private static UriMatcher uriMatcher;
	SQLiteDatabase db = null;

	static {
		uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
		uriMatcher.addURI(AUTHORITY, "book", BOOK_DIR);
		uriMatcher.addURI(AUTHORITY, "book/#", BOOK_ITEM);
		uriMatcher.addURI(AUTHORITY, "category", CATEGORY_DIR);
		uriMatcher.addURI(AUTHORITY, "category/#", CATEGORY_ITEM);
	}

	public MyContentProvider() {
	}

	@Override
	public boolean onCreate() {
		db = Connector.getWritableDatabase();
		return true;
	}

	@Override
	public int delete(Uri uri, String selection, String[] selectionArgs) {
		int dltRows = 0;
		switch (uriMatcher.match(uri)) {
			case BOOK_DIR:
				dltRows = db.delete("Book", selection, selectionArgs);
				break;
			case BOOK_ITEM:
				String bookId = uri.getPathSegments().get(1);
				dltRows = db.delete("Book", "id = ?", new String[]{bookId});
				break;
			case CATEGORY_DIR:
				dltRows = db.delete("Category", selection, selectionArgs);
				break;
			case CATEGORY_ITEM:
				String categoryId = uri.getPathSegments().get(1);
				dltRows = db.delete("Category", "id = ?", new String[]{categoryId});
				break;
			default:
				break;
		}
		return dltRows;
	}

	@Override
	public String getType(Uri uri) {
		switch (uriMatcher.match(uri)) {
			case BOOK_DIR:
				return "vnd.android.cursor.dir/" + AUTHORITY + ".book";
			case BOOK_ITEM:
				return "vnd.android.cursor.item/" + AUTHORITY + ".book";
			case CATEGORY_DIR:
				return "vnd.android.cursor.dir/" + AUTHORITY + ".category";
			case CATEGORY_ITEM:
				return "vnd.android.cursor.item/" + AUTHORITY + ".category";
		}
		return null;
	}

	@Override
	public Uri insert(Uri uri, ContentValues values) {
		Uri uriRet = null;
		switch (uriMatcher.match(uri)) {
			case BOOK_DIR:
			case BOOK_ITEM:
				long newBookId = db.insert("Book", null, values);
				uriRet = Uri.parse("content://" + AUTHORITY + "/book/" + newBookId);
				break;
			case CATEGORY_DIR:
			case CATEGORY_ITEM:
				long newCategoryId = db.insert("Category", null, values);
				uriRet = Uri.parse("content://" + AUTHORITY + "/category/" + newCategoryId);
				break;
			default:
				break;
		}
		return uriRet;
	}

	@Override
	public Cursor query(Uri uri, String[] projection, String selection,
	                    String[] selectionArgs, String sortOrder) {
		Cursor cursor = null;
		switch (uriMatcher.match(uri)) {
			case BOOK_DIR:
				cursor = db.query("Book", projection, selection, selectionArgs, null, null, sortOrder);
				break;
			case BOOK_ITEM:
				String bookId = uri.getPathSegments().get(1);
				cursor = db.query("Book", projection, "id = ?", new String[]{bookId}, null, null, sortOrder);
				break;
			case CATEGORY_DIR:
				cursor = db.query("Category", projection, selection, selectionArgs, null, null, sortOrder);
				break;
			case CATEGORY_ITEM:
				String categoryId = uri.getPathSegments().get(1);
				cursor = db.query("Category", projection, "id = ?", new String[]{categoryId}, null, null, sortOrder);
				break;
			default:
				break;
		}
		return cursor;
	}

	@Override
	public int update(Uri uri, ContentValues values, String selection,
	                  String[] selectionArgs) {
		int updatedRows = 0;
		switch (uriMatcher.match(uri)) {
			case BOOK_DIR:
				updatedRows = db.update("Book", values, selection, selectionArgs);
				break;
			case BOOK_ITEM:
				String bookId = uri.getPathSegments().get(1);
				updatedRows = db.update("Book", values, "id = ?", new String[]{bookId});
				break;
			case CATEGORY_DIR:
				updatedRows = db.update("Category", values, selection, selectionArgs);
				break;
			case CATEGORY_ITEM:
				String categoryId = uri.getPathSegments().get(1);
				updatedRows = db.update("Category", values, "id = ?", new String[]{categoryId});
				break;
			default:
				break;
		}
		return updatedRows;
	}
}
