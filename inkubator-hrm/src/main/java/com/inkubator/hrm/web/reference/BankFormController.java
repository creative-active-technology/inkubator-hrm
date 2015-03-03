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

    @PostConstruct
    @Override
    public void initialization() {
        super.initialization();
        String param = FacesUtil.getRequestParameter("execution");
        bankModel = new BankModel();
        isUpdate = Boolean.FALSE;

        try {
            if (StringUtils.isNotEmpty(param)) {
                Bank bank = bankService.getEntiyByPK(Long.parseLong(param.substring(1)));
                if (bank != null) {
                    bankModel.setId(bank.getId());
                    bankModel.setBankCode(bank.getBankCode());
                    bankModel.setBankName(bank.getBankName());
                    bankModel.setSwiftCode(bank.getSwiftCode());
                    bankModel.setIban(bank.getIban());
                    bankModel.setBankIdentificationNumber(bank.getBankIdentificationNo());
                    bankModel.setDescription(bank.getDescription());
                    bankModel.setBranchCode(bank.getBranchCode());
                    bankModel.setBranchName(bank.getBranchName());
                    bankModel.setAddress(bank.getAddress());
                    bankModel.setBankPhone(bank.getBankPhone());
                    bankModel.setBankFax(bank.getBankFax());
                    if (bank.getBankGroup().getId() != null) {
                        bankModel.setBankGroup(bank.getBankGroup().getId());
                    }
                    if (bank.getBank().getId() != null) {
                        bankModel.setBank(bank.getBank().getId());
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
        bank.setBankCode(bankModel.getBankCode());
        bank.setBankName(bankModel.getBankName());
        bank.setSwiftCode(bankModel.getSwiftCode());
        bank.setIban(bankModel.getIban());
        bank.setBankIdentificationNo(bankModel.getBankIdentificationNumber());
        bank.setDescription(bankModel.getDescription());
        bank.setBranchCode(bankModel.getBranchCode());
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

}
