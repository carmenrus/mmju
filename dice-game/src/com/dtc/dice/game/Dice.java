package com.dtc.dice.game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Dice {
	
	private List<Integer> indexes;
	
	public Dice() {
		this.indexes = new ArrayList<>();
		for(int i=1; i<=6; i++)
			this.indexes.add(i);
	}
	
	public Integer getNumber() {
		Collections.shuffle(indexes);
		return this.indexes.get(0);
	}

}
