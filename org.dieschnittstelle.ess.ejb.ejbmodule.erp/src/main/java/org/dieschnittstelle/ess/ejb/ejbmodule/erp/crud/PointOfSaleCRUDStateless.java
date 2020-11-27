package org.dieschnittstelle.ess.ejb.ejbmodule.erp.crud;

import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.dieschnittstelle.ess.entities.erp.PointOfSale;
import org.apache.logging.log4j.Logger;

/**
 * very rudimentary implementation without any logging... 
 */
@Stateless
public class PointOfSaleCRUDStateless implements PointOfSaleCRUDRemote, PointOfSaleCRUDLocal {

	protected static Logger logger = org.apache.logging.log4j.LogManager.getLogger(PointOfSaleCRUDStateless.class);
	
	@PersistenceContext(unitName = "erp_PU")
	private EntityManager em;
	
	/*
	 * TODO ADD1: comment in/out @TransactionAttribute
	 */
	@Override
	//@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public PointOfSale createPointOfSale(PointOfSale pos) {
		em.persist(pos);

		return pos;
	}

	@Override
	public PointOfSale readPointOfSale(long posId) {
		return em.find(PointOfSale.class,posId);
	}

	@Override
	public boolean deletePointOfSale(long posId) {
		em.remove(em.find(PointOfSale.class,posId));
		return true;
	}

	@Override
	public List<PointOfSale> readAllPointsOfSale() {
		return em.createQuery("SELECT p FROM PointOfSale AS p").getResultList();
	}

}
