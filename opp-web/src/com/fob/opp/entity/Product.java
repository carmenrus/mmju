package com.fob.opp.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the product database table.
 * 
 */
@Entity
@NamedQuery(name="Product.findAll", query="SELECT p FROM Product p")
public class Product implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private Timestamp creation;

	private Timestamp modification;

	@Column(name="month_intro")
	private int monthIntro;

	private String name;

	@Column(name="number_of_unit")
	private int numberOfUnit;

	@Column(name="profit_per_unit")
	private double profitPerUnit;

	@Column(name="unit_cost")
	private double unitCost;

	@Column(name="unit_price")
	private double unitPrice;

	@Column(name="update_user")
	private String updateUser;

	//bi-directional many-to-one association to Sale
	@OneToMany(mappedBy="product")
	private List<Sale> sales;

	public Product() {
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

	public int getMonthIntro() {
		return this.monthIntro;
	}

	public void setMonthIntro(int monthIntro) {
		this.monthIntro = monthIntro;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getNumberOfUnit() {
		return this.numberOfUnit;
	}

	public void setNumberOfUnit(int numberOfUnit) {
		this.numberOfUnit = numberOfUnit;
	}

	public double getProfitPerUnit() {
		return this.profitPerUnit;
	}

	public void setProfitPerUnit(double profitPerUnit) {
		this.profitPerUnit = profitPerUnit;
	}

	public double getUnitCost() {
		return this.unitCost;
	}

	public void setUnitCost(double unitCost) {
		this.unitCost = unitCost;
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

	public List<Sale> getSales() {
		return this.sales;
	}

	public void setSales(List<Sale> sales) {
		this.sales = sales;
	}

	public Sale addSale(Sale sale) {
		getSales().add(sale);
		sale.setProduct(this);

		return sale;
	}

	public Sale removeSale(Sale sale) {
		getSales().remove(sale);
		sale.setProduct(null);

		return sale;
	}

}