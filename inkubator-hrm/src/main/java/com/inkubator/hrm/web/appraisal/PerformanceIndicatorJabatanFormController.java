/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.appraisal;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.apache.commons.lang3.StringUtils;

import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.entity.AppraisalPerformanceGroup;
import com.inkubator.hrm.entity.AppraisalPerformanceIndicatorJabatan;
import com.inkubator.hrm.entity.Jabatan;
import com.inkubator.hrm.service.AppraisalPerformanceGroupService;
import com.inkubator.hrm.service.AppraisalPerformanceIndicatorJabatanService;
import com.inkubator.hrm.service.JabatanService;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesUtil;
import com.inkubator.webcore.util.MessagesResourceUtil;

/**
 *
 * @author rizkykojek
 */
@ManagedBean(name = "performanceIndicatorJabatanFormController")
@ViewScoped
public class PerformanceIndicatorJabatanFormController extends BaseController{
	
	private Boolean isUpdate;
    private Jabatan jabatan;
    private Map<Long, Long> mapIndicatorScoreIndex = new HashMap<>(); // detail for -> Map(appraisalPerformanceIndicatorId, systemScoringIndexId)
    private List<AppraisalPerformanceGroup> listPerformanceGroup;
    
    @ManagedProperty(value = "#{jabatanService}")
    private JabatanService jabatanService;
    @ManagedProperty(value = "#{appraisalPerformanceIndicatorJabatanService}")
    private AppraisalPerformanceIndicatorJabatanService appraisalPerformanceIndicatorJabatanService;
    @ManagedProperty(value = "#{appraisalPerformanceGroupService}")
    private AppraisalPerformanceGroupService appraisalPerformanceGroupService;
     
    @PostConstruct
    @Override
    public void initialization(){
        super.initialization();
        try{
            isUpdate = Boolean.FALSE;
            
            String id = FacesUtil.getRequestParameter("execution");
            if(StringUtils.isNotEmpty(id)){
            	jabatan = jabatanService.getJabatanByIdWithDetail(Long.parseLong(id.substring(1)));
            	listPerformanceGroup = appraisalPerformanceGroupService.getAllDataFetchPerformanceIndicatorAndScoringIndex();

            	List<AppraisalPerformanceIndicatorJabatan>  listPerformanceIndicator = appraisalPerformanceIndicatorJabatanService.getAllDataByJabatanIdFetchScoringIndex(jabatan.getId());
	            if(listPerformanceIndicator.size() > 0) {
	            	isUpdate = Boolean.TRUE;
	            	for(AppraisalPerformanceIndicatorJabatan appraisalPerformanceIndicatorJabatan : listPerformanceIndicator){
		               	mapIndicatorScoreIndex.put(appraisalPerformanceIndicatorJabatan.getPerformanceIndicator().getId(), appraisalPerformanceIndicatorJabatan.getSystemScoringIndex().getId());
		            }
	            }
            }
            
        } catch (Exception e){
            LOGGER.error("error", e);
        }
    }
    
    @PreDestroy
    public void cleanAndExit(){
    	isUpdate = null; 
    	jabatan = null; 
    	mapIndicatorScoreIndex = null; 
    	listPerformanceGroup = null;
    	jabatanService = null; 
    	appraisalPerformanceIndicatorJabatanService = null; 
    	appraisalPerformanceGroupService = null;
    }
    
    public String doSave(){
    	try {
    		appraisalPerformanceIndicatorJabatanService.saveOrUpdate(jabatan.getId(), mapIndicatorScoreIndex);
			
    		if (isUpdate) {
				MessagesResourceUtil.setMessagesFlas(FacesMessage.SEVERITY_INFO, "global.save_info", "global.update_successfully",
	                    FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());			    
			} else {
				MessagesResourceUtil.setMessagesFlas(FacesMessage.SEVERITY_INFO, "global.save_info", "global.added_successfully",
	                    FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());				
			}
			
			return "/protected/appraisal/performance_indicator_jabatan_view.htm?faces-redirect=true";
		} catch (Exception e) {
        	LOGGER.error("error", e);
		}
    	
    	return null;
    }
    
	public String doBack(){
		return "/protected/appraisal/performance_indicator_jabatan_view.htm?faces-redirect=true";
	}

	public Boolean getIsUpdate() {
		return isUpdate;
	}

	public void setIsUpdate(Boolean isUpdate) {
		this.isUpdate = isUpdate;
	}

	public Jabatan getJabatan() {
		return jabatan;
	}

	public void setJabatan(Jabatan jabatan) {
		this.jabatan = jabatan;
	}

	public Map<Long, Long> getMapIndicatorScoreIndex() {
		return mapIndicatorScoreIndex;
	}

	public void setMapIndicatorScoreIndex(Map<Long, Long> mapIndicatorScoreIndex) {
		this.mapIndicatorScoreIndex = mapIndicatorScoreIndex;
	}

	public List<AppraisalPerformanceGroup> getListPerformanceGroup() {
		return listPerformanceGroup;
	}

	public void setListPerformanceGroup(List<AppraisalPerformanceGroup> listPerformanceGroup) {
		this.listPerformanceGroup = listPerformanceGroup;
	}

	public JabatanService getJabatanService() {
		return jabatanService;
	}

	public void setJabatanService(JabatanService jabatanService) {
		this.jabatanService = jabatanService;
	}

	public AppraisalPerformanceIndicatorJabatanService getAppraisalPerformanceIndicatorJabatanService() {
		return appraisalPerformanceIndicatorJabatanService;
	}

	public void setAppraisalPerformanceIndicatorJabatanService(
			AppraisalPerformanceIndicatorJabatanService appraisalPerformanceIndicatorJabatanService) {
		this.appraisalPerformanceIndicatorJabatanService = appraisalPerformanceIndicatorJabatanService;
	}

	public AppraisalPerformanceGroupService getAppraisalPerformanceGroupService() {
		return appraisalPerformanceGroupService;
	}

	public void setAppraisalPerformanceGroupService(AppraisalPerformanceGroupService appraisalPerformanceGroupService) {
		this.appraisalPerformanceGroupService = appraisalPerformanceGroupService;
	}
	
}
