package com.jdc.ws.client;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.control.PasswordField;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import com.jdc.ws.entity.Role;
import com.jdc.ws.entity.User;

public class WS_Main implements Initializable{

	private ClientService client;
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		client = new ClientServiceImp();
		
		// set list as multi select mode
		roleList.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		
		// set table columns
		nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
		roleColumn.setCellValueFactory(p -> {
			if(null != p) {
				User user = p.getValue();
				List<Role> roles = user.getRoles();
				StringBuilder sb = new StringBuilder();
				if(null != roles) {
					for(Role r : roles)
						sb.append(r.getName()).append(", ");
					
					String str = sb.toString();
					if(null != str && !str.isEmpty())
						str = str.substring(0, str.length() - 2);

					return new SimpleStringProperty(str);
				}
			}
			return null;
		});
		
		// context menu
		ContextMenu menu = new ContextMenu();
		MenuItem delete = new MenuItem("Delete");
		delete.setOnAction(this::delete);
		menu.getItems().add(delete);
		userTable.setContextMenu(menu);
		
		// load view data
		loadViewData();
	}
	
	private void delete(ActionEvent e) {
		// clear message
		message.setText("");
		// get selected data
		User user = userTable.getSelectionModel().getSelectedItem();
		if(null == user) {
			message.setText("Please select user!");
		} else {
			client.deleteUser(user.getId());
			
			// load view data
			loadViewData();
		}
	}
	
	private void loadViewData() {
		// load table
		userTable.getItems().clear();
		userTable.getItems().addAll(client.getUsers());
		
		// load list
		roleList.getItems().clear();
		roleList.getItems().addAll(client.getRoles());
	}
	
	public void addUser() {
		try {
			// check
			checkInput();
			// get input data
			User user = new User();
			user.setName(name.getText());
			user.setPassword(pass.getText());
			user.setRoles(roleList.getSelectionModel().getSelectedItems());
			
			// server request
			client.addUser(user);
			
			// clear inputs
			name.clear();
			pass.clear();
			roleList.getSelectionModel().clearSelection();
			
			// load view data
			loadViewData();
		
		} catch(ClientException e) {
			message.setText(e.getMessage());
		}
		
	}
	
	private void checkInput() {
		message.setText("");
		if(name.getText() == null || name.getText().isEmpty()) {
			throw new ClientException("Please Check User Name");
		}
		
		if(pass.getText() ==null || pass.getText().isEmpty()) {
			throw new ClientException("Please Check Password");
		}
	}

	
	@FXML
	private TextField name;
	@FXML
	private PasswordField pass;
	@FXML
	private ListView<Role> roleList;
	@FXML
	private TableView<User> userTable;
	@FXML
	private TableColumn<User, String> nameColumn;
	@FXML
	private TableColumn<User, String> roleColumn;
	@FXML
	private Label message;
	
}
