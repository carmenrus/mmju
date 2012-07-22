package com.mmju.jsp.ep6.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Map;

import com.mmju.jsp.ep6.EM_Model;
import com.mmju.jsp.ep6.EM_SqlConf;

public class Md_Delete extends EM_Model {

	@Override
	public void doBusiness() {
		// get Inputs
		Map<String, Object> inputs = super.getInputs();
		Connection conn = null;

		// build sql
		try {
			conn = getConnection();
			PreparedStatement pst = conn.prepareStatement(EM_SqlConf
			        .getSql("Md_Delete_SQLD_001"));
			pst.setString(1, (String) inputs.get("brand"));
			pst.setString(2, (String) inputs.get("model"));
			pst.setString(3, (String) inputs.get("year"));

			if (pst.executeUpdate() < 0) {
				throw new RuntimeException("Fail to Delete Query.");
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
