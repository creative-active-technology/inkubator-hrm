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
import com.inkubator.hrm.entity.RecruitMppApply;
import com.inkubator.hrm.entity.RecruitMppApplyDetail;
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
import com.inkubator.hrm.service.RecruitMppApplyService;
import com.inkubator.hrm.service.RecruitMppPeriodService;
import com.inkubator.hrm.web.model.RecruitHireApplyModel;
import com.inkubator.securitycore.util.UserInfoUtil;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesUtil;
import com.inkubator.webcore.util.MessagesResourceUtil;

import java.util.ArrayList;
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
 * @author deni.fahri
 */
@ManagedBean(name = "recruitHireApplyFormController")
@ViewScoped
public class RecruitHireApplyFormController extends BaseController {

    @ManagedProperty(value = "#{recruitMppPeriodService}")
    private RecruitMppPeriodService recruitMppPeriodService;
    @ManagedProperty(value = "#{jabatanService}")
    private JabatanService jabatanService;
    @ManagedProperty(value = "#{employeeTypeService}")
    private EmployeeTypeService employeeTypeService;
    @ManagedProperty(value = "#{currencyService}")
    private CurrencyService currencyService;
    @ManagedProperty(value = "#{orgTypeOfSpecListService}")
    private OrgTypeOfSpecListService orgTypeOfSpecListService;
    @ManagedProperty(value = "#{orgTypeOfSpecService}")
    private OrgTypeOfSpecService orgTypeOfSpecService;
    @ManagedProperty(value = "#{recruitHireApplyService}")
    private RecruitHireApplyService recruitHireApplyService;
    @ManagedProperty(value = "#{empDataService}")
    private EmpDataService empDataService;
    @ManagedProperty(value = "#{recruitMppApplyDetailService}")
    private RecruitMppApplyDetailService recruitMppApplyDetailService;
    @ManagedProperty(value = "#{hrmUserService}")
    private HrmUserService hrmUserService;
    @ManagedProperty(value = "#{approvalActivityService}")
    private ApprovalActivityService approvalActivityService;
    @ManagedProperty(value = "#{recruitMppApplyService}")
    private RecruitMppApplyService recruitMppApplyService;
    private Map<String, Long> mapPeriode = new TreeMap<>();
    private Map<String, Long> mapJabatan = new TreeMap<>();
    private Map<String, Long> mapEmployeeType = new TreeMap<>();
    private Map<String, Long> mapCurrency = new TreeMap<>();
    private List<DualListModel> dataForRenders = new ArrayList<>();
    private RecruitHireApplyModel model;
    private List<String> name = new ArrayList<>();
    private Boolean isEdit = Boolean.FALSE;
    private String activityNumber;
    private ApprovalActivity selectedApprovalActivity;
    private Integer maxRequestEmp;

