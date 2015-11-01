/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.flow;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.faces.application.FacesMessage;

import org.primefaces.event.SelectEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import org.springframework.webflow.execution.RequestContext;

import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.entity.EmpData;
import com.inkubator.hrm.entity.RecruitHireApply;
import com.inkubator.hrm.entity.RecruitVacancySelection;
import com.inkubator.hrm.entity.RecruitVacancySelectionDetail;
import com.inkubator.hrm.entity.RecruitmenSelectionSeries;
import com.inkubator.hrm.entity.RecruitmenSelectionSeriesDetail;
import com.inkubator.hrm.service.EmpDataService;
import com.inkubator.hrm.service.RecruitHireApplyService;
import com.inkubator.hrm.service.RecruitSelectionTypeService;
import com.inkubator.hrm.service.RecruitVacancySelectionDetailService;
import com.inkubator.hrm.service.RecruitVacancySelectionService;
import com.inkubator.hrm.service.RecruitmenSelectionSeriesDetailService;
import com.inkubator.hrm.service.RecruitmenSelectionSeriesService;
import com.inkubator.hrm.web.model.RecruitVacancySelectionDetailModel;
import com.inkubator.hrm.web.model.RecruitVacancySelectionModel;
import com.inkubator.webcore.WebCoreConstant;
import com.inkubator.webcore.util.FacesUtil;
import com.inkubator.webcore.util.MessagesResourceUtil;

/*--------------------------------------------------------------------
 *  Author:        Deni
 *  Written:       12/5/2015
 *  Finish:        -
 *  Last updated:  -
 *
 *  
 * 
 *
 *
 *--------------------------------------------------------------------*/
@Component(value = "recruitVacancySelectionFormController")
@Lazy
public class RecruitVacancySelectionFormController implements Serializable {

	org.apache.log4j.Logger LOGGER = org.apache.log4j.Logger
			.getLogger(getClass());

	@Autowired
	private RecruitSelectionTypeService recruitSelectionTypeService;
	@Autowired
	private RecruitHireApplyService recruitHireApplyService;
	@Autowired
	private RecruitmenSelectionSeriesDetailService recruitmenSelectionSeriesDetailService;
	@Autowired
	private EmpDataService empDataService;
	@Autowired
	private RecruitVacancySelectionService recruitVacancySelectionService;
	@Autowired
	private RecruitmenSelectionSeriesService recruitmenSelectionSeriesService;
	@Autowired
	private RecruitVacancySelectionDetailService recruitVacancySelectionDetailService;
	
