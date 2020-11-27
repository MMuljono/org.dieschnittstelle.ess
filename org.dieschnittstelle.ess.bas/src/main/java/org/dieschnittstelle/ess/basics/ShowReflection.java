package org.dieschnittstelle.ess.basics;

import org.dieschnittstelle.ess.basics.reflection.ReflectedStockItemBuilder;

import static org.dieschnittstelle.ess.utils.Utils.*;

public class ShowReflection {
	
	public static void main(String[] args) {
		// we initialise the collection
		StockItemCollection collection = new StockItemCollection("stockitems_reflection.xml", new ReflectedStockItemBuilder());
		// we load the contents into the collection
		collection.load();
		// we initialise a consumer
		Consumer consumer = new Consumer();
		// ... and let them consume
		consumer.doShopping(collection.getStockItems());
	}

}
