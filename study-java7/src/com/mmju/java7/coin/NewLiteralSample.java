package com.mmju.java7.coin;

public class NewLiteralSample {
	
	public static void main(String[] args) {
		int a = 4;
		int b = 0b100;
		System.out.println("a == b : " + (a == b));

		a += 1_000_000;
		System.out.println(a);
	}
}
