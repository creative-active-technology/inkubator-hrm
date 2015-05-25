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
import com.inkubator.hrm.entity.LoanNewApplication;
import com.inkubator.hrm.entity.LoanNewApplicationInstallment;
import com.inkubator.hrm.entity.LoanNewSchemaListOfEmp;
import com.inkubator.hrm.entity.LoanNewSchemaListOfType;
import com.inkubator.hrm.json.util.JsonUtil;
import com.inkubator.hrm.service.ApprovalActivityService;
import com.inkubator.hrm.service.ApprovalDefinitionLoanService;
import com.inkubator.hrm.service.ApprovalDefinitionService;
import com.inkubator.hrm.service.EmpDataService;
import com.inkubator.hrm.service.LoanNewApplicationService;
import com.inkubator.hrm.service.LoanNewSchemaListOfEmpService;
import com.inkubator.hrm.service.LoanNewSchemaListOfTypeService;
import com.inkubator.hrm.service.LoanNewSchemaService;
import com.inkubator.hrm.service.LoanNewTypeService;
import com.inkubator.hrm.util.HrmUserInfoUtil;
import com.inkubator.hrm.web.model.LoanApplicationFormModel;
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
@ManagedBean(name = "loanApplicationFormController")
@ViewScoped
public class LoanApplicationFormController extends BaseController {

    @ManagedProperty(value = "#{loanNewApplicationService}")
    private LoanNewApplicationService loanNewApplicationService;
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

    private Boolean isAdmin;
    private Boolean isRevised;

    private Map<String, Long> mapLoanNewType = new HashMap<>();
    private Map<String, Long> mapSubsidiType = new HashMap<>();

    private Long subsidiType;
    private Long loanNewTypeId;
    private Long approvalDefId;

    private ApprovalActivity currentActivity;
    private ApprovalActivity askingRevisedActivity;

    private LoanApplicationFormModel model;

    @PostConstruct
    @Override
    public void initialization() {
        super.initialization();
        isRevised = Boolean.FALSE;
        model = new LoanApplicationFormModel();
        model.setRangeFirstInstallmentToDisbursement(1);
        model.setListLoanNewApplicationInstallments(new ArrayList<LoanNewApplicationInstallment>());
        model.setListApprover(new ArrayList<EmpData>());
        model.setNomor(HRMConstant.LOAN + "-" + RandomNumberUtil.getRandomNumber(9));
        model.setRangeFirstInstallmentToDisbursement(1);
        try {

            mapSubsidiType.put("Cicilan", 1l);
            mapSubsidiType.put("Bunga", 2l);

            isAdmin = Lambda.exists(HrmUserInfoUtil.getRoles(), Matchers.containsString(HRMConstant.ADMINISTRATOR_ROLE));

            //di cek terlebih dahulu, jika datangnya dari proses approval, artinya user akan melakukan revisi data yg masih dalam bentuk json	        
            String appActivityId = FacesUtil.getRequestParameter("activity");
            if (StringUtils.isNotEmpty(appActivityId)) {
                //parsing data from json to object 
                isRevised = Boolean.TRUE;
                currentActivity = approvalActivityService.getEntityByPkWithDetail(Long.parseLong(appActivityId.substring(1)));
                askingRevisedActivity = approvalActivityService.getEntityByActivityNumberAndSequence(currentActivity.getActivityNumber(),
                        currentActivity.getSequence() - 1);
                this.convertJsonToModel(currentActivity.getPendingData());
            } else {
                if (!isAdmin) { //jika bukan administrator, langsung di set empData berdasarkan yang login

                    model.setEmpData(HrmUserInfoUtil.getEmpData());
                    model.setNamakaryawan(HrmUserInfoUtil.getRealName());
                    this.updateDataPinjamanByEmployee();

                }
            }

        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }

    }

