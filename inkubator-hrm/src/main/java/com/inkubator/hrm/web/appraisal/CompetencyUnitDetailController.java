/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.appraisal;

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
import org.springframework.dao.DataIntegrityViolationException;

import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.entity.AppraisalCompetencyUnit;
import com.inkubator.hrm.entity.AppraisalCompetencyUnitIndicator;
import com.inkubator.hrm.service.AppraisalCompetencyUnitIndicatorService;
import com.inkubator.hrm.service.AppraisalCompetencyUnitService;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesUtil;
import com.inkubator.webcore.util.MessagesResourceUtil;

import ch.lambdaj.Lambda;

/**
 *
 * @author rizkykojek
 */
@ManagedBean(name = "competencyUnitDetailController")
@ViewScoped
public class CompetencyUnitDetailController extends BaseController {
	
	private AppraisalCompetencyUnit competencyUnit;
	private AppraisalCompetencyUnitIndicator selectedUnitIndicator;
	private List<AppraisalCompetencyUnitIndicator> listUnitIndicator;
    
    @ManagedProperty(value = "#{appraisalCompetencyUnitService}")
    private AppraisalCompetencyUnitService appraisalCompetencyUnitService;
    @ManagedProperty(value = "#{appraisalCompetencyUnitIndicatorService}")
    private AppraisalCompetencyUnitIndicatorService appraisalCompetencyUnitIndicatorService;
    

    @PostConstruct
    @Override
    public void initialization() {
        try {
            super.initialization();
            String id = FacesUtil.getRequestParameter("execution").substring(1);
            competencyUnit = appraisalCompetencyUnitService.getEntityByIdWithDetail(Long.parseLong(id));
            this.generateListUnitIndicator();
        } catch (Exception ex) {
            LOGGER.error("Error", ex);

        }
    }

    @PreDestroy
    public void cleanAndExit() {
    	competencyUnit = null;
    	listUnitIndicator = null;
    	appraisalCompetencyUnitService = null;
    	appraisalCompetencyUnitIndicatorService = null;
    	selectedUnitIndicator = null;
    }   

    public String doBack() {
    	return "/protected/appraisal/competency_unit_view.htm?faces-redirect=true";
    }
    
    private void generateListUnitIndicator(){
    	try {
    		listUnitIndicator = appraisalCompetencyUnitIndicatorService.getAllDataByCompetencyUnitId(competencyUnit.getId());
    		listUnitIndicator = Lambda.sort(listUnitIndicator, Lambda.on(AppraisalCompetencyUnitIndicator.class).getLevelIndex());
    	} catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
    }
    
    public void onDialogReturnIndicator(SelectEvent event) {
    	this.generateListUnitIndicator();
    	super.onDialogReturn(event);
    }
    
    public void doDeleteIndicator(){
    	try {
    		appraisalCompetencyUnitIndicatorService.delete(selectedUnitIndicator);
    		this.generateListUnitIndicator();
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_INFO, "global.delete", "global.delete_successfully", FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());

        } catch (ConstraintViolationException | DataIntegrityViolationException ex) {
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_ERROR, "global.error", "error.delete_constraint", FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
            LOGGER.error("Error ", ex);
        } catch (Exception ex) {
            LOGGER.error("Error ", ex);
        }
    }
    
    public void doAddIndicator(){
    	Map<String, List<String>> dataToSend = new HashMap<>();
        
    	List<String> paramCompetencyUnitId = new ArrayList<>();
        paramCompetencyUnitId.add(String.valueOf(competencyUnit.getId()));
        dataToSend.put("paramCompetencyUnitId", paramCompetencyUnitId);
        
        showDialogIndicator(dataToSend);
    }
    
    public void doUpdateIndicator(){
    	Map<String, List<String>> dataToSend = new HashMap<>();
        
    	List<String> paramCompetencyUnitId = new ArrayList<>();
        paramCompetencyUnitId.add(String.valueOf(competencyUnit.getId()));
        dataToSend.put("paramCompetencyUnitId", paramCompetencyUnitId);
        
        List<String> paramCompetencyUnitIndicatorId = new ArrayList<>();
        paramCompetencyUnitIndicatorId.add(String.valueOf(selectedUnitIndicator.getId()));
        dataToSend.put("paramCompetencyUnitIndicatorId", paramCompetencyUnitIndicatorId);
        
        showDialogIndicator(dataToSend);
    }
    
    private void showDialogIndicator(Map<String, List<String>> params) {
        Map<String, Object> options = new HashMap<>();
        options.put("modal", true);
        options.put("draggable", true);
        options.put("resizable", false);
        options.put("contentWidth", 560);
        options.put("contentHeight", 360);
        RequestContext.getCurrentInstance().openDialog("competency_unit_indicator_form", options, params);
    }

	public AppraisalCompetencyUnit getCompetencyUnit() {
		return competencyUnit;
	}

	public void setCompetencyUnit(AppraisalCompetencyUnit competencyUnit) {
		this.competencyUnit = competencyUnit;
	}

	public List<AppraisalCompetencyUnitIndicator> getListUnitIndicator() {
		return listUnitIndicator;
	}

	public void setListUnitIndicator(List<AppraisalCompetencyUnitIndicator> listUnitIndicator) {
		this.listUnitIndicator = listUnitIndicator;
	}

	public AppraisalCompetencyUnitService getAppraisalCompetencyUnitService() {
		return appraisalCompetencyUnitService;
	}

	public void setAppraisalCompetencyUnitService(AppraisalCompetencyUnitService appraisalCompetencyUnitService) {
		this.appraisalCompetencyUnitService = appraisalCompetencyUnitService;
	}

	public AppraisalCompetencyUnitIndicatorService getAppraisalCompetencyUnitIndicatorService() {
		return appraisalCompetencyUnitIndicatorService;
	}

	public void setAppraisalCompetencyUnitIndicatorService(
			AppraisalCompetencyUnitIndicatorService appraisalCompetencyUnitIndicatorService) {
		this.appraisalCompetencyUnitIndicatorService = appraisalCompetencyUnitIndicatorService;
	}

	public AppraisalCompetencyUnitIndicator getSelectedUnitIndicator() {
		return selectedUnitIndicator;
	}

	public void setSelectedUnitIndicator(AppraisalCompetencyUnitIndicator selectedUnitIndicator) {
		this.selectedUnitIndicator = selectedUnitIndicator;
	}
	
}
