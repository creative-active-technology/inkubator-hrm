/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.report;

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
@ManagedBean(name = "generatedPphReportViewController")
@ViewScoped
public class GeneratedPphReportViewController extends BaseController{
    private Long empDataId;
    
    @PostConstruct
    @Override
    public void initialization() {
        super.initialization();
        String param = FacesUtil.getRequestParameter("execution");
        empDataId = Long.parseLong(param.substring(1));
     
    }
    
    @PreDestroy
    private void cleanAndExit() {
        empDataId = null;
    }

    public Long getEmpDataId() {
        return empDataId;
    }

    public void setEmpDataId(Long empDataId) {
        this.empDataId = empDataId;
    }

    public String doBack() {
        return "/protected/report/report_pph_view.htm?faces-redirect=true&execution=e" + empDataId;
    }
}
