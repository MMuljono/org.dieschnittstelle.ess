package org.dieschnittstelle.ess.jrs;

import java.util.List;

import org.dieschnittstelle.ess.entities.GenericCRUDExecutor;
import org.dieschnittstelle.ess.entities.crm.AbstractTouchpoint;
import org.dieschnittstelle.ess.entities.crm.StationaryTouchpoint;
import org.dieschnittstelle.ess.entities.erp.AbstractProduct;
import org.dieschnittstelle.ess.entities.erp.IndividualisedProductItem;

import org.apache.logging.log4j.Logger;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;



/*
 * TODO JRS2: implementieren Sie hier die im Interface deklarierten Methoden
 */

public class ProductCRUDServiceImpl implements IProductCRUDService {

	/*@Context
	private ServletContext servletContext;*/

	/*private GenericCRUDExecutor<AbstractTouchpoint> getExec() {
		return (GenericCRUDExecutor <AbstractTouchpoint>)servletContext.getAttribute("productCRUD");
	}*/
	private GenericCRUDExecutor<AbstractProduct> productCRUD;

	public ProductCRUDServiceImpl(@Context ServletContext servletContext, @Context HttpServletRequest request) {
		// read out the dataAccessor
		this.productCRUD = (GenericCRUDExecutor<AbstractProduct>) servletContext.getAttribute("productCRUD");
	}


	@Override
	public AbstractProduct createProduct(
			AbstractProduct prod) {
		return (AbstractProduct) this.productCRUD.createObject(prod);
		// TODO Auto-generated method stub
		/*return null;*/
	}

	@Override
	public List<AbstractProduct> readAllProducts() {
		// TODO Auto-generated method stub
		return (List) this.productCRUD.readAllObjects();
		/*return null;*/
	}

	@Override
	public AbstractProduct updateProduct(long id,
										 AbstractProduct update) {
		// TODO Auto-generated method stub
		return (IndividualisedProductItem) this.productCRUD.updateObject(update);
		/*return null;*/
	}

	@Override
	public boolean deleteProduct(long id) {
		// TODO Auto-generated method stub
		return this.productCRUD.deleteObject(id);
		/*return false;*/
	}

	@Override
	public AbstractProduct readProduct(long id) {
		// TODO Auto-generated method stub
		return (AbstractProduct) this.productCRUD.readObject(id);
		/*return null;*/
	}
	
}
