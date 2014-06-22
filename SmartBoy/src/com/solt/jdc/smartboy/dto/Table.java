package com.solt.jdc.smartboy.dto;

import java.io.Serializable;

public class Table implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private int id;
	private String name;
	private int chairs;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getChairs() {
		return chairs;
	}
	public void setChairs(int chairs) {
		this.chairs = chairs;
	}
	public Table(int id, String name, int chairs) {
		super();
		this.id = id;
		this.name = name;
		this.chairs = chairs;
	}
}
