package com.solt.jdc.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the output database table.
 * 
 */
@Entity
@NamedQuery(name="Output.findAll", query="SELECT o FROM Output o")
public class Output implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private int catagory;

	private String comment;

	@Temporal(TemporalType.DATE)
	private Date creation;

	@Temporal(TemporalType.DATE)
	private Date modification;

	private int outcome;

	private String stuff;

	//uni-directional many-to-one association to Transaction
	@ManyToOne
	private Transaction transaction;

	public Output() {
		this.creation = new Date();
		this.modification = this.creation;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCatagory() {
		return this.catagory;
	}

	public void setCatagory(int catagory) {
		this.catagory = catagory;
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

	public String getStuff() {
		return this.stuff;
	}

	public void setStuff(String stuff) {
		this.stuff = stuff;
	}

	public Transaction getTransaction() {
		return this.transaction;
	}

	public void setTransaction(Transaction transaction) {
		this.transaction = transaction;
	}

}