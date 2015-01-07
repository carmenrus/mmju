package com.fob.opp.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the employee database table.
 * 
 */
@Entity
@NamedQuery(name="Employee.findAll", query="SELECT e FROM Employee e")
public class Employee implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private Timestamp creation;

	private Timestamp modification;

	@Column(name="month_ending")
	private int monthEnding;

	@Column(name="month_starting")
	private int monthStarting;

	@Column(name="monthly_wages")
	private double monthlyWages;

	private String name;

	@Column(name="update_user")
	private String updateUser;

	//bi-directional many-to-one association to Enterpreneur
	@ManyToOne
	private Enterpreneur enterpreneur;

	//bi-directional many-to-one association to Salary
	@OneToMany(mappedBy="employee")
	private List<Salary> salaries;

	public Employee() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
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

	public int getMonthEnding() {
		return this.monthEnding;
	}

	public void setMonthEnding(int monthEnding) {
		this.monthEnding = monthEnding;
	}

	public int getMonthStarting() {
		return this.monthStarting;
	}

	public void setMonthStarting(int monthStarting) {
		this.monthStarting = monthStarting;
	}

	public double getMonthlyWages() {
		return this.monthlyWages;
	}

	public void setMonthlyWages(double monthlyWages) {
		this.monthlyWages = monthlyWages;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUpdateUser() {
		return this.updateUser;
	}

	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}

	public Enterpreneur getEnterpreneur() {
		return this.enterpreneur;
	}

	public void setEnterpreneur(Enterpreneur enterpreneur) {
		this.enterpreneur = enterpreneur;
	}

	public List<Salary> getSalaries() {
		return this.salaries;
	}

	public void setSalaries(List<Salary> salaries) {
		this.salaries = salaries;
	}

	public Salary addSalary(Salary salary) {
		getSalaries().add(salary);
		salary.setEmployee(this);

		return salary;
	}

	public Salary removeSalary(Salary salary) {
		getSalaries().remove(salary);
		salary.setEmployee(null);

		return salary;
	}

}