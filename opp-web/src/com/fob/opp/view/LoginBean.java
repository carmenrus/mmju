package com.fob.opp.view;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.fob.opp.bo.AccountDao;
import com.fob.opp.bo.Util;
import com.fob.opp.entity.Account;

@Named
@SessionScoped
public class LoginBean implements Serializable{

	private static final long serialVersionUID = 1L;
	private String name;
	private String pass;
	
	@Inject
	private AccountDao dao;
		
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	
	public String login() {
		Account member = dao.login(name, pass);
		if(null != member) {
			Util.getSession().setAttribute("user", member);
			if(member.getRole() == 1) {
				return "admin/home";
			} 
			return "enter/home";
		} 
		Util.getSession().invalidate();
		return "login";
	}
	
}
