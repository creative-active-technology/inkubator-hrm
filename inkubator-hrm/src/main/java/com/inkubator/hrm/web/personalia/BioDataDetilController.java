/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.personalia;

import com.inkubator.hrm.entity.BioData;
import com.inkubator.hrm.service.BioDataService;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesUtil;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Deni Husni FR
 */
@ManagedBean(name = "bioDataDetilController")
@ViewScoped
public class BioDataDetilController extends BaseController {
    
    @ManagedProperty(value = "#{bioDataService}")
    private BioDataService bioDataService;
    
    private BioData selectedBioData;
    
    public void setBioDataService(BioDataService bioDataService) {
        this.bioDataService = bioDataService;
    }
    
    @PostConstruct
    @Override
    public void initialization() {
        try {
            super.initialization();
            String userId = FacesUtil.getRequestParameter("execution");
            selectedBioData = bioDataService.getEntiyByPK(Long.parseLong(userId.substring(1)));
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
    }
    
    public String doDetail() {
        return "/protected/personalia/biodata_detail.htm?faces-redirect=true&execution=e" + selectedBioData.getId();
    }
    
    public BioData getSelectedBioData() {
        return selectedBioData;
    }
    
    public void setSelectedBioData(BioData selectedBioData) {
        this.selectedBioData = selectedBioData;
    }
    
    public void onDelete() {
        try {
            selectedBioData = this.bioDataService.getEntiyByPK(selectedBioData.getId());
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
    }
    
    public String doEdit() {
        return null;
    }
    
}
