package com.solt.jdc.smartboy.util;

import java.util.ArrayList;
import java.util.List;

import com.solt.jdc.smartboy.dto.OrderItem;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.widget.Toast;

public class OrderService extends Service {

	private List<OrderItem> orders;
	private IBinder binder = new OrderBinder();
	
	public class OrderBinder extends Binder{
		public OrderService getService() {
			return OrderService.this;
		}
	}
	
	@Override
	public IBinder onBind(Intent intent) {
		return binder;
	}

	@Override
	public void onCreate() {
		super.onCreate();
		orders = new ArrayList<OrderItem>();
	}

	public void clear() {
		this.orders.clear();
	}

	public void add(OrderItem item) {
		this.orders.add(item);
	}

	public void send() {
		Toast.makeText(getApplicationContext(),
				String.format("%d has been sended.", this.orders.size()),
				Toast.LENGTH_SHORT).show();
		this.clear();
	}

}
