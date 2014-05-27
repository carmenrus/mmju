package com.solt.jdc.entity;

import java.io.Serializable;

import javax.persistence.*;


/**
 * The persistent class for the township database table.
 * 
 */
@Entity
@NamedQuery(name="Township.findAll", query="SELECT t FROM Township t")
public class Township implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private String name;

	public Township() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return name;
	}
}