package com.solt.jdc.client;

import java.util.List;

import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;

import com.solt.jdc.entity.Transaction;

public class TransactionBroker extends AbstractBroker<Transaction> {

	private static TransactionBroker broker;
	
	private TransactionBroker() {
		super(Transaction.class);
	}
	
	public synchronized static TransactionBroker getInstance() {
		if(null == broker) {
			broker = new TransactionBroker(); 
		}
		return broker;
	}

	@Override
	public List<Transaction> getAll() {
		return baseTarget().request(MediaType.APPLICATION_JSON).buildGet()
				.invoke().readEntity(new GenericType<List<Transaction>>(){});
	}

}
