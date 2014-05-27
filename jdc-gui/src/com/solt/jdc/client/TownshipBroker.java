package com.solt.jdc.client;

import java.util.List;

import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;

import com.solt.jdc.entity.Township;

public class TownshipBroker extends AbstractBroker<Township> {
	
	private static TownshipBroker broker;

	private TownshipBroker() {
		super(Township.class);
	}
	
	public synchronized static TownshipBroker getInstance() {
		if(null == broker) {
			broker = new TownshipBroker();
		}
		return broker;
	}

	@Override
	public List<Township> getAll() {
		return baseTarget().request(MediaType.APPLICATION_JSON).buildGet()
				.invoke().readEntity(new GenericType<List<Township>>(){});
	}

}
