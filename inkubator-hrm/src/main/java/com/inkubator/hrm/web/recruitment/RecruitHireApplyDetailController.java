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
import com.inkubator.hrm.service.RecruitHireApplyDetailService;
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
@ManagedBean(name = "recruitHireApplyDetailController")
@ViewScoped
public class RecruitHireApplyDetailController extends BaseController {

    @ManagedProperty(value = "#{recruitHireApplyService}")
    private RecruitHireApplyService recruitHireApplyService;
    @ManagedProperty(value = "#{recruitHireApplyDetailService}")
    private RecruitHireApplyDetailService recruitHireApplyDetailService;
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
    private Map<String, List<OrgTypeOfSpecList>> mapTypeList = new HashMap<>();
    private RecruitHireApplyModel model;
    private List<String> name = new ArrayList<>();
    private String activityNumber;
    private ApprovalActivity selectedApprovalActivity;
    private Boolean isApprovedOrRejected;

    @PostConstruct
    @Override
    public void initialization() {
        try {

            model = new RecruitHireApplyModel();
            isApprovedOrRejected = Boolean.TRUE;
            activityNumber = FacesUtil.getRequestParameter("execution");            

            //if activityNumber is not empty, it means to edit existing Recruitment Request Data
            if (StringUtils.isNotBlank(activityNumber)) {                
                name = orgTypeOfSpecListService.getOrgTypeSpecName();
                selectedApprovalActivity = approvalActivityService.getEntityByActivityNumberLastSequence(activityNumber);
                
                if (null != selectedApprovalActivity) {
                    isApprovedOrRejected = selectedApprovalActivity.getApprovalStatus() == HRMConstant.APPROVAL_STATUS_APPROVED
                            || selectedApprovalActivity.getApprovalStatus() == HRMConstant.APPROVAL_STATUS_REJECTED;


                    if (!isApprovedOrRejected) {
                        model = convertJsonToModel(selectedApprovalActivity.getPendingData());
                    } else {
                        RecruitHireApply recruitHireApply = recruitHireApplyService.getEntityWithDetailByActivityNumber(activityNumber);                        
                        model = convertModelFromEntity(recruitHireApply);

                        List<RecruitHireApplyDetail> listRecruitHireApplyDetails = recruitHireApplyDetailService.getListWithDetailByRecruitHireApplyId(recruitHireApply.getId());
                        List<OrgTypeOfSpecList> listTypeOfSpec = new ArrayList<>();

                        for (RecruitHireApplyDetail detail : listRecruitHireApplyDetails) {
                            listTypeOfSpec.add(detail.getOrgTypeOfSpecList());
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
                            }
                        }

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
        recruitHireApplyDetailService = null;
        recruitHireApplyService = null;
        name = null;
        activityNumber = null;
        selectedApprovalActivity = null;
        model = null;
        mapTypeList = null;
    }

    public String doBack() {
        cleanAndExit();
        return "/protected/recruitment/recruitment_req_history_view.htm?faces-redirect=true";
    }

    public String doEdit() {
        return "/protected/recruitment/recruitment_request_form.htm?faces-redirect=true&execution=" + selectedApprovalActivity.getActivityNumber();
    }

    private RecruitHireApplyModel convertModelFromEntity(RecruitHireApply recruitHireApply) throws Exception {
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
        recruitHireApplyModel.setEmpStatus(recruitHireApply.getEmployeeType() == null ? null : recruitHireApply.getEmployeeType().getId());
        recruitHireApplyModel.setGender(recruitHireApply.getGender());
        recruitHireApplyModel.setGpaMax(recruitHireApply.getGpaMax());
        recruitHireApplyModel.setGpaMin(recruitHireApply.getGpaMin());
        recruitHireApplyModel.setJabatanId(recruitHireApply.getJabatan() == null ? null : recruitHireApply.getJabatan().getId());
        recruitHireApplyModel.setMaritalStatus(recruitHireApply.getMaritalStatus());
        recruitHireApplyModel.setRecruitMppId(recruitHireApply.getRecruitMppPeriod() == null ? null : recruitHireApply.getRecruitMppPeriod().getId());
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
        model = convertModelFromEntity(recruitHireApply);

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
            }
        }

        return model;
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

    public String getActivityNumber() {
        return activityNumber;
    }

    public void setActivityNumber(String activityNumber) {
        this.activityNumber = activityNumber;
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

    public void setRecruitHireApplyService(RecruitHireApplyService recruitHireApplyService) {
        this.recruitHireApplyService = recruitHireApplyService;
    }

    public Boolean getIsApprovedOrRejected() {
        return isApprovedOrRejected;
    }

    public void setIsApprovedOrRejected(Boolean isApprovedOrRejected) {
        this.isApprovedOrRejected = isApprovedOrRejected;
    }

    public void setRecruitHireApplyDetailService(RecruitHireApplyDetailService recruitHireApplyDetailService) {
        this.recruitHireApplyDetailService = recruitHireApplyDetailService;
    }

}
