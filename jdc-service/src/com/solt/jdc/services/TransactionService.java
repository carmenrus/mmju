package com.solt.jdc.services;

import java.util.Date;

import com.solt.jdc.entity.Transaction;

public class TransactionService extends AbstractService<Transaction> {

	@Override
	public void setClass() {
		super.entity = Transaction.class;
	}

	@Override
	public Transaction update(Transaction t) {
		Transaction c = find(t.getId());
		c.setComment(t.getComment());
		c.setIncome(t.getIncome());
		c.setOutcome(t.getOutcome());
		c.setStatus(t.getStatus());
		c.setType(t.getType());
		c.setModification(new Date());
		return dao.update(c);
	}

}
