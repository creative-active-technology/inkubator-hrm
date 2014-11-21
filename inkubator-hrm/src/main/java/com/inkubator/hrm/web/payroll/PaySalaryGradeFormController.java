/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.payroll;

import com.inkubator.exception.BussinessException;
import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.entity.Currency;
import com.inkubator.hrm.entity.PaySalaryGrade;
import com.inkubator.hrm.service.CurrencyService;
import com.inkubator.hrm.service.PaySalaryGradeService;
import com.inkubator.hrm.util.MapUtil;
import com.inkubator.hrm.web.model.PaySalaryGradeModel;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesUtil;
import com.inkubator.webcore.util.MessagesResourceUtil;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import org.primefaces.context.RequestContext;

/**
 *
 * @author Deni
 */
@ManagedBean(name = "paySalaryGradeFormController")
@ViewScoped
public class PaySalaryGradeFormController extends BaseController{
    @ManagedProperty(value = "#{paySalaryGradeService}")
    private PaySalaryGradeService paySalaryGradeService;
    @ManagedProperty(value = "#{currencyService}")
    private CurrencyService currencyService;
    private PaySalaryGradeModel paySalaryGradeModel;
    private PaySalaryGrade selectedPaySalaryGrade;
    private Boolean isEdit;
    private Map<String, Long> listCurrencies = new TreeMap<String, Long>();;
    private List<Currency> ListCurrency = new ArrayList<>();
    private Currency currency;
    private Integer grade;

    @PreDestroy
    private void cleanAndExit() {
        paySalaryGradeService = null;
        currencyService = null;
        paySalaryGradeModel = null;
        isEdit = null;
        selectedPaySalaryGrade = null;
        listCurrencies = null;
        ListCurrency = null;
        currency = null;
        grade = null;
    }
    
    @PostConstruct
    @Override
    public void initialization() {
       
        super.initialization();
        String param = FacesUtil.getRequestParameter("param");
        paySalaryGradeModel = new PaySalaryGradeModel();
        try {
            if (param != null) {
                
                PaySalaryGrade paySalaryGrade = paySalaryGradeService.getEntiyByPK(Long.parseLong(param));
                isEdit = Boolean.TRUE;
                paySalaryGradeModel.setId(paySalaryGrade.getId());
                paySalaryGradeModel.setGradeSalary(paySalaryGrade.getGradeSalary());
                paySalaryGradeModel.setCurrencyid(paySalaryGrade.getCurrency().getId());
                paySalaryGradeModel.setMinSalary(paySalaryGrade.getMinSalary());
                paySalaryGradeModel.setMediumSalary(paySalaryGrade.getMediumSalary());
                paySalaryGradeModel.setMaxSalary(paySalaryGrade.getMaxSalary());
            } else {
                isEdit = Boolean.FALSE;
                List<PaySalaryGrade> listGrade = new ArrayList<>();
                listGrade = paySalaryGradeService.getAllData();
                grade = 0;
                if(listGrade.isEmpty()){
                    grade = 1;
                    paySalaryGradeModel.setGradeSalary(grade);
                }else{
                    for (PaySalaryGrade salaryGrade : listGrade) {
                        if(grade < salaryGrade.getGradeSalary() || salaryGrade.getGradeSalary() == 0 || salaryGrade.getGradeSalary() == null){
                            grade = salaryGrade.getGradeSalary() + 1;
                        }
                    }
                    paySalaryGradeModel.setGradeSalary(grade);
                }
            }
            listCurrencyDropDown();
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
    }
    
    public void listCurrencyDropDown() throws Exception{
        //Education Level
        ListCurrency = currencyService.getAllData();
        for (Currency currenci : ListCurrency) {
            listCurrencies.put(currenci.getName(), currenci.getId());
        }
        MapUtil.sortByValue(listCurrencies);
    }
    
    public void doSave() throws Exception {
        PaySalaryGrade paySalaryGrade = getEntityFromViewModel(paySalaryGradeModel);
        try {
            if (isEdit) {
                paySalaryGradeService.update(paySalaryGrade);
                RequestContext.getCurrentInstance().closeDialog(HRMConstant.UPDATE_CONDITION);
            } else {
                paySalaryGradeService.save(paySalaryGrade);
                RequestContext.getCurrentInstance().closeDialog(HRMConstant.SAVE_CONDITION);
            }
            cleanAndExit();
        } catch (BussinessException ex) { 
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_ERROR, "global.error", ex.getErrorKeyMessage(), FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
    }
    
    private PaySalaryGrade getEntityFromViewModel(PaySalaryGradeModel model) throws Exception {
        PaySalaryGrade paySalaryGrade = new PaySalaryGrade();
        if (model.getId() != null) {
            paySalaryGrade.setId(model.getId());
        }
        paySalaryGrade.setGradeSalary(grade);
        paySalaryGrade.setMinSalary(model.getMinSalary());
        paySalaryGrade.setMaxSalary(model.getMaxSalary());
        paySalaryGrade.setMediumSalary(model.getMediumSalary());
        paySalaryGrade.setCurrency(currencyService.getEntiyByPK(model.getCurrencyid()));
        return paySalaryGrade;
    }
    
    public void doReset(){
        paySalaryGradeModel.setMinSalary(null);
        paySalaryGradeModel.setMediumSalary(null);
        paySalaryGradeModel.setMaxSalary(null);
        paySalaryGradeModel.setCurrencyid(null);
    }
    
    public PaySalaryGradeService getPaySalaryGradeService() {
        return paySalaryGradeService;
    }

    public void setPaySalaryGradeService(PaySalaryGradeService paySalaryGradeService) {
        this.paySalaryGradeService = paySalaryGradeService;
    }

    public CurrencyService getCurrencyService() {
        return currencyService;
    }

    public void setCurrencyService(CurrencyService currencyService) {
        this.currencyService = currencyService;
    }

    public PaySalaryGradeModel getPaySalaryGradeModel() {
        return paySalaryGradeModel;
    }

    public void setPaySalaryGradeModel(PaySalaryGradeModel paySalaryGradeModel) {
        this.paySalaryGradeModel = paySalaryGradeModel;
    }

    public PaySalaryGrade getSelectedPaySalaryGrade() {
        return selectedPaySalaryGrade;
    }

    public void setSelectedPaySalaryGrade(PaySalaryGrade selectedPaySalaryGrade) {
        this.selectedPaySalaryGrade = selectedPaySalaryGrade;
    }

    public Boolean getIsEdit() {
        return isEdit;
    }

    public void setIsEdit(Boolean isEdit) {
        this.isEdit = isEdit;
    }

    public Map<String, Long> getListCurrencies() {
        return listCurrencies;
    }

    public void setListCurrencies(Map<String, Long> listCurrencies) {
        this.listCurrencies = listCurrencies;
    }

    public List<Currency> getListCurrency() {
        return ListCurrency;
    }

    public void setListCurrency(List<Currency> ListCurrency) {
        this.ListCurrency = ListCurrency;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }
    
    
}