	/*
	 * Insert value for dropdown recruit vacancy selection and recruit hire
	 * apply
	 */
	public RecruitVacancySelectionModel initSearchRecruitVacancySelectionFormFlow(RequestContext context) throws Exception {
		//inisialisasi variable
		RecruitVacancySelectionModel recruitVacancySelectionModel = new RecruitVacancySelectionModel();
		Map<String, Long> dropDownRecruitHireApply = new TreeMap<String, Long>();
		Map<String, Long> dropDownRecruitSelectionSeries = new TreeMap<String, Long>();
		
		/*set value if param != null means edit
		* else skip this condition
		*/
		if (context.getFlowScope().get("param") != null) {
            Long id = Long.parseLong(context.getFlowScope().get("param").toString());
            RecruitVacancySelection entity = recruitVacancySelectionService.getEntityByPkWithDetail(Long.valueOf(id));
            // step 1
            recruitVacancySelectionModel.setId(id);
            recruitVacancySelectionModel.setCode(entity.getCode());
            recruitVacancySelectionModel.setRecruitVacancySelectionDate(entity.getRecruitVacancySelectionDate());
            recruitVacancySelectionModel.setRecruitHireApplyId(entity.getRecruitHireApply().getId());
            recruitVacancySelectionModel.setJobTitleName(entity.getRecruitHireApply().getJabatan().getName());
    		recruitVacancySelectionModel.setEffectiveDate(entity.getRecruitHireApply().getEfectiveDate());
    		recruitVacancySelectionModel.setStaffName(entity.getRecruitHireApply().getEmployeeType().getName());
    		recruitVacancySelectionModel.setRecruitHireApplyName(entity.getRecruitHireApply().getReason());
    		recruitVacancySelectionModel.setExtraBudget(entity.getExtraBudget());
    		recruitVacancySelectionModel.setRecruitSelectionSeriesId(entity.getRecruitmenSelectionSeries().getId());
    		recruitVacancySelectionModel.setExistingRecruitSelectionSeriesId(entity.getRecruitmenSelectionSeries().getId());
    		//step 2
    		List<RecruitVacancySelectionDetail> entityDetail = recruitVacancySelectionDetailService.getAllDataByRecruitVacancySelection(Long.valueOf(id));
            List<RecruitVacancySelectionDetailModel> listVacancySelectionDetailToShow = new ArrayList<>();
    		RecruitVacancySelectionDetailModel recruitVacancySelectionDetailModel;
    		for (RecruitVacancySelectionDetail recruitVacancySelectionDetail : entityDetail) {
    			recruitVacancySelectionDetailModel = new RecruitVacancySelectionDetailModel();
    			recruitVacancySelectionDetailModel.setId(recruitVacancySelectionDetail.getId());
    			recruitVacancySelectionDetailModel.setRecruitSelectionTypeName(recruitVacancySelectionDetail.getRecruitSelectionType().getName());
    			recruitVacancySelectionDetailModel.setStartDate(recruitVacancySelectionDetail.getStartDate());
    			recruitVacancySelectionDetailModel.setEndDate(recruitVacancySelectionDetail.getEndDate());
    			recruitVacancySelectionDetailModel.setTime(recruitVacancySelectionDetail.getTime());
    			recruitVacancySelectionDetailModel.setPlace(recruitVacancySelectionDetail.getPlace());
    			recruitVacancySelectionDetailModel.setListEmpData(recruitVacancySelectionDetail.getListEmpData());
    			recruitVacancySelectionModel.setSelectedEmpData(recruitVacancySelectionDetail.getListEmpData());
    			recruitVacancySelectionDetailModel.setIndividualCost(recruitVacancySelectionDetail.getIndividualCost());
    			recruitVacancySelectionDetailModel.setBasicCost(recruitVacancySelectionDetail.getBasicCost());
    			listVacancySelectionDetailToShow.add(recruitVacancySelectionDetailModel);
    		}
    		recruitVacancySelectionModel.setListVacancySelectionDetail(listVacancySelectionDetailToShow);
		}

		//set list dropdown for recruit hire apply and selection series
		List<RecruitmenSelectionSeries> listRecruitmenSelectionSeries = recruitmenSelectionSeriesService.getAllData();
		List<RecruitHireApply> listRecruitHireApply = recruitHireApplyService.getAllData();
		for (RecruitHireApply recruitHireApply : listRecruitHireApply) {
			dropDownRecruitHireApply.put(recruitHireApply.getReqHireCode() + " - " + recruitHireApply.getReason(), recruitHireApply.getId());
		}

		for (RecruitmenSelectionSeries recruitSelectionSeries : listRecruitmenSelectionSeries) {
			dropDownRecruitSelectionSeries.put(recruitSelectionSeries.getName(),
					recruitSelectionSeries.getId());
		}
		recruitVacancySelectionModel.setDropDownRecruitHireApply(dropDownRecruitHireApply);
		recruitVacancySelectionModel.setDropDownRecruitSelectionSeries(dropDownRecruitSelectionSeries);
		return recruitVacancySelectionModel;
	}

	/*
	 * Method for update label jobTitle, EffectiveDate and StaffName when hireApply when user choose selected hireApply in step 1
	 */
	public void updateDetailLabelRekrutment(RequestContext context) throws Exception {
		RecruitVacancySelectionModel recruitVacancySelectionModel = (RecruitVacancySelectionModel) context.getFlowScope().get("recruitVacancySelectionModel");
		Long recruitHireApplyId = recruitVacancySelectionModel.getRecruitHireApplyId();
		RecruitHireApply recruitHireApply = recruitHireApplyService.getEntityByPkWithDetail(recruitHireApplyId);
		recruitVacancySelectionModel.setJobTitleName(recruitHireApply.getJabatan().getName());
		recruitVacancySelectionModel.setEffectiveDate(recruitHireApply.getEfectiveDate());
		recruitVacancySelectionModel.setStaffName(recruitHireApply.getEmployeeType().getName());
		recruitVacancySelectionModel.setRecruitHireApplyName(recruitHireApply.getReason());
		context.getFlowScope().put("recruitVacancySelectionModel", recruitVacancySelectionModel);
	}

	/*
	 * Methode to step 2
	 */
	public String getRecruitVacancySelectionDetail(RequestContext context) throws Exception {
		String condition = "yes";
		RecruitVacancySelectionModel recruitVacancySelectionModel = (RecruitVacancySelectionModel) context.getFlowScope().get("recruitVacancySelectionModel");
		/*
		 *  check if listVacancySelectionDetail Empty or not
		 *  if empty means add
		 *  if not empty means edit
		 */
		List<RecruitVacancySelectionDetailModel> listVacancySelectionDetailToShow = new ArrayList<>();
		
		/*
		 * if = for create new data 
		 * else = for update / edit data
		 */
		if(recruitVacancySelectionModel.getId() == null && recruitVacancySelectionModel.getExistingRecruitSelectionSeriesId() == null){
			List<RecruitmenSelectionSeriesDetail> listVacancySelectionDetail = recruitmenSelectionSeriesDetailService.getListBySelectionSeriesId(recruitVacancySelectionModel.getRecruitSelectionSeriesId());
			RecruitVacancySelectionDetailModel recruitVacancySelectionDetailModel;
			for (RecruitmenSelectionSeriesDetail recruitmenSelectionSeriesDetail : listVacancySelectionDetail) {
				recruitVacancySelectionDetailModel = new RecruitVacancySelectionDetailModel();
				recruitVacancySelectionDetailModel.setRecruitSelectionTypeName(recruitmenSelectionSeriesDetail.getRecruitSelectionType().getName());
				listVacancySelectionDetailToShow.add(recruitVacancySelectionDetailModel);
			}
			recruitVacancySelectionModel.setListVacancySelectionDetail(listVacancySelectionDetailToShow);
			recruitVacancySelectionModel.setIsUpdatedNewTypeSelection(Boolean.TRUE);
		}else if(recruitVacancySelectionModel.getId() != null && recruitVacancySelectionModel.getExistingRecruitSelectionSeriesId() != null){
			/*
			 * if = for update data with different recruitSelectionSeries
			 * else = for update data with same recruitSelectionSeries
			 */
			if(!recruitVacancySelectionModel.getExistingRecruitSelectionSeriesId().equals(recruitVacancySelectionModel.getRecruitSelectionSeriesId())){
				List<RecruitmenSelectionSeriesDetail> listVacancySelectionDetail = recruitmenSelectionSeriesDetailService.getListBySelectionSeriesId(recruitVacancySelectionModel.getRecruitSelectionSeriesId());
				RecruitVacancySelectionDetailModel recruitVacancySelectionDetailModel;
				for (RecruitmenSelectionSeriesDetail recruitmenSelectionSeriesDetail : listVacancySelectionDetail) {
					recruitVacancySelectionDetailModel = new RecruitVacancySelectionDetailModel();
					recruitVacancySelectionDetailModel.setRecruitSelectionTypeName(recruitmenSelectionSeriesDetail.getRecruitSelectionType().getName());
					listVacancySelectionDetailToShow.add(recruitVacancySelectionDetailModel);
				}
				recruitVacancySelectionModel.setListVacancySelectionDetail(listVacancySelectionDetailToShow);
				recruitVacancySelectionModel.setIsUpdatedNewTypeSelection(Boolean.TRUE);
			}else{
				recruitVacancySelectionModel.setIsUpdatedNewTypeSelection(Boolean.FALSE);
				List<RecruitVacancySelectionDetail> entityDetail = recruitVacancySelectionDetailService.getAllDataByRecruitVacancySelection(recruitVacancySelectionModel.getId());
	    		RecruitVacancySelectionDetailModel recruitVacancySelectionDetailModel;
	    		for (RecruitVacancySelectionDetail recruitVacancySelectionDetail : entityDetail) {
	    			recruitVacancySelectionDetailModel = new RecruitVacancySelectionDetailModel();
	    			recruitVacancySelectionDetailModel.setId(recruitVacancySelectionDetail.getId());
	    			recruitVacancySelectionDetailModel.setRecruitSelectionTypeName(recruitVacancySelectionDetail.getRecruitSelectionType().getName());
	    			recruitVacancySelectionDetailModel.setStartDate(recruitVacancySelectionDetail.getStartDate());
	    			recruitVacancySelectionDetailModel.setEndDate(recruitVacancySelectionDetail.getEndDate());
	    			recruitVacancySelectionDetailModel.setTime(recruitVacancySelectionDetail.getTime());
	    			recruitVacancySelectionDetailModel.setPlace(recruitVacancySelectionDetail.getPlace());
	    			recruitVacancySelectionDetailModel.setListEmpData(recruitVacancySelectionDetail.getListEmpData());
	    			recruitVacancySelectionDetailModel.setIndividualCost(recruitVacancySelectionDetail.getIndividualCost());
	    			recruitVacancySelectionDetailModel.setBasicCost(recruitVacancySelectionDetail.getBasicCost());
	    			listVacancySelectionDetailToShow.add(recruitVacancySelectionDetailModel);
	    		}
			}
    		recruitVacancySelectionModel.setListVacancySelectionDetail(listVacancySelectionDetailToShow);
		}	
		context.getFlashScope().put("recruitmenSelectionSeriesDetailService", recruitVacancySelectionModel);
		return condition;
	}