    @PostConstruct
    @Override
    public void initialization() {
        try {

            maxRequestEmp = 0;
            model = new RecruitHireApplyModel();
            List<RecruitMppPeriod> listMppPeriod = recruitMppPeriodService.getAllData();
            
            for (RecruitMppPeriod recruitMppPeriod : listMppPeriod) {
                String periodeStart = DateFormatter.getDateAsStringActiveLocale(recruitMppPeriod.getPeriodeStart(), "dd MMMM yyyy", new Locale(FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString()));
                String periodeEnd = DateFormatter.getDateAsStringActiveLocale(recruitMppPeriod.getPeriodeEnd(), "dd MMMM yyyy", new Locale(FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString()));
                mapPeriode.put(periodeStart + " - " + periodeEnd + "  |  " + recruitMppPeriod.getName(), recruitMppPeriod.getId());
            }
           

            List<EmployeeType> typeToShow = employeeTypeService.getAllData();
            for (EmployeeType employeeType : typeToShow) {
                mapEmployeeType.put(employeeType.getName(), employeeType.getId());
            }

            List<Currency> curencLiss = currencyService.getAllData();
            for (Currency currency : curencLiss) {
                mapCurrency.put(currency.getCode() + " - " + currency.getName(), currency.getId());
            }

            activityNumber = FacesUtil.getRequestParameter("execution");

            //if activityNumber is not empty, it means to edit existing Recruitment Request Data
            if (StringUtils.isNotBlank(activityNumber)) {
                dataForRenders = orgTypeOfSpecListService.getAllBySpectJabatan();
                name = orgTypeOfSpecListService.getOrgTypeSpecName();
                selectedApprovalActivity = approvalActivityService.getEntityByActivityNumberLastSequence(activityNumber);
                if (null != selectedApprovalActivity) {
                    isEdit = Boolean.TRUE;

                    //Make sure only process who have not been approved that can be modified.
                    if (selectedApprovalActivity.getApprovalStatus() != HRMConstant.APPROVAL_STATUS_APPROVED) {
                        model = convertJsonToModel(selectedApprovalActivity.getPendingData());
                    }
                    
                }
            } else {
                HrmUser user = hrmUserService.getUserWithDetail(UserInfoUtil.getUserName());
                EmpData employeeApplier = empDataService.getByIdWithDetail(user.getEmpData().getId());
                model.setEmpDataApplier(employeeApplier);
                model.setActual(0l);
                model.setMpp(0l);

                dataForRenders = orgTypeOfSpecListService.getAllBySpectJabatan();
                name = orgTypeOfSpecListService.getOrgTypeSpecName();
            }

        } catch (Exception ex) {
            LOGGER.error(ex, ex);
        }
    }

    @PreDestroy
    public void cleanAndExit() {
        recruitHireApplyService = null;
        recruitMppApplyDetailService = null;
        empDataService = null;
        employeeTypeService = null;
        approvalActivityService = null;
        hrmUserService = null;
        orgTypeOfSpecService = null;
        orgTypeOfSpecListService = null;
        jabatanService = null;
        recruitMppPeriodService = null;
        currencyService = null;
        dataForRenders = null;
        name = null;
        activityNumber = null;
        selectedApprovalActivity = null;
        mapCurrency = null;
        mapEmployeeType = null;
        mapPeriode = null;
        mapJabatan = null;
        model = null;
        isEdit = null;
    }

    public String doBack() {
        cleanAndExit();
        return "/protected/recruitment/recruitment_req_history_view.htm?faces-redirect=true";
    }
    
    public void doReset(){
    	model.setJabatanId(null);
    	model.setRecruitMppPeriodId(null);
    }

