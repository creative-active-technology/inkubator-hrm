/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.organisation;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import com.inkubator.hrm.entity.Company;
import com.inkubator.hrm.service.CompanyService;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesUtil;

/**
 *
 * @author rizkykojek
 */
@ManagedBean(name = "companyDetailController")
@ViewScoped
public class CompanyDetailController extends BaseController {

    private Company selectedCompany;
    @ManagedProperty(value = "#{companyService}")
    private CompanyService companyService;

    @PostConstruct
    @Override
    public void initialization() {
        try {
            super.initialization();
            String id = FacesUtil.getRequestParameter("execution");
            selectedCompany = companyService.getEntityByPKWithDetail(Long.parseLong(id.substring(1)));           
        } catch (Exception ex) {
            LOGGER.error("Error", ex);

        }
    }

    @PreDestroy
    public void cleanAndExit() {
        selectedCompany = null;
        companyService = null;
    }    

	public Company getSelectedCompany() {
		return selectedCompany;
	}

	public void setSelectedCompany(Company selectedCompany) {
		this.selectedCompany = selectedCompany;
	}

	public void setCompanyService(CompanyService companyService) {
		this.companyService = companyService;
	}

	public String doBack() {
        return "/protected/organisation/company_view.htm?faces-redirect=true";
    }

    public String doUpdate() {
        return "/protected/organisation/company_form.htm?faces-redirect=true&execution=e" + selectedCompany.getId();
    }

}
