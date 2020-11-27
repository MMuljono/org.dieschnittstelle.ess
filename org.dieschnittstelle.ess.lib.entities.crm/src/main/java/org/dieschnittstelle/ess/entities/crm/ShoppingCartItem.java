package org.dieschnittstelle.ess.entities.crm;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.PostLoad;
import javax.persistence.PostPersist;
import javax.persistence.PostRemove;
import javax.persistence.PostUpdate;
import javax.persistence.PrePersist;
import javax.persistence.PreRemove;
import javax.persistence.PreUpdate;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.apache.logging.log4j.Logger;
import org.dieschnittstelle.ess.entities.erp.AbstractProduct;

/**
 * a product bundle on the part of the crm system that tracks the number of
 * units for some erpProductId and also tracks wheher the product is a campaign
 */
@Entity
public class ShoppingCartItem implements Serializable {

	protected static Logger logger = org.apache.logging.log4j.LogManager.getLogger(ShoppingCartItem.class);


	/**
	 * 
	 */
	private static final long serialVersionUID = 5027719621777767575L;

	@Id
	@GeneratedValue
	private long id;

	private long erpProductId;

	private int units;

	private boolean isCampaign;
	
	@Transient
	@JsonIgnore
	private AbstractProduct productObj;
	
	public ShoppingCartItem() {
		logger.info("<constructor>");
	}
	
	public ShoppingCartItem(long erpProductId, int units) {
		this(erpProductId, units, false);
	}

	public ShoppingCartItem(long erpProductId, int units, boolean isCampaign) {
		this.erpProductId = erpProductId;
		this.units = units;
		this.isCampaign = isCampaign;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setErpProductId(long erpProductId) {
		this.erpProductId = erpProductId;
	}

	public void setCampaign(boolean campaign) {
		isCampaign = campaign;
	}

	public long getId() {
		return id;
	}

	public long getErpProductId() {
		return erpProductId;
	}

	public int getUnits() {
		return units;
	}
	
	public void setUnits(int units) {
		this.units = units;
	}

	public boolean isCampaign() {
		return isCampaign;
	}

	public String toString() {
		return "<ShoppingCartItem " + this.id + " (" + this.erpProductId + ":"
				+ this.units + ")>";
	}

	/*
	 * lifecycle logging
	 */

	@PostLoad
	public void onPostLoad() {
		logger.info("@PostLoad: " + this);
	}

	@PostPersist
	public void onPostPersist() {
		logger.info("@PostPersist: " + this);
	}

	@PostRemove
	public void onPostRemove() {
		logger.info("@PostRemove: " + this);
	}

	@PostUpdate
	public void onPostUpdate() {
		logger.info("@PostUpdate: " + this);
	}

	@PrePersist
	public void onPrePersist() {
		logger.info("@PrePersist: " + this);
	}

	@PreRemove
	public void onPreRemove() {
		logger.info("@PreRemove: " + this);
	}

	@PreUpdate
	public void onPreUpdate() {
		logger.info("@PreUpdate: " + this);
	}

	public AbstractProduct getProductObj() {
		if (productObj == null) {
			throw new RuntimeException("ShoppingCartItem.productObj is transient and has not been set! Use erpProductId to access product.");
		}
		return productObj;
	}

	public void setProductObj(AbstractProduct productObj) {
		this.productObj = productObj;
	}

}
