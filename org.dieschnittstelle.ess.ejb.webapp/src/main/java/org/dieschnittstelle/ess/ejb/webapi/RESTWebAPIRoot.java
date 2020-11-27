package org.dieschnittstelle.ess.ejb.webapi;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import org.apache.logging.log4j.Logger;

/*
 * Note that in order for the webapi to work correctly, the option
 * -Dresteasy.preferJacksonOverJsonB=true must be set when starting
 * jboss, otherwise JsonTypeInfo will not be considered, see https://docs.jboss.org/resteasy/docs/4.3.1.Final/userguide/html/JAX-RS_2.1_additions.html
 */
@ApplicationPath("/api")
public class RESTWebAPIRoot extends Application {

	protected static Logger logger = org.apache.logging.log4j.LogManager.getLogger(RESTWebAPIRoot.class);
	
	public RESTWebAPIRoot() {
		logger.info("<constructor>");
	}

}
