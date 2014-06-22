package com.solt.jdc.entity;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the student_jdc database table.
 * 
 */
@Embeddable
public class StudentJdcPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="student_id", insertable=false, updatable=false)
	private int studentId;

	@Column(name="jdc_class_id", insertable=false, updatable=false)
	private int jdcClassId;

	public StudentJdcPK() {
	}
	public int getStudentId() {
		return this.studentId;
	}
	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}
	public int getJdcClassId() {
		return this.jdcClassId;
	}
	public void setJdcClassId(int jdcClassId) {
		this.jdcClassId = jdcClassId;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof StudentJdcPK)) {
			return false;
		}
		StudentJdcPK castOther = (StudentJdcPK)other;
		return 
			(this.studentId == castOther.studentId)
			&& (this.jdcClassId == castOther.jdcClassId);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.studentId;
		hash = hash * prime + this.jdcClassId;
		
		return hash;
	}
}