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
import com.inkubator.hrm.entity.CompanyPolicy;
import com.inkubator.hrm.service.CompanyPolicyService;
import com.inkubator.hrm.web.lazymodel.CompanyPolicyLazyDataModel;
import com.inkubator.hrm.web.search.CompanyPolicySearchParameter;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesUtil;
import com.inkubator.webcore.util.MessagesResourceUtil;

/**
 *
 * @author rizkykojek
 */
@ManagedBean(name = "companyPolicyViewController")
@ViewScoped
public class CompanyPolicyViewController extends BaseController {

    private CompanyPolicySearchParameter searchParameter;
    private LazyDataModel<CompanyPolicy> lazyDataModel;
    private CompanyPolicy selectedCompanyPolicy;
    @ManagedProperty(value = "#{companyPolicyService}")
    private CompanyPolicyService companyPolicyService;

    @PostConstruct
    @Override
    public void initialization() {
        super.initialization();
        searchParameter = new CompanyPolicySearchParameter();
    }

    @PreDestroy
    public void cleanAndExit() {
        companyPolicyService = null;
        searchParameter = null;
        lazyDataModel = null;
        selectedCompanyPolicy = null;
    }

    public LazyDataModel<CompanyPolicy> getLazyDataModel() {
    	if (lazyDataModel == null) {
            lazyDataModel = new CompanyPolicyLazyDataModel(searchParameter, companyPolicyService);
        }
        return lazyDataModel;
	}

	public void setLazyDataModel(LazyDataModel<CompanyPolicy> lazyDataModel) {
		this.lazyDataModel = lazyDataModel;
	}

	public CompanyPolicySearchParameter getSearchParameter() {
		return searchParameter;
	}

	public void setSearchParameter(CompanyPolicySearchParameter searchParameter) {
		this.searchParameter = searchParameter;
	}

	public CompanyPolicy getSelectedCompanyPolicy() {
		return selectedCompanyPolicy;
	}

	public void setSelectedCompanyPolicy(CompanyPolicy selectedCompanyPolicy) {
		this.selectedCompanyPolicy = selectedCompanyPolicy;
	}

	public void setCompanyPolicyService(CompanyPolicyService companyPolicyService) {
		this.companyPolicyService = companyPolicyService;
	}

	public void doSearch() {
        lazyDataModel = null;
    }

    public String doDetail() {
        return "/protected/organisation/company_policy_detail.htm?faces-redirect=true&execution=e" + selectedCompanyPolicy.getId();
    }

    public void doSelectEntity() {
        try {
        	selectedCompanyPolicy = this.companyPolicyService.getEntityByPkWithDetail(selectedCompanyPolicy.getId());
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
    }

    public void doDelete() {
        try {
            companyPolicyService.delete(selectedCompanyPolicy);
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_INFO, "global.delete", "global.delete_successfully", FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());

        } catch (ConstraintViolationException | DataIntegrityViolationException ex) {
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_ERROR, "global.error", "error.delete_constraint", FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());            
        } catch (Exception ex) {
            LOGGER.error("Error when doDelete Leave", ex);
        }
    }

    public String doAdd() {
        return "/protected/organisation/company_policy_form.htm?faces-redirect=true";
    }

    public String doUpdate() {
        return "/protected/organisation/company_policy_form.htm?faces-redirect=true&execution=e" + selectedCompanyPolicy.getId();
    }
}
