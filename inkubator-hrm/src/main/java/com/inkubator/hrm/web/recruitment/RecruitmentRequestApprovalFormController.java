/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.recruitment;

import ch.lambdaj.Lambda;
import ch.lambdaj.group.Group;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.inkubator.common.util.DateFormatter;
import com.inkubator.common.util.RandomNumberUtil;
import com.inkubator.exception.BussinessException;
import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.entity.ApprovalActivity;
import com.inkubator.hrm.entity.Currency;
import com.inkubator.hrm.entity.EmpData;
import com.inkubator.hrm.entity.EmployeeType;
import com.inkubator.hrm.entity.HrmUser;
import com.inkubator.hrm.entity.Jabatan;
import com.inkubator.hrm.entity.OrgTypeOfSpec;
import com.inkubator.hrm.entity.OrgTypeOfSpecList;
import com.inkubator.hrm.entity.RecruitHireApply;
import com.inkubator.hrm.entity.RecruitHireApplyDetail;
import com.inkubator.hrm.entity.RecruitMppPeriod;
import com.inkubator.hrm.json.util.JsonUtil;
import com.inkubator.hrm.service.ApprovalActivityService;
import com.inkubator.hrm.service.CurrencyService;
import com.inkubator.hrm.service.EmpDataService;
import com.inkubator.hrm.service.EmployeeTypeService;
import com.inkubator.hrm.service.HrmUserService;
import com.inkubator.hrm.service.JabatanService;
import com.inkubator.hrm.service.OrgTypeOfSpecListService;
import com.inkubator.hrm.service.OrgTypeOfSpecService;
import com.inkubator.hrm.service.RecruitHireApplyService;
import com.inkubator.hrm.service.RecruitMppApplyDetailService;
import com.inkubator.hrm.service.RecruitMppPeriodService;
import com.inkubator.hrm.util.HrmUserInfoUtil;
import com.inkubator.hrm.web.model.RecruitHireApplyModel;
import com.inkubator.securitycore.util.UserInfoUtil;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesUtil;
import com.inkubator.webcore.util.MessagesResourceUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import org.apache.commons.lang3.StringUtils;
import org.hamcrest.Matchers;
import org.hamcrest.text.StringContains;
import org.primefaces.model.DualListModel;

/**
 *
 * @author Ahmad Mudzakkir Amal
 */
@ManagedBean(name = "recruitmentRequestApprovalFormController")
@ViewScoped
public class RecruitmentRequestApprovalFormController extends BaseController {

    @ManagedProperty(value = "#{orgTypeOfSpecListService}")
    private OrgTypeOfSpecListService orgTypeOfSpecListService;
    @ManagedProperty(value = "#{orgTypeOfSpecService}")
    private OrgTypeOfSpecService orgTypeOfSpecService;    
    @ManagedProperty(value = "#{empDataService}")
    private EmpDataService empDataService;
    @ManagedProperty(value = "#{recruitMppApplyDetailService}")
    private RecruitMppApplyDetailService recruitMppApplyDetailService;
    @ManagedProperty(value = "#{hrmUserService}")
    private HrmUserService hrmUserService;
    @ManagedProperty(value = "#{approvalActivityService}")
    private ApprovalActivityService approvalActivityService;
    @ManagedProperty(value = "#{recruitHireApplyService}")
    private RecruitHireApplyService recruitHireApplyService;
    private List<DualListModel> dataForRenders = new ArrayList<>();
    private Map<String, List<OrgTypeOfSpecList>> mapTypeList = new HashMap<>();
    private RecruitHireApplyModel model;
    private List<String> name = new ArrayList<>();    
    private String idApprovalActivity;
    private ApprovalActivity selectedApprovalActivity;
    private Boolean isWaitingApproval;
    private String comment;

    @PostConstruct
    @Override
    public void initialization() {
        try {

            model = new RecruitHireApplyModel();           

            idApprovalActivity = FacesUtil.getRequestParameter("execution").substring(1);            

            //if activityNumber is not empty, it means to edit existing Recruitment Request Data
            if (StringUtils.isNotBlank(idApprovalActivity)) {
                dataForRenders = orgTypeOfSpecListService.getAllBySpectJabatan();
                name = orgTypeOfSpecListService.getOrgTypeSpecName();
                selectedApprovalActivity = approvalActivityService.getEntiyByPK(Long.parseLong(idApprovalActivity));
                
                if (null != selectedApprovalActivity) {
                    isWaitingApproval = selectedApprovalActivity.getApprovalStatus() == HRMConstant.APPROVAL_STATUS_WAITING_APPROVAL;
                    //Make sure only process who have not been approved that can be modified.
                    if (selectedApprovalActivity.getApprovalStatus() != HRMConstant.APPROVAL_STATUS_APPROVED) {
                        model = convertJsonToModel(selectedApprovalActivity.getPendingData());
                    }
                }
            } 

        } catch (Exception ex) {
            LOGGER.error(ex, ex);
        }
    }

