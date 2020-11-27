package org.dieschnittstelle.ess.entities.erp;

import java.io.Serializable;


public class ProductAtPosPK implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9113210426279286629L;

	private IndividualisedProductItem product;

	private PointOfSale pos;

	public ProductAtPosPK() {
	}

	public ProductAtPosPK(IndividualisedProductItem product,
			PointOfSale pos) {
		this.product = product;
		this.pos = pos;
	}

	public IndividualisedProductItem getProduct() {
		return product;
	}

	public void setProduct(IndividualisedProductItem product) {
		this.product = product;
	}

	public PointOfSale getPos() {
		return pos;
	}

	public void setPos(PointOfSale pos) {
		this.pos = pos;
	}

	// see http://uaihebert.com/?p=42
	public int hashCode() {
		return this.product.hashCode() + this.pos.hashCode();
	}

	public boolean equals(Object obj) {

		if (obj == null)
			return false;
		if (obj.getClass() != this.getClass())
			return false;

		return this.product.getId() == ((ProductAtPosPK) obj).getProduct()
				.getId()
				&& this.pos.getId() == ((ProductAtPosPK) obj)
						.getPos().getId();
	}

}
