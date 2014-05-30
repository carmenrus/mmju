package com.solt.jdc.gui.view;

import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

import com.solt.jdc.client.BillBroker;
import com.solt.jdc.client.StudentBroker;
import com.solt.jdc.common.ApplicationContext;
import com.solt.jdc.common.ApplicationContext.CommonList;
import com.solt.jdc.entity.Bill;
import com.solt.jdc.entity.Student;
import com.solt.jdc.entity.StudentJdc;
import com.solt.jdc.entity.Township;
import com.solt.jdc.entity.Transaction;

public class StudentsController extends AbstractController {

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
	private ListView<StudentJdc> classList;
	@FXML
	private ListView<Student> studentList;
	@FXML
	private ListView<Bill> billList;

	@FXML
	private TextField courseFee;
	@FXML
	private TextField discount;
	@FXML
	private TextField fee;
	@FXML
	private TextField paid;
	@FXML
	private TextField remain;
	@FXML
	private TextField pay;
	@FXML
	private TextField filter;

	@SuppressWarnings("unchecked")
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		super.initialize(location, resources);
		ToggleGroup gender = new ToggleGroup();
		gender.getToggles().add(male);
		gender.getToggles().add(female);
		male.setSelected(true);
		this.townships.getItems().addAll(
				(List<Township>) ApplicationContext.get(CommonList.Township));
		this.studentList.getItems().addAll(
				(List<Student>) ApplicationContext.get(CommonList.Student));
		this.studentList.getSelectionModel().selectedItemProperty()
				.addListener(StudentsController.this::selectStudent);

		// init list
		this.classList
				.getSelectionModel()
				.selectedItemProperty()
				.addListener((a, b, c) -> {
					// set bills
						StudentJdc jdc = classList.getSelectionModel()
								.getSelectedItem();
						this.billList.getItems().clear();
						List<Bill> bills = StudentBroker.getInstance()
								.getBillByJdcClass(jdc);
						this.billList.getItems().addAll(bills);
						// set balance
						if (null != bills && bills.size() > 0) {
							this.courseFee.setText(String.valueOf(bills.get(bills.size() -1)
									.getStudentJdc().getJdcClass().getCourse()
									.getFee()));
							this.discount.setText(String.valueOf(bills.get(bills.size() -1)
									.getDiscount()));
							this.fee.setText(substract.apply(this.courseFee,
									this.discount));
							this.paid.setText(String.valueOf(bills.stream()
									.mapToInt(v -> v.getPaid()).sum()));
							this.remain.setText(substract.apply(this.fee,
									this.paid));
							this.pay.clear();
						} else {
							clearTextFields.accept(Arrays.asList(courseFee,
									discount, fee, paid, remain).toArray(
									new TextField[5]));
						}
					});

		filter.textProperty().addListener(
				(a, b, c) -> studentListFilter.accept(c, studentList));
	}

	public void selectStudent(ObservableValue<? extends Student> observable,
			Student oldValue, Student newValue) {
		this.setStudent(newValue);
	}

	private void setStudent(Student stu) {

		this.classList.getItems().clear();

		if (null != stu) {
			this.name.setText(stu.getName());
			this.nrc.setText(stu.getNrcNumber());
			if (stu.isGender()) {
				this.male.setSelected(true);
			} else {
				this.female.setSelected(true);
			}
			this.dob.setValue(super.getLocalDate(stu.getDateOfBirth()));
			this.phone.setText(stu.getPhone());
			this.email.setText(stu.getEmail());
			this.townships.setValue(stu.getTownship());
			this.address.setText(stu.getAddres());
			this.classList.getItems().addAll(
					StudentBroker.getInstance().getClassByStudent(stu.getId()));
			this.classList.getSelectionModel().select(0);
		} else {
			this.clearTextFields.accept(Arrays.asList(name, nrc, phone, email,
					address).toArray(new TextField[5]));
			this.male.setSelected(true);
			this.dob.setValue(null);
			this.townships.setValue(null);
		}

	}

	@SuppressWarnings("unchecked")
	public void paid() {
		StudentJdc jdc = classList.getSelectionModel().getSelectedItem();
		Bill bill = new Bill();
		bill.setPaid(stringToInt.apply(pay.getText()));
		bill.setStudentJdc(jdc);
		Transaction tran = new Transaction();
		tran.setIncome(bill.getPaid());
		tran.setComment(jdc.toString() + " -> " + jdc.getStudent().getName());
		
		bill.setTransaction(tran);
		
		BillBroker.getInstance().persist(bill, Bill.class);
		
		// referesh student
		ApplicationContext.put(CommonList.Student, StudentBroker.getInstance().getAll());
		
		// select student
		studentList.getItems().clear();
		studentList.getItems().addAll((List<Student>)ApplicationContext.get(CommonList.Student));
		studentList.getSelectionModel().select(jdc.getStudent());
		
		// select class
		classList.getSelectionModel().select(jdc);
	}

	public void edit() {

	}

}
