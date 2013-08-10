package com.dtc.dice.game;

public class User {
	
	private Integer total_ammount;
	private Game game;
	
	public User() {
		this.game = new Game();
		this.total_ammount = 50000;
	}

	public Integer getTotal_ammount() {
		return total_ammount;
	}

	public Game getGame() {
		return game;
	}

	public void doBalance(Integer amount) {
		this.total_ammount += amount;
	}
}
