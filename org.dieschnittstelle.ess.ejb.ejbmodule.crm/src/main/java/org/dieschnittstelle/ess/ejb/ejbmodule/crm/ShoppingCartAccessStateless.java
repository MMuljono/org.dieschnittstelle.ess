package org.dieschnittstelle.ess.ejb.ejbmodule.crm;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class ShoppingCartAccessStateless implements ShoppingCartAccessLocal{

    @PersistenceContext(unitName="crm_PU")
    private EntityManager em;

    @Override
    public ShoppingCartStateful findShoppingCartById(long shoppingCartId) {
        return em.find(ShoppingCartStateful.class, shoppingCartId);
    }
}
