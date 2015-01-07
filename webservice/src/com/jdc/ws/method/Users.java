package com.jdc.ws.method;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.BeanParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.jdc.ws.entity.Dao;
import com.jdc.ws.entity.Role;
import com.jdc.ws.entity.User;

@Path("users")
public class Users {
	
	@Inject
	private Dao<User> dao;
	
	@GET
	public List<User> findAll() {
		return dao.findAll(User.class);
	}
	
	@GET
	@Path("{id}")
	public User findById(@PathParam("id") int id) {
		return dao.findById(User.class, id);
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public User create(@FormParam("name") String name,@FormParam("pass") String pass) {
		User u = new User();
		u.setName(name);
		u.setPassword(pass);
		dao.persist(u);
		return u;
	}
	
	@POST
	@Path("json")
	@Consumes(MediaType.APPLICATION_JSON)
	public User createJSON(User u) {
		dao.persist(u);
		return u;
	}
	
	// not use now
	@PUT
	public User update(@BeanParam User u) {
		dao.merge(u);
		return u;
	}
	
	@DELETE
	@Path("{id}")
	public Response delete(@PathParam("id") int id) {
		User u = dao.findById(User.class, id);
		System.out.println(id);
		System.out.println(u.getName());
		dao.remove(u);
		return Response.noContent().build();
	}
	
	@GET
	@Path("{id}/roles")
	public List<Role> getRoles(@PathParam("id") int id) {
		return dao.findById(User.class, id).getRoles();
	}

}
