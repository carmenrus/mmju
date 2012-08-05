package com.mmju.jsp.ep6.model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.mmju.jsp.ep2.Car;
import com.mmju.jsp.ep6.EM_Model;
import com.mmju.jsp.ep6.EM_SqlConf;

public class Md_select extends EM_Model {

	@Override
	public void doBusiness() {
		Connection conn = getConnection();
		List<Car> carlist = new ArrayList<Car>();
		try {
			Statement stmt = conn.createStatement();
			stmt.execute(EM_SqlConf.getSql("Md_select_SQLS_001"));
			ResultSet rs = stmt.getResultSet();

			while (rs.next()) {
				Car car = new Car();
				car.setBrand(rs.getString("brand"));
				car.setModel(rs.getString("model"));
				car.setYear(rs.getString("year"));
				carlist.add(car);
			}

		} catch (SQLException e) {
			throw new RuntimeException();
		} finally {
			if (null != conn) {
				try {
					conn.close();
				} catch (SQLException e) {
				}
			}
		}
		System.out.println(carlist.size());
		super.setOutputs("car_list", carlist);
	}
}
