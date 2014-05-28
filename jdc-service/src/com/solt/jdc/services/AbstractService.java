package com.solt.jdc.services;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.websocket.server.PathParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import com.solt.jdc.model.Dao;

public abstract class AbstractService<T> {
	
	@Context
	protected UriInfo uri;
	@Inject
	protected Dao<T> dao;
	
	protected Class<T> entity;

	@PostConstruct
	public void init() {
		this.setClass();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<T> getAll() {
		return this.dao.find(entity);
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public T create(T time) {
		this.dao.persist(time);
		return time;
	}
	
	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public T find(@PathParam("id") int id) {
		return this.dao.find(entity, id);
	}

	
	@DELETE
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response delete(@PathParam("id") int id) {
		this.dao.delete(entity, id);
		return Response.noContent().build();
	}

	public abstract void setClass();
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public T update(T t) {
		this.dao.update(t);
		return t;
	}
}
