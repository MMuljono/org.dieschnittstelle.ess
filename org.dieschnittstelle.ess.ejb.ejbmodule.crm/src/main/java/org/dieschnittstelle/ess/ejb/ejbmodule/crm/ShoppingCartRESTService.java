package org.dieschnittstelle.ess.ejb.ejbmodule.crm;

import org.dieschnittstelle.ess.entities.crm.ShoppingCartItem;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * Created by master on 20.02.17.
 */
@Path("/shoppingcarts")
@Produces({MediaType.APPLICATION_JSON})
@Consumes({MediaType.APPLICATION_JSON})
public interface ShoppingCartRESTService {

    @POST
    public long createNewCart();

    @POST
    @Path("/{cartId}")
    public void addItem(@PathParam("cartId") long cartId, ShoppingCartItem product);

    @GET
    @Path("/{cartId}")
    public List<ShoppingCartItem> getItems(@PathParam("cartId") long cartId);

    @DELETE
    @Path("/{cartId}")
    public boolean deleteCart(@PathParam("cartId") long cartId);

}
