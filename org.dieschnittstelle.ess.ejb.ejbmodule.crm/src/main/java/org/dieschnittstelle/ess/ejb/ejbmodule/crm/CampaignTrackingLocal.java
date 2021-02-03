package org.dieschnittstelle.ess.ejb.ejbmodule.crm;

import org.dieschnittstelle.ess.entities.crm.AbstractTouchpoint;
import org.dieschnittstelle.ess.entities.crm.CampaignExecution;

import javax.ejb.Local;
import java.util.List;

@Local
public interface CampaignTrackingLocal {
    public void addCampaignExecution(CampaignExecution campaign);

    public int existsValidCampaignExecutionAtTouchpoint(long erpProductId, AbstractTouchpoint tp);

    public void purchaseCampaignAtTouchpoint(long erpProductId, AbstractTouchpoint tp, int units);

    public List<CampaignExecution> getAllCampaignExecutions();
}
