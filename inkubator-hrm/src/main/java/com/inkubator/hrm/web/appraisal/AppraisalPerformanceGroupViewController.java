package com.inkubator.hrm.web.appraisal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.inkubator.hrm.entity.AppraisalPerformanceGroup;
import com.inkubator.hrm.service.AppraisalPerformanceGroupService;
import com.inkubator.hrm.web.lazymodel.AppraisalPerformanceGroupLazyDataModel;
import com.inkubator.hrm.web.search.AppraisalPerformanceGroupSearchParameter;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesUtil;
import com.inkubator.webcore.util.MessagesResourceUtil;

@ManagedBean(name = "appraisalperformanceGroupViewController")
@ViewScoped
public class AppraisalPerformanceGroupViewController extends BaseController{
	@ManagedProperty(value = "#{appraisalPerformanceGroupService}")
	private AppraisalPerformanceGroupService service;
	private AppraisalPerformanceGroupSearchParameter searchParameter;
	private LazyDataModel<AppraisalPerformanceGroup> lazy;
	private AppraisalPerformanceGroup selected;
	
	@Override
	public void initialization(){
		super.initialization();
		searchParameter = new AppraisalPerformanceGroupSearchParameter();
	}
	
	@PreDestroy
	private void cleanAndExit(){
		service = null;
		searchParameter = null;
		lazy = null;
		selected = null;
	}
	
	public void doSearch(){
		lazy = null;
	}
	
	public void doSelectEntity(){
		try{
			selected = service.getEntiyByPK(selected.getId());
		}catch(Exception ex){
			LOGGER.info("Error ", ex);
		}
	}
	
	public void doDelete(){
        try{
            this.service.delete(selected);
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_INFO, "global.delete", "global.delete_successfully",
                    FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
        } catch (ConstraintViolationException | DataIntegrityViolationException ex){
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_ERROR, "global.error", "error.delete_constraint",
                    FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
            LOGGER.error("Error", ex);
        } catch (Exception ex){
            LOGGER.error("Error", ex);
        }
    }
	
	public void showDialog(Map<String, List<String>> params){
        Map<String, Object> options = new HashMap<>();
        options.put("modal", true);
        options.put("draggable", true);
        options.put("resizable", true);
        options.put("contentWidth", 400);
        options.put("contentHeight", 430);
        RequestContext.getCurrentInstance().openDialog("appraisal_perform_group_form", options, params);
    }
	
	public void doAdd(){
		showDialog(null);
	}
	
	 public String doDetail() {
	        return "/protected/appraisal/appraisal_perform_group_detail.htm?faces-redirect=true&execution=" + selected.getId();
	    	//return "/protected/recruitment/recruit_mpp_apply_view.htm?faces-redirect=true";
	    }
	
	public void doEdit(){
        Map<String, List<String>> dataToSend = new HashMap<>();
        List<String> dataIsi = new ArrayList<>();
        dataIsi.add(String.valueOf(selected.getId()));
        dataToSend.put("appraisalPerformanceGroupId", dataIsi);
        showDialog(dataToSend);
    }
	
	@Override
    public void onDialogReturn(SelectEvent event){
        lazy = null;
        super.onDialogReturn(event);
    }
	
	public void onDelete(){
        try{
            selected = this.service.getEntiyByPK(selected.getId());
        } catch (Exception ex){
            LOGGER.error("Error", ex);
        }
    }

	public AppraisalPerformanceGroupService getService() {
		return service;
	}

	public void setService(AppraisalPerformanceGroupService service) {
		this.service = service;
	}

	public AppraisalPerformanceGroupSearchParameter getSearchParameter() {
		return searchParameter;
	}

	public void setSearchParameter(AppraisalPerformanceGroupSearchParameter searchParameter) {
		this.searchParameter = searchParameter;
	}

	public LazyDataModel<AppraisalPerformanceGroup> getLazy() {
		if(lazy == null){
			lazy = new AppraisalPerformanceGroupLazyDataModel(searchParameter, service);
		}
		return lazy;
	}

	public void setLazy(LazyDataModel<AppraisalPerformanceGroup> lazy) {
		this.lazy = lazy;
	}

	public AppraisalPerformanceGroup getSelected() {
		return selected;
	}

	public void setSelected(AppraisalPerformanceGroup selected) {
		this.selected = selected;
	}
}
