package com.mmju.hhk.ep2;

public class BinaryGetter {
	
	public static void main(String[] args) {
		System.out.println(getByte("A"));
		System.out.println(getByte("l"));
	}

	public static String getByte(String c) {
		byte [] bs = c.getBytes();
		StringBuilder binary = new StringBuilder();
		for(byte b : bs) {
			 int val = b;
		     for (int i = 0; i < 8; i++)
		     {
		        binary.append((val & 128) == 0 ? 0 : 1);
		        val <<= 1;
		     }
		     binary.append(' ');
		}
		return binary.toString();
	}
}
