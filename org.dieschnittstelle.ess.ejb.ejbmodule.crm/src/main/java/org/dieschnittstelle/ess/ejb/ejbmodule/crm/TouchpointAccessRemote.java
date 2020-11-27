package org.dieschnittstelle.ess.ejb.ejbmodule.crm;

import java.util.List;

import javax.ejb.Remote;
//import javax.ejb.Remote;
import javax.jws.WebService;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import org.dieschnittstelle.ess.entities.crm.AbstractTouchpoint;

@Remote
@WebService
@Path("/touchpoints")
@Consumes({MediaType.APPLICATION_JSON})
@Produces({MediaType.APPLICATION_JSON})
public interface TouchpointAccessRemote {

	@POST
	public AbstractTouchpoint createTouchpointAndPointOfSale(AbstractTouchpoint touchpoint) throws ShoppingException;

	@GET
	public List<AbstractTouchpoint> readAllTouchpoints();

}
