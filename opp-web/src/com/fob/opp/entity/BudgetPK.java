package com.fob.opp.entity;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the budget database table.
 * 
 */
@Embeddable
public class BudgetPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	private int id;

	@Column(name="enterpreneur_id", insertable=false, updatable=false)
	private int enterpreneurId;

	public BudgetPK() {
	}
	public int getId() {
		return this.id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getEnterpreneurId() {
		return this.enterpreneurId;
	}
	public void setEnterpreneurId(int enterpreneurId) {
		this.enterpreneurId = enterpreneurId;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof BudgetPK)) {
			return false;
		}
		BudgetPK castOther = (BudgetPK)other;
		return 
			(this.id == castOther.id)
			&& (this.enterpreneurId == castOther.enterpreneurId);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.id;
		hash = hash * prime + this.enterpreneurId;
		
		return hash;
	}
}