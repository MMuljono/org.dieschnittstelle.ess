package org.dieschnittstelle.ess.ejb.ejbmodule.erp.crud;

import javax.ejb.Remote;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import org.dieschnittstelle.ess.entities.erp.PointOfSale;

@Remote
@Path("/pointsOfSale")
@Produces({MediaType.APPLICATION_JSON})
@Consumes({MediaType.APPLICATION_JSON})
public interface PointOfSaleCRUDRemote {

	@POST
	public PointOfSale createPointOfSale(PointOfSale pos);

	@GET
	@Path("/{posId}")
	public PointOfSale readPointOfSale(@PathParam("posId") long posId);

	@DELETE
	@Path("/{posId}")
	public boolean deletePointOfSale(@PathParam("posId") long posId);

}
