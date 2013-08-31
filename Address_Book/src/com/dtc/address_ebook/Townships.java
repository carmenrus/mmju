package com.dtc.address_ebook;

import java.io.Serializable;
import java.lang.String;
import java.util.List;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Townships
 *
 */
@Entity

public class Townships implements Serializable {

	   
	@Id
	@GeneratedValue
	
	private int id;
	private String name;
	private static final long serialVersionUID = 1L;
	
	@OneToMany(mappedBy = "townships")
	private List<Address> addresslist;
	
	
	public List<Address> getAddresslist() {
		return addresslist;
	}
	public void setAddresslist(List<Address> addresslist) {
		this.addresslist = addresslist;
	}

	@ManyToOne
	private Division division;
	
	public Division getDivision() {
		return division;
	}
	public void setDivision(Division division) {
		this.division = division;
	}
	public Townships() {
		super();
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
