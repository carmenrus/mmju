package com.mmju.hhk.ep2;

public class CoolCrypto {
	
	public static void main(String[] args) {

		String key = "]VTYJQC]aGC]_PDJ[{RJ[EEMLA";
		String input = "creature_creature_creature";
		
		if(args.length == 2) {
			key = args[0];
			input = args[1];
		}
		
		System.out.println(crypt(key, input));
	}
	
	public static String crypt(String key, String input) {
		StringBuilder sb = new StringBuilder();
		
		int count = 0;
		for(int i=0; i < input.length(); i++) {
			if(count >= key.length()) {
				count = 0;
			}
			char c = input.charAt(i);
			c^= key.charAt(count);
			sb.append(c);
			count ++;
		}

		return sb.toString();
	}
}
