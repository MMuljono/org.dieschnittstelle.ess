package org.dieschnittstelle.ess.ejb.ejbmodule.crm;

import org.dieschnittstelle.ess.entities.crm.AbstractTouchpoint;

import javax.ejb.AsyncResult;
import javax.ejb.Asynchronous;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.List;
import java.util.concurrent.Future;

@Stateless
@Asynchronous
public class TouchpointAccessStatelessAsync implements TouchpointAccessRemoteAsync {

    @EJB
    private TouchpointAccessLocal touchpointAccessSync;


    @Override
    public Future<AbstractTouchpoint> createTouchpointAndPointOfSale(AbstractTouchpoint touchpoint) throws ShoppingException {
        try {
            Thread.sleep(1000);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return new AsyncResult<>(touchpointAccessSync.createTouchpointAndPointOfSale(touchpoint));
    }

    @Override
    public Future<List<AbstractTouchpoint>> readAllTouchpoints() {
        return new AsyncResult<>(touchpointAccessSync.readAllTouchpoints());
    }

}
