package com.solt.jdc.services;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.solt.jdc.entity.Bill;
import com.solt.jdc.entity.Student;

@Path("/student")
public class StudentService extends AbstractService<Student> {
	
	@Inject
	private EntityManager em;

	@Override
	public void setClass() {
		super.entity = Student.class;
	}
	
	@GET
	@Path("bill/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Bill> getBill(@PathParam("id") int id) {
		return em.createNamedQuery("Bill.findByStudent", Bill.class).setParameter("student_id", id).getResultList();
	}

}
