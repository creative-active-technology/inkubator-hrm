/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.loan;

import com.inkubator.common.util.DateTimeUtil;
import com.inkubator.exception.BussinessException;
import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.entity.CostCenter;
import com.inkubator.hrm.entity.EmpData;
import com.inkubator.hrm.entity.LoanNewApplication;
import com.inkubator.hrm.entity.LoanNewDisbursement;
import com.inkubator.hrm.entity.TransactionCodefication;
import com.inkubator.hrm.entity.WtPeriode;
import com.inkubator.hrm.service.CostCenterService;
import com.inkubator.hrm.service.EmpDataService;
import com.inkubator.hrm.service.LoanNewApplicationService;
import com.inkubator.hrm.service.LoanNewDisbursementService;
import com.inkubator.hrm.service.TransactionCodeficationService;
import com.inkubator.hrm.service.WtPeriodeService;
import com.inkubator.hrm.util.HrmUserInfoUtil;
import com.inkubator.hrm.util.KodefikasiUtil;
import com.inkubator.hrm.web.lazymodel.LoanNewDisbursementLazyDataModel;
import com.inkubator.hrm.web.model.LoanNewDisbursementFormModel;
import com.inkubator.hrm.web.search.LoanNewSearchParameter;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesUtil;
import com.inkubator.webcore.util.MessagesResourceUtil;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.primefaces.model.LazyDataModel;

/**
 *
 * @author Ahmad Mudzakkir Amal
 */
@ManagedBean(name = "loanNewDisbursementFormController")
@ViewScoped
public class LoanNewDisbursementFormController extends BaseController {

    @ManagedProperty(value = "#{empDataService}")
    private EmpDataService empDataService;
    @ManagedProperty(value = "#{transactionCodeficationService}")
    private TransactionCodeficationService transactionCodeficationService;
    @ManagedProperty(value = "#{loanNewApplicationService}")
    private LoanNewApplicationService loanNewApplicationService;
    @ManagedProperty(value = "#{loanNewDisbursementService}")
    private LoanNewDisbursementService loanNewDisbursementService;
    @ManagedProperty(value = "#{wtPeriodeService}")
    private WtPeriodeService wtPeriodeService;
    @ManagedProperty(value = "#{costCenterService}")
    private CostCenterService costCenterService;
    private LoanNewDisbursementFormModel model;
    private LazyDataModel<LoanNewApplication> lazy;
    private LoanNewSearchParameter loanNewSearchParameter;
    private Date minimumBackDate;
    @PostConstruct
    @Override
    public void initialization() {

        super.initialization();
        model = new LoanNewDisbursementFormModel();
        loanNewSearchParameter = new LoanNewSearchParameter();
        try {
            
            TransactionCodefication transactionCodefication = transactionCodeficationService.getEntityByModulCode(HRMConstant.LOAN_DISBURSEMENT_KODE);
            Long currentMaxLoanId = loanNewDisbursementService.getCurrentMaxId();
            model.setDisbursementCode(KodefikasiUtil.getKodefikasi(((int) currentMaxLoanId.longValue()), transactionCodefication.getCode()));
            
            //Minimum backdate paling lambat awal bulan dari bulan selanjutnya dari periode penggajian yang aktif
            WtPeriode activeWtPeriode = wtPeriodeService.getEntityByPayrollTypeActive();
            minimumBackDate = DateUtils.addDays(activeWtPeriode.getUntilPeriode(), 1);
            
            List<CostCenter> listCoa = costCenterService.getAllData();
            Map<String,Long> mapCoa = new HashMap<String, Long>();
            for(CostCenter coa : listCoa){
                mapCoa.put(coa.getName(), coa.getId());
            }
            model.setMapCoa(mapCoa);

        } catch (Exception ex) {
            Logger.getLogger(LoanNewDisbursementFormController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @PreDestroy
    public void cleanAndExit() {        
        loanNewApplicationService = null;
        loanNewDisbursementService = null;
        transactionCodeficationService = null;
        transactionCodeficationService = null;
        empDataService = null;
        costCenterService = null;
        model = null;

    }

    public String doDisburse() {
        try {
            LoanNewDisbursement loanNewDisbursement = convertModelToEntity(model);
            loanNewDisbursementService.disburseLoanApplication(loanNewDisbursement, model.getListLoanNewApplicationId());
            MessagesResourceUtil.setMessagesFlas(FacesMessage.SEVERITY_INFO, "global.save_info", "global.update_successfully", FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
            cleanAndExit();
            return "/protected/home.htm?faces-redirect=true";
        } catch (BussinessException ex) {
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_ERROR, "global.error", ex.getErrorKeyMessage(), FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
        } catch (Exception ex) {
            Logger.getLogger(LoanNewDisbursementFormController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
   

    public String doBack() {
        cleanAndExit();
        return "/protected/home.htm?faces-redirect=true";
    }


    public List<EmpData> completeEmpData(String query) {
        try {
            List<EmpData> allEmpData = empDataService.getAllDataByNameOrNik(StringUtils.stripToEmpty(query), HrmUserInfoUtil.getCompanyId());
            return allEmpData;
        } catch (Exception ex) {
            Logger.getLogger(LoanNewDisbursementFormController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    private LoanNewDisbursement convertModelToEntity(LoanNewDisbursementFormModel model) {
        LoanNewDisbursement loanNewDisbursement = new LoanNewDisbursement();
        
        loanNewDisbursement.setCoaId(model.getCoaId());
        loanNewDisbursement.setDescription(model.getDescription());
        loanNewDisbursement.setDibursementCode(model.getDisbursementCode());
        loanNewDisbursement.setDibursementDate(model.getDisbursementDate());

        return loanNewDisbursement;
    }

    public void setLoanNewApplicationService(LoanNewApplicationService loanNewApplicationService) {
        this.loanNewApplicationService = loanNewApplicationService;
    }

    public LoanNewDisbursementFormModel getModel() {
        return model;
    }

    public void setModel(LoanNewDisbursementFormModel model) {
        this.model = model;
    }

    public void setEmpDataService(EmpDataService empDataService) {
        this.empDataService = empDataService;
    }

    public void setTransactionCodeficationService(TransactionCodeficationService transactionCodeficationService) {
        this.transactionCodeficationService = transactionCodeficationService;
    }

    public LazyDataModel<LoanNewApplication> getLazy() {
        if (lazy == null) {
            lazy = new LoanNewDisbursementLazyDataModel(loanNewSearchParameter, loanNewApplicationService);
        }
        return lazy;
    }

    public void setLazy(LazyDataModel<LoanNewApplication> lazy) {
        this.lazy = lazy;
    }

    public LoanNewSearchParameter getLoanNewSearchParameter() {
        return loanNewSearchParameter;
    }

    public void setLoanNewSearchParameter(LoanNewSearchParameter loanNewSearchParameter) {
        this.loanNewSearchParameter = loanNewSearchParameter;
    }

    public void setLoanNewDisbursementService(LoanNewDisbursementService loanNewDisbursementService) {
        this.loanNewDisbursementService = loanNewDisbursementService;
    }

    public void setCostCenterService(CostCenterService costCenterService) {
        this.costCenterService = costCenterService;
    }

	public Date getMinimumBackDate() {
		return minimumBackDate;
	}

	public void setMinimumBackDate(Date minimumBackDate) {
		this.minimumBackDate = minimumBackDate;
	}

	public void setWtPeriodeService(WtPeriodeService wtPeriodeService) {
		this.wtPeriodeService = wtPeriodeService;
	}
    
    
    
}
