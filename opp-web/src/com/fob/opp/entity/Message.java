package com.fob.opp.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the message database table.
 * 
 */
@Entity
@NamedQuery(name="Message.findAll", query="SELECT m FROM Message m")
public class Message implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	@Lob
	private byte[] body;

	private Timestamp creation;

	private String title;

	private byte type;

	//bi-directional many-to-one association to AccountMessage
	@OneToMany(mappedBy="message")
	private List<AccountMessage> accountMessages;

	//bi-directional many-to-one association to Account
	@ManyToOne
	@JoinColumn(name="from_user")
	private Account account;

	public Message() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public byte[] getBody() {
		return this.body;
	}

	public void setBody(byte[] body) {
		this.body = body;
	}

	public Timestamp getCreation() {
		return this.creation;
	}

	public void setCreation(Timestamp creation) {
		this.creation = creation;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public byte getType() {
		return this.type;
	}

	public void setType(byte type) {
		this.type = type;
	}

	public List<AccountMessage> getAccountMessages() {
		return this.accountMessages;
	}

	public void setAccountMessages(List<AccountMessage> accountMessages) {
		this.accountMessages = accountMessages;
	}

	public AccountMessage addAccountMessage(AccountMessage accountMessage) {
		getAccountMessages().add(accountMessage);
		accountMessage.setMessage(this);

		return accountMessage;
	}

	public AccountMessage removeAccountMessage(AccountMessage accountMessage) {
		getAccountMessages().remove(accountMessage);
		accountMessage.setMessage(null);

		return accountMessage;
	}

	public Account getAccount() {
		return this.account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

}