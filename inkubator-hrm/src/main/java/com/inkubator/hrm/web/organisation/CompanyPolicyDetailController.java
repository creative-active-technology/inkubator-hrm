package com.inkubator.hrm.web.organisation;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import ch.lambdaj.Lambda;

import com.inkubator.hrm.entity.CompanyPolicy;
import com.inkubator.hrm.entity.CompanyPolicyJabatan;
import com.inkubator.hrm.entity.GolonganJabatan;
import com.inkubator.hrm.service.CompanyPolicyService;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesUtil;

/**
 *
 * @author rizkykojek
 */
@ManagedBean(name = "companyPolicyDetailController")
@ViewScoped
public class CompanyPolicyDetailController extends BaseController {

	private CompanyPolicy selectedCompanyPolicy;
	private List<GolonganJabatan> golonganJabatans;
	@ManagedProperty(value = "#{companyPolicyService}")
    private CompanyPolicyService companyPolicyService;
	
	@PostConstruct
    @Override
    public void initialization() {
		super.initialization();
		try {
	        String id = FacesUtil.getRequestParameter("execution");
	        selectedCompanyPolicy = companyPolicyService.getEntityByPkWithDetail(Long.parseLong(id.substring(1)));
	        golonganJabatans = Lambda.extract(selectedCompanyPolicy.getCompanyPolicyJabatans(), Lambda.on(CompanyPolicyJabatan.class).getGolonganJabatan());
		} catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
	}
	
	@PreDestroy
    public void cleanAndExit() {
		selectedCompanyPolicy = null;
		golonganJabatans = null;
		companyPolicyService = null;
	}
	
	public CompanyPolicy getSelectedCompanyPolicy() {
		return selectedCompanyPolicy;
	}

	public void setSelectedCompanyPolicy(CompanyPolicy selectedCompanyPolicy) {
		this.selectedCompanyPolicy = selectedCompanyPolicy;
	}

	public List<GolonganJabatan> getGolonganJabatans() {
		return golonganJabatans;
	}

	public void setGolonganJabatans(List<GolonganJabatan> golonganJabatans) {
		this.golonganJabatans = golonganJabatans;
	}

	public CompanyPolicyService getCompanyPolicyService() {
		return companyPolicyService;
	}

	public void setCompanyPolicyService(CompanyPolicyService companyPolicyService) {
		this.companyPolicyService = companyPolicyService;
	}

	public String doBack() {
        return "/protected/organisation/company_policy_view.htm?faces-redirect=true";
    }

    public String doUpdate() {
        return "/protected/organisation/company_policy_form.htm?faces-redirect=true&execution=e" + selectedCompanyPolicy.getId();
    }
}
