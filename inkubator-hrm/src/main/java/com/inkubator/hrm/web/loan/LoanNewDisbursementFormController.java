/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.loan;

import ch.lambdaj.Lambda;
import ch.lambdaj.group.Group;
import com.google.gson.Gson;
import com.inkubator.exception.BussinessException;
import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.entity.ApprovalActivity;
import com.inkubator.hrm.entity.EmpData;
import com.inkubator.hrm.entity.HrmUser;
import com.inkubator.hrm.entity.LoanNewApplication;
import com.inkubator.hrm.entity.LoanNewApplicationInstallment;
import com.inkubator.hrm.entity.LoanNewSchemaListOfType;
import com.inkubator.hrm.entity.TransactionCodefication;
import com.inkubator.hrm.json.util.JsonUtil;
import com.inkubator.hrm.service.ApprovalActivityService;
import com.inkubator.hrm.service.EmpDataService;
import com.inkubator.hrm.service.HrmUserService;
import com.inkubator.hrm.service.LoanNewApplicationService;
import com.inkubator.hrm.service.LoanNewCancelationService;
import com.inkubator.hrm.service.LoanNewSchemaListOfTypeService;
import com.inkubator.hrm.service.TransactionCodeficationService;
import com.inkubator.hrm.util.HrmUserInfoUtil;
import com.inkubator.hrm.util.KodefikasiUtil;
import com.inkubator.hrm.web.lazymodel.LoanNewDisbursementLazyDataModel;
import com.inkubator.hrm.web.model.LoanNewCancellationFormModel;
import com.inkubator.hrm.web.model.LoanNewDisbursementFormModel;
import com.inkubator.hrm.web.search.LoanNewSearchParameter;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesUtil;
import com.inkubator.webcore.util.MessagesResourceUtil;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
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
import org.primefaces.model.LazyDataModel;

/**
 *
 * @author Ahmad Mudzakkir Amal
 */
@ManagedBean(name = "loanNewDisbursementFormController")
@ViewScoped
public class LoanNewDisbursementFormController extends BaseController {

    @ManagedProperty(value = "#{loanNewSchemaListOfTypeService}")
    private LoanNewSchemaListOfTypeService loanNewSchemaListOfTypeService;
    @ManagedProperty(value = "#{empDataService}")
    private EmpDataService empDataService;  
    @ManagedProperty(value = "#{approvalActivityService}")
    private ApprovalActivityService approvalActivityService;
    @ManagedProperty(value = "#{hrmUserService}")
    private HrmUserService hrmUserService;
    @ManagedProperty(value = "#{transactionCodeficationService}")
    private TransactionCodeficationService transactionCodeficationService;
    @ManagedProperty(value = "#{loanNewApplicationService}")
    private LoanNewApplicationService loanNewApplicationService;    
    private LoanNewDisbursementFormModel model;
    private LazyDataModel<LoanNewApplication> lazy;
    private LoanNewSearchParameter loanNewSearchParameter;

