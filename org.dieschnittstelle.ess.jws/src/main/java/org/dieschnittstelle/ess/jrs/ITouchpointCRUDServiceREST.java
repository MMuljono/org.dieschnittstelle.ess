package org.dieschnittstelle.ess.jrs;

import java.util.List;

import org.dieschnittstelle.ess.entities.crm.StationaryTouchpoint;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

@Path("/touchpoints")
@Consumes({ "application/json" })
@Produces({ "application/json" })
public interface ITouchpointCRUDServiceREST {
	
	@GET
	public List<StationaryTouchpoint> readAllTouchpoints();
	
	@POST
	public StationaryTouchpoint createTouchpoint(StationaryTouchpoint touchpoint); 
	
	@DELETE
	@Path("/{touchpointId}")
	public boolean deleteTouchpoint(@PathParam("touchpointId") long id); 
	
	@GET
	@Path("/{touchpointId}")
	public StationaryTouchpoint readTouchpoint(@PathParam("touchpointId") long id);
	
	/*
	 * UE JRS: add a new annotated method for using the updateTouchpoint functionality of TouchpointCRUDExecutor and implement it
	 */
	
}
