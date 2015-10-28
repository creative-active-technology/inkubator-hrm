/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.recruitment;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.LazyDataModel;

import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.entity.EmployeeType;
import com.inkubator.hrm.entity.RecruitApplicant;
import com.inkubator.hrm.entity.RecruitMppApply;
import com.inkubator.hrm.entity.RecruitMppApplyDetail;
import com.inkubator.hrm.entity.ReimbursmentSchemaEmployeeType;
import com.inkubator.hrm.service.JabatanService;
import com.inkubator.hrm.service.RecruitApplicantService;
import com.inkubator.hrm.service.RecruitMppApplyDetailService;
import com.inkubator.hrm.service.RecruitMppApplyService;
import com.inkubator.hrm.service.RecruitSelectionApplicantInitialService;
import com.inkubator.hrm.web.lazymodel.RecruitInitialLazyDataModel;
import com.inkubator.hrm.web.model.RecruitInitialSelectionModel;
import com.inkubator.hrm.web.search.RecruitInitialSelectionSearchParameter;
import com.inkubator.securitycore.util.UserInfoUtil;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesUtil;
import com.inkubator.webcore.util.MessagesResourceUtil;

/**
 *
 * @author Deni
 */
@ManagedBean(name = "recruitInitialSelectionFormController")
@ViewScoped
public class RecruitInitialSelectionFormController extends BaseController {
    @ManagedProperty(value = "#{recruitApplicantService}")
    private RecruitApplicantService recruitApplicantService;
    @ManagedProperty(value = "#{recruitMppApplyService}")
    private RecruitMppApplyService recruitMppApplyService;
    @ManagedProperty(value = "#{recruitMppApplyDetailService}")
    private RecruitMppApplyDetailService recruitMppApplyDetailService;
    @ManagedProperty(value = "#{jabatanService}")
    private JabatanService jabatanService;
    @ManagedProperty(value = "#{recruitSelectionApplicantInitialService}")
    private RecruitSelectionApplicantInitialService recruitSelectionApplicantInitialService;
    private LazyDataModel<RecruitApplicant> lazyDataModel;
    private Map<String, Long> dropDownRecruitHireApply = new TreeMap<String, Long>();
    private Map<String, Long> dropDownJabatan = new TreeMap<String, Long>();
    private RecruitInitialSelectionModel recruitInitialSelectionModel;
    private RecruitInitialSelectionSearchParameter searchParameter;
    private Boolean isUnCheckAll;
    private Boolean isCheckAll;
    
    @PostConstruct
    @Override
    public void initialization() {
        super.initialization();
        try{
        	recruitInitialSelectionModel = new RecruitInitialSelectionModel();
        	searchParameter = new RecruitInitialSelectionSearchParameter();
        	List<RecruitMppApply> listRecruitHireApply = new ArrayList<RecruitMppApply>();
            listRecruitHireApply = recruitMppApplyService.getAllData();
            for(RecruitMppApply recruitMppApply : listRecruitHireApply){
            	dropDownRecruitHireApply.put(recruitMppApply.getRecruitMppApplyName(), recruitMppApply.getId());
            }
            isCheckAll = Boolean.FALSE;
            isUnCheckAll = Boolean.TRUE;
        }catch (Exception e){
            LOGGER.error("Error", e);
        }
    }
    
    @PreDestroy
    public void cleanAndExit() {
    	recruitApplicantService = null;
    	recruitMppApplyService = null;
    	lazyDataModel = null;
    	dropDownRecruitHireApply = null;
    	dropDownJabatan = null;
    	recruitInitialSelectionModel = null;
    	jabatanService = null;
    	recruitMppApplyDetailService = null;
    	searchParameter = null;
    	recruitSelectionApplicantInitialService = null;
    }

    public void doCheckAll() throws Exception{
    	isCheckAll = Boolean.TRUE;
    	isUnCheckAll = Boolean.FALSE;
    	doCheckOrUnCheck(isCheckAll, isUnCheckAll);
    }
    
    public void doUnCheckAll() throws Exception{
    	isCheckAll = Boolean.FALSE;
    	isUnCheckAll = Boolean.TRUE;
    	doCheckOrUnCheck(isCheckAll, isUnCheckAll);
    }
    
    public void doCheckOrUnCheck(Boolean isCheck, Boolean isUncheck) throws Exception{

    	List<RecruitApplicant> listDataApplicant = recruitApplicantService.getAllData();
    	Map<Long, Boolean> selectedIds =  new HashMap<Long, Boolean>();
    	for(RecruitApplicant recruitApplicant : listDataApplicant){
    		if(isUncheck){
    			selectedIds.put(recruitApplicant.getId(), Boolean.FALSE);
    			recruitInitialSelectionModel.getSelectedIds().clear();;
    		}
    		if(isCheck){
    			selectedIds.put(recruitApplicant.getId(), Boolean.TRUE);
    			recruitInitialSelectionModel.setSelectedIds(selectedIds);
    		}
		}
    }
    
