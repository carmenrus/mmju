package com.solt.jdc.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.solt.jdc.entity.Bill;
import com.solt.jdc.entity.Student;
import com.solt.jdc.entity.StudentJdc;
import com.solt.jdc.model.StudentModel;

@Path("/student")
public class StudentService extends AbstractService<Student> {
	
	@Inject
	private StudentModel studentModel;
	
	@Inject
	private BillService billService;
	
	@Override
	public void setClass() {
		super.entity = Student.class;
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("jdc/{id}")
	public List<StudentJdc> getStudentJdcs(@PathParam("id") int id) {
		return studentModel.getClasses(id);
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("bill/{id}/{jdcid}")
	public List<Bill> getStudentJdcBills(@PathParam("id") int id,@PathParam("jdcid") int jdcid) {
		return studentModel.getBillByStudentClass(id, jdcid);
	}
	
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("pay")
	public List<Student> getStudentToPay() {
		List<Bill> bills = billService.getAll();
		List<Student> list = this.getAll();
		
		Map<Student, Map<StudentJdc, List<Bill>>> map = new HashMap<>();
		
		for(Bill b : bills) {
			StudentJdc jdc = b.getStudentJdc();
			Student student = jdc.getStudent();
			if(map.get(student) == null) {
				map.put(student, new HashMap<StudentJdc, List<Bill>>());
			}
			
			if(map.get(student).get(jdc) == null) {
				map.get(student).put(jdc, new ArrayList<Bill>());
			}
			
			map.get(student).get(jdc).add(b);
		}
		
		for(Entry<Student, Map<StudentJdc, List<Bill>>> e : map.entrySet()) {
			if(isPaid(e.getValue())) {
				list.remove(e.getKey());
			}
		}
		
		return list;
	}

	
	private boolean isPaid(Map<StudentJdc, List<Bill>> jdcs) {
		for(List<Bill> bills : jdcs.values()) {
			if(!isPaid(bills))
				return false;
		}
		
		return true;
	}

	private boolean isPaid(List<Bill> bills) {
		boolean start = true;
		int toPaid = 0;
		int paid = 0;
		for(Bill b: bills) {
			if(start) {
				toPaid = b.getStudentJdc().getJdcClass().getCourse().getFee() - b.getDiscount();
				start = false;
			}
			paid += b.getPaid();
		}
		return toPaid - paid == 0;
	}
	
}
