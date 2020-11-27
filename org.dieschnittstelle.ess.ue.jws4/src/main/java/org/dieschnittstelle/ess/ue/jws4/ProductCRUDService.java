package org.dieschnittstelle.ess.ue.jws4;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.ParameterStyle;
import javax.servlet.ServletContext;
import javax.ws.rs.GET;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.WebServiceContext;
import javax.xml.ws.handler.MessageContext;

import org.dieschnittstelle.ess.entities.GenericCRUDExecutor;
import org.dieschnittstelle.ess.entities.crm.AbstractTouchpoint;
import org.dieschnittstelle.ess.entities.erp.AbstractProduct;
import org.dieschnittstelle.ess.entities.erp.Campaign;
import org.dieschnittstelle.ess.entities.erp.IndividualisedProductItem;
import org.dieschnittstelle.ess.entities.erp.ProductType;

/*
 * TODO JWS4: machen Sie die Funktionalitaet dieser Klasse als Web Service verfuegbar und verwenden Sie fuer
 *  die Umetzung der Methoden die Instanz von GenericCRUDExecutor<AbstractProduct>,
 *  die Sie aus dem ServletContext auslesen koennen
 */
@WebService(targetNamespace = "http://dieschnittstelle.org/ess/jws",serviceName = "ProductCRUDWebService", name = "IProductCRUDService", portName = "ProductCRUDPort")
@SOAPBinding
@XmlSeeAlso({IndividualisedProductItem.class, ProductType.class})
public class ProductCRUDService {

	@Resource
	private WebServiceContext webServiceContext;

	@WebMethod
	private GenericCRUDExecutor <AbstractProduct> getExec(){
		ServletContext servletContext = (ServletContext) webServiceContext.getMessageContext().get(MessageContext.SERVLET_CONTEXT);
		GenericCRUDExecutor <AbstractProduct> exec = (GenericCRUDExecutor) servletContext.getAttribute("productCRUD");
		return exec;
	}

	@WebMethod
	public List<AbstractProduct> readAllProducts() {
//		ServletContext servletContext = (ServletContext) webServiceContext.getMessageContext().get(MessageContext.SERVLET_CONTEXT);
//		GenericCRUDExecutor<AbstractProduct> exec = (GenericCRUDExecutor<AbstractProduct>) servletContext.getAttribute("touchpointCRUD");
//		return exec.readAllObjects();
		return getExec().readAllObjects();
	}

	@WebMethod
	public AbstractProduct createProduct(AbstractProduct product) {
//		ServletContext servletContext = (ServletContext) webServiceContext.getMessageContext().get(MessageContext.SERVLET_CONTEXT);
//		GenericCRUDExecutor<AbstractProduct> exec = (GenericCRUDExecutor<AbstractProduct>) servletContext.getAttribute("touchpointCRUD");
//		return exec.createObject(product);

		return getExec().createObject(product);
	}

	@WebMethod
	public AbstractProduct updateProduct(AbstractProduct update) {
//		ServletContext servletContext = (ServletContext) webServiceContext.getMessageContext().get(MessageContext.SERVLET_CONTEXT);
//		GenericCRUDExecutor<AbstractProduct> exec = (GenericCRUDExecutor<AbstractProduct>) servletContext.getAttribute("touchpointCRUD");
//		return exec.updateObject(update);

		return getExec().updateObject(update);
	}

	@WebMethod
	public boolean deleteProduct(long id) {

//		ServletContext servletContext = (ServletContext) webServiceContext.getMessageContext().get(MessageContext.SERVLET_CONTEXT);
//		GenericCRUDExecutor<AbstractProduct> exec = (GenericCRUDExecutor<AbstractProduct>) servletContext.getAttribute("touchpointCRUD");
//		return exec.deleteObject(id);
		return getExec().deleteObject(id);
	}

	@WebMethod
	public AbstractProduct readProduct(long id) {
//		ServletContext servletContext = (ServletContext) webServiceContext.getMessageContext().get(MessageContext.SERVLET_CONTEXT);
//		GenericCRUDExecutor<AbstractProduct> exec = (GenericCRUDExecutor<AbstractProduct>) servletContext.getAttribute("touchpointCRUD");
//		return exec.readObject(id);

		return getExec().readObject(id);
	}

}
