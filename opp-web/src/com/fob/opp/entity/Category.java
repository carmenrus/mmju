package com.fob.opp.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the category database table.
 * 
 */
@Entity
@NamedQuery(name="Category.findAll", query="SELECT c FROM Category c")
public class Category implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private String name;

	private byte type;

	//bi-directional many-to-one association to Expence
	@OneToMany(mappedBy="category")
	private List<Expence> expences;

	//bi-directional many-to-one association to MonthlyExpence
	@OneToMany(mappedBy="category")
	private List<MonthlyExpence> monthlyExpences;

	//bi-directional many-to-one association to StartupItem
	@OneToMany(mappedBy="category")
	private List<StartupItem> startupItems;

	public Category() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public byte getType() {
		return this.type;
	}

	public void setType(byte type) {
		this.type = type;
	}

	public List<Expence> getExpences() {
		return this.expences;
	}

	public void setExpences(List<Expence> expences) {
		this.expences = expences;
	}

	public Expence addExpence(Expence expence) {
		getExpences().add(expence);
		expence.setCategory(this);

		return expence;
	}

	public Expence removeExpence(Expence expence) {
		getExpences().remove(expence);
		expence.setCategory(null);

		return expence;
	}

	public List<MonthlyExpence> getMonthlyExpences() {
		return this.monthlyExpences;
	}

	public void setMonthlyExpences(List<MonthlyExpence> monthlyExpences) {
		this.monthlyExpences = monthlyExpences;
	}

	public MonthlyExpence addMonthlyExpence(MonthlyExpence monthlyExpence) {
		getMonthlyExpences().add(monthlyExpence);
		monthlyExpence.setCategory(this);

		return monthlyExpence;
	}

	public MonthlyExpence removeMonthlyExpence(MonthlyExpence monthlyExpence) {
		getMonthlyExpences().remove(monthlyExpence);
		monthlyExpence.setCategory(null);

		return monthlyExpence;
	}

	public List<StartupItem> getStartupItems() {
		return this.startupItems;
	}

	public void setStartupItems(List<StartupItem> startupItems) {
		this.startupItems = startupItems;
	}

	public StartupItem addStartupItem(StartupItem startupItem) {
		getStartupItems().add(startupItem);
		startupItem.setCategory(this);

		return startupItem;
	}

	public StartupItem removeStartupItem(StartupItem startupItem) {
		getStartupItems().remove(startupItem);
		startupItem.setCategory(null);

		return startupItem;
	}

}