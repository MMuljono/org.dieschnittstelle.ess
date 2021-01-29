package org.dieschnittstelle.ess.ejb.ejbmodule.erp.crud;

import java.util.List;

import javax.ejb.Local;

import org.dieschnittstelle.ess.entities.erp.IndividualisedProductItem;
import org.dieschnittstelle.ess.entities.erp.PointOfSale;
import org.dieschnittstelle.ess.entities.erp.StockItem;

/*
 * TODO EJB+JPA3/4/6:
 * this interface shall be implemented using a stateless EJB with an EntityManager.
 * See the comments below for hints at how to implement the methods
 */
@Local
public interface StockItemCRUDLocal {

    /*
     * before this method can be implemented and executed successfully, make
     * sure that the ProductCRUD EJB has been implemented and product
     * objects are persisted in the database.
     *
     * once you persist the item using the entity manager it is very likely that this will
     * first result in a PersistentObjectException due to the fact that the product
     * contained in the StockItem has not been introduced in the current
     * transaction context. In this case, there are two alternative solutions:
     * A) call merge() on the current value of the product attribute and
     * re-set the attribute to the return value of this call, then call
     * persist() on item
     * B) declare the association from StockItem to IndividualisedProductItem as
     * cascading for merge (only for merge!) and call merge() on item, which results
     * in persisting the item if it does not exist in the database yet
     */
    public StockItem createStockItem(StockItem item);

    /*
     * use the find() method of the EntityManager and pass it the
     * of the object to be read and the unique identifier of StockItem,
     * which is an instance of the primary key class ProductAtPosPK.
     * In case you observe an UnknownEntityException when calling this
     * method, check whether StockItem has been declared as an entity.
     */
    public StockItem readStockItem(IndividualisedProductItem prod, PointOfSale pos);

    /*
     * use the merge() method of the EntityManager
     */
    public StockItem updateStockItem(StockItem item);

    /*
     * here you can create a simple Query using em.createQuery() - see readAllPointsOfSale()
     * in PointOfSaleCRUDStateless as an example
     */
    public List<StockItem> readAllStockItems();

    /*
     * here you can create a Query using the id of the prod object -
	 * see readAllTransactionsForTouchpointAndCustomer() in
	 * CustomerTransactionCRUDStateless (in .ess.ejbmodule.crm) as an
	 * example
     */
    public List<StockItem> readStockItemsForProduct(IndividualisedProductItem prod);

    /*
     * here you can create a Query using the id of the pos object
     */
    public List<StockItem> readStockItemsForPointOfSale(PointOfSale pos);

}