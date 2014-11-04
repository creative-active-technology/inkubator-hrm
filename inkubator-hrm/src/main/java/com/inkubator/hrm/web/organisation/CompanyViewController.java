package com.inkubator.hrm.web.organisation;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.hibernate.exception.ConstraintViolationException;
import org.primefaces.model.LazyDataModel;
import org.springframework.dao.DataIntegrityViolationException;

import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.entity.Company;
import com.inkubator.hrm.service.CompanyService;
import com.inkubator.hrm.web.lazymodel.CompanyLazyDataModel;
import com.inkubator.hrm.web.search.CompanySearchParameter;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesUtil;
import com.inkubator.webcore.util.MessagesResourceUtil;

/**
 *
 * @author rizkykojek
 */
@ManagedBean(name = "companyViewController")
@ViewScoped
public class CompanyViewController extends BaseController {

    private CompanySearchParameter searchParameter;
    private LazyDataModel<Company> lazyDataCompany;
    private Company selectedCompany;
    @ManagedProperty(value = "#{companyService}")
    private CompanyService companyService;

    @PostConstruct
    @Override
    public void initialization() {
        super.initialization();
        searchParameter = new CompanySearchParameter();
    }

    @PreDestroy
    public void cleanAndExit() {
        companyService = null;
        searchParameter = null;
        lazyDataCompany = null;
        selectedCompany = null;
    }

    public LazyDataModel<Company> getLazyDataCompany() {
    	if (lazyDataCompany == null) {
            lazyDataCompany = new CompanyLazyDataModel(searchParameter, companyService);
        }
        return lazyDataCompany;
	}

	public void setLazyDataCompany(LazyDataModel<Company> lazyDataCompany) {
		this.lazyDataCompany = lazyDataCompany;
	}

	public CompanySearchParameter getSearchParameter() {
		return searchParameter;
	}

	public void setSearchParameter(CompanySearchParameter searchParameter) {
		this.searchParameter = searchParameter;
	}

	public Company getSelectedCompany() {
		return selectedCompany;
	}

	public void setSelectedCompany(Company selectedCompany) {
		this.selectedCompany = selectedCompany;
	}

	public CompanyService getCompanyService() {
		return companyService;
	}

	public void setCompanyService(CompanyService companyService) {
		this.companyService = companyService;
	}

	public void doSearch() {
        lazyDataCompany = null;
    }

    public String doDetail() {
        return "/protected/organisation/company_detail.htm?faces-redirect=true&execution=e" + selectedCompany.getId();
    }

    public void doSelectEntity() {
        try {
            selectedCompany = this.companyService.getEntiyByPK(selectedCompany.getId());
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
    }

    public void doDelete() {
        try {
            companyService.delete(selectedCompany);
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_INFO, "global.delete", "global.delete_successfully", FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());

        } catch (ConstraintViolationException | DataIntegrityViolationException ex) {
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_ERROR, "global.error", "error.delete_constraint", FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
            LOGGER.error("Error when doDelete Leave", ex);
        } catch (Exception ex) {
            LOGGER.error("Error when doDelete Leave", ex);
        }
    }

    public String doAdd() {
        return "/protected/organisation/company_form.htm?faces-redirect=true";
    }

    public String doUpdate() {
        return "/protected/organisation/company_form.htm?faces-redirect=true&execution=e" + selectedCompany.getId();
    }
}
