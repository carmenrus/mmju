package com.mmju.jsp.ep6;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class EM_Controller extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private Properties flowsProp;
	private Properties modelConf;

	public static Map<String, String> SQL_CONF = new HashMap<String, String>();;

	@Override
	public void init() throws ServletException {

		flowsProp = new Properties();
		modelConf = new Properties();
		final String _extension = ".sql";
		BufferedReader br = null;
		try {
			// load flows
			flowsProp.load(getServletContext().getResourceAsStream(
			        "/WEB-INF/conf/apl/flows.properties"));

			// load models
			modelConf.load(getServletContext().getResourceAsStream(
			        "/WEB-INF/conf/apl/model.properties"));

			// load sql
			File sqlPath = new File(getServletContext().getRealPath(
			        "/WEB-INF/conf/sql"));
			File[] sqlFiles = sqlPath.listFiles(new FilenameFilter() {
				@Override
				public boolean accept(File dir, String name) {
					return name.endsWith(_extension);
				}
			});

			for (int i = 0; i < sqlFiles.length; i++) {
				String name = sqlFiles[i].getName().replace(_extension, "");
				StringBuilder sb = new StringBuilder();
				br = new BufferedReader(new InputStreamReader(
				        new FileInputStream(sqlFiles[i])));
				String line = null;
				
				while(null != (line = br.readLine())) {
					sb.append(line);
				}
				
				EM_SqlConf.put(name, sb.toString());
			}

		} catch (Exception e) {
			throw new RuntimeException();
		} finally {
			if (null != br) {
				try {
	                br.close();
                } catch (IOException e) {
                }
			}
		}
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
	        throws ServletException, IOException {
		this.doPost(req, resp);
	}

	@SuppressWarnings("unchecked")
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
	        throws ServletException, IOException {

		// getModel
		List<EM_Model> models = this
		        .getModels(getBusinessID(req.getPathInfo()));

		for (EM_Model model : models) {
			// model#setInput
			model.setInputs(req.getParameterMap());

			// model#doBusiness
			model.doBusiness();
		}
		// set View
		if (models.size() > 0)
			this.setView(models.get(models.size() - 1).getOutPuts(), req, resp);
	}

	/**
	 * To get Model Classes setting in model configuration
	 * 
	 * @param businessIDs
	 * @return
	 */
	private List<EM_Model> getModels(String businessID) {
		try {
			List<EM_Model> models = new ArrayList<EM_Model>();
			String names = (String) this.modelConf.get(businessID);
			String[] classNames = names.split(",");
			if (null != classNames) {
				for (String name : classNames)
					models.add((EM_Model) Class.forName(name.trim())
					        .newInstance());
			}
			return models;
		} catch (Exception e) {
			throw new RuntimeException();
		}
	}

	/**
	 * setting view
	 * <ul>
	 * <li>getting output from business logic model</li>
	 * <li>set the result to HttpRequest</li>
	 * <li>despatch request to view</li>
	 * </ul>
	 * 
	 * @param outputs
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	private void setView(Map<String, Object> outputs, HttpServletRequest req,
	        HttpServletResponse resp) throws ServletException, IOException {
		Iterator<String> itr = outputs.keySet().iterator();
		while (itr.hasNext()) {
			String key = itr.next();
			req.setAttribute(key, outputs.get(key));
		}
		this.getDispatcher(req.getRequestURI()).forward(req, resp);
	}

	/**
	 * getting business logic id
	 * 
	 * @param reqPathInf
	 * @return
	 */
	private String getBusinessID(String reqPathInf) {
		if (null != reqPathInf && !reqPathInf.isEmpty()) {
			String[] infos = reqPathInf.split("/");
			for (int i = 0; i < infos.length; i++) {
				if (infos.length - 1 == i) {
					return infos[i].replace(".em", "");
				}
			}
		}
		return null;
	}

	/**
	 * getDispatcher
	 * 
	 * @param reqInfo
	 * @return
	 */
	private RequestDispatcher getDispatcher(String reqInfo) {
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
}
