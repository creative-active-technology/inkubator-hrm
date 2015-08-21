/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.employee;

import com.inkubator.hrm.entity.ApprovalActivity;
import com.inkubator.hrm.entity.BusinessTravel;
import com.inkubator.hrm.entity.BusinessTravelComponent;
import com.inkubator.hrm.entity.EmpCareerHistory;
import com.inkubator.hrm.entity.EmpData;
import com.inkubator.hrm.entity.EmpPersonAchievement;
import com.inkubator.hrm.entity.JabatanDeskripsi;
import com.inkubator.hrm.entity.JabatanSpesifikasi;
import com.inkubator.hrm.entity.LeaveImplementation;
import com.inkubator.hrm.entity.Loan;
import com.inkubator.hrm.entity.PersonalDiscipline;
import com.inkubator.hrm.service.ApprovalActivityService;
import com.inkubator.hrm.service.BusinessTravelComponentService;
import com.inkubator.hrm.service.BusinessTravelService;
import com.inkubator.hrm.service.EmpCareerHistoryService;
import com.inkubator.hrm.service.EmpDataService;
import com.inkubator.hrm.service.EmpPersonAchievementService;
import com.inkubator.hrm.service.LeaveImplementationService;
import com.inkubator.hrm.service.LeaveSchemeService;
import com.inkubator.hrm.service.LeaveService;
import com.inkubator.hrm.service.LoanService;
import com.inkubator.hrm.service.PersonalDisciplineService;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesUtil;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Deni Husni FR
 */
@ManagedBean(name = "empDataDetilController")
@ViewScoped
public class EmpDataDetilController extends BaseController {

    @ManagedProperty(value = "#{empDataService}")
    private EmpDataService empDataService;
    private EmpData selectedEmpData;
    private List<JabatanSpesifikasi> listJabatanSpesifikasi;
    private List<JabatanDeskripsi> jabatanDeskripsis;
    private List<EmpCareerHistory> listCareerHistory;
    private String id;
    @ManagedProperty(value = "#{empCareerHistoryService}")
    private EmpCareerHistoryService empCareerHistoryService;

    //personal discipline
    @ManagedProperty(value = "#{personalDisciplineService}")
    private PersonalDisciplineService personalDisciplineService;
    private List<PersonalDiscipline> listPersonalDiscipline;

    //Achievement
    @ManagedProperty(value = "#{empPersonAchievementService}")
    private EmpPersonAchievementService empPersonAchievementService;
    private List<EmpPersonAchievement> listPersonAchievement;
    private String formData;
    
    //Loan
    @ManagedProperty(value = "#{loanService}")
    private LoanService loanService;
    private List<Loan> listLoan;
    
    //bussiness travel history
    private  BusinessTravel selectedBusinessTravel;
    private List<BusinessTravel> businessTravelList;
    @ManagedProperty(value = "#{businessTravelService}")
    private BusinessTravelService businessTravelService;
    @ManagedProperty(value = "#{businessTravelComponentService}")
    private BusinessTravelComponentService businessTravelComponentService;
    
    //leave history
    private LeaveImplementation selectedLeaveImplementation;
    private List<LeaveImplementation> leaveImplementations;
    @ManagedProperty(value = "#{leaveImplementationService}")
    private LeaveImplementationService leaveImplementationService;
    @ManagedProperty(value = "#{leaveService}")
    private LeaveService leaveService;
    @ManagedProperty(value = "#{leaveSchemeService}")
    private LeaveSchemeService leaveSchemeService;
    @ManagedProperty(value = "#{approvalActivityService}")
    private ApprovalActivityService approvalActivityService;
    
