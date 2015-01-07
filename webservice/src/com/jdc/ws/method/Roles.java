package com.jdc.ws.method;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.BeanParam;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

import com.jdc.ws.entity.Dao;
import com.jdc.ws.entity.Role;
import com.jdc.ws.entity.User;

@Path("roles")
public class Roles {
	
	@Inject
	private Dao<Role> dao;
	
	@GET
	public List<Role> findAll() {
		return dao.findAll(Role.class);
	}
	
	@GET
	@Path("{id}")
	public Role findById(@PathParam("id") int id) {
		return dao.findById(Role.class, id);
	}
	
	@POST
	public Role create(@BeanParam Role u) {
		dao.persist(u);
		return u;
	}
	
	@PUT
	public Role update(@BeanParam Role u) {
		dao.merge(u);
		return u;
	}
	
	@DELETE
	@Path("{id}")
	public Response delete(@PathParam("id") int id) {
		dao.remove(dao.findById(Role.class, id));
		return Response.noContent().build();
	}


	@GET
	@Path("{id}/roles")
	public List<User> getUsers(@PathParam("id") int id) {
		return dao.findById(Role.class, id).getUsers();
	}


}
