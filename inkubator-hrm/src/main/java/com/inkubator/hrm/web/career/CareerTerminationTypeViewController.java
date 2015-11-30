package com.inkubator.hrm.web.career;

import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.entity.CareerTerminationType;
import com.inkubator.hrm.service.CareerTerminationTypeService;
import com.inkubator.hrm.web.lazymodel.CareerTerminationTypeLazyDataModel;
import com.inkubator.hrm.web.search.CareerTerminationTypeSearchParameter;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesUtil;
import com.inkubator.webcore.util.MessagesResourceUtil;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import org.hibernate.exception.ConstraintViolationException;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.LazyDataModel;
import org.springframework.dao.DataIntegrityViolationException;

/**
 *
 * @author Ahmad Mudzakkir Amal
 */
@ManagedBean(name = "careerTerminationTypeViewController")
@ViewScoped
public class CareerTerminationTypeViewController extends BaseController {

    private CareerTerminationTypeSearchParameter searchParameter;
    private LazyDataModel<CareerTerminationType> lazyData;
    private CareerTerminationType selectedCareerTerminationType;
    @ManagedProperty(value = "#{careerTerminationTypeService}")
    private CareerTerminationTypeService careerTerminationTypeService;

    @PostConstruct
    @Override
    public void initialization() {
        super.initialization();
        searchParameter = new CareerTerminationTypeSearchParameter();
    }

    @PreDestroy
    public void cleanAndExit() {
        careerTerminationTypeService = null;
        searchParameter = null;
        lazyData = null;
        selectedCareerTerminationType = null;
    }

    public void doDelete() {
        try {
            careerTerminationTypeService.delete(selectedCareerTerminationType);
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_INFO, "global.delete", "global.delete_successfully", FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
        } catch (ConstraintViolationException | DataIntegrityViolationException ex) {
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_ERROR, "global.error", "error.delete_constraint", FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
            LOGGER.error("Error when doDelete terminationType ", ex);
        } catch (Exception ex) {
            LOGGER.error("Error when doDelete terminationType", ex);
        }
    }
    
    public void doSearch() {
        lazyData = null;
    }

    public void doDetail() {
        try {
            selectedCareerTerminationType = this.careerTerminationTypeService.getEntityWithDetailById(selectedCareerTerminationType.getId());
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
    }

    public void doAdd() {
        showDialog(null);
    }

    public void doUpdate() {
        Map<String, List<String>> dataToSend = new HashMap<>();
        dataToSend.put("param", Arrays.asList(String.valueOf(selectedCareerTerminationType.getId())));
        showDialog(dataToSend);
    }

    private void showDialog(Map<String, List<String>> params) {
        Map<String, Object> options = new HashMap<>();
        options.put("modal", true);
        options.put("draggable", true);
        options.put("resizable", false);
        options.put("contentWidth", 530);
        options.put("contentHeight", 420);
        RequestContext.getCurrentInstance().openDialog("career_termination_type_form", options, params);
    }

    @Override
    public void onDialogReturn(SelectEvent event) {
        //re-calculate searching
        doSearch();
        super.onDialogReturn(event);
    }
    
    public CareerTerminationTypeSearchParameter getSearchParameter() {
        return searchParameter;
    }

    public void setSearchParameter(CareerTerminationTypeSearchParameter searchParameter) {
        this.searchParameter = searchParameter;
    }

    public LazyDataModel<CareerTerminationType> getLazyData() {
    	 if (lazyData == null) {
             lazyData = new CareerTerminationTypeLazyDataModel(searchParameter, careerTerminationTypeService);
         }
         return lazyData;
	}

	public void setLazyData(LazyDataModel<CareerTerminationType> lazyData) {
		this.lazyData = lazyData;
	}

	public CareerTerminationType getSelectedCareerTerminationType() {
		return selectedCareerTerminationType;
	}

	public void setSelectedCareerTerminationType(CareerTerminationType selectedCareerTerminationType) {
		this.selectedCareerTerminationType = selectedCareerTerminationType;
	}

	public void setCareerTerminationTypeService(CareerTerminationTypeService careerTerminationTypeService) {
		this.careerTerminationTypeService = careerTerminationTypeService;
	}

	

   
}
