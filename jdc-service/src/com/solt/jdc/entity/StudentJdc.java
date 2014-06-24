package com.solt.jdc.entity;

import java.io.Serializable;

import javax.persistence.*;
import static javax.persistence.CascadeType.REFRESH;


/**
 * The persistent class for the student_jdc database table.
 * 
 */
@Entity
@Table(name="student_jdc")
@NamedQueries(value = { 
		@NamedQuery(name="StudentJdc.findAll", query="SELECT s FROM StudentJdc s"),
		@NamedQuery(name = "StudentJdc.getClassesByStudentId", query = "SELECT s FROM StudentJdc s where s.id.studentId = :studentId")})
public class StudentJdc implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private StudentJdcPK id = new StudentJdcPK();

	//uni-directional many-to-one association to JdcClass
	@ManyToOne(cascade = REFRESH)
	@JoinColumn(name="jdc_class_id")
	private JdcClass jdcClass;

	//uni-directional many-to-one association to Student
	@ManyToOne(cascade = REFRESH)
	private Student student;

	public StudentJdc() {
	}

	public StudentJdcPK getId() {
		return this.id;
	}

	public void setId(StudentJdcPK id) {
		this.id = id;
	}

	public JdcClass getJdcClass() {
		return this.jdcClass;
	}

	public void setJdcClass(JdcClass jdcClass) {
		this.id.setJdcClassId(jdcClass.getId());
		this.jdcClass = jdcClass;
	}

	public Student getStudent() {
		this.id.setStudentId(student.getId());
		return this.student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}
	
	@Override
	public String toString() {
		return this.jdcClass.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result
				+ ((jdcClass == null) ? 0 : jdcClass.hashCode());
		result = prime * result + ((student == null) ? 0 : student.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		StudentJdc other = (StudentJdc) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (jdcClass == null) {
			if (other.jdcClass != null)
				return false;
		} else if (!jdcClass.equals(other.jdcClass))
			return false;
		if (student == null) {
			if (other.student != null)
				return false;
		} else if (!student.equals(other.student))
			return false;
		return true;
	}

}