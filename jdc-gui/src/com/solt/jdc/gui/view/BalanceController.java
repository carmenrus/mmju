package com.solt.jdc.gui.view;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.ResourceBundle;

import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputControl;
import javafx.scene.control.cell.PropertyValueFactory;

import com.solt.jdc.client.OutputBroker;
import com.solt.jdc.client.TransactionBroker;
import com.solt.jdc.entity.Output;
import com.solt.jdc.entity.Transaction;

public class BalanceController extends AbstractController {

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		super.initialize(location, resources);

		this.colComment.setCellValueFactory(new PropertyValueFactory<>(
				"comment"));
		this.colIncome
				.setCellValueFactory(new PropertyValueFactory<>("income"));
		this.colOutcome.setCellValueFactory(new PropertyValueFactory<>(
				"outcome"));
		this.colDate.setCellValueFactory((
				CellDataFeatures<Transaction, String> param) -> {
			if (null != param) {
				return new SimpleStringProperty((new SimpleDateFormat(
						"yyyy/MM/dd HH:mm").format(param.getValue()
						.getCreation())));
			}
			return null;
		});

		this.colBalance.setCellValueFactory(new PropertyValueFactory<>(
				"balance"));

		this.loadTable();

		clear.setOnAction(BalanceController.this::clear);

		submit.setOnAction(e -> {
			Output o = new Output();
			o.setStuff(stuff.getText());
			o.setOutcome(stringToInt.apply(amount.getText()));
			o.setComment(comment.getText());

			Transaction tran = new Transaction();
			tran.setOutcome(o.getOutcome());
			tran.setComment(o.getStuff() + " : " + o.getComment());
			o.setTransaction(tran);
			OutputBroker.getInstance().persist(o, Output.class);

			this.clear(null);
			this.loadTable();
		});

		this.from.setOnAction(e -> this.loadTable());
		this.to.setOnAction(e -> this.loadTable());
	}

	private void clear(ActionEvent e) {
		clearTextFields.accept(Arrays.asList(this.stuff, this.amount,
				this.comment).toArray(new TextInputControl[3]));
	}

	private void loadTable() {
		this.table.getItems().clear();
		this.table.getItems().addAll(
				TransactionBroker.getInstance().getAll(this.getFrom(),
						this.getTo()));
		this.total.setText(TransactionBroker.getInstance().getTotal());
	}

	private Date getFrom() {
		return (this.from.getValue() == null) ? super.getDefaultDateFrom()
				: super.getDateFromPicker(from);
	}
	
	private Date getTo() {
		return (this.to.getValue() == null) ? super.getDefaultDateTo()
				: super.getDateFromPicker(to);
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
	@FXML
	private Label total;

}
