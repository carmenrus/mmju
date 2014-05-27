package com.solt.jdc.client;

import java.util.List;

import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;

import com.solt.jdc.entity.JdcClass;

public class JdcClassBroker extends AbstractBroker<JdcClass> {

	public JdcClassBroker() {
		super(JdcClass.class);
	}

	@Override
	public List<JdcClass> getAll() {
		return baseTarget().request(MediaType.APPLICATION_JSON).buildGet()
				.invoke().readEntity(new GenericType<List<JdcClass>>(){});
	}

}
