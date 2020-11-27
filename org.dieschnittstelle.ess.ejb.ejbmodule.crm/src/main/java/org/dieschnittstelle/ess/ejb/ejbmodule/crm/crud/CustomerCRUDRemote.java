package org.dieschnittstelle.ess.ejb.ejbmodule.crm.crud;

import javax.ejb.Remote;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import org.dieschnittstelle.ess.entities.crm.Customer;

@Remote
@Path("/customers")
@Consumes({MediaType.APPLICATION_JSON})
@Produces({MediaType.APPLICATION_JSON})
public interface CustomerCRUDRemote {	

	@POST
	public Customer createCustomer(Customer customer);

	@GET
	@Path("/{customerId}")
	public Customer readCustomer(@PathParam("customerId") long id);

	// the update method is unorthodox, as it should normally identify the customer via its id in the path
	@PUT
	public Customer updateCustomer(Customer customer);

	@PUT
	public Customer updateCustomerWithSleep(Customer customer,@QueryParam("sleep") long sleep);

	@DELETE
	@Path("/{customerId}")
	public boolean deleteCustomer(@PathParam("customerId") int id);

	@GET
	public Customer readCustomerForEmail(@QueryParam("email") String email);

}
