/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.personalia;

import ch.lambdaj.Lambda;
import com.inkubator.hrm.web.employee.*;
import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.entity.BioData;
import com.inkubator.hrm.entity.EmpData;
import com.inkubator.hrm.entity.GolonganJabatan;
import com.inkubator.hrm.entity.LoanNewApplicationInstallment;
import com.inkubator.hrm.entity.LoanNewSchemaListOfEmp;
import com.inkubator.hrm.entity.LoanNewSchemaListOfType;
import com.inkubator.hrm.entity.LoanNewSchemaListOfTypeId;
import com.inkubator.hrm.entity.LoanNewType;
import com.inkubator.hrm.entity.LoanPaymentDetail;
import com.inkubator.hrm.entity.TransactionCodefication;
import com.inkubator.hrm.entity.WtGroupWorking;
import com.inkubator.hrm.service.BioDataService;
import com.inkubator.hrm.service.EmpDataService;
import com.inkubator.hrm.service.GolonganJabatanService;
import com.inkubator.hrm.service.LoanNewApplicationService;
import com.inkubator.hrm.service.LoanNewSchemaListOfEmpService;
import com.inkubator.hrm.service.LoanNewSchemaListOfTypeService;
import com.inkubator.hrm.service.LoanNewSchemaService;
import com.inkubator.hrm.service.LoanNewTypeService;
import com.inkubator.hrm.service.LoanSchemaService;
import com.inkubator.hrm.service.LoanService;
import com.inkubator.hrm.service.LoanTypeService;
import com.inkubator.hrm.service.TempJadwalKaryawanService;
import com.inkubator.hrm.service.TransactionCodeficationService;
import com.inkubator.hrm.service.WtGroupWorkingService;
import com.inkubator.hrm.util.HrmUserInfoUtil;
import com.inkubator.hrm.util.KodefikasiUtil;
import com.inkubator.hrm.util.MapUtil;
import com.inkubator.hrm.web.model.LoanApplicationFormModel;
import com.inkubator.hrm.web.model.PlacementOfEmployeeWorkScheduleModel;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesUtil;
import com.inkubator.webcore.util.MessagesResourceUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

/**
 *
 * @author Ahmad Mudzakkir Amal
 */
@ManagedBean(name = "loanApplicationFormController")
@ViewScoped
public class LoanApplicationFormController extends BaseController {

    @ManagedProperty(value = "#{loanService}")
    private LoanService loanService;
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
    @ManagedProperty(value = "#{bioDataService}")
    private BioDataService bioDataService;
    @ManagedProperty(value = "#{transactionCodeficationService}")
    private TransactionCodeficationService transactionCodeficationService;
    private Boolean isAdmin;
//    private Boolean isSubsidiByCicilan;
//    private Boolean isSubsidiByBunga;
    private EmpData selectedEmployee;
    private Map<String, Long> mapLoanNewType = new HashMap<>();
    private Map<String, Long> mapSubsidiType = new HashMap<>();
    private Long subsidiType;
    private Long loanNewTypeId;
    private LoanNewSchemaListOfTypeId selectedLoanNewSchemaListOfTypeId;

    private LoanApplicationFormModel model;
    private List<EmpData> source = new ArrayList<>();

