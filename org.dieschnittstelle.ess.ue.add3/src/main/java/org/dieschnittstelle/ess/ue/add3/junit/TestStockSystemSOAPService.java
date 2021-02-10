package org.dieschnittstelle.ess.ue.add3.junit;

import static org.junit.Assert.*;

import org.dieschnittstelle.ess.ejb.ejbmodule.erp.StockSystemRemote;
import org.dieschnittstelle.ess.entities.erp.ws.IndividualisedProductItem;
import org.dieschnittstelle.ess.ue.StockSystemWebService;
import org.junit.Before;
import org.junit.Test;

import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.bind.annotation.XmlSeeAlso;
import java.util.List;


// TODO: generate classes given the wsdl
// TODO: remove the comments
// TODO: import all required classes from the generated packages

/*
 * 
 */

public class TestStockSystemSOAPService {
	private StockSystemRemote serviceProxy;
	StockSystemWebService service = new StockSystemWebService();

	@Before
	public void prepareContext() {
		// TODO instantiate the serviceProxy by instantiating the generated web service class and getting the port for StockSystemRemote
		serviceProxy = service.getStockSystemSingletonPort();
	}

	@Test
	public void stockSystemServiceWorks() {
		// read out all products
		List<IndividualisedProductItem> products = serviceProxy.getAllProductsOnStock();
		assertTrue("stock exists and can be read", products.size() > 0);

		// we are using the first product for the tests...
		IndividualisedProductItem testprod = products.get(0);

		// obtain the poss where the first product is available
		List<Long> poss = serviceProxy.getPointsOfSale(testprod);
		assertTrue("selected product is associated with at least one point of sale", poss.size() > 0);

		// we are using the first pos for the tests...
		long testpos = poss.get(0);

		// obtain the local and total units
		int unitsAtPos = serviceProxy.getUnitsOnStock(testprod, testpos);
		int unitsTotal = serviceProxy.getTotalUnitsOnStock(testprod);

		// add units for the first pos
		int unitsToAdd = 5;
		serviceProxy.addToStock(testprod, testpos, unitsToAdd);

		// compare the final units
		assertEquals("after adding units, units at pos correctly incremented", unitsAtPos + unitsToAdd, serviceProxy.getUnitsOnStock(testprod, testpos));
		assertEquals("after adding units, total units correctly incremented", unitsTotal + unitsToAdd, serviceProxy.getTotalUnitsOnStock(testprod));


	}
	
}
