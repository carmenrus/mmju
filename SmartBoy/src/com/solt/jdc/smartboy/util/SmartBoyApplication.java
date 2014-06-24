package com.solt.jdc.smartboy.util;

import java.util.ArrayList;
import java.util.List;

import android.app.Application;
import android.widget.Toast;

import com.solt.jdc.smartboy.dto.Category;
import com.solt.jdc.smartboy.dto.Item;
import com.solt.jdc.smartboy.dto.OrderItem;
import com.solt.jdc.smartboy.dto.Table;

public class SmartBoyApplication extends Application implements LocalManager{

	private List<OrderItem> orders;
	private List<Table> tables;
	private List<Category> categories;
	private List<Item> items;
	
	private void initData() {
		LocalManager local = LocalTestManager.getTestLocalManaget();
		this.tables = local.getTables();
		this.categories = local.getCategories();
		this.items = local.getItems();
	}

	@Override
	public void onCreate() {
		super.onCreate();
		this.orders = new ArrayList<OrderItem>();
		this.initData();
	}

	
	@Override
	public void onTerminate() {
		super.onTerminate();
		this.orders.clear();
	}
	
	public void clear() {
		this.orders.clear();
	}

	public void add(OrderItem item) {
		this.orders.add(item);
	}
	
	public List<OrderItem> getOrders() {
		return this.orders;
	}

	public void send() {
		Toast.makeText(getApplicationContext(),
				String.format("%d has been sended.", this.orders.size()),
				Toast.LENGTH_SHORT).show();
		this.clear();
	}


	@Override
	public List<Table> getTables() {
		return this.tables;
	}


	@Override
	public List<Category> getCategories() {
		return categories;
	}


	@Override
	public Table getTable(int tableId) {
		for(Table t : tables) {
			if(t.getId() == tableId) {
				return t;
			}
		}
		return null;
	}


	@Override
	public List<Item> getItems(int category) {
		List<Item> list = new ArrayList<Item>();
		for(Item i : items) {
			if(i.getCategory() == category) {
				list.add(i);
			}
		}
		return list;
	}


	@Override
	public Item getItem(int id) {
		for(Item i : items) {
			if(i.getId() == id)
				return i;
		}
		return null;
	}


	@Override
	public List<Item> getItems() {
		return items;
	}

}
