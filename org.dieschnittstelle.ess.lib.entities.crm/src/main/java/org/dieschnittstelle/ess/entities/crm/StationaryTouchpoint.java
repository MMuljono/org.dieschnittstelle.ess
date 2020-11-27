package org.dieschnittstelle.ess.entities.crm;

import org.apache.logging.log4j.Logger;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.PostLoad;
import javax.persistence.PostPersist;
import javax.persistence.PostRemove;
import javax.persistence.PostUpdate;
import javax.persistence.PrePersist;
import javax.persistence.PreRemove;
import javax.persistence.PreUpdate;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

/**
 * @author kreutel
 */
@XmlAccessorType(XmlAccessType.PROPERTY)
@XmlType(namespace = "http://dieschnittstelle.org/ess/entities/crm/ws")
@Entity
@DiscriminatorValue("stationary")
public class StationaryTouchpoint extends AbstractTouchpoint  implements Serializable {

	protected static Logger logger = org.apache.logging.log4j.LogManager.getLogger(StationaryTouchpoint.class);

	/**
	 * 
	 */
	private static final long serialVersionUID = -6123798695442913993L;
	
	/**
	 * we assume a OneToOne association: this is for demonstrating simple cascading of composition-type associations,
	 * see Customer->Address for a more intuitive handling of an "address" association as ManyToOne
	 */
	@OneToOne(cascade={CascadeType.ALL})
	private Address address;
	
	public StationaryTouchpoint() {
		logger.info("<constructor>");
	}
	
	public StationaryTouchpoint(int erpPointOfSaleId) {
		this.setErpPointOfSaleId(erpPointOfSaleId);
	}

	public StationaryTouchpoint(int erpPointOfSaleId,String name,Address address) {
		super.setErpPointOfSaleId(erpPointOfSaleId);
		super.setName(name);
		this.setAddress(address);
	}
	
	public String toString() {
		return "<StationaryTouchpoint " + this.id + "/" + this.erpPointOfSaleId + " " + this.name + " " + this.address + ">";
	}
	
	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
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
	
}
