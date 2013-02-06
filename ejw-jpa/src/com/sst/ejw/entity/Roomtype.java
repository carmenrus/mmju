package com.sst.ejw.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the roomtype database table.
 * 
 */
@Entity
public class Roomtype implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int roomtypeID;

	private int roombed;

	private int roomprice;

	private String roomtype;

	//bi-directional many-to-one association to Room
	@OneToMany(mappedBy="roomtype")
	private List<Room> rooms;

	public Roomtype() {
	}

	public int getRoomtypeID() {
		return this.roomtypeID;
	}

	public void setRoomtypeID(int roomtypeID) {
		this.roomtypeID = roomtypeID;
	}

	public int getRoombed() {
		return this.roombed;
	}

	public void setRoombed(int roombed) {
		this.roombed = roombed;
	}

	public int getRoomprice() {
		return this.roomprice;
	}

	public void setRoomprice(int roomprice) {
		this.roomprice = roomprice;
	}

	public String getRoomtype() {
		return this.roomtype;
	}

	public void setRoomtype(String roomtype) {
		this.roomtype = roomtype;
	}

	public List<Room> getRooms() {
		return this.rooms;
	}

	public void setRooms(List<Room> rooms) {
		this.rooms = rooms;
	}

}