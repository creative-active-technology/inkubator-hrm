package com.inkubator.hrm.web.reference;

import com.inkubator.exception.BussinessException;
import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.entity.Bank;
import com.inkubator.hrm.entity.BankGroup;
import com.inkubator.hrm.service.BankGroupService;
import com.inkubator.hrm.service.BankService;
import com.inkubator.hrm.web.model.BankModel;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesUtil;
import com.inkubator.webcore.util.MessagesResourceUtil;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author Taufik Hidayat
 */
@ManagedBean(name = "bankFormController")
@ViewScoped
public class BankFormController extends BaseController {

    private BankModel bankModel;
    private Boolean isUpdate;
    @ManagedProperty(value = "#{bankService}")
    private BankService bankService;
    @ManagedProperty(value = "#{bankGroupService}")
    private BankGroupService bankGroupService;
    private Map<String, Long> dropDownBank = new HashMap<String, Long>();
    private Map<String, Long> dropDownBankGroup = new HashMap<String, Long>();
    private Boolean isReadOnlyBankNameAndCode;
    private Boolean isReadOnlyBankNameAndCodeBranch;

    @PostConstruct
    @Override
    public void initialization() {
        super.initialization();
        String param = FacesUtil.getRequestParameter("execution");
        bankModel = new BankModel();
        isUpdate = Boolean.FALSE;
        isReadOnlyBankNameAndCode = Boolean.FALSE;
        isReadOnlyBankNameAndCodeBranch = Boolean.TRUE;
        try {
            if (StringUtils.isNotEmpty(param)) {
                Bank bank = bankService.getEntityWithDetail(Long.parseLong(param.substring(1)));
                if (bank != null) {
                    bankModel.setId(bank.getId());
                    bankModel.setSwiftCode(bank.getSwiftCode());
                    bankModel.setIban(bank.getIban());
                    bankModel.setBankIdentificationNumber(bank.getBankIdentificationNo());
                    bankModel.setDescription(bank.getDescription());
                    bankModel.setBranchCodeInput(bank.getBranchCode());
                    bankModel.setAddress(bank.getAddress());
                    bankModel.setBankPhone(bank.getBankPhone());
                    bankModel.setBankFax(bank.getBankFax());
                    bankModel.setBranchName(bank.getBranchName());
                    if (bank.getBankGroup().getId() != null) {
                        bankModel.setBankGroup(bank.getBankGroup().getId());
                    }
                    if (bank.getBank() != null) {
                        isReadOnlyBankNameAndCode = Boolean.TRUE;
                        isReadOnlyBankNameAndCodeBranch = Boolean.FALSE;
                        bankModel.setBank(bank.getBank().getId());
                        bankModel.setBankCode(bank.getBank().getBankCode());
                        bankModel.setBankName(bank.getBank().getBankName());
                        bankModel.setBranchCode(bank.getBank().getBankCode());
                    } else {
                        isReadOnlyBankNameAndCodeBranch = Boolean.TRUE;
                        isReadOnlyBankNameAndCode = Boolean.FALSE;
                        bankModel.setBranchName(bank.getBranchName());
                        bankModel.setBankCode(bank.getBankCode());
                        bankModel.setBankName(bank.getBankName());
                    }
                    isUpdate = Boolean.TRUE;
                }

            }

            // dropdown bank
            List<Bank> listBank = bankService.getAllData();
            for (Bank bnk : listBank) {
                dropDownBank.put(bnk.getBankName(), bnk.getId());
            }
            // dropdown grop bank
            List<BankGroup> listBankGroup = bankGroupService.getAllData();
            for (BankGroup bankGroup : listBankGroup) {
                dropDownBankGroup.put(bankGroup.getName(), bankGroup.getId());
            }
        } catch (Exception e) {
            LOGGER.error("Error", e);
        }
    }

    @PreDestroy
    public void cleanAndExit() {
        bankService = null;
        bankModel = null;
        isUpdate = null;
        dropDownBank = null;
        dropDownBankGroup = null;
        bankGroupService = null;
        isReadOnlyBankNameAndCode = null;
    }

    public BankModel getBankModel() {
        return bankModel;
    }

    public void setBankModel(BankModel bankModel) {
        this.bankModel = bankModel;
    }

    public Boolean getIsUpdate() {
        return isUpdate;
    }

    public void setIsUpdate(Boolean isUpdate) {
        this.isUpdate = isUpdate;
    }

    public void setBankService(BankService bankService) {
        this.bankService = bankService;
    }