    public void doSave() throws Exception{
    	try {
    		recruitSelectionApplicantInitialService.save(recruitInitialSelectionModel.getListRecruitApplicantId());
            MessagesResourceUtil.setMessagesFlas(FacesMessage.SEVERITY_INFO, "global.save_info", "global.added_successfully",
                    FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
            /*lazyDataModel = null;*/
    } catch (Exception ex) {
        LOGGER.error("Error", ex);
    }
    	
    }
    
    public void doChangeJabatanDropDown() throws Exception{
    	dropDownJabatan.clear();
    	List<RecruitMppApplyDetail> listRecruitMppApplyDetail = recruitMppApplyDetailService.getAllDataJabatanByRecruitMppApplyId(searchParameter.getRecruitMppApplyId());
    	for(RecruitMppApplyDetail recruitMppApplyDetail : listRecruitMppApplyDetail){
    		dropDownJabatan.put(recruitMppApplyDetail.getJabatan().getName(), recruitMppApplyDetail.getJabatan().getId());
    	}
    }
    
    public void doSearch(){
    	lazyDataModel = null;
    }
    
	public RecruitApplicantService getRecruitApplicantService() {
		return recruitApplicantService;
	}

	public void setRecruitApplicantService(
			RecruitApplicantService recruitApplicantService) {
		this.recruitApplicantService = recruitApplicantService;
	}

	public RecruitMppApplyService getRecruitMppApplyService() {
		return recruitMppApplyService;
	}

	public void setRecruitMppApplyService(
			RecruitMppApplyService recruitMppApplyService) {
		this.recruitMppApplyService = recruitMppApplyService;
	}

	public LazyDataModel<RecruitApplicant> getLazyDataModel() {
		if(lazyDataModel == null){
			lazyDataModel = new RecruitInitialLazyDataModel(searchParameter, recruitApplicantService);
		}
		return lazyDataModel;
	}

	public void setLazyDataModel(LazyDataModel<RecruitApplicant> lazyDataModel) {
		this.lazyDataModel = lazyDataModel;
	}

	public Map<String, Long> getDropDownRecruitHireApply() {
		return dropDownRecruitHireApply;
	}

	public void setDropDownRecruitHireApply(
			Map<String, Long> dropDownRecruitHireApply) {
		this.dropDownRecruitHireApply = dropDownRecruitHireApply;
	}

	public RecruitMppApplyDetailService getRecruitMppApplyDetailService() {
		return recruitMppApplyDetailService;
	}

	public void setRecruitMppApplyDetailService(
			RecruitMppApplyDetailService recruitMppApplyDetailService) {
		this.recruitMppApplyDetailService = recruitMppApplyDetailService;
	}

	public JabatanService getJabatanService() {
		return jabatanService;
	}

	public void setJabatanService(JabatanService jabatanService) {
		this.jabatanService = jabatanService;
	}

	public Map<String, Long> getDropDownJabatan() {
		return dropDownJabatan;
	}

	public void setDropDownJabatan(Map<String, Long> dropDownJabatan) {
		this.dropDownJabatan = dropDownJabatan;
	}

	public RecruitInitialSelectionModel getRecruitInitialSelectionModel() {
		return recruitInitialSelectionModel;
	}

	public void setRecruitInitialSelectionModel(
			RecruitInitialSelectionModel recruitInitialSelectionModel) {
		this.recruitInitialSelectionModel = recruitInitialSelectionModel;
	}

	public RecruitInitialSelectionSearchParameter getSearchParameter() {
		return searchParameter;
	}

	public void setSearchParameter(
			RecruitInitialSelectionSearchParameter searchParameter) {
		this.searchParameter = searchParameter;
	}

	public Boolean getIsCheckAll() {
		return isCheckAll;
	}

	public void setIsCheckAll(Boolean isCheckAll) {
		this.isCheckAll = isCheckAll;
	}

	public Boolean getIsUnCheckAll() {
		return isUnCheckAll;
	}

	public void setIsUnCheckAll(Boolean isUnCheckAll) {
		this.isUnCheckAll = isUnCheckAll;
	}

	public RecruitSelectionApplicantInitialService getRecruitSelectionApplicantInitialService() {
		return recruitSelectionApplicantInitialService;
	}

	public void setRecruitSelectionApplicantInitialService(
			RecruitSelectionApplicantInitialService recruitSelectionApplicantInitialService) {
		this.recruitSelectionApplicantInitialService = recruitSelectionApplicantInitialService;
	}
    
    
}
