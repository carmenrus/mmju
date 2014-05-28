package com.solt.jdc.entity;

import java.io.Serializable;

import javax.persistence.*;

import java.util.Date;

import static javax.persistence.CascadeType.PERSIST;
import static javax.persistence.CascadeType.REFRESH;
import static javax.persistence.CascadeType.MERGE;


/**
 * The persistent class for the bill database table.
 * 
 */
@Entity
@NamedQueries(value = { @NamedQuery(name="Bill.findAll", query="SELECT b FROM Bill b"),
		@NamedQuery(name="Bill.findByStudent", query="SELECT b FROM Bill b where b.student.id =:student_id")})
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

	private int status;

	private int total;

	//uni-directional many-to-one association to JdcClass
	@ManyToOne
	@JoinColumn(name="jdc_class_id")
	private JdcClass jdcClass;

	//uni-directional many-to-one association to Student
	@ManyToOne
	private Student student;

	//uni-directional many-to-one association to Transaction
	@ManyToOne(cascade = { PERSIST, MERGE, REFRESH })
	private Transaction transaction;

	public Bill() {
		this.creation = new Date();
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

	public int getStatus() {
		return this.status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getTotal() {
		return this.total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public JdcClass getJdcClass() {
		return this.jdcClass;
	}

	public void setJdcClass(JdcClass jdcClass) {
		this.jdcClass = jdcClass;
	}

	public Student getStudent() {
		return this.student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public Transaction getTransaction() {
		return this.transaction;
	}

	public void setTransaction(Transaction transaction) {
		this.transaction = transaction;
	}

}