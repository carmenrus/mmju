package com.solt.jdc.services;

import javax.inject.Inject;
import javax.ws.rs.Path;

import com.solt.jdc.entity.Bill;
import com.solt.jdc.entity.Student;
import com.solt.jdc.model.Dao;

@Path("/bill")
public class BillService extends AbstractService<Bill> {
	
	@Inject
	Dao<Student> studentDao;
	
	@Override
	public void setClass() {
		super.entity = Bill.class;
	}
	
	@Override
	public Bill create(Bill b) {
		if(b.getStudent().getId() == 0) {
			studentDao.persist(b.getStudent());
		}
		return super.create(b);
	}
}
