package org.dieschnittstelle.ess.ejb.ejbmodule.crm.crud;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.dieschnittstelle.ess.entities.crm.Customer;
import org.apache.logging.log4j.Logger;

@Stateless
public class CustomerCRUDStateless implements CustomerCRUDRemote, CustomerCRUDLocal {

	protected static Logger logger = org.apache.logging.log4j.LogManager.getLogger(CustomerCRUDStateless.class);
	
	@PersistenceContext(unitName = "crm_PU")
	private EntityManager em;
	
	@Override
	public Customer createCustomer(Customer customer) {

		// using merge rather than persist here will result in persisting the Customer instance, as well as its
		// address value in case the latter has not been persisted yet
		customer = em.merge(customer);

		return customer;
	}

	@Override
	public Customer readCustomer(long id) {
		Customer customer = em.find(Customer.class, id);

		return customer;
	}

	@Override
	public Customer updateCustomer(Customer customer) {
		customer = em.merge(customer);

		return customer;
	}
	
	@Override
	public Customer updateCustomerWithSleep(Customer customer,long sleep) {	
		logger.info("sleep" + sleep + "@" + this + ": entity manager is: " + em);
		logger.info("sleep" + sleep + "@" + this + ": before merge(): got remote: " + customer);
		// we read out the customer using the provided method
		logger.info("sleep" + sleep + "@" + this + ": before merge(): got local: " + readCustomer(customer.getId()));
		
		customer = em.merge(customer);
		logger.info("sleep" + sleep + "@" + this + ": after merge(): " + customer);

		try {
			Thread.sleep(sleep);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		logger.info("sleep" + sleep + "@" + this + ": after sleep(): " + customer);

		return customer;		
	}

	@Override
	public boolean deleteCustomer(int id) {
		em.remove(em.find(Customer.class,id));

		return true;
	}

	/*
	 * UE JSF3: implementieren Sie die Methode readCustomerForEmail()
	 */
	@Override
	public Customer readCustomerForEmail(String email) {
		// TODO Auto-generated method stub
		return null;
	}
	

}
