package com.mmju.jsp.ep2;

import java.io.Serializable;

public class Car implements Serializable {

	private static final long serialVersionUID = 1L;

	private String brand;
	private String model;
	private String year;

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}
}
