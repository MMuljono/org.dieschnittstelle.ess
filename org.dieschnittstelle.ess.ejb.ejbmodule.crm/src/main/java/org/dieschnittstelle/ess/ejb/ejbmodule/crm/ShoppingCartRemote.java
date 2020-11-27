package org.dieschnittstelle.ess.ejb.ejbmodule.crm;

import java.util.List;

import org.dieschnittstelle.ess.entities.crm.ShoppingCartItem;

import javax.ejb.Remote;

@Remote
public interface ShoppingCartRemote {

	public void addItem(ShoppingCartItem product);
	
	public List<ShoppingCartItem> getItems();
	
}
