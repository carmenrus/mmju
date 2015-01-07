package com.fob.opp.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the expence database table.
 * 
 */
@Entity
@NamedQuery(name="Expence.findAll", query="SELECT e FROM Expence e")
public class Expence implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private Timestamp creation;

	private Timestamp modification;

	private String title;

	private double total;

	@Column(name="update_user")
	private String updateUser;

	//bi-directional many-to-one association to Category
	@ManyToOne
	private Category category;

	//bi-directional many-to-one association to Salary
	@OneToMany(mappedBy="expence")
	private List<Salary> salaries;

	public Expence() {
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

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public double getTotal() {
		return this.total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public String getUpdateUser() {
		return this.updateUser;
	}

	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}

	public Category getCategory() {
		return this.category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public List<Salary> getSalaries() {
		return this.salaries;
	}

	public void setSalaries(List<Salary> salaries) {
		this.salaries = salaries;
	}

	public Salary addSalary(Salary salary) {
		getSalaries().add(salary);
		salary.setExpence(this);

		return salary;
	}

	public Salary removeSalary(Salary salary) {
		getSalaries().remove(salary);
		salary.setExpence(null);

		return salary;
	}

}