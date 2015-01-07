package com.fob.opp.entity;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the account_message database table.
 * 
 */
@Embeddable
public class AccountMessagePK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="account_id", insertable=false, updatable=false)
	private String accountId;

	@Column(name="message_id", insertable=false, updatable=false)
	private int messageId;

	public AccountMessagePK() {
	}
	public String getAccountId() {
		return this.accountId;
	}
	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}
	public int getMessageId() {
		return this.messageId;
	}
	public void setMessageId(int messageId) {
		this.messageId = messageId;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof AccountMessagePK)) {
			return false;
		}
		AccountMessagePK castOther = (AccountMessagePK)other;
		return 
			this.accountId.equals(castOther.accountId)
			&& (this.messageId == castOther.messageId);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.accountId.hashCode();
		hash = hash * prime + this.messageId;
		
		return hash;
	}
}