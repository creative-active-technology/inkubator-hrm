/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.employee;

import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.entity.ApprovalActivity;
import com.inkubator.hrm.entity.BioBankAccount;
import com.inkubator.hrm.entity.BioDocument;
import com.inkubator.hrm.entity.BioEmploymentHistory;
import com.inkubator.hrm.entity.BioInsurance;
import com.inkubator.hrm.entity.BioMedicalHistory;
import com.inkubator.hrm.entity.BioPotensiSwot;
import com.inkubator.hrm.entity.BioProject;
import com.inkubator.hrm.entity.BioSertifikasi;
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
import com.inkubator.hrm.service.BioBankAccountService;
import com.inkubator.hrm.service.BioDocumentService;
import com.inkubator.hrm.service.BioEmploymentHistoryService;
import com.inkubator.hrm.service.BioInsuranceService;
import com.inkubator.hrm.service.BioMedicalHistoryService;
import com.inkubator.hrm.service.BioPotensiSwotService;
import com.inkubator.hrm.service.BioProjectService;
import com.inkubator.hrm.service.BioSertifikasiService;
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
import com.inkubator.webcore.util.MessagesResourceUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
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
    
    //bio medical history
    private BioMedicalHistory selectedBioMedicalHistory;
    private List<BioMedicalHistory> bioMedicalHistorys;
    @ManagedProperty(value = "#{bioMedicalHistoryService}")
    private BioMedicalHistoryService bioMedicalHistoryService;

    //bio sertifikasi
     @ManagedProperty(value = "#{bioSertifikasiService}")
     private BioSertifikasiService  bioSertifikasiService;
     private List<BioSertifikasi> listBioSertifikasi;
     private BioSertifikasi selectedBioSertifikasi;
     
     //start. bio employment
    private BioEmploymentHistory selectedBioEmploymentHistory;
    private List<BioEmploymentHistory> bioEmploymentHistorys;
    @ManagedProperty(value = "#{bioEmploymentHistoryService}")
    private BioEmploymentHistoryService bioEmploymentHistoryService;
    
    //start. bio project
    private BioProject selectedBioProject;
    private List<BioProject> bioProjects;
    @ManagedProperty(value = "#{bioProjectService}")
    private BioProjectService bioProjectService;
    
    //start. bio potensi
    private BioPotensiSwot selectedBioPotensiSwot;
    private List<BioPotensiSwot> ListBioPotensiSwot;
    @ManagedProperty(value = "#{bioPotensiSwotService}")
    private BioPotensiSwotService bioPotensiSwotService;
    
    //bio document
    private BioDocument selectedBioDocument;
    private List<BioDocument> bioDocuments;
    @ManagedProperty(value = "#{bioDocumentService}")
    private BioDocumentService bioDocumentService;
    
    //bussiness travel
    private BusinessTravel selectedBusinessTravel;
    private List<BusinessTravel> businessTravelList;
    @ManagedProperty(value = "#{businessTravelService}")
    private BusinessTravelService businessTravelService;
    @ManagedProperty(value = "#{businessTravelComponentService}")
    private BusinessTravelComponentService businessTravelComponentService;
    
    //leave implementation
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
    
  //start. bio bank
    private BioBankAccount selectedBioBankAccount;
    private List<BioBankAccount> bioBankAccounts;
    @ManagedProperty(value = "#{bioBankAccountService}")
    private BioBankAccountService bioBankAccountService;
//end. bio bank
    //start. bio insurance
    private BioInsurance selectedBioInsurance;
    private List<BioInsurance> bioInsurances;
    @ManagedProperty(value = "#{bioInsuranceService}")
    private BioInsuranceService bioInsuranceService;
//end. bio insurance
    
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
            /////
