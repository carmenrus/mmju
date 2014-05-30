package com.solt.jdc.client;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;

import com.solt.jdc.entity.Transaction;

public class TransactionBroker extends AbstractBroker<Transaction> {

	private static TransactionBroker broker;
	private BalanceCalculator cal;
	
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
		return this.setBalance(baseTarget().request(MediaType.APPLICATION_JSON).buildGet()
				.invoke().readEntity(new GenericType<List<Transaction>>(){})); 
	}
	
	public List<Transaction> getAll(Date from, Date to) {
		return this.setBalance(baseTarget()
				.path(this.getString(from))
				.path(getString(to))
				.request(MediaType.APPLICATION_JSON).buildGet()
				.invoke().readEntity(new GenericType<List<Transaction>>(){})); 
	}
	
	public String getTotal() {
		return String.valueOf(cal.getTotal());
	}
	
	private String getString(Date date) {
		return new SimpleDateFormat("yyyyMMdd").format(date);
	}
	
	private List<Transaction> setBalance(List<Transaction> rawList) {
		cal = new BalanceCalculator();
		return rawList.stream().map(e -> {
			cal.addTransaction(e);
			e.setBalance(cal.getTotal());
			return e;
		}).collect(Collectors.toList());
	}
	
	private static class BalanceCalculator {
		private int total = 0;

		public void addTransaction(Transaction t) {
			total -= t.getOutcome();
			total += t.getIncome();
		}

		public int getTotal() {
			return this.total;
		}
		
	}

}
