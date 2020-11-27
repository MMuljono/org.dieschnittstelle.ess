package org.dieschnittstelle.ess.ejb.ejbmodule.crm.crud;

import javax.ejb.Local;

import org.dieschnittstelle.ess.entities.crm.Customer;

@Local
public interface CustomerCRUDLocal {	
	
	public Customer createCustomer(Customer customer);

	public Customer readCustomer(long id);

	public Customer updateCustomer(Customer customer);
		
	public boolean deleteCustomer(int id);
	
}
