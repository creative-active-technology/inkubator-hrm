package com.inkubator.hrm.web.personalia;

import com.inkubator.hrm.web.organisation.*;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.apache.commons.lang3.StringUtils;
import org.primefaces.context.RequestContext;

import com.inkubator.exception.BussinessException;
import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.entity.Bank;
import com.inkubator.hrm.entity.Company;
import com.inkubator.hrm.entity.CompanyBankAccount;
import com.inkubator.hrm.entity.EmpData;
import com.inkubator.hrm.entity.OhsaEmpInvolve;
import com.inkubator.hrm.entity.OhsaIncident;
import com.inkubator.hrm.entity.SavingType;
import com.inkubator.hrm.service.BankService;
import com.inkubator.hrm.service.CompanyBankAccountService;
import com.inkubator.hrm.service.EmpDataService;
import com.inkubator.hrm.service.OhsaEmpInvolveService;
import com.inkubator.hrm.service.OhsaIncidentService;
import com.inkubator.hrm.service.SavingTypeService;
import com.inkubator.hrm.web.model.CompanyBankAccountModel;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesUtil;
import com.inkubator.webcore.util.MessagesResourceUtil;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ahmad Mudzakkir Amal
 */
@ManagedBean(name = "ohsaEmpInvolveFormController")
@ViewScoped
public class OhsaEmpInvolveFormController extends BaseController {

    private Boolean isUpdate;
    private OhsaEmpInvolve ohsaEmpInvolve;
    @ManagedProperty(value = "#{ohsaEmpInvolveService}")
    private OhsaEmpInvolveService ohsaEmpInvolveService;
    @ManagedProperty(value = "#{empDataService}")
    private EmpDataService empDataService;
    @ManagedProperty(value = "#{ohsaIncidentService}")
    private OhsaIncidentService ohsaIncidentService;

    @PostConstruct
    @Override
    public void initialization() {
        super.initialization();
        try {

            isUpdate = Boolean.FALSE;
            ohsaEmpInvolve = new OhsaEmpInvolve();

            String selectedIncidentId = FacesUtil.getRequestParameter("selectedIncidentId");
            OhsaIncident selectedOhsaIncident = ohsaIncidentService.getEntiyByPK(Long.parseLong(selectedIncidentId));
            ohsaEmpInvolve.setOhsaIncident(selectedOhsaIncident);

        } catch (Exception e) {
            LOGGER.error("Error", e);
        }
    }

    @PreDestroy
    public void cleanAndExit() {
        ohsaEmpInvolve = null;
        isUpdate = null;
        ohsaEmpInvolveService = null;
        ohsaIncidentService = null;
        empDataService = null;
    }

    public Boolean getIsUpdate() {
        return isUpdate;
    }

    public void setIsUpdate(Boolean isUpdate) {
        this.isUpdate = isUpdate;
    }

    public OhsaEmpInvolve getOhsaEmpInvolve() {
        return ohsaEmpInvolve;
    }

    public void setOhsaEmpInvolve(OhsaEmpInvolve ohsaEmpInvolve) {
        this.ohsaEmpInvolve = ohsaEmpInvolve;
    }

    public OhsaEmpInvolveService getOhsaEmpInvolveService() {
        return ohsaEmpInvolveService;
    }

    public void setOhsaEmpInvolveService(OhsaEmpInvolveService ohsaEmpInvolveService) {
        this.ohsaEmpInvolveService = ohsaEmpInvolveService;
    }

    public EmpDataService getEmpDataService() {
        return empDataService;
    }

    public void setEmpDataService(EmpDataService empDataService) {
        this.empDataService = empDataService;
    }

    public OhsaIncidentService getOhsaIncidentService() {
        return ohsaIncidentService;
    }

    public void setOhsaIncidentService(OhsaIncidentService ohsaIncidentService) {
        this.ohsaIncidentService = ohsaIncidentService;
    }

    public void doSave() {

        try {
            if (isUpdate) {
              
                ohsaEmpInvolveService.update(ohsaEmpInvolve);
                RequestContext.getCurrentInstance().closeDialog(HRMConstant.UPDATE_CONDITION);
            } else {
              
                ohsaEmpInvolveService.save(ohsaEmpInvolve);
                RequestContext.getCurrentInstance().closeDialog(HRMConstant.SAVE_CONDITION);
            }
            cleanAndExit();
        } catch (BussinessException ex) {
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_ERROR, "global.error", ex.getErrorKeyMessage(), FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
    }

//	public void doReset() {
//        if (isUpdate) {
//            try {
//                ohsaEmpInvolve = companyBankAccountService.getEntiyByPK(ohsaEmpInvolve.getId());
//                if (companyBankAccount != null) {
//                    ohsaEmpInvolve = getModelFromEntity(companyBankAccount);
//                }
//            } catch (Exception ex) {
//                LOGGER.error("Error", ex);
//            }
//        } else {
//            ohsaEmpInvolve = new CompanyBankAccountModel();          
//        }
//    }
//	
    private CompanyBankAccountModel getModelFromEntity(CompanyBankAccount entity) {
        CompanyBankAccountModel model = new CompanyBankAccountModel();
        model.setId(entity.getId());
        model.setCompanyId(entity.getCompany().getId());
        model.setBankId(entity.getBank().getId());
        model.setAccountNumber(entity.getAccountNumber());
        model.setSavingTypeId(entity.getSavingType().getId());
        model.setAccountName(entity.getAccountName());
        model.setIsDefault(entity.getIsDefault());
        return model;

    }

    private CompanyBankAccount getEntityFromViewModel(CompanyBankAccountModel model) {
        CompanyBankAccount entity = new CompanyBankAccount();
        if (model.getId() != null) {
            entity.setId(model.getId());
        }
        entity.setCompany(new Company(model.getCompanyId()));
        entity.setBank(new Bank(model.getBankId()));
        entity.setAccountNumber(model.getAccountNumber());
        entity.setSavingType(new SavingType(model.getSavingTypeId()));
        entity.setAccountName(model.getAccountName());
        entity.setIsDefault(model.getIsDefault());

        return entity;
    }

    public List<EmpData> completeEmpData(String query) {
        try {
            List<EmpData> allEmpData = empDataService.getAllDataByNameOrNik(StringUtils.stripToEmpty(query));

            return allEmpData;
        } catch (Exception ex) {
            Logger.getLogger(OhsaEmpInvolveFormController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
