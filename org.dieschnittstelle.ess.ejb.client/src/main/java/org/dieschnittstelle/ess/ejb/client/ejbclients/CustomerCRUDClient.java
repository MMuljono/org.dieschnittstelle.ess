package org.dieschnittstelle.ess.ejb.client.ejbclients;

import org.dieschnittstelle.ess.ejb.ejbmodule.crm.crud.CustomerCRUDRemote;
import org.dieschnittstelle.ess.entities.crm.Customer;
import org.dieschnittstelle.ess.ejb.client.Constants;

public class CustomerCRUDClient implements CustomerCRUDRemote {

	private CustomerCRUDRemote ejbProxy;

	public CustomerCRUDClient() throws Exception {
		ejbProxy = EJBProxyFactory.getInstance().getProxy(CustomerCRUDRemote.class,Constants.CUSTOMER_CRUD_BEAN_URI);
	}

	@Override
	public Customer readCustomerForEmail(String email) {
		return ejbProxy.readCustomerForEmail(email);
	}

	@Override
	public Customer createCustomer(Customer customer) {
		Customer created = ejbProxy.createCustomer(customer);
		
		// as a side-effect, we set the id on the customer object
		customer.setId(created.getId());
		// we also set the id of the address, which might have been initially created, as a side-effect
		customer.getAddress().setId(created.getAddress().getId());
		
		return created;
	}

	@Override
	public Customer readCustomer(long id) {
		return ejbProxy.readCustomer(id);
	}

	@Override
	public Customer updateCustomer(Customer customer) {
		return ejbProxy.updateCustomer(customer);
	}

	@Override
	public Customer updateCustomerWithSleep(Customer customer, long sleep) {
		return ejbProxy.updateCustomerWithSleep(customer, sleep);
	}

	@Override
	public boolean deleteCustomer(int id) {
		return ejbProxy.deleteCustomer(id);
	}

}
