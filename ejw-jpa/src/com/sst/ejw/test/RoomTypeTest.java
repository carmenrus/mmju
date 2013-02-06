package com.sst.ejw.test;

import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import com.sst.ejw.entity.Room;
import com.sst.ejw.entity.Roomtype;

public class RoomTypeTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		EntityManager em = Persistence.createEntityManagerFactory("ejw-jpa").createEntityManager();
		Roomtype type = new Roomtype();
		type.setRoomtypeID(1001);
		type.setRoombed(2);
		type.setRoomprice(5000);
		type.setRoomtype("double room");
		type.setRooms(new ArrayList<Room>());
		em.getTransaction().begin();
		em.persist(type);
		em.getTransaction().commit();
		em.close();
	}

}
