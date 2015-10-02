package com.inkubator.hrm.web.recruitment;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import com.inkubator.hrm.service.BusinessTypeService;
import com.inkubator.hrm.service.DialectService;
import com.inkubator.hrm.service.EducationLevelService;
import com.inkubator.hrm.service.InstitutionEducationService;
import com.inkubator.hrm.service.KlasifikasiKerjaService;
import com.inkubator.hrm.service.MaritalStatusService;
import com.inkubator.hrm.service.NationalityService;
import com.inkubator.hrm.service.RaceService;
import com.inkubator.hrm.service.RecruitApplicantService;
import com.inkubator.hrm.service.ReligionService;
import com.inkubator.hrm.web.model.ApplicantModel;
import com.inkubator.webcore.controller.BaseController;

/**
 *
 * @author rizkykojek
 */
@ManagedBean(name = "recruitApplicantFormController")
@ViewScoped
public class RecruitApplicantFormController extends BaseController {

	private ApplicantModel model;
	@ManagedProperty(value = "#{recruitApplicantService}")
    private RecruitApplicantService recruitApplicantService;
	@ManagedProperty(value = "#{maritalStatusService}")
    private MaritalStatusService maritalStatusService;
	@ManagedProperty(value = "#{religionService}")
    private ReligionService religionService;
	@ManagedProperty(value = "#{raceService}")
    private RaceService raceService;
	@ManagedProperty(value = "#{dialectService}")
    private DialectService dialectService;
	@ManagedProperty(value = "#{nationalityService}")
    private NationalityService nationalityService;
	@ManagedProperty(value = "#{educationLevelService}")
    private EducationLevelService educationLevelService;
	@ManagedProperty(value = "#{institutionEducationService}")
	private InstitutionEducationService institutionEducationService;
	@ManagedProperty(value = "#{businessTypeService}")
	private BusinessTypeService businessTypeService;
	@ManagedProperty(value = "#{klasifikasiKerjaService}")
	private KlasifikasiKerjaService klasifikasiKerjaService;
	
	
	@PostConstruct
    @Override
    public void initialization() {
        super.initialization();
        try {
        	model = new ApplicantModel();
        	model.setListEducationLevel(educationLevelService.getAllDataOrderByLevel());
        	model.setListDialect(dialectService.getAllData());
        	model.setListBusinessType(businessTypeService.getAllData());
        	model.setListInstitutionEducation(institutionEducationService.getAllData());
        	model.setListKlasifikasiKerja(klasifikasiKerjaService.getAllData());
        	model.setListMaritalStatus(maritalStatusService.getAllData());
        	model.setListNationality(nationalityService.getAllData());
        	model.setListRace(raceService.getAllData());
        	model.setListReligion(religionService.getAllData());
        } catch (Exception e) {
            LOGGER.error("Error", e);
        }
    }

    @PreDestroy
    public void cleanAndExit() {
    	model = null;
    	recruitApplicantService = null;
    	maritalStatusService = null;
    	religionService = null;
    	raceService = null;
    	dialectService = null;
    	nationalityService = null;
    	educationLevelService = null;
    	institutionEducationService = null;
    	businessTypeService = null;
    	klasifikasiKerjaService = null;
    }
    
    public void doSave(){
    	
    }
    
    public String doBack(){
    	return "/protected/recruitment/recruit_applicant_view.htm?faces-redirect=true";
    }

	public ApplicantModel getModel() {
		return model;
	}

	public void setModel(ApplicantModel model) {
		this.model = model;
	}

	public RecruitApplicantService getRecruitApplicantService() {
		return recruitApplicantService;
	}

	public void setRecruitApplicantService(RecruitApplicantService recruitApplicantService) {
		this.recruitApplicantService = recruitApplicantService;
	}

	public MaritalStatusService getMaritalStatusService() {
		return maritalStatusService;
	}

	public void setMaritalStatusService(MaritalStatusService maritalStatusService) {
		this.maritalStatusService = maritalStatusService;
	}

	public ReligionService getReligionService() {
		return religionService;
	}

	public void setReligionService(ReligionService religionService) {
		this.religionService = religionService;
	}

	public RaceService getRaceService() {
		return raceService;
	}

	public void setRaceService(RaceService raceService) {
		this.raceService = raceService;
	}

	public DialectService getDialectService() {
		return dialectService;
	}

	public void setDialectService(DialectService dialectService) {
		this.dialectService = dialectService;
	}

	public NationalityService getNationalityService() {
		return nationalityService;
	}

	public void setNationalityService(NationalityService nationalityService) {
		this.nationalityService = nationalityService;
	}

	public EducationLevelService getEducationLevelService() {
		return educationLevelService;
	}

	public void setEducationLevelService(EducationLevelService educationLevelService) {
		this.educationLevelService = educationLevelService;
	}

	public InstitutionEducationService getInstitutionEducationService() {
		return institutionEducationService;
	}

	public void setInstitutionEducationService(InstitutionEducationService institutionEducationService) {
		this.institutionEducationService = institutionEducationService;
	}

	public BusinessTypeService getBusinessTypeService() {
		return businessTypeService;
	}

	public void setBusinessTypeService(BusinessTypeService businessTypeService) {
		this.businessTypeService = businessTypeService;
	}

	public KlasifikasiKerjaService getKlasifikasiKerjaService() {
		return klasifikasiKerjaService;
	}

	public void setKlasifikasiKerjaService(KlasifikasiKerjaService klasifikasiKerjaService) {
		this.klasifikasiKerjaService = klasifikasiKerjaService;
	}    
    
}