//            bioBankAccounts = bioBankAccountService.getAllDataByBioDataId(selectedEmpData.getBioData().getId());
            bioMedicalHistorys = bioMedicalHistoryService.getAllDataByBioDataId(selectedEmpData.getBioData().getId());
            listBioSertifikasi = bioSertifikasiService.getAllDataByBioDataId(selectedEmpData.getBioData().getId());
            bioEmploymentHistorys = bioEmploymentHistoryService.getAllDataByBioDataId(selectedEmpData.getBioData().getId());
            bioProjects = bioProjectService.getAllDataByBioDataId(selectedEmpData.getBioData().getId());
            ListBioPotensiSwot = bioPotensiSwotService.getAllDataByBioDataId(selectedEmpData.getBioData().getId());
            bioDocuments = bioDocumentService.getAllDataByBioDataId(selectedEmpData.getBioData().getId());
            bioBankAccounts = bioBankAccountService.getAllDataByBioDataId(selectedEmpData.getBioData().getId());
            bioInsurances = bioInsuranceService.getAllDataByBioDataId(selectedEmpData.getBioData().getId());
            //Inisialisasi Riwayat Dinas
            businessTravelList = businessTravelService.getAllDataByEmpDataId(selectedEmpData.getId());
            
            //Looping List Dinas dan hitung Total Biaya dari masing - masing Dinas
            for (BusinessTravel businessTravel : businessTravelList) {
                countTotalAmoutOfBusinessTravel(businessTravel);
            }
            
            //Inisialisasi Riwayat Cuti
            leaveImplementations = leaveImplementationService.getAllDataByEmpDataId(selectedEmpData.getId());
            for (LeaveImplementation lv : leaveImplementations) {
                if (null != lv.getApprovalActivityNumber()) {
                    setLeaveApprovalOfficer(lv);
                }
            }
            
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }

    }
    
    //Hitung Total Biaya Perjalanan dari masing-masing perjalanan
    public void countTotalAmoutOfBusinessTravel(BusinessTravel businessTravel) throws Exception {
        List<BusinessTravelComponent> businessTravelComponents = businessTravelComponentService.getAllDataByBusinessTravelId(businessTravel.getId());
        Double totalAmount = 0.0;
        for (BusinessTravelComponent btc : businessTravelComponents) {
            totalAmount = totalAmount + btc.getPayByAmount();
        }
        businessTravel.setTotalAmount(totalAmount);
    }
    
    public void setLeaveApprovalOfficer(LeaveImplementation leaveImplementation) throws Exception {
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
        return "/protected/employee/emp_rotasi_form.htm?faces-redirect=true&execution=e" + selectedEmpData.getId();
    }

    public String doBack() {        
        return "/protected/personalia/emp_background_view.htm?faces-redirect=true";
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
        System.out.println(" eksekusi kartu nama");
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

    public BioMedicalHistory getSelectedBioMedicalHistory() {
        return selectedBioMedicalHistory;
    }

    public void setSelectedBioMedicalHistory(BioMedicalHistory selectedBioMedicalHistory) {
        this.selectedBioMedicalHistory = selectedBioMedicalHistory;
    }

    public List<BioMedicalHistory> getBioMedicalHistorys() {
        return bioMedicalHistorys;
    }

    public void setBioMedicalHistorys(List<BioMedicalHistory> bioMedicalHistorys) {
        this.bioMedicalHistorys = bioMedicalHistorys;
    }

    public BioMedicalHistoryService getBioMedicalHistoryService() {
        return bioMedicalHistoryService;
    }

    public void setBioMedicalHistoryService(BioMedicalHistoryService bioMedicalHistoryService) {
        this.bioMedicalHistoryService = bioMedicalHistoryService;
    }

    public BioSertifikasiService getBioSertifikasiService() {
        return bioSertifikasiService;
    }

    public void setBioSertifikasiService(BioSertifikasiService bioSertifikasiService) {
        this.bioSertifikasiService = bioSertifikasiService;
    }

    public List<BioSertifikasi> getListBioSertifikasi() {
        return listBioSertifikasi;
    }

    public void setListBioSertifikasi(List<BioSertifikasi> listBioSertifikasi) {
        this.listBioSertifikasi = listBioSertifikasi;
    }

    public BioSertifikasi getSelectedBioSertifikasi() {
        return selectedBioSertifikasi;
    }

    public void setSelectedBioSertifikasi(BioSertifikasi selectedBioSertifikasi) {
        this.selectedBioSertifikasi = selectedBioSertifikasi;
    }

    public BioEmploymentHistory getSelectedBioEmploymentHistory() {
        return selectedBioEmploymentHistory;
    }

    public void setSelectedBioEmploymentHistory(BioEmploymentHistory selectedBioEmploymentHistory) {
        this.selectedBioEmploymentHistory = selectedBioEmploymentHistory;
    }

    public List<BioEmploymentHistory> getBioEmploymentHistorys() {
        return bioEmploymentHistorys;
    }

    public void setBioEmploymentHistorys(List<BioEmploymentHistory> bioEmploymentHistorys) {
        this.bioEmploymentHistorys = bioEmploymentHistorys;
    }

    public BioEmploymentHistoryService getBioEmploymentHistoryService() {
        return bioEmploymentHistoryService;
    }

    public void setBioEmploymentHistoryService(BioEmploymentHistoryService bioEmploymentHistoryService) {
        this.bioEmploymentHistoryService = bioEmploymentHistoryService;
    }

    public BioProject getSelectedBioProject() {
        return selectedBioProject;
    }

    public void setSelectedBioProject(BioProject selectedBioProject) {
        this.selectedBioProject = selectedBioProject;
    }

    public List<BioProject> getBioProjects() {
        return bioProjects;
    }

    public void setBioProjects(List<BioProject> bioProjects) {
        this.bioProjects = bioProjects;
    }

    public BioProjectService getBioProjectService() {
        return bioProjectService;
    }

    public void setBioProjectService(BioProjectService bioProjectService) {
        this.bioProjectService = bioProjectService;
    }

    public BioPotensiSwot getSelectedBioPotensiSwot() {
        return selectedBioPotensiSwot;
    }

    public void setSelectedBioPotensiSwot(BioPotensiSwot selectedBioPotensiSwot) {
        this.selectedBioPotensiSwot = selectedBioPotensiSwot;
    }

    public List<BioPotensiSwot> getListBioPotensiSwot() {
        return ListBioPotensiSwot;
    }

    public void setListBioPotensiSwot(List<BioPotensiSwot> ListBioPotensiSwot) {
        this.ListBioPotensiSwot = ListBioPotensiSwot;
    }

    public BioPotensiSwotService getBioPotensiSwotService() {
        return bioPotensiSwotService;
    }

    public void setBioPotensiSwotService(BioPotensiSwotService bioPotensiSwotService) {
        this.bioPotensiSwotService = bioPotensiSwotService;
    }

    public BioDocument getSelectedBioDocument() {
        return selectedBioDocument;
    }

    public void setSelectedBioDocument(BioDocument selectedBioDocument) {
        this.selectedBioDocument = selectedBioDocument;
    }

    public List<BioDocument> getBioDocuments() {
        return bioDocuments;
    }

    public void setBioDocuments(List<BioDocument> bioDocuments) {
        this.bioDocuments = bioDocuments;
    }

    public BioDocumentService getBioDocumentService() {
        return bioDocumentService;
    }

    public void setBioDocumentService(BioDocumentService bioDocumentService) {
        this.bioDocumentService = bioDocumentService;
    }

    public String getFormData() {
        return formData;
    }

    public void setFormData(String formData) {
        this.formData = formData;
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
        
    
	public BioBankAccount getSelectedBioBankAccount() {
		return selectedBioBankAccount;
	}

	public void setSelectedBioBankAccount(BioBankAccount selectedBioBankAccount) {
		this.selectedBioBankAccount = selectedBioBankAccount;
	}

	public List<BioBankAccount> getBioBankAccounts() {
		return bioBankAccounts;
	}

	public void setBioBankAccounts(List<BioBankAccount> bioBankAccounts) {
		this.bioBankAccounts = bioBankAccounts;
	}

	public BioBankAccountService getBioBankAccountService() {
		return bioBankAccountService;
	}

	public void setBioBankAccountService(BioBankAccountService bioBankAccountService) {
		this.bioBankAccountService = bioBankAccountService;
	}

	
	public BioInsurance getSelectedBioInsurance() {
		return selectedBioInsurance;
	}

	public void setSelectedBioInsurance(BioInsurance selectedBioInsurance) {
		this.selectedBioInsurance = selectedBioInsurance;
	}

	public List<BioInsurance> getBioInsurances() {
		return bioInsurances;
	}

	public void setBioInsurances(List<BioInsurance> bioInsurances) {
		this.bioInsurances = bioInsurances;
	}

	public BioInsuranceService getBioInsuranceService() {
		return bioInsuranceService;
	}

	public void setBioInsuranceService(BioInsuranceService bioInsuranceService) {
		this.bioInsuranceService = bioInsuranceService;
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
        bioMedicalHistorys = null;
        listBioSertifikasi = null;
        bioEmploymentHistorys = null;
        bioProjects = null;
        ListBioPotensiSwot = null;
        bioDocuments = null;
        bioBankAccounts = null;
        bioBankAccountService = null;
    }
    
    
    ////////////////////bio medical history method//////////////////////////////
    public void doSelectBioMedicalHistory() {
        try {
            selectedBioMedicalHistory = bioMedicalHistoryService.getEntityByPkWithDetail(selectedBioMedicalHistory.getId());
        } catch (Exception e) {
            LOGGER.error("Error", e);
        }
    }

    public void doUpdateBioMedicalHistory() {

        List<String> bioMedicalHistoryId = new ArrayList<>();
        bioMedicalHistoryId.add(String.valueOf(selectedBioMedicalHistory.getId()));

        List<String> bioDataId = new ArrayList<>();
        bioDataId.add(String.valueOf(selectedEmpData.getBioData().getId()));

        Map<String, List<String>> dataToSend = new HashMap<>();
        dataToSend.put("bioMedicalHistoryId", bioMedicalHistoryId);
        dataToSend.put("bioDataId", bioDataId);
        showDialogBioMedicalHistory(dataToSend);

    }

    public void doAddBioMedicalHistory() {
        List<String> bioDataId = new ArrayList<>();
        bioDataId.add(String.valueOf(selectedEmpData.getBioData().getId()));

        Map<String, List<String>> dataToSend = new HashMap<>();
        dataToSend.put("bioDataId", bioDataId);
        showDialogBioMedicalHistory(dataToSend);
    }

    public void doDeleteBioMedicalHistory() {
        try {
            bioMedicalHistoryService.delete(selectedBioMedicalHistory);
            bioMedicalHistorys = bioMedicalHistoryService.getAllDataByBioDataId(selectedBioMedicalHistory.getBioData().getId());
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_INFO, "global.delete", "global.delete_successfully", FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());

        } catch (ConstraintViolationException | DataIntegrityViolationException ex) {
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_ERROR, "global.error", "error.delete_constraint", FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
        } catch (Exception ex) {
            LOGGER.error("Error when doDelete bioMedicalHistory", ex);
        }
    }

    private void showDialogBioMedicalHistory(Map<String, List<String>> params) {
        Map<String, Object> options = new HashMap<>();
        options.put("modal", true);
        options.put("draggable", true);
        options.put("resizable", false);
        options.put("contentWidth", 450);
        options.put("contentHeight", 420);
        RequestContext.getCurrentInstance().openDialog("bio_medic_hist_form", options, params);
    }

    public void onDialogReturnBioMedicalHistory(SelectEvent event) {
        try {
            bioMedicalHistorys = bioMedicalHistoryService.getAllDataByBioDataId(selectedEmpData.getBioData().getId());
            super.onDialogReturn(event);
        } catch (Exception e) {
            LOGGER.error("Error", e);
        }
    }
    ////////////////////////////////////////////////////////////////////////////
    
    
    
    /////////////////////////Employment History Method//////////////////////////
    public void doSelectBioEmploymentHistory() {
        try {
            selectedBioEmploymentHistory = bioEmploymentHistoryService.getEntityByPKWithDetail(selectedBioEmploymentHistory.getId());
        } catch (Exception e) {
            LOGGER.error("Error", e);
        }
    }

    public void doUpdateBioEmploymentHistory() {

        List<String> bioEmploymentHistoryId = new ArrayList<>();
        bioEmploymentHistoryId.add(String.valueOf(selectedBioEmploymentHistory.getId()));

        List<String> bioDataId = new ArrayList<>();
        bioDataId.add(String.valueOf(selectedEmpData.getBioData().getId()));

        Map<String, List<String>> dataToSend = new HashMap<>();
        dataToSend.put("bioEmploymentHistoryId", bioEmploymentHistoryId);
        dataToSend.put("bioDataId", bioDataId);
        showDialogBioEmploymentHistory(dataToSend);

    }

    public void doAddBioEmploymentHistory() {
        List<String> bioDataId = new ArrayList<>();
        bioDataId.add(String.valueOf(selectedEmpData.getBioData().getId()));

        Map<String, List<String>> dataToSend = new HashMap<>();
        dataToSend.put("bioDataId", bioDataId);
        showDialogBioEmploymentHistory(dataToSend);
    }

    public void doDeleteBioEmploymentHistory() {
        try {
            bioEmploymentHistoryService.delete(selectedBioEmploymentHistory);
            bioEmploymentHistorys = bioEmploymentHistoryService.getAllDataByBioDataId(selectedBioEmploymentHistory.getBioData().getId());
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_INFO, "global.delete", "global.delete_successfully", FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());

        } catch (ConstraintViolationException | DataIntegrityViolationException ex) {
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_ERROR, "global.error", "error.delete_constraint", FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
        } catch (Exception ex) {
            LOGGER.error("Error when doDelete bioEmploymentHistory", ex);
        }
    }

    private void showDialogBioEmploymentHistory(Map<String, List<String>> params) {
        Map<String, Object> options = new HashMap<>();
        options.put("modal", true);
        options.put("draggable", true);
        options.put("resizable", false);
        options.put("contentWidth", 550);
        options.put("contentHeight", 450);
        RequestContext.getCurrentInstance().openDialog("bio_emp_hist_form", options, params);
    }

    public void onDialogReturnBioEmploymentHistory(SelectEvent event) {
        try {
            bioEmploymentHistorys = bioEmploymentHistoryService.getAllDataByBioDataId(selectedEmpData.getBioData().getId());
            super.onDialogReturn(event);
        } catch (Exception e) {
            LOGGER.error("Error", e);
        }
    }
    ////////////////////////////////////////////////////////////////////////////
    
    
    //////////////////////////////Potensi///////////////////////////////////////
    public void doSelectBioPotensiSwot() {
        try {
            selectedBioPotensiSwot = bioPotensiSwotService.getEntiyByPK(selectedBioPotensiSwot.getId());
        } catch (Exception e) {
            LOGGER.error("Error", e);
        }
    }

    public void doUpdateBioPotensiSwot() {

        List<String> bioBankPotensiSwotId = new ArrayList<>();
        bioBankPotensiSwotId.add(String.valueOf(selectedBioPotensiSwot.getId()));

        List<String> bioDataId = new ArrayList<>();
        bioDataId.add(String.valueOf(selectedEmpData.getBioData().getId()));

        Map<String, List<String>> dataToSend = new HashMap<>();
        dataToSend.put("bioPotensiSwotId", bioBankPotensiSwotId);
        dataToSend.put("bioDataId", bioDataId);
        showDialogBioPotensiSwot(dataToSend);

    }
    
    public void doDeleteBioPotensi() {
        try {
            bioPotensiSwotService.delete(selectedBioPotensiSwot);
            ListBioPotensiSwot = bioPotensiSwotService.getAllDataByBioDataId(selectedBioPotensiSwot.getBioData().getId());
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_INFO, "global.delete", "global.delete_successfully", FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());

        } catch (ConstraintViolationException | DataIntegrityViolationException ex) {
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_ERROR, "global.error", "error.delete_constraint", FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
        } catch (Exception ex) {
            LOGGER.error("Error when doDelete bioBankAccount", ex);
        }
    }
    
    public void doAddBioPotensiSwot() {
        List<String> bioDataId = new ArrayList<>();
        bioDataId.add(String.valueOf(selectedEmpData.getBioData().getId()));

        Map<String, List<String>> dataToSend = new HashMap<>();
        dataToSend.put("bioDataId", bioDataId);
        showDialogBioPotensiSwot(dataToSend);
    }
    
    private void showDialogBioPotensiSwot(Map<String, List<String>> params) {
        Map<String, Object> options = new HashMap<>();
        options.put("modal", true);
        options.put("draggable", true);
        options.put("resizable", false);
        options.put("contentWidth", 500);
        options.put("contentHeight", 400);
        RequestContext.getCurrentInstance().openDialog("bio_potensi_swot_form", options, params);
    }
    
    public void onDialogReturnBioPotensiSwot(SelectEvent event) {
        try {
            ListBioPotensiSwot = bioPotensiSwotService.getAllDataByBioDataId(selectedEmpData.getBioData().getId());
            super.onDialogReturn(event);
        } catch (Exception e) {
            LOGGER.error("Error", e);
        }
    }
    
    ////////////////////////////////////////////////////////////////////////////

    
    //////////////////////////////////Project///////////////////////////////////
    public void doSelectBioProject() {
        try {
            selectedBioProject = bioProjectService.getEntityByPKWithDetail(selectedBioProject.getId());
        } catch (Exception e) {
            LOGGER.error("Error", e);
        }
    }

    public void doUpdateBioProject() {

        List<String> bioProjectId = new ArrayList<>();
        bioProjectId.add(String.valueOf(selectedBioProject.getId()));

        List<String> bioDataId = new ArrayList<>();
        bioDataId.add(String.valueOf(selectedEmpData.getBioData().getId()));

        Map<String, List<String>> dataToSend = new HashMap<>();
        dataToSend.put("bioProjectId", bioProjectId);
        dataToSend.put("bioDataId", bioDataId);
        showDialogBioProject(dataToSend);

    }

    public void doAddBioProject() {
        List<String> bioDataId = new ArrayList<>();
        bioDataId.add(String.valueOf(selectedEmpData.getBioData().getId()));

        Map<String, List<String>> dataToSend = new HashMap<>();
        dataToSend.put("bioDataId", bioDataId);
        showDialogBioProject(dataToSend);
    }

    public void doDeleteBioProject() {
        try {
            bioProjectService.delete(selectedBioProject);
            bioProjects = bioProjectService.getAllDataByBioDataId(selectedBioProject.getBioData().getId());
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_INFO, "global.delete", "global.delete_successfully", FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());

        } catch (ConstraintViolationException | DataIntegrityViolationException ex) {
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_ERROR, "global.error", "error.delete_constraint", FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
        } catch (Exception ex) {
            LOGGER.error("Error when doDelete bioProject", ex);
        }
    }

    private void showDialogBioProject(Map<String, List<String>> params) {
        Map<String, Object> options = new HashMap<>();
        options.put("modal", true);
        options.put("draggable", true);
        options.put("resizable", false);
        options.put("contentWidth", 550);
        options.put("contentHeight", 450);
        RequestContext.getCurrentInstance().openDialog("bio_project_form", options, params);
    }

    public void onDialogReturnBioProject(SelectEvent event) {
        try {
            bioProjects = bioProjectService.getAllDataByBioDataId(selectedEmpData.getBioData().getId());
            super.onDialogReturn(event);
        } catch (Exception e) {
            LOGGER.error("Error", e);
        }
    }
    ////////////////////////////////////////////////////////////////////////////
    
    
    /////////////////////////////Sertifikasi////////////////////////////////////
    public void doSelectBioSertifikasi() {
        try {
            selectedBioSertifikasi = bioSertifikasiService.getEntityByPKWithDetail(selectedBioSertifikasi.getId());
        } catch (Exception e) {
            LOGGER.error("Error", e);
        }
    }
    
     public void doDeleteBioSertifikasi() {
        try {
            bioSertifikasiService.delete(selectedBioSertifikasi);
            listBioSertifikasi = bioSertifikasiService.getAllDataByBioDataId(selectedBioSertifikasi.getBioData().getId());
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_INFO, "global.delete", "global.delete_successfully", FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());

        } catch (ConstraintViolationException | DataIntegrityViolationException ex) {
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_ERROR, "global.error", "error.delete_constraint", FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
        } catch (Exception ex) {
            LOGGER.error("Error when doDelete bioProject", ex);
        }
    }
    
     public void doAddBioSertifikasi() {
        List<String> bioDataId = new ArrayList<>();
        bioDataId.add(String.valueOf(selectedEmpData.getBioData().getId()));

        Map<String, List<String>> dataToSend = new HashMap<>();
        dataToSend.put("bioDataId", bioDataId);
        showDialogBioSertifikasi(dataToSend);
    }
    
    public void doUpdateBioSertifikasi() {
        List<String> bioSertifikasiId = new ArrayList<>();
        bioSertifikasiId.add(String.valueOf(selectedBioSertifikasi.getId()));

        List<String> bioDataId = new ArrayList<>();
        bioDataId.add(String.valueOf(selectedEmpData.getBioData().getId()));

        Map<String, List<String>> dataToSend = new HashMap<>();
        dataToSend.put("bioSertifikasiId", bioSertifikasiId);
        dataToSend.put("bioDataId", bioDataId);
        showDialogBioSertifikasi(dataToSend);
    }
    
     private void showDialogBioSertifikasi(Map<String, List<String>> params) {
        Map<String, Object> options = new HashMap<>();
        options.put("modal", true);
        options.put("draggable", true);
        options.put("resizable", false);
        options.put("contentWidth", 600);
        options.put("contentHeight", 520);
        RequestContext.getCurrentInstance().openDialog("bio_sertifikasi_form", options, params);
    }

    public void onDialogReturnBioSertifikasi(SelectEvent event) {
        try {
            listBioSertifikasi = bioSertifikasiService.getAllDataByBioDataId(selectedEmpData.getBioData().getId());
            super.onDialogReturn(event);
        } catch (Exception e) {
            LOGGER.error("Error", e);
        }
    }
    ////////////////////////////////////////////////////////////////////////////
    
    ///////////////////////////////Document/////////////////////////////////////
    public void doSelectBioDocument() {
        try {
            selectedBioDocument = bioDocumentService.getEntiyByPK(selectedBioDocument.getId());
        } catch (Exception e) {
            LOGGER.error("Error", e);
        }
    }

    public void doUpdateBioDocument() {
        List<String> bioDocumentId = new ArrayList<>();
        bioDocumentId.add(String.valueOf(selectedBioDocument.getId()));

        List<String> bioDataId = new ArrayList<>();
        bioDataId.add(String.valueOf(selectedEmpData.getBioData().getId()));

        Map<String, List<String>> dataToSend = new HashMap<>();
        dataToSend.put("bioDocumentId", bioDocumentId);
        dataToSend.put("bioDataId", bioDataId);
        showDialogBioDocument(dataToSend);
    }

    public void doAddBioDocument() {
        List<String> bioDataId = new ArrayList<>();
        bioDataId.add(String.valueOf(selectedEmpData.getBioData().getId()));

        Map<String, List<String>> dataToSend = new HashMap<>();
        dataToSend.put("bioDataId", bioDataId);
        showDialogBioDocument(dataToSend);
    }

    public void doDeleteBioDocument() {
        try {
            bioDocumentService.delete(selectedBioDocument);
            bioDocuments = bioDocumentService.getAllDataByBioDataId(selectedBioDocument.getBioData().getId());
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_INFO, "global.delete", "global.delete_successfully", FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());

        } catch (ConstraintViolationException | DataIntegrityViolationException ex) {
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_ERROR, "global.error", "error.delete_constraint", FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
        } catch (Exception ex) {
            LOGGER.error("Error when doDelete bioDocument", ex);
        }
    }

    private void showDialogBioDocument(Map<String, List<String>> params) {
        Map<String, Object> options = new HashMap<>();
        options.put("modal", true);
        options.put("draggable", true);
        options.put("resizable", false);
        options.put("contentWidth", 600);
        options.put("contentHeight", 420);
        RequestContext.getCurrentInstance().openDialog("bio_doc_form", options, params);
    }

    public void onDialogReturnBioDocument(SelectEvent event) {
        try {
            bioDocuments = bioDocumentService.getAllDataByBioDataId(selectedEmpData.getBioData().getId());
            super.onDialogReturn(event);
        } catch (Exception e) {
            LOGGER.error("Error", e);
        }
    }
    
    /*
     * Bank Account
     */
    public void doAddBioBankAccount() {
        List<String> bioDataId = new ArrayList<>();
        bioDataId.add(String.valueOf(selectedEmpData.getBioData().getId()));

        Map<String, List<String>> dataToSend = new HashMap<>();
        dataToSend.put("bioDataId", bioDataId);
        showDialogBioBankAccount(dataToSend);
    }
    
    public void doDeleteBioBankAccount() {
        try {
            bioBankAccountService.delete(selectedBioBankAccount);
            bioBankAccounts = bioBankAccountService.getAllDataByBioDataId(selectedBioBankAccount.getBioData().getId());
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_INFO, "global.delete", "global.delete_successfully", FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());

        } catch (ConstraintViolationException | DataIntegrityViolationException ex) {
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_ERROR, "global.error", "error.delete_constraint", FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
        } catch (Exception ex) {
            LOGGER.error("Error when doDelete bioBankAccount", ex);
        }
    }
    
    public void doUpdateBioBankAccount() {

        List<String> bioBankAccountId = new ArrayList<>();
        bioBankAccountId.add(String.valueOf(selectedBioBankAccount.getId()));

        List<String> bioDataId = new ArrayList<>();
        bioDataId.add(String.valueOf(selectedEmpData.getBioData().getId()));

        Map<String, List<String>> dataToSend = new HashMap<>();
        dataToSend.put("bioBankAccountId", bioBankAccountId);
        dataToSend.put("bioDataId", bioDataId);
        showDialogBioBankAccount(dataToSend);

    }
    
    public void doSelectBioBankAccount() {
        try {
            selectedBioBankAccount = bioBankAccountService.getEntityByPKWithDetail(selectedBioBankAccount.getId());
        } catch (Exception e) {
            LOGGER.error("Error", e);
        }
    }
    
    private void showDialogBioBankAccount(Map<String, List<String>> params) {
        Map<String, Object> options = new HashMap<>();
        options.put("modal", true);
        options.put("draggable", true);
        options.put("resizable", false);
        options.put("contentWidth", 500);
        options.put("contentHeight", 500);
        RequestContext.getCurrentInstance().openDialog("bio_bank_acc_form", options, params);
    }
    
    public void onDialogReturnBioBankAccount(SelectEvent event) {
        try {
            bioBankAccounts = bioBankAccountService.getAllDataByBioDataId(selectedEmpData.getBioData().getId());
            super.onDialogReturn(event);
        } catch (Exception e) {
            LOGGER.error("Error", e);
        }
    }
    
    /**
     * START Bio Insurance method
     */
    public void doSelectBioInsurance() {
        try {
            selectedBioInsurance = bioInsuranceService.getEntiyByPK(selectedBioInsurance.getId());
        } catch (Exception e) {
            LOGGER.error("Error", e);
        }
    }

    public void doUpdateBioInsurance() {

        List<String> bioInsuranceId = new ArrayList<>();
        bioInsuranceId.add(String.valueOf(selectedBioInsurance.getId()));

        List<String> bioDataId = new ArrayList<>();
        bioDataId.add(String.valueOf(selectedEmpData.getBioData().getId()));

        Map<String, List<String>> dataToSend = new HashMap<>();
        dataToSend.put("bioInsuranceId", bioInsuranceId);
        dataToSend.put("bioDataId", bioDataId);
        showDialogBioInsurance(dataToSend);

    }

    public void doAddBioInsurance() {
        List<String> bioDataId = new ArrayList<>();
        bioDataId.add(String.valueOf(selectedEmpData.getBioData().getId()));

        Map<String, List<String>> dataToSend = new HashMap<>();
        dataToSend.put("bioDataId", bioDataId);
        showDialogBioInsurance(dataToSend);
    }

    public void doDeleteBioInsurance() {
        try {
            bioInsuranceService.delete(selectedBioInsurance);
            bioInsurances = bioInsuranceService.getAllDataByBioDataId(selectedBioInsurance.getBioData().getId());
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_INFO, "global.delete", "global.delete_successfully", FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());

        } catch (ConstraintViolationException | DataIntegrityViolationException ex) {
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_ERROR, "global.error", "error.delete_constraint", FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
        } catch (Exception ex) {
            LOGGER.error("Error when doDelete bioInsurance", ex);
        }
    }

    private void showDialogBioInsurance(Map<String, List<String>> params) {
        Map<String, Object> options = new HashMap<>();
        options.put("modal", true);
        options.put("draggable", true);
        options.put("resizable", false);
        options.put("contentWidth", 450);
        options.put("contentHeight", 420);
        RequestContext.getCurrentInstance().openDialog("bio_insurance_form", options, params);
    }

    public void onDialogReturnBioInsurance(SelectEvent event) {
        try {
            bioInsurances = bioInsuranceService.getAllDataByBioDataId(selectedEmpData.getBioData().getId());
            super.onDialogReturn(event);
        } catch (Exception e) {
            LOGGER.error("Error", e);
        }
    }

    /**
     * END Bio Insurance method
     */
    
}
