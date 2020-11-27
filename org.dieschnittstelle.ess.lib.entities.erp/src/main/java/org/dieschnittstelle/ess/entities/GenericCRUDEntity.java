package org.dieschnittstelle.ess.entities;

import java.io.Serializable;

public interface GenericCRUDEntity extends Serializable {
	
	/*
	 * obtain an id
	 */
	public long getId();
	
	public void setId(long id);
	
}