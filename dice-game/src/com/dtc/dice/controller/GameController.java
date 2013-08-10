package com.dtc.dice.controller;

import java.io.IOException;
import java.net.URL;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

import com.dtc.dice.game.Game;
import com.dtc.dice.game.User;

public class GameController implements Initializable {

	@FXML
	private Label result;
	@FXML
	private Label sys_money;
	@FXML
	private Label user_money;
	@FXML
	private Label sys_d1;
	@FXML
	private Label sys_d2;
	@FXML
	private Label user_d1;
	@FXML
	private Label user_d2;
	@FXML
	private TextField vote_money;

	@FXML
	private StackPane sys_stk1;
	@FXML
	private StackPane sys_stk2;
	@FXML
	private StackPane use_stk1;
	@FXML
	private StackPane use_stk2;

	private ObservableList<Path> data;

	private User system;
	private User user;

	@FXML
	public void doReset() {
		this.system = new User();
		this.user = new User();
		this.setValues("The game has been reset.");
	}

	@FXML
	public void doPlay() {
		try {
			int vate = this.getVateMoney();
			if (vate > 0) {
				this.system.doBalance(-vate);
				this.user.doBalance(-vate);

				Game us_game = this.user.getGame();
				Game sy_game = this.system.getGame();

				us_game.doGame();
				sy_game.doGame();

				int game_result = us_game.compareTo(sy_game);

				if (game_result == 0) {
					this.user.doBalance(vate);
					this.system.doBalance(vate);
					this.setValues("Draw!");
				} else if (game_result > 0) {
					this.user.doBalance(2 * vate);
					this.setValues("You Win!");
				} else {
					this.system.doBalance(2 * vate);
					this.setValues("You Loose!");
				}

				this.judge();
			}
		} catch (NumberFormatException e) {
			this.vote_money.setText("");
			this.result.setText("Please set vate money with digit.");
		}
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		try {
			this.system = new User();
			this.user = new User();
			this.setValues("Let's start the game.");

			Path images = Paths.get("img");
			if (Files.isDirectory(images, LinkOption.NOFOLLOW_LINKS)) {
				DirectoryStream<Path> dir = Files.newDirectoryStream(images);
				data = FXCollections.observableArrayList();

				for (Path image : dir) {
					data.add(image);
				}

				this.setImage(sys_stk1, this.getImageView(data.get(0)));
				this.setImage(sys_stk2, this.getImageView(data.get(0)));
				this.setImage(use_stk1, this.getImageView(data.get(0)));
				this.setImage(use_stk2, this.getImageView(data.get(0)));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private int getVateMoney() {
		String vateString = this.vote_money.getText();
		int vate = Integer.parseInt(vateString);

		if (this.user.getTotal_ammount() == 0) {
			this.result.setText("You have no money. Please reset the game.");
			return 0;
		}

		if (this.system.getTotal_ammount() == 0) {
			this.result.setText("System have no money. Please reset the game.");
			return 0;
		}

		if (vate == 0 || vate > this.user.getTotal_ammount()) {
			this.result
					.setText("Please set vate money within 0 and total ammount.");
			return 0;
		}

		if (vate > this.system.getTotal_ammount()) {
			this.result
					.setText("Vate money must not more than system total ammount.");
			return 0;
		}

		return vate;
	}

	private void judge() {
		if (this.system.getTotal_ammount() <= 0) {
			this.result.setText(this.result.getText()
					+ " System has no money. Please reset the game;");
		} else if (this.user.getTotal_ammount() <= 0) {
			this.result.setText(this.result.getText()
					+ " You have no money. Please reset the game;");
		}
	}

	private void setValues(String resultStr) {
		try {
			this.result.setText(resultStr);
			this.vote_money.setText("");
			this.sys_d1.setText(String.valueOf(this.system.getGame()
					.getValue1()));
			this.sys_d2.setText(String.valueOf(this.system.getGame()
					.getValue2()));

			this.sys_money.setText(String.valueOf(this.system
					.getTotal_ammount()));
			this.user_d1.setText(String
					.valueOf(this.user.getGame().getValue1()));
			this.user_d2.setText(String
					.valueOf(this.user.getGame().getValue2()));
			this.user_money
					.setText(String.valueOf(this.user.getTotal_ammount()));
			if (this.data != null) {
				this.setImage(sys_stk1, this.getImageView(this.data
						.get(this.system.getGame().getValue1() - 1)));
				this.setImage(sys_stk2, this.getImageView(this.data
						.get(this.system.getGame().getValue2() - 1)));
				this.setImage(use_stk1, this.getImageView(this.data
						.get(this.user.getGame().getValue1() - 1)));
				this.setImage(use_stk2, this.getImageView(this.data
						.get(this.user.getGame().getValue2() - 1)));
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private final Node getImageView(Path image) throws IOException {
		ImageView view = new ImageView(new Image(Files.newInputStream(image)));
		return view;
	}

	private void setImage(StackPane stk, Node img) {
		stk.getChildren().clear();
		stk.getChildren().add(img);
	}
}
