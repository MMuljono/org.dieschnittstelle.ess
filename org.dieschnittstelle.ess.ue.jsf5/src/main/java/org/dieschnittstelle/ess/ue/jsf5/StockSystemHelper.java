package org.dieschnittstelle.ess.ue.jsf5;

import java.util.Collection;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

import org.dieschnittstelle.ess.ejb.ejbmodule.crm.CampaignTrackingRemote;
import org.dieschnittstelle.ess.ejb.ejbmodule.crm.TouchpointAccessRemote;
import org.dieschnittstelle.ess.ejb.ejbmodule.crm.crud.CustomerCRUDRemote;
import org.dieschnittstelle.ess.ejb.ejbmodule.crm.crud.CustomerTransactionCRUDRemote;
import org.dieschnittstelle.ess.entities.crm.AbstractTouchpoint;
import org.dieschnittstelle.ess.entities.crm.Address;
import org.dieschnittstelle.ess.entities.crm.CampaignExecution;
import org.dieschnittstelle.ess.entities.crm.Customer;
import org.dieschnittstelle.ess.entities.crm.CustomerTransaction;
import org.dieschnittstelle.ess.entities.crm.Gender;
import org.dieschnittstelle.ess.entities.crm.MobileTouchpoint;
import org.dieschnittstelle.ess.entities.crm.StationaryTouchpoint;
import org.dieschnittstelle.ess.ejb.ejbmodule.erp.StockSystemRemote;
import org.dieschnittstelle.ess.ejb.ejbmodule.erp.crud.ProductCRUDRemote;
import org.dieschnittstelle.ess.entities.erp.Campaign;
import org.dieschnittstelle.ess.entities.erp.IndividualisedProductItem;
import org.dieschnittstelle.ess.entities.erp.ProductBundle;
import org.dieschnittstelle.ess.entities.erp.ProductType;
import org.apache.log4j.Logger;

@Named
@ApplicationScoped
public class StockSystemHelper {

	protected static Logger logger = Logger.getLogger(StockSystemHelper.class);

	// the entities
	private StationaryTouchpoint TOUCHPOINT_1;

	private StationaryTouchpoint TOUCHPOINT_2;

	private MobileTouchpoint TOUCHPOINT_3;

	private IndividualisedProductItem PRODUCT_1;

	private IndividualisedProductItem PRODUCT_2;

	private Campaign CAMPAIGN_1;

	private Campaign CAMPAIGN_2;

	private Customer CUSTOMER_1;

	private Customer CUSTOMER_2;

	// instantiate the constants
	public StockSystemHelper() {

		Address addr1 = new Address("Luxemburger Strasse", "10", "13353",
				"Berlin");
		TOUCHPOINT_1 = new StationaryTouchpoint(0, "BHT Mensa", addr1);

		Address addr2 = new Address("Leopoldplatz", "1", "13353", "Berlin");
		TOUCHPOINT_2 = new StationaryTouchpoint(0, "U Leopoldplatz", addr2);

		TOUCHPOINT_3 = new MobileTouchpoint("01778896571");
		TOUCHPOINT_3.setName("Mobiler Verkaufsstand");

		PRODUCT_1 = new IndividualisedProductItem("Schrippe", ProductType.ROLL,
				720);
		PRODUCT_2 = new IndividualisedProductItem("Kirschplunder",
				ProductType.PASTRY, 1080);

		CAMPAIGN_1 = new Campaign();
		CAMPAIGN_1.addBundle(new ProductBundle(PRODUCT_1, 5));
		CAMPAIGN_1.addBundle(new ProductBundle(PRODUCT_2, 2));

		CAMPAIGN_2 = new Campaign();
		CAMPAIGN_2.addBundle(new ProductBundle(PRODUCT_2, 3));

		CUSTOMER_1 = new Customer("Anna", "Musterfrau", Gender.W);
		CUSTOMER_1.setAddress(new Address("Kopernikusstrasse", "11", "10245",
				"Berlin"));
		CUSTOMER_1.setEmail("anna@example.com");

		CUSTOMER_2 = new Customer("Benedikt", "Mustermann", Gender.M);
		CUSTOMER_2.setAddress(new Address("Corinthstrasse", "44", "10245",
				"Berlin"));
		CUSTOMER_2.setEmail("bene@example.com");
	}

