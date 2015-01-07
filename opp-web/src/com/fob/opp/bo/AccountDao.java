package com.fob.opp.bo;

import java.io.Serializable;
import java.util.List;

import javax.persistence.TypedQuery;

import com.fob.opp.entity.Account;

public class AccountDao extends Dao<Account> implements Serializable{
	private static final long serialVersionUID = 1L;

	public Account login(String login, String pass) {
		TypedQuery<Account> query = super.em.createNamedQuery("Account.login", Account.class);
		query.setParameter("loginId", login);
		query.setParameter("password", pass);
		List<Account> list = query.getResultList();
		if(list.size() > 0) {
			return list.get(0);
		}
		return null;
	}
}
