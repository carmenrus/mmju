package com.dtc.javafx.day7;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import javafx.util.Callback;

public class CustomCombo extends Application{

	@Override
	public void start(Stage stage) throws Exception {

		VBox box = new VBox();
		box.setAlignment(Pos.TOP_LEFT);
		box.setPrefHeight(180);
		box.setSpacing(10);
		box.setPadding(new Insets(20));
		box.setPrefSize(680, 480);
		final WebView web = new WebView();
		ComboBox<Site> combo = new ComboBox<>();
		combo.setPrefWidth(160);
		combo.getItems().add(new Site("Twitter", "https://twitter.com/"));
		combo.getItems().add(new Site("Facebook", "http://www.facebook.com/"));
		combo.getItems().add(new Site("Youtube", "http://www.youtube.com/"));
		combo.getItems().add(new Site("My Space", "https://myspace.com/"));
		
		combo.setCellFactory(new Callback<ListView<Site>, ListCell<Site>>() {
			@Override
			public ListCell<Site> call(ListView<Site> arg0) {
				return new ListCell<Site>() {
					@Override
					protected void updateItem(Site item, boolean upd) {
						super.updateItem(item, upd);
						if(null != item)
							setText(item.getName());
					}
				};
			}
		});
		
		combo.setButtonCell(new ListCell<Site>() {
			@Override
			protected void updateItem(Site item, boolean upd) {
				super.updateItem(item, upd);
				if(null != item)
					setText(item.getName());
			}
		});
		
		combo.getSelectionModel()
			.selectedItemProperty().addListener(new ChangeListener<Site>() {

			@Override
			public void changed(ObservableValue<? extends Site> obj,
					Site oldObj, Site newObj) {
				web.getEngine().load(newObj.getUrl());
			}
		});
		
		combo.getSelectionModel().selectFirst();
		
		box.getChildren().add(combo);
		box.getChildren().add(web);
		stage.setScene(new Scene(box));
		stage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}

}
