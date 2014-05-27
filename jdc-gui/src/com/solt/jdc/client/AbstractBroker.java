package com.solt.jdc.client;

import java.util.List;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

public abstract class AbstractBroker<T> {

	public abstract List<T> getAll();

	public AbstractBroker(String resource) {
		this.resource = resource;
	}

	public AbstractBroker(Class<T> resource) {
		this.resource = resource.getSimpleName().toLowerCase();
	}

	public T persist(T t, Class<T> entityClass) {
		return this.baseTarget().request()
				.buildPost(Entity.entity(t, MediaType.APPLICATION_JSON))
				.invoke().readEntity(entityClass);
	}

	public void delete(String id) {
		this.baseTarget().path(id).request(MediaType.TEXT_PLAIN).buildDelete()
				.invoke();
	}

	public T find(Class<T> entityClass, String id) {
		return this.baseTarget().path(id).request(MediaType.APPLICATION_JSON)
				.buildGet().invoke().readEntity(entityClass);
	}
	
	public T update(T t, Class<T> entityClass) {
		return this.baseTarget().request()
				.buildPut(Entity.entity(t, MediaType.APPLICATION_JSON))
				.invoke().readEntity(entityClass);
	}

	protected static final String BASE_URL = "http://localhost:8080/jdc-service/rs/";
	protected String resource;

	protected String baseUrlString() {
		return BASE_URL + resource;
	}

	protected WebTarget baseTarget() {
		return ClientBuilder.newClient().target(baseUrlString());
	}
	
}
