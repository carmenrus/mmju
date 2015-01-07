package com.fob.opp.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the account_message database table.
 * 
 */
@Entity
@Table(name="account_message")
@NamedQuery(name="AccountMessage.findAll", query="SELECT a FROM AccountMessage a")
public class AccountMessage implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private AccountMessagePK id;

	@Column(name="send_date")
	private Timestamp sendDate;

	private byte status;

	//bi-directional many-to-one association to Account
	@ManyToOne
	@JoinColumn(name="account_id")
	private Account account;

	//bi-directional many-to-one association to Message
	@ManyToOne
	private Message message;

	public AccountMessage() {
	}

	public AccountMessagePK getId() {
		return this.id;
	}

	public void setId(AccountMessagePK id) {
		this.id = id;
	}

	public Timestamp getSendDate() {
		return this.sendDate;
	}

	public void setSendDate(Timestamp sendDate) {
		this.sendDate = sendDate;
	}

	public byte getStatus() {
		return this.status;
	}

	public void setStatus(byte status) {
		this.status = status;
	}

	public Account getAccount() {
		return this.account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public Message getMessage() {
		return this.message;
	}

	public void setMessage(Message message) {
		this.message = message;
	}

}