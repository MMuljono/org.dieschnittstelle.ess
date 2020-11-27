package org.dieschnittstelle.ess.ejb.client.demos;

import org.dieschnittstelle.ess.ejb.client.TotalUsecase;
import org.dieschnittstelle.ess.ejb.client.ejbclients.EJBProxyFactory;

public class CreateTouchpoints {

	public static void main(String[] args) {
		EJBProxyFactory.initialise();

		try {
			TotalUsecase uc = new TotalUsecase();
			uc.createTouchpoints();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
