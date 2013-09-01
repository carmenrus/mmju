package com.dtc.javafx.day7;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ComboSample extends Application{
	
	enum Data {
		ONE, TWO, THREE, FOUR;
	}

	@Override
	public void start(Stage stage) throws Exception {
		
		VBox box = new VBox();
		box.setAlignment(Pos.CENTER);
		box.setSpacing(10);
		box.setPadding(new Insets(20));
		
		ComboBox<String> stringCombo = new ComboBox<>();
		stringCombo.getItems().addAll("Yangon", "Mandalay", "Pagoo");
		stringCombo.setPrefWidth(120);
		stringCombo.getSelectionModel().selectFirst();
		
		ComboBox<Data> enumCombo = new ComboBox<>();
		enumCombo.getItems().addAll(Data.values());
		enumCombo.setPrefWidth(120);
		enumCombo.getSelectionModel().selectLast();;
		
		ComboBox<Integer> intCombo = new ComboBox<>();
		intCombo.getItems().addAll(3,4,5,6,7);
		intCombo.setPrefWidth(120);
		intCombo.getSelectionModel().select(3);
		
		box.getChildren().addAll(stringCombo, enumCombo, intCombo);
		
		stage.setScene(new Scene(box));
		stage.show();

	}

	public static void main(String[] args) {
		launch(args);
	}
}
