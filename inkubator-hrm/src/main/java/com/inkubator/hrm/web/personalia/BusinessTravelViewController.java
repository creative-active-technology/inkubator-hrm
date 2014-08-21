package com.inkubator.hrm.web.personalia;

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
import com.inkubator.hrm.entity.BusinessTravel;
import com.inkubator.hrm.service.BusinessTravelService;
import com.inkubator.hrm.web.lazymodel.BusinessTravelLazyDataModel;
import com.inkubator.hrm.web.search.BusinessTravelSearchParameter;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesUtil;
import com.inkubator.webcore.util.MessagesResourceUtil;

/**
 *
 * @author rizkykojek
 */
@ManagedBean(name = "businessTravelViewController")
@ViewScoped
public class BusinessTravelViewController extends BaseController {

    private BusinessTravelSearchParameter searchParameter;
    private LazyDataModel<BusinessTravel> lazyDataBusinessTravel;
    private BusinessTravel selectedBusinessTravel;
    @ManagedProperty(value = "#{businessTravelService}")
    private BusinessTravelService businessTravelService;

    @PostConstruct
    @Override
    public void initialization() {
        super.initialization();
        searchParameter = new BusinessTravelSearchParameter();
    }

    @PreDestroy
    public void cleanAndExit() {
        businessTravelService = null;
        searchParameter = null;
        lazyDataBusinessTravel = null;
        selectedBusinessTravel = null;
    }    

	public LazyDataModel<BusinessTravel> getLazyDataBusinessTravel() {
		if(lazyDataBusinessTravel == null){
			lazyDataBusinessTravel = new BusinessTravelLazyDataModel(searchParameter, businessTravelService);
		}
		return lazyDataBusinessTravel;
	}

	public void setLazyDataBusinessTravel(LazyDataModel<BusinessTravel> lazyDataBusinessTravel) {
		this.lazyDataBusinessTravel = lazyDataBusinessTravel;
	}

	public BusinessTravelSearchParameter getSearchParameter() {
		return searchParameter;
	}

	public void setSearchParameter(BusinessTravelSearchParameter searchParameter) {
		this.searchParameter = searchParameter;
	}

	public BusinessTravel getSelectedBusinessTravel() {
		return selectedBusinessTravel;
	}

	public void setSelectedBusinessTravel(BusinessTravel selectedBusinessTravel) {
		this.selectedBusinessTravel = selectedBusinessTravel;
	}

	public void setBusinessTravelService(BusinessTravelService businessTravelService) {
		this.businessTravelService = businessTravelService;
	}

	public void doSearch() {
        lazyDataBusinessTravel = null;
    }

    public String doDetail() {
        return "/protected/working_time/leave_detail.htm?faces-redirect=true&execution=e" + selectedBusinessTravel.getId();
    }

    public void doSelectEntity() {
        try {
            selectedBusinessTravel = this.businessTravelService.getEntiyByPK(selectedBusinessTravel.getId());
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
    }

    public void doDelete() {
        try {
            businessTravelService.delete(selectedBusinessTravel);
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_INFO, "global.delete", "global.delete_successfully", FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());

        } catch (ConstraintViolationException | DataIntegrityViolationException ex) {
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_ERROR, "global.error", "error.delete_constraint", FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
            LOGGER.error("Error when doDelete BusinessTravel", ex);
        } catch (Exception ex) {
            LOGGER.error("Error when doDelete BusinessTravel", ex);
        }
    }

    public String doAdd() {
        return "/protected/working_time/leave_form.htm?faces-redirect=true";
    }

    public String doUpdate() {
        return "/protected/working_time/leave_form.htm?faces-redirect=true&execution=e" + selectedBusinessTravel.getId();
    }
}
