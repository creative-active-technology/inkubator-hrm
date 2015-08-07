package com.inkubator.hrm.web.recruitment;

import java.io.IOException;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;

import org.hibernate.exception.ConstraintViolationException;
import org.primefaces.model.LazyDataModel;
import org.springframework.dao.DataIntegrityViolationException;

import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.entity.RecruitVacancyAdvertisement;
import com.inkubator.hrm.service.RecruitVacancyAdvertisementService;
import com.inkubator.hrm.web.lazymodel.VacancyAdvertisementLazyDataModel;
import com.inkubator.hrm.web.search.VacancyAdvertisementSearchParameter;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesUtil;
import com.inkubator.webcore.util.MessagesResourceUtil;

/**
 *
 * @author rizkykojek
 */
@ManagedBean(name = "vacancyAdvertisementViewController")
@ViewScoped
public class VacancyAdvertisementViewController extends BaseController {

    private VacancyAdvertisementSearchParameter parameter;
    private LazyDataModel<RecruitVacancyAdvertisement> lazyData;
    private RecruitVacancyAdvertisement selected;
    @ManagedProperty(value = "#{recruitVacancyAdvertisementService}")
    private RecruitVacancyAdvertisementService recruitVacancyAdvertisementService;

    @PostConstruct
    @Override
    public void initialization() {
        super.initialization();
        parameter = new VacancyAdvertisementSearchParameter();
    }

    @PreDestroy
    public void cleanAndExit() {
    	recruitVacancyAdvertisementService = null;
        parameter = null;
        lazyData = null;
        selected = null;
    }

	public void doSearch() {
        lazyData = null;
    }

    public String doDetail() {
        return "/protected/recruitment/vacancy_advertisement_detail.htm?faces-redirect=true&execution=e" + selected.getId();
    }
    
    public void doAdd() {
        try {
            ExternalContext red = FacesUtil.getExternalContext();
            red.redirect(red.getRequestContextPath() + "/flow-protected/vacancy_adverstisement");
        } catch (IOException ex) {
          LOGGER.error("Erorr", ex);
        }
    }

    public void doUpdate() {
    	try {
            ExternalContext red = FacesUtil.getExternalContext();
            red.redirect(red.getRequestContextPath() + "/flow-protected/vacancy_adverstisement?id=" + selected.getId());
        } catch (IOException ex) {
          LOGGER.error("Erorr", ex);
        }
    }

    public void doSelectEntity() {
        try {
            selected = this.recruitVacancyAdvertisementService.getEntiyByPK(selected.getId());
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
    }

    public void doDelete() {
        try {
        	recruitVacancyAdvertisementService.delete(selected);
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_INFO, "global.delete", "global.delete_successfully", FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());

        } catch (ConstraintViolationException | DataIntegrityViolationException ex) {
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_ERROR, "global.error", "error.delete_constraint", FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
            LOGGER.error("Error ", ex);
        } catch (Exception ex) {
            LOGGER.error("Error ", ex);
        }
    }

	public VacancyAdvertisementSearchParameter getParameter() {
		return parameter;
	}

	public void setParameter(VacancyAdvertisementSearchParameter parameter) {
		this.parameter = parameter;
	}

	public LazyDataModel<RecruitVacancyAdvertisement> getLazyData() {
		if (lazyData == null){
			lazyData = new VacancyAdvertisementLazyDataModel(parameter, recruitVacancyAdvertisementService);
		}
		return lazyData;
	}

	public void setLazyData(LazyDataModel<RecruitVacancyAdvertisement> lazyData) {
		this.lazyData = lazyData;
	}

	public RecruitVacancyAdvertisement getSelected() {
		return selected;
	}

	public void setSelected(RecruitVacancyAdvertisement selected) {
		this.selected = selected;
	}

	public RecruitVacancyAdvertisementService getRecruitVacancyAdvertisementService() {
		return recruitVacancyAdvertisementService;
	}

	public void setRecruitVacancyAdvertisementService(RecruitVacancyAdvertisementService recruitVacancyAdvertisementService) {
		this.recruitVacancyAdvertisementService = recruitVacancyAdvertisementService;
	}
    
}
