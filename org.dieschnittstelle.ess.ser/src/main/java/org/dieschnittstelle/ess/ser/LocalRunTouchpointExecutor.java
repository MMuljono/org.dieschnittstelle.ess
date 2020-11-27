package org.dieschnittstelle.ess.ser;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import static org.dieschnittstelle.ess.utils.Utils.*;

import org.dieschnittstelle.ess.entities.crm.AbstractTouchpoint;
import org.dieschnittstelle.ess.entities.crm.MobileTouchpoint;

public class LocalRunTouchpointExecutor {

	public static void main(String[] args) {
		
		int count = 0;
		
		File datafile = new File("./touchpoints.data");
		
		show("using datafile: " + datafile.getAbsolutePath());
		
		TouchpointCRUDExecutor exec = new TouchpointCRUDExecutor(datafile);
		show("after loading: " + exec.readAllTouchpoints());
		
		exec.load();
		while (true) {
			String cmd = readInput();
			if ("c".equals(cmd)) {
				AbstractTouchpoint tp = new MobileTouchpoint("01700000000");
				tp.setName("TP" + (count++));
				show(exec.createTouchpoint(tp));
			}
			else if ("r".equals(cmd)) {
				show(exec.readAllTouchpoints());
			}
			else if ("d".equals(cmd)) {
				List<AbstractTouchpoint> tps = exec.readAllTouchpoints();
				if (tps.size() > 0) {
					show(exec.deleteTouchpoint(tps.get(0).getId()));
				}
			}
			else if ("s".equals(cmd)) {
				exec.store();
				break;
			}
		}

		
		System.out.println("done.");
		
	}
	
	
	private static String readInput() {
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			
			System.out.println("/>");
			return br.readLine().trim().toLowerCase();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "";
	}
	
}
