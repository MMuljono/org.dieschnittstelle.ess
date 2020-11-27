package org.dieschnittstelle.ess.ejb.ejbmodule.erp.crud;

import java.util.List;

import javax.ejb.Local;

import org.dieschnittstelle.ess.entities.erp.PointOfSale;

@Local
public interface PointOfSaleCRUDLocal {
	
	public PointOfSale createPointOfSale(PointOfSale pos);

	public PointOfSale readPointOfSale(long posId);

	public boolean deletePointOfSale(long posId);

	public List<PointOfSale> readAllPointsOfSale();

}

