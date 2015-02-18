package com.inkubator.hrm.web.reference;

import com.inkubator.exception.BussinessException;
import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.entity.Bank;
import com.inkubator.hrm.service.BankService;
import com.inkubator.hrm.web.model.BankModel;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesUtil;
import com.inkubator.webcore.util.MessagesResourceUtil;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import org.apache.commons.lang3.StringUtils;
import org.primefaces.context.RequestContext;

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

    @PostConstruct
    @Override
    public void initialization() {
        super.initialization();
        String param = FacesUtil.getRequestParameter("execution");
        bankModel = new BankModel();
        isUpdate = Boolean.FALSE;
        if (StringUtils.isNotEmpty(param)) {
            try {
                Bank bank = bankService.getEntiyByPK(Long.parseLong(param.substring(1)));
                if (bank != null) {
                    bankModel.setId(bank.getId());
                    bankModel.setBankCode(bank.getBankCode());
                    bankModel.setBankName(bank.getBankName());
                    bankModel.setSwiftCode(bank.getSwiftCcode());
                    bankModel.setIban(bank.getIban());
                    bankModel.setBankIdentificationNumber(bank.getBankIdentificationNo());
                    bankModel.setDescription(bank.getDescription());
                    bankModel.setBranchCode(bank.getBranchCode());
                    bankModel.setBranchName(bank.getBranchName());
                    bankModel.setAddress(bank.getAddress());
                    bankModel.setBankPhone(bank.getBankPhone());
                    bankModel.setBankFax(bank.getBankFax());
                    bankModel.setBankGroup(bank.getBankGroup());
                    isUpdate = Boolean.TRUE;
                }
            } catch (Exception e) {
                LOGGER.error("Error", e);
            }
        }
    }

    @PreDestroy
    public void cleanAndExit() {
        bankService = null;
        bankModel = null;
        isUpdate = null;
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
        bank.setSwiftCcode(bankModel.getSwiftCode());
        bank.setIban(bankModel.getIban());
        bank.setBankIdentificationNo(bankModel.getBankIdentificationNumber());
        bank.setDescription(bankModel.getDescription());
        bank.setBranchCode(bankModel.getBranchCode());
        bank.setBranchName(bankModel.getBranchName());
        bank.setAddress(bankModel.getAddress());
        bank.setBankPhone(bankModel.getBankPhone());
        bank.setBankFax(bankModel.getBankFax());
        bank.setBankGroup(bankModel.getBankGroup());
        return bank;
    }
    
    public void doReset(){
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
}
