package org.dieschnittstelle.ess.ejb.client.ejbclients;

import org.apache.logging.log4j.Logger;
import org.dieschnittstelle.ess.ejb.client.Constants;
import org.dieschnittstelle.ess.ejb.ejbmodule.crm.ShoppingException;
import org.dieschnittstelle.ess.ejb.ejbmodule.crm.TouchpointAccessRemote;
import org.dieschnittstelle.ess.ejb.ejbmodule.crm.TouchpointAccessRemoteAsync;
import org.dieschnittstelle.ess.entities.crm.AbstractTouchpoint;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class TouchpointAccessClientAsync implements TouchpointAccessRemote {

	protected static Logger logger = org.apache.logging.log4j.LogManager.getLogger(TouchpointAccessRemoteAsync.class);

	private TouchpointAccessRemoteAsync ejbProxy;

	public TouchpointAccessClientAsync() throws Exception {
		this.ejbProxy = EJBProxyFactory.getInstance().getProxy(TouchpointAccessRemoteAsync.class,Constants.TOUCHPOINT_ACCESS_ASYNC_BEAN_URI);
	}


	public List<AbstractTouchpoint> readAllTouchpoints() {
		try {
			Future<List<AbstractTouchpoint>> result = ejbProxy.readAllTouchpoints();
			return result.get();
		}
		catch (InterruptedException|ExecutionException e) {
			throw new RuntimeException(e.getMessage(),e);
		}
	}

	@Override
	public AbstractTouchpoint createTouchpointAndPointOfSale(AbstractTouchpoint touchpoint) throws ShoppingException {
		try {
			logger.info("createTouchpointAndPointOfSale()");
			Future<AbstractTouchpoint> resultFuture = ejbProxy.createTouchpointAndPointOfSale(touchpoint);
			logger.info("createTouchpointAndPointOfSale(): received result future...");
			AbstractTouchpoint created = resultFuture.get();
			logger.info("createTouchpointAndPointOfSale(): received result value");
			touchpoint.setId(created.getId());
			touchpoint.setErpPointOfSaleId(created.getErpPointOfSaleId());

			return created;
		}
		catch (InterruptedException|ExecutionException e) {
			throw new RuntimeException(e.getMessage(),e);
		}
	}
		
}
