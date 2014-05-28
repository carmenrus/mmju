package com.solt.jdc.services;

import javax.ws.rs.Path;

import com.solt.jdc.entity.Transaction;

@Path("/transaction")
public class TransactionService extends AbstractService<Transaction> {

	@Override
	public void setClass() {
		super.entity = Transaction.class;
	}

}
