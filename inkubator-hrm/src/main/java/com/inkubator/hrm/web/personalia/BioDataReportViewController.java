/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.personalia;

import com.inkubator.hrm.entity.BioData;
import com.inkubator.hrm.service.BioDataService;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesUtil;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Deni
 */
@ManagedBean(name = "bioDataReportViewController")
@ViewScoped
public class BioDataReportViewController extends BaseController{
    
    private Long bioDataId;
    private Map<String, Object> params;
    private BioData selectedBiodata;
    @ManagedProperty(value = "#{bioDataService}")
    private BioDataService biodataService;
    
    
    @PostConstruct
    @Override
    public void initialization() {
        System.out.println("init");
        super.initialization();
        String param = FacesUtil.getRequestParameter("execution");
        bioDataId = Long.parseLong(param.substring(1));

        System.out.println(bioDataId+"BIODATA");
    }
    
    @PreDestroy
    private void cleanAndExit() {
        selectedBiodata = null;
        biodataService = null;
    }
    
    public Long getBioDataId() {
        return bioDataId;
    }

    public void setBioDataId(Long bioDataId) {
        this.bioDataId = bioDataId;
    }

    public Map<String, Object> getParams() {
        return params;
    }

    public void setParams(Map<String, Object> params) {
        this.params = params;
    }

    public BioData getSelectedBiodata() {
        return selectedBiodata;
    }

    public void setSelectedBiodata(BioData selectedBiodata) {
        this.selectedBiodata = selectedBiodata;
    }

    public BioDataService getBiodataService() {
        return biodataService;
    }

    public void setBiodataService(BioDataService biodataService) {
        this.biodataService = biodataService;
    }
    
    
}
