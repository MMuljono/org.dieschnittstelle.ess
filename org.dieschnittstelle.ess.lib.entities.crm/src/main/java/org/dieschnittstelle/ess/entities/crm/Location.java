package org.dieschnittstelle.ess.entities.crm;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.PostLoad;
import javax.persistence.PostPersist;
import javax.persistence.PostRemove;
import javax.persistence.PostUpdate;
import javax.persistence.PrePersist;
import javax.persistence.PreRemove;
import javax.persistence.PreUpdate;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.logging.log4j.Logger;

@Entity
// this may result in problems with hibernate, see http://stackoverflow.com/questions/916169/cannot-use-identity-column-key-generation-with-union-subclass-table-per-clas
//@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
public class Location implements Serializable {

	protected static Logger logger = org.apache.logging.log4j.LogManager.getLogger(Location.class);

	/**
	 * 
	 */
	private static final long serialVersionUID = -131090102062445239L;

	private long id;
	
	private long geoLat;
	
	private long geoLong;

	public Location() {
		logger.info("<constructor>");
	}
	
	@Id
	@GeneratedValue
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}

	public long getGeoLat() {
		return geoLat;
	}

	public void setGeoLat(long geoLat) {
		this.geoLat = geoLat;
	}

	public long getGeoLong() {
		return geoLong;
	}

	public void setGeoLong(long geoLong) {
		this.geoLong = geoLong;
	}
	
	public boolean equals(Object other) {
		return EqualsBuilder.reflectionEquals(this, other);
	}
	
	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this);
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
