package com.mmju.jsp.ep6;

import java.sql.Connection;
import java.util.Map;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public abstract class EM_Model {

	protected Map<String, Object> inPuts;
	protected Map<String, Object> outPuts;

	public void setInputs(Map<String, Object> input) {
		this.inPuts = input;
	}

	public Map<String, Object> getOutPuts() {
		return this.outPuts;
	}

	/**
	 * Main Business Logic
	 */
	public abstract void doBusiness();

	/**
	 * getConnection
	 * 
	 * @return JDBC Connection
	 */
	protected Connection getConnection() {
		try {
			Context ctx = new InitialContext();
			DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/ezjsp");
			return ds.getConnection();
		} catch (Exception e) {
			throw new RuntimeException();
		}
	}
	
	protected void setOutputs(String key, Object value) {
		outPuts.put(key, value);
	}
}
