package org.dieschnittstelle.ess.jsf;

import org.dieschnittstelle.ess.entities.crm.Address;
import org.dieschnittstelle.ess.entities.crm.Customer;
import org.dieschnittstelle.ess.entities.crm.Gender;
import org.dieschnittstelle.ess.entities.crm.MobileTouchpoint;
import org.dieschnittstelle.ess.entities.crm.StationaryTouchpoint;
import org.dieschnittstelle.ess.entities.erp.Campaign;
import org.dieschnittstelle.ess.entities.erp.IndividualisedProductItem;
import org.dieschnittstelle.ess.entities.erp.ProductBundle;
import org.dieschnittstelle.ess.entities.erp.ProductType;

/**
 * this class specifies a couple of entities for the domain objects that are used by the client classes
 */
public class Constants {
	
	/*
	 * the bean identifiers 
	 */
	public static final String SHOPPING_CART_BEAN = "ejb:org.dieschnittstelle.ess.ejb/org.dieschnittstelle.ess.ejb.ejbmodule.crm/ShoppingCartStateful!org.dieschnittstelle.ess.ejb.ejbmodule.crm.ShoppingCartRemote?stateful";
	public static final String CAMPAIGN_TRACKING_BEAN = "ejb:org.dieschnittstelle.ess.ejb/org.dieschnittstelle.ess.ejb.ejbmodule.crm/CampaignTrackingSingleton!org.dieschnittstelle.ess.ejb.ejbmodule.crm.CampaignTrackingRemote";
	public static final String CUSTOMER_TRACKING_BEAN = "ejb:org.dieschnittstelle.ess.ejb/org.dieschnittstelle.ess.ejb.ejbmodule.crm/customerTrackingSystem!org.dieschnittstelle.ess.ejb.ejbmodule.crm.CustomerTrackingRemote";
	public static final String CUSTOMER_CRUD_BEAN = "ejb:org.dieschnittstelle.ess.ejb/org.dieschnittstelle.ess.ejb.ejbmodule.crm/CustomerCRUDStateless!org.dieschnittstelle.ess.ejb.ejbmodule.crm.crud.CustomerCRUDRemote";
	public static final String TOUCHPOINT_CRUD_BEAN = "ejb:org.dieschnittstelle.ess.ejb/org.dieschnittstelle.ess.ejb.ejbmodule.crm/TouchpointCRUDStateless!org.dieschnittstelle.ess.ejb.ejbmodule.crm.crud.TouchpointCRUDRemote";
	public static final String TOUCHPOINT_ACCESS_BEAN = "ejb:org.dieschnittstelle.ess.ejb/org.dieschnittstelle.ess.ejb.ejbmodule.crm/TouchpointAccessStateless!org.dieschnittstelle.ess.ejb.ejbmodule.crm.TouchpointAccessRemote";
	public static final String TRANSACTIONS_CRUD_BEAN = "ejb:org.dieschnittstelle.ess.ejb/org.dieschnittstelle.ess.ejb.ejbmodule.crm/CustomerTransactionCRUDStateless!org.dieschnittstelle.ess.ejb.ejbmodule.crm.crud.CustomerTransactionCRUDRemote";
	public static final String POS_CRUD_BEAN = "ejb:org.dieschnittstelle.ess.ejb/org.dieschnittstelle.ess.ejb.ejbmodule.erp/PointOfSaleCRUDStateless!org.dieschnittstelle.ess.ejb.ejbmodule.crm.crud.PointOfSaleCRUDRemote";
	public static final String STOCK_SYSTEM_BEAN = "ejb:org.dieschnittstelle.ess.ejb/org.dieschnittstelle.ess.ejb.ejbmodule.erp/StockSystemSingleton!org.dieschnittstelle.ess.ejb.ejbmodule.crm.StockSystemRemote";
	
	/*
	 * constants for the objects that are dealt with in the different accessors to the beans
	 */

	public static StationaryTouchpoint TOUCHPOINT_1;

	public static StationaryTouchpoint TOUCHPOINT_2;

	public static MobileTouchpoint TOUCHPOINT_3;

	public static IndividualisedProductItem PRODUCT_1;

	public static IndividualisedProductItem PRODUCT_2;
	
	public static Campaign CAMPAIGN_1;

	public static Campaign CAMPAIGN_2;
	
	public static Customer CUSTOMER_1;

	public static Customer CUSTOMER_2;
	
	// instantiate the constants
	static {
		
		Address addr1 = new Address("Luxemburger Strasse", "10", "13353", "Berlin");
		TOUCHPOINT_1 = new StationaryTouchpoint(-1, "BHT Mensa", addr1);
		
		Address addr2 = new Address("Leopoldplatz", "1", "13353", "Berlin");
		TOUCHPOINT_2 = new StationaryTouchpoint(-1, "U Leopoldplatz", addr2);
		
		TOUCHPOINT_3 = new MobileTouchpoint("01778896571");

		PRODUCT_1 = new IndividualisedProductItem("Schrippe",	ProductType.ROLL, 720);
		PRODUCT_1.setId(1);
		PRODUCT_1.setPrice(39);

		PRODUCT_2 = new IndividualisedProductItem("Kirschplunder",ProductType.PASTRY, 1080);
		PRODUCT_2.setId(2);
		PRODUCT_2.setPrice(109);

		CAMPAIGN_1 = new Campaign();
		CAMPAIGN_1.setId(3);
		CAMPAIGN_1.addBundle(new ProductBundle(PRODUCT_1, 5));
		
		CAMPAIGN_2 = new Campaign();
		CAMPAIGN_2.setId(4);
		CAMPAIGN_2.addBundle(new ProductBundle(PRODUCT_2, 2));

		CUSTOMER_1 = new Customer("Anna", "Musterfrau", Gender.W);
		CUSTOMER_1.setAddress(new Address("Kopernikusstrasse","11","10245","Berlin"));

		CUSTOMER_2 = new Customer("Benedikt", "Mustermann", Gender.M);
		CUSTOMER_2.setAddress(new Address("Corinthstrasse","44","10245","Berlin"));
	}
	
}