    @PostConstruct
    @Override
    public void initialization() {

        super.initialization();
        model = new LoanNewDisbursementFormModel();

        try {

//            TransactionCodefication transactionCodefication = transactionCodeficationService.getEntityByModulCode(HRMConstant.LOAN_CANCELLATION_KODE);
//            Long currentMaxLoanId = loanNewCancelationService.getCurrentMaxId();
//            model.setDisbursementCode(KodefikasiUtil.getKodefikasi(((int) currentMaxLoanId.longValue()), transactionCodefication.getCode()));

//            isAdmin = Lambda.exists(HrmUserInfoUtil.getRoles(), Matchers.containsString(HRMConstant.ADMINISTRATOR_ROLE));
//            if (!isAdmin) { //jika bukan administrator, langsung di set empData berdasarkan yang login
//
//                model.setEmpData(HrmUserInfoUtil.getEmpData());
//                model.setNamakaryawan(HrmUserInfoUtil.getRealName());
//                this.updateDataPinjamanByEmployee();
//
//            }

        } catch (Exception ex) {
            Logger.getLogger(LoanNewDisbursementFormController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @PreDestroy
    public void cleanAndExit() {
        
        loanNewApplicationService = null;
        transactionCodeficationService = null;
        loanNewSchemaListOfTypeService = null;
        transactionCodeficationService = null;
        empDataService = null;
        approvalActivityService = null;
        hrmUserService = null;
        model = null;
        
    }

    public String doDisburse() {
//        try {
//            loanNewApplicationService.cancelLoanApplicationAndSaveToLoanNewCancellation(model);
//            MessagesResourceUtil.setMessagesFlas(FacesMessage.SEVERITY_INFO, "global.save_info", "global.update_successfully", FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
//            cleanAndExit();
//            return "/protected/personalia/loan_new_cancellation_form.htm?faces-redirect=true";
//        } catch (BussinessException ex) {
//            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_ERROR, "global.error", ex.getErrorKeyMessage(), FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
//        } catch (Exception ex) {
//            Logger.getLogger(LoanNewDisbursementFormController.class.getName()).log(Level.SEVERE, null, ex);
//        }
        return null;
    }

    public void doReset() {

        try {

            model = new LoanNewDisbursementFormModel();
//            TransactionCodefication transactionCodefication = transactionCodeficationService.getEntityByModulCode(HRMConstant.LOAN_CANCELLATION_KODE);
//            Long currentMaxLoanId = loanNewCancelationService.getCurrentMaxId();
//            model.setCancellationNumber(KodefikasiUtil.getKodefikasi(((int) currentMaxLoanId.longValue()), transactionCodefication.getCode()));

        } catch (Exception ex) {
            Logger.getLogger(LoanNewDisbursementFormController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public String doBack() {
        cleanAndExit();
        return "/protected/home.htm?faces-redirect=true";
    }

    public void updateDataPinjamanByCoa() {
        try {

//            ApprovalActivity selectedApprovalActivity = approvalActivityService.getEntiyByPK(model.getLoanPendingActivity());
//            String pendingData = selectedApprovalActivity.getPendingData();
//            convertJsonToModel(pendingData);

        } catch (Exception ex) {
            Logger.getLogger(LoanNewDisbursementFormController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void updateDataPinjamanByEmployee() {
        try {

//            model.setNamakaryawan(model.getEmpData().getNikWithFullName());
//            HrmUser hrmUser = hrmUserService.getByEmpDataId(model.getEmpData().getId());
//            Map<String, Long> mapNomorActivity = new HashMap<>();
//            
//            // get list pending request activity
//            List<ApprovalActivity> listPending = approvalActivityService.getPendingRequest(hrmUser.getUserId());
//
//            //filter only activity that comes from LOAN 
//            listPending = Lambda.select(listPending, Lambda.having(Lambda.on(ApprovalActivity.class).getApprovalDefinition().getName(), Matchers.equalTo(HRMConstant.LOAN)));
//
//            //grouping list by ActivityNumber
//            Group<ApprovalActivity> groupPendingActivity = Lambda.group(listPending, Lambda.by(Lambda.on(ApprovalActivity.class).getActivityNumber()));
//
//            //iterate each group list element
//            for (String key : groupPendingActivity.keySet()) {
//                List<ApprovalActivity> listGroupedActivity = groupPendingActivity.find(key);
//
//                if (!listGroupedActivity.isEmpty()) {
//                    
//                    // filter hanya yang approval nya masih dalam menunggu proses persetujuan, dan belum ada satupun dari para approver (jika memang lebih dari satu approver) yang yang sudah approve (sequence baru 1 record per activity number)
//                    if (listGroupedActivity.size() == 1 && Objects.equals(listGroupedActivity.get(0).getApprovalStatus(), HRMConstant.APPROVAL_STATUS_WAITING_APPROVAL)) {
//                        
//                        ApprovalActivity approvalActivity = listGroupedActivity.get(0);
//                        String pendingData = approvalActivity.getPendingData();
//
//                        Gson gson = JsonUtil.getHibernateEntityGsonBuilder().create();
//                        LoanNewApplication loanNewApplication = gson.fromJson(pendingData, LoanNewApplication.class);
//                        mapNomorActivity.put(loanNewApplication.getNomor(), approvalActivity.getId());
//                    }
//                }
//
//            }
//
//            model.setMapNomorActivity(mapNomorActivity);

        } catch (Exception e) {
            Logger.getLogger(LoanNewDisbursementFormController.class.getName()).log(Level.SEVERE, null, e);
        }

    }

    public List<EmpData> completeEmpData(String query) {
        try {
            List<EmpData> allEmpData = empDataService.getAllDataByNameOrNik(StringUtils.stripToEmpty(query));
            return allEmpData;
        } catch (Exception ex) {
            Logger.getLogger(LoanNewDisbursementFormController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    private void convertJsonToModel(String json) {
        try {

            Gson gson = JsonUtil.getHibernateEntityGsonBuilder().create();
            LoanNewApplication entity = gson.fromJson(json, LoanNewApplication.class);

//            model.setLoanDate(entity.getApplicationDate());
//            model.setPurpose(entity.getPurposeNote());
//            model.setNamakaryawan(model.getEmpData().getNikWithFullName());
//            model.setLoanNewSchema(entity.getLoanNewSchema());
//            model.setLoanNewType(entity.getLoanNewType());
//            model.setExpectedDisbursementDate(entity.getDibursementDate());
//            model.setTermin(entity.getTermin());
//            model.setFirstPaymentDate(entity.getFirstLoanPaymentDate());
//            model.setLastPaymentDate(entity.getMaxLoanPaymentDate());
//            model.setNominalLoan(entity.getNominalPrincipal());
//            model.setLoanNumber(entity.getNomor());
//
//            List<LoanNewApplication> listExisitingLoanWithSameSchema = loanNewApplicationService.getListLoanDisbursedOrPaidByEmpDataIdAndLoanNewSchemaId(entity.getEmpData().getId(), entity.getLoanNewSchema().getId());
//            Double totalUsedLoan = Lambda.sum(listExisitingLoanWithSameSchema, Lambda.on(LoanNewApplication.class).getNominalPrincipal());
//            model.setNominalUsedLoan(totalUsedLoan);
//            model.setTotalNominalUsedLoan(totalUsedLoan + entity.getNominalPrincipal());
//
//            LoanNewSchemaListOfType loanNewSchemaListOfType = loanNewSchemaListOfTypeService.getEntityByLoanNewSchemaIdAndLoanNewTypeIdWithDetail(entity.getLoanNewSchema().getId(), entity.getLoanNewType().getId());
//            model.setMaxLoanAmount(loanNewSchemaListOfType.getMaximumAllocation());
//            model.setMinLoanAmount(loanNewSchemaListOfType.getMinimumAllocation());
//            model.setMaxTermin(loanNewSchemaListOfType.getMaxPeriode());
//
//            model.setTermin(entity.getTermin());
//            model.setMaximumInstallment(entity.getLoanNewSchema().getTotalMaximumInstallment().doubleValue());
//            model.setDescription(entity.getDescription());
//
//            if (entity.getSubsidizedDiscOfInterest() == null && entity.getSubsidizedNominal() == null) {
//                model.setSubsidiType("-");
//            } else {
//                if (entity.getSubsidizedDiscOfInterest() != null) {
//
//                    model.setSubsidiType("Bunga");
//                    model.setNilaiSubsidi(entity.getSubsidizedDiscOfInterest().doubleValue());
//
//                } else if (entity.getSubsidizedNominal() != null) {
//
//                    model.setSubsidiType("Nominal");
//                    model.setNilaiSubsidi(entity.getSubsidizedNominal());
//                }
//            }
//
//            List<LoanNewApplicationInstallment> listInstallments = loanNewApplicationService.getAllDataLoanNewApplicationInstallment(entity.getLoanNewType().getInterest().doubleValue(), entity.getTermin(), entity.getFirstLoanPaymentDate(), entity.getNominalPrincipal(), entity.getLoanNewType().getInterestMethod());
//            model.setInstallment(listInstallments.get(0).getTotalPayment());

        } catch (Exception e) {
            Logger.getLogger(LoanNewDisbursementFormController.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public void setLoanNewApplicationService(LoanNewApplicationService loanNewApplicationService) {
        this.loanNewApplicationService = loanNewApplicationService;
    }

    public void setLoanNewSchemaListOfTypeService(LoanNewSchemaListOfTypeService loanNewSchemaListOfTypeService) {
        this.loanNewSchemaListOfTypeService = loanNewSchemaListOfTypeService;
    }

    public LoanNewDisbursementFormModel getModel() {
        return model;
    }

    public void setModel(LoanNewDisbursementFormModel model) {
        this.model = model;
    }

    public void setEmpDataService(EmpDataService empDataService) {
        this.empDataService = empDataService;
    }

    public void setApprovalActivityService(ApprovalActivityService approvalActivityService) {
        this.approvalActivityService = approvalActivityService;
    }

    public void setHrmUserService(HrmUserService hrmUserService) {
        this.hrmUserService = hrmUserService;
    }

    public void setTransactionCodeficationService(TransactionCodeficationService transactionCodeficationService) {
        this.transactionCodeficationService = transactionCodeficationService;
    }

    public LazyDataModel<LoanNewApplication> getLazy() {
        if (lazy == null) {
            lazy = new LoanNewDisbursementLazyDataModel(loanNewSearchParameter, loanNewApplicationService);
        }
        return lazy;
    }

    public void setLazy(LazyDataModel<LoanNewApplication> lazy) {
        this.lazy = lazy;
    }

    public LoanNewSearchParameter getLoanNewSearchParameter() {
        return loanNewSearchParameter;
    }

    public void setLoanNewSearchParameter(LoanNewSearchParameter loanNewSearchParameter) {
        this.loanNewSearchParameter = loanNewSearchParameter;
    }
    
}
