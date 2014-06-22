package com.solt.jdc.services;

import javax.ws.rs.Path;

import com.solt.jdc.entity.Township;

@Path("/township")
public class TownshipService extends AbstractService<Township> {

	@Override
	public void setClass() {
		super.entity = Township.class;
	}

}
