package com.fob.opp.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.sql.Timestamp;


/**
 * The persistent class for the payment database table.
 * 
 */
@Entity
@NamedQuery(name="Payment.findAll", query="SELECT p FROM Payment p")
public class Payment implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private double amount;

	private Timestamp creation;

	private Timestamp modification;

	@Temporal(TemporalType.DATE)
	private Date month;

	private String status;

	@Column(name="update_user")
	private String updateUser;

	//bi-directional many-to-one association to Loan
	@ManyToOne
	@JoinColumns({
		@JoinColumn(name="enterpreneur_id", referencedColumnName="enterpreneur_id"),
		@JoinColumn(name="fee_agreement_id", referencedColumnName="fee_agreement_id")
		})
	private Loan loan;

	public Payment() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getAmount() {
		return this.amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public Timestamp getCreation() {
		return this.creation;
	}

	public void setCreation(Timestamp creation) {
		this.creation = creation;
	}

	public Timestamp getModification() {
		return this.modification;
	}

	public void setModification(Timestamp modification) {
		this.modification = modification;
	}

	public Date getMonth() {
		return this.month;
	}

	public void setMonth(Date month) {
		this.month = month;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getUpdateUser() {
		return this.updateUser;
	}

	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}

	public Loan getLoan() {
		return this.loan;
	}

	public void setLoan(Loan loan) {
		this.loan = loan;
	}

}