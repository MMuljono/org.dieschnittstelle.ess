package org.dieschnittstelle.ess.entities.crm;

import java.io.Serializable;
import org.apache.logging.log4j.Logger;

import javax.persistence.Entity;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

@Entity
public class Address extends Location  implements Serializable {

	protected static Logger logger = org.apache.logging.log4j.LogManager.getLogger(Address.class);

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String street;
	
	private String houseNr;
	
	private String zipCode;
	
	private String city;
	
	public Address() {
		logger.info("<constructor>");
	}
	
	public Address(String street,String houseNr,String zipCode,String city) {
		this.street = street;
		this.houseNr = houseNr;
		this.zipCode = zipCode;
		this.city = city;
	}

	public Address(String street,String houseNr,String zipCode,String city, long geoLat,long geoLong) {
		this(street,houseNr,zipCode,city);
		this.setGeoLat(geoLat);
		this.setGeoLong(geoLong);
	}
		
	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getHouseNr() {
		return houseNr;
	}

	public void setHouseNr(String houseNr) {
		this.houseNr = houseNr;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}
	
	public String toString() {
		return "<Address " + this.getId() + ", " + this.street + " " + this.houseNr + ", " + this.zipCode + " " + this.city + ">";
	}
	
	public boolean equals(Object other) {
		return EqualsBuilder.reflectionEquals(this, other);
	}
	
	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this);
	}

}
