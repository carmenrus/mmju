package com.solt.jdc.client;

import java.util.List;

import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;

import com.solt.jdc.entity.Student;

public class StudentBroker extends AbstractBroker<Student> {

	private static StudentBroker broker;
	
	private StudentBroker() {
		super(Student.class);
	}
	
	public synchronized static StudentBroker getInstance() {
		if(broker == null)
			broker = new StudentBroker();
		
		return broker;
	}

	@Override
	public List<Student> getAll() {
		return baseTarget().request(MediaType.APPLICATION_JSON).buildGet()
				.invoke().readEntity(new GenericType<List<Student>>(){});
	}

}
