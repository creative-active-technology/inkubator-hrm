package com.inkubator.hrm.web.recruitment;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.LazyDataModel;

import com.inkubator.hrm.entity.RecruitApplicant;
import com.inkubator.hrm.service.RecruitApplicantService;
import com.inkubator.hrm.web.lazymodel.SelectionApplicantRealizationLazyDataModel;
import com.inkubator.hrm.web.search.SelectionApplicantRealizationSearchParameter;
import com.inkubator.webcore.controller.BaseController;

/**
 *
 * @author rizkykojek
 */
@ManagedBean(name = "selectionApplicantRealizationViewController")
@ViewScoped
public class SelectionApplicantRealizationViewController extends BaseController {

    private SelectionApplicantRealizationSearchParameter parameter;
    private LazyDataModel<RecruitApplicant> lazyData;
    private RecruitApplicant selected;
    @ManagedProperty(value = "#{recruitApplicantService}")
    private RecruitApplicantService recruitApplicantService;

    @PostConstruct
    @Override
    public void initialization() {
        super.initialization();
        parameter = new SelectionApplicantRealizationSearchParameter();
    }

    @PreDestroy
    public void cleanAndExit() {
    	recruitApplicantService = null;
        parameter = null;
        lazyData = null;
        selected = null;
    }

	public void doSearch() {
        lazyData = null;
    }
    
    public String doEvaluate() {
    	return "/protected/recruitment/selection_applicant_realization_form.htm?faces-redirect=true&execution=e" + selected.getId();
    }

    public void doSelectEntity() {
        try {
            selected = this.recruitApplicantService.getEntityByPkWithDetail(selected.getId());
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
    }

	public SelectionApplicantRealizationSearchParameter getParameter() {
		return parameter;
	}

	public void setParameter(SelectionApplicantRealizationSearchParameter parameter) {
		this.parameter = parameter;
	}

	public LazyDataModel<RecruitApplicant> getLazyData() {
		if(lazyData == null){
			lazyData = new SelectionApplicantRealizationLazyDataModel(parameter, recruitApplicantService);
		}
		return lazyData;
	}

	public void setLazyData(LazyDataModel<RecruitApplicant> lazyData) {
		this.lazyData = lazyData;
	}

	public RecruitApplicant getSelected() {
		return selected;
	}

	public void setSelected(RecruitApplicant selected) {
		this.selected = selected;
	}

	public RecruitApplicantService getRecruitApplicantService() {
		return recruitApplicantService;
	}

	public void setRecruitApplicantService(RecruitApplicantService recruitApplicantService) {
		this.recruitApplicantService = recruitApplicantService;
	}

}
