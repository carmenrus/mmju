package com.sst.ejw.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the booking database table.
 * 
 */
@Entity
public class Booking implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int customerID;

	private int bookingID;

	private Timestamp checkin;

	private Timestamp checkout;

	//bi-directional many-to-one association to Customer
	@ManyToOne
	@JoinColumn(name="customerID",insertable=false, updatable=false)
	private Customer customer;

	//bi-directional many-to-one association to Room
	@ManyToOne
	@JoinColumn(name="roomID",insertable=false, updatable=false)
	private Room room;

	public Booking() {
	}

	public int getCustomerID() {
		return this.customerID;
	}

	public void setCustomerID(int customerID) {
		this.customerID = customerID;
	}

	public int getBookingID() {
		return this.bookingID;
	}

	public void setBookingID(int bookingID) {
		this.bookingID = bookingID;
	}

	public Timestamp getCheckin() {
		return this.checkin;
	}

	public void setCheckin(Timestamp checkin) {
		this.checkin = checkin;
	}

	public Timestamp getCheckout() {
		return this.checkout;
	}

	public void setCheckout(Timestamp checkout) {
		this.checkout = checkout;
	}

	public Customer getCustomer() {
		return this.customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Room getRoom() {
		return this.room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}

}