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
import com.inkubator.hrm.entity.RecruitmenSelectionSeries;
import com.inkubator.hrm.entity.RecruitmenSelectionSeriesDetail;
import com.inkubator.hrm.service.EmpDataService;
import com.inkubator.hrm.service.RecruitHireApplyService;
import com.inkubator.hrm.service.RecruitSelectionTypeService;
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
	
	/*
	 * Insert value for dropdown recruit vacancy selection and recruit hire
	 * apply
	 */
	public RecruitVacancySelectionModel initSearchRecruitVacancySelectionFormFlow(
			RequestContext context) throws Exception {
		RecruitVacancySelectionModel recruitVacancySelectionModel = new RecruitVacancySelectionModel();
		List<RecruitmenSelectionSeries> listRecruitmenSelectionSeries = new ArrayList<RecruitmenSelectionSeries>();
		List<RecruitHireApply> listRecruitHireApply = new ArrayList<RecruitHireApply>();
		Map<String, Long> dropDownRecruitHireApply = new TreeMap<String, Long>();
		Map<String, Long> dropDownRecruitSelectionSeries = new TreeMap<String, Long>();

		listRecruitmenSelectionSeries = recruitmenSelectionSeriesService.getAllData();
		listRecruitHireApply = recruitHireApplyService.getAllData();
		for (RecruitHireApply recruitHireApply : listRecruitHireApply) {
			dropDownRecruitHireApply.put(recruitHireApply.getReqHireCode()
					+ " - " + recruitHireApply.getReason(),
					recruitHireApply.getId());
		}

		for (RecruitmenSelectionSeries recruitSelectionSeries : listRecruitmenSelectionSeries) {
			dropDownRecruitSelectionSeries.put(recruitSelectionSeries.getName(),
					recruitSelectionSeries.getId());
		}
		recruitVacancySelectionModel
				.setDropDownRecruitHireApply(dropDownRecruitHireApply);
		recruitVacancySelectionModel
				.setDropDownRecruitSelectionSeries(dropDownRecruitSelectionSeries);
		return recruitVacancySelectionModel;
	}

	/*
	 * Method for update label jobTitle, EffectiveDate and StaffName
	 */
	public void updateDetailLabelRekrutment(RequestContext context)
			throws Exception {
		RecruitVacancySelectionModel recruitVacancySelectionModel = (RecruitVacancySelectionModel) context
				.getFlowScope().get("recruitVacancySelectionModel");
		Long recruitHireApplyId = recruitVacancySelectionModel
				.getRecruitHireApplyId();
		RecruitHireApply recruitHireApply = recruitHireApplyService
				.getEntityByPkWithDetail(recruitHireApplyId);
		recruitVacancySelectionModel.setJobTitleName(recruitHireApply
				.getJabatan().getName());
		recruitVacancySelectionModel.setEffectiveDate(recruitHireApply
				.getEfectiveDate());
		recruitVacancySelectionModel.setStaffName(recruitHireApply
				.getEmployeeType().getName());
		recruitVacancySelectionModel.setRecruitHireApplyName(recruitHireApply
				.getReason());
		context.getFlowScope().put("recruitVacancySelectionModel",
				recruitVacancySelectionModel);
	}

	/*
	 * Methode for get List Recruit Selection Series Detail with LazyData by
	 * SelectionType Id
	 */
	public String getRecruitVacancySelectionDetail(RequestContext context) throws Exception {
		System.out.println("getRecruitVacancySelectionDetail");
		String condition = "yes";
		RecruitVacancySelectionModel recruitVacancySelectionModel = (RecruitVacancySelectionModel) context.getFlowScope().get("recruitVacancySelectionModel");
		List<RecruitmenSelectionSeriesDetail> listVacancySelectionDetail = recruitmenSelectionSeriesDetailService.getEntityBySelectionSeriesId(recruitVacancySelectionModel.getRecruitSelectionSeriesId());
		List<RecruitVacancySelectionDetailModel> listVacancySelectionDetailToShow = new ArrayList<>();
		RecruitVacancySelectionDetailModel recruitVacancySelectionDetailModel;
		for (RecruitmenSelectionSeriesDetail recruitmenSelectionSeriesDetail : listVacancySelectionDetail) {
			recruitVacancySelectionDetailModel = new RecruitVacancySelectionDetailModel();
			recruitVacancySelectionDetailModel.setRecruitSelectionTypeName(recruitmenSelectionSeriesDetail.getRecruitSelectionType().getName());
			listVacancySelectionDetailToShow.add(recruitVacancySelectionDetailModel);
		}
		recruitVacancySelectionModel.setListVacancySelectionDetail(listVacancySelectionDetailToShow);
		
		context.getFlashScope().put("recruitmenSelectionSeriesDetailService", recruitVacancySelectionModel);
		return condition;
	}

	/*
	 * Save List Selected Employee every Selection Series Data
	 */
	public void saveListEmployee(RequestContext context) throws Exception {
		org.primefaces.context.RequestContext contextPrime = FacesUtil
				.getRequestContext();
		Boolean listEmpExist = Boolean.FALSE;
		String employeeName = "";
		RecruitVacancySelectionModel recruitVacancySelectionModel = (RecruitVacancySelectionModel) context
				.getFlowScope().get("recruitVacancySelectionModel");
		List<RecruitVacancySelectionDetailModel> newData = new ArrayList<RecruitVacancySelectionDetailModel>();
		/*for (RecruitVacancySelectionDetailModel data : recruitVacancySelectionModel.getListVacancySelectionDetail()) {
			if (data.getRecruitSelectionSeriesName().equals(recruitVacancySelectionModel.getSelectedVacSelectionDetailModel().getRecruitSelectionSeriesName())) {
				System.out.println(data.getStartDate() + " startDate");
				// data.setListEmployeeId(recruitVacancySelectionModel.getListEmployeeId());
				data.setListEmpData(recruitVacancySelectionModel.getSelectedEmpData());
				newData.add(data);
			} else {
				newData.add(data);
			}
		}*/
//		recruitVacancySelectionModel.setListVacancySelectionDetail(newData);
//		context.getFlowScope().put("recruitVacancySelectionModel",recruitVacancySelectionModel);
		// condition for close dialog pop-up if listEmpExist not empty
		System.out.println(recruitVacancySelectionModel.getSelectedVacSelectionDetailModel().getStartDate() + " selected");
		recruitVacancySelectionModel.getSelectedVacSelectionDetailModel().setListEmpData(recruitVacancySelectionModel.getSelectedEmpData());
		System.out.println(recruitVacancySelectionModel.getSelectedVacSelectionDetailModel().getListEmpData().size() + " sizeeeeee");
		if (recruitVacancySelectionModel.getSelectedEmpData().isEmpty()) {
			System.out.println("error shake");
			MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_INFO,
					"global.error", "error.email_not_registered", FacesUtil
							.getSessionAttribute(HRMConstant.BAHASA_ACTIVE)
							.toString());
		} else {
			System.out.println("berhasil");
			listEmpExist = Boolean.TRUE;
		}
		contextPrime.addCallbackParam("listEmpExist", listEmpExist);
		
		
	}

	public void doGetListEmployee(RequestContext context) throws Exception{
		RecruitVacancySelectionModel recruitVacancySelectionModel = (RecruitVacancySelectionModel) context
				.getFlowScope().get("recruitVacancySelectionModel");
		System.out.println(recruitVacancySelectionModel.getSelectedVacSelectionDetailModel().getStartDate() + " selected");
		List<EmpData> listEmpData = empDataService.getAllDataNotTerminateWithSearchParameter(recruitVacancySelectionModel.getNikOrNameSearchParameter());
		recruitVacancySelectionModel.setListEmpData(listEmpData);
	}
	
	public void saveRecruitmentDetail(RequestContext context) throws Exception {
		RecruitVacancySelectionModel recruitVacancySelectionModel = (RecruitVacancySelectionModel) context
				.getFlowScope().get("recruitVacancySelectionModel");
		if(recruitVacancySelectionModel != null){
			recruitVacancySelectionService.saveRecruitVacancySelectionSeries(recruitVacancySelectionModel);
		}
	}

	public void onDialogReturn(SelectEvent event) {
		String condition = (String) event.getObject();
		if (condition.equalsIgnoreCase(WebCoreConstant.SAVE_CONDITION)) {
			MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_INFO,
					"global.save_info", "global.added_successfully", FacesUtil
							.getSessionAttribute(WebCoreConstant.BAHASA_ACTIVE)
							.toString());
		}
		if (condition.equalsIgnoreCase(WebCoreConstant.UPDATE_CONDITION)) {
			MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_INFO,
					"global.save_info", "global.update_successfully", FacesUtil
							.getSessionAttribute(WebCoreConstant.BAHASA_ACTIVE)
							.toString());
		}

	}
}
