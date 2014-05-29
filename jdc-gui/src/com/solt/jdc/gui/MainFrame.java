package com.solt.jdc.gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;

public class MainFrame implements Initializable{
	
	@FXML
	private Button registration;
	@FXML
	private Button admin;
	@FXML
	private Button close;
	@FXML
	private Button students;
	@FXML
	private Button balance;
	@FXML
	private StackPane container;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		registration.setOnAction(MainFrame.this::loadView);
		admin.setOnAction(MainFrame.this::loadView);
		students.setOnAction(MainFrame.this::loadView);
		
		close.setOnAction(e -> System.exit(0));
		this.loadView("Registration");
	}
	
	public void loadView(ActionEvent event) {
		this.loadView(((Button)event.getSource()).getText());
	}
	
	public void loadView(String name) {
		try {
			HBox view = FXMLLoader.load(this.getClass().getResource("view/" + name + ".fxml"));
			container.getChildren().clear();
			container.getChildren().add(view);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
