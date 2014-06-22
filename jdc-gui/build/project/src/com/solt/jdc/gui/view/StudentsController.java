package com.solt.jdc.gui.view;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
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
import com.solt.jdc.common.JdcException;
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
	@FXML
	private Button edit;
	@FXML
	private Button submit;
	@FXML
	private Button cancel;
	@FXML
	private ListView<Student> needToPay;

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
		// students
		this.studentList.getItems().addAll(
				(List<Student>) ApplicationContext.get(CommonList.Student));

		this.studentList
				.getSelectionModel()
				.selectedItemProperty()
				.addListener(
						(a, b, c) -> {
							if (null != c) {
								setStudent(this.studentList.getSelectionModel()
										.getSelectedItem());
								needToPay.getSelectionModel().clearSelection();
							}
						});

		// students to pay
		this.needToPay.getItems().addAll(
				StudentBroker.getInstance().getAllToPaid());

		this.needToPay
				.getSelectionModel()
				.selectedItemProperty()
				.addListener(
						(a, b, c) -> {
							if (null != c) {
								setStudent(this.needToPay.getSelectionModel()
										.getSelectedItem());
								studentList.getSelectionModel()
										.clearSelection();
							}
						});

		// init list
		this.classList
				.getSelectionModel()
				.selectedItemProperty()
				.addListener((a, b, c) -> {
					this.edit.setDisable(false);
					this.submit.setDisable(false);
					this.pay.setEditable(true);
					// set bills
						StudentJdc jdc = classList.getSelectionModel()
								.getSelectedItem();
						this.billList.getItems().clear();
						List<Bill> bills = StudentBroker.getInstance()
								.getBillByJdcClass(jdc);
						this.billList.getItems().addAll(bills);
						// set balance
						if (null != bills && bills.size() > 0) {
							this.courseFee.setText(String.valueOf(bills
									.get(bills.size() - 1).getStudentJdc()
									.getJdcClass().getCourse().getFee()));
							this.discount.setText(String.valueOf(bills.get(
									bills.size() - 1).getDiscount()));
							this.fee.setText(substract.apply(this.courseFee,
									this.discount));
							this.paid.setText(String.valueOf(bills.stream()
									.mapToInt(v -> v.getPaid()).sum()));
							this.remain.setText(substract.apply(this.fee,
									this.paid));
							this.pay.clear();
							if ("0".equals(this.remain.getText())) {
								this.pay.setEditable(false);
								this.submit.setDisable(true);
							}
						} else {
							clearTextFields.accept(Arrays.asList(courseFee,
									discount, fee, paid, remain).toArray(
									new TextField[5]));
						}
					});

		filter.textProperty().addListener(
				(a, b, c) -> studentListFilter = (w, list) -> {
					final List<Student> students = new ArrayList<>();
					if (null == w || w.isEmpty()) {
						students.addAll(StudentBroker.getInstance()
								.getAllToPaid());
					} else {
						students.addAll(list.getItems().stream()
								.filter(s -> s.getName().startsWith(w))
								.collect(Collectors.toList()));
					}
					list.getItems().clear();
					list.getItems().addAll(students);
				});

		filter.textProperty().addListener(
				(a, b, c) -> studentListFilter.accept(c, studentList));

		cancel.setOnAction(e -> {
			setEditMode(false);
			edit.setText("Edit");
		});

		this.submit.setOnAction(StudentsController.this::paid);

		this.edit.setOnAction(e -> {
			if ("Edit".equals(((Button) e.getSource()).getText())) {
				this.setEditMode(true);
				edit.setText("Save");
			} else {
				boolean fromTop = this.needToPay.getSelectionModel()
						.getSelectedItem() != null;
				this.setEditMode(false);
				Student stu = getStudent();
				StudentBroker.getInstance().update(stu, Student.class);
				ApplicationContext.put(CommonList.Student, StudentBroker
						.getInstance().getAll());
				this.studentList.getItems().clear();
				this.studentList.getItems().addAll(
						(List<Student>) ApplicationContext
								.get(CommonList.Student));
				this.needToPay.getItems().clear();
				this.needToPay.getItems().addAll(
						StudentBroker.getInstance().getAllToPaid());
				if (fromTop) {
					this.selectById(stu.getId(), needToPay);
				} else {
					this.selectById(stu.getId(), studentList);
				}
				edit.setText("Edit");
			}
		});
	}

	private void selectById(int id, ListView<Student> list) {
		list.getItems().filtered(s -> s.getId() == id).stream()
				.forEach(s -> list.getSelectionModel().select(s));
	}

	private void setEditMode(boolean edit) {
		studentList.setMouseTransparent(edit);
		needToPay.setMouseTransparent(edit);
		filter.setDisable(edit);
		submit.setDisable(edit);
		pay.setDisable(edit);
		cancel.setVisible(edit);

		name.setEditable(edit);
		nrc.setEditable(edit);
		phone.setEditable(edit);
		email.setEditable(edit);
		address.setEditable(edit);

		male.setDisable(!edit);
		female.setDisable(!edit);
		dob.setDisable(!edit);
		townships.setDisable(!edit);
	}

	private Student getStudent() {
		Student stu = (this.studentList.getSelectionModel().getSelectedItem() != null) ? this.studentList
				.getSelectionModel().getSelectedItem() : this.needToPay
				.getSelectionModel().getSelectedItem();
		stu.setName(name.getText());
		stu.setAddres(address.getText());
		stu.setDateOfBirth(super.getDateFromPicker(dob));
		stu.setEmail(email.getText());
		stu.setGender(male.isSelected());
		stu.setModification(new Date());
		stu.setNrcNumber(nrc.getText());
		stu.setPhone(phone.getText());
		stu.setTownship(townships.getValue());
		return stu;
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
	public void paid(ActionEvent e) {
		try {
			StudentJdc jdc = classList.getSelectionModel().getSelectedItem();
			Bill bill = new Bill();
			bill.setPaid(stringToInt.apply(pay.getText()));
			bill.setStudentJdc(jdc);
			Transaction tran = new Transaction();
			tran.setIncome(bill.getPaid());
			tran.setComment(jdc.toString() + " -> "
					+ jdc.getStudent().getName());

			bill.setTransaction(tran);

			BillBroker.getInstance().persist(bill, Bill.class);

			// referesh student
			ApplicationContext.put(CommonList.Student, StudentBroker
					.getInstance().getAll());

			// select student
			studentList.getItems().clear();
			studentList.getItems().addAll(
					(List<Student>) ApplicationContext.get(CommonList.Student));
			needToPay.getItems().clear();
			needToPay.getItems().addAll(
					StudentBroker.getInstance().getAllToPaid());
			studentList.getSelectionModel().select(jdc.getStudent());

			// select class
			classList.getSelectionModel().select(jdc);

		} catch (JdcException je) {
			if(je.isAlert())
				showError("Error", je.getMessage());
		}
	}

}
