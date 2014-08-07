package com.cbq.soa.b2bv;

import java.util.Date;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

@Path("/services")
public class DemoTest {

	@GET
	public String sayhello() {
	   return "hello";
	}
	
	private String createHeader(String methodName) {
		StringBuilder sb = new StringBuilder(500);
		sb.append("<h1>CerradaHouse Project</h1>");
		sb.append("Method : " + methodName + "<br/>");
		sb.append("Request time: " + new Date() + "<br/>");
		return sb.toString();

	}

	private String printHTMLValues(MultivaluedMap<String, String> mapValues) {
		StringBuilder sb = new StringBuilder(500);
		sb.append("<ul>");
		for (String key : mapValues.keySet()) {
			sb.append("<li>KEY: " + key + " - Value: " + mapValues.get(key) + "</li>");
		}
		sb.append("</ul>");
		return sb.toString();

	}

	/**
	 * @param ui
	 * @return
	 */
	@GET
	@Produces("text/html")
	@Path("/{parameter}")
	public Response processGET(@Context
	UriInfo ui) {
		StringBuilder sb = new StringBuilder(createHeader("GET"));
		sb.append("URI Path Parameter(s):<br/>" + printHTMLValues(ui.getPathParameters()));
		sb.append("Query Parameter(s):<br/>" + printHTMLValues(ui.getQueryParameters()));
		return Response.status(200).entity(sb.toString()).build();

	}
}
