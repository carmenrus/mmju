package com.solt.jdc.client;

import java.util.List;

import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;

import com.solt.jdc.entity.JdcClass;

public class JdcClassBroker extends AbstractBroker<JdcClass> {

	private static JdcClassBroker broker;
	
	private JdcClassBroker() {
		super(JdcClass.class);
	}
	
	public synchronized static JdcClassBroker getInstancce() {
		if(null == broker) {
			broker = new JdcClassBroker();
		}
		return broker;
	}

	@Override
	public List<JdcClass> getAll() {
		return baseTarget().request(MediaType.APPLICATION_JSON).buildGet()
				.invoke().readEntity(new GenericType<List<JdcClass>>(){});
	}

}
