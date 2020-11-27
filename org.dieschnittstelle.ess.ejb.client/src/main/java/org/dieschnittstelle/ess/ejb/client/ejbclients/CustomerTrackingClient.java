package org.dieschnittstelle.ess.ejb.client.ejbclients;

import java.util.List;

import org.dieschnittstelle.ess.ejb.ejbmodule.crm.CustomerTrackingRemote;
import org.dieschnittstelle.ess.entities.crm.CustomerTransaction;
import org.dieschnittstelle.ess.ejb.client.Constants;

public class CustomerTrackingClient implements CustomerTrackingRemote {

	private CustomerTrackingRemote ejbProxy;
	
	public CustomerTrackingClient() throws Exception {
		ejbProxy = EJBProxyFactory.getInstance().getProxy(CustomerTrackingRemote.class,Constants.CUSTOMER_TRACKING_BEAN_URI);
	}
	
	@Override
	public void createTransaction(CustomerTransaction transaction) {
		ejbProxy.createTransaction(transaction);
	}

	@Override
	public List<CustomerTransaction> readAllTransactions() {
		return ejbProxy.readAllTransactions();
	}

}
