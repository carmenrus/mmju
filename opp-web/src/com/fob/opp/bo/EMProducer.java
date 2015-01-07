package com.fob.opp.bo;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@ApplicationScoped
public class EMProducer {
	
	@PersistenceContext
	private EntityManager em;
	
	@Produces
	public EntityManager getEm() {
		return this.em;
	}

}
