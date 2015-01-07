package com.jdc.ws.client;

import java.util.List;

import com.jdc.ws.entity.Role;
import com.jdc.ws.entity.User;

public interface ClientService {
	
	public List<User> getUsers();
	public List<Role> getRoles();
	public void deleteUser(int id);
	public User addUser(User user);

}
