package com.dtc.dice.game;

public class Game implements Comparable<Game> {

	private int value1;
	private int value2;
	private boolean isDouble;

	public int getValue1() {
		return value1;
	}

	public int getValue2() {
		return value2;
	}

	public boolean isDouble() {
		return isDouble;
	}

	public void doGame() {
		// set value1
		this.value1 = new Dice().getNumber();
		this.value2 = new Dice().getNumber();

		if (this.value1 == this.value2) {
			this.isDouble = true;
		} else {
			this.isDouble = false;
		}
	}

	@Override
	public int compareTo(Game o) {
		if (this.isDouble() && o.isDouble()) {
			if (this.getValue1() == 1 && o.getValue1() == 1) {
				return 0;
			} else if (this.getValue1() != 1 && o.getValue1() == 1) {
				return -1;
			} else if (this.getValue1() == 1 && o.getValue1() != 1) {
				return 1;
			} else {
				return this.getValue1() - o.getValue1();
			}
		} else if (!this.isDouble() && o.isDouble()) {
			return -1;
		} else if (this.isDouble() && !o.isDouble()) {
			return 1;
		} else {
			return (this.getValue1() + this.getValue2())
					- (o.getValue1() + o.getValue2());
		}
	}
}
