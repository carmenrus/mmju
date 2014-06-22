package com.solt.jdc.client;

import java.util.List;

import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;

import com.solt.jdc.entity.Bill;

public class BillBroker extends AbstractBroker<Bill> {
	
	private static BillBroker broker;

	private BillBroker() {
		super(Bill.class);
	}
	
	public synchronized static BillBroker getInstance() {
		if(null == broker) {
			broker = new BillBroker();
		}
		return broker;
	}

	@Override
	public List<Bill> getAll() {
		return baseTarget().request(MediaType.APPLICATION_JSON, MediaType.TEXT_HTML, MediaType.TEXT_PLAIN).buildGet()
				.invoke().readEntity(new GenericType<List<Bill>>(){});
	}

}
