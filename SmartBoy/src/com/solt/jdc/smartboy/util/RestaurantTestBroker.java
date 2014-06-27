package com.solt.jdc.smartboy.util;

import java.util.Arrays;
import java.util.List;

import android.content.Context;

import com.solt.jdc.smartboy.dto.Order;
import com.solt.jdc.smartboy.dto.OrderItem;

public class RestaurantTestBroker implements RestaurantBroker {

	private RestaurantTestBroker(){}
	private static RestaurantBroker broker;
	private static Context CONTEXT;
	private LocalDatabaseManager local = new LocalDatabaseManager(CONTEXT);
	
	
	public static RestaurantBroker getTestBroker(Context context) {
		CONTEXT = context;
		broker = (null != broker)?broker:new RestaurantTestBroker();
		return broker;
	}

	@Override
	public List<Order> getOrders(int tableId) {
		
		Order order = new Order();

		order.setId(1);
		order.setTableId(tableId);
		order.setItems(Arrays.asList(new OrderItem(local.getItems().get(1), 3, 0),
				new OrderItem(local.getItems().get(3), 2, 0)));

		return Arrays.asList(order);
	}

	@Override
	public boolean checkOut(int tableId) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public Order doOrder(Order order) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<OrderItem> cancelOrder(List<OrderItem> items) {
		// TODO Auto-generated method stub
		return null;
	}

}
