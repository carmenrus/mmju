package com.solt.jdc.smartboy.dto;

import java.io.Serializable;
import java.util.List;

public class Order implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private int id;
	private int tableId;
	private List<OrderItem> items;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getTableId() {
		return tableId;
	}
	public void setTableId(int tableId) {
		this.tableId = tableId;
	}
	public List<OrderItem> getItems() {
		return items;
	}
	public void setItems(List<OrderItem> items) {
		this.items = items;
	}
}
