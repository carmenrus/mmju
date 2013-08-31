package com.dtc.address_ebook;

import com.dtc.address_ebook.Townships;
import java.io.Serializable;
import java.lang.String;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Address
 *
 */
@Entity

public class Address implements Serializable {

	   
	@Id
	@GeneratedValue
	private int id;
	private String address;
	@ManyToOne
	private Townships townships;
	
	@OneToOne(mappedBy="address")
	private Auser auser;

	private static final long serialVersionUID = 1L;


	public Auser getAuser() {
		return auser;
	}
	public void setAuser(Auser auser) {
		this.auser = auser;
	}
	public Address() {
		super();
	}   
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}   
	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}   
	public Townships getTownships() {
		return this.townships;
	}

	public void setTownships(Townships townships) {
		this.townships = townships;
	}
   
}