    @PostConstruct
    @Override
    public void initialization() {
        try {
            super.initialization();
            String empId = FacesUtil.getRequestParameter("execution");
            formData = FacesUtil.getRequestParameter("from");
            selectedEmpData = empDataService.getByEmpIdWithDetail(Long.parseLong(empId.substring(1)));
            jabatanDeskripsis = new ArrayList<>(selectedEmpData.getJabatanByJabatanId().getJabatanDeskripsis());
            listJabatanSpesifikasi = new ArrayList<>(selectedEmpData.getJabatanByJabatanId().getJabatanSpesifikasis());
            listCareerHistory = empCareerHistoryService.getEmployeeCareerByBioId(selectedEmpData.getBioData().getId());
            listPersonalDiscipline = personalDisciplineService.getAllDataByEmployeeId(selectedEmpData.getId());
            listPersonAchievement = empPersonAchievementService.getAllDataByEmployeeId(selectedEmpData.getId());
            listLoan = loanService.getAllDataByEmpDataIdAndStatusDisbursed(selectedEmpData.getId());
            
            //Inisialisasi Riwayat Dinas
            businessTravelList = businessTravelService.getAllDataByEmpDataId(selectedEmpData.getId());
            
            //Looping List Dinas dan hitung Total Biaya dari masing - masing Dinas
            for(BusinessTravel businessTravel : businessTravelList){
                countTotalAmoutOfBusinessTravel(businessTravel);
            }
            
            //Inisialisasi Riwayat Cuti
            leaveImplementations = leaveImplementationService.getAllDataByEmpDataId(selectedEmpData.getId());
            for(LeaveImplementation lv : leaveImplementations){
                if(null != lv.getApprovalActivityNumber()){
                    setLeaveApprovalOfficer(lv);
                }
            }
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }

    }
    
    //Hitung Total Biaya Perjalanan dari masing-masing perjalanan
    public void countTotalAmoutOfBusinessTravel(BusinessTravel businessTravel) throws Exception{
        List<BusinessTravelComponent> businessTravelComponents = businessTravelComponentService.getAllDataByBusinessTravelId(businessTravel.getId());            
        Double totalAmount = 0.0;
        for(BusinessTravelComponent btc :businessTravelComponents){
            	totalAmount = totalAmount + btc.getPayByAmount();
            }
        businessTravel.setTotalAmount(totalAmount);
    }
    
