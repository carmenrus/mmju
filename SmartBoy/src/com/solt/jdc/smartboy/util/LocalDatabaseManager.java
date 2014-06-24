package com.solt.jdc.smartboy.util;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.solt.jdc.smartboy.dto.Category;
import com.solt.jdc.smartboy.dto.Item;
import com.solt.jdc.smartboy.dto.Table;

public class LocalDatabaseManager extends SQLiteOpenHelper {

	// Database setting
	private static final int DB_VERSION = 1;
	private static final String DB_NAME = "SMART_BOY_DB";

	// tables
	private static final String TBL_CATEGORY = "CATEGORY";
	private static final String TBL_TABLE = "S_TABLE";
	private static final String TBL_ITEM = "ITEM";

	// common columns
	private static final String KEY_ID = "ID";
	private static final String KEY_NAME = "NAME";

	// table columns
	private static final String KEY_CHAIRS = "CHAIRS";

	// item columns
	private static final String KEY_CATEGORY = "CATEGORY";
	private static final String KEY_PRICE = "PRICE";

	private static final String SELECT_ALL = "select * from %s order by ID";

	public LocalDatabaseManager(Context context) {
		super(context, DB_NAME, null, DB_VERSION);
	}

	public List<Table> getTables() {
		SQLiteDatabase db = getReadableDatabase();
		Cursor c = db.rawQuery(String.format(SELECT_ALL, TBL_TABLE), null);
		List<Table> list = new ArrayList<Table>();

		while (c.moveToNext()) {
			list.add(new Table(c.getInt(c.getColumnIndex(KEY_ID)), c
					.getString(c.getColumnIndex(KEY_NAME)), c.getInt(c
					.getColumnIndex(KEY_CHAIRS))));
		}

		return list;
	}

	public List<Category> getCategories() {
		SQLiteDatabase db = getReadableDatabase();
		Cursor c = db.rawQuery(String.format(SELECT_ALL, TBL_TABLE), null);
		List<Category> list = new ArrayList<Category>();

		while (c.moveToNext()) {
			list.add(new Category(c.getInt(c.getColumnIndex(KEY_ID)), c
					.getString(c.getColumnIndex(KEY_NAME))));
		}

		return list;
	}

	public List<Item> getItems() {
		SQLiteDatabase db = getReadableDatabase();
		Cursor c = db.rawQuery(String.format(SELECT_ALL, TBL_TABLE), null);
		List<Item> list = new ArrayList<Item>();

		while (c.moveToNext()) {
			list.add(new Item(c.getInt(c.getColumnIndex(KEY_ID)), 
					c.getString(c.getColumnIndex(KEY_NAME)),
					c.getInt(c.getColumnIndex(KEY_CATEGORY)),
					c.getInt(c.getColumnIndex(KEY_PRICE))));
		}

		return list;
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// create category
		db.execSQL(String.format(
				"CREATE TABLE %s (%s INTEGER PRIMARY KEY, %s TEXT)",
				TBL_CATEGORY, KEY_ID, KEY_NAME));
		
		// create item
		db.execSQL(String
				.format("CREATE TABLE %s (%s INTEGER PRIMARY KEY, %s TEXT, %s INTEGER, % INTEGER)",
						TBL_ITEM, KEY_ID, KEY_NAME, KEY_PRICE, KEY_CATEGORY));

		// create table
		db.execSQL(String
				.format("CREATE TABLE %s (%s INTEGER PRIMARY KEY, %s TEXT, %s INTEGER)",
						TBL_TABLE, KEY_ID, KEY_NAME, KEY_CHAIRS));
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL(String.format("DROP TABLE IF EXITS %s", TBL_CATEGORY));
		db.execSQL(String.format("DROP TABLE IF EXITS %s", TBL_ITEM));
		db.execSQL(String.format("DROP TABLE IF EXITS %s", TBL_TABLE));

		onCreate(db);
	}

}
