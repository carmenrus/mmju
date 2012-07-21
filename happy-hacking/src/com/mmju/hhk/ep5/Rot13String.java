package com.mmju.hhk.ep5;

public class Rot13String {
	
	private StringBuilder sb;
	
	public Rot13String(String str) {
		sb = new StringBuilder();
		if(null != str && !str.isEmpty()) {
			for(char c : str.toCharArray()) {
				sb.append(getRot13Char(c));
			}
		}
	}
	
	private char getRot13Char(char c) {
		if((c >= 'a' && c <= 'm') || (c >= 'A' && c <= 'M')) 
			c += 13;
		else if ((c >= 'n' && c <= 'z') || (c >= 'N' && c <= 'Z'))
			c -= 13;
		return c;
	}

	public String encode() {
		return this.sb.toString();
	}
	
	public static void main(String[] args) {
		// Origin
	    String origin = "hello HELLO";
	    System.out.println(origin);
	    
	    // Convert
	    String conv = new Rot13String(origin).encode();
	    System.out.println(conv);
	    
	    // Convert(Convert())
	    System.out.println(new Rot13String(conv).encode());
    }
}
