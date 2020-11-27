package org.dieschnittstelle.ess.jsf.model;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import org.dieschnittstelle.ess.ejb.ejbmodule.crm.ShoppingCartLocal;
import org.dieschnittstelle.ess.entities.crm.ShoppingCartItem;
import org.apache.log4j.Logger;

/**
 * this class works as a presentation-side proxy of the shopping cart EJB business component
 * 
 * due to the usage of the local EJB interface, all changes of product bundles are immediately propagated to the ejb
 */
@Named
@SessionScoped
public class ShoppingCartModel implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 161019355728792587L;

	protected static Logger logger = Logger.getLogger(ShoppingCartModel.class);

	/*
	 * show by-reference vs. by-value semantics of local vs. remote!
	 */
	@EJB(lookup="java:global/org.dieschnittstelle.ess.ejb/org.dieschnittstelle.ess.ejb.ejbmodule.crm/ShoppingCartStateful!org.dieschnittstelle.ess.ejb.ejbmodule.crm.ShoppingCartLocal")
	private ShoppingCartLocal shoppingCart;
	
//	using the remote interface the call-by-value behaviour is very obvious...
//	@EJB(lookup="java:global/org.dieschnittstelle.ess.ejb/org.dieschnittstelle.ess.ejb.ejbmodule.crm/ShoppingCartStateful!ShoppingCartRemote")
//	private ShoppingCartRemote shoppingCart;
	
	public List<ShoppingCartItem> getItems() {
		return shoppingCart.getItems();
	}
	
	/**
	 * add a product
	 * 
	 * @param bundle
	 * @return
	 */
	public void addProduct(ShoppingCartItem bundle) {
		logger.info("addProduct()");
		shoppingCart.addItem(bundle);
	}
	
	/*
	 * additional methods for accessing information from the cart
	 */
	
	public int getNumOfProductsInCart() {
		logger.info("countProductsInCart()");
		int totalCount = 0;
		for (ShoppingCartItem item : shoppingCart.getItems()) {
			totalCount += item.getUnits();
		}

		return totalCount;
	}

	public int getPriceOfProductsInCart() {
		logger.info("getPriceOfProductsInCart()");
		int totalPrice = 0;
		for (ShoppingCartItem item : shoppingCart.getItems()) {
			totalPrice += item.getUnits()
					* item
                    .getProductObj().getPrice();
		}

		return totalPrice;
	}

}