    @PreDestroy
    public void cleanAndExit() {        
        recruitMppApplyDetailService = null;
        empDataService = null;        
        approvalActivityService = null;
        hrmUserService = null;
        orgTypeOfSpecService = null;
        orgTypeOfSpecListService = null;        
        dataForRenders = null;
        name = null;
        idApprovalActivity = null;
        selectedApprovalActivity = null;
        isWaitingApproval = null;
        model = null;
        mapTypeList = null;
    }

    public String doBack() {
        cleanAndExit();
        return "/protected/recruitment/recruitment_req_history_view.htm?faces-redirect=true";
    }
    
   
    public String doApproved() {
        try {
        	recruitHireApplyService.approved(selectedApprovalActivity.getId(), null, comment);
            MessagesResourceUtil.setMessagesFlas(FacesMessage.SEVERITY_INFO, "global.approval_info", "global.approved_successfully",
                    FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
            return "/protected/recruitment/recruitment_req_history_view.htm?faces-redirect=true";
        } catch (BussinessException ex) {            
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_ERROR, "global.error", ex.getErrorKeyMessage(), FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
        } catch (Exception e) {
            LOGGER.error("Error ", e);
        }
        return null;
    }

    public String doRejected() {
        try {
        	recruitHireApplyService.rejected(selectedApprovalActivity.getId(), comment);
            MessagesResourceUtil.setMessagesFlas(FacesMessage.SEVERITY_INFO, "global.approval_info", "global.rejected_successfully",
                    FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
            return "/protected/recruitment/recruitment_req_history_view.htm?faces-redirect=true";
        } catch (BussinessException ex) {            
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_ERROR, "global.error", ex.getErrorKeyMessage(), FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
        } catch (Exception e) {
            LOGGER.error("Error ", e);
        }
        return null;
    }

    private RecruitHireApplyModel getModelFromEntity(RecruitHireApply recruitHireApply) throws Exception {
        HrmUser user = hrmUserService.getUserWithDetail(selectedApprovalActivity.getRequestBy());
        EmpData employeeApplier = empDataService.getByIdWithDetail(user.getEmpData().getId());

        RecruitHireApplyModel recruitHireApplyModel = new RecruitHireApplyModel();
        recruitHireApplyModel.setActual(0l);
        recruitHireApplyModel.setAgeMax(recruitHireApply.getAgeMax());
        recruitHireApplyModel.setAgeMin(recruitHireApply.getAgeMin());
        recruitHireApplyModel.setCandidateCountRequest(recruitHireApply.getCandidateCountRequest());
        recruitHireApplyModel.setCurrencyId(recruitHireApply.getCurrency().getId());
        recruitHireApplyModel.setEfectiveDate(recruitHireApply.getEfectiveDate());
        recruitHireApplyModel.setEmpDataApplier(employeeApplier);
        recruitHireApplyModel.setEmpStatus(recruitHireApply.getEmployeeType().getId());
        recruitHireApplyModel.setGender(recruitHireApply.getGender());
        recruitHireApplyModel.setGpaMax(recruitHireApply.getGpaMax());
        recruitHireApplyModel.setGpaMin(recruitHireApply.getGpaMin());
        recruitHireApplyModel.setJabatanId(recruitHireApply.getJabatan().getId());
        recruitHireApplyModel.setMaritalStatus(recruitHireApply.getMaritalStatus());
        recruitHireApplyModel.setRecruitMppId(recruitHireApply.getRecruitMppPeriod().getId());
        recruitHireApplyModel.setProposeDate(recruitHireApply.getProposeDate());
        recruitHireApplyModel.setReason(recruitHireApply.getReason());
        recruitHireApplyModel.setSalaryMin(recruitHireApply.getSalaryMin());
        recruitHireApplyModel.setSalaryMax(recruitHireApply.getSalaryMax());
        recruitHireApplyModel.setYearExperience(recruitHireApply.getYearExperience());

        Long totalActual = empDataService.getTotalKaryawanByJabatanId(HrmUserInfoUtil.getCompanyId(), recruitHireApplyModel.getJabatanId());
        recruitHireApplyModel.setActual(totalActual);

        Long totalMpp = recruitMppApplyDetailService.getRecruitPlanByJabatanIdAndMppPeriodId(recruitHireApplyModel.getJabatanId(), recruitHireApplyModel.getRecruitMppId());
        if (null != totalMpp) {
            model.setMpp(totalMpp);
        } else {
            model.setMpp(0l);
        }

        return recruitHireApplyModel;
    }
    
    private RecruitHireApplyModel convertJsonToModel(String jsonData) throws Exception {

        RecruitHireApplyModel model = null;
        Gson gson = JsonUtil.getHibernateEntityGsonBuilder().create();

        JsonParser parser = new JsonParser();
        JsonObject jsonObject = (JsonObject) parser.parse(jsonData);
        RecruitHireApply recruitHireApply = gson.fromJson(jsonObject, RecruitHireApply.class);
        model = getModelFromEntity(recruitHireApply);

        JsonArray arrayDetailRecruitmentRequest = jsonObject.getAsJsonArray("listDetailRecruitHireApply");
        List<OrgTypeOfSpecList> listTypeOfSpec = new ArrayList<>();

        if (null != arrayDetailRecruitmentRequest) {
            for (int i = 0; i < arrayDetailRecruitmentRequest.size(); i++) {
                RecruitHireApplyDetail detail = gson.fromJson(arrayDetailRecruitmentRequest.get(i), RecruitHireApplyDetail.class);
                listTypeOfSpec.add(detail.getOrgTypeOfSpecList());
            }
        }

        //Group list by OrgTypeOfSpec.name
        Group<OrgTypeOfSpecList> groupOrgTypeOfSpecList = Lambda.group(listTypeOfSpec, Lambda.by(Lambda.on(OrgTypeOfSpecList.class).getOrgTypeOfSpec().getId()));

        //iterate each group list element
        for (String key : groupOrgTypeOfSpecList.keySet()) {

            List<OrgTypeOfSpecList> listGroupedOrgTypeOfSpecList = groupOrgTypeOfSpecList.find(key);
            OrgTypeOfSpec orgTypeOfSpec = orgTypeOfSpecService.getEntiyByPK(Long.parseLong(key));
            int index = name.indexOf(orgTypeOfSpec.getName());

            if (index != -1) {
                
                mapTypeList.put(orgTypeOfSpec.getName(), listGroupedOrgTypeOfSpecList);
                List<OrgTypeOfSpecList> listSource = dataForRenders.get(index).getSource();
                List<OrgTypeOfSpecList> listSourceToRemove = Lambda.select(listSource, Lambda.having(Lambda.on(OrgTypeOfSpecList.class).getCode(), Matchers.isIn(Lambda.extract(listGroupedOrgTypeOfSpecList, Lambda.on(OrgTypeOfSpecList.class).getCode()))));
                listSource.removeAll(listSourceToRemove);
                
                dataForRenders.get(index).setTarget(listGroupedOrgTypeOfSpecList);
                dataForRenders.get(index).setSource(listSource);
            }
        }

        return model;
    }
    
    public List<DualListModel> getDataForRenders() {
        return dataForRenders;
    }

    public void setDataForRenders(List<DualListModel> dataForRenders) {
        this.dataForRenders = dataForRenders;
    }

    public void setOrgTypeOfSpecListService(OrgTypeOfSpecListService orgTypeOfSpecListService) {
        this.orgTypeOfSpecListService = orgTypeOfSpecListService;
    }

    public List<String> getName() {
        return name;
    }

    public void setName(List<String> name) {
        this.name = name;
    }

    public RecruitHireApplyModel getModel() {
        return model;
    }

    public void setModel(RecruitHireApplyModel model) {
        this.model = model;
    }

    public void setEmpDataService(EmpDataService empDataService) {
        this.empDataService = empDataService;
    }

    public void setRecruitMppApplyDetailService(RecruitMppApplyDetailService recruitMppApplyDetailService) {
        this.recruitMppApplyDetailService = recruitMppApplyDetailService;
    }

    public void setHrmUserService(HrmUserService hrmUserService) {
        this.hrmUserService = hrmUserService;
    }

    public String getIdApprovalActivity() {
        return idApprovalActivity;
    }

    public void setIdApprovalActivity(String idApprovalActivity) {
        this.idApprovalActivity = idApprovalActivity;
    }

   
    public ApprovalActivity getSelectedApprovalActivity() {
        return selectedApprovalActivity;
    }

    public void setSelectedApprovalActivity(ApprovalActivity selectedApprovalActivity) {
        this.selectedApprovalActivity = selectedApprovalActivity;
    }

    public void setApprovalActivityService(ApprovalActivityService approvalActivityService) {
        this.approvalActivityService = approvalActivityService;
    }

    public void setOrgTypeOfSpecService(OrgTypeOfSpecService orgTypeOfSpecService) {
        this.orgTypeOfSpecService = orgTypeOfSpecService;
    }

    public Map<String, List<OrgTypeOfSpecList>> getMapTypeList() {
        return mapTypeList;
    }

    public void setMapTypeList(Map<String, List<OrgTypeOfSpecList>> mapTypeList) {
        this.mapTypeList = mapTypeList;
    }

    public Boolean getIsWaitingApproval() {
        return isWaitingApproval;
    }

    public void setIsWaitingApproval(Boolean isWaitingApproval) {
        this.isWaitingApproval = isWaitingApproval;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setRecruitHireApplyService(RecruitHireApplyService recruitHireApplyService) {
        this.recruitHireApplyService = recruitHireApplyService;
    }
    
    
    
}
