package com.sst.ejw.test;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import com.sst.ejw.entity.Roomtype;

public class RoomTypeRefTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		EntityManager em = Persistence.createEntityManagerFactory("ejw-jpa").createEntityManager();
		Roomtype rt = em.find(Roomtype.class, new Integer(1001));
		System.out.println(rt.getRoomtype());
		System.out.println(rt.getRoombed());
	}

}
