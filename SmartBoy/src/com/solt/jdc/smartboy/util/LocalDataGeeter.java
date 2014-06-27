package com.solt.jdc.smartboy.util;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.solt.jdc.smartboy.dto.Category;
import com.solt.jdc.smartboy.dto.Item;
import com.solt.jdc.smartboy.dto.Table;

public class LocalDataGeeter {

	private JSonPerser parser;

	public LocalDataGeeter(JSonPerser parser) {
		super();
		this.parser = parser;
	}

	public List<Category> getCategories() {
		List<Category> list = new ArrayList<Category>();
		try {
			JSONArray array = parser.getJsonArray(JSonPerser.BASE_URL
					+ JSonPerser.CATEGORY);
			if (null != array) {
				for (int i = 0; i < array.length(); i++) {
					JSONObject obj = array.getJSONObject(i);
					list.add(new Category(obj.getInt("id"), obj
							.getString("name")));
				}
			}
			return list;
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	public List<Item> getItems() {
		List<Item> list = new ArrayList<Item>();
		try {
			JSONArray array = parser.getJsonArray(JSonPerser.BASE_URL
					+ JSonPerser.ITEM);
			if (null != array) {
				for (int i = 0; i < array.length(); i++) {
					JSONObject obj = array.getJSONObject(i);
					list.add(new Item(obj.getInt("id"), obj.getString("name"),
							obj.getInt("category"), obj.getInt("price")));
				}
			}
			return list;
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}


	public List<Table> getTables() {
		List<Table> list = new ArrayList<Table>();
		try {
			JSONArray array = parser.getJsonArray(JSonPerser.BASE_URL
					+ JSonPerser.ITEM);
			if (null != array) {
				for (int i = 0; i < array.length(); i++) {
					JSONObject obj = array.getJSONObject(i);
					list.add(new Table(obj.getInt("id"), obj.getString("name"),
							obj.getInt("chairs")));
				}
			}
			return list;
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

}
