package com.inkubator.hrm.web.reimbursement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.LazyDataModel;

import com.inkubator.hrm.service.RmbsSchemaListOfEmpService;
import com.inkubator.hrm.web.lazymodel.RmbsSchemaEmpLazyDataModel;
import com.inkubator.hrm.web.model.RmbsSchemaEmpViewModel;
import com.inkubator.hrm.web.search.RmbsSchemaEmpSearchParameter;
import com.inkubator.webcore.controller.BaseController;

/**
 *
 * @author rizkykojek
 */
@ManagedBean(name = "rmbsSchemaEmpViewController")
@ViewScoped
public class RmbsSchemaEmpViewController extends BaseController {

    private RmbsSchemaEmpSearchParameter parameter;
    private LazyDataModel<RmbsSchemaEmpViewModel> lazyData;
    private RmbsSchemaEmpViewModel selected;
    @ManagedProperty(value = "#{rmbsSchemaListOfEmpService}")
    private RmbsSchemaListOfEmpService rmbsSchemaListOfEmpService;

    @PostConstruct
    @Override
    public void initialization() {
        super.initialization();
        parameter = new RmbsSchemaEmpSearchParameter();
    }

    @PreDestroy
    public void cleanAndExit() {
    	rmbsSchemaListOfEmpService = null;
        parameter = null;
        lazyData = null;
        selected = null;
    }

    public void doSearch() {
        lazyData = null;
    }
    
    public String doDetail(){
    	return "/protected/reimbursement/rmbs_schema_emp_detail.htm?faces-redirect=true&empDataId=e" + selected.getEmpDataId() + "&rmbsSchemaId=e" + selected.getRmbsSchemaId();
    }

    public void doUpdate() {
        Map<String, List<String>> dataToSend = new HashMap<>();
        
        List<String> empDataId = new ArrayList<>();
        empDataId.add(String.valueOf(selected.getEmpDataId()));
        dataToSend.put("empDataId", empDataId);
        
        List<String> rmbsSchemaId = new ArrayList<>();
        rmbsSchemaId.add(String.valueOf(selected.getRmbsSchemaId()));
        dataToSend.put("rmbsSchemaId", rmbsSchemaId);
        
        showDialog(dataToSend);
    }

    private void showDialog(Map<String, List<String>> params) {
        Map<String, Object> options = new HashMap<>();
        options.put("modal", true);
        options.put("draggable", true);
        options.put("resizable", false);
        options.put("contentWidth", 550);
        options.put("contentHeight", 430);
        RequestContext.getCurrentInstance().openDialog("rmbs_schema_emp_form", options, params);
    }

    @Override
    public void onDialogReturn(SelectEvent event) {
        //re-calculate searching
        doSearch();
        super.onDialogReturn(event);
    }

	public RmbsSchemaEmpSearchParameter getParameter() {
		return parameter;
	}

	public void setParameter(RmbsSchemaEmpSearchParameter parameter) {
		this.parameter = parameter;
	}

	public LazyDataModel<RmbsSchemaEmpViewModel> getLazyData() {
		if(lazyData == null) {
			lazyData = new RmbsSchemaEmpLazyDataModel(parameter, rmbsSchemaListOfEmpService);
		}
		return lazyData;
	}

	public void setLazyData(LazyDataModel<RmbsSchemaEmpViewModel> lazyData) {
		this.lazyData = lazyData;
	}

	public RmbsSchemaEmpViewModel getSelected() {
		return selected;
	}

	public void setSelected(RmbsSchemaEmpViewModel selected) {
		this.selected = selected;
	}

	public RmbsSchemaListOfEmpService getRmbsSchemaListOfEmpService() {
		return rmbsSchemaListOfEmpService;
	}

	public void setRmbsSchemaListOfEmpService(
			RmbsSchemaListOfEmpService rmbsSchemaListOfEmpService) {
		this.rmbsSchemaListOfEmpService = rmbsSchemaListOfEmpService;
	}
    
    

	
}
