package com.inkubator.hrm.web.recruitment;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.LazyDataModel;

import com.inkubator.hrm.entity.RecruitVacancyAdvertisementDetail;
import com.inkubator.hrm.service.RecruitVacancyAdvertisementDetailService;
import com.inkubator.hrm.web.lazymodel.VacancyLazyDataModel;
import com.inkubator.hrm.web.search.VacancySearchParameter;
import com.inkubator.webcore.controller.BaseController;

/**
 *
 * @author rizkykojek
 */
@ManagedBean(name = "vacancyViewController")
@ViewScoped
public class VacancyViewController extends BaseController {

    private VacancySearchParameter parameter;
    private LazyDataModel<RecruitVacancyAdvertisementDetail> lazyData;
    private RecruitVacancyAdvertisementDetail selected;
    @ManagedProperty(value = "#{recruitVacancyAdvertisementDetailService}")
    private RecruitVacancyAdvertisementDetailService recruitVacancyAdvertisementDetailService;

    @PostConstruct
    @Override
    public void initialization() {
        super.initialization();
        parameter = new VacancySearchParameter();
    }

    @PreDestroy
    public void cleanAndExit() {
    	recruitVacancyAdvertisementDetailService = null;
        parameter = null;
        lazyData = null;
        selected = null;
    }

	public void doSearch() {
        lazyData = null;
    }

    public String doDetail() {
        return "/protected/recruitment/vacancy_detail.htm?faces-redirect=true&execution=e" + selected.getId();
    }

    public void doSelectEntity() {
        try {
            selected = this.recruitVacancyAdvertisementDetailService.getEntiyByPK(selected.getId());
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
    }

	public VacancySearchParameter getParameter() {
		return parameter;
	}

	public void setParameter(VacancySearchParameter parameter) {
		this.parameter = parameter;
	}

	public LazyDataModel<RecruitVacancyAdvertisementDetail> getLazyData() {
		if(lazyData == null){
			lazyData =  new VacancyLazyDataModel(parameter, recruitVacancyAdvertisementDetailService);
		}
		return lazyData;
	}

	public void setLazyData(LazyDataModel<RecruitVacancyAdvertisementDetail> lazyData) {
		this.lazyData = lazyData;
	}

	public RecruitVacancyAdvertisementDetail getSelected() {
		return selected;
	}

	public void setSelected(RecruitVacancyAdvertisementDetail selected) {
		this.selected = selected;
	}

	public RecruitVacancyAdvertisementDetailService getRecruitVacancyAdvertisementDetailService() {
		return recruitVacancyAdvertisementDetailService;
	}

	public void setRecruitVacancyAdvertisementDetailService(RecruitVacancyAdvertisementDetailService recruitVacancyAdvertisementDetailService) {
		this.recruitVacancyAdvertisementDetailService = recruitVacancyAdvertisementDetailService;
	}
    
}
