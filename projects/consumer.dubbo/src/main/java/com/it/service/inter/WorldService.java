package com.it.service.inter;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;


@Path("world")
public interface WorldService {

	@GET
	@Path("service")
	public String service(@QueryParam("name")String args);
}
