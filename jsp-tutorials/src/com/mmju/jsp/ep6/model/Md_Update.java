package com.mmju.jsp.ep6.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.mmju.jsp.ep6.EM_Model;
import com.mmju.jsp.ep6.EM_SqlConf;

public class Md_Update extends EM_Model {

	@Override
	public void doBusiness() {

		Connection conn = null;

		// build sql
		try {
			conn = getConnection();
			PreparedStatement pst = conn.prepareStatement(EM_SqlConf
			        .getSql("Md_Update_SQLU_001"));

			pst.setString(1, getStringValue("ed_brand"));
			pst.setString(2, getStringValue("ed_model"));
			pst.setString(3, getStringValue("ed_year"));

			pst.setString(4, getStringValue("brand"));
			pst.setString(5, getStringValue("model"));
			pst.setString(6, getStringValue("year"));

			if (pst.executeUpdate() < 0) {
				throw new RuntimeException("Fail to Update Query.");
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
