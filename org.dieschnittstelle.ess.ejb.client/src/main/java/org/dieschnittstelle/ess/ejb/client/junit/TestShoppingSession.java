package org.dieschnittstelle.ess.ejb.client.junit;

import org.dieschnittstelle.ess.ejb.client.Constants;
import org.dieschnittstelle.ess.ejb.client.TotalUsecase;
import org.dieschnittstelle.ess.ejb.client.ejbclients.EJBProxyFactory;
import org.dieschnittstelle.ess.ejb.client.ejbclients.StockSystemClient;
import org.junit.Before;
import org.junit.Test;

import static org.dieschnittstelle.ess.ejb.client.Constants.*;
import static org.junit.Assert.assertEquals;

public class TestShoppingSession {

	private StockSystemClient stockSystemClient;
	
	@Before
	public void prepareContext() throws Exception {
		EJBProxyFactory.initialise();

		Constants.resetEntities();
		stockSystemClient = new StockSystemClient();
	}

	@Test
	public void stockSystemWorksWithShoppingSession() throws Exception {
		
		TotalUsecase uc = new TotalUsecase();
		uc.setStepping(false);
		uc.setProvokeErrorOnPurchase(false);
		uc.setUseShoppingSessionClient(true);
		
		uc.runAll();
		
		// we read out the number of units for the two products that have been purchased

		// here's how the numbers will be determined for *each* shopping cart:		
		// product_1: 2+3 (as individual items) + 5 (from campaign_1): 10 
		// product_2: 2 (as individual items) + 2 (from campaign_1) + 6 (from 2 * campaign_2): 10
		// two shopping carts will be processed successfully - the third one will be interrupted because campaigns are not available anymore. 
		
		// initially, 100 units have been put on stock for each product		
		assertEquals("after shopping cart processing, number of p1 is correct",80,stockSystemClient.getUnitsOnStock(PRODUCT_1, TOUCHPOINT_1.getErpPointOfSaleId()));
		assertEquals("after shopping cart processing, number of p2 is correct",80,stockSystemClient.getUnitsOnStock(PRODUCT_2, TOUCHPOINT_1.getErpPointOfSaleId()));
	}
	
}
