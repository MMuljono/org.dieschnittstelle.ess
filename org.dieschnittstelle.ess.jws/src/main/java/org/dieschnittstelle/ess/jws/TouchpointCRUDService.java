package org.dieschnittstelle.ess.jws;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.xml.ws.WebServiceContext;
import javax.xml.ws.handler.MessageContext;

import org.dieschnittstelle.ess.entities.crm.AbstractTouchpoint;
import org.dieschnittstelle.ess.entities.crm.StationaryTouchpoint;
import org.dieschnittstelle.ess.entities.GenericCRUDExecutor;
import org.apache.logging.log4j.Logger;

@WebService(targetNamespace = "http://dieschnittstelle.org/ess/jws", name = "ITouchpointCRUDService", serviceName = "TouchpointCRUDWebService", portName = "TouchpointCRUDPort")
@SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.WRAPPED)
public class TouchpointCRUDService {

	protected static Logger logger = org.apache.logging.log4j.LogManager
			.getLogger(TouchpointCRUDService.class);

	@Resource
	private WebServiceContext wscontext;

	public TouchpointCRUDService() {
		logger.info("<constructor>");
	}

	@PostConstruct
	@WebMethod(exclude = true)
	public void initialiseContext() {
		logger.info("@PostConstruct: the wscontext is: " + wscontext);

		// we cannot read out any context attributes (ServletContext,
		// HttpServletRequest) from the WebServiceContext as this is only
		// allowed from a thread that actually handles a particular request to a
		// service operation
		// wscontext.getMessageContext().get(MessageContext.SERVLET_CONTEXT);
	}

	@WebMethod
	public List<AbstractTouchpoint> readAllTouchpoints() {
		logger.info("readAllTouchpoints()");

		logger.info("readAllTouchpoints(): I am: " + this);
		
		// we obtain the servlet context from the wscontext
		ServletContext ctx = (ServletContext) wscontext.getMessageContext()
				.get(MessageContext.SERVLET_CONTEXT);
		logger.info("readAllTouchpoints(): servlet context is: " + ctx);
		// we also read out the http request
		HttpServletRequest httpRequest = (HttpServletRequest) wscontext
				.getMessageContext().get(MessageContext.SERVLET_REQUEST);
		logger.info("readAllTouchpoints(): servlet request is: " + httpRequest);

		GenericCRUDExecutor<AbstractTouchpoint> touchpointCRUD = (GenericCRUDExecutor<AbstractTouchpoint>) ctx
				.getAttribute("touchpointCRUD");
		logger.info("readAllTouchpoints(): read touchpointCRUD from servletContext: "
				+ touchpointCRUD);
		
		// check that more than one requests is handled by the same instance of this class simulataneously
//		try {
//			Thread.sleep(30000);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}

		return touchpointCRUD.readAllObjects();
	}

	@WebMethod
	public AbstractTouchpoint createTouchpoint(AbstractTouchpoint touchpoint) {
		// obtain the CRUD executor from the servlet context
		GenericCRUDExecutor<AbstractTouchpoint> touchpointCRUD = (GenericCRUDExecutor<AbstractTouchpoint>) ((ServletContext) wscontext
				.getMessageContext().get(MessageContext.SERVLET_CONTEXT))
				.getAttribute("touchpointCRUD");

		return (StationaryTouchpoint) touchpointCRUD
				.createObject(touchpoint);
	}

	@WebMethod
	public boolean deleteTouchpoint(long id) {
		logger.info("deleteTouchpoint(): sleeping...");

		// for demonstrating async processing, we include a sleep here
		try {
			Thread.sleep(2000);
		}
		catch (InterruptedException e) {
			e.printStackTrace();
		}

		logger.info("deleteTouchpoint(): continuing...");

		// obtain the CRUD executor from the servlet context
		GenericCRUDExecutor<AbstractTouchpoint> touchpointCRUD = (GenericCRUDExecutor<AbstractTouchpoint>) ((ServletContext) wscontext
				.getMessageContext().get(MessageContext.SERVLET_CONTEXT))
				.getAttribute("touchpointCRUD");

		return touchpointCRUD.deleteObject(id);
	}
	
	/*
	 * TODO JWS3: erweitern Sie den Service
	 */

}
