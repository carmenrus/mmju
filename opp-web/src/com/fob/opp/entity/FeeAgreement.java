package com.fob.opp.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the fee_agreement database table.
 * 
 */
@Entity
@Table(name="fee_agreement")
@NamedQuery(name="FeeAgreement.findAll", query="SELECT f FROM FeeAgreement f")
public class FeeAgreement implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private String calculation;

	private Timestamp creation;

	@Column(name="interest_rate")
	private String interestRate;

	private Timestamp modification;

	private String name;

	private byte status;

	@Column(name="update_user")
	private String updateUser;

	//bi-directional many-to-one association to Loan
	@OneToMany(mappedBy="feeAgreement")
	private List<Loan> loans;

	public FeeAgreement() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCalculation() {
		return this.calculation;
	}

	public void setCalculation(String calculation) {
		this.calculation = calculation;
	}

	public Timestamp getCreation() {
		return this.creation;
	}

	public void setCreation(Timestamp creation) {
		this.creation = creation;
	}

	public String getInterestRate() {
		return this.interestRate;
	}

	public void setInterestRate(String interestRate) {
		this.interestRate = interestRate;
	}

	public Timestamp getModification() {
		return this.modification;
	}

	public void setModification(Timestamp modification) {
		this.modification = modification;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public byte getStatus() {
		return this.status;
	}

	public void setStatus(byte status) {
		this.status = status;
	}

	public String getUpdateUser() {
		return this.updateUser;
	}

	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}

	public List<Loan> getLoans() {
		return this.loans;
	}

	public void setLoans(List<Loan> loans) {
		this.loans = loans;
	}

	public Loan addLoan(Loan loan) {
		getLoans().add(loan);
		loan.setFeeAgreement(this);

		return loan;
	}

	public Loan removeLoan(Loan loan) {
		getLoans().remove(loan);
		loan.setFeeAgreement(null);

		return loan;
	}

}