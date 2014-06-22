package com.solt.jdc.gui.view;

import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;

import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputControl;

import org.controlsfx.dialog.Dialogs;

import com.solt.jdc.common.ApplicationContext;
import com.solt.jdc.common.JdcException;
import com.solt.jdc.common.ApplicationContext.CommonList;
import com.solt.jdc.entity.Course;
import com.solt.jdc.entity.Student;
import com.solt.jdc.entity.TimeTable;

public abstract class AbstractController implements Initializable {

	protected Consumer<TextInputControl[]> clearTextFields;
	protected BiFunction<TextField, TextField, String> substract;
	protected Function<Integer, String> intToString;
	protected Function<String, Integer> stringToInt;

	protected BiConsumer<String, ListView<Student>> studentListFilter;

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

	protected String getText(String fieldName, TextInputControl text) {

		if (null == text.getText() || text.getText().isEmpty()) {
			throw new JdcException(true, "Please fill in the " + fieldName +" text field.");
		}

		return text.getText();
	}

	public LocalDate getLocalDate(Date date) {
		return LocalDateTime
				.ofInstant(date.toInstant(), ZoneId.systemDefault())
				.toLocalDate();
	}

	@SuppressWarnings("unchecked")
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		clearTextFields = tf -> Arrays.asList(tf).forEach(t -> t.clear());
		intToString = v -> String.valueOf(v);
		stringToInt = v -> { 
			try {
				return Integer.parseInt(v);
			} catch(NumberFormatException e) {
				throw new JdcException(true, "Please Add Numbers correctly");
			}
		};
		substract = (t1, t2) -> String.valueOf(Integer.parseInt(t1.getText())
				- Integer.parseInt(t2.getText()));
		
		studentListFilter = (w, list) -> {
			final List<Student> students = new ArrayList<>();
			if(null == w || w.isEmpty()) {
				students.addAll((List<Student>)ApplicationContext.get(CommonList.Student));
			} else {
				students.addAll(list.getItems().stream()
					.filter(s -> s.getName().startsWith(w))
					.collect(Collectors.toList()));
			}
			list.getItems().clear();
			list.getItems().addAll(students);
		};

	}
	
	protected Date getDefaultDateFrom() {
		try {
			return new SimpleDateFormat("yyyyMMdd").parse("20121201");
		} catch (ParseException e) {
			return null;
		}
	}
	
	protected Date getDefaultDateTo() {
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
		try {
			return new SimpleDateFormat("yyyyMMddHHmm").parse(df.format(new Date()) + "2359");
		} catch (ParseException e) {
			return null;
		}
	}
	
	protected void showError(String title, String message) {
		Dialogs.create().owner(ApplicationContext.getStage()).title(title).message(message).showError();
	}
}
