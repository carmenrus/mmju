package com.dtc.address_ebook;

import com.dtc.address_ebook.Address;

import java.io.Serializable;
import java.lang.String;

import javax.persistence.*;

import static javax.persistence.CascadeType.ALL;

/**
 * Entity implementation class for Entity: Auser
 *
 */
@Entity
@NamedQueries(value = { @NamedQuery(name = "Auser.findAll", query = "select a from Auser a order by a.name") })
public class Auser implements Serializable {

	   
	@Id
	@GeneratedValue
	private int id;
	private String name;
	private String phone;
	private String email;
	
	@OneToOne(cascade = ALL)
	@JoinColumn(name = "address_id", referencedColumnName = "id")
	private Address address;
	private String memo;
	private static final long serialVersionUID = 1L;

	public Auser() {
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
	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}   
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}   
	public Address getAddress() {
		return this.address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}   
	public String getMemo() {
		return this.memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}
   
}
