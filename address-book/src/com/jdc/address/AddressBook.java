package com.jdc.address;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class AddressBook extends Application implements Initializable {

	@FXML
	private TextField name;
	@FXML
	private TextField phone;
	@FXML
	private TextField address;

	@FXML
	private TableView<Address> table;
	@FXML
	private TableColumn<Address, String> cName;
	@FXML
	private TableColumn<Address, String> cPhone;
	@FXML
	private TableColumn<Address, String> cAddress;

	private FileManager fm;

	private AddressEditor editor;
	private Stage editStage;

	@Override
	public void start(Stage primaryStage) throws IOException {

		Parent root = FXMLLoader.load(getClass()
				.getResource("AddressBook.fxml"));
		primaryStage.setScene(new Scene(root));
		primaryStage.setTitle("Address Book");
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}

	public void save() {
		// get data from inputs
		Address a = new Address(this.name.getText(), this.phone.getText(),
				this.address.getText());

		// add to table
		this.table.getItems().add(a);

		// save to file
		fm.AddData(a);

		// clear inputs
		this.name.clear();
		this.phone.clear();
		this.address.clear();
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		cName.setCellValueFactory(new PropertyValueFactory<Address, String>(
				"name"));
		cPhone.setCellValueFactory(new PropertyValueFactory<Address, String>(
				"phone"));
		cAddress.setCellValueFactory(new PropertyValueFactory<Address, String>(
				"address"));

		fm = new FileManager();
		this.table.getItems().addAll(fm.getAllData());

		// delete menu
		MenuItem delete = new MenuItem("Delete");
		delete.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				Address selectedAddress = table.getSelectionModel()
						.getSelectedItem();
				fm.delete(selectedAddress);
				table.getItems().clear();
				table.getItems().addAll(fm.getAllData());
			}
		});

		// edit menu
		MenuItem edit = new MenuItem("Edit");
		edit.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				try {
					editStage = new Stage();
					FXMLLoader loder = new FXMLLoader(getClass().getResource(
							"AddressEditor.fxml"));
					editStage.setScene(new Scene((Parent) loder.load()));
					editor = loder.getController();

					editor.setAddress(table.getSelectionModel()
							.getSelectedItem());
					editStage.initModality(Modality.APPLICATION_MODAL);
					editStage.show();

					editStage.setOnHiding(new EventHandler<WindowEvent>() {

						@Override
						public void handle(WindowEvent arg0) {
							if (editor.isEdit()) {
								Address oldAddress = table.getSelectionModel()
										.getSelectedItem();
								Address newAddress = editor.getAddress();

								if (!oldAddress.equals(newAddress)) {
									fm.update(oldAddress, newAddress);
									table.getItems().clear();
									table.getItems().addAll(fm.getAllData());
								}
							}
						}
					});
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});

		ContextMenu cm = new ContextMenu();
		cm.getItems().addAll(edit, delete);

		this.table.setContextMenu(cm);
	}

}
