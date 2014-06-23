/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.organisation;

import com.inkubator.hrm.entity.CostCenter;
import com.inkubator.hrm.service.CostCenterService;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesUtil;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author deniarianto
 */
@ManagedBean(name = "costCenterDetailController")
@ViewScoped
public class CostCenterDetailController extends BaseController{
     private CostCenter selectedCostCenter;
    @ManagedProperty(value = "#{costCenterService}")
    private CostCenterService costCenterService;

    public CostCenter getSelectedCostCenter() {
        return selectedCostCenter;
    }

    public void setSelectedCostCenter(CostCenter selectedCostCenter) {
        this.selectedCostCenter = selectedCostCenter;
    }

    public CostCenterService getCostCenterService() {
        return costCenterService;
    }

    public void setCostCenterService(CostCenterService costCenterService) {
        this.costCenterService = costCenterService;
    }
    
    @PostConstruct
    @Override
    public void initialization() {
        try {
            super.initialization();
            String userId = FacesUtil.getRequestParameter("execution");
            selectedCostCenter = costCenterService.getCostCenterByIdWithDetail(Long.parseLong(userId));
        } catch (Exception ex) {
            LOGGER.error("Error", ex);

        }
    }
    
    @PreDestroy
    public void cleanAndExit() {
        selectedCostCenter = null;
        costCenterService = null;
    }
}
