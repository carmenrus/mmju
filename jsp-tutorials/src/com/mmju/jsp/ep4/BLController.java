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
import javax.servlet.http.HttpSession;

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
					"/WEB-INF/conf/flows.properties");
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

		if ("list".equals(action)) {
			this.showCarList(req, resp);
		} else if ("add".equals(action)) {
			this.addCar(req, resp);
		} else if ("confirm".equals(action)) {
			this.comfirm(req, resp);
		}

		this.getDispetcher(req.getRequestURI()).forward(req, resp);
	}

	/**
	 * This method do to show car list
	 * 
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	private void showCarList(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		req.setAttribute(CAR_LIST, this.carList);
	}

	/**
	 * Check the car to add, and if the car is not on the list<br />
	 * Add this car to list and show the car list.
	 * 
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	private void addCar(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		boolean isNew = true;
		HttpSession session = req.getSession(true);
		Car inCar = (Car) session.getAttribute("new_car");

		if (null != inCar) {
			for (Car car : this.carList) {
				if (car.getBrand().equals(inCar.getBrand())
						&& car.getModel().equals(inCar.getModel())
						&& car.getYear().equals(inCar.getYear())) {
					isNew = false;
				}
			}

			if (isNew) {
				this.carList.add(inCar);
				this.save();
			}
		}

		req.setAttribute(CAR_LIST, this.carList);
	}

	private void comfirm(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		HttpSession session = req.getSession(true);
		session.removeAttribute("new_car");
		Car car = new Car();
		car.setBrand(req.getParameter("brand"));
		car.setModel(req.getParameter("model"));
		car.setYear(req.getParameter("year"));
		session.setAttribute("new_car", car);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		this.doGet(req, resp);
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
