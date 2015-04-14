/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.loan;

import ch.lambdaj.Lambda;
import com.google.common.collect.ImmutableSet;
import com.google.gson.Gson;
import com.inkubator.common.CommonUtilConstant;
import com.inkubator.common.util.DateTimeUtil;
import com.inkubator.common.util.RandomNumberUtil;
import com.inkubator.exception.BussinessException;
import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.entity.ApprovalActivity;
import com.inkubator.hrm.entity.ApprovalDefinition;
import com.inkubator.hrm.entity.ApprovalDefinitionLoan;
import com.inkubator.hrm.entity.EmpData;
import com.inkubator.hrm.entity.HrmUser;
import com.inkubator.hrm.entity.LoanNewApplication;
import com.inkubator.hrm.entity.LoanNewApplicationInstallment;
import com.inkubator.hrm.entity.LoanNewSchemaListOfEmp;
import com.inkubator.hrm.entity.LoanNewSchemaListOfType;
import com.inkubator.hrm.json.util.JsonUtil;
import com.inkubator.hrm.service.ApprovalActivityService;
import com.inkubator.hrm.service.ApprovalDefinitionLoanService;
import com.inkubator.hrm.service.ApprovalDefinitionService;
import com.inkubator.hrm.service.EmpDataService;
import com.inkubator.hrm.service.HrmUserService;
import com.inkubator.hrm.service.LoanNewApplicationService;
import com.inkubator.hrm.service.LoanNewCancelationService;
import com.inkubator.hrm.service.LoanNewSchemaListOfEmpService;
import com.inkubator.hrm.service.LoanNewSchemaListOfTypeService;
import com.inkubator.hrm.service.LoanNewSchemaService;
import com.inkubator.hrm.service.LoanNewTypeService;
import com.inkubator.hrm.util.HrmUserInfoUtil;
import com.inkubator.hrm.web.model.LoanApplicationFormModel;
import com.inkubator.hrm.web.model.LoanNewCancellationFormModel;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesUtil;
import com.inkubator.webcore.util.MessagesResourceUtil;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.xml.crypto.Data;
import org.apache.commons.lang3.StringUtils;
import org.hamcrest.Matchers;

/**
 *
 * @author Ahmad Mudzakkir Amal
 */
@ManagedBean(name = "loanNewCancellationFormController")
@ViewScoped
public class LoanNewCancellationFormController extends BaseController {

    @ManagedProperty(value = "#{loanNewCancelationService}")
    private LoanNewCancelationService loanNewCancelationService;
    @ManagedProperty(value = "#{loanNewSchemaService}")
    private LoanNewSchemaService loanNewSchemaService;
    @ManagedProperty(value = "#{loanNewSchemaListOfEmpService}")
    private LoanNewSchemaListOfEmpService loanNewSchemaListOfEmpService;
    @ManagedProperty(value = "#{loanNewSchemaListOfTypeService}")
    private LoanNewSchemaListOfTypeService loanNewSchemaListOfTypeService;
    @ManagedProperty(value = "#{empDataService}")
    private EmpDataService empDataService;
    @ManagedProperty(value = "#{loanNewTypeService}")
    private LoanNewTypeService loanNewTypeService;
    @ManagedProperty(value = "#{approvalDefinitionService}")
    private ApprovalDefinitionService approvalDefinitionService;
    @ManagedProperty(value = "#{approvalDefinitionLoanService}")
    private ApprovalDefinitionLoanService approvalDefinitionLoanService;
    @ManagedProperty(value = "#{approvalActivityService}")
    private ApprovalActivityService approvalActivityService;
    @ManagedProperty(value = "#{hrmUserService}")
    private HrmUserService hrmUserService;

    private Boolean isAdmin;
    private Long approvalDefId;

    private ApprovalActivity currentActivity;
    private ApprovalActivity askingRevisedActivity;

    private LoanNewCancellationFormModel model;

