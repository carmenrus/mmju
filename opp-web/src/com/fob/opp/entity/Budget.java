package com.fob.opp.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the budget database table.
 * 
 */
@Entity
@NamedQuery(name="Budget.findAll", query="SELECT b FROM Budget b")
public class Budget implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private BudgetPK id;

	private String remark;

	private double total;

	//bi-directional many-to-one association to Enterpreneur
	@ManyToOne
	private Enterpreneur enterpreneur;

	//bi-directional many-to-one association to MonthlyExpence
	@OneToMany(mappedBy="budget")
	private List<MonthlyExpence> monthlyExpences;

	//bi-directional many-to-one association to StartupItem
	@OneToMany(mappedBy="budget")
	private List<StartupItem> startupItems;

	public Budget() {
	}

	public BudgetPK getId() {
		return this.id;
	}

	public void setId(BudgetPK id) {
		this.id = id;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public double getTotal() {
		return this.total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public Enterpreneur getEnterpreneur() {
		return this.enterpreneur;
	}

	public void setEnterpreneur(Enterpreneur enterpreneur) {
		this.enterpreneur = enterpreneur;
	}

	public List<MonthlyExpence> getMonthlyExpences() {
		return this.monthlyExpences;
	}

	public void setMonthlyExpences(List<MonthlyExpence> monthlyExpences) {
		this.monthlyExpences = monthlyExpences;
	}

	public MonthlyExpence addMonthlyExpence(MonthlyExpence monthlyExpence) {
		getMonthlyExpences().add(monthlyExpence);
		monthlyExpence.setBudget(this);

		return monthlyExpence;
	}

	public MonthlyExpence removeMonthlyExpence(MonthlyExpence monthlyExpence) {
		getMonthlyExpences().remove(monthlyExpence);
		monthlyExpence.setBudget(null);

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
		startupItem.setBudget(this);

		return startupItem;
	}

	public StartupItem removeStartupItem(StartupItem startupItem) {
		getStartupItems().remove(startupItem);
		startupItem.setBudget(null);

		return startupItem;
	}

}