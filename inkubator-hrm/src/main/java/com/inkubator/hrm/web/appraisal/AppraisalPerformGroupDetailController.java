/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.appraisal;


import com.inkubator.hrm.entity.AppraisalPerformanceGroup;
import com.inkubator.hrm.entity.AppraisalPerformanceIndicator;
import com.inkubator.hrm.entity.RecruitMppApply;
import com.inkubator.hrm.service.AppraisalPerformanceGroupService;
import com.inkubator.hrm.service.AppraisalPerformanceIndicatorService;
import com.inkubator.hrm.web.model.RecruitMppApplyModel;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesUtil;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import org.apache.commons.lang3.StringUtils;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;

/**
 *
 * @author Ahmad Mudzakkir Amal
 */
@ManagedBean(name = "appraisalPerformGroupDetailController")
@ViewScoped
public class AppraisalPerformGroupDetailController extends BaseController {

    
    @ManagedProperty(value = "#{appraisalPerformanceGroupService}")
    private AppraisalPerformanceGroupService appraisalPerformanceGroupService;
    @ManagedProperty(value = "#{appraisalPerformanceIndicatorService}")
    private AppraisalPerformanceIndicatorService appraisalPerformanceIndicatorService;
    private List<AppraisalPerformanceIndicator> listPerformanceAppraisalIndicator;
    private AppraisalPerformanceGroup selectedAppraisalPerformanceGroup;
    private AppraisalPerformanceIndicator selectedAppraisalPerformanceIndicator;
    
    @PostConstruct
    @Override
    public void initialization() {
        super.initialization();
        try {

        	listPerformanceAppraisalIndicator = new ArrayList<AppraisalPerformanceIndicator>();
            String idAppraisalPerformanceGroup = FacesUtil.getRequestParameter("execution");
            if (StringUtils.isNotBlank(idAppraisalPerformanceGroup) && StringUtils.isNumeric(idAppraisalPerformanceGroup)) {
                selectedAppraisalPerformanceGroup = appraisalPerformanceGroupService.getEntiyByPK(Long.valueOf(idAppraisalPerformanceGroup));
                listPerformanceAppraisalIndicator = appraisalPerformanceIndicatorService.getListByIdAppraisalPerformanceGroup(Long.valueOf(idAppraisalPerformanceGroup));
            }

        } catch (Exception e) {
            LOGGER.error("Error", e);
        }
    }

   

    @PreDestroy
    public void cleanAndExit() {
        appraisalPerformanceGroupService = null;
        appraisalPerformanceIndicatorService = null;
        selectedAppraisalPerformanceGroup = null;
        listPerformanceAppraisalIndicator = null;
        
    }
    
    public void doSelectEntityIndicator() {
    	
    }

    public String doBack() {
        cleanAndExit();
        return "/protected/appraisal/appraisal_perform_group_view.htm?faces-redirect=true";
    }
    
    public void doAddEntityIndicator(){
    	//showDialog(null);
    	Map<String, List<String>> dataToSend = new HashMap<>();
        dataToSend.put("appraisalPerformanceGroupId", Arrays.asList(String.valueOf(selectedAppraisalPerformanceGroup.getId())));
        showDialog(dataToSend);
    }

    public String doEditEntityIndicator() {
        //return "/protected/recruitment/recruit_mpp_apply_form.htm?faces-redirect=true&execution=" + activityNumber;
    	return "/protected/recruitment/recruit_mpp_apply_view.htm?faces-redirect=true";
    }

    public void doDeleteEntityIndicator(){
    	  try {
          	appraisalPerformanceIndicatorService.delete(selectedAppraisalPerformanceIndicator);
          } catch (Exception e) {
              LOGGER.error("Error", e);
          }
    }
    
    private void showDialog(Map<String, List<String>> params) {
        Map<String, Object> options = new HashMap<>();
        options.put("modal", true);
        options.put("draggable", true);
        options.put("resizable", false);
        options.put("contentWidth", 430);
        options.put("contentHeight", 330);
        RequestContext.getCurrentInstance().openDialog("performance_indicator_form", options, params);
    }

    @Override
    public void onDialogReturn(SelectEvent event){
    	 try {
    		 listPerformanceAppraisalIndicator = appraisalPerformanceIndicatorService.getListByIdAppraisalPerformanceGroup(Long.valueOf(selectedAppraisalPerformanceGroup.getId()));
    	      super.onDialogReturn(event);
    	 }catch(Exception e){
    		 LOGGER.error("Error", e);
    	 }
    }

	public AppraisalPerformanceIndicator getSelectedAppraisalPerformanceIndicator() {
		return selectedAppraisalPerformanceIndicator;
	}

	public void setSelectedAppraisalPerformanceIndicator(
			AppraisalPerformanceIndicator selectedAppraisalPerformanceIndicator) {
		this.selectedAppraisalPerformanceIndicator = selectedAppraisalPerformanceIndicator;
	}

	public List<AppraisalPerformanceIndicator> getListPerformanceAppraisalIndicator() {
		return listPerformanceAppraisalIndicator;
	}

	public void setListPerformanceAppraisalIndicator(
			List<AppraisalPerformanceIndicator> listPerformanceAppraisalIndicator) {
		this.listPerformanceAppraisalIndicator = listPerformanceAppraisalIndicator;
	}

	public AppraisalPerformanceGroup getSelectedAppraisalPerformanceGroup() {
		return selectedAppraisalPerformanceGroup;
	}

	public void setSelectedAppraisalPerformanceGroup(AppraisalPerformanceGroup selectedAppraisalPerformanceGroup) {
		this.selectedAppraisalPerformanceGroup = selectedAppraisalPerformanceGroup;
	}

	public void setAppraisalPerformanceGroupService(AppraisalPerformanceGroupService appraisalPerformanceGroupService) {
		this.appraisalPerformanceGroupService = appraisalPerformanceGroupService;
	}

	public void setAppraisalPerformanceIndicatorService(
			AppraisalPerformanceIndicatorService appraisalPerformanceIndicatorService) {
		this.appraisalPerformanceIndicatorService = appraisalPerformanceIndicatorService;
	}
 
}