    @PostConstruct
    @Override
    public void initialization() {
        super.initialization();

        model = new LoanNewCancellationFormModel();
       
        try {

            isAdmin = Lambda.exists(HrmUserInfoUtil.getRoles(), Matchers.containsString(HRMConstant.ADMINISTRATOR_ROLE));
            if (!isAdmin) { //jika bukan administrator, langsung di set empData berdasarkan yang login

                model.setEmpData(HrmUserInfoUtil.getEmpData());
                model.setNamakaryawan(HrmUserInfoUtil.getRealName());
                this.updateDataPinjamanByEmployee();

            }

        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }

    }

    @PreDestroy
    public void cleanAndExit() {
        loanNewCancelationService = null;
        loanNewSchemaService = null;
        loanNewTypeService = null;
        approvalDefinitionService = null;
        approvalDefinitionLoanService = null;
        loanNewSchemaListOfTypeService = null;
        loanNewSchemaListOfEmpService = null;
        empDataService = null;
        model = null;      
        approvalDefId = null;
        isAdmin = null;
    }

    public String doCancelLoan() {

//        if (this.isValidForm()) {
//            String path = StringUtils.EMPTY;
//            LoanNewApplication loanNewApplication = getEntityFromModel(model);
//            try {
//
//                String result = loanNewApplicationService.saveWithApproval(loanNewApplication);
//
//                if (StringUtils.equals(result, "success_need_approval")) {
//
//                    path = "/protected/personalia/loan_application_form.htm?faces-redirect=true";
//                    MessagesResourceUtil.setMessagesFlas(FacesMessage.SEVERITY_INFO, "global.save_info", "global.added_successfully_and_requires_approval", FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
//
//                } else {
//                    path = "/protected/personalia/loan_application_form.htm?faces-redirect=true";
//                    MessagesResourceUtil.setMessagesFlas(FacesMessage.SEVERITY_INFO, "global.save_info", "global.added_successfully", FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
//                }
//
//                return path;
//
//            } catch (BussinessException ex) {
//                MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_ERROR, "global.error", ex.getErrorKeyMessage(), FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
//            } catch (Exception ex) {
//                LOGGER.error("Error", ex);
//            }
//
//        }
        return null;
    }

   

    public void doReset() {
        if (isAdmin) {
            model = new LoanNewCancellationFormModel();          
        } else {
            model = new LoanNewCancellationFormModel();
            model.setEmpData(HrmUserInfoUtil.getEmpData());
            model.setNamakaryawan(HrmUserInfoUtil.getRealName());            
            this.updateDataPinjamanByEmployee();
        }

    }

    public String doBack() {
        cleanAndExit();
        //return "/protected/employee/emp_schedule_view.htm?faces-redirect=true";
        return "/protected/home.htm?faces-redirect=true";
    }



