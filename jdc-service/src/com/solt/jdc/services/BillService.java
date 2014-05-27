package com.solt.jdc.services;

import java.util.Date;

import com.solt.jdc.entity.Bill;

public class BillService extends AbstractService<Bill> {

	@Override
	public void setClass() {
		super.entity = Bill.class;
	}

	@Override
	public Bill update(Bill t) {
		Bill c = find(t.getId());
		c.setDiscount(t.getDiscount());
		c.setJdcClass(t.getJdcClass());
		c.setModification(new Date());
		c.setPaid(t.getPaid());
		c.setRemain(t.getRemain());
		c.setStatus(t.getStatus());
		c.setStudent(t.getStudent());
		c.setTotal(t.getTotal());
		c.setTransaction(t.getTransaction());
		return dao.update(c);
	}

}
