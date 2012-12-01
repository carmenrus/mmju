package com.mmju.java7.coin;

public class SwitchSample {
	
	public static void main(String[] args) {
		writeChar("one");
		writeChar("two");
		writeChar("four");
	}

	static void writeChar(String str) {
		switch (str) {
		case "one":
			System.out.println("One is comming");
			break;
		case "two":
			System.out.println("Two is comming");
			break;
		case "three":
			System.out.println("Three is comming");
			break;
		default:
			System.out.println(str + " is comming");
			break;

		}
	}
}