	// the ejbs

	// declare the attributes that will be instantiated with the ejb clients
	// TODO: use your StockSystemRemote and ProductCRUDRemote EJB interfaces that have been developed in the exercises for JPA - the names for ejbs and interfaces used here are the ones from the demos, change where necessary
	@Resource(mappedName = "java:global/org.dieschnittstelle.ess.ejb/org.dieschnittstelle.ess.ejb.ejbmodule.erp/ProductCRUDStateless!org.dieschnittstelle.ess.ejb.ejbmodule.erp.crud.ProductCRUDRemote")
	private ProductCRUDRemote productCRUD;
	@Resource(mappedName = "java:global/org.dieschnittstelle.ess.ejb/org.dieschnittstelle.ess.ejb.ejbmodule.crm/TouchpointAccessStateless!org.dieschnittstelle.ess.ejb.ejbmodule.crm.TouchpointAccessRemote")
	private TouchpointAccessRemote touchpointAccess;
	@Resource(mappedName = "java:global/org.dieschnittstelle.ess.ejb/org.dieschnittstelle.ess.ejb.ejbmodule.erp/StockSystemSingleton!org.dieschnittstelle.ess.ejb.ejbmodule.erp.StockSystemRemote")
	private StockSystemRemote stockSystem;
	@Resource(mappedName = "java:global/org.dieschnittstelle.ess.ejb/org.dieschnittstelle.ess.ejb.ejbmodule.crm/CustomerCRUDStateless!org.dieschnittstelle.ess.ejb.ejbmodule.crm.crud.CustomerCRUDRemote")
	private CustomerCRUDRemote customerCRUD;
	@Resource(mappedName = "java:global/org.dieschnittstelle.ess.ejb/org.dieschnittstelle.ess.ejb.ejbmodule.crm/CampaignTrackingSingleton!org.dieschnittstelle.ess.ejb.ejbmodule.crm.CampaignTrackingRemote")
	private CampaignTrackingRemote campaignTracking;
	@Resource(mappedName = "java:global/org.dieschnittstelle.ess.ejb/org.dieschnittstelle.ess.ejb.ejbmodule.crm/CustomerTransactionCRUDStateless!org.dieschnittstelle.ess.ejb.ejbmodule.crm.crud.CustomerTransactionCRUDRemote")
	private CustomerTransactionCRUDRemote transactionCRUD;

	public void createProducts() {
		// create products
		PRODUCT_1.setId(productCRUD.createProduct(PRODUCT_1).getId());
		PRODUCT_2.setId(productCRUD.createProduct(PRODUCT_2).getId());

		// TODO: consider campaigns
		CAMPAIGN_1.setId(productCRUD.createProduct(CAMPAIGN_1).getId());
		CAMPAIGN_2.setId(productCRUD.createProduct(CAMPAIGN_2).getId());

		System.out.println("\n***************** created products\n");
	}

	public void createTouchpoints() {
		try {
			// create touchpoints
			AbstractTouchpoint tp1 = touchpointAccess
					.createTouchpointAndPointOfSale(TOUCHPOINT_1);
			TOUCHPOINT_1.setId(tp1.getId());
			TOUCHPOINT_1.setErpPointOfSaleId(tp1.getErpPointOfSaleId());
			AbstractTouchpoint tp2 = touchpointAccess
					.createTouchpointAndPointOfSale(TOUCHPOINT_2);
			TOUCHPOINT_2.setId(tp2.getId());
			TOUCHPOINT_2.setErpPointOfSaleId(tp2.getErpPointOfSaleId());

			System.out.println("\n***************** created touchpoints\n");
		}
		catch (Exception e) {
			throw new RuntimeException("Got exception trying to create touchpoints: " + e,e);
		}
	}

