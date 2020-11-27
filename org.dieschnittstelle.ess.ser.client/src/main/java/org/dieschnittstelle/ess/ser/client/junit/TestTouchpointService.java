package org.dieschnittstelle.ess.ser.client.junit;

import java.util.List;

import org.dieschnittstelle.ess.entities.crm.AbstractTouchpoint;
import org.dieschnittstelle.ess.entities.crm.Address;
import org.dieschnittstelle.ess.entities.crm.StationaryTouchpoint;
import org.dieschnittstelle.ess.ser.client.ShowTouchpointService;
import org.junit.Test;

import static org.junit.Assert.*;

public class TestTouchpointService {

	@Test
	public void serviceWorks() {

		// create the accessor for the service
		ShowTouchpointService client = new ShowTouchpointService();
		client.setStepwise(false);
		
		// create a touchpoint
		Address addr = new Address("Luxemburger Strasse", "10", "13353",
				"Berlin");
		StationaryTouchpoint tp = new StationaryTouchpoint(-1,
				"BHT Verkaufsstand", addr);

		// read out all touchpoints
		List<AbstractTouchpoint> initialTps = client.readAllTouchpoints();

		// create a new touchpoint
		tp = (StationaryTouchpoint) client.createNewTouchpoint(tp);
		List<AbstractTouchpoint> newTps = client.readAllTouchpoints();
		assertNotNull("touchpoint creation returns an object", tp);
		assertEquals("list of touchpoints is extended on creation",
				initialTps.size() + 1, newTps.size());
		AbstractTouchpoint createdTp = getTouchpointFromList(newTps, tp);
		assertTrue("created touchpoint coincides with local copy", tp.getName()
				.equals(createdTp.getName()));
		assertNotNull("created touchpoint contains embedded address",
				((StationaryTouchpoint) createdTp).getAddress());

		// delete the new touchpoint
		client.deleteTouchpoint(tp);
		assertEquals("deletion reduces touchpoint list", initialTps.size(),
				client.readAllTouchpoints().size());

	}

	private AbstractTouchpoint getTouchpointFromList(
			List<AbstractTouchpoint> tps, AbstractTouchpoint tp) {
		for (AbstractTouchpoint currentTp : tps) {
			if (currentTp.getId() == tp.getId()) {
				return currentTp;
			}
		}
		return null;
	}

}
