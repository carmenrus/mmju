package com.solt.jdc.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.solt.jdc.entity.Transaction;

@Path("/transaction")
public class TransactionService extends AbstractService<Transaction> {

	@Inject
	private EntityManager em;
	
	@Override
	public void setClass() {
		super.entity = Transaction.class;
	}
	
	@Override
	public List<Transaction> getAll() {
		return em.createNamedQuery("Transaction.findAll", Transaction.class).getResultList();
	}
	
	@GET
	@Path("{from}/{to}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Transaction> getAll(@PathParam("from") String from, @PathParam("to") String to) {
		return em.createNamedQuery("Transaction.findAllFromTo", Transaction.class)
				.setParameter("from", this.getDateFromString(from))
				.setParameter("to", this.getDateFromString(to))
				.getResultList();
	}
	
	private Date getDateFromString(String str) {
		try {
			return new SimpleDateFormat("yyyyMMdd").parse(str);
		} catch (ParseException e) {
			return null;
		}
	}
}
