package com.solt.jdc.entity;

import java.io.Serializable;

import javax.persistence.*;

import java.util.Date;


/**
 * The persistent class for the transaction database table.
 * 
 */
@Entity
@NamedQueries(value = { 
	@NamedQuery(name="Transaction.findAll", query="SELECT t FROM Transaction t order by t.creation"),
	@NamedQuery(name="Transaction.findAllFrom", query="SELECT t FROM Transaction t where t.creation >= :from order by t.creation"),
	@NamedQuery(name="Transaction.findAllFromTo", query="SELECT t FROM Transaction t where t.creation >= :from and t.creation <= :to order by t.creation")
	})
public class Transaction implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private String comment;

	@Temporal(TemporalType.DATE)
	private Date creation;

	private int income;

	@Temporal(TemporalType.DATE)
	private Date modification;

	private int outcome;
	
	@Transient
	private int balance;

	public int getBalance() {
		return balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}

	public Transaction() {
		this.creation = new Date();
		this.modification = this.creation;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getComment() {
		return this.comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Date getCreation() {
		return this.creation;
	}

	public void setCreation(Date creation) {
		this.creation = creation;
	}

	public int getIncome() {
		return this.income;
	}

	public void setIncome(int income) {
		this.income = income;
	}

	public Date getModification() {
		return this.modification;
	}

	public void setModification(Date modification) {
		this.modification = modification;
	}

	public int getOutcome() {
		return this.outcome;
	}

	public void setOutcome(int outcome) {
		this.outcome = outcome;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((comment == null) ? 0 : comment.hashCode());
		result = prime * result
				+ ((creation == null) ? 0 : creation.hashCode());
		result = prime * result + id;
		result = prime * result + income;
		result = prime * result
				+ ((modification == null) ? 0 : modification.hashCode());
		result = prime * result + outcome;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Transaction other = (Transaction) obj;
		if (comment == null) {
			if (other.comment != null)
				return false;
		} else if (!comment.equals(other.comment))
			return false;
		if (creation == null) {
			if (other.creation != null)
				return false;
		} else if (!creation.equals(other.creation))
			return false;
		if (id != other.id)
			return false;
		if (income != other.income)
			return false;
		if (modification == null) {
			if (other.modification != null)
				return false;
		} else if (!modification.equals(other.modification))
			return false;
		if (outcome != other.outcome)
			return false;
		return true;
	}

}