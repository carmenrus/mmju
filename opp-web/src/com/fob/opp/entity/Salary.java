package com.fob.opp.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.sql.Timestamp;


/**
 * The persistent class for the salary database table.
 * 
 */
@Entity
@NamedQuery(name="Salary.findAll", query="SELECT s FROM Salary s")
public class Salary implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private double amount;

	@Temporal(TemporalType.DATE)
	@Column(name="biz_date")
	private Date bizDate;

	private Timestamp creation;

	private Timestamp modification;

	private byte status;

	@Column(name="update_user")
	private String updateUser;

	//bi-directional many-to-one association to Employee
	@ManyToOne
	private Employee employee;

	//bi-directional many-to-one association to Expence
	@ManyToOne
	private Expence expence;

	public Salary() {
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

	public Date getBizDate() {
		return this.bizDate;
	}

	public void setBizDate(Date bizDate) {
		this.bizDate = bizDate;
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

	public Employee getEmployee() {
		return this.employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public Expence getExpence() {
		return this.expence;
	}

	public void setExpence(Expence expence) {
		this.expence = expence;
	}

}