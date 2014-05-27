package com.solt.jdc.services;

import java.util.Date;

import javax.ws.rs.Path;

import com.solt.jdc.entity.Student;

@Path("/student")
public class StudentService extends AbstractService<Student> {

	@Override
	public void setClass() {
		super.entity = Student.class;
	}

	@Override
	public Student update(Student t) {
		Student c = find(t.getId());
		c.setAddres(t.getAddres());
		c.setDateOfBirth(t.getDateOfBirth());
		c.setEmail(t.getEmail());
		c.setModification(new Date());
		c.setName(t.getName());
		c.setNrcNumber(t.getNrcNumber());
		c.setPhone(t.getPhone());
		c.setTownship(t.getTownship());
		return dao.update(c);
	}

}
