package com.solt.jdc.services;

import javax.ws.rs.Path;

import com.solt.jdc.entity.JdcClass;

@Path("/jdcclass")
public class JdcClassService extends AbstractService<JdcClass> {
	
	@Override
	public void setClass() {
		super.entity = JdcClass.class;
	}

	@Override
	public JdcClass update(JdcClass t) {
		JdcClass c = this.find(t.getId());
		c.setCourse(t.getCourse());
		c.setStart(t.getStart());
		c.setStatus(t.getStatus());
		c.setTimeTable(t.getTimeTable());
		return this.dao.update(c);
	}

}
