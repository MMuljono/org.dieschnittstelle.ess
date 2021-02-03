package org.dieschnittstelle.ess.ejb.ejbmodule.crm.shopping;


import org.apache.logging.log4j.Logger;
import org.dieschnittstelle.ess.ejb.ejbmodule.crm.*;
import org.dieschnittstelle.ess.ejb.ejbmodule.crm.crud.CustomerCRUDLocal;
import org.dieschnittstelle.ess.ejb.ejbmodule.crm.crud.TouchpointCRUDLocal;
import org.dieschnittstelle.ess.ejb.ejbmodule.erp.StockSystemLocal;
import org.dieschnittstelle.ess.ejb.ejbmodule.erp.crud.ProductCRUDLocal;
import org.dieschnittstelle.ess.ejb.ejbmodule.erp.crud.ProductCRUDRemote;
import org.dieschnittstelle.ess.entities.crm.AbstractTouchpoint;
import org.dieschnittstelle.ess.entities.crm.Customer;
import org.dieschnittstelle.ess.entities.crm.CustomerTransaction;
import org.dieschnittstelle.ess.entities.crm.ShoppingCartItem;
import org.dieschnittstelle.ess.entities.erp.AbstractProduct;
import org.dieschnittstelle.ess.entities.erp.Campaign;
import org.dieschnittstelle.ess.entities.erp.IndividualisedProductItem;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import java.util.List;
import java.util.stream.Collectors;

import static org.dieschnittstelle.ess.utils.Utils.show;

@Stateless
public class PurchaseShoppingCartServiceStateless implements PurchaseShoppingCartService {

    protected static Logger logger = org.apache.logging.log4j.LogManager.getLogger(PurchaseShoppingCartServiceStateless.class);

    /*
     * the three beans that are used
     */
    private ShoppingCartRemote shoppingCart;

    @EJB
    private CustomerTrackingLocal customerTracking;

    @EJB
    private CampaignTrackingLocal campaignTracking;

    @EJB
    private CustomerCRUDLocal customerCRUD;

    @EJB
    private TouchpointCRUDLocal touchpointCRUD;

    @EJB
    private ShoppingCartAccessLocal shoppingCartAccess;

    @EJB
    private ShoppingCartRESTService shoppingCartRESTService;

    @EJB
    private ProductCRUDLocal productCRUD;

    @EJB
    private StockSystemLocal stockSystem;

    /**
     * the customer
     */
    private Customer customer;

    /**
     * the touchpoint
     */
    private AbstractTouchpoint touchpoint;

