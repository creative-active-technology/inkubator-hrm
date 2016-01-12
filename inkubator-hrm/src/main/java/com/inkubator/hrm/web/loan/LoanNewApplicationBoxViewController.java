package com.inkubator.hrm.web.loan;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.hamcrest.Matchers;
import org.primefaces.model.LazyDataModel;

import ch.lambdaj.Lambda;

import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.service.LoanNewApplicationService;
import com.inkubator.hrm.web.lazymodel.LoanNewApplicationBoxLazyDataModel;
import com.inkubator.hrm.web.model.LoanNewApplicationBoxViewModel;
import com.inkubator.hrm.web.search.LoanNewApplicationBoxSearchParameter;
import com.inkubator.securitycore.util.UserInfoUtil;
import com.inkubator.webcore.controller.BaseController;

/**
 *
 * @author Ahmad Mudzakkir Amal
 */
@ManagedBean(name = "loanNewApplicationBoxViewController")
@ViewScoped
public class LoanNewApplicationBoxViewController extends BaseController {

    private Boolean isAdministator;
    private LoanNewApplicationBoxSearchParameter parameter;
    private LazyDataModel<LoanNewApplicationBoxViewModel> lazyData;
    private LoanNewApplicationBoxViewModel selected;
    private Boolean isComboStatus;
    private Boolean isDibusrmentStatus;
    private Boolean isText;

    @ManagedProperty(value = "#{loanNewApplicationService}")
    private LoanNewApplicationService loanNewApplicationService;

    @PostConstruct
    @Override
    public void initialization() {
        super.initialization();
        parameter = new LoanNewApplicationBoxSearchParameter();
        isAdministator = Lambda.exists(UserInfoUtil.getRoles(), Matchers.containsString(HRMConstant.ADMINISTRATOR_ROLE));
        if (!isAdministator) { //kalo bukan administrator, maka set userId di parameter searchingnya
            parameter.setUserId(UserInfoUtil.getUserName());
        }
        isText = Boolean.TRUE;
    }

    @PreDestroy
    public void cleanAndExit() {
        loanNewApplicationService = null;
        parameter = null;
        lazyData = null;
        selected = null;
    }

    public void doSearch() {
        lazyData = null;
    }

    public String doDetail() {
        return "/protected/loan/loan_application_detail.htm?faces-redirect=true&execution=d" + selected.getActivityNumber();
    }

    public LoanNewApplicationBoxSearchParameter getParameter() {
        return parameter;
    }

    public void setParameter(LoanNewApplicationBoxSearchParameter parameter) {
        this.parameter = parameter;
    }

    public LazyDataModel<LoanNewApplicationBoxViewModel> getLazyData() {
        if (lazyData == null) {
            lazyData = new LoanNewApplicationBoxLazyDataModel(parameter, loanNewApplicationService);
        }
        return lazyData;
    }

    public void setLazyData(LazyDataModel<LoanNewApplicationBoxViewModel> lazyData) {
        this.lazyData = lazyData;
    }

    public LoanNewApplicationBoxViewModel getSelected() {
        return selected;
    }

    public void setSelected(LoanNewApplicationBoxViewModel selected) {
        this.selected = selected;
    }

    public void setLoanNewApplicationService(LoanNewApplicationService loanNewApplicationService) {
        this.loanNewApplicationService = loanNewApplicationService;
    }

    public Boolean getIsAdministator() {
        return isAdministator;
    }

    public void setIsAdministator(Boolean isAdministator) {
        this.isAdministator = isAdministator;
    }

    public Boolean getIsComboStatus() {
        return isComboStatus;
    }

    public void setIsComboStatus(Boolean isComboStatus) {
        this.isComboStatus = isComboStatus;
    }

    public Boolean getIsDibusrmentStatus() {
        return isDibusrmentStatus;
    }

    public void setIsDibusrmentStatus(Boolean isDibusrmentStatus) {
        this.isDibusrmentStatus = isDibusrmentStatus;
    }

    public void doChange() {
        System.out.println(" hahahahah");
        if (parameter.getKeyParam().equals("approvalStatus")) {
            isComboStatus = Boolean.TRUE;
            isDibusrmentStatus = Boolean.FALSE;
            isText = Boolean.FALSE;
        }

        if (parameter.getKeyParam().equals("disbursementStatus")) {
            isComboStatus = Boolean.FALSE;
            isDibusrmentStatus = Boolean.TRUE;
            isText = Boolean.FALSE;
        }

        if (!parameter.getKeyParam().equals("disbursementStatus") && !parameter.getKeyParam().equals("approvalStatus")) {
            isComboStatus = Boolean.FALSE;
            isDibusrmentStatus = Boolean.FALSE;
            isText = Boolean.TRUE;
        }
    }

    public Boolean getIsText() {
        return isText;
    }

    public void setIsText(Boolean isText) {
        this.isText = isText;
    }
}
