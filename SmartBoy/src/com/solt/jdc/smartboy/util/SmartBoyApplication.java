package com.solt.jdc.smartboy.util;

import java.util.ArrayList;
import java.util.List;

import android.app.Application;
import android.widget.Toast;

import com.solt.jdc.smartboy.dto.OrderItem;

public class SmartBoyApplication extends Application {

	private List<OrderItem> orders;

	@Override
	public void onCreate() {
		super.onCreate();
		this.orders = new ArrayList<OrderItem>();
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

}
