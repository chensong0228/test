package com.cs.spring.mvc.restful.cxf.server;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

public interface DemoRestFulServer {
	
	@GET
	@Path("/get/queryParam")
	@Produces({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
	public String testGetQueryParam(@QueryParam("id") String id,@QueryParam("name") String name);
	
	@POST
	@Path("/post/queryParam")
	@Produces({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
	public String testPostQueryParam(@QueryParam("id") String id,@QueryParam("name") String name,String postStr);
	
	@DELETE
	@Path("/delete/queryParam")
	@Produces({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
	public void testDeleteQueryParam(@QueryParam("id") String id,@QueryParam("name") String name);
	
	@PUT
	@Path("/put/queryParam")
	@Produces({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
	public String testPutQueryParam(@QueryParam("id") String id,String info);
	
	@GET
	@Path("/get/pathParam/{id}/{name}")
	@Produces({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
	public String testGetPathParam(@PathParam("id") String id,@PathParam("name") String name);
	
	@POST
	@Path("/post/pathParam/{id}/{name}")
	@Produces({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
	public String testPostPathParam(@PathParam("id") String id,@PathParam("name") String name,String postStr);
	
	@DELETE
	@Path("/delete/pathParam/{id}/{name}")
	@Produces({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
	public void testDeletePathParam(@PathParam("id") String id,@PathParam("name") String name);
	
	@PUT
	@Path("/put/pathParam/{id}")
	@Produces({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
	public String testPutPathParam(@PathParam("id") String id,String info);

}
