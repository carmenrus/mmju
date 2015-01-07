package com.fob.opp.bo;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;

public class Dao<T> implements Serializable{
	private static final long serialVersionUID = 1L;

	@Inject
	protected EntityManager em;

	@Transactional
	public void persist(T t) {
		em.persist(t);
	}

	public List<T> findAll(Class<T> type) {
		return em
				.createQuery(
						String.format("select a from %s a",
								type.getSimpleName()), type).getResultList();
	}

	public T findById(Class<T> type, Object id) {
		return em.find(type, id);
	}
	
	@Transactional
	public void remove(T t) {
		try {
			em.remove(em.merge(t));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Transactional
	public void merge(T t) {
		em.merge(t);
	}
	
	public void referesh(T t) {
		em.refresh(t);
	}
}
