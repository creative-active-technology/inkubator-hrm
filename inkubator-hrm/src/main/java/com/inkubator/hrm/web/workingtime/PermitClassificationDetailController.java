/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.workingtime;

import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.entity.PermitClassification;
import com.inkubator.hrm.service.PermitClassificationService;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesUtil;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;

/**
 *
 * @author Taufik Hidayat
 */
@ManagedBean(name = "permitClassificationDetailController")
@ViewScoped
public class PermitClassificationDetailController extends BaseController {

    private PermitClassification selectedPermitClassification;
    @ManagedProperty(value = "#{permitClassificationService}")
    private PermitClassificationService permitClassificationService;
    private Boolean hidden;

    public Boolean getHidden() {
        return hidden;
    }

    public void setHidden(Boolean hidden) {
        this.hidden = hidden;
    }
    
    

    public PermitClassification getSelectedPermitClassification() {
        return selectedPermitClassification;
    }

    public void setSelectedPermitClassification(PermitClassification selectedPermitClassification) {
        this.selectedPermitClassification = selectedPermitClassification;
    }

    public void setPermitClassificationService(PermitClassificationService permitClassificationService) {
        this.permitClassificationService = permitClassificationService;
    }

    
    @PostConstruct
    @Override
    public void initialization() {
        try {
            super.initialization();
            hidden = Boolean.FALSE;
            String userId = FacesUtil.getRequestParameter("execution");
            System.out.println("permitClassification id nya : " + userId);
            selectedPermitClassification = permitClassificationService.getEntityByPKWithDetail(Long.parseLong(userId.substring(1)));
            if(selectedPermitClassification.getAvailibility().equals(HRMConstant.AVALILIBILITY_PER_DATE)){
                hidden = Boolean.TRUE;
            }
                        
        } catch (Exception ex) {
            LOGGER.error("Error", ex);

        }
    }

    public String doBack() {
        return "/protected/working_time/permit_classification_view.htm?faces-redirect=true";
    }

    public String doEdit() {
        return "/protected/working_time/permit_classification_form.htm?faces-redirect=true&execution=e" + selectedPermitClassification.getId();
    }

    @PreDestroy
    public void cleanAndExit() {
        selectedPermitClassification = null;
        permitClassificationService = null;
    }

}