package com.mmju.jsp.ep6;

import java.util.HashMap;
import java.util.Map;

public class EM_SqlConf {
	
	private static Map<String, String> map;
	
	static {
		map = new HashMap<String, String>();
	}
	
	static void put(String key, String value) {
		map.put(key, value);
	}
	
	public static String getSql(String id) {
		return map.get(id);
	}

}
