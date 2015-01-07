package com.jdc.sightline;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.util.HashSet;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.stream.Collectors;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.stage.FileChooser;

public class SampleController implements Initializable {
	
	@FXML
	private TableView<SightLineWord> words;
	@FXML
	private TableView<SightLineWord> review;
	@FXML
	private TableView<SightLineWord> choise;
	
	@FXML
	private TableColumn<SightLineWord, String> english;
	@FXML
	private TableColumn<SightLineWord, String> japanese;
	@FXML
	private TableColumn<SightLineWord, String> product;

	@FXML
	private TableColumn<SightLineWord, String> rEnglish;
	@FXML
	private TableColumn<SightLineWord, String> rJapanese;
	@FXML
	private TableColumn<SightLineWord, String> rProduct;

	@FXML
	private TableColumn<SightLineWord, String> origin;
	@FXML
	private TableColumn<SightLineWord, String> choice;
	@FXML
	private TextArea text;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		text.setWrapText(true);
		english.setCellValueFactory(new PropertyValueFactory<>("english"));
		japanese.setCellValueFactory(new PropertyValueFactory<>("japanese"));
		product.setCellValueFactory(new PropertyValueFactory<>("product"));
		
		rEnglish.setCellValueFactory(new PropertyValueFactory<>("english"));
		rJapanese.setCellValueFactory(new PropertyValueFactory<>("japanese"));
		rProduct.setCellValueFactory(new PropertyValueFactory<>("product"));

		origin.setCellValueFactory(new PropertyValueFactory<>("english"));
		choice.setCellValueFactory(new PropertyValueFactory<>("japanese"));
		choise.getSelectionModel().selectedItemProperty().addListener((a, b, c) -> {
			if(null != c) {
				Clipboard clip = Clipboard.getSystemClipboard();
				ClipboardContent content = new ClipboardContent();
				content.putString(c.getJapanese());
				clip.setContent(content);
			}
		});
	}

	public void loadGlosary() {
		
		FileChooser fc = new FileChooser();
		fc.setTitle("Choose Glossary File");
		File f = fc.showOpenDialog(words.getScene().getWindow());
		if(null != f) {
			try {
				List<String> lines = Files.readAllLines(f.toPath());
				words.getItems().clear();
				words.getItems().addAll(lines.stream().map(SightLineWord::new).collect(Collectors.toList()));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void loadReviewData() {
		FileChooser fc = new FileChooser();
		fc.setTitle("Choose Review File");
		File f = fc.showOpenDialog(words.getScene().getWindow());
		if(null != f) {
			try {
				List<String> lines = Files.readAllLines(f.toPath());
				review.getItems().clear();
				review.getItems().addAll(lines.stream().map(SightLineWord::new).collect(Collectors.toList()));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void check() {
		
		final String input = text.getText();
		
		if(input.length() > 0) {
			Set<SightLineWord> set = new HashSet<SightLineWord>();
			set.addAll(words.getItems());
			set.addAll(review.getItems());

			choise.getItems().clear();
			choise.getItems().addAll(
			set.stream().filter(a -> input.toLowerCase().contains(a.getEnglish().toLowerCase())).collect(Collectors.toList()));
		}
	}
}
