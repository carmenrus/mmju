package com.mmju.jsp.ep3;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mmju.jsp.ep2.Car;

public class Episode3Servlet extends HttpServlet {

	private static final long serialVersionUID = 8059386185104611609L;
	private static final String ERROR = "error_message";
	private static final String CAR_LIST = "car_list";

	private List<Car> carList;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String action = req.getPathInfo();

		if ("/list".equals(action)) {
			this.showCarList(req, resp);
		} else if ("/add".equals(action)) {
			this.addCar(req, resp);
		} else if ("/confirm".equals(action)) {
			this.comfirm(req, resp);
		} else {
			RequestDispatcher disp = getServletContext().getRequestDispatcher(
					"/jsp/episode3/ep3-11.jsp");
			disp.forward(req, resp);
		}
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
		RequestDispatcher disp;
		if (this.carList.size() <= 0) {
			req.setAttribute(ERROR,
					"There is No Car in the List! Please add the car.");
			disp = getServletContext().getRequestDispatcher(
					"/jsp/episode3/ep3-11.jsp");
		} else {
			req.setAttribute(CAR_LIST, this.carList);
			disp = getServletContext().getRequestDispatcher(
					"/jsp/episode3/ep3-10.jsp");
		}
		disp.forward(req, resp);
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

		this.showCarList(req, resp);
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
		getServletContext().getRequestDispatcher("/jsp/episode3/ep3-12.jsp")
				.forward(req, resp);
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

	@SuppressWarnings("unchecked")
	@Override
	public void init() throws ServletException {

		try {
			ObjectInputStream os = new ObjectInputStream(new FileInputStream(
					getObjPath()));
			this.carList = (List<Car>) os.readObject();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		if (null == this.carList)
			this.carList = new ArrayList<Car>();
	}

	private String getObjPath() {
		String c = System.getProperties().getProperty("file.separator");
		File baseDir = (File) this.getServletContext().getAttribute(
				"javax.servlet.context.tempdir");
		return baseDir.getAbsolutePath() + c + "/object.dat";
	}

}
