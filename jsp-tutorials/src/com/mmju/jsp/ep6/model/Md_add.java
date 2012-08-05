package com.mmju.jsp.ep6.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.mmju.jsp.ep6.EM_Model;
import com.mmju.jsp.ep6.EM_SqlConf;

public class Md_add extends EM_Model {

	@Override
	public void doBusiness() {

		Connection conn = null;

		// build sql
		try {
			conn = getConnection();
			PreparedStatement pst = conn.prepareStatement(EM_SqlConf
					.getSql("Md_add_SQLI_001"));
			pst.setString(1, getStringValue("brand"));
			pst.setString(2, getStringValue("model"));
			pst.setString(3, getStringValue("year"));

			if (pst.executeUpdate() < 0) {
				throw new RuntimeException("Fail to insert Query.");
			}
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage());
		} finally {
			if (null != conn) {
				try {
					conn.close();
				} catch (SQLException e) {
				}
			}
		}
	}

}
