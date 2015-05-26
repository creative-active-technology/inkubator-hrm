/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.flow;

import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.entity.EmpData;
import com.inkubator.hrm.entity.RecruitHireApply;
import com.inkubator.hrm.entity.RecruitSelectionType;
import com.inkubator.hrm.entity.RecruitmenSelectionSeriesDetail;
import com.inkubator.hrm.service.EmpDataService;
import com.inkubator.hrm.service.RecruitHireApplyService;
import com.inkubator.hrm.service.RecruitSelectionTypeService;
import com.inkubator.hrm.service.RecruitmenSelectionSeriesDetailService;
import com.inkubator.hrm.web.lazymodel.RecruitVacancyEmployeeLazyDataModel;
import com.inkubator.hrm.web.model.RecruitVacancySelectionDetailModel;
import com.inkubator.hrm.web.model.RecruitVacancySelectionModel;
import com.inkubator.webcore.WebCoreConstant;
import com.inkubator.webcore.util.FacesUtil;
import com.inkubator.webcore.util.MessagesResourceUtil;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import javax.faces.application.FacesMessage;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.LazyDataModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import org.springframework.webflow.execution.RequestContext;

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

    org.apache.log4j.Logger LOGGER = org.apache.log4j.Logger.getLogger(getClass());

    @Autowired
    private RecruitSelectionTypeService recruitSelectionTypeService;
    @Autowired
    private RecruitHireApplyService recruitHireApplyService;
    @Autowired
    private RecruitmenSelectionSeriesDetailService recruitmenSelectionSeriesDetailService;
    @Autowired
    private EmpDataService empDataService;

    /*
     * Insert value for dropdown recruit vacancy selection and recruit hire apply   
     */
    public RecruitVacancySelectionModel initSearchRecruitVacancySelectionFormFlow(RequestContext context) throws Exception {
        RecruitVacancySelectionModel recruitVacancySelectionModel = new RecruitVacancySelectionModel();
        List<RecruitSelectionType> listRecruitSelectionType = new ArrayList<RecruitSelectionType>();
        List<RecruitHireApply> listRecruitHireApply = new ArrayList<RecruitHireApply>();
        Map<String, Long> dropDownRecruitHireApply = new TreeMap<String, Long>();;
        Map<String, Long> dropDownRecruitSelectionType = new TreeMap<String, Long>();;

        listRecruitSelectionType = recruitSelectionTypeService.getAllData();
        listRecruitHireApply = recruitHireApplyService.getAllData();
        for (RecruitHireApply recruitHireApply : listRecruitHireApply) {
            dropDownRecruitHireApply.put(recruitHireApply.getReqHireCode() + " - " + recruitHireApply.getReason(), recruitHireApply.getId());
        }
        
        for (RecruitSelectionType recruitSelectionType : listRecruitSelectionType) {
            dropDownRecruitSelectionType.put(recruitSelectionType.getName(), recruitSelectionType.getId());
        }
        recruitVacancySelectionModel.setDropDownRecruitHireApply(dropDownRecruitHireApply);
        recruitVacancySelectionModel.setDropDownRecruitSelectionType(dropDownRecruitSelectionType);
        return recruitVacancySelectionModel;
    }
    
    public void updateDetailLabelRekrutment(RequestContext context) throws Exception {
        RecruitVacancySelectionModel recruitVacancySelectionModel = (RecruitVacancySelectionModel) context.getFlowScope().get("recruitVacancySelectionModel");
        Long recruitHireApplyId = recruitVacancySelectionModel.getRecruitHireApplyId();
        RecruitHireApply recruitHireApply = recruitHireApplyService.getEntityByPkWithDetail(recruitHireApplyId);
        recruitVacancySelectionModel.setJobTitleName(recruitHireApply.getJabatan().getName());
        recruitVacancySelectionModel.setEffectiveDate(recruitHireApply.getEfectiveDate());
        recruitVacancySelectionModel.setStaffName(recruitHireApply.getEmployeeType().getName());
        recruitVacancySelectionModel.setRecruitHireApplyName(recruitHireApply.getReason());
    }
    
    public void getRecruitVacancySelectionDetail(RequestContext context) throws Exception{
        RecruitVacancySelectionModel recruitVacancySelectionModel = (RecruitVacancySelectionModel) context.getFlowScope().get("recruitVacancySelectionModel");
        List<RecruitmenSelectionSeriesDetail> listVacancySelectionDetail = recruitmenSelectionSeriesDetailService.getEntityBySelectionTypeId(recruitVacancySelectionModel.getRecruitSelectionTypeId());
        List<RecruitVacancySelectionDetailModel> listVacancySelectionDetailToShow = new ArrayList<>();
        RecruitVacancySelectionDetailModel recruitVacancySelectionDetailModel;
        LazyDataModel<EmpData> lazyDataModel = new RecruitVacancyEmployeeLazyDataModel(recruitVacancySelectionModel.getNikOrName(), empDataService);
        for (RecruitmenSelectionSeriesDetail recruitmenSelectionSeriesDetail : listVacancySelectionDetail) {
            recruitVacancySelectionDetailModel = new RecruitVacancySelectionDetailModel();
            recruitVacancySelectionDetailModel.setRecruitSelectionSeriesName(recruitmenSelectionSeriesDetail.getRecruitmenSelectionSeries().getName());
//            recruitVacancySelectionDetailModel.setLazyDataModel(lazyDataModel);
            listVacancySelectionDetailToShow.add(recruitVacancySelectionDetailModel);
        }
        List<EmpData> listEmpData = empDataService.getAllData();
        recruitVacancySelectionModel.setListEmpData(listEmpData);
//        recruitVacancySelectionModel.setLazyDataModel(lazyDataModel);
        recruitVacancySelectionModel.setListVacancySelectionDetail(listVacancySelectionDetailToShow);
    }
    
    public void saveListEmployee(RequestContext context) throws Exception{
        org.primefaces.context.RequestContext contextPrime = FacesUtil.getRequestContext();
        System.out.println("step 1");
        Boolean listEmpExist = Boolean.FALSE;
        RecruitVacancySelectionModel recruitVacancySelectionModel = (RecruitVacancySelectionModel) context.getFlowScope().get("recruitVacancySelectionModel");
        System.out.println("step 2 sebelum selected");
        System.out.println(recruitVacancySelectionModel.getSelectedVacSelectionDetailModel().getRecruitSelectionSeriesName());
        System.out.println("step 3 sesudah selected");
        List<RecruitVacancySelectionDetailModel> newData = new ArrayList<RecruitVacancySelectionDetailModel>();
        for (RecruitVacancySelectionDetailModel data : recruitVacancySelectionModel.getListVacancySelectionDetail()) {
            if(data.getRecruitSelectionSeriesName().equals(recruitVacancySelectionModel.getSelectedVacSelectionDetailModel().getRecruitSelectionSeriesName())){
                data.setListEmpData(recruitVacancySelectionModel.getSelectedListEmpData());
                data.setRecruitSelectionSeriesName("update selection series");
                System.out.println("aaa");
                newData.add(data);
            }
        }
        recruitVacancySelectionModel.setListVacancySelectionDetail(newData);
        context.getFlowScope().put("recruitVacancySelectionModel", recruitVacancySelectionModel);
        System.out.println("step akhir");
        System.out.println(recruitVacancySelectionModel.getListVacancySelectionDetail());
        if(recruitVacancySelectionModel.getSelectedListEmpData().isEmpty()){
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_INFO, "global.error", "error.email_not_registered",
                        FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
        }else{
            listEmpExist = Boolean.TRUE;
        }
        contextPrime.addCallbackParam("listEmpExist", listEmpExist);
    }
    

    public void saveRecruitmentDetail(RequestContext context) throws Exception{
        RecruitVacancySelectionModel recruitVacancySelectionModel = (RecruitVacancySelectionModel) context.getFlowScope().get("recruitVacancySelectionModel");
        for (RecruitVacancySelectionDetailModel data : recruitVacancySelectionModel.getListVacancySelectionDetail()) {
            System.out.println(data.getBasicCost() + " 1");
            System.out.println(data.getStartDate() + " 2");
            System.out.println(data.getEndDate() + " 3");
            System.out.println(data.getListEmpData() + " 4");
        }
    }
    
    public void onDialogReturn(SelectEvent event) {
        String condition = (String) event.getObject();
        if (condition.equalsIgnoreCase(WebCoreConstant.SAVE_CONDITION)) {
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_INFO, "global.save_info", "global.added_successfully",
                    FacesUtil.getSessionAttribute(WebCoreConstant.BAHASA_ACTIVE).toString());
        }
        if (condition.equalsIgnoreCase(WebCoreConstant.UPDATE_CONDITION)) {
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_INFO, "global.save_info", "global.update_successfully",
                    FacesUtil.getSessionAttribute(WebCoreConstant.BAHASA_ACTIVE).toString());
        }

    }
}
