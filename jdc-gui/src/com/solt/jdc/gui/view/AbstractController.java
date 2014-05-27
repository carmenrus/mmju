package com.solt.jdc.gui.view;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import com.solt.jdc.client.JdcException;
import com.solt.jdc.entity.Course;
import com.solt.jdc.entity.TimeTable;

public abstract class AbstractController implements Initializable {

	public Date getDateFromPicker(DatePicker dp) {
		if (null == dp.getValue()) {
			throw new JdcException(true, "Please set date to Date Picker.");
		}
		return Date.from(dp.getValue().atStartOfDay()
				.atZone(ZoneId.systemDefault()).toInstant());
	}

	protected Course getCourse(ComboBox<Course> comb) {
		if (null == comb.getSelectionModel().getSelectedItem()) {
			throw new JdcException(true, "Please select Course.");
		}

		return comb.getSelectionModel().getSelectedItem();
	}

	protected TimeTable getTime(ComboBox<TimeTable> comb) {
		if (null == comb.getSelectionModel().getSelectedItem()) {
			throw new JdcException(true, "Please select Time Table.");
		}

		return comb.getSelectionModel().getSelectedItem();
	}

	protected String getText(ComboBox<String> comb) {
		String str = comb.getSelectionModel().getSelectedItem();
		if (null == str || str.isEmpty()) {
			throw new JdcException(true, "Please select value of combobox.");
		}

		return str;
	}

	protected String getText(TextField text) {

		if (null == text.getText() || text.getText().isEmpty()) {
			throw new JdcException(true, "Please fill in the text field.");
		}

		return text.getText();
	}

	public LocalDate getLocalDate(Date date) {
		return LocalDateTime
				.ofInstant(date.toInstant(), ZoneId.systemDefault())
				.toLocalDate();
	}

}
