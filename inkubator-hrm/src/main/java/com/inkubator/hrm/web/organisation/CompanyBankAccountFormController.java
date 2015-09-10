package com.inkubator.hrm.web.organisation;

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
import com.inkubator.hrm.entity.SavingType;
import com.inkubator.hrm.service.BankService;
import com.inkubator.hrm.service.CompanyBankAccountService;
import com.inkubator.hrm.service.SavingTypeService;
import com.inkubator.hrm.web.model.CompanyBankAccountModel;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesUtil;
import com.inkubator.webcore.util.MessagesResourceUtil;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author rizkykojek
 */
@ManagedBean(name = "companyBankAccountFormController")
@ViewScoped
public class CompanyBankAccountFormController extends BaseController {

    private CompanyBankAccountModel accountModel;
    private Boolean isUpdate;
//    private List<Bank> banks;
    private List<SavingType> savingTypes;
    @ManagedProperty(value = "#{bankService}")
    private BankService bankService;
    @ManagedProperty(value = "#{savingTypeService}")
    private SavingTypeService savingTypeService;
    @ManagedProperty(value = "#{companyBankAccountService}")
    private CompanyBankAccountService companyBankAccountService;
    private Map<String, Long> dropDownBank = new HashMap<String, Long>();

    @PostConstruct
    @Override
    public void initialization() {
        super.initialization();
        accountModel = new CompanyBankAccountModel();
        System.out.println(" Hehehrh eksekusisii");
        try {
            isUpdate = Boolean.FALSE;
            List<Bank> listBank = bankService.getAllWithparent();
            for (Bank bnk : listBank) {
                if (bnk.getBankName() != null) {
                    dropDownBank.put(bnk.getBankName(), bnk.getId());
                } else {
                    dropDownBank.put(bnk.getBank().getBankName() + " - " + bnk.getBranchName(), bnk.getId());
                }
            }
            savingTypes = savingTypeService.getAllData();

            String companyId = FacesUtil.getRequestParameter("companyId");
            accountModel.setCompanyId(Long.parseLong(companyId));

            String companyBankAccountId = FacesUtil.getRequestParameter("companyBankAccountId");
            if (StringUtils.isNotEmpty(companyBankAccountId)) {
                CompanyBankAccount companyBankAccount = companyBankAccountService.getEntiyByPK(Long.parseLong(companyBankAccountId));
                if (companyBankAccount != null) {
                    accountModel = getModelFromEntity(companyBankAccount);
                    isUpdate = Boolean.TRUE;
                }
            }
        } catch (Exception e) {
            LOGGER.error("Error", e);
        }
    }

    @PreDestroy
    public void cleanAndExit() {
//        accountModel = null;
        isUpdate = null;
//        banks = null;
        savingTypes = null;
        bankService = null;
        savingTypeService = null;
        companyBankAccountService = null;
    }

    public CompanyBankAccountModel getAccountModel() {
        return accountModel;
    }

    public void setAccountModel(CompanyBankAccountModel accountModel) {
        this.accountModel = accountModel;
    }

    public Boolean getIsUpdate() {
        return isUpdate;
    }

    public void setIsUpdate(Boolean isUpdate) {
        this.isUpdate = isUpdate;
    }

//    public List<Bank> getBanks() {
//        return banks;
//    }
//
//    public void setBanks(List<Bank> banks) {
//        this.banks = banks;
//    }
    public List<SavingType> getSavingTypes() {
        return savingTypes;
    }

    public void setSavingTypes(List<SavingType> savingTypes) {
        this.savingTypes = savingTypes;
    }

    public void setBankService(BankService bankService) {
        this.bankService = bankService;
    }

    public void setSavingTypeService(SavingTypeService savingTypeService) {
        this.savingTypeService = savingTypeService;
    }

    public void setCompanyBankAccountService(
            CompanyBankAccountService companyBankAccountService) {
        this.companyBankAccountService = companyBankAccountService;
    }

    public void doSave() {
        CompanyBankAccount companyBankAccount = getEntityFromViewModel(accountModel);
        try {
            if (isUpdate) {
                companyBankAccountService.update(companyBankAccount);
                RequestContext.getCurrentInstance().closeDialog(HRMConstant.UPDATE_CONDITION);
            } else {
                companyBankAccountService.save(companyBankAccount);
                RequestContext.getCurrentInstance().closeDialog(HRMConstant.SAVE_CONDITION);
            }
            cleanAndExit();
        } catch (BussinessException ex) {
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_ERROR, "global.error", ex.getErrorKeyMessage(), FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
    }

    public void doReset() {
        if (isUpdate) {
            try {
                CompanyBankAccount companyBankAccount = companyBankAccountService.getEntiyByPK(accountModel.getId());
                if (companyBankAccount != null) {
                    accountModel = getModelFromEntity(companyBankAccount);
                }
            } catch (Exception ex) {
                LOGGER.error("Error", ex);
            }
        } else {
            accountModel = new CompanyBankAccountModel();
        }
    }

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

    public Map<String, Long> getDropDownBank() {
        return dropDownBank;
    }

    public void setDropDownBank(Map<String, Long> dropDownBank) {
        this.dropDownBank = dropDownBank;
    }

}
