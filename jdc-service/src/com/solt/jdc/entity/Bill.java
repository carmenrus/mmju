package com.solt.jdc.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import static javax.persistence.CascadeType.PERSIST;


/**
 * The persistent class for the bill database table.
 * 
 */
@Entity
@NamedQuery(name="Bill.findAll", query="SELECT b FROM Bill b")
public class Bill implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	@Temporal(TemporalType.DATE)
	private Date creation;

	private int discount;

	@Temporal(TemporalType.DATE)
	private Date modification;

	private int paid;

	private int remain;

	private int total;

	//uni-directional many-to-one association to StudentJdc
	@ManyToOne(cascade = PERSIST)
	@JoinColumns({
		@JoinColumn(name="jdc_class_id", referencedColumnName="jdc_class_id"),
		@JoinColumn(name="student_id", referencedColumnName="student_id")
		})
	private StudentJdc studentJdc;

	//uni-directional many-to-one association to Transaction
	@ManyToOne(cascade = PERSIST)
	private Transaction transaction;

	public Bill() {
		this.creation = new Date();
		this.modification = this.creation;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getCreation() {
		return this.creation;
	}

	public void setCreation(Date creation) {
		this.creation = creation;
	}

	public int getDiscount() {
		return this.discount;
	}

	public void setDiscount(int discount) {
		this.discount = discount;
	}

	public Date getModification() {
		return this.modification;
	}

	public void setModification(Date modification) {
		this.modification = modification;
	}

	public int getPaid() {
		return this.paid;
	}

	public void setPaid(int paid) {
		this.paid = paid;
	}

	public int getRemain() {
		return this.remain;
	}

	public void setRemain(int remain) {
		this.remain = remain;
	}

	public int getTotal() {
		return this.total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public StudentJdc getStudentJdc() {
		return this.studentJdc;
	}

	public void setStudentJdc(StudentJdc studentJdc) {
		this.studentJdc = studentJdc;
	}

	public Transaction getTransaction() {
		return this.transaction;
	}

	public void setTransaction(Transaction transaction) {
		this.transaction = transaction;
	}

}