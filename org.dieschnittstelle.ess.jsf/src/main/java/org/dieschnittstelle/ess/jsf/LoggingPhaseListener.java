package org.dieschnittstelle.ess.jsf;

import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;

public class LoggingPhaseListener implements PhaseListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5271527049488379483L;

	@Override
	public void afterPhase(PhaseEvent arg0) {
		System.out.println("******** AFTER: " + arg0.getPhaseId() + "********");
	}

	@Override
	public void beforePhase(PhaseEvent arg0) {
		System.out.println("******** BEFORE: " + arg0.getPhaseId() + "********");
	}

	@Override
	public PhaseId getPhaseId() {
		return PhaseId.ANY_PHASE;
	}
	
}