    public void updateDataPinjamanByNomor() {
        try {

            LoanNewSchemaListOfType loanNewSchemaListOfType = null;

//            if (isRevised) {
//
//                loanNewSchemaListOfType = loanNewSchemaListOfTypeService.getEntityByLoanNewTypeIdWithDetail(loanNewTypeId);
//            } else {
//
//                loanNewSchemaListOfType = loanNewSchemaListOfTypeService.getEntityByIdWithDetail(loanNewTypeId);
//            }

            model.setSelectedLoanNewSchemaListOfType(loanNewSchemaListOfType);
            model.setMinimumInstallment(loanNewSchemaListOfType.getMinimumMonthlyInstallment());

//            if (null != model.getRangeFirstInstallmentToDisbursement()) {
//                model.setLoanPeriod(loanNewSchemaListOfType.getMaxPeriode() - model.getRangeFirstInstallmentToDisbursement());
//            } else {
//                model.setLoanPeriod(loanNewSchemaListOfType.getMaxPeriode());
//            }

            model.setMaxLoanAmount(model.getSelectedLoanNewSchemaListOfType().getMaximumAllocation());
            model.setMinLoanAmount(model.getSelectedLoanNewSchemaListOfType().getMinimumAllocation());

//            if (null != model.getNominalLoan()) {
//                model.setAvailableLoanAmount(model.getMaxLoanAmount() - model.getNominalLoan());
//            } else {
//                model.setAvailableLoanAmount(model.getMaxLoanAmount());
//            }
//
//            if (!model.getListLoanNewApplicationInstallments().isEmpty()) {
//                //model.setListLoanNewApplicationInstallments(new ArrayList<LoanNewApplicationInstallment>());
//                doCalculateInstallmentSchedule();
//            }

        } catch (Exception ex) {
            Logger.getLogger(LoanNewCancellationFormController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void updateDataPinjamanByEmployee() {
        try {
            
            model.setNamakaryawan(model.getEmpData().getNikWithFullName());
            HrmUser hrmUser = hrmUserService.getByEmpDataId(model.getEmpData().getId());
            List<ApprovalActivity> listPending = approvalActivityService.getPendingRequest(hrmUser.getUserId());
            
        } catch (Exception e) {
            Logger.getLogger(LoanNewCancellationFormController.class.getName()).log(Level.SEVERE, null, e);
        }

    }

    public List<EmpData> completeEmpData(String query) {
        try {
            List<EmpData> allEmpData = empDataService.getAllDataByNameOrNik(StringUtils.stripToEmpty(query));
            return allEmpData;
        } catch (Exception ex) {
            Logger.getLogger(LoanNewCancellationFormController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    

    private LoanNewApplication getEntityFromModel(LoanNewCancellationFormModel model) {

        LoanNewApplication loanNewApplication = new LoanNewApplication();

        loanNewApplication.setApplicationDate(model.getLoanDate());
        loanNewApplication.setDibursementDate(model.getExpectedDisbursementDate());        
        loanNewApplication.setDescription(model.getDescription());
        loanNewApplication.setNominalPrincipal(model.getNominalLoan());
        loanNewApplication.setEmpData(model.getEmpData());
        //loanNewApplication.setLoanNewApplicationInstallments(ImmutableSet.copyOf(model.getListLoanNewApplicationInstallments()));
        loanNewApplication.setLoanNewType(model.getSelectedLoanNewSchemaListOfType().getLoanNewType());
        loanNewApplication.setLoanNewSchema(model.getSelectedLoanNewSchemaListOfType().getLoanNewSchema());
        loanNewApplication.setPurposeNote(model.getPurpose());
        loanNewApplication.setSubsidizedDiscOfInterest(model.getSubsidiBunga());
        loanNewApplication.setSubsidizedNominal(model.getSubsidiCicilan());
        loanNewApplication.setTermin(model.getTermin());
        loanNewApplication.setNomor(model.getNomor());
        loanNewApplication.setCreatedOn(new Date());
        loanNewApplication.setCreatedBy(HrmUserInfoUtil.getUserName());

        return loanNewApplication;
    }

    private void convertJsonToModel(String json) {
        try {
            System.out.println("json : " + json);
            Gson gson = JsonUtil.getHibernateEntityGsonBuilder().create();
            LoanNewApplication entity = gson.fromJson(json, LoanNewApplication.class);

            model.setEmpData(empDataService.getByEmpIdWithDetail(entity.getEmpData().getId()));
            model.setLoanDate(entity.getApplicationDate());
            model.setNamakaryawan(model.getEmpData().getNikWithFullName());
            model.setLoanNewSchemaListOfEmp(loanNewSchemaListOfEmpService.getEntityByEmpDataIdAndLoanSchemaId(entity.getEmpData().getId(), entity.getLoanNewSchema().getId()));
            model.setPurpose(entity.getPurposeNote());
            model.setExpectedDisbursementDate(entity.getDibursementDate());
            model.setNominalLoan(entity.getNominalPrincipal());           
            model.setTermin(entity.getTermin());
            model.setDescription(entity.getDescription());

//            //Assign loanNewTypeId with LoanNewType id to trigger selectedLoanNewSchemeListOfType on method updateDataPinjamanByLoanNewType
//            loanNewTypeId = entity.getLoanNewType().getId();
//            System.out.println("loanNewTypeId : " + loanNewTypeId);
            this.updateDataPinjamanByEmployee();
            this.updateDataPinjamanByNomor();
           

            //Re-assign loanNewType Id with id of selectedLoanNewSchemeListOfType
            //bacause at UI, list LoanType key is using id of LoanNewSchemeListOfType instead of  LoanNewType id
//            loanNewTypeId = model.getSelectedLoanNewSchemaListOfType().getId();
//            if (null != entity.getSubsidizedDiscOfInterest() || null != entity.getSubsidizedNominal()) {
//                model.setIsSubsidi(Boolean.TRUE);
//
//                if (null != entity.getSubsidizedNominal()) {
//                    subsidiType = 1l;
//                    model.setSubsidiCicilan(entity.getSubsidizedNominal());
//                } else if (null != entity.getSubsidizedDiscOfInterest()) {
//                    subsidiType = 2l;
//                    model.setSubsidiBunga(entity.getSubsidizedDiscOfInterest());
//                }
//            }

        } catch (Exception e) {
            LOGGER.error("Error", e);
        }
    }

    public Long getApprovalDefId() {
        return approvalDefId;
    }

    public void setApprovalDefId(Long approvalDefId) {
        this.approvalDefId = approvalDefId;
    }

    public ApprovalDefinitionService getApprovalDefinitionService() {
        return approvalDefinitionService;
    }

    public void setApprovalDefinitionService(ApprovalDefinitionService approvalDefinitionService) {
        this.approvalDefinitionService = approvalDefinitionService;
    }

    public ApprovalDefinitionLoanService getApprovalDefinitionLoanService() {
        return approvalDefinitionLoanService;
    }

    public void setApprovalDefinitionLoanService(ApprovalDefinitionLoanService approvalDefinitionLoanService) {
        this.approvalDefinitionLoanService = approvalDefinitionLoanService;
    }  

    public LoanNewSchemaListOfEmpService getLoanNewSchemaListOfEmpService() {
        return loanNewSchemaListOfEmpService;
    }

    public void setLoanNewSchemaListOfEmpService(LoanNewSchemaListOfEmpService loanNewSchemaListOfEmpService) {
        this.loanNewSchemaListOfEmpService = loanNewSchemaListOfEmpService;
    }

    public LoanNewSchemaListOfTypeService getLoanNewSchemaListOfTypeService() {
        return loanNewSchemaListOfTypeService;
    }

    public void setLoanNewSchemaListOfTypeService(LoanNewSchemaListOfTypeService loanNewSchemaListOfTypeService) {
        this.loanNewSchemaListOfTypeService = loanNewSchemaListOfTypeService;
    }

   
    public Boolean getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(Boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

    public LoanNewSchemaService getLoanNewSchemaService() {
        return loanNewSchemaService;
    }

    public void setLoanNewSchemaService(LoanNewSchemaService loanNewSchemaService) {
        this.loanNewSchemaService = loanNewSchemaService;
    }

    public LoanNewTypeService getLoanNewTypeService() {
        return loanNewTypeService;
    }

    public void setLoanNewTypeService(LoanNewTypeService loanNewTypeService) {
        this.loanNewTypeService = loanNewTypeService;
    }

    public LoanNewCancellationFormModel getModel() {
        return model;
    }

    public void setModel(LoanNewCancellationFormModel model) {
        this.model = model;
    }

    public EmpDataService getEmpDataService() {
        return empDataService;
    }

    public void setEmpDataService(EmpDataService empDataService) {
        this.empDataService = empDataService;
    }   

    public ApprovalActivity getCurrentActivity() {
        return currentActivity;
    }

    public void setCurrentActivity(ApprovalActivity currentActivity) {
        this.currentActivity = currentActivity;
    }

    public ApprovalActivity getAskingRevisedActivity() {
        return askingRevisedActivity;
    }

    public void setAskingRevisedActivity(ApprovalActivity askingRevisedActivity) {
        this.askingRevisedActivity = askingRevisedActivity;
    }

    public void setApprovalActivityService(ApprovalActivityService approvalActivityService) {
        this.approvalActivityService = approvalActivityService;
    }

    public void setLoanNewCancelationService(LoanNewCancelationService loanNewCancelationService) {
        this.loanNewCancelationService = loanNewCancelationService;
    }

    public void setHrmUserService(HrmUserService hrmUserService) {
        this.hrmUserService = hrmUserService;
    }
    
    

}
