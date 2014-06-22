package com.solt.jdc.model;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@ApplicationScoped
public class CommonProducer {
	
	@Produces
	@PersistenceContext(name="jdc-service")
	private EntityManager em;	
}
