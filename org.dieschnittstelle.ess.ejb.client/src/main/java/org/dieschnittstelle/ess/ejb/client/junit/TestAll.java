package org.dieschnittstelle.ess.ejb.client.junit;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ TestProductCRUD.class, TestStockSystem.class, TestShoppingSession.class })
public class TestAll {

}
