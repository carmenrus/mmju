package com.mmju.jsp.ep4;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mmju.jsp.ep2.Car;

public class BLController extends HttpServlet {

	private static final long serialVersionUID = 224899052595101068L;
	private static final String CAR_LIST = "car_list";

	private List<Car> carList;
	private Properties flowsProp;

	@SuppressWarnings("unchecked")
	@Override
	public void init() throws ServletException {

		flowsProp = new Properties();

		try {
			InputStream in = getServletContext().getResourceAsStream(
					"/WEB-INF/conf/apl/flows.properties");
			flowsProp.load(in);

			ObjectInputStream os = new ObjectInputStream(new FileInputStream(
					getObjPath()));
			this.carList = (List<Car>) os.readObject();

			super.init();
		} catch (Exception e) {
			this.carList = new ArrayList<Car>();
		}
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		String action = this.getKey(req.getRequestURI());
		req.setAttribute("action-type", req);

		if ("add".equals(action)) {
			this.addCar(this.getCarFromRequest(req));
		} else if ("confirm".equals(action) || "details".equals(action)) {
			req.setAttribute("new_car", this.getCarFromRequest(req));
		} else if ("update".equals(action)) {
			this.deleteCar(this.getCarFromRequest(req));
			this.addCar(this.getEdCarFromRequest(req));
		} else if ("delete".equals(action)) {
			this.deleteCar(this.getCarFromRequest(req));
		}

		req.setAttribute("ref_key", this.getKey(req.getHeader("Referer")));
		req.setAttribute(CAR_LIST, this.carList);

		this.getDispetcher(req.getRequestURI()).forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		this.doGet(req, resp);
	}

	private Car getEdCarFromRequest(HttpServletRequest req) {
		Car car = new Car();
		car.setBrand(req.getParameter("ed_brand"));
		car.setModel(req.getParameter("ed_model"));
		car.setYear(req.getParameter("ed_year"));
		return car;
	}

	private Car getCarFromRequest(HttpServletRequest req) {
		Car car = new Car();
		car.setBrand(req.getParameter("brand"));
		car.setModel(req.getParameter("model"));
		car.setYear(req.getParameter("year"));
		return car;
	}

	private void addCar(Car inCar) {
		boolean isNew = true;
		try {
			for (Car car : this.carList) {
				if (car.getBrand().equals(inCar.getBrand())
						&& car.getModel().equals(inCar.getModel())
						&& car.getYear().equals(inCar.getYear())) {
					isNew = false;
				}
			}
		} catch (NullPointerException e) {
			this.carList.clear();
			this.save();
		}

		if (isNew) {
			this.carList.add(inCar);
			this.save();
		}
	}

	private void deleteCar(Car inCar) {
		int index = 0;
		for (Car car : this.carList) {

			if (car.getBrand().equals(inCar.getBrand())
					&& car.getModel().equals(inCar.getModel())
					&& car.getYear().equals(inCar.getYear())) {
				break;
			}
			index++;
		}

		if (index < this.carList.size()) {
			this.carList.remove(index);
			this.save();
		}
	}

	private void save() {
		try {
			ObjectOutputStream os = new ObjectOutputStream(
					new FileOutputStream(getObjPath()));
			os.writeObject(this.carList);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private String getObjPath() {
		String c = System.getProperties().getProperty("file.separator");
		File baseDir = (File) this.getServletContext().getAttribute(
				"javax.servlet.context.tempdir");
		return baseDir.getAbsolutePath() + c + "/object.dat";
	}

	private RequestDispatcher getDispetcher(String reqInfo) {
		if (null != reqInfo && !reqInfo.isEmpty()) {
			String[] infos = reqInfo.split("/");
			StringBuilder sb = new StringBuilder("/jsp");
			for (int i = 0; i < infos.length; i++) {

				if (infos[i].length() > 0) {
					sb.append("/");
					if (infos.length - 1 == i) {
						String key = infos[i].replace(".ep4", "");
						sb.append(flowsProp.getProperty(key));
					} else {
						sb.append(infos[i]);
					}
				}
			}
			return getServletContext().getRequestDispatcher(sb.toString());
		}
		return null;
	}

	private String getKey(String reqPathInf) {
		if (null != reqPathInf && !reqPathInf.isEmpty()) {
			String[] infos = reqPathInf.split("/");
			for (int i = 0; i < infos.length; i++) {
				if (infos.length - 1 == i) {
					return infos[i].replace(".ep4", "");
				}
			}
		}
		return null;
	}

}
