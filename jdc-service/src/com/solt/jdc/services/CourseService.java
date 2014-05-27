package com.solt.jdc.services;

import javax.ws.rs.Path;

import com.solt.jdc.entity.Course;

@Path("/course")
public class CourseService extends AbstractService<Course>{
	
	@Override
	public void setClass() {
		super.entity = Course.class;
	}

	@Override
	public Course update(Course t) {
		Course c = find(t.getId());
		c.setName(t.getName());
		c.setDescription(t.getDescription());
		c.setRequirement(t.getRequirement());
		c.setDuration(t.getDuration());
		c.setFee(t.getFee());
		return dao.update(c);
	}

}
