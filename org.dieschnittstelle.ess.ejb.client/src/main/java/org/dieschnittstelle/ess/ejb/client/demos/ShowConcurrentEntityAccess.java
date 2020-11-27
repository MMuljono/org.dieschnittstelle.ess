package org.dieschnittstelle.ess.ejb.client.demos;

import org.apache.logging.log4j.Logger;
import org.dieschnittstelle.ess.ejb.client.ejbclients.EJBProxyFactory;
import org.dieschnittstelle.ess.entities.crm.Customer;
import org.dieschnittstelle.ess.ejb.client.Constants;
import org.dieschnittstelle.ess.utils.Utils;
import org.dieschnittstelle.ess.ejb.client.ejbclients.CustomerCRUDClient;

public class ShowConcurrentEntityAccess {

	protected static Logger logger = org.apache.logging.log4j.LogManager
			.getLogger(ShowConcurrentEntityAccess.class);

	public static void main(String[] args) {
		EJBProxyFactory.initialise();

		try {
			
			final CustomerCRUDClient customerCRUD = new CustomerCRUDClient();

			final Customer cust = customerCRUD
					.createCustomer(Constants.CUSTOMER_2);
			logger.info("created customer: " + cust);

			Utils.step();

			// we start two threads that access the bean - the first call will
			// return
			// Max even though the first name has been changed in the
			// meantime to Manfred by the subsequent call. However, actual
			// commitment of the write transaction is done after the sleep, i.e.
			// Max will be finally written to the db and will be returned to the
			// client.

			// this shows that entities are not dealt with as singletons, but
			// that each client access runs in its own
			// transaction, and each transaction has its own copy of the entity
			new Thread(new Runnable() {

				@Override
				public void run() {
					cust.setFirstName("Max");
					logger.info("sleep10000: will set customer: " + cust);
					Customer mycust = customerCRUD.updateCustomerWithSleep(
							cust, 10000);

					logger.info("sleep10000: got customer: " + mycust);
				}
			}).start();

			// Utils.step();
			// sleep a second before we start the second thread
			Thread.sleep(1000);

			// we start a second thread - this one will return Manfred because
			// the transaction is committed before the sleep10000 transaction,
			// which will finally override the value written here
			new Thread(new Runnable() {

				@Override
				public void run() {
					cust.setFirstName("Manfred");
					logger.info("sleep5000: will set customer: " + cust);

					Customer mycust = customerCRUD.updateCustomerWithSleep(
							cust, 5000);
					logger.info("sleep5000: got customer: " + mycust);

					// we do another access without sleep
					Customer intermcust = customerCRUD.readCustomer(mycust.getId());
					logger.info("intermediately read customer for id " + mycust.getId() + ": "
							+ intermcust);
				}
			}).start();

			Utils.step();

			// once everything is done, we read out the customer again
			Customer finalcust = customerCRUD.readCustomer(cust.getId());
			logger.info("finally read customer for id " + cust.getId() + ": "
					+ finalcust);

		} catch (Throwable t) {
			t.printStackTrace();
		}

	}

}
