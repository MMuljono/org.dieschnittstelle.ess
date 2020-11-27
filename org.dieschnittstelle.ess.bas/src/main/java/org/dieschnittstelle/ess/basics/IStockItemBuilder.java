package org.dieschnittstelle.ess.basics;

import org.w3c.dom.Element;

public interface IStockItemBuilder {

	public IStockItem buildStockItemFromElement(Element el);
	
}