    public Boolean getIsReadOnlyBankNameAndCode() {
        return isReadOnlyBankNameAndCode;
    }

    public void setIsReadOnlyBankNameAndCode(Boolean isReadOnlyBankNameAndCode) {
        this.isReadOnlyBankNameAndCode = isReadOnlyBankNameAndCode;
    }

    public String doSave() {
        System.out.println("masuk method");
        Bank bank = getEntityFromViewModel(bankModel);
        System.out.println("berhasil bikin object");
        try {
            if (isUpdate) {
                bankService.update(bank);
            } else {
                bankService.save(bank);
            }
            cleanAndExit();

            MessagesResourceUtil.setMessagesFlas(FacesMessage.SEVERITY_INFO, "global.save_info", "global.added_successfully",
                    FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
            return "/protected/reference/bank_view.htm?faces-redirect=true";
        } catch (BussinessException ex) {
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_ERROR, "global.error", ex.getErrorKeyMessage(), FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }

        return null;
    }

    private Bank getEntityFromViewModel(BankModel bankModel) {
        Bank bank = new Bank();
        if (bankModel.getId() != null) {
            bank.setId(bankModel.getId());
        }
        if (bankModel.getBank() == null || bankModel.getBank() == 0) {
            bank.setBankCode(bankModel.getBankCode());
            bank.setBankName(bankModel.getBankName());
        }
        bank.setSwiftCode(bankModel.getSwiftCode());
        bank.setIban(bankModel.getIban());
        bank.setBankIdentificationNo(bankModel.getBankIdentificationNumber());
        bank.setDescription(bankModel.getDescription());
        bank.setBranchCode(bankModel.getBranchCodeInput());
        bank.setBranchName(bankModel.getBranchName());
        bank.setAddress(bankModel.getAddress());
        bank.setBankPhone(bankModel.getBankPhone());
        bank.setBankFax(bankModel.getBankFax());
        bank.setBankGroup(new BankGroup(bankModel.getBankGroup()));
        bank.setBank(new Bank(bankModel.getBank()));
        return bank;
    }

    public void doReset() {
        bankModel.setAddress(null);
        bankModel.setBankCode(null);
        bankModel.setBankFax(null);
        bankModel.setBankGroup(null);
        bankModel.setBankName(null);
        bankModel.setBankPhone(null);
        bankModel.setBranchCode(null);
        bankModel.setBranchName(null);
        bankModel.setIban(null);
        bankModel.setSwiftCode(null);
        bankModel.setDescription(null);
    }

    public void doChangeReadOnlyBankNameAndCode() throws Exception {
        if (bankModel.getBank() == 0) {
            isReadOnlyBankNameAndCode = Boolean.FALSE;
            isReadOnlyBankNameAndCodeBranch = Boolean.TRUE;
            bankModel.setBankCode(null);
            bankModel.setBankName(null);
            bankModel.setBranchCode(null);
        } else if (bankModel.getBank() != null) {
            isReadOnlyBankNameAndCode = Boolean.TRUE;
            isReadOnlyBankNameAndCodeBranch = Boolean.FALSE;
            Bank bank = bankService.getEntiyByPK(bankModel.getBank());
            bankModel.setBankCode(bank.getBankCode());
            bankModel.setBankName(bank.getBankName());
            bankModel.setBranchCode(bank.getBankCode());
        }
    }

    public String doBack() {
        return "/protected/reference/bank_view.htm?faces-redirect=true";
    }

    public BankGroupService getBankGroupService() {
        return bankGroupService;
    }

    public void setBankGroupService(BankGroupService bankGroupService) {
        this.bankGroupService = bankGroupService;
    }

    public Map<String, Long> getDropDownBank() {
        return dropDownBank;
    }

    public void setDropDownBank(Map<String, Long> dropDownBank) {
        this.dropDownBank = dropDownBank;
    }

    public Map<String, Long> getDropDownBankGroup() {
        return dropDownBankGroup;
    }

    public void setDropDownBankGroup(Map<String, Long> dropDownBankGroup) {
        this.dropDownBankGroup = dropDownBankGroup;
    }

    public Boolean getIsReadOnlyBankNameAndCodeBranch() {
        return isReadOnlyBankNameAndCodeBranch;
    }

    public void setIsReadOnlyBankNameAndCodeBranch(Boolean isReadOnlyBankNameAndCodeBranch) {
        this.isReadOnlyBankNameAndCodeBranch = isReadOnlyBankNameAndCodeBranch;
    }

}
