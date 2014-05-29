package com.solt.jdc.services;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.solt.jdc.entity.Bill;
import com.solt.jdc.entity.Student;
import com.solt.jdc.entity.StudentJdc;
import com.solt.jdc.model.StudentModel;

@Path("/student")
public class StudentService extends AbstractService<Student> {
	
	@Inject
	private StudentModel studentModel;
	
	@Override
	public void setClass() {
		super.entity = Student.class;
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("jdc/{id}")
	public List<StudentJdc> getStudentJdcs(@PathParam("id") int id) {
		return studentModel.getClasses(id);
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("bill/{id}/{jdcid}")
	public List<Bill> getStudentJdcBills(@PathParam("id") int id,@PathParam("jdcid") int jdcid) {
		return studentModel.getBillByStudentClass(id, jdcid);
	}
	
}
