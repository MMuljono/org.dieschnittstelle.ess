package org.dieschnittstelle.ess.ejb.client.demos;

import org.dieschnittstelle.ess.ejb.client.TotalUsecase;
import org.dieschnittstelle.ess.ejb.client.ejbclients.EJBProxyFactory;

public class PrepareCampaigns {

	public static void main(String[] args) {
		EJBProxyFactory.initialise();

		TotalUsecase uc;
		try {
			uc = new TotalUsecase();
			uc.prepareCampaigns();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
}
