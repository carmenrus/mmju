package com.dtc.address_ui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.Callback;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import com.dtc.address_ebook.Address;
import com.dtc.address_ebook.Auser;
import com.dtc.address_ebook.Division;
import com.dtc.address_ebook.Townships;

public class AddressUIMain extends Application implements Initializable {

	@FXML
	private ListView<Auser> list;
	@FXML
	private TextField name;
	@FXML
	private TextField phone;
	@FXML
	private TextField email;
	@FXML
	private ComboBox<Division> divisions;
	@FXML
	private ComboBox<Townships> townships;
	@FXML
	private TextField address;
	@FXML
	private TextArea memo;
	@FXML
	private Label message;
	
	@FXML
	private Button save;
	@FXML
	private Button add;
	@FXML
	private Button edit;
	@FXML
	private Button delete;
	@FXML
	private Button cancel;
	
	private Node [] nodes = new Node[7];

	@Override
	public void start(Stage primaryStage) throws IOException {

		Parent root = FXMLLoader.load(getClass().getResource(
				"AddressUIMain.fxml"));
		primaryStage.setScene(new Scene(root));
		primaryStage.setTitle("Address Book");
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}

	@FXML
	public void addNew() {
		this.message.setText("");
		this.list.getSelectionModel().clearSelection();
		this.setDisable(false);
	}

	@FXML
	public void edit() {
		this.message.setText("");
		if(null != this.list.getSelectionModel().getSelectedItem()) {
			this.setDisable(false);
		} else {
			this.memo.setText("Select One User on List");
		}
	}

	@FXML
	public void delete() {
		this.message.setText("");
		Auser tmpUser = this.list.getSelectionModel().getSelectedItem();
		if(null != tmpUser) {
			this.em.getTransaction().begin();
			this.em.remove(tmpUser);
			this.em.getTransaction().commit();
			this.list.getItems().remove(tmpUser);
			this.message.setText(tmpUser.getName() + " data has benn removed.");
			if (this.list.getItems().size() > 0) 
				this.list.getSelectionModel().select(0);
			else
				this.setUserView(null);
		} else {
			this.message.setText("Select One User on List");
		}
	}

	@FXML
	public void save() {
		this.message.setText("");
		boolean isEdit = false;
		
		if(null == name.getText() || name.getText().isEmpty()) {
			this.message.setText("User Name is requried! Please set User Name!");
			return;
		}
		
		Auser user = this.list.getSelectionModel().getSelectedItem();
		if(null == user) {
			user = new Auser();
		} else {
			isEdit = true;
		}
		
		user.setName(name.getText());
		user.setEmail(email.getText());
		user.setPhone(phone.getText());
		user.setMemo(memo.getText());
		
		Address addr = user.getAddress();
		if(addr == null) {
			addr = new Address();
		}
		
		addr.setAddress(address.getText());
		addr.setTownships(townships.getSelectionModel().getSelectedItem());
		
		em.getTransaction().begin();
		user.setAddress(addr);
		if(user.getId() <= 0) {
			em.persist(user);
		} else {
			em.merge(user);
		}
		em.getTransaction().commit();
		
		if(!isEdit) {
			list.getItems().add(user);
			list.getSelectionModel().select(user);
		} else {
			this.setUserView(user);
		}
		
		this.setDisable(true);
	}
	
	@FXML
	public void cancel() {
		this.memo.setText("");
		this.setDisable(true);
		this.list.getSelectionModel().clearSelection();
		this.setUserView(null);
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		this.em = Persistence.createEntityManagerFactory("Address_Book")
				.createEntityManager();
		
		this.initListView();

		this.initDivisionCombo();
		
		this.initTownshipCombo();

		nodes[0] = this.name;
		nodes[1] = this.phone;
		nodes[2] = this.email;
		nodes[3] = this.divisions;
		nodes[4] = this.townships;
		nodes[5] = this.address;
		nodes[6] = this.memo;

		this.setDisable(true);
		
		if(list.getItems().size() > 0)
			list.getSelectionModel().select(0);
	}
	
	
	private void setUserView(Auser user) {
		if(null == user) {
			for(Node n : nodes) {
				if(n instanceof ComboBox<?>) {
					ComboBox<?> combo = (ComboBox<?>)n;
					combo.getSelectionModel().clearSelection();
				} else if (n instanceof TextArea) {
					TextArea ta = (TextArea)n;
					ta.clear();
				} else {
					TextField tf = (TextField)n;
					tf.clear();
				}
			}
		} else {
			name.setText(user.getName());
			phone.setText(user.getPhone());
			email.setText(user.getEmail());
			address.setText(user.getAddress().getAddress());
			
			Townships tmpTownship = user.getAddress().getTownships();
			if(null == tmpTownship) {
				divisions.getSelectionModel().clearSelection();
				townships.getSelectionModel().clearSelection();
			} else {
				divisions.getSelectionModel().select(tmpTownship.getDivision());
				townships.getSelectionModel().select(tmpTownship);
			}
			memo.setText(user.getMemo());
		}
	}
	
	private void initListView() {
		list.getItems().addAll(em.createNamedQuery("Auser.findAll", Auser.class).getResultList());
		list.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
		
		list.setCellFactory(new Callback<ListView<Auser>, ListCell<Auser>>() {
			
			@Override
			public ListCell<Auser> call(ListView<Auser> arg0) {
				return new ListCell<Auser>() {
					@Override
					protected void updateItem(Auser au, boolean bu) {
						super.updateItem(au, bu);
						if(null != au)
							setText(au.getName());
					}
				};
			}
		});
		
		list.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Auser>() {

			@Override
			public void changed(ObservableValue<? extends Auser> obs,
					Auser oldObj, Auser newObj) {
				setUserView(newObj);
			}
		});
	}
	
	private void initDivisionCombo() {
		this.divisions.getItems().addAll(
				em.createNamedQuery("Division.findAll", Division.class)
						.getResultList());
		
		this.divisions
				.setCellFactory(new Callback<ListView<Division>, ListCell<Division>>() {

					@Override
					public ListCell<Division> call(ListView<Division> arg0) {
						return new ListCell<Division>() {
							@Override
							protected void updateItem(Division di,
									boolean boo) {
								super.updateItem(di, boo);
								if(di != null)
									setText(di.getName());
							}
							
						};
					}
				});
		
		this.divisions.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Division>() {

			@Override
			public void changed(ObservableValue<? extends Division> arg0,
					Division oldObj, Division newObj) {
				townships.getItems().clear();
				if(null != newObj) {
					townships.getItems().addAll(newObj.getTownshiplist());
				}
			}
		});
	}

	private void initTownshipCombo() {
		this.townships.setCellFactory(new Callback<ListView<Townships>, ListCell<Townships>>() {
			
			@Override
			public ListCell<Townships> call(ListView<Townships> arg0) {
				return new ListCell<Townships>() {
					@Override
					protected void updateItem(Townships ts, boolean boo) {
						super.updateItem(ts, boo);
						if(null != ts)
							setText(ts.getName());
					}
				};
			}
		});
	}
	
	@Override
	protected void finalize() throws Throwable {
		this.em.close();
		super.finalize();
	}

	private EntityManager em;
	
	private void setDisable(boolean mode) {
		for(Node n : nodes)
			n.setDisable(mode);
		
		this.save.setDisable(mode);
		this.cancel.setDisable(mode);
		
		this.add.setDisable(!mode);
		this.edit.setDisable(!mode);
		this.delete.setDisable(!mode);
		this.list.setDisable(!mode);
	}
}
