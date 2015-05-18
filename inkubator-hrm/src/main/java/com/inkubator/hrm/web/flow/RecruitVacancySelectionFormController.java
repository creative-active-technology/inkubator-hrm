/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.flow;

import com.inkubator.hrm.entity.RecruitHireApply;
import com.inkubator.hrm.entity.RecruitSelectionType;
import com.inkubator.hrm.entity.RecruitmenSelectionSeriesDetail;
import com.inkubator.hrm.service.RecruitHireApplyService;
import com.inkubator.hrm.service.RecruitSelectionTypeService;
import com.inkubator.hrm.service.RecruitmenSelectionSeriesDetailService;
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
        String tipeKaryawan = "";
        RecruitVacancySelectionModel recruitVacancySelectionModel = (RecruitVacancySelectionModel) context.getFlowScope().get("recruitVacancySelectionModel");
        Long recruitHireApplyId = recruitVacancySelectionModel.getRecruitHireApplyId();
        System.out.println(recruitHireApplyId + " hohohohohoho");
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
        RecruitVacancySelectionDetailModel recruitVacancySelectionDetailModel = new RecruitVacancySelectionDetailModel();
        for (RecruitmenSelectionSeriesDetail recruitmenSelectionSeriesDetail : listVacancySelectionDetail) {
            recruitVacancySelectionDetailModel.setRecruitSelectionSeriesName(recruitmenSelectionSeriesDetail.getRecruitmenSelectionSeries().getName());
            listVacancySelectionDetailToShow.add(recruitVacancySelectionDetailModel);
        }
        recruitVacancySelectionModel.setListVacancySelectionDetail(listVacancySelectionDetailToShow);
    }
    
    public void saveRecruitmentDetail(RequestContext context) throws Exception {
        System.out.println("hahahihihuhuhehehoho");
    }
    
    public void doAdd(){
        System.out.println("hohohoho");
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
