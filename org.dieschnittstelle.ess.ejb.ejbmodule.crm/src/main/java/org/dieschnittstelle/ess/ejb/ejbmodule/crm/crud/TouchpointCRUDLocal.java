package org.dieschnittstelle.ess.ejb.ejbmodule.crm.crud;

import java.util.List;

import javax.ejb.Local;

import org.dieschnittstelle.ess.ejb.ejbmodule.crm.ShoppingException;
import org.dieschnittstelle.ess.entities.crm.AbstractTouchpoint;

@Local
public interface TouchpointCRUDLocal {
	
	public AbstractTouchpoint createTouchpoint(AbstractTouchpoint Touchpoint) throws ShoppingException;

	public AbstractTouchpoint readTouchpoint(long id);

	public List<AbstractTouchpoint> readAllTouchpoints();
	
	public AbstractTouchpoint updateTouchpoint(AbstractTouchpoint Touchpoint);
		
	public boolean deleteTouchpoint(int id);

}
