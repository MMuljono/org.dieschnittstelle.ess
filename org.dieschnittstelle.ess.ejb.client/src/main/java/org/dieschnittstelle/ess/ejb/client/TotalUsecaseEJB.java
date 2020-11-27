package org.dieschnittstelle.ess.ejb.client;

import org.apache.logging.log4j.Logger;
import org.dieschnittstelle.ess.ejb.client.ejbclients.*;

import static org.dieschnittstelle.ess.ejb.client.Constants.*;

public class TotalUsecaseEJB {

	protected static Logger logger = org.apache.logging.log4j.LogManager.getLogger(TotalUsecaseEJB.class);

	public static void main(String[] args) {
		// here, we will use ejb proxies for accessing the server-side components
		EJBProxyFactory.initialise(false);

		try {
			(new TotalUsecase()).runAll();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
