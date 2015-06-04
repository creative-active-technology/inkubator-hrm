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

    //private RmbsTypeSearchParameter parameter;
    //private LazyDataModel<RmbsType> lazyData;
    //private RmbsType selected;
    //@ManagedProperty(value = "#{rmbsTypeService}")
    //private RmbsTypeService rmbsTypeService;

    @PostConstruct
    @Override
    public void initialization() {
        super.initialization();
       // parameter = new RmbsTypeSearchParameter();
    }

    @PreDestroy
    public void cleanAndExit() {
//    	rmbsTypeService = null;
//        parameter = null;
//        lazyData = null;
//        selected = null;
    }

    public void doSearch() {
        //lazyData = null;
    }

    public void doSelectEntity() {
//        try {
//            selected = this.rmbsTypeService.getEntityByPkWithDetail(selected.getId());
//        } catch (Exception ex) {
//            LOGGER.error("Error", ex);
//        }
    }

    public void doDelete() {
//        try {
//        	rmbsTypeService.delete(selected);
//            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_INFO, "global.delete", "global.delete_successfully", FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
//
//        } catch (ConstraintViolationException | DataIntegrityViolationException ex) {
//            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_ERROR, "global.error", "error.delete_constraint", FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
//            LOGGER.error("Error ", ex);
//        } catch (Exception ex) {
//            LOGGER.error("Error ", ex);
//        }
    }

    public void doAdd() {
        showDialog(null);
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

//	public RmbsTypeSearchParameter getParameter() {
//		return parameter;
//	}
//
//	public void setParameter(RmbsTypeSearchParameter parameter) {
//		this.parameter = parameter;
//	}
//
//	public LazyDataModel<RmbsType> getLazyData() {
//		if(lazyData == null) {
//			lazyData = new RmbsTypeLazyDataModel(parameter, rmbsTypeService);
//		}
//		return lazyData;
//	}

//	public void setLazyData(LazyDataModel<RmbsType> lazyData) {
//		this.lazyData = lazyData;
//	}
//
//	public RmbsType getSelected() {
//		return selected;
//	}
//
//	public void setSelected(RmbsType selected) {
//		this.selected = selected;
//	}
//
//	public RmbsTypeService getRmbsTypeService() {
//		return rmbsTypeService;
//	}
//
//	public void setRmbsTypeService(RmbsTypeService rmbsTypeService) {
//		this.rmbsTypeService = rmbsTypeService;
//	}
    
    
}
