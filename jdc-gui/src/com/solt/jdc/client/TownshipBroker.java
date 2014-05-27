package com.solt.jdc.client;

import java.util.List;

import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;

import com.solt.jdc.entity.Township;

public class TownshipBroker extends AbstractBroker<Township> {

	public TownshipBroker() {
		super(Township.class);
	}

	@Override
	public List<Township> getAll() {
		return baseTarget().request(MediaType.APPLICATION_JSON).buildGet()
				.invoke().readEntity(new GenericType<List<Township>>(){});
	}

}
