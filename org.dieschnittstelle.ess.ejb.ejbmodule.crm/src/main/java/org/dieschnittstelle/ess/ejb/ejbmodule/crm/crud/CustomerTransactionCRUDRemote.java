package org.dieschnittstelle.ess.ejb.ejbmodule.crm.crud;

import java.util.Collection;

import javax.ejb.Remote;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import org.dieschnittstelle.ess.entities.crm.AbstractTouchpoint;
import org.dieschnittstelle.ess.entities.crm.Customer;
import org.dieschnittstelle.ess.entities.crm.CustomerTransaction;

@Remote
@Path("/transactions")
@Consumes({MediaType.APPLICATION_JSON})
@Produces({MediaType.APPLICATION_JSON})
public interface CustomerTransactionCRUDRemote {

	// as we take over the signature of the ejbs, we use put request passing the arguments as body (rather than
	// a get request passing an id via a query parameter)
	@PUT
	@Path("/read-for-touchpoint")
	public Collection<CustomerTransaction> readAllTransactionsForTouchpoint(AbstractTouchpoint touchpoint);

	@PUT
	@Path("/read-for-customer")
	public Collection<CustomerTransaction> readAllTransactionsForCustomer(Customer customer);

	// this method is currently not considered for being accessed via the rest interface as the two arguments would
	// ideally need to be wrapped into a single container
	// in case the method is not annotated we obtain a client-side exception when trying to create a service proxy,
	// seems there currently does not exist a possibility to exclude methods from being exposed as service operations
	@HEAD
	public Collection<CustomerTransaction> readAllTransactionsForTouchpointAndCustomer(AbstractTouchpoint touchpoint, Customer customer);

}
