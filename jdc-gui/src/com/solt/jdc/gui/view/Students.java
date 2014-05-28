package com.solt.jdc.gui.view;

import java.net.URL;
import java.util.ResourceBundle;

import com.solt.jdc.entity.Bill;
import com.solt.jdc.entity.Student;
import com.solt.jdc.entity.StudentJdc;
import com.solt.jdc.entity.Township;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;

public class Students extends AbstractController{
	
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
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
	}
	
	public void paid() {
		
	}
	
	public void edit() {
		
	}
	
	public void filter() {
		
	}
}
