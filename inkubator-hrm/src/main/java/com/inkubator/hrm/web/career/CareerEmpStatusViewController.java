package com.inkubator.hrm.web.career;

import java.util.ArrayList;
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

import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.entity.CareerEmpStatus;
import com.inkubator.hrm.service.CareerEmpStatusService;
import com.inkubator.hrm.web.lazymodel.CareerEmpStatusLazyDataModel;
import com.inkubator.hrm.web.search.CareerEmpStatusSearchParameter;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesUtil;
import com.inkubator.webcore.util.MessagesResourceUtil;

/**
 *
 * @author rizkykojek
 */
@ManagedBean(name = "careerEmpStatusViewController")
@ViewScoped
public class CareerEmpStatusViewController extends BaseController {

    private CareerEmpStatusSearchParameter parameter;
    private LazyDataModel<CareerEmpStatus> lazyData;
    private CareerEmpStatus selected;
    @ManagedProperty(value = "#{careerEmpStatusService}")
    private CareerEmpStatusService careerEmpStatusService;

    @PostConstruct
    @Override
    public void initialization() {
        super.initialization();
        parameter = new CareerEmpStatusSearchParameter();
    }

    @PreDestroy
    public void cleanAndExit() {
    	careerEmpStatusService = null;
        parameter = null;
        lazyData = null;
        selected = null;
    }

    public void doSearch() {
        lazyData = null;
    }

    public void doSelectEntity() {
        try {
            selected = this.careerEmpStatusService.getEntityByPkWithDetail(selected.getId());
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
    }

    public void doDelete() {
        try {
        	careerEmpStatusService.delete(selected);
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_INFO, "global.delete", "global.delete_successfully", FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());

        } catch (ConstraintViolationException | DataIntegrityViolationException ex) {
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_ERROR, "global.error", "error.delete_constraint", FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
            LOGGER.error("Error ", ex);
        } catch (Exception ex) {
            LOGGER.error("Error ", ex);
        }
    }

    public void doAdd() {
        showDialog(null);
    }

    public void doUpdate() {
        Map<String, List<String>> dataToSend = new HashMap<>();
        List<String> values = new ArrayList<>();
        values.add(String.valueOf(selected.getId()));
        dataToSend.put("param", values);
        showDialog(dataToSend);
    }

    private void showDialog(Map<String, List<String>> params) {
        Map<String, Object> options = new HashMap<>();
        options.put("modal", true);
        options.put("draggable", true);
        options.put("resizable", false);
        options.put("contentWidth", 560);
        options.put("contentHeight", 360);
        RequestContext.getCurrentInstance().openDialog("career_emp_status_form", options, params);
    }

    @Override
    public void onDialogReturn(SelectEvent event) {
        //re-calculate searching
        doSearch();
        super.onDialogReturn(event);
    }

	public CareerEmpStatusSearchParameter getParameter() {
		return parameter;
	}

	public void setParameter(CareerEmpStatusSearchParameter parameter) {
		this.parameter = parameter;
	}

	public LazyDataModel<CareerEmpStatus> getLazyData() {
		if(lazyData == null){
			lazyData = new CareerEmpStatusLazyDataModel(parameter, careerEmpStatusService);
		}
		return lazyData;
	}

	public void setLazyData(LazyDataModel<CareerEmpStatus> lazyData) {
		this.lazyData = lazyData;
	}

	public CareerEmpStatus getSelected() {
		return selected;
	}

	public void setSelected(CareerEmpStatus selected) {
		this.selected = selected;
	}

	public CareerEmpStatusService getCareerEmpStatusService() {
		return careerEmpStatusService;
	}

	public void setCareerEmpStatusService(CareerEmpStatusService careerEmpStatusService) {
		this.careerEmpStatusService = careerEmpStatusService;
	}
    
}
