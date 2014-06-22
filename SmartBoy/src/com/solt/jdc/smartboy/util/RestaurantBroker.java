package com.solt.jdc.smartboy.util;

import java.util.List;

import com.solt.jdc.smartboy.dto.Order;
import com.solt.jdc.smartboy.dto.OrderItem;

public interface RestaurantBroker {

	public List<Order> getOrders(int tableId);
	public boolean checkOut(int tableId);
	public Order doOrder(Order order);
	public List<OrderItem> cancelOrder(List<OrderItem> items);
}
