package com.jdc.ws.client;

import java.util.List;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.jdc.ws.entity.Role;
import com.jdc.ws.entity.User;

public class ClientServiceImp implements ClientService {

	private static String BASE_URL = "http://localhost:8080/webservice/rs/%s";

	@Override
	public List<User> getUsers() {
		return getTarget("users").request(MediaType.APPLICATION_JSON)
				.buildGet().invoke().readEntity(new GenericType<List<User>>() {
				});
	}

	@Override
	public List<Role> getRoles() {
		return getTarget("roles").request(MediaType.APPLICATION_JSON)
				.buildGet().invoke().readEntity(new GenericType<List<Role>>() {
				});
	}

	@Override
	public void deleteUser(int id) {
		getTarget("users").path(String.valueOf(id)).request().buildDelete().invoke();
	}

	@Override
	public User addUser(User user) {
		Invocation inv = getTarget("users").path("json").request()
				.buildPost(Entity.entity(user, MediaType.APPLICATION_JSON));
		Response resp = inv.invoke();
		return resp.readEntity(User.class);
	}

	private WebTarget getTarget(String resource) {
		return ClientBuilder.newClient().target(
				String.format(BASE_URL, resource));
	}

}
