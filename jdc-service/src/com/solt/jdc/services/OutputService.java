package com.solt.jdc.services;

import javax.ws.rs.Path;

import com.solt.jdc.entity.Output;

@Path("/output")
public class OutputService extends AbstractService<Output> {

	@Override
	public void setClass() {
		super.entity = Output.class;
	}
	
}