	public void createStock() {
		// create stock
		stockSystem.addToStock(PRODUCT_1, TOUCHPOINT_1.getErpPointOfSaleId(),
				100);
		stockSystem.addToStock(PRODUCT_1, TOUCHPOINT_2.getErpPointOfSaleId(),
				110);
		stockSystem.addToStock(PRODUCT_2, TOUCHPOINT_1.getErpPointOfSaleId(),
				200);
		stockSystem.addToStock(PRODUCT_2, TOUCHPOINT_2.getErpPointOfSaleId(),
				220);

		System.out.println("\n***************** created stock\n");
	}

	public void prepareCampaigns() {
		// create campaign executions
		campaignTracking.addCampaignExecution(new CampaignExecution(
				TOUCHPOINT_1, CAMPAIGN_1.getId(), 20, -1));
		campaignTracking.addCampaignExecution(new CampaignExecution(
				TOUCHPOINT_1, CAMPAIGN_2.getId(), 10, -1));

		logger.info("campaigns are: "
				+ campaignTracking.getAllCampaignExecutions());

		System.out.println("\n***************** created campaign executions\n");
	}

	public void createCustomers() {
		// create customers
		CUSTOMER_1.setId(customerCRUD.createCustomer(CUSTOMER_1).getId());
		CUSTOMER_2.setId(customerCRUD.createCustomer(CUSTOMER_2).getId());

		System.out.println("\n***************** created customers\n");
	}

	/**
	 * this must be used for JSF6
	 *
	 * TODO: use your ShoppingSessionFacade here - you should declare it as a @Resource obtaining a client proxy via dependency injection
	 */
	public void doShopping() {
		try {
			// TODO: remove the comments once session facade is declared as an instance attribute above

			// add a customer and a touchpoint
//			session.setCustomer(CUSTOMER_1);
//			session.setTouchpoint(TOUCHPOINT_1);
//
//			// now add items
//			session.addProduct(PRODUCT_1, 2);
//			session.addProduct(PRODUCT_1, 3);
//			session.addProduct(PRODUCT_2, 2);
//			session.addProduct(CAMPAIGN_1, 1);
//			session.addProduct(CAMPAIGN_2, 2);
//
//			// now try to commit the session
//			session.purchase();
		}
		catch(Exception e) {
			throw new RuntimeException("got exception during shopping: " + e, e);
		}
	}


	public void showTransactions() {
		System.out.println("\n***************** show transactions\n");

		Collection<CustomerTransaction> trans = transactionCRUD
				.readAllTransactionsForTouchpoint(TOUCHPOINT_1);
		logger.info("transactions for touchpoint are: " + trans);
		trans = transactionCRUD.readAllTransactionsForCustomer(CUSTOMER_1);
		logger.info("transactions for customer are: " + trans);
		trans = transactionCRUD.readAllTransactionsForTouchpointAndCustomer(
				TOUCHPOINT_1, CUSTOMER_1);
		logger.info("transactions for touchpoint and customer are: " + trans);
		// now try to read out the transactions by obtaining the customer
		// and retrieving getTransactions()
		trans = CUSTOMER_1.getTransactions();
		logger.info("transactions on local customer object are: " + trans);

		Customer cust = customerCRUD.readCustomer(CUSTOMER_1.getId());
		logger.info("read remote customer object: " + cust);
		trans = cust.getTransactions();
		logger.info("read transactions from remote object: " + trans);

	}

	@PostConstruct
	public void prepareData() {
		createProducts();
		createTouchpoints();
		createStock();
		prepareCampaigns();
		createCustomers();
	}

}
