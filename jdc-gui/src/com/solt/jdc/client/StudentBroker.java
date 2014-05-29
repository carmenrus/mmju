package com.solt.jdc.client;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;

import com.solt.jdc.entity.Bill;
import com.solt.jdc.entity.Student;
import com.solt.jdc.entity.StudentJdc;

public class StudentBroker extends AbstractBroker<Student> {

	private static StudentBroker broker;

	private StudentBroker() {
		super(Student.class);
	}

	public synchronized static StudentBroker getInstance() {
		if (broker == null)
			broker = new StudentBroker();

		return broker;
	}

	@Override
	public List<Student> getAll() {
		return baseTarget().request(MediaType.APPLICATION_JSON).buildGet()
				.invoke().readEntity(new GenericType<List<Student>>() {
				});
	}

	public List<StudentJdc> getClassByStudent(int id) {
		return baseTarget().path("jdc").path(String.valueOf(id))
				.request(MediaType.APPLICATION_JSON).buildGet().invoke()
				.readEntity(new GenericType<List<StudentJdc>>() {
				});
	}

	public List<Bill> getBillByJdcClass(int studentId, int jdcClassId) {
		return baseTarget().path("bill").path(String.valueOf(studentId))
				.path(String.valueOf(jdcClassId))
				.request(MediaType.APPLICATION_JSON).buildGet().invoke()
				.readEntity(new GenericType<List<Bill>>() {
				});
	}
	
	public List<Bill> getBillByJdcClass(StudentJdc jdc) {
		if(null != jdc) {
			return baseTarget().path("bill").path(String.valueOf(jdc.getId().getStudentId()))
					.path(String.valueOf(jdc.getId().getJdcClassId()))
					.request(MediaType.APPLICATION_JSON).buildGet().invoke()
					.readEntity(new GenericType<List<Bill>>() {
					});
		}
		
		return new ArrayList<Bill>();
	}

}
