package org.dieschnittstelle.ess.ejb.client.shopping;

import org.apache.logging.log4j.Logger;
import org.dieschnittstelle.ess.ejb.ejbmodule.crm.ShoppingException;
import org.dieschnittstelle.ess.entities.crm.AbstractTouchpoint;
import org.dieschnittstelle.ess.entities.crm.Customer;
import org.dieschnittstelle.ess.entities.erp.AbstractProduct;

public class ShoppingSessionClient implements ShoppingBusinessDelegate {

	protected static Logger logger = org.apache.logging.log4j.LogManager
			.getLogger(ShoppingSessionClient.class);

	/*
	 * TODO PAT1: use an appropriate proxy for the server-side shopping interface, depending on whether
	 *  it is a stateful shopping session ejb (ShoppingSessionFacadeRemote) or a stateless
	 *  rest service (PurchaseShoppingCartService)
	 *  Note that, if the rest service is used, touchpoint and customer need to be stored locally
	 *  before purchase() is invoked. For accessing shopping cart data use a local ShoppingCartClient
	 *  in this case and access the shopping cart using the provided getter method
	 */

	public ShoppingSessionClient() {
		/* TODO: instantiate the proxy using the EJBProxyFactory (see the other client classes) */
	}

	/* TODO: implement the following methods s */

	@Override
	public void setTouchpoint(AbstractTouchpoint touchpoint) {
	
	}

	@Override
	public void setCustomer(Customer customer) {
	
	}

	@Override
	public void addProduct(AbstractProduct product, int units) {
	
	}

	@Override
	public void purchase() throws ShoppingException {
	
	}

}
