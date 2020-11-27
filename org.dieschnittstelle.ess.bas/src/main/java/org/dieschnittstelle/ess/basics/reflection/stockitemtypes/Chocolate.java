package org.dieschnittstelle.ess.basics.reflection.stockitemtypes;

import org.dieschnittstelle.ess.basics.IStockItem;

import static org.dieschnittstelle.ess.utils.Utils.*;

public class Chocolate implements IStockItem {

	private int units;
	
	private String brandname;
	
	private int price;

	private String expiry;
	
	public Chocolate() {
		show("Chocolate: constructor invoked");
	}
	
	public Chocolate(String brandname) {
		this.brandname = brandname;
	}
	
	
	@Override
	public void initialise(int units,String brandname) {
		show("Chocolate: intialise() invoked");

		this.units = units;
		this.brandname = brandname;
	}

	@Override
	public int getUnits() {
		return this.units;
	}

	@Override
	public void setUnits(int units) {
		this.units = units;
	}

	@Override
	public String toString() {
		return String.format("<Chocolate %s %d %d %s>", this.brandname, this.units, this.price, this.expiry);
	}

	public String getBrandname() {
		return brandname;
	}

	public void setBrandname(String brandname) {
		this.brandname = brandname;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		show("Chocolate: setPrice() invoked");

		this.price = price;
	}

	public String getExpiry() {
		return expiry;
	}

	public void setExpiry(String expiry) {
		this.expiry = expiry;
	}
}