    @PostConstruct
    @Override
    public void initialization() {
        super.initialization();

        String param = FacesUtil.getRequestParameter("param");
        model = new LoanApplicationFormModel();
        model.setRangeFirstInstallmentToDisbursement(1);
        model.setListLoanNewApplicationInstallments(new ArrayList<LoanNewApplicationInstallment>());
        try {

            TransactionCodefication transactionCodefication = transactionCodeficationService.getEntityByModulCode((HRMConstant.LOAN_KODE));
            Long currentMaxLoanId = loanService.getCurrentMaxId();
            model.setNomor(KodefikasiUtil.getKodefikasi(((int) currentMaxLoanId.longValue()), transactionCodefication.getCode()));

            mapSubsidiType.put("Cicilan", 1l);
            mapSubsidiType.put("Bunga", 2l);
            
//            isSubsidiByCicilan = Boolean.FALSE;
//            isSubsidiByBunga = Boolean.FALSE;

            if (Lambda.exists(HrmUserInfoUtil.getRoles(), Matchers.containsString(HRMConstant.ADMINISTRATOR_ROLE))) {
                isAdmin = Boolean.TRUE;
            } else {

                isAdmin = Boolean.FALSE;
                model.setEmpData(HrmUserInfoUtil.getEmpData());
                model.setNamakaryawan(HrmUserInfoUtil.getRealName());
                LoanNewSchemaListOfEmp loanNewSchemaListOfEmp = loanNewSchemaListOfEmpService.getEntityWithDetailByEmpDataId(HrmUserInfoUtil.getEmpData().getId());
                model.setLoanNewSchemaListOfEmp(loanNewSchemaListOfEmp);

                List<LoanNewSchemaListOfType> listOfTypes = loanNewSchemaListOfTypeService.getEntityByLoanNewSchema(loanNewSchemaListOfEmp.getLoanNewSchema().getId());
                model.setListLoanNewSchemaListOfType(listOfTypes);

                for (LoanNewSchemaListOfType loanNewType : listOfTypes) {
                    mapLoanNewType.put(loanNewType.getLoanNewType().getLoanTypeName(), loanNewType.getId().getLoanNewTypeId());
                }

            }

        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }

    }

//    public Boolean getIsSubsidiByCicilan() {
//        return isSubsidiByCicilan;
//    }
//
//    public void setIsSubsidiByCicilan(Boolean isSubsidiByCicilan) {
//        this.isSubsidiByCicilan = isSubsidiByCicilan;
//    }
//
//    public Boolean getIsSubsidiByBunga() {
//        return isSubsidiByBunga;
//    }
//
//    public void setIsSubsidiByBunga(Boolean isSubsidiByBunga) {
//        this.isSubsidiByBunga = isSubsidiByBunga;
//    }

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

    public TransactionCodeficationService getTransactionCodeficationService() {
        return transactionCodeficationService;
    }

    public void setTransactionCodeficationService(TransactionCodeficationService transactionCodeficationService) {
        this.transactionCodeficationService = transactionCodeficationService;
    }

    public Long getLoanNewTypeId() {
        return loanNewTypeId;
    }

    public void setLoanNewTypeId(Long loanNewTypeId) {
        this.loanNewTypeId = loanNewTypeId;
    }

    public LoanNewSchemaListOfTypeId getSelectedLoanNewSchemaListOfTypeId() {
        return selectedLoanNewSchemaListOfTypeId;
    }

    public void setSelectedLoanNewSchemaListOfTypeId(LoanNewSchemaListOfTypeId selectedLoanNewSchemaListOfTypeId) {
        this.selectedLoanNewSchemaListOfTypeId = selectedLoanNewSchemaListOfTypeId;
    }

    public EmpData getSelectedEmployee() {
        return selectedEmployee;
    }

