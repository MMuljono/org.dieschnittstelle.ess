package org.dieschnittstelle.ess.basics;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * at the moment, this class does not do that much. It only keeps on iterating
 * over a list of consumables and consumes a part of each of it until each
 * consumable is consumed
 */
public class Consumer {

	/*
	 * some random generator for determining the number of units that will be
	 * purchased
	 */
	private static final Random randomiser = new Random();

	public void doShopping(List<IStockItem> items) {

		for (IStockItem item : items) {
			// determine the number of units for each consumable
			int units = randomiser.nextInt(item.getUnits() + 1);
			System.out.println("WILL PURCHASE " + units + " OF: " + item);
			item.purchase(units);
		}

	}

}
