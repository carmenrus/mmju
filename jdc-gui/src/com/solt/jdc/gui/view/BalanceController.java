package com.solt.jdc.gui.view;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;

import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import com.solt.jdc.client.TransactionBroker;
import com.solt.jdc.entity.Transaction;

public class BalanceController extends AbstractController {

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		super.initialize(location, resources);

		this.bal = new BalanceCalculator();
		this.colComment.setCellValueFactory(new PropertyValueFactory<>(
				"comment"));
		this.colIncome
				.setCellValueFactory(new PropertyValueFactory<>("income"));
		this.colOutcome
				.setCellValueFactory(new PropertyValueFactory<>("outcome"));
		this.colDate.setCellValueFactory((
				CellDataFeatures<Transaction, String> param) -> {
			if (null != param) {
				return new SimpleStringProperty((new SimpleDateFormat(
						"yyyy/MM/dd HH:mm").format(param.getValue()
						.getCreation())));
			}
			return null;
		});

		this.colBalance.setCellValueFactory(BalanceController.this::getBalance);
		
		this.loadTable();
	}
	
	private void loadTable() {
		this.table.getItems().clear();
		this.table.getItems().addAll(TransactionBroker.getInstance().getAll());
	}

	private SimpleStringProperty getBalance(
			CellDataFeatures<Transaction, String> param) {
		if (null != param) {
			Transaction t = param.getValue();
			this.bal.addTransaction(t);
			return new SimpleStringProperty(super.intToString.apply(this.bal.getTotal()));
		}
		return null;
	}

	@FXML
	private TextField stuff;
	@FXML
	private TextField amount;
	@FXML
	private TextArea comment;
	@FXML
	private Button clear;
	@FXML
	private Button submit;

	@FXML
	private DatePicker from;
	@FXML
	private DatePicker to;
	@FXML
	private TableView<Transaction> table;
	@FXML
	private TableColumn<Transaction, String> colDate;
	@FXML
	private TableColumn<Transaction, String> colComment;
	@FXML
	private TableColumn<Transaction, String> colIncome;
	@FXML
	private TableColumn<Transaction, String> colOutcome;
	@FXML
	private TableColumn<Transaction, String> colBalance;

	private BalanceCalculator bal;

	private static class BalanceCalculator {
		private int total = 0;

		public void addTransaction(Transaction t) {
			total -= t.getOutcome();
			total += t.getIncome();
		}

		public int getTotal() {
			return this.total;
		}
	}

}