    public String doSave() {
        String redirect = null;
        try {
            List<OrgTypeOfSpecList> listSelectedSpec = new ArrayList<>();

            for (DualListModel<OrgTypeOfSpecList> dual : dataForRenders) {
                listSelectedSpec.addAll(dual.getTarget());
            }

            Set<RecruitHireApplyDetail> setRecruitHireApplyDetails = new HashSet<>();
            RecruitHireApply recruitHireApply = getEntityFromModel(model);
            recruitHireApply.setId(Long.parseLong(RandomNumberUtil.getRandomNumber(12)));

            for (OrgTypeOfSpecList typeSpecList : listSelectedSpec) {
                RecruitHireApplyDetail recruitHireApplyDetail = new RecruitHireApplyDetail();
                recruitHireApplyDetail.setId(Long.parseLong(RandomNumberUtil.getRandomNumber(12)));
                recruitHireApplyDetail.setOrgTypeOfSpecList(typeSpecList);
                recruitHireApplyDetail.setRecruitHireApply(recruitHireApply);
                setRecruitHireApplyDetails.add(recruitHireApplyDetail);
            }

            recruitHireApply.setRecruitHireApplyDetails(setRecruitHireApplyDetails);
            String message = StringUtils.EMPTY;
            if (isEdit) {
                recruitHireApplyService.updateRecruitHireWithApproval(recruitHireApply, selectedApprovalActivity.getActivityNumber());
            } else {
                message = recruitHireApplyService.saveRecruitHireWithApproval(recruitHireApply);
            }

            cleanAndExit();
            if (StringUtils.equals(message, "success_need_approval")) {
                redirect = "/protected/recruitment/recruitment_req_history_view.htm?faces-redirect=true";
                MessagesResourceUtil.setMessagesFlas(FacesMessage.SEVERITY_INFO, "global.save_info", "global.added_successfully_and_requires_approval",
                        FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
            } else {
                redirect = "/protected/recruitment/recruitment_req_history_view.htm?faces-redirect=true";
                MessagesResourceUtil.setMessagesFlas(FacesMessage.SEVERITY_INFO, "global.save_info", "global.added_successfully",
                        FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
            }
            
        } catch (BussinessException ex) {
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_ERROR, "global.error", ex.getErrorKeyMessage(), FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
        } catch (Exception ex) {
            LOGGER.error(ex, ex);
        }

        return redirect;
    }

    public void onChangeMppPeriod() {
        try {
            mapJabatan.clear();
            //Get jabatan dari periode terpilih dari mpp apply yang statusnya telah di setujui, dan belum di lakukan proses Recruitment
            List<RecruitMppApplyDetail> listMppDetailThatNoRecruited = recruitMppApplyDetailService.getListByMppPeriodIdWithApprovalStatusAndHaveNotBeenRecruited(model.getRecruitMppPeriodId(), HRMConstant.APPROVAL_STATUS_APPROVED );
            
            for (RecruitMppApplyDetail detail : listMppDetailThatNoRecruited) {
                mapJabatan.put(detail.getJabatan().getName(), detail.getJabatan().getId());
            }

        } catch (Exception ex) {
            Logger.getLogger(RecruitHireApplyFormController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void onChangeJabatan() {
        try {
            Long totalActual = empDataService.getTotalKaryawanByJabatanId(model.getJabatanId());
            model.setActual(totalActual);
            if (model.getRecruitMppPeriodId() != null) {

                Long totalMpp = recruitMppApplyDetailService.getRecruitPlanByJabatanIdAndMppPeriodId(model.getJabatanId(), model.getRecruitMppPeriodId());
                if (null != totalMpp) {
                    model.setMpp(totalMpp);
                } else {
                    model.setMpp(0l);
                }

            }
            maxRequestEmp = model.getMpp().intValue();
        } catch (Exception ex) {
            Logger.getLogger(RecruitHireApplyFormController.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        recruitHireApplyModel.setRecruitMppPeriodId(recruitHireApply.getRecruitMppPeriod().getId());
        recruitHireApplyModel.setProposeDate(recruitHireApply.getProposeDate());
        recruitHireApplyModel.setReason(recruitHireApply.getReason());
        recruitHireApplyModel.setSalaryMin(recruitHireApply.getSalaryMin());
        recruitHireApplyModel.setSalaryMax(recruitHireApply.getSalaryMax());
        recruitHireApplyModel.setYearExperience(recruitHireApply.getYearExperience());

        Long totalActual = empDataService.getTotalKaryawanByJabatanId(recruitHireApplyModel.getJabatanId());
        recruitHireApplyModel.setActual(totalActual);

        Long totalMpp = recruitMppApplyDetailService.getRecruitPlanByJabatanIdAndMppPeriodId(recruitHireApplyModel.getJabatanId(), recruitHireApplyModel.getRecruitMppId());
        if (null != totalMpp) {
        	recruitHireApplyModel.setMpp(totalMpp);
        } else {
        	recruitHireApplyModel.setMpp(0l);
        }
        
        return recruitHireApplyModel;
    }

    private RecruitHireApply getEntityFromModel(RecruitHireApplyModel model) throws Exception {
        RecruitHireApply recruitHireApply = new RecruitHireApply();

        if (model.getRecruitHireApplyId() != null) {
            recruitHireApply.setId(model.getRecruitHireApplyId());
        }

        recruitHireApply.setRecruitMppPeriod(new RecruitMppPeriod(model.getRecruitMppPeriodId(), null, null, null, null));
        recruitHireApply.setAgeMax(model.getAgeMax());
        recruitHireApply.setAgeMin(model.getAgeMin());
        recruitHireApply.setCandidateCountRequest(model.getCandidateCountRequest());
        recruitHireApply.setCurrency(new Currency(model.getCurrencyId()));
        recruitHireApply.setEfectiveDate(model.getEfectiveDate());
        recruitHireApply.setEmployeeType(new EmployeeType(model.getEmpStatus()));
        recruitHireApply.setGender(model.getGender());
        recruitHireApply.setGpaMax(model.getGpaMax());
        recruitHireApply.setGpaMin(model.getGpaMin());
        recruitHireApply.setJabatan(new Jabatan(model.getJabatanId()));
        recruitHireApply.setMaritalStatus(model.getMaritalStatus());
        recruitHireApply.setProposeDate(model.getProposeDate());
        recruitHireApply.setReason(model.getReason());
        recruitHireApply.setReqHireCode(model.getReqHireCode());
        recruitHireApply.setSalaryMax(model.getSalaryMax());
        recruitHireApply.setSalaryMin(model.getSalaryMin());
        recruitHireApply.setYearExperience(model.getYearExperience());
        return recruitHireApply;
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

                List<OrgTypeOfSpecList> listSource = dataForRenders.get(index).getSource();
                List<OrgTypeOfSpecList> listSourceToRemove = Lambda.select(listSource, Lambda.having(Lambda.on(OrgTypeOfSpecList.class).getCode(), Matchers.isIn(Lambda.extract(listGroupedOrgTypeOfSpecList, Lambda.on(OrgTypeOfSpecList.class).getCode()))));
                listSource.removeAll(listSourceToRemove);

                dataForRenders.get(index).setTarget(listGroupedOrgTypeOfSpecList);
                dataForRenders.get(index).setSource(listSource);
            }
        }
        
        List<RecruitMppApplyDetail> listMppDetail = recruitMppApplyDetailService.getListInSelectedMppPeriodIdWithApprovalStatus(model.getRecruitMppPeriodId(), HRMConstant.APPROVAL_STATUS_APPROVED );
        for (RecruitMppApplyDetail detail : listMppDetail) {
            mapJabatan.put(detail.getJabatan().getName(), detail.getJabatan().getId());
        }


        return model;
    }

    public void setRecruitMppPeriodService(RecruitMppPeriodService recruitMppPeriodService) {
        this.recruitMppPeriodService = recruitMppPeriodService;
    }

    public Map<String, Long> getMapPeriode() {
        return mapPeriode;
    }

    public void setMapPeriode(Map<String, Long> mapPeriode) {
        this.mapPeriode = mapPeriode;
    }

    public void setJabatanService(JabatanService jabatanService) {
        this.jabatanService = jabatanService;
    }

    public Map<String, Long> getMapJabatan() {
        return mapJabatan;
    }

    public void setMapJabatan(Map<String, Long> mapJabatan) {
        this.mapJabatan = mapJabatan;
    }

    public void setEmployeeTypeService(EmployeeTypeService employeeTypeService) {
        this.employeeTypeService = employeeTypeService;
    }

    public Map<String, Long> getMapEmployeeType() {
        return mapEmployeeType;
    }

    public void setMapEmployeeType(Map<String, Long> mapEmployeeType) {
        this.mapEmployeeType = mapEmployeeType;
    }

    public void setCurrencyService(CurrencyService currencyService) {
        this.currencyService = currencyService;
    }

    public Map<String, Long> getMapCurrency() {
        return mapCurrency;
    }

    public void setMapCurrency(Map<String, Long> mapCurrency) {
        this.mapCurrency = mapCurrency;
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

    public Boolean getIsEdit() {
        return isEdit;
    }

    public void setIsEdit(Boolean isEdit) {
        this.isEdit = isEdit;
    }

    public void setRecruitHireApplyService(RecruitHireApplyService recruitHireApplyService) {
        this.recruitHireApplyService = recruitHireApplyService;
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

    public void setRecruitMppApplyService(
            RecruitMppApplyService recruitMppApplyService) {
        this.recruitMppApplyService = recruitMppApplyService;
    }

    public Integer getMaxRequestEmp() {
        return maxRequestEmp;
    }

    public void setMaxRequestEmp(Integer maxRequestEmp) {
        this.maxRequestEmp = maxRequestEmp;
    }

}