    public void setSelectedEmployee(EmpData selectedEmployee) {
        this.selectedEmployee = selectedEmployee;
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

    public BioDataService getBioDataService() {
        return bioDataService;
    }

    public void setBioDataService(BioDataService bioDataService) {
        this.bioDataService = bioDataService;
    }

    @PreDestroy
    private void cleanAndExit() {
        loanService = null;
        loanNewSchemaService = null;
        loanNewTypeService = null;
        empDataService = null;
        model = null;

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

    public LoanService getLoanService() {
        return loanService;
    }

    public void setLoanService(LoanService loanService) {
        this.loanService = loanService;
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

    public List<EmpData> getSource() {
        return source;
    }

    public void setSource(List<EmpData> source) {
        this.source = source;
    }

    public String doSave() {

        try {
//            List<EmpData> dataToSave = dualListModel.getTarget();
//            tempJadwalKaryawanService.saveMassPenempatanJadwal(dataToSave, model.getWorkingGroupId());
//            MessagesResourceUtil.setMessagesFlas(FacesMessage.SEVERITY_INFO, "global.save_info", "global.added_successfully",
//                    FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
//            return "/protected/employee/emp_schedule_view.htm?faces-redirect=true";

        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
        return null;
    }

    public void doReset() {
        model.setNomor(null);
        model.setLoanId(null);
    }

    public String doBack() {
        return "/protected/employee/emp_schedule_view.htm?faces-redirect=true";
    }

    public void onChangeSubsidiIsRequired() {
        System.out.println("Ada subsidi ? " + model.getIsSubsidi());
        if(!model.getIsSubsidi()){
            subsidiType = 0l;
            model.setSubsidiBunga(null);
            model.setSubsidiCicilan(null);
        }            
        
    }
    
   

    public void onChangeSubsidi() {
        System.out.println("Subsidi Type : " + subsidiType);
        
//        if(subsidiType == 1){
//            isSubsidiByCicilan = Boolean.TRUE;
//            isSubsidiByBunga = Boolean.FALSE;
//        }else if(subsidiType == 2){
//            isSubsidiByBunga = Boolean.TRUE;
//            isSubsidiByCicilan = Boolean.FALSE;
//        }
    }

    public void updateDataPeriod() {

        try {
            LoanNewSchemaListOfType loanNewSchemaListOfType = loanNewSchemaListOfTypeService.getEntityByLoanNewSchemaIdAndLoanNewTypeIdWithDetail(model.getLoanNewSchemaListOfEmp().getLoanNewSchema().getId(), loanNewTypeId);

            System.out.println("loanNewSchemaListOfType == null ? " + (loanNewSchemaListOfType == null));
            model.setSelectedLoanNewSchemaListOfType(loanNewSchemaListOfType);
            model.setMinimumInstallment(loanNewSchemaListOfType.getMinimumMonthlyInstallment());

            if (null != model.getRangeFirstInstallmentToDisbursement()) {
                model.setLoanPeriod(loanNewSchemaListOfType.getMaxPeriode() - model.getRangeFirstInstallmentToDisbursement());
            } else {
                model.setLoanPeriod(loanNewSchemaListOfType.getMaxPeriode());
            }
        } catch (Exception ex) {
            Logger.getLogger(LoanApplicationFormController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void updateDataPinjamanByEmployee() {
        try {

            model.setNamakaryawan(selectedEmployee.getNikWithFullName());
            LoanNewSchemaListOfEmp loanNewSchemaListOfEmp = loanNewSchemaListOfEmpService.getEntityWithDetailByEmpDataId(selectedEmployee.getId());
            model.setLoanNewSchemaListOfEmp(loanNewSchemaListOfEmp);

            List<LoanNewSchemaListOfType> listOfTypes = loanNewSchemaListOfTypeService.getEntityByLoanNewSchema(loanNewSchemaListOfEmp.getLoanNewSchema().getId());
            model.setListLoanNewSchemaListOfType(listOfTypes);

            for (LoanNewSchemaListOfType loanNewSchemaListOfType : listOfTypes) {
                mapLoanNewType.put(loanNewSchemaListOfType.getLoanNewType().getLoanTypeName(), loanNewSchemaListOfType.getId().getLoanNewTypeId());
            }

            Double maxLoan = Lambda.sum(listOfTypes, Lambda.on(LoanNewSchemaListOfType.class).getMaximumAllocation().doubleValue());
            Double minLoan = Lambda.sum(listOfTypes, Lambda.on(LoanNewSchemaListOfType.class).getMinimumAllocation().doubleValue());

            model.setMaxLoanAmount(maxLoan);
            model.setMinLoanAmount(minLoan);

            if (null != model.getNominalLoan()) {
                model.setAvailableLoanAmount(maxLoan - model.getNominalLoan());
            } else {
                model.setAvailableLoanAmount(maxLoan);
            }
            
            

        } catch (Exception e) {
            Logger.getLogger(LoanApplicationFormController.class.getName()).log(Level.SEVERE, null, e);
        }

    }

    public void updateDataPinjamanByJumlahPinjaman() {
        try {
            System.out.println("model.getNominalLoan() : " + model.getNominalLoan());
            if (null != model.getNominalLoan()) {
                model.setAvailableLoanAmount(model.getMaxLoanAmount() - model.getNominalLoan());
            }

        } catch (Exception e) {
            Logger.getLogger(LoanApplicationFormController.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public void updateDataPinjamanByRangeCicilan() {
        try {
            System.out.println("model.getRangeFirstInstallmentToDisbursement() : " + model.getRangeFirstInstallmentToDisbursement());
            if (null != model.getSelectedLoanNewSchemaListOfType()) {
                model.setLoanPeriod(model.getSelectedLoanNewSchemaListOfType().getMaxPeriode() - model.getRangeFirstInstallmentToDisbursement());
            }

        } catch (Exception e) {
            Logger.getLogger(LoanApplicationFormController.class.getName()).log(Level.SEVERE, null, e);
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
}
