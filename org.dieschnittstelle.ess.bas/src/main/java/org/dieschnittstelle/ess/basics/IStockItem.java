package org.dieschnittstelle.ess.basics;

import static org.dieschnittstelle.ess.utils.Utils.show;

public interface IStockItem {

	default void purchase(int unitsToPurchase) {
		if (unitsToPurchase > this.getUnits()) {
			throw new RuntimeException(
					"You cannot purchase more than what is available. Got: "
							+ unitsToPurchase
							+ " units to purchase, but have available only: "
							+ this.getUnits());
		}
		this.setUnits(this.getUnits() - unitsToPurchase);
	}


	void initialise(int units, String brandname);
	
	int getUnits();

	void setUnits(int units);
	
	String getBrandname();
		
}
