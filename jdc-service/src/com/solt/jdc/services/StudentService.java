package com.solt.jdc.services;

import javax.ws.rs.Path;

import com.solt.jdc.entity.Student;

@Path("/student")
public class StudentService extends AbstractService<Student> {

	@Override
	public void setClass() {
		super.entity = Student.class;
	}

}