    public void setLeaveApprovalOfficer(LeaveImplementation leaveImplementation) throws Exception{
        ApprovalActivity selectedApprovalActivity = approvalActivityService.getEntityByActivityNumberLastSequence(leaveImplementation.getApprovalActivityNumber());
        leaveImplementation.setApprovedBy(selectedApprovalActivity.getApprovedBy());
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public EmpData getSelectedEmpData() {
        return selectedEmpData;
    }

    public void setSelectedEmpData(EmpData selectedEmpData) {
        this.selectedEmpData = selectedEmpData;
    }

    public void setEmpDataService(EmpDataService empDataService) {
        this.empDataService = empDataService;
    }

    public String doEdit() {
        if (formData != null) {
            return "/protected/employee/emp_rotasi_form.htm?faces-redirect=true&execution=r" + selectedEmpData.getId() + "&from=rotasi";
        }
        return "/protected/employee/emp_placement_form.htm?faces-redirect=true&execution=e" + selectedEmpData.getId();
    }

    public String doBack() {
        if (formData != null) {
            return "/protected/employee/emp_rotasi_view.htm?faces-redirect=true";
        }
        return "/protected/employee/emp_placement_view.htm?faces-redirect=true";
    }

    public List<JabatanSpesifikasi> getListJabatanSpesifikasi() {
        return listJabatanSpesifikasi;
    }

    public void setListJabatanSpesifikasi(List<JabatanSpesifikasi> listJabatanSpesifikasi) {
        this.listJabatanSpesifikasi = listJabatanSpesifikasi;
    }

    public List<JabatanDeskripsi> getJabatanDeskripsis() {
        return jabatanDeskripsis;
    }

    public void setJabatanDeskripsis(List<JabatanDeskripsi> jabatanDeskripsis) {
        this.jabatanDeskripsis = jabatanDeskripsis;
    }

    public void doSelectEmpCardName() {
        try {
            selectedEmpData = empDataService.getByEmpIdWithDetail(selectedEmpData.getId());
        } catch (Exception e) {
            LOGGER.error("Error", e);
        }
    }

    public void setEmpCareerHistoryService(EmpCareerHistoryService empCareerHistoryService) {
        this.empCareerHistoryService = empCareerHistoryService;
    }

    public List<EmpCareerHistory> getListCareerHistory() {
        return listCareerHistory;
    }

    public void setListCareerHistory(List<EmpCareerHistory> listCareerHistory) {
        this.listCareerHistory = listCareerHistory;
    }

    public PersonalDisciplineService getPersonalDisciplineService() {
        return personalDisciplineService;
    }

    public void setPersonalDisciplineService(PersonalDisciplineService personalDisciplineService) {
        this.personalDisciplineService = personalDisciplineService;
    }

    public List<PersonalDiscipline> getListPersonalDiscipline() {
        return listPersonalDiscipline;
    }

    public void setListPersonalDiscipline(List<PersonalDiscipline> listPersonalDiscipline) {
        this.listPersonalDiscipline = listPersonalDiscipline;
    }

    public EmpPersonAchievementService getEmpPersonAchievementService() {
        return empPersonAchievementService;
    }

    public void setEmpPersonAchievementService(EmpPersonAchievementService empPersonAchievementService) {
        this.empPersonAchievementService = empPersonAchievementService;
    }

    public List<EmpPersonAchievement> getListPersonAchievement() {
        return listPersonAchievement;
    }

    public void setListPersonAchievement(List<EmpPersonAchievement> listPersonAchievement) {
        this.listPersonAchievement = listPersonAchievement;
    }

    public List<Loan> getListLoan() {
		return listLoan;
	}

	public void setListLoan(List<Loan> listLoan) {
		this.listLoan = listLoan;
	}

	public void setLoanService(LoanService loanService) {
		this.loanService = loanService;
        }

    public BusinessTravel getSelectedBusinessTravel() {
        return selectedBusinessTravel;
    }

    public void setSelectedBusinessTravel(BusinessTravel selectedBusinessTravel) {
        this.selectedBusinessTravel = selectedBusinessTravel;
    }

    public List<BusinessTravel> getBusinessTravelList() {
        return businessTravelList;
    }

    public void setBusinessTravelList(List<BusinessTravel> businessTravelList) {
        this.businessTravelList = businessTravelList;
    }

    public BusinessTravelService getBusinessTravelService() {
        return businessTravelService;
    }

    public void setBusinessTravelService(BusinessTravelService businessTravelService) {
        this.businessTravelService = businessTravelService;
    }

    public BusinessTravelComponentService getBusinessTravelComponentService() {
        return businessTravelComponentService;
    }

    public void setBusinessTravelComponentService(BusinessTravelComponentService businessTravelComponentService) {
        this.businessTravelComponentService = businessTravelComponentService;
    }

    public LeaveImplementation getSelectedLeaveImplementation() {
        return selectedLeaveImplementation;
    }

    public void setSelectedLeaveImplementation(LeaveImplementation selectedLeaveImplementation) {
        this.selectedLeaveImplementation = selectedLeaveImplementation;
    }

    public List<LeaveImplementation> getLeaveImplementations() {
        return leaveImplementations;
    }

    public void setLeaveImplementations(List<LeaveImplementation> leaveImplementations) {
        this.leaveImplementations = leaveImplementations;
    }

    public LeaveImplementationService getLeaveImplementationService() {
        return leaveImplementationService;
    }

    public void setLeaveImplementationService(LeaveImplementationService leaveImplementationService) {
        this.leaveImplementationService = leaveImplementationService;
    }

    public LeaveService getLeaveService() {
        return leaveService;
    }

    public void setLeaveService(LeaveService leaveService) {
        this.leaveService = leaveService;
    }

    public LeaveSchemeService getLeaveSchemeService() {
        return leaveSchemeService;
    }

    public void setLeaveSchemeService(LeaveSchemeService leaveSchemeService) {
        this.leaveSchemeService = leaveSchemeService;
    }

    public ApprovalActivityService getApprovalActivityService() {
        return approvalActivityService;
    }

    public void setApprovalActivityService(ApprovalActivityService approvalActivityService) {
        this.approvalActivityService = approvalActivityService;
    }
        
    
        
	@PreDestroy
    public void cleanAndExit() {
        empDataService = null;
        selectedEmpData = null;
        listJabatanSpesifikasi = null;
        jabatanDeskripsis = null;
        listCareerHistory = null;
        id = null;
        empCareerHistoryService = null;
        personalDisciplineService = null;
        listPersonalDiscipline = null;
        empPersonAchievementService = null;
        listPersonAchievement = null;
        loanService = null;
        listLoan = null;
        businessTravelList = null;
        businessTravelService = null;
        leaveImplementations = null;
        leaveService = null;
        leaveService = null;
        
    }

}
