package com.solt.jdc.services;

import javax.ws.rs.Path;

import com.solt.jdc.entity.Course;

@Path("/course")
public class CourseService extends AbstractService<Course>{
	
	@Override
	public void setClass() {
		super.entity = Course.class;
	}

}
