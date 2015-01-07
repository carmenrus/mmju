package com.jdc.ws.sample;

import javax.ws.rs.BeanParam;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.MatrixParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;

@Path("path")
public class PathSample {
	
	@GET
	public String sampleGet() {
		return "Hello Get Sample";
	}
	
	@GET
	@Path("sub")
	public String sampleSubPath() {
		return "Hello From Sub Path";
	}
	
	@GET
	@Path("hello/{name}")
	public String samplePathParam(@PathParam("name") String str) {
		return String.format("Hello %s", str);
	}
	
	@GET
	@Path("que")
	public String queryParam(@QueryParam("q") String str) {
		return String.format("Query Param Value : %s", str);
	}
	
	@GET
	@Path("matrix")
	public String matrix(@MatrixParam("name") String param) {
		return String.format("Matrix Param Value : %s", param);
	}
	
	@GET
	@Path("head")
	public String header(@HeaderParam("Referer") String ref) {
		return String.format("Referer is %s", ref);
	}
	
	@POST
	public User formSample(@FormParam("first") String first, 
			@FormParam("last") String last) {
		return new User(first, last);
	}
	
	@POST
	@Path("bean")
	public BeanUser getBean(@BeanParam BeanUser user) {
		return user;
	}
}
