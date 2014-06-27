package com.solt.jdc.smartboy.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;

import android.util.Log;

import com.solt.jdc.smartboy.MainActivity;

public class JSonPerser {
	
	public static final String BASE_URL = "http://192.168.0.111:8080/smb/rs/";
	public static final String CATEGORY = "category";
	public static final String TABLE = "table";
	public static final String ITEM = "item";
	
	private InputStream is;
	
	public JSONArray getJsonArray(String url) {
		DefaultHttpClient client = new DefaultHttpClient();
		HttpGet get = new HttpGet(url);
		
		try {
			HttpResponse response = client.execute(get);
			is = response.getEntity().getContent();
			BufferedReader reader = new BufferedReader(new InputStreamReader(is));
			String line = null;
			StringBuffer sb = new StringBuffer();
			while(null != (line = reader.readLine())) {
				sb.append(line + "\n");
			}
			
			is.close();
			return new JSONArray(sb.toString());
		} catch (Exception e) {
			Log.e(MainActivity.TABLE, e.getMessage());
		}
		
		return null;
	}

}