    public void setTouchpoint(AbstractTouchpoint touchpoint) {
        this.touchpoint = touchpoint;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public void addProduct(AbstractProduct product, int units) {
        this.shoppingCart.addItem(new ShoppingCartItem(product.getId(), units, product instanceof Campaign));
    }

    /*
     * verify whether campaigns are still valid
     */
    public void verifyCampaigns() throws ShoppingException {
        if (this.customer == null || this.touchpoint == null) {
            throw new RuntimeException("cannot verify campaigns! No touchpoint has been set!");
        }

        for (ShoppingCartItem item : this.shoppingCart.getItems()) {
            if (item.isCampaign()) {
                int availableCampaigns = this.campaignTracking.existsValidCampaignExecutionAtTouchpoint(
                        item.getErpProductId(), this.touchpoint);
                logger.info("got available campaigns for product " + item.getErpProductId() + ": "
                        + availableCampaigns);
                // we check whether we have sufficient campaign items available
                if (availableCampaigns < item.getUnits()) {
                    throw new ShoppingException("verifyCampaigns() failed for productBundle " + item
                            + " at touchpoint " + this.touchpoint + "! Need " + item.getUnits()
                            + " instances of campaign, but only got: " + availableCampaigns);
                }
            }
        }
    }

    public void purchase()  throws ShoppingException {
        logger.info("purchase()");

        if (this.customer == null || this.touchpoint == null) {
            throw new RuntimeException(
                    "cannot commit shopping session! Either customer or touchpoint has not been set: " + this.customer
                            + "/" + this.touchpoint);
        }

        // verify the campaigns
        verifyCampaigns();

        // remove the products from stock
        checkAndRemoveProductsFromStock();

        // then we add a new customer transaction for the current purchase
        // TODO PAT1: once this functionality has been moved to the server side components, make sure
        //  that the ShoppingCartItem instances will be cloned/copied by constructing new items before
        //  using them for creating the CustomerTransaction object.
        List<ShoppingCartItem> products = this.shoppingCart.getItems()
                .stream()
                .map(item-> {
                    ShoppingCartItem clone = new ShoppingCartItem(item.getErpProductId(), item.getUnits(), item.isCampaign());
                    clone.setId(0);
                    return clone;
                })
                .collect(Collectors.toList());
        CustomerTransaction transaction = new CustomerTransaction(this.customer, this.touchpoint, products);
        transaction.setCompleted(true);
        customerTracking.createTransaction(transaction);

        logger.info("purchase(): done.\n");
    }

    /*
     * TODO PAT2: complete the method implementation in your server-side component for shopping / purchasing
     */
    private void checkAndRemoveProductsFromStock() {
        logger.info("checkAndRemoveProductsFromStock");

        for (ShoppingCartItem item : this.shoppingCart.getItems()) {

            // TODO: ermitteln Sie das AbstractProduct für das gegebene ShoppingCartItem. Nutzen Sie dafür dessen erpProductId und die ProductCRUD EJB
            AbstractProduct product = this.productCRUD.readProduct(item.getErpProductId());
            if (item.isCampaign()) {
                logger.info("am i being here?");
                this.campaignTracking.purchaseCampaignAtTouchpoint(item.getErpProductId(), this.touchpoint,
                        item.getUnits());
                // TODO: wenn Sie eine Kampagne haben, muessen Sie hier
                // 1) ueber die ProductBundle Objekte auf dem Campaign Objekt iterieren, und
                // 2) fuer jedes ProductBundle das betreffende Produkt in der auf dem Bundle angegebenen Anzahl, multipliziert mit dem Wert von
                // item.getUnits() aus dem Warenkorb,
                // - hinsichtlich Verfuegbarkeit ueberpruefen, und
                // - falls verfuegbar, aus dem Warenlager entfernen - nutzen Sie dafür die StockSystem EJB
                // (Anm.: item.getUnits() gibt Ihnen Auskunft darüber, wie oft ein Produkt, im vorliegenden Fall eine Kampagne, im
                // Warenkorb liegt)
                Campaign campaignbundle = (Campaign) productCRUD.readProduct(item.getErpProductId());
                campaignbundle.getBundles().stream().forEach(e->{
                    int totalunits = item.getUnits() * e.getUnits();
                    int totalStocks =  stockSystem.getTotalUnitsOnStock((IndividualisedProductItem) e.getProduct());
                    if ( totalunits < totalStocks) {
                        stockSystem.removeFromStock((IndividualisedProductItem) e.getProduct(),touchpoint.getId(),totalunits);
                    }
                });
            } else {
                logger.info("or am i being here?");
                // TODO: andernfalls (wenn keine Kampagne vorliegt) muessen Sie
                // 1) das Produkt in der in item.getUnits() angegebenen Anzahl hinsichtlich Verfuegbarkeit ueberpruefen und
                // 2) das Produkt, falls verfuegbar, in der entsprechenden Anzahl aus dem Warenlager entfernen
                int total = stockSystem.getTotalUnitsOnStock((IndividualisedProductItem) product);
                if (item.getUnits() < total) {
                    stockSystem.removeFromStock((IndividualisedProductItem) product,touchpoint.getId(),item.getUnits());
                }
            }

        }
    }

    @Override
    public void purchaseCartAtTouchpointForCustomer(long shoppingCartId, long touchpointId, long customerId) throws ShoppingException {
        show("purchaseCartAtTouchpointForCustomer()");
        this.customer = this.customerCRUD.readCustomer(customerId);
        this.touchpoint = this.touchpointCRUD.readTouchpoint(touchpointId);
        show("customer: "+ this.customer);
        show("touchpoint: "+ this.touchpoint);
//        this.shoppingCart = new ShoppingCartStateful();
//        this.shoppingCartRESTService.getItems(shoppingCartId)
//                .stream()
//                .map(item->{
//                    ShoppingCartItem cloned = new ShoppingCartItem(item.getErpProductId(), item.getUnits(), item.isCampaign());
//                    cloned.setId(0);
//                    return cloned;
//                }).forEach(cloned -> {
//                    this.shoppingCart.addItem(cloned);
//        });
        this.shoppingCart = this.shoppingCartAccess.findShoppingCartById(shoppingCartId);
        show("shoppingCart: "+ this.shoppingCart);
        purchase();
    }
}
