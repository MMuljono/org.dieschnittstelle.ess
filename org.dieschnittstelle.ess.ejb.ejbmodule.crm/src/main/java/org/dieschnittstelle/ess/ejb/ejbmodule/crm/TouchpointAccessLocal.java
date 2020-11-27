package org.dieschnittstelle.ess.ejb.ejbmodule.crm;

import java.util.List;

import javax.ejb.Local;

import org.dieschnittstelle.ess.entities.crm.AbstractTouchpoint;

@Local
public interface TouchpointAccessLocal {

	public AbstractTouchpoint createTouchpointAndPointOfSale(AbstractTouchpoint touchpoint) throws ShoppingException;

	public List<AbstractTouchpoint> readAllTouchpoints();
	
}
