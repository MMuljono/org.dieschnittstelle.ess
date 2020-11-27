package org.dieschnittstelle.ess.ser;

import java.io.File;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.dieschnittstelle.ess.entities.crm.AbstractTouchpoint;
import org.dieschnittstelle.ess.entities.GenericCRUDExecutor;
import org.apache.logging.log4j.Logger;

public class TouchpointServletContextListener implements ServletContextListener {

	protected static Logger logger = org.apache.logging.log4j.LogManager
			.getLogger(TouchpointServletContextListener.class);

	@Override
	public void contextDestroyed(ServletContextEvent evt) {
		logger.info("contextDestroyed()");

		// we read out the TouchpointCRUDExecutor and let it store its content
		GenericCRUDExecutor<AbstractTouchpoint> exec = (GenericCRUDExecutor<AbstractTouchpoint>) evt
				.getServletContext().getAttribute("touchpointCRUD");

		logger.info("contextDestroyed(): loaded executor from context: " + exec);

		if (exec == null) {
			logger.warn("contextDestroyed(): no executor found in context. Ignore.");
		} else {
			exec.store();
		}
	}

	@Override
	public void contextInitialized(ServletContextEvent evt) {

		logger.info("contextInitialised()");

		// we create a new executor for a file to be stored in the context root
		String rootPath = evt.getServletContext().getRealPath("../");
		
		GenericCRUDExecutor<AbstractTouchpoint> exec = new GenericCRUDExecutor<AbstractTouchpoint>(new File(
				rootPath, "touchpoints.data"));

		// we call load() on the executor to load any exsisting data (if there
		// are any)
		exec.load();

		// then we put the executor into the context to make it available to the
		// other components
		evt.getServletContext().setAttribute("touchpointCRUD", exec);
	}

}
