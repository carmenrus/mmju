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

import com.solt.jdc.client.JdcException;
import com.solt.jdc.common.ApplicationContext;
import com.solt.jdc.common.ApplicationContext.CommonList;
import com.solt.jdc.entity.JdcClass;
import com.solt.jdc.entity.Student;
import com.solt.jdc.entity.Township;

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
