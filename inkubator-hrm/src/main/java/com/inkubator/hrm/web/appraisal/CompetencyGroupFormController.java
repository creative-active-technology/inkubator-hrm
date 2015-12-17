/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.appraisal;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.apache.commons.lang3.StringUtils;
import org.primefaces.model.DualListModel;

import com.inkubator.exception.BussinessException;
import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.entity.AppraisalCompetencyGroup;
import com.inkubator.hrm.entity.AppraisalCompetencyGroupKlasifikasiKerja;
import com.inkubator.hrm.entity.AppraisalCompetencyType;
import com.inkubator.hrm.entity.KlasifikasiKerja;
import com.inkubator.hrm.service.AppraisalCompetencyGroupService;
import com.inkubator.hrm.service.AppraisalCompetencyTypeService;
import com.inkubator.hrm.service.KlasifikasiKerjaService;
import com.inkubator.hrm.web.model.AppraisalCompetencyGroupModel;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesUtil;
import com.inkubator.webcore.util.MessagesResourceUtil;

import ch.lambdaj.Lambda;

/**
 *
 * @author rizkykojek
 */
@ManagedBean(name = "competencyGroupFormController")
@ViewScoped
public class CompetencyGroupFormController extends BaseController{
	
	private Boolean isUpdate;
    private AppraisalCompetencyGroupModel model;
    
    @ManagedProperty(value = "#{appraisalCompetencyGroupService}")
    private AppraisalCompetencyGroupService appraisalCompetencyGroupService;
    @ManagedProperty(value = "#{klasifikasiKerjaService}")
    private KlasifikasiKerjaService klasifikasiKerjaService;
    @ManagedProperty(value = "#{appraisalCompetencyTypeService}")
    private AppraisalCompetencyTypeService appraisalCompetencyTypeService;
    
    @PostConstruct
    @Override
    public void initialization(){
        super.initialization();
        try{
            model = new AppraisalCompetencyGroupModel();
            isUpdate = Boolean.FALSE;
            
            String id = FacesUtil.getRequestParameter("execution");
            if(StringUtils.isNotEmpty(id)){
            	AppraisalCompetencyGroup competencyGroup = appraisalCompetencyGroupService.getEntityByIdWithDetail(Long.parseLong(id.substring(1)));
                if(competencyGroup != null){
                	isUpdate = Boolean.TRUE;
                	model = getModelFromEntity(competencyGroup);
                }
            } else {
            	List<KlasifikasiKerja> listKlasifikasiKerja = klasifikasiKerjaService.getAllData();
            	model.setDualListModelKlasifikasiKerja(new DualListModel<>(listKlasifikasiKerja, new ArrayList<>()));
            }
            
            model.setListCompetencyType(appraisalCompetencyTypeService.getAllData());
        } catch (Exception e){
            LOGGER.error("error", e);
        }
    }
    
    @PreDestroy
    public void cleanAndExit(){
    	isUpdate = null;
    	model = null;
    	appraisalCompetencyGroupService = null;
    	klasifikasiKerjaService = null;
    	appraisalCompetencyTypeService = null;
        
    }
    
    private AppraisalCompetencyGroupModel getModelFromEntity(AppraisalCompetencyGroup competencyGroup) throws Exception{
    	AppraisalCompetencyGroupModel model = new AppraisalCompetencyGroupModel();
        model.setId(competencyGroup.getId());
        model.setCode(competencyGroup.getCode());
        model.setName(competencyGroup.getName());
        model.setDescription(competencyGroup.getDescription());
        model.setCompetencyTypeId(competencyGroup.getCompetencyType().getId());
        
        List<KlasifikasiKerja> sourceList = klasifikasiKerjaService.getAllData();
        List<KlasifikasiKerja> targetList = Lambda.extract(competencyGroup.getKlasifikasiKerjas(), Lambda.on(AppraisalCompetencyGroupKlasifikasiKerja.class).getKlasifikasiKerja());
        sourceList.removeAll(targetList);
        
        model.getDualListModelKlasifikasiKerja().setSource(sourceList);
        model.getDualListModelKlasifikasiKerja().setTarget(targetList);
        
        return model;
    }
    
    private AppraisalCompetencyGroup getEntityFromViewModel(AppraisalCompetencyGroupModel model){
    	AppraisalCompetencyGroup competencyGroup = new AppraisalCompetencyGroup();
        if(model.getId() != null){
            competencyGroup.setId(model.getId());
        }
        competencyGroup.setCode(model.getCode());
        competencyGroup.setName(model.getName());
        competencyGroup.setDescription(model.getDescription());
        competencyGroup.setCompetencyType(new AppraisalCompetencyType(model.getCompetencyTypeId()));
        return competencyGroup;
    }
    
    
    public String doSave(){
    	AppraisalCompetencyGroup competencyGroup = getEntityFromViewModel(model);
    	try {
			if (isUpdate) {
				appraisalCompetencyGroupService.update(competencyGroup, model.getDualListModelKlasifikasiKerja().getTarget());
				MessagesResourceUtil.setMessagesFlas(FacesMessage.SEVERITY_INFO, "global.save_info", "global.update_successfully",
	                    FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
			    return "/protected/appraisal/competency_group_view.htm?faces-redirect=true";
			} else {
				appraisalCompetencyGroupService.save(competencyGroup, model.getDualListModelKlasifikasiKerja().getTarget());
				MessagesResourceUtil.setMessagesFlas(FacesMessage.SEVERITY_INFO, "global.save_info", "global.added_successfully",
	                    FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
				return "/protected/appraisal/competency_group_view.htm?faces-redirect=true";
			}
		} catch (BussinessException ex) {
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_ERROR, "global.error", ex.getErrorKeyMessage(), FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
        }catch (Exception e) {
			e.printStackTrace();
		}
    	return null;
    }
    
	public String doBack(){
		return "/protected/appraisal/competency_group_view.htm?faces-redirect=true";
	}

	public Boolean getIsUpdate() {
		return isUpdate;
	}

	public void setIsUpdate(Boolean isUpdate) {
		this.isUpdate = isUpdate;
	}

	public AppraisalCompetencyGroupModel getModel() {
		return model;
	}

	public void setModel(AppraisalCompetencyGroupModel model) {
		this.model = model;
	}

	public AppraisalCompetencyGroupService getAppraisalCompetencyGroupService() {
		return appraisalCompetencyGroupService;
	}

	public void setAppraisalCompetencyGroupService(AppraisalCompetencyGroupService appraisalCompetencyGroupService) {
		this.appraisalCompetencyGroupService = appraisalCompetencyGroupService;
	}

	public KlasifikasiKerjaService getKlasifikasiKerjaService() {
		return klasifikasiKerjaService;
	}

	public void setKlasifikasiKerjaService(KlasifikasiKerjaService klasifikasiKerjaService) {
		this.klasifikasiKerjaService = klasifikasiKerjaService;
	}

	public AppraisalCompetencyTypeService getAppraisalCompetencyTypeService() {
		return appraisalCompetencyTypeService;
	}

	public void setAppraisalCompetencyTypeService(AppraisalCompetencyTypeService appraisalCompetencyTypeService) {
		this.appraisalCompetencyTypeService = appraisalCompetencyTypeService;
	}
	
}
