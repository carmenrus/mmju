package com.solt.jdc.services;

import javax.inject.Inject;
import javax.ws.rs.Path;

import com.solt.jdc.entity.Bill;
import com.solt.jdc.model.BillModel;

@Path("/bill")
public class BillService extends AbstractService<Bill> {
	
	@Inject
	private BillModel billModel;
	
	@Override
	public void setClass() {
		super.entity = Bill.class;
	}
	
	@Override
	public Bill create(Bill b) {
		// student
		return billModel.persist(b);
	}
}
