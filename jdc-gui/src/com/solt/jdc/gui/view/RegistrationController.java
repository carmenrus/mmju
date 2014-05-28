package com.solt.jdc.gui.view;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

import com.solt.jdc.client.BillBroker;
import com.solt.jdc.client.JdcException;
import com.solt.jdc.client.StudentBroker;
import com.solt.jdc.common.ApplicationContext;
import com.solt.jdc.common.ApplicationContext.CommonList;
import com.solt.jdc.entity.Bill;
import com.solt.jdc.entity.JdcClass;
import com.solt.jdc.entity.Student;
import com.solt.jdc.entity.StudentJdc;
import com.solt.jdc.entity.Township;
import com.solt.jdc.entity.Transaction;

public class RegistrationController extends AbstractController{
	
	@FXML
	private TextField name;
	@FXML
	private TextField nrc;
	@FXML
	private RadioButton male;
	@FXML
	private RadioButton female;
	@FXML
	private DatePicker dob;
	@FXML
	private TextField email;
	@FXML
	private TextField phone;
	@FXML
	private ComboBox<Township> townships;
	@FXML
	private TextField address;
	
	@FXML
	private ComboBox<JdcClass> jdcClasses;
	@FXML
	private TextField fee;
	@FXML
	private RadioButton noDiscount;
	@FXML
	private RadioButton jdc;
	@FXML
	private RadioButton nhi;
	@FXML
	private RadioButton student;
	
	@FXML
	private TextField feesToPaid;
	@FXML
	private TextField paid;
	@FXML
	private TextField remain;
	
	@FXML
	private TextField filter;
	@FXML
	private ListView<Student> studentList;
	
	private Student currentStudent;
	
	@SuppressWarnings("unchecked")
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		ToggleGroup gender = new ToggleGroup();
		gender.getToggles().add(male);
		gender.getToggles().add(female);
		male.setSelected(true);
		
		ToggleGroup discount = new ToggleGroup();
		discount.getToggles().add(noDiscount);
		discount.getToggles().add(jdc);
		discount.getToggles().add(nhi);
		discount.getToggles().add(student);
		noDiscount.setSelected(true);
		
		this.jdcClasses.getItems().addAll((List<JdcClass>)ApplicationContext.get(CommonList.JdcClass));
		this.townships.getItems().addAll((List<Township>)ApplicationContext.get(CommonList.Township));
		
		this.jdcClasses.setOnAction(RegistrationController.this::setFeesView);
		this.noDiscount.setOnAction(RegistrationController.this::setFeesView);
		this.jdc.setOnAction(RegistrationController.this::setFeesView);
		this.nhi.setOnAction(RegistrationController.this::setFeesView);
		this.student.setOnAction(RegistrationController.this::setFeesView);
		
		this.paid.textProperty().addListener(RegistrationController.this::setPaid);
		this.studentList.getSelectionModel().selectedItemProperty().addListener(RegistrationController.this::selectStudent);

		this.loadStudentList();
	}
	
	@SuppressWarnings("unchecked")
	private void loadStudentList() {
		studentList.getItems().clear();
		studentList.getItems().addAll((List<Student>)ApplicationContext.get(CommonList.Student));
	}

	private void setStudent(Student stu) {
		this.currentStudent = stu;
		if(null != stu) {
			this.name.setText(stu.getName());
			this.nrc.setText(stu.getNrcNumber());
			if(stu.isGender()) {
				this.male.setSelected(true);
			} else {
				this.female.setSelected(true);
			}
			this.dob.setValue(super.getLocalDate(stu.getDateOfBirth()));
			this.phone.setText(stu.getPhone());
			this.email.setText(stu.getEmail());
			this.townships.setValue(stu.getTownship());
			this.address.setText(stu.getAddres());
		} else {
			this.name.clear();
			this.nrc.clear();
			this.male.setSelected(true);
			this.dob.setValue(null);
			this.phone.clear();
			this.email.clear();
			this.townships.setValue(null);
			this.address.clear();
		}
	}
	
	public Student getStudent() {
		if(this.currentStudent == null) {
			this.currentStudent = new Student();
		}
		
		this.currentStudent.setName(this.name.getText());
		this.currentStudent.setNrcNumber(this.nrc.getText());
		this.currentStudent.setGender(this.male.isSelected());
		this.currentStudent.setDateOfBirth(super.getDateFromPicker(dob));
		this.currentStudent.setPhone(this.phone.getText());
		this.currentStudent.setEmail(this.email.getText());
		this.currentStudent.setTownship(this.townships.getValue());
		this.currentStudent.setAddres(this.address.getText());
		
		return this.currentStudent;
	}
	
	public Bill getBill() {
		Bill bill = new Bill();
		JdcClass jdc = this.jdcClasses.getValue();
		int fee = jdc.getCourse().getFee();
		if(this.noDiscount.isSelected()) {
			bill.setDiscount(0);
			bill.setTotal(fee);
		} else {
			bill.setDiscount(fee / 10);
			bill.setTotal(fee - bill.getDiscount());
		}
		
		bill.setPaid(Integer.parseInt(this.paid.getText()));
		bill.setRemain(Integer.parseInt(this.remain.getText()));
		
		Student student = this.getStudent();
		
		StudentJdc studentJdc = new StudentJdc();
		studentJdc.setStudent(student);
		studentJdc.setJdcClass(jdc);
		bill.setStudentJdc(studentJdc);
		
		Transaction tran = new Transaction();
		tran.setIncome(bill.getPaid());
		tran.setComment(student.getName() + " paid for " + jdc.toString());
		bill.setTransaction(tran);
		return bill;
	}
	
	public void clear() {
		// clear list
		this.loadStudentList();
		// clear student
		this.setStudent(null);
		// clear bill info
		this.jdcClasses.setValue(null);
		this.fee.clear();
		this.noDiscount.setSelected(true);
		this.feesToPaid.clear();
		this.paid.clear();
		this.remain.clear();
	}
	
	public void register() {
		BillBroker.getInstance().persist(this.getBill(), Bill.class);
		ApplicationContext.put(CommonList.Student, StudentBroker.getInstance().getAll());
		// clear all inputs
		this.clear();
	}
	
	public void selectStudent(ObservableValue<? extends Student> observable,
			Student oldValue, Student newValue) {
		this.setStudent(newValue);
	}
	
	private void setFeesView(ActionEvent ae) {
		JdcClass c = jdcClasses.getValue();
		if(null != c) {
			this.fee.setText(String.valueOf(c.getCourse().getFee()));
			if(this.noDiscount.isSelected()) {
				this.feesToPaid.setText(this.fee.getText());
				this.paid.setText("0");
				this.remain.setText(this.fee.getText());
			} else {
				int discount = c.getCourse().getFee() / 10;
				this.feesToPaid.setText(String.valueOf(c.getCourse().getFee() - discount));
				this.paid.setText("0");
				this.remain.setText(this.feesToPaid.getText());
			}
		}
	}
	
	private void setPaid(ObservableValue<? extends String> observable,
			String oldValue, String newValue) {
		try {
			JdcClass c = jdcClasses.getValue();
			if(null != c) {
				int fValue = c.getCourse().getFee();
				int pValue = Integer.parseInt(newValue);
				int rValue = fValue - pValue;
				if(this.noDiscount.isSelected()) {
					this.remain.setText(String.valueOf(rValue));
				} else {
					int discount = c.getCourse().getFee() / 10;
					this.remain.setText(String.valueOf(rValue - discount));
				}
			}
		} catch (Exception e) {
			throw new JdcException(true, "Please value with decimal format.");
		}
	}

}
