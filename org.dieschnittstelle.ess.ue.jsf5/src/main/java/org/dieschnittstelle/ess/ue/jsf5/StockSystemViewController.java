package org.dieschnittstelle.ess.ue.jsf5;

import java.util.*;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.dieschnittstelle.ess.ejb.ejbmodule.crm.TouchpointAccessLocal;
import org.dieschnittstelle.ess.ejb.ejbmodule.crm.TouchpointAccessStateless;
import org.dieschnittstelle.ess.ejb.ejbmodule.erp.StockSystemLocal;
import org.dieschnittstelle.ess.entities.crm.AbstractTouchpoint;
import org.dieschnittstelle.ess.entities.erp.IndividualisedProductItem;
import org.dieschnittstelle.ess.entities.erp.PointOfSale;
import org.dieschnittstelle.ess.entities.erp.StockItem;
import org.apache.log4j.Logger;

/* declare the class as named component using CDI annotations */
@Named("vc")
/* TODO declare the class as application scoped using the CDI annotation */
public class StockSystemViewController {

	protected static Logger logger = Logger
			.getLogger(StockSystemViewController.class);

	/*
	 * TODO declare a dependency to the stock system ejb via a new local interface,
	 * using the @Resource annotation and the mappedName:
	 * java:global/org.dieschnittstelle.ess.ejb/org.dieschnittstelle.ess.ejb.ejbmodule.erp/StockSystemSingleton!org.dieschnittstelle.ess.ejb.ejbmodule.erp.StockSystemLocal
	 */

	/*
	 * use the helper bean - this is needed for JSF6
	 */
	@Inject
	private StockSystemHelper stockSystemHelper;

	/*
	 * TODO: later on (after only displaying the list), we also need access to the
	 * touchpoints ejb, using the @Resource annotation and the mappedName:
	 * java:global/org.dieschnittstelle.ess.ejb/org.dieschnittstelle.ess.ejb.ejbmodule.crm/TouchpointAccessStateless!org.dieschnittstelle.ess.ejb.ejbmodule.crm.TouchpointAccessLocal
	 */

	/*
	 * these are local structures created from the data read out from the beans
	 */
	/* the map of stock items */
	private Map<String, StockItem> stockItemsMap = new TreeMap<String, StockItem>();

	/*
	 * TODO: return the values of the stockItemsMap. Note that you might need to create a new ArrayList from the values
	 */
	public Collection<StockItem> getStockItems() {
		return new ArrayList<StockItem>();
	}

	/*
	 * TODO: add a method that retrieves a touchpoint name given some point of sale id
	 */

	/*
	 * the action method for updating the units of a stockItem
	 */
	public String updateUnits() {
		/*
		 * we access the parameters from the FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap()
		 */
		/* TODO: read out the parameter(s) that we need to identify the item to be updated */

		/*
		 * TODO: call addToStock() on stockSystem. Note that once StockItem is read out from the map it will already have the new
		 * amount of units set via the UI, which is the complete amount of units. However, addToStock() expects an amount of units
		 * that will be added to the current amount. Find a solution for this issue without adding additional EJB dependencies
		 */


		/* returning the empty string here results in keeping the current view */
		return "";
	}

	/*
	 * TODO: add a method for updating the price of a stock item
	 */

	/*
	 * a method that calls the doShopping() method on stockSystemHelper
	 */
	public void doShopping() {
		stockSystemHelper.doShopping();
		refreshView();
	}

	/*
	 * a method for refreshing the view
	 */
	public void refreshView() {
		logger.info("refreshView()");
		// we call loadData
		loadData();
	}

	/*
	 * a method loadData() that loads all stock items and uses some wrapper
	 * classes to provide an integrated view on the data enriching the items
	 * with touchpoint names from crm
	 *
	 * TODO: should be called once this bean is created
	 */
	public void loadData() {

		logger.info("@PostConstruct: helper is: " + stockSystemHelper);

		// TODO: remove the comments (you might start with the first for statement and keep the second one commented until access to the touchpoints bean is done)
//		this.stockItemsMap.clear();
//
//		/*
//		 * read out the stock items and create the stock items map
//		 */
//		for (StockItem item : stockSystem.getCompleteStock()) {
//			this.stockItemsMap.put(getIdForStockItem(item), item);
//		}

	}

	/*
	 * helper methods for identifying a stock item based on the ids of its product and pointOfSale
	 */

	private static final String ID_SEPARATOR = "___";

	public String getIdForStockItem(StockItem item) {
		return item.getProduct().getId() + ID_SEPARATOR + item.getPos().getId();
	}

	public StockItem getStockItemForIds(String prodId,String posId) {
		return this.stockItemsMap.get(prodId + ID_SEPARATOR + posId);
	}

}
