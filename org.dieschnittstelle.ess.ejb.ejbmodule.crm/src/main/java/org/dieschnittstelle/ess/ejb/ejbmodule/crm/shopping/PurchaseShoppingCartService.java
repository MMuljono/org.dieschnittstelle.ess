package org.dieschnittstelle.ess.ejb.ejbmodule.crm.shopping;

import org.dieschnittstelle.ess.ejb.ejbmodule.crm.ShoppingException;
import org.dieschnittstelle.ess.entities.crm.AbstractTouchpoint;
import org.dieschnittstelle.ess.entities.crm.Customer;
import org.dieschnittstelle.ess.entities.erp.AbstractProduct;

// TODO: PAT1: this is the interface to be provided as a rest service if rest service access is used
public interface PurchaseShoppingCartService {

	void purchaseCartAtTouchpointForCustomer(long shoppingCartId, long touchpointId, long customerId) throws ShoppingException;
	
}
