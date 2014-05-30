package com.solt.jdc.client;

import java.util.List;

import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;

import com.solt.jdc.entity.Output;

public class OutputBroker extends AbstractBroker<Output> {
	
	private static OutputBroker broker;

	protected OutputBroker() {
		super(Output.class);
	}

	public synchronized static OutputBroker getInstance() {
		if(null == broker)
			broker = new OutputBroker();
		
		return broker;
	}
	
	@Override
	public List<Output> getAll() {
		return baseTarget().request(MediaType.APPLICATION_JSON).buildGet()
				.invoke().readEntity(new GenericType<List<Output>>() {
				});
	}

}
