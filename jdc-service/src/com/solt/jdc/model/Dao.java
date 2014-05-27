package com.solt.jdc.model;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
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
		System.out.println(sb.toString());
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
	public void update(Class<T> entityClass, Object id, Map<String, Object> param) {
		StringBuffer sb = new StringBuffer("update ");
		sb.append(entityClass.getSimpleName());
		sb.append(" c ");
		sb.append(this.getSetParam(param));
		sb.append(" where c.id = :id");
		
		Query q = em.createQuery(sb.toString());
		q.setParameter("id", id);
		this.setParam(q, param);
		q.executeUpdate();
		
	}
	
	@Transactional
	public T update(T t) {
		this.em.merge(t);
		return t;
	}
	
	private String getSetParam(Map<String, Object> param) {
		return null;
	}
	
	private void setParam(Query q, Map<String, Object> param) {
		for(Entry<String, Object> e : param.entrySet()) {
			q.setParameter(e.getKey(), e.getValue());
		}
	}
	
}
