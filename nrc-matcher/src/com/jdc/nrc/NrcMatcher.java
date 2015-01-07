package com.jdc.nrc;

public class NrcMatcher {
	
	public static void main(String[] args) {
		// 2 digit to start
		System.out.println("12".matches("^[0-9]{2}"));
		System.out.println("12".matches("^\\d{2}"));
		
		// 3 char
		System.out.println("abc".matches("^[a-zA-Z]{3}"));
		System.out.println("abc".matches("^\\w{3}"));
		
		// A or a 
		// + is one or more {1,}
		// * is zero or more {0,}
		// . any one char
		// ? no or one {0,1}
		System.out.println("Naing".matches("^[N|n]\\w+"));
		System.out.println("naing".matches("^[N|n][a-z]{4}"));

		// escape special character \\ or surround with []
		System.out.println("$".matches("$"));
		System.out.println("$".matches("\\$"));
		System.out.println("$".matches("[$]"));
		
		System.out.println("/YKN(Naing)".matches("/\\w{3}\\((Naing|Pyu|Ae)\\)"));
		System.out.println("/YKN(Paing)".matches("/[a-zA-Z]{3}[(](Naing|Paing|Kyaung)[)]"));
		

		String nrc = "^\\d{2}/\\w{3}\\((Naing|Paing|Kyaung)\\)\\d{6}";
		System.out.println("12/YKN(Naing)009988".matches(nrc));
	}

}
