package org.dieschnittstelle.ess.wsv.interpreter.json;

public class ObjectMappingException extends Exception {

	public ObjectMappingException(Exception e) {
		super(e);
	}

	public ObjectMappingException(String msg) {
		super(msg);
	}

}
