/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.personalia;

import com.inkubator.exception.BussinessException;
import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.entity.Currency;
import com.inkubator.hrm.entity.LoanNewType;
import com.inkubator.hrm.entity.LoanType;
import com.inkubator.hrm.service.CurrencyService;
import com.inkubator.hrm.service.LoanNewTypeService;
import com.inkubator.hrm.service.LoanTypeService;
import com.inkubator.hrm.web.model.LoanNewTypeModel;
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
 * @author Deni
 */
@ManagedBean(name = "loanNewTypeFormController")
@ViewScoped
public class LoanNewTypeFormController extends BaseController {

    private LoanNewTypeModel loanNewTypeModel;
    private Boolean isUpdate;
    private Boolean rounding;
    @ManagedProperty(value = "#{loanNewTypeService}")
    private LoanNewTypeService loanNewTypeService;
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
            loanNewTypeModel = new LoanNewTypeModel();
            isUpdate = Boolean.FALSE;
            
            List<Currency> currencyList = currencyService.getAllData();
            for (Currency currency : currencyList) {
                mapCurrency.put(currency.getName(), currency.getId());
            }
            
            if (StringUtils.isNumeric(param)) {
                LoanNewType loanNewType = loanNewTypeService.getEntityWithRelationByPk(Long.parseLong(param));
                if (loanNewType != null) {
                    loanNewTypeModel.setId(loanNewType.getId());
                    loanNewTypeModel.setLoanTypeCode(loanNewType.getLoanTypeCode());
                    loanNewTypeModel.setLoanTypeName(loanNewType.getLoanTypeName());
                    loanNewTypeModel.setCurrencyCode(loanNewType.getCurrency().getId());
                    loanNewTypeModel.setCurrencyName(loanNewType.getCurrency().getName());
                    loanNewTypeModel.setInterestMethod(loanNewType.getInterestMethod());
                    loanNewTypeModel.setInterest(loanNewType.getInterest().doubleValue());
                    loanNewTypeModel.setRoundingStatus(loanNewType.getRoundingStatus());
                    loanNewTypeModel.setDescription(loanNewType.getDescription());
                    isUpdate = Boolean.TRUE;
                    rounding = loanNewTypeModel.getRoundingStatus().intValue() == 1 ? Boolean.TRUE : Boolean.FALSE;
                    currencyId = loanNewType.getCurrency().getId();
                }
            }
        } catch (Exception e) {
            LOGGER.error("Error", e);
        }
    }

    @PreDestroy
    public void cleanAndExit() {
        loanNewTypeService = null;
        loanNewTypeModel = null;
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

    public LoanNewTypeModel getLoanNewTypeModel() {
        return loanNewTypeModel;
    }

    public void setLoanNewTypeModel(LoanNewTypeModel loanNewTypeModel) {
        this.loanNewTypeModel = loanNewTypeModel;
    }

    public LoanNewTypeService getLoanNewTypeService() {
        return loanNewTypeService;
    }

    public void setLoanNewTypeService(LoanNewTypeService loanNewTypeService) {
        this.loanNewTypeService = loanNewTypeService;
    }

    public CurrencyService getCurrencyService() {
        return currencyService;
    }

    public void setCurrencyService(CurrencyService currencyService) {
        this.currencyService = currencyService;
    }

    public void doSave() {

        try {
            LoanNewType loanNewType = getEntityFromViewModel(loanNewTypeModel);
            if (isUpdate) {
                loanNewTypeService.update(loanNewType);
                RequestContext.getCurrentInstance().closeDialog(HRMConstant.UPDATE_CONDITION);
            } else {
                loanNewTypeService.save(loanNewType);
                RequestContext.getCurrentInstance().closeDialog(HRMConstant.SAVE_CONDITION);
            }
            cleanAndExit();
        } catch (BussinessException ex) {
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_ERROR, "global.error", ex.getErrorKeyMessage(), FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
    }

    private LoanNewType getEntityFromViewModel(LoanNewTypeModel loanNewTypeModel) throws Exception {
        LoanNewType loanNewType = new LoanNewType();
        if (loanNewTypeModel.getId() != null) {
            loanNewType.setId(loanNewTypeModel.getId());
        }
        loanNewType.setLoanTypeCode(loanNewTypeModel.getLoanTypeCode());
        loanNewType.setLoanTypeName(loanNewTypeModel.getLoanTypeName());       
        Currency currency = currencyService.getEntiyByPK(currencyId);
        loanNewType.setCurrency(currency);
        loanNewType.setInterestMethod(loanNewTypeModel.getInterestMethod());
        loanNewType.setInterest(new BigDecimal(loanNewTypeModel.getInterest()));
        loanNewType.setRoundingStatus(rounding ? 1 : 0);
        loanNewType.setDescription(loanNewTypeModel.getDescription());
        
        return loanNewType;
    }
}
