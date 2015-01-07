package com.fob.opp.entity;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the loan database table.
 * 
 */
@Embeddable
public class LoanPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="enterpreneur_id", insertable=false, updatable=false)
	private int enterpreneurId;

	@Column(name="fee_agreement_id", insertable=false, updatable=false)
	private int feeAgreementId;

	public LoanPK() {
	}
	public int getEnterpreneurId() {
		return this.enterpreneurId;
	}
	public void setEnterpreneurId(int enterpreneurId) {
		this.enterpreneurId = enterpreneurId;
	}
	public int getFeeAgreementId() {
		return this.feeAgreementId;
	}
	public void setFeeAgreementId(int feeAgreementId) {
		this.feeAgreementId = feeAgreementId;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof LoanPK)) {
			return false;
		}
		LoanPK castOther = (LoanPK)other;
		return 
			(this.enterpreneurId == castOther.enterpreneurId)
			&& (this.feeAgreementId == castOther.feeAgreementId);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.enterpreneurId;
		hash = hash * prime + this.feeAgreementId;
		
		return hash;
	}
}