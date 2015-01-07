package com.fob.opp.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the loan database table.
 * 
 */
@Entity
@NamedQuery(name="Loan.findAll", query="SELECT l FROM Loan l")
public class Loan implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private LoanPK id;

	private double amount;

	private byte currency;

	@Column(name="exchange_rate")
	private String exchangeRate;

	private int years;

	//bi-directional many-to-one association to Enterpreneur
	@ManyToOne
	private Enterpreneur enterpreneur;

	//bi-directional many-to-one association to FeeAgreement
	@ManyToOne
	@JoinColumn(name="fee_agreement_id")
	private FeeAgreement feeAgreement;

	//bi-directional many-to-one association to Payment
	@OneToMany(mappedBy="loan")
	private List<Payment> payments;

	public Loan() {
	}

	public LoanPK getId() {
		return this.id;
	}

	public void setId(LoanPK id) {
		this.id = id;
	}

	public double getAmount() {
		return this.amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public byte getCurrency() {
		return this.currency;
	}

	public void setCurrency(byte currency) {
		this.currency = currency;
	}

	public String getExchangeRate() {
		return this.exchangeRate;
	}

	public void setExchangeRate(String exchangeRate) {
		this.exchangeRate = exchangeRate;
	}

	public int getYears() {
		return this.years;
	}

	public void setYears(int years) {
		this.years = years;
	}

	public Enterpreneur getEnterpreneur() {
		return this.enterpreneur;
	}

	public void setEnterpreneur(Enterpreneur enterpreneur) {
		this.enterpreneur = enterpreneur;
	}

	public FeeAgreement getFeeAgreement() {
		return this.feeAgreement;
	}

	public void setFeeAgreement(FeeAgreement feeAgreement) {
		this.feeAgreement = feeAgreement;
	}

	public List<Payment> getPayments() {
		return this.payments;
	}

	public void setPayments(List<Payment> payments) {
		this.payments = payments;
	}

	public Payment addPayment(Payment payment) {
		getPayments().add(payment);
		payment.setLoan(this);

		return payment;
	}

	public Payment removePayment(Payment payment) {
		getPayments().remove(payment);
		payment.setLoan(null);

		return payment;
	}

}