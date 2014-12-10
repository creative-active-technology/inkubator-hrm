package com.inkubator.hrm.web.organisation;

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
import com.inkubator.hrm.entity.CostCenterDept;
import com.inkubator.hrm.service.CostCenterDeptService;
import com.inkubator.hrm.web.lazymodel.CostCenterDeptLazyDataModel;
import com.inkubator.hrm.web.search.CostCenterDeptSearchParameter;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesUtil;
import com.inkubator.webcore.util.MessagesResourceUtil;

/**
 *
 * @author rizkykojek
 */
@ManagedBean(name = "costCenterDeptViewController")
@ViewScoped
public class CostCenterDeptViewController extends BaseController {

    private CostCenterDeptSearchParameter parameter;
    private LazyDataModel<CostCenterDept> lazyDataModel;
    private CostCenterDept selectedCostCenterDept;
    @ManagedProperty(value = "#{costCenterDeptService}")
    private CostCenterDeptService costCenterDeptService;

    @PostConstruct
    @Override
    public void initialization() {
        super.initialization();
        parameter =  new CostCenterDeptSearchParameter();
    }

    @PreDestroy
    public void cleanAndExit() {
        costCenterDeptService = null;
        parameter = null;
        lazyDataModel = null;
        selectedCostCenterDept = null;
    }

    public CostCenterDeptSearchParameter getParameter() {
		return parameter;
	}

	public void setParameter(CostCenterDeptSearchParameter parameter) {
		this.parameter = parameter;
	}

	public LazyDataModel<CostCenterDept> getLazyDataModel() {
    	if(lazyDataModel == null) {
    		lazyDataModel = new CostCenterDeptLazyDataModel(parameter, costCenterDeptService);
    	}
		return lazyDataModel;
	}

	public void setLazyDataModel(LazyDataModel<CostCenterDept> lazyDataModel) {
		this.lazyDataModel = lazyDataModel;
	}

	public CostCenterDept getSelectedCostCenterDept() {
		return selectedCostCenterDept;
	}

	public void setSelectedCostCenterDept(CostCenterDept selectedCostCenterDept) {
		this.selectedCostCenterDept = selectedCostCenterDept;
	}

	public CostCenterDeptService getCostCenterDeptService() {
		return costCenterDeptService;
	}

	public void setCostCenterDeptService(CostCenterDeptService costCenterDeptService) {
		this.costCenterDeptService = costCenterDeptService;
	}

	public void doSearch() {
        lazyDataModel = null;
    }

    public void doSelectEntity() {
        try {
            selectedCostCenterDept = this.costCenterDeptService.getEntiyByPK(selectedCostCenterDept.getId());
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
    }

    public void doDelete() {
        try {
            costCenterDeptService.delete(selectedCostCenterDept);
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_INFO, "global.delete", "global.delete_successfully", FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());

        } catch (ConstraintViolationException | DataIntegrityViolationException ex) {
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_ERROR, "global.error", "error.delete_constraint", FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
            LOGGER.error("Error when doDelete religion ", ex);
        } catch (Exception ex) {
            LOGGER.error("Error when doDelete religion", ex);
        }
    }

    public void doAdd() {
        showDialog(null);
    }

    public void doUpdate() {
        Map<String, List<String>> dataToSend = new HashMap<>();
        List<String> values = new ArrayList<>();
        values.add(String.valueOf(selectedCostCenterDept.getId()));
        dataToSend.put("param", values);
        showDialog(dataToSend);
    }

    private void showDialog(Map<String, List<String>> params) {
        Map<String, Object> options = new HashMap<>();
        options.put("modal", true);
        options.put("draggable", true);
        options.put("resizable", false);
        options.put("contentWidth", 400);
        options.put("contentHeight", 320);
        RequestContext.getCurrentInstance().openDialog("cost_center_dept_form", options, params);
    }

    @Override
    public void onDialogReturn(SelectEvent event) {
        //re-calculate searching
        doSearch();
        super.onDialogReturn(event);
    }
}
