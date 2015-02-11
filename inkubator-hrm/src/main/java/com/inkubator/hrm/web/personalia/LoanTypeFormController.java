package com.inkubator.hrm.web.personalia;

import com.inkubator.hrm.web.reference.*;
import com.inkubator.exception.BussinessException;
import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.entity.Bank;
import com.inkubator.hrm.entity.Currency;
import com.inkubator.hrm.entity.LoanSchema;
import com.inkubator.hrm.entity.LoanType;
import com.inkubator.hrm.service.CurrencyService;
import com.inkubator.hrm.service.LoanTypeService;
import com.inkubator.hrm.web.model.BankModel;
import com.inkubator.hrm.web.model.LoanTypeModel;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesUtil;
import com.inkubator.webcore.util.MessagesResourceUtil;
import java.math.BigDecimal;
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
import org.primefaces.context.RequestContext;

/**
 *
 * @author Ahmad Mudzakkir Amal
 */
@ManagedBean(name = "loanTypeFormController")
@ViewScoped
public class LoanTypeFormController extends BaseController {

    private LoanTypeModel loanTypeModel;
    private Boolean isUpdate;
    private Boolean rounding;
    @ManagedProperty(value = "#{loanTypeService}")
    private LoanTypeService loanTypeService;
    @ManagedProperty(value = "#{currencyService}")
    private CurrencyService currencyService;
    private Map<String, Long> mapCurrency = new HashMap<String, Long>();
    private Long currencyId;

    @PostConstruct
    @Override
    public void initialization() {
        super.initialization();
        try {
            String param = FacesUtil.getRequestParameter("param");
            loanTypeModel = new LoanTypeModel();
            isUpdate = Boolean.FALSE;
            
            List<Currency> currencyList = currencyService.getAllData();
            for (Currency currency : currencyList) {
                mapCurrency.put(currency.getName(), currency.getId());
            }
            
            if (StringUtils.isNumeric(param)) {
                LoanType loanType = loanTypeService.getEntityWithRelationByPk(Long.parseLong(param));
                if (loanType != null) {
                    loanTypeModel.setId(loanType.getId());
                    loanTypeModel.setLoanTypeCode(loanType.getLoanTypeCode());
                    loanTypeModel.setLoanTypeName(loanType.getLoanTypeName());
                    loanTypeModel.setCurrencyCode(loanType.getCurrency().getId());
                    loanTypeModel.setCurrencyName(loanType.getCurrency().getName());
                    loanTypeModel.setInterestMethod(loanType.getInterestMethod());
                    loanTypeModel.setInterest(loanType.getInterest().doubleValue());
                    loanTypeModel.setRoundingStatus(loanType.getRoundingStatus());
                    loanTypeModel.setDescription(loanType.getDescription());
                    isUpdate = Boolean.TRUE;
                    rounding = loanTypeModel.getRoundingStatus().intValue() == 1 ? Boolean.TRUE : Boolean.FALSE;
                    currencyId = loanType.getCurrency().getId();
                }
            }
        } catch (Exception e) {
            LOGGER.error("Error", e);
        }
    }

    @PreDestroy
    public void cleanAndExit() {
        loanTypeService = null;
        loanTypeModel = null;
        isUpdate = null;
    }

    public Long getCurrencyId() {
        return currencyId;
    }

    public void setCurrencyId(Long currencyId) {
        this.currencyId = currencyId;
    }
    
    
    public Boolean getRounding() {
        return rounding;
    }

    public void setRounding(Boolean rounding) {
        this.rounding = rounding;
    }
    
    
    public Map<String, Long> getMapCurrency() {
        return mapCurrency;
    }

    public void setMapCurrency(Map<String, Long> mapCurrency) {
        this.mapCurrency = mapCurrency;
    }

    public Boolean getIsUpdate() {
        return isUpdate;
    }

    public void setIsUpdate(Boolean isUpdate) {
        this.isUpdate = isUpdate;
    }

    public LoanTypeModel getLoanTypeModel() {
        return loanTypeModel;
    }

    public void setLoanTypeModel(LoanTypeModel loanTypeModel) {
        this.loanTypeModel = loanTypeModel;
    }

    public LoanTypeService getLoanTypeService() {
        return loanTypeService;
    }

    public void setLoanTypeService(LoanTypeService loanTypeService) {
        this.loanTypeService = loanTypeService;
    }

    public CurrencyService getCurrencyService() {
        return currencyService;
    }

    public void setCurrencyService(CurrencyService currencyService) {
        this.currencyService = currencyService;
    }

    public void doSave() {

        try {
            LoanType loanType = getEntityFromViewModel(loanTypeModel);
            if (isUpdate) {
                loanTypeService.update(loanType);
                RequestContext.getCurrentInstance().closeDialog(HRMConstant.UPDATE_CONDITION);
            } else {
                loanTypeService.save(loanType);
                RequestContext.getCurrentInstance().closeDialog(HRMConstant.SAVE_CONDITION);
            }
            cleanAndExit();
        } catch (BussinessException ex) {
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_ERROR, "global.error", ex.getErrorKeyMessage(), FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
    }

    private LoanType getEntityFromViewModel(LoanTypeModel loanTypeModel) throws Exception {
        LoanType loanType = new LoanType();
        if (loanTypeModel.getId() != null) {
            loanType.setId(loanTypeModel.getId());
        }
        loanType.setLoanTypeCode(loanTypeModel.getLoanTypeCode());
        loanType.setLoanTypeName(loanTypeModel.getLoanTypeName());       
        Currency currency = currencyService.getEntiyByPK(currencyId);
        loanType.setCurrency(currency);
        loanType.setInterestMethod(loanTypeModel.getInterestMethod());
        loanType.setInterest(new BigDecimal(loanTypeModel.getInterest()));
        loanType.setRoundingStatus(rounding ? 1 : 0);
        loanType.setDescription(loanTypeModel.getDescription());
        
        return loanType;
    }
}