    @PreDestroy
    public void cleanAndExit() {
        loanNewApplicationService = null;
        loanNewSchemaService = null;
        loanNewTypeService = null;
        approvalDefinitionService = null;
        approvalDefinitionLoanService = null;
        loanNewSchemaListOfTypeService = null;
        loanNewSchemaListOfEmpService = null;
        empDataService = null;
        model = null;
        mapLoanNewType = null;
        mapSubsidiType = null;
        loanNewTypeId = null;
        subsidiType = null;
        approvalDefId = null;
        isAdmin = null;
    }

    public String doApply() {

        if (this.isValidForm()) {
            String path = StringUtils.EMPTY;
            LoanNewApplication loanNewApplication = getEntityFromModel(model);
            try {

                String result = loanNewApplicationService.saveWithApproval(loanNewApplication);

                if (StringUtils.equals(result, "success_need_approval")) {

                    path = "/protected/personalia/loan_application_form.htm?faces-redirect=true";
                    MessagesResourceUtil.setMessagesFlas(FacesMessage.SEVERITY_INFO, "global.save_info", "global.added_successfully_and_requires_approval", FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());

                } else {
                    path = "/protected/personalia/loan_application_form.htm?faces-redirect=true";
                    MessagesResourceUtil.setMessagesFlas(FacesMessage.SEVERITY_INFO, "global.save_info", "global.added_successfully", FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
                }

                return path;

            } catch (BussinessException ex) {
                MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_ERROR, "global.error", ex.getErrorKeyMessage(), FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
            } catch (Exception ex) {
                LOGGER.error("Error", ex);
            }

        }
        return null;
    }

    public String doRevised() {
        LoanNewApplication loanNewApplication = getEntityFromModel(model);
        String path = StringUtils.EMPTY;
        
        try {
            String message = loanNewApplicationService.saveWithRevised(loanNewApplication, currentActivity.getId(), currentActivity.getActivityNumber());
            System.out.println("akhir  proses revisi, message : " + message);
            if (StringUtils.equals(message, "success_need_approval")) {
                path = "/protected/personalia/loan_application_form.htm?faces-redirect=true";
                MessagesResourceUtil.setMessagesFlas(FacesMessage.SEVERITY_INFO, "global.save_info", "global.added_successfully_and_requires_approval", FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());

            } else {
                path = "/protected/personalia/loan_application_form.htm?faces-redirect=true";
                MessagesResourceUtil.setMessagesFlas(FacesMessage.SEVERITY_INFO, "global.save_info", "global.added_successfully", FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
            }
            return path;

        } catch (BussinessException ex) {
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_ERROR, "global.error", ex.getErrorKeyMessage(), FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
        return null;
    }

    private Boolean isValidForm() {
        boolean isValid = false;
        try {

            if (model.getNominalLoan() > model.getSelectedLoanNewSchemaListOfType().getMaximumAllocation()) {
                MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_ERROR, "global.error", "loan.error_nominal_principal", FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());

            } else if (model.getListLoanNewApplicationInstallments().isEmpty()) {
                MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_ERROR, "global.error", "loan.loan_isntallment_table_should_not_be_empty", FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());

            } else {
                isValid = true;
            }
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }

        return isValid;
    }

    public void doReset() {
        if (isAdmin) {
            model = new LoanApplicationFormModel();
            model.setRangeFirstInstallmentToDisbursement(1);
            model.setListLoanNewApplicationInstallments(new ArrayList<LoanNewApplicationInstallment>());
            model.setListApprover(new ArrayList<EmpData>());
            model.setRangeFirstInstallmentToDisbursement(1);
            model.setNomor(HRMConstant.LOAN + "-" + RandomNumberUtil.getRandomNumber(9));
            mapLoanNewType.clear();
            subsidiType = 0l;
        } else {
            model = new LoanApplicationFormModel();
            model.setEmpData(HrmUserInfoUtil.getEmpData());
            model.setNamakaryawan(HrmUserInfoUtil.getRealName());
            model.setRangeFirstInstallmentToDisbursement(1);
            model.setListLoanNewApplicationInstallments(new ArrayList<LoanNewApplicationInstallment>());
            model.setRangeFirstInstallmentToDisbursement(1);
            model.setNomor(HRMConstant.LOAN + "-" + RandomNumberUtil.getRandomNumber(9));
            mapLoanNewType.clear();
            subsidiType = 0l;
            this.updateDataPinjamanByEmployee();
        }

    }

    public String doBack() {
        cleanAndExit();
        //return "/protected/employee/emp_schedule_view.htm?faces-redirect=true";
        return "/protected/home.htm?faces-redirect=true";
    }

    public void onChangeSubsidiIsRequired() {
        if (!model.getIsSubsidi()) {
            subsidiType = 0l;
            model.setSubsidiBunga(null);
            model.setSubsidiCicilan(null);
        }
    }

    public void updateDataPinjamanByLoanNewType() {
        try {
            
            LoanNewSchemaListOfType loanNewSchemaListOfType = null;
            
            if(isRevised){
                
                loanNewSchemaListOfType = loanNewSchemaListOfTypeService.getEntityByLoanNewTypeIdWithDetail(loanNewTypeId);
            }else{
                
                loanNewSchemaListOfType = loanNewSchemaListOfTypeService.getEntityByIdWithDetail(loanNewTypeId);
            } 
            
            model.setSelectedLoanNewSchemaListOfType(loanNewSchemaListOfType);
            model.setMinimumInstallment(loanNewSchemaListOfType.getMinimumMonthlyInstallment());

            if (null != model.getRangeFirstInstallmentToDisbursement()) {
                model.setLoanPeriod(loanNewSchemaListOfType.getMaxPeriode() - model.getRangeFirstInstallmentToDisbursement());
            } else {
                model.setLoanPeriod(loanNewSchemaListOfType.getMaxPeriode());
            }

            model.setMaxLoanAmount(model.getSelectedLoanNewSchemaListOfType().getMaximumAllocation());
            model.setMinLoanAmount(model.getSelectedLoanNewSchemaListOfType().getMinimumAllocation());

            if (null != model.getNominalLoan()) {
                model.setAvailableLoanAmount(model.getMaxLoanAmount() - model.getNominalLoan());
            } else {
                model.setAvailableLoanAmount(model.getMaxLoanAmount());
            }

            if (!model.getListLoanNewApplicationInstallments().isEmpty()) {
                //model.setListLoanNewApplicationInstallments(new ArrayList<LoanNewApplicationInstallment>());
                doCalculateInstallmentSchedule();
            }

        } catch (Exception ex) {
            Logger.getLogger(LoanApplicationFormController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void updateDataPinjamanByEmployee() {
        try {

            model.setNamakaryawan(model.getEmpData().getNikWithFullName());
            LoanNewSchemaListOfEmp loanNewSchemaListOfEmp = loanNewSchemaListOfEmpService.getEntityWithDetailByEmpDataId(model.getEmpData().getId());

            if (null == loanNewSchemaListOfEmp) {
                MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_ERROR, "global.error", "loan.emp_doesnt_have_any_loan_schema", FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
                return;
            }
            model.setLoanNewSchemaListOfEmp(loanNewSchemaListOfEmp);

            List<LoanNewSchemaListOfType> listOfTypes = loanNewSchemaListOfTypeService.getEntityByLoanNewSchema(loanNewSchemaListOfEmp.getLoanNewSchema().getId());
            model.setListLoanNewSchemaListOfType(listOfTypes);

            for (LoanNewSchemaListOfType loanNewSchemaListOfType : listOfTypes) {
                mapLoanNewType.put(loanNewSchemaListOfType.getLoanNewType().getLoanTypeName(), loanNewSchemaListOfType.getId());
            }
            
            List<ApprovalDefinitionLoan> listApprovalDefinitionLoans = approvalDefinitionLoanService.getByLoanIdWithDetail(loanNewSchemaListOfEmp.getLoanNewSchema().getId());
            //if loan Schema has not had approval definition, show Error Message
            if(listApprovalDefinitionLoans.isEmpty()){
                MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_ERROR, "global.error", "loan.loan_new_schema_doesnt_have_approval_def", FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
                return;
            }
            
            List<ApprovalDefinition> listAppDef = Lambda.extract(approvalDefinitionLoanService.getByLoanIdWithDetail(loanNewSchemaListOfEmp.getLoanNewSchema().getId()), Lambda.on(ApprovalDefinitionLoan.class).getApprovalDefinition());            
            List<EmpData> listApprover = loanNewApplicationService.getListApproverByListAppDefintion(listAppDef, model.getEmpData().getId());
            model.setListApprover(listApprover);

            if (!model.getListLoanNewApplicationInstallments().isEmpty()) {                
                doCalculateInstallmentSchedule();
            }

        } catch (Exception e) {
            Logger.getLogger(LoanApplicationFormController.class.getName()).log(Level.SEVERE, null, e);
        }

    }

    public void updateDataPinjamanByJumlahPinjaman() {
        try {

            if (null != model.getNominalLoan()) {
                model.setAvailableLoanAmount(model.getMaxLoanAmount() - model.getNominalLoan());
            }

            if (!model.getListLoanNewApplicationInstallments().isEmpty()) {
                //model.setListLoanNewApplicationInstallments(new ArrayList<LoanNewApplicationInstallment>());
                doCalculateInstallmentSchedule();
            }

        } catch (Exception e) {
            Logger.getLogger(LoanApplicationFormController.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public void updateDataPinjamanByRangeCicilan() {
        try {
            if (null != model.getSelectedLoanNewSchemaListOfType()) {
                model.setLoanPeriod(model.getSelectedLoanNewSchemaListOfType().getMaxPeriode() - model.getRangeFirstInstallmentToDisbursement());
            }

            if (!model.getListLoanNewApplicationInstallments().isEmpty()) {
                //model.setListLoanNewApplicationInstallments(new ArrayList<LoanNewApplicationInstallment>());
                doCalculateInstallmentSchedule();
            }
        } catch (Exception e) {
            Logger.getLogger(LoanApplicationFormController.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public void updateDataPinjamanByTermin() {
        try {

            if (!model.getListLoanNewApplicationInstallments().isEmpty()) {
                //model.setListLoanNewApplicationInstallments(new ArrayList<LoanNewApplicationInstallment>());
                doCalculateInstallmentSchedule();
            }
        } catch (Exception e) {
            Logger.getLogger(LoanApplicationFormController.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public void updateDataPinjamanExpectedDisbursementDate() {
        try {

            if (!model.getListLoanNewApplicationInstallments().isEmpty()) {
                //model.setListLoanNewApplicationInstallments(new ArrayList<LoanNewApplicationInstallment>());
                doCalculateInstallmentSchedule();
            }

        } catch (Exception ex) {
            Logger.getLogger(LoanApplicationFormController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<EmpData> completeEmpData(String query) {
        try {
            List<EmpData> allEmpData = empDataService.getAllDataByNameOrNik(StringUtils.stripToEmpty(query));
            return allEmpData;
        } catch (Exception ex) {
            Logger.getLogger(LoanApplicationFormController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public void doCalculateInstallmentSchedule() {
        try {

            Double interest = model.getSelectedLoanNewSchemaListOfType().getLoanNewType().getInterest().doubleValue();
            Integer termin = model.getTermin();
            Date disbursementDate = DateTimeUtil.getDateFrom(model.getExpectedDisbursementDate(), model.getRangeFirstInstallmentToDisbursement(), CommonUtilConstant.DATE_FORMAT_MONTH);
            Double loanNominal = model.getNominalLoan();
            Integer interestMethod = model.getSelectedLoanNewSchemaListOfType().getLoanNewType().getInterestMethod();

            List<LoanNewApplicationInstallment> listLoanInstallments = loanNewApplicationService.getAllDataLoanNewApplicationInstallment(interest, termin, disbursementDate, loanNominal, interestMethod);
            model.setListLoanNewApplicationInstallments(listLoanInstallments);

        } catch (Exception e) {
            Logger.getLogger(LoanApplicationFormController.class.getName()).log(Level.SEVERE, null, e);

        }

    }

    private LoanNewApplication getEntityFromModel(LoanApplicationFormModel model) {

        LoanNewApplication loanNewApplication = new LoanNewApplication();

        loanNewApplication.setApplicationDate(model.getLoanDate());
        loanNewApplication.setDibursementDate(model.getExpectedDisbursementDate());
        loanNewApplication.setBufferTime(model.getRangeFirstInstallmentToDisbursement());
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
            model.setRangeFirstInstallmentToDisbursement(entity.getBufferTime());
            model.setTermin(entity.getTermin());
            model.setDescription(entity.getDescription());
            
            //Assign loanNewTypeId with LoanNewType id to trigger selectedLoanNewSchemeListOfType on method updateDataPinjamanByLoanNewType
            loanNewTypeId = entity.getLoanNewType().getId();   
            System.out.println("loanNewTypeId : " + loanNewTypeId);
            this.updateDataPinjamanByEmployee();
            this.updateDataPinjamanByLoanNewType();
            this.doCalculateInstallmentSchedule();
            
            
            //Re-assign loanNewType Id with id of selectedLoanNewSchemeListOfType
            //bacause at UI, list LoanType key is using id of LoanNewSchemeListOfType instead of  LoanNewType id
            loanNewTypeId = model.getSelectedLoanNewSchemaListOfType().getId();
            if(null != entity.getSubsidizedDiscOfInterest() || null != entity.getSubsidizedNominal()){
                model.setIsSubsidi(Boolean.TRUE);
                
                if(null != entity.getSubsidizedNominal()){
                    subsidiType = 1l;
                    model.setSubsidiCicilan(entity.getSubsidizedNominal());
                }else if(null != entity.getSubsidizedDiscOfInterest()){
                    subsidiType = 2l;
                    model.setSubsidiBunga(entity.getSubsidizedDiscOfInterest());
                }
            }


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

    public Long getSubsidiType() {
        return subsidiType;
    }

    public void setSubsidiType(Long subsidiType) {
        this.subsidiType = subsidiType;
    }

    public Map<String, Long> getMapSubsidiType() {
        return mapSubsidiType;
    }

    public void setMapSubsidiType(Map<String, Long> mapSubsidiType) {
        this.mapSubsidiType = mapSubsidiType;
    }

    public LoanNewApplicationService getLoanNewApplicationService() {
        return loanNewApplicationService;
    }

    public void setLoanNewApplicationService(LoanNewApplicationService loanNewApplicationService) {
        this.loanNewApplicationService = loanNewApplicationService;
    }

    public Long getLoanNewTypeId() {
        return loanNewTypeId;
    }

    public void setLoanNewTypeId(Long loanNewTypeId) {
        this.loanNewTypeId = loanNewTypeId;
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

    public Map<String, Long> getMapLoanNewType() {
        return mapLoanNewType;
    }

    public void setMapLoanNewType(Map<String, Long> mapLoanNewType) {
        this.mapLoanNewType = mapLoanNewType;
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

    public LoanApplicationFormModel getModel() {
        return model;
    }

    public void setModel(LoanApplicationFormModel model) {
        this.model = model;
    }

    public EmpDataService getEmpDataService() {
        return empDataService;
    }

    public void setEmpDataService(EmpDataService empDataService) {
        this.empDataService = empDataService;
    }

    public Boolean getIsRevised() {
        return isRevised;
    }

    public void setIsRevised(Boolean isRevised) {
        this.isRevised = isRevised;
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
    
    
}
