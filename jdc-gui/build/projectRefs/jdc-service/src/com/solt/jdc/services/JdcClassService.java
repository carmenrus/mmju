package com.solt.jdc.services;

import javax.ws.rs.Path;

import com.solt.jdc.entity.JdcClass;

@Path("/jdcclass")
public class JdcClassService extends AbstractService<JdcClass> {
	
	@Override
	public void setClass() {
		super.entity = JdcClass.class;
	}

}
