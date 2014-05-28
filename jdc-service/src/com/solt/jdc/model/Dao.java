package com.solt.jdc.model;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;

public class Dao<T> {

	@Inject
	private EntityManager em;
	
	@Transactional
	public void persist(T t) {
		em.persist(t);
	}
	
	public List<T> find(Class<T> entityClass) {
		StringBuffer sb = new StringBuffer("select c from ");
		sb.append(entityClass.getSimpleName());
		sb.append(" c");
		return em.createQuery(sb.toString(), entityClass).getResultList();
	}
	
	public T find(Class<T> entityClass, Object id) {
		return em.find(entityClass, id);
	}
	
	@Transactional
	public void delete(Class<T> entityClass, Object id) {
		T entity = em.find(entityClass, id);
		em.remove(entity);
	}
	
	
	@Transactional
	public T update(T t) {
		this.em.merge(t);
		return t;
	}
	
}
