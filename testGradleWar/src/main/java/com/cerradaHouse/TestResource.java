package com.cerradaHouse;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;


@Path("/test")
public class TestResource {

	@XmlRootElement(name = "response")
	private static class XMLResponse {

		private Date timestamp;
		private String methodType;
		private Map<String, String> queryParams;

		public XMLResponse() {
			this.timestamp = new Date();
		};

		public Date getTimestamp() {
			return timestamp;
		}

		public void setTimestamp(Date timestamp) {
			this.timestamp = timestamp;
		}

		public String getMethodType() {
			return methodType;
		}

		public void setMethodType(String methodType) {
			this.methodType = methodType;
		}

		@XmlElementWrapper
		public Map<String, String> getQueryParams() {
			return queryParams;
		}

		public void setQueryParams(Map<String, String> queryParams) {
			this.queryParams = queryParams;
		}

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

	/**
	 * This method returns the URI Path parameter passed and all the query parameters. HTTP METHOD : GET
	 * 
	 * @param ui UriInfo object (injected)
	 * @return summary of the values (text/html)
	 */
	@GET
	@Produces("text/html")
	@Path("/html/{parameter}")
	public Response processGET_HTML(@Context
	UriInfo ui) {
		StringBuilder sb = new StringBuilder(createHeader("GET"));
		sb.append("URI Path Parameter(s):<br/>" + printHTMLValues(ui.getPathParameters()));
		sb.append("Query Parameter(s):<br/>" + printHTMLValues(ui.getQueryParameters()));
		return Response.ok(sb.toString()).build();

	}

	@GET
	@Produces("application/xml")
	@Path("/xml/{parameter}")
	public XMLResponse processGET_XML(@Context
	UriInfo ui) {
		XMLResponse response = new XMLResponse();
		response.setMethodType("GET");
		Map<String, String> queryParams = new HashMap<String, String>();
		MultivaluedMap<String, String> multivaluedHashMap = ui.getQueryParameters();

		for (String key : multivaluedHashMap.keySet()) {
			List<String> values = multivaluedHashMap.get(key);
			StringBuilder sb = new StringBuilder(values.size() * 15);
			sb.append("[");
			for (String value : values) {
				sb.append(value + ",");
			}
			sb.setLength(sb.length() - 1);
			sb.append("]");

			queryParams.put(key, sb.toString());
		}
		response.setQueryParams(queryParams);
		return response;

	}

}