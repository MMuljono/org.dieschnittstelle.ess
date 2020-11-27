package org.dieschnittstelle.ess.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
 * well, this util class is rather small, so far...
 */
public class Utils {

	protected static Logger logger = LogManager.getLogger(Utils.class);

	/*
	 * display some message, possibly using java string format
	 */
	public static void show(Object msg,Object... args) {
		String formatedmsg = "------------ ";

		if (msg != null && msg instanceof String && args != null && args.length > 0) {
			formatedmsg += String.format((String)msg,args);
		}
		else {
			formatedmsg += msg;
		}

		logger.info(formatedmsg + "\n");
	}


	/** 
	 * also this method is useful for demos
	 */
	public static void step() {
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			System.out.println("/>");
			br.readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
