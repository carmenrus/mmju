package com.solt.jdc.model;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import com.solt.jdc.entity.Bill;
import com.solt.jdc.entity.StudentJdc;

public class StudentModel {

	@Inject
	private EntityManager em;

	public List<StudentJdc> getClasses(int id) {
		return em
				.createNamedQuery("StudentJdc.getClassesByStudentId",
						StudentJdc.class).setParameter("studentId", id)
				.getResultList();
	}
	
	public List<Bill> getBillByStudentClass(int studentId, int classId) {
		return em
				.createNamedQuery("Bill.getBillByStudentJdc",
						Bill.class).setParameter("studentId", studentId).setParameter("jdcClassId", classId)
				.getResultList();
	}
}
