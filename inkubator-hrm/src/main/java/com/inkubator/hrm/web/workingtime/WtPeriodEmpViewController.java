package com.inkubator.hrm.web.workingtime;

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
import com.inkubator.hrm.service.WtPeriodeService;
import com.inkubator.hrm.web.lazymodel.WtPeriodEmpLazyDataModel;
import com.inkubator.hrm.web.model.WtPeriodEmpViewModel;
import com.inkubator.hrm.web.search.WtPeriodeEmpSearchParameter;
//import com.inkubator.hrm.entity.RmbsType;
//import com.inkubator.hrm.service.RmbsTypeService;
//import com.inkubator.hrm.web.lazymodel.RmbsTypeLazyDataModel;
//import com.inkubator.hrm.web.search.RmbsTypeSearchParameter;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesUtil;
import com.inkubator.webcore.util.MessagesResourceUtil;

/**
 *
 * @author Ahmad Mudzakkir Amal
 */
@ManagedBean(name = "wtPeriodEmpViewController")
@ViewScoped
public class WtPeriodEmpViewController extends BaseController {

    private WtPeriodeEmpSearchParameter parameter;
    private LazyDataModel<WtPeriodEmpViewModel> lazyData;
    private WtPeriodEmpViewModel selected;
    @ManagedProperty(value = "#{wtPeriodeService}")
    private WtPeriodeService wtPeriodeService;

    @PostConstruct
    @Override
    public void initialization() {
        super.initialization();
        parameter = new WtPeriodeEmpSearchParameter();
    }

    @PreDestroy
    public void cleanAndExit() {
    	wtPeriodeService = null;
        parameter = null;
        lazyData = null;
        selected = null;
    }

    public void doSearch() {
        lazyData = null;
    }

    public String doDetail() {
return "/protected/working_time/wt_period_emp_detail.htm?faces-redirect=true&execution=e" + selected.getWtPeriodId().longValue();
    }

    private void showDialog(Map<String, List<String>> params) {
        Map<String, Object> options = new HashMap<>();
        options.put("modal", true);
        options.put("draggable", true);
        options.put("resizable", false);
        options.put("contentWidth", 560);
        options.put("contentHeight", 440);
        RequestContext.getCurrentInstance().openDialog("rmbs_type_form", options, params);
    }

    @Override
    public void onDialogReturn(SelectEvent event) {
        //re-calculate searching
        doSearch();
        super.onDialogReturn(event);
    }

	public LazyDataModel<WtPeriodEmpViewModel> getLazyData() {
		if(lazyData == null) {
			lazyData = new WtPeriodEmpLazyDataModel(parameter, wtPeriodeService);
		}
		return lazyData;
	}

	public void setLazyData(LazyDataModel<WtPeriodEmpViewModel> lazyData) {
		this.lazyData = lazyData;
	}

	public WtPeriodEmpViewModel getSelected() {
		return selected;
	}

	public void setSelected(WtPeriodEmpViewModel selected) {
		this.selected = selected;
	}

    public void setWtPeriodeService(WtPeriodeService wtPeriodeService) {
        this.wtPeriodeService = wtPeriodeService;
    }

    public WtPeriodeEmpSearchParameter getParameter() {
        return parameter;
    }

    public void setParameter(WtPeriodeEmpSearchParameter parameter) {
        this.parameter = parameter;
    }

}
