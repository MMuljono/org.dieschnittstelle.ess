package org.dieschnittstelle.ess.ser;

import org.apache.http.HttpStatus;
import org.apache.logging.log4j.Logger;

import javax.servlet.AsyncContext;
import javax.servlet.RequestDispatcher;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ObjectOutputStream;

import static org.dieschnittstelle.ess.utils.Utils.show;

@WebServlet(urlPatterns = "/api/async/touchpoints", asyncSupported = true)
public class TouchpointServiceServletAsync extends HttpServlet {

	protected static Logger logger = org.apache.logging.log4j.LogManager
			.getLogger(TouchpointServiceServletAsync.class);

	public TouchpointServiceServletAsync() {
		show("TouchpointServiceServlet: constructor invoked\n");
	}
	
	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) {

		logger.info("doGet()");

		AsyncContext asyncCtx = request.startAsync();
		RequestDispatcher dispatcher = asyncCtx.getRequest().getRequestDispatcher("/api/touchpoints");

		new Thread(()->{
			logger.info("doGet(): sleeping...");
			try {
				Thread.sleep(2000);
			}
			catch (InterruptedException e) {
				e.printStackTrace();
			}
			logger.info("doGet(): continuing...");
			try {
				dispatcher.forward(asyncCtx.getRequest(), asyncCtx.getResponse());
			}
			catch (Exception e) {
				response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
			}
		}).start();

	}
	
	/*
	@Override	
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) {

		// assume POST will only be used for touchpoint creation, i.e. there is
		// no need to check the uri that has been used

		// obtain the executor for reading out the touchpoints from the servlet context using the touchpointCRUD attribute

		try {
			// create an ObjectInputStream from the request's input stream
		
			// read an AbstractTouchpoint object from the stream
		
			// call the create method on the executor and take its return value
		
			// set the response status as successful, using the appropriate
			// constant from HttpServletResponse
		
			// then write the object to the response's output stream, using a
			// wrapping ObjectOutputStream
		
			// ... and write the object to the stream
		
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

	}
	*/


	
}
