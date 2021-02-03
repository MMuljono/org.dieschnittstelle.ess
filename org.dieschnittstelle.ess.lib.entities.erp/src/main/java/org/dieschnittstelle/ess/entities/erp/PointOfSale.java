package org.dieschnittstelle.ess.entities.erp;

import java.io.Serializable;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import org.apache.logging.log4j.Logger;

@Entity
//@JsonTypeInfo(use=JsonTypeInfo.Id.CLASS, include=JsonTypeInfo.As.PROPERTY, property="@class")
@SequenceGenerator(name = "pos_sequence", sequenceName = "pos_id_sequence")
public class PointOfSale implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3397160788902953608L;
	
	protected static Logger logger = org.apache.logging.log4j.LogManager.getLogger(PointOfSale.class);
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pos_sequence")
	private long id;

	public PointOfSale() {
		logger.info("<constructor>");
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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
