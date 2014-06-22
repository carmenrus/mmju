package com.solt.jdc.smartboy.util;

import java.util.Arrays;
import java.util.List;

import com.solt.jdc.smartboy.dto.Order;
import com.solt.jdc.smartboy.dto.OrderItem;

public class RestaurantTestBroker implements RestaurantBroker {

	private LocalManager local = LocalTestManager.getTestLocalManaget();
	
	private RestaurantTestBroker(){}
	private static RestaurantBroker broker;
	
	public static RestaurantBroker getTestBroker() {
		
		broker = (null != broker)?broker:new RestaurantTestBroker();
		return broker;
	}

	@Override
	public List<Order> getOrders(int tableId) {
		
		Order order = new Order();

		order.setId(1);
		order.setTableId(tableId);
		order.setItems(Arrays.asList(new OrderItem(local.getItem(1), 3, 0),
				new OrderItem(local.getItem(3), 2, 0)));

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
