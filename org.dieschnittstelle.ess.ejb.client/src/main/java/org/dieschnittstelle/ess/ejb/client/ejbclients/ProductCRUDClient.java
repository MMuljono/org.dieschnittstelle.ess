package org.dieschnittstelle.ess.ejb.client.ejbclients;

import java.util.List;

import org.dieschnittstelle.ess.ejb.client.Constants;
import org.dieschnittstelle.ess.ejb.ejbmodule.erp.crud.ProductCRUDRemote;
import org.dieschnittstelle.ess.entities.erp.AbstractProduct;

public class ProductCRUDClient implements ProductCRUDRemote {

	private ProductCRUDRemote ejbProxy;

	public ProductCRUDClient() throws Exception {
		// TODO: obtain a proxy specifying the ejb interface and uri. Let all subsequent methods use the proxy.
//		this.ejbProxy = EJBProxyFactory.getInstance().getProxy(null,"");
	}

	public AbstractProduct createProduct(AbstractProduct prod) {

		// TODO: KOMMENTIEREN SIE DIE FOLGENDE ZUWEISUNG VON IDs UND DIE RETURN-ANWEISUNG AUS
		prod.setId(Constants.nextId());
		return prod;

		// TODO: KOMMENTIEREN SIE DEN FOLGENDEN CODE, INKLUSIVE DER ID ZUWEISUNG, EIN
//		AbstractProduct created = ejbProxy.createProduct(prod);
//		// as a side-effect we set the id of the created product on the argument before returning
//		prod.setId(created.getId());
//		return created;
	}

	public List<AbstractProduct> readAllProducts() {
//		return ejbProxy.readAllProducts();
		return null;
	}

	public AbstractProduct updateProduct(AbstractProduct update) {
//		return ejbProxy.updateProduct(update);
		return null;
	}

	public AbstractProduct readProduct(long productID) {
//		return ejbProxy.readProduct(productID);
		return null;
	}

	public boolean deleteProduct(long productID) {
//		return ejbProxy.deleteProduct(productID);
		return false;
	}

}
