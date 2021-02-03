package org.dieschnittstelle.ess.ejb.ejbmodule.crm;

import javax.ejb.Local;

@Local
public interface ShoppingCartAccessLocal {
    public ShoppingCartStateful findShoppingCartById(long shoppingCartId);
}
