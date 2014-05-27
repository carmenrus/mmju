package com.solt.jdc.client;

import java.util.List;

import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;

import com.solt.jdc.entity.Course;

public class CourseBroker extends AbstractBroker<Course> {

	public CourseBroker() {
		super(Course.class);
	}

	@Override
	public List<Course> getAll() {
		return baseTarget().request(MediaType.APPLICATION_JSON, MediaType.TEXT_HTML, MediaType.TEXT_PLAIN).buildGet()
				.invoke().readEntity(new GenericType<List<Course>>(){});
	}

}
