package com.solt.jdc.services;

import java.util.Date;

import javax.ws.rs.Path;

import com.solt.jdc.entity.Output;

@Path("/output")
public class OutputService extends AbstractService<Output> {

	@Override
	public void setClass() {
		super.entity = Output.class;
	}

	@Override
	public Output update(Output t) {
		Output c = find(t.getId());
		c.setCatagory(t.getCatagory());
		c.setComment(t.getComment());
		c.setModification(new Date());
		c.setOutcome(t.getOutcome());
		c.setStatus(t.getStatus());
		c.setStuff(t.getStuff());
		c.setTransaction(t.getTransaction());
		return dao.update(c);
	}

}
