package com.fob.opp.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.sql.Timestamp;


/**
 * The persistent class for the sale database table.
 * 
 */
@Entity
@NamedQuery(name="Sale.findAll", query="SELECT s FROM Sale s")
public class Sale implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private int amount;

	@Temporal(TemporalType.DATE)
	@Column(name="biz_date")
	private Date bizDate;

	private Timestamp creation;

	private Timestamp modification;

	private double tax;

	private double total;

	@Column(name="update_user")
	private String updateUser;

	//bi-directional many-to-one association to Product
	@ManyToOne
	private Product product;

	public Sale() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getAmount() {
		return this.amount;
	}

	public void setAmount(int amount) {
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

	public double getTax() {
		return this.tax;
	}

	public void setTax(double tax) {
		this.tax = tax;
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

	public Product getProduct() {
		return this.product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

}