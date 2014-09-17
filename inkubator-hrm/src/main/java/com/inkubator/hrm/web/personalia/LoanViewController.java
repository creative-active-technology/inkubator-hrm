package com.inkubator.hrm.web.personalia;

import java.io.IOException;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;

import org.hibernate.exception.ConstraintViolationException;
import org.primefaces.model.LazyDataModel;
import org.springframework.dao.DataIntegrityViolationException;

import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.entity.Loan;
import com.inkubator.hrm.service.LoanService;
import com.inkubator.hrm.web.lazymodel.LoanLazyDataModel;
import com.inkubator.hrm.web.search.LoanSearchParameter;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesUtil;
import com.inkubator.webcore.util.MessagesResourceUtil;

/**
 *
 * @author rizkykojek
 */
@ManagedBean(name = "loanViewController")
@ViewScoped
public class LoanViewController extends BaseController {
	
    private LoanSearchParameter searchParameter;
    private LazyDataModel<Loan> lazyDataLoan;
    private Loan selectedLoan;
    @ManagedProperty(value = "#{loanService}")
    private LoanService loanService;

    @PostConstruct
    @Override
    public void initialization() {
        super.initialization();
        searchParameter = new LoanSearchParameter();
    }

    @PreDestroy
    public void cleanAndExit() {
        searchParameter = null;
        lazyDataLoan = null;
        selectedLoan = null;
        loanService = null;
    }

	public LoanSearchParameter getSearchParameter() {
		return searchParameter;
	}

	public void setSearchParameter(LoanSearchParameter searchParameter) {
		this.searchParameter = searchParameter;
	}

	public LazyDataModel<Loan> getLazyDataLoan() {
		if(lazyDataLoan == null) {
			lazyDataLoan = new LoanLazyDataModel(searchParameter, loanService);
		}
		return lazyDataLoan;
	}

	public void setLazyDataLoan(LazyDataModel<Loan> lazyDataLoan) {
		this.lazyDataLoan = lazyDataLoan;
	}

	public Loan getSelectedLoan() {
		return selectedLoan;
	}

	public void setSelectedLoan(Loan selectedLoan) {
		this.selectedLoan = selectedLoan;
	}

	public void setLoanService(LoanService loanService) {
		this.loanService = loanService;
	}

	public void doSearch() {
        lazyDataLoan = null;
    }

    public String doDetail() {
        return "/protected/personalia/loan_detail.htm?faces-redirect=true&execution=e" + selectedLoan.getId();
    }

    public void doSelectEntity() {
        try {
            selectedLoan = this.loanService.getEntiyByPK(selectedLoan.getId());
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
    }

    public void doDelete() {
        try {
            loanService.delete(selectedLoan);
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_INFO, "global.delete", "global.delete_successfully", FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());

        } catch (ConstraintViolationException | DataIntegrityViolationException ex) {
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_ERROR, "global.error", "error.delete_constraint", FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
            LOGGER.error("Error when doDelete BusinessTravel", ex);
        } catch (Exception ex) {
            LOGGER.error("Error when doDelete BusinessTravel", ex);
        }
    }

    public void doAdd() {
        try {
            ExternalContext red = FacesUtil.getExternalContext();
            red.redirect(red.getRequestContextPath() + "/flow-protected/loan");
        } catch (IOException ex) {
          LOGGER.error("Erorr", ex);
        }
    }

    public void doUpdate() {
    	try {
            ExternalContext red = FacesUtil.getExternalContext();
            red.redirect(red.getRequestContextPath() + "/flow-protected/loan?id=" + selectedLoan.getId());
        } catch (IOException ex) {
          LOGGER.error("Erorr", ex);
        }
    }
}
