package com.fob.opp.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the account database table.
 * 
 */
@Entity
@NamedQueries(value={
		@NamedQuery(name="Account.findAll", query="SELECT a FROM Account a"),
		@NamedQuery(name="Account.login", query="SELECT a FROM Account a where a.loginId = :loginId and a.password = :password"),
		})
public class Account implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="login_id")
	private String loginId;

	private String password;

	private byte role;

	//bi-directional many-to-one association to Enterpreneur
	@ManyToOne
	private Enterpreneur enterpreneur;

	//bi-directional many-to-one association to AccountMessage
	@OneToMany(mappedBy="account")
	private List<AccountMessage> accountMessages;

	//bi-directional many-to-one association to Message
	@OneToMany(mappedBy="account")
	private List<Message> messages;

	public Account() {
	}

	public String getLoginId() {
		return this.loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public byte getRole() {
		return this.role;
	}

	public void setRole(byte role) {
		this.role = role;
	}

	public Enterpreneur getEnterpreneur() {
		return this.enterpreneur;
	}

	public void setEnterpreneur(Enterpreneur enterpreneur) {
		this.enterpreneur = enterpreneur;
	}

	public List<AccountMessage> getAccountMessages() {
		return this.accountMessages;
	}

	public void setAccountMessages(List<AccountMessage> accountMessages) {
		this.accountMessages = accountMessages;
	}

	public AccountMessage addAccountMessage(AccountMessage accountMessage) {
		getAccountMessages().add(accountMessage);
		accountMessage.setAccount(this);

		return accountMessage;
	}

	public AccountMessage removeAccountMessage(AccountMessage accountMessage) {
		getAccountMessages().remove(accountMessage);
		accountMessage.setAccount(null);

		return accountMessage;
	}

	public List<Message> getMessages() {
		return this.messages;
	}

	public void setMessages(List<Message> messages) {
		this.messages = messages;
	}

	public Message addMessage(Message message) {
		getMessages().add(message);
		message.setAccount(this);

		return message;
	}

	public Message removeMessage(Message message) {
		getMessages().remove(message);
		message.setAccount(null);

		return message;
	}

}