/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.loan;

import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.entity.BioAddress;
import com.inkubator.hrm.entity.BioData;
import com.inkubator.hrm.entity.BioEducationHistory;
import com.inkubator.hrm.entity.BioEmergencyContact;
import com.inkubator.hrm.entity.BioFamilyRelationship;
import com.inkubator.hrm.entity.BioIdCard;
import com.inkubator.hrm.entity.BioKeahlian;
import com.inkubator.hrm.entity.BioPeopleInterest;
import com.inkubator.hrm.entity.BioRelasiPerusahaan;
import com.inkubator.hrm.entity.BioSpesifikasiAbility;
import com.inkubator.hrm.entity.BioSpesifikasiAbilityId;
import com.inkubator.hrm.entity.EmpData;
import com.inkubator.hrm.entity.LoanNewApplication;
import com.inkubator.hrm.service.BioAddressService;
import com.inkubator.hrm.service.BioDataService;
import com.inkubator.hrm.service.BioEducationHistoryService;
import com.inkubator.hrm.service.BioEmergencyContactService;
import com.inkubator.hrm.service.BioFamilyRelationshipService;
import com.inkubator.hrm.service.BioIdCardService;
import com.inkubator.hrm.service.BioKeahlianService;
import com.inkubator.hrm.service.BioPeopleInterestService;
import com.inkubator.hrm.service.BioRelasiPerusahaanService;
import com.inkubator.hrm.service.BioSpesifikasiAbilityService;
import com.inkubator.hrm.service.EmpDataService;
import com.inkubator.hrm.service.LoanNewApplicationService;
import com.inkubator.hrm.util.HrmUserInfoUtil;
import com.inkubator.hrm.web.model.BioEducationHistoryViewModel;
import com.inkubator.hrm.web.model.LoanHistoryViewModel;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesUtil;
import com.inkubator.webcore.util.MessagesResourceUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;
import java.util.logging.Level;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.hibernate.exception.ConstraintViolationException;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;
import org.springframework.dao.DataIntegrityViolationException;

/**
 *
 * @author Ahmad Mudzakkir Amal
 */
@ManagedBean(name = "loanHistoryViewController")
@ViewScoped
public class LoanHistoryViewController extends BaseController {


    @ManagedProperty(value = "#{empDataService}")
    private EmpDataService empDataService;
    private EmpData selectedEmpData;
    
    //Start. LoanNewApplication
    private LoanHistoryViewModel selectedLoanHistoryViewModel;
    @ManagedProperty(value = "#{loanNewApplicationService}")
    private LoanNewApplicationService loanNewApplicationService;
    private List<LoanHistoryViewModel> listLoanHistoryViewModel;
    //End. LoanNewApplication


    @PostConstruct
    @Override
    public void initialization() {
        try {
            super.initialization();
            Long empDataId = HrmUserInfoUtil.getEmpId();
            selectedEmpData = empDataService.getByEmpIdWithDetail(empDataId);
            listLoanHistoryViewModel = loanNewApplicationService.getListLoanHistoryByEmpDataId(selectedEmpData.getId());
        } catch (Exception ex) {
            LOGGER.error(ex, ex);
        }

    }
    

    public EmpData getSelectedEmpData() {
		return selectedEmpData;
	}

	public void setSelectedEmpData(EmpData selectedEmpData) {
		this.selectedEmpData = selectedEmpData;
	}

	public LoanHistoryViewModel getSelectedLoanHistoryViewModel() {
		return selectedLoanHistoryViewModel;
	}

	public void setSelectedLoanHistoryViewModel(
			LoanHistoryViewModel selectedLoanHistoryViewModel) {
		this.selectedLoanHistoryViewModel = selectedLoanHistoryViewModel;
	}

	public List<LoanHistoryViewModel> getListLoanHistoryViewModel() {
		return listLoanHistoryViewModel;
	}

	public void setListLoanHistoryViewModel(
			List<LoanHistoryViewModel> listLoanHistoryViewModel) {
		this.listLoanHistoryViewModel = listLoanHistoryViewModel;
	}

	public void setEmpDataService(EmpDataService empDataService) {
		this.empDataService = empDataService;
	}

	public void setLoanNewApplicationService(
			LoanNewApplicationService loanNewApplicationService) {
		this.loanNewApplicationService = loanNewApplicationService;
	}


    public String doDemployeeDetail() {
        long id = HrmUserInfoUtil.getEmpId();
        return "/protected/employee/emp_placement_detail.htm?faces-redirect=true&execution=e" + id;
    }

    public String doBackGround() {
        long id = HrmUserInfoUtil.getEmpId();
        return "/protected/personalia/emp_background_detail.htm?faces-redirect=true&execution=e" + id;
    }

}
