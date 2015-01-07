package com.fob.opp.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the startup_item database table.
 * 
 */
@Entity
@Table(name="startup_item")
@NamedQuery(name="StartupItem.findAll", query="SELECT s FROM StartupItem s")
public class StartupItem implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private Timestamp creation;

	private Timestamp modification;

	private String name;

	private double total;

	private int unit;

	@Column(name="unit_price")
	private double unitPrice;

	@Column(name="update_user")
	private String updateUser;

	//bi-directional many-to-one association to Budget
	@ManyToOne
	@JoinColumns({
		@JoinColumn(name="budget_enterpreneur_id", referencedColumnName="enterpreneur_id"),
		@JoinColumn(name="budget_id", referencedColumnName="id")
		})
	private Budget budget;

	//bi-directional many-to-one association to Category
	@ManyToOne
	private Category category;

	public StartupItem() {
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

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getTotal() {
		return this.total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public int getUnit() {
		return this.unit;
	}

	public void setUnit(int unit) {
		this.unit = unit;
	}

	public double getUnitPrice() {
		return this.unitPrice;
	}

	public void setUnitPrice(double unitPrice) {
		this.unitPrice = unitPrice;
	}

	public String getUpdateUser() {
		return this.updateUser;
	}

	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}

	public Budget getBudget() {
		return this.budget;
	}

	public void setBudget(Budget budget) {
		this.budget = budget;
	}

	public Category getCategory() {
		return this.category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

}