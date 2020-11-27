package org.dieschnittstelle.ess.ejb.client.demos;

import org.dieschnittstelle.ess.ejb.client.Constants;
import org.dieschnittstelle.ess.ejb.client.ejbclients.EJBProxyFactory;
import org.dieschnittstelle.ess.ejb.ejbmodule.crm.crud.TouchpointCRUDRemote;

/* demonstrate direct access to the CRUD layer */
public class CreateTouchpointsAccessingCRUD {

	public static void main(String[] args) {
		EJBProxyFactory.initialise();

		try {
			TouchpointCRUDRemote tpCRUD = EJBProxyFactory.getInstance().getProxy(TouchpointCRUDRemote.class,"ejb:org.dieschnittstelle.ess.ejb/org.dieschnittstelle.ess.ejb.ejbmodule.crm/TouchpointCRUDStateless!org.dieschnittstelle.ess.ejb.ejbmodule.crm.crud.TouchpointCRUDRemote");
			tpCRUD.createTouchpoint(Constants.TOUCHPOINT_1);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
