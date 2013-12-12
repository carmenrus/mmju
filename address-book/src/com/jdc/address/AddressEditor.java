package com.jdc.address;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class AddressEditor {

	@FXML
	private TextField name;
	@FXML
	private TextField phone;
	@FXML
	private TextField address;
	@FXML
	private AnchorPane root;
	
	private boolean edit;

	
	public boolean isEdit() {
		return edit;
	}

	public void setEdit(boolean edit) {
		this.edit = edit;
	}

	public void save() {
		this.setEdit(true);
		this.root.getScene().getWindow().hide();
	}

	public void cancel() {
		this.setEdit(false);
		this.root.getScene().getWindow().hide();
	}

	public void setAddress(Address address) {
		this.name.setText(address.getName());
		this.phone.setText(address.getPhone());
		this.address.setText(address.getAddress());
	}

	public Address getAddress() {
		return new Address(this.name.getText(), this.phone.getText(),
				this.address.getText());
	}
}
