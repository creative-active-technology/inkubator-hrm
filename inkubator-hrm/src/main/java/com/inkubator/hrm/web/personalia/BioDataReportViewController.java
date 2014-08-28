/* To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.personalia;

import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesUtil;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Deni
 */
@ManagedBean(name = "bioDataReportViewController")
@ViewScoped
public class BioDataReportViewController extends BaseController{
    
    private Long bioDataId;
    
    
    @PostConstruct
    @Override
    public void initialization() {
     
        super.initialization();
        String param = FacesUtil.getRequestParameter("execution");
        bioDataId = Long.parseLong(param.substring(1));
    }
    
    @PreDestroy
    private void cleanAndExit() {
        bioDataId = null;
    }
    
    public Long getBioDataId() {
        return bioDataId;
    }

    public void setBioDataId(Long bioDataId) {
        this.bioDataId = bioDataId;
    }

    public String doBack() {
        return "/protected/personalia/biodata_detail.htm?faces-redirect=true&execution=e" + bioDataId;
    }
    
    
}
