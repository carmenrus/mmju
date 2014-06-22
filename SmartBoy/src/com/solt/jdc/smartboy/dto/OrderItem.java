package com.solt.jdc.smartboy.dto;

import java.io.Serializable;

public class OrderItem implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private Item item;
	private int count;
	private int status;
	public OrderItem(Item item, int count, int status) {
		super();
		this.item = item;
		this.count = count;
		this.status = status;
	}
	public Item getItem() {
		return item;
	}
	public void setItem(Item item) {
		this.item = item;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}

}
