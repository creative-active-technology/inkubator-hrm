/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.employee;

import com.inkubator.hrm.entity.BioData;
import com.inkubator.hrm.service.BioDataService;
import com.inkubator.webcore.controller.BaseController;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import org.primefaces.context.RequestContext;

/**
 *
 * @author Deni Husni FR
 */
@ManagedBean(name = "empBioSearchController")
@ViewScoped
public class EmpBioSearchController extends BaseController {

    @ManagedProperty(value = "#{bioDataService}")
    private BioDataService bioDataService;
    private String biodataName;
    private List<BioData> biodataToShow = new ArrayList();

    @PostConstruct
    @Override
    public void initialization() {
        super.initialization();

    }

    public void doSearch() {
        try {
            biodataToShow= new ArrayList();
            
            biodataToShow = bioDataService.getByName(biodataName);
        } catch (Exception ex) {
           LOGGER.error("Error", ex);
        }
    }

    public String getBiodataName() {
        return biodataName;
    }

    public void setBiodataName(String biodataName) {
        this.biodataName = biodataName;
    }

    public List<BioData> getBiodataToShow() {
        return biodataToShow;
    }

    public void setBiodataToShow(List<BioData> biodataToShow) {
        this.biodataToShow = biodataToShow;
    }

    public void setBioDataService(BioDataService bioDataService) {
        this.bioDataService = bioDataService;
    }
    
    
    public void doSelect(BioData bioData){
        RequestContext.getCurrentInstance().closeDialog(bioData);
    }
    
     @PreDestroy
    public void cleanAndExit() {
        bioDataService=null;
        biodataName=null;
        biodataToShow=null;
        
    }
}
