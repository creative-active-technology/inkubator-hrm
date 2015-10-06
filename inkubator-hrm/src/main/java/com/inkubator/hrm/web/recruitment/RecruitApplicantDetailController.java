package com.inkubator.hrm.web.recruitment;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import com.inkubator.hrm.entity.OrgTypeOfSpecList;
import com.inkubator.hrm.entity.RecruitApplicant;
import com.inkubator.hrm.entity.RecruitApplicantSpecList;
import com.inkubator.hrm.service.RecruitApplicantService;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesUtil;

import ch.lambdaj.Lambda;

/**
 *
 * @author rizkykojek
 */
@ManagedBean(name = "recruitApplicantDetailController")
@ViewScoped
public class RecruitApplicantDetailController extends BaseController {

	private RecruitApplicant selectedRecruitApplicant;
	private List<OrgTypeOfSpecList> listJobsSpecification;
	@ManagedProperty(value = "#{recruitApplicantService}")
    private RecruitApplicantService recruitApplicantService;
	
	@PostConstruct
    @Override
    public void initialization() {
		super.initialization();
		try {
	        String id = FacesUtil.getRequestParameter("execution");
	        selectedRecruitApplicant = recruitApplicantService.getEntityByPkWithDetail(Long.parseLong(id.substring(1)));
	        listJobsSpecification = Lambda.extract(selectedRecruitApplicant.getRecruitApplicantSpecLists(), Lambda.on(RecruitApplicantSpecList.class).getOrgTypeOfSpecList());
	        listJobsSpecification = Lambda.sort(listJobsSpecification, Lambda.on(OrgTypeOfSpecList.class).getOrgTypeOfSpec().getName());
		} catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
	}
	
	@PreDestroy
    public void cleanAndExit() {
		selectedRecruitApplicant = null;
		listJobsSpecification = null;
		recruitApplicantService = null;
	}

	public String doBack() {
        return "/protected/recruitment/recruit_applicant_view.htm?faces-redirect=true";
    }

    public String doUpdate() {
        return "/protected/recruitment/recruit_applicant_form.htm?faces-redirect=true&execution=e" + selectedRecruitApplicant.getId();
    }

	public RecruitApplicant getSelectedRecruitApplicant() {
		return selectedRecruitApplicant;
	}

	public void setSelectedRecruitApplicant(RecruitApplicant selectedRecruitApplicant) {
		this.selectedRecruitApplicant = selectedRecruitApplicant;
	}

	public List<OrgTypeOfSpecList> getListJobsSpecification() {
		return listJobsSpecification;
	}

	public void setListJobsSpecification(List<OrgTypeOfSpecList> listJobsSpecification) {
		this.listJobsSpecification = listJobsSpecification;
	}

	public RecruitApplicantService getRecruitApplicantService() {
		return recruitApplicantService;
	}

	public void setRecruitApplicantService(RecruitApplicantService recruitApplicantService) {
		this.recruitApplicantService = recruitApplicantService;
	}    
    
}
