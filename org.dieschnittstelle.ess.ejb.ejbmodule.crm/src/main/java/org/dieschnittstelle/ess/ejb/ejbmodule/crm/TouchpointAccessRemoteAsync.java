package org.dieschnittstelle.ess.ejb.ejbmodule.crm;

import org.dieschnittstelle.ess.entities.crm.AbstractTouchpoint;

import javax.ejb.Remote;
import java.util.List;
import java.util.concurrent.Future;

@Remote
public interface TouchpointAccessRemoteAsync {

    public Future<AbstractTouchpoint> createTouchpointAndPointOfSale(AbstractTouchpoint touchpoint) throws ShoppingException;

    public Future<List<AbstractTouchpoint>> readAllTouchpoints();

}
