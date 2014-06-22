package com.solt.jdc.client;

import java.util.List;

import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;

import com.solt.jdc.entity.Course;

public class CourseBroker extends AbstractBroker<Course> {

	private static CourseBroker broker;
	
	private CourseBroker() {
		super(Course.class);
	}
	
	public synchronized static CourseBroker getInstance() {
		if(null == broker) {
			broker = new CourseBroker();
		}
		return broker;
	}

	@Override
	public List<Course> getAll() {
		return baseTarget().request(MediaType.APPLICATION_JSON, MediaType.TEXT_HTML, MediaType.TEXT_PLAIN).buildGet()
				.invoke().readEntity(new GenericType<List<Course>>(){});
	}

}