	/*
	 * Save List Selected Employee every Selection Type Data
	 */
	public void saveListEmployee(RequestContext context) throws Exception {
		org.primefaces.context.RequestContext contextPrime = FacesUtil.getRequestContext();
		Boolean listEmpExist = Boolean.FALSE;
		RecruitVacancySelectionModel recruitVacancySelectionModel = (RecruitVacancySelectionModel) context.getFlowScope().get("recruitVacancySelectionModel");
		// condition for close dialog pop-up if listEmpExist not empty
		recruitVacancySelectionModel.getSelectedVacSelectionDetailModel().setListEmpData(recruitVacancySelectionModel.getSelectedEmpData());
		if (recruitVacancySelectionModel.getSelectedEmpData().isEmpty()) {
			MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_INFO, "global.error", "error.email_not_registered", FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
		} else {
			listEmpExist = Boolean.TRUE;
		}
		contextPrime.addCallbackParam("listEmpExist", listEmpExist);
	}

	/*
	 * get list employee is not terminate
	 */
	public void doGetListEmployee(RequestContext context) throws Exception{
		RecruitVacancySelectionModel recruitVacancySelectionModel = (RecruitVacancySelectionModel) context.getFlowScope().get("recruitVacancySelectionModel");
		List<EmpData> listEmpData = empDataService.getAllDataNotTerminateWithSearchParameter(recruitVacancySelectionModel.getNikOrNameSearchParameter());
		recruitVacancySelectionModel.setListEmpData(listEmpData);
	}
	
	public void saveRecruitmentDetail(RequestContext context) throws Exception {
		RecruitVacancySelectionModel recruitVacancySelectionModel = (RecruitVacancySelectionModel) context.getFlowScope().get("recruitVacancySelectionModel");
		if(recruitVacancySelectionModel.getId() == null){
			recruitVacancySelectionService.saveRecruitVacancySelectionSeries(recruitVacancySelectionModel);
		}else{
			recruitVacancySelectionService.updateRecruitVacancySelectionSeries(recruitVacancySelectionModel);
		}
	}
	
	/*
	 * method init for dialog employee selectors
	 */
	public String initEmployeeSelectors(RequestContext context) throws Exception {
		String condition = "no";
		RecruitVacancySelectionModel recruitVacancySelectionModel = (RecruitVacancySelectionModel) context.getFlowScope().get("recruitVacancySelectionModel");
		List<EmpData> listEmpData = new ArrayList<EmpData>();
		if(recruitVacancySelectionModel.getId() != null){
			condition = "yes";
			listEmpData = recruitVacancySelectionModel.getSelectedEmpData();
		}

		recruitVacancySelectionModel.setListEmpData(listEmpData);
		recruitVacancySelectionModel.setSelectedEmpData(listEmpData);
		return condition;
	}
}
