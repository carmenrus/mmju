package com.solt.jdc.entity;

import java.io.Serializable;

import javax.persistence.*;


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
	@ManyToOne
	@JoinColumn(name="jdc_class_id")
	private JdcClass jdcClass;

	//uni-directional many-to-one association to Student
	@ManyToOne
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

}