package org.dieschnittstelle.ess.ejb.ejbmodule.erp;

import org.dieschnittstelle.ess.ejb.ejbmodule.erp.crud.PointOfSaleCRUDLocal;
import org.dieschnittstelle.ess.ejb.ejbmodule.erp.crud.StockItemCRUDLocal;
import org.dieschnittstelle.ess.entities.erp.IndividualisedProductItem;
import org.dieschnittstelle.ess.entities.erp.PointOfSale;
import org.dieschnittstelle.ess.entities.erp.StockItem;

import javax.ejb.EJB;
import javax.ejb.Singleton;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Singleton
public class StockSystemSingleton implements StockSystemLocal {

    @EJB
    private StockItemCRUDLocal siCRUD;

    @EJB
    private PointOfSaleCRUDLocal posCRUD;

    @Override
    public void addToStock(IndividualisedProductItem product, long pointOfSaleId, int units) {
        PointOfSale pos = posCRUD.readPointOfSale(pointOfSaleId);
        StockItem stockItem = siCRUD.readStockItem(product,pos);
        if (stockItem == null) {
            stockItem = new StockItem(product, pos, units);
            siCRUD.createStockItem(stockItem);
        } else {
            stockItem.setUnits(stockItem.getUnits()+units);
            siCRUD.updateStockItem(stockItem);
        }
    }

    @Override
    public void removeFromStock(IndividualisedProductItem product, long pointOfSaleId, int units) {
        addToStock(product, pointOfSaleId, -units);
    }

    @Override
    public List<IndividualisedProductItem> getProductsOnStock(long pointOfSaleId) {
        PointOfSale pos = posCRUD.readPointOfSale(pointOfSaleId);
        List <StockItem> stockItems = siCRUD.readStockItemsForPointOfSale(pos);
        List <IndividualisedProductItem> individualProducts = new ArrayList<>();
        stockItems.stream().forEach(e -> individualProducts.add(e.getProduct()));
        return individualProducts;
    }

    @Override
    public List<IndividualisedProductItem> getAllProductsOnStock() {

        List <PointOfSale> pos = posCRUD.readAllPointsOfSale();
        List <IndividualisedProductItem> individualProducts = pos.stream()
                .map(e-> getProductsOnStock(e.getId()))
                .flatMap(list -> list.stream())
                .distinct()
                .collect(Collectors.toList());
//        List <StockItem> stockItems = siCRUD.readAllStockItems();
//        List <IndividualisedProductItem> individualProducts = (List<IndividualisedProductItem>) stockItems.stream().map(e -> e.getProduct()).distinct();
        return individualProducts;
    }

    @Override
    public int getUnitsOnStock(IndividualisedProductItem product, long pointOfSaleId) {
        PointOfSale pos = posCRUD.readPointOfSale(pointOfSaleId);
        StockItem stockItem = siCRUD.readStockItem(product,pos);
        return stockItem.getUnits();
    }

    @Override
    public int getTotalUnitsOnStock(IndividualisedProductItem product) {
        List <StockItem> stockItems = siCRUD.readStockItemsForProduct(product);
        return stockItems.stream().mapToInt(si -> si.getUnits()).sum();
//        int totalUnits = 0;
//        for (StockItem si : stockItems) {
//            totalUnits += si.getUnits();
//        }
    }

    @Override
    public List<Long> getPointsOfSale(IndividualisedProductItem product) {
        List <StockItem> stockItems = siCRUD.readStockItemsForProduct(product);
        List <Long> pos = new ArrayList<>();
        stockItems.stream().map(e -> e.getPos().getId()).forEach(e-> pos.add(e));
        return pos;
//        List <StockItem> stockItems = posCRUD.readPointOfSale(product);
//        return null;
    }

    @Override
    public List<StockItem> getCompleteStock() {
//        List <StockItem> stockItems = siCRUD.readAllStockItems();
        List <IndividualisedProductItem> allProducts = getAllProductsOnStock();
        List <StockItem> stockItems = (List<StockItem>) allProducts.stream().map(e -> siCRUD.readStockItemsForProduct(e));
        return stockItems;
//        return null;
    }
}
