package com.inkubator.hrm.web.recruitment;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.primefaces.model.DualListModel;

import com.inkubator.exception.BussinessException;
import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.entity.BioData;
import com.inkubator.hrm.entity.OrgTypeOfSpec;
import com.inkubator.hrm.entity.OrgTypeOfSpecList;
import com.inkubator.hrm.entity.RecruitApplicant;
import com.inkubator.hrm.entity.RecruitApplicantSpecList;
import com.inkubator.hrm.service.BusinessTypeService;
import com.inkubator.hrm.service.CityService;
import com.inkubator.hrm.service.DialectService;
import com.inkubator.hrm.service.EducationLevelService;
import com.inkubator.hrm.service.InstitutionEducationService;
import com.inkubator.hrm.service.KlasifikasiKerjaService;
import com.inkubator.hrm.service.MaritalStatusService;
import com.inkubator.hrm.service.NationalityService;
import com.inkubator.hrm.service.OrgTypeOfSpecListService;
import com.inkubator.hrm.service.OrgTypeOfSpecService;
import com.inkubator.hrm.service.RaceService;
import com.inkubator.hrm.service.RecruitApplicantService;
import com.inkubator.hrm.service.RecruitVacancyAdvertisementService;
import com.inkubator.hrm.service.ReligionService;
import com.inkubator.hrm.web.model.ApplicantModel;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesUtil;
import com.inkubator.webcore.util.MessagesResourceUtil;

import ch.lambdaj.Lambda;
import ch.lambdaj.group.Group;

/**
 *
 * @author rizkykojek
 */
@ManagedBean(name = "recruitApplicantFormController")
@ViewScoped
public class RecruitApplicantFormController extends BaseController {

	private ApplicantModel model;
	private Boolean isUpdate;
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
	@ManagedProperty(value = "#{cityService}")
	private CityService cityService;
	@ManagedProperty(value = "#{orgTypeOfSpecListService}")
	private OrgTypeOfSpecListService orgTypeOfSpecListService;
	@ManagedProperty(value = "#{orgTypeOfSpecService}")
	private OrgTypeOfSpecService orgTypeOfSpecService;
	@ManagedProperty(value = "#{recruitVacancyAdvertisementService}")
	private RecruitVacancyAdvertisementService recruitVacancyAdvertisementService;
	
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
        	model.setListCity(cityService.getAllData());
        	model.setSpecListDualModel(orgTypeOfSpecListService.getAllBySpectJabatan());
        	model.setSpecListName(orgTypeOfSpecListService.getOrgTypeSpecName());
        	
        	
        	isUpdate = Boolean.FALSE;
        	String param = FacesUtil.getRequestParameter("execution");
			if (StringUtils.isNotEmpty(param) && StringUtils.isNumeric(param.substring(1))) {
				RecruitApplicant applicant = recruitApplicantService.getEntityByPkWithDetail(Long.parseLong(param.substring(1)));
				if (applicant != null) {
					this.getModelFromEntity(applicant);
					isUpdate = Boolean.TRUE;
					
					//untuk "edit/update applicant", tampilkan semua list iklan lowongan tanpa ada filter 
					model.setListVacancyAdvertisement(recruitVacancyAdvertisementService.getAllData());
				}
			} else {
				//default value
				DateTime dt = new DateTime();
				model.setEducationEndYear(dt.getYear());
				model.setEducationStartYear(dt.getYear());
				model.setLastWorkSince(dt.getYear());
				model.setLastWorkEnd(dt.getYear());
				model.setLastJabatanSince(dt.getYear());
				model.setIsActive(Boolean.TRUE);
				model.setIsVerified(Boolean.TRUE);
				
				//untuk "add applicant",  list iklan lowongan yang  ditampilan difilter berdasarkan "efektif date"
				model.setListVacancyAdvertisement(recruitVacancyAdvertisementService.getAllDataIsStillEffective()); 
			}
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
    	cityService = null;
    	orgTypeOfSpecListService = null;
    	orgTypeOfSpecService = null;
    	recruitVacancyAdvertisementService = null;
    }
    
    public String doSave(){
        try {
        	RecruitApplicant applicant;
            if (isUpdate) {
            	applicant = recruitApplicantService.update(model);
            	MessagesResourceUtil.setMessagesFlas(FacesMessage.SEVERITY_INFO, "global.save_info", "global.update_successfully",
                        FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
            } else {
            	applicant = recruitApplicantService.save(model);
            	MessagesResourceUtil.setMessagesFlas(FacesMessage.SEVERITY_INFO, "global.save_info", "global.added_successfully",
                        FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
            }
            return "/protected/recruitment/recruit_applicant_detail.htm?faces-redirect=true&execution=e" + applicant.getId();
            
        } catch (BussinessException ex) { 
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_ERROR, "global.error", ex.getErrorKeyMessage(), FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
        
        return null;
    }

	public String doBack(){
    	return "/protected/recruitment/recruit_applicant_view.htm?faces-redirect=true";
    }
	
	public void onChangeFreshGraduate(){
		model.setLastWorkCompany(StringUtils.EMPTY);
		model.setBusinessTypeId(null);
		model.setLastWorkSince(null);
		model.setLastWorkEnd(null);
		model.setLastJabatan(StringUtils.EMPTY);
		model.setKlasifikasiKerjaId(null);
		model.setLastJabatanSince(null);
	}
	
    @SuppressWarnings({ "rawtypes", "unchecked" })
	private void getModelFromEntity(RecruitApplicant applicant) {
		model.setId(applicant.getId());
		model.setEducationLevelId(applicant.getEducationLevel() != null ? applicant.getEducationLevel().getId() : null);
		model.setInstitutionEducationId(applicant.getInstitutionEducation() != null ? applicant.getInstitutionEducation().getId() : null);
		model.setEducationStartYear(applicant.getEducationStartYear());
		model.setEducationEndYear(applicant.getEducationEndYear());
		model.setScale(applicant.getScale());
		model.setScore(applicant.getScore());
		model.setCertificateNumber(applicant.getCertificateNumber());
		model.setLastWorkCompany(applicant.getLastWorkCompany());
		model.setLastWorkSince(applicant.getLastWorkSince());
		model.setLastWorkEnd(applicant.getLastWorkEnd());
		model.setLastJabatan(applicant.getLastJabatan());
		model.setKlasifikasiKerjaId(applicant.getKlasifikasiKerja() != null ? applicant.getKlasifikasiKerja().getId() : null);
		model.setLastJabatanSince(applicant.getLastJabatanSince());
		model.setBusinessTypeId(applicant.getBusinessType() != null ? applicant.getBusinessType().getId() : null);
		model.setIsFreshGraduate(applicant.getIsFreshGraduate());
		model.setIsActive(applicant.getIsActive());
		model.setIsVerified(applicant.getIsVerified());
		model.setVacancyAdvertisementId(applicant.getRecruitVacancyAdvertisement().getId());
		model.setUploadPath(applicant.getUploadPath());
		
		BioData bioData = applicant.getBioData();
		model.setBioDataId(bioData.getId());
		model.setFirstName(bioData.getFirstName());
		model.setLastName(bioData.getLastName());
		model.setTitle(bioData.getTitle());
		model.setNickname(bioData.getNickname());
		model.setDateOfBirth(bioData.getDateOfBirth());
		model.setCityOfBirthId(bioData.getCity() != null ? bioData.getCity().getId() : null);
		model.setGender(bioData.getGender());
		model.setMaritalStatusId(bioData.getMaritalStatus() != null ? bioData.getMaritalStatus().getId() : null);
		model.setNoKK(bioData.getNoKK());
		model.setBloodType(bioData.getBloodType());
		model.setNpwp(bioData.getNpwp());
		model.setJamsostek(bioData.getJamsostek());
		model.setNationalityId(bioData.getNationality() != null ? bioData.getNationality().getId() : null);
		model.setDialectId(bioData.getDialect() != null ? bioData.getDialect().getId() : null);
		model.setReligionId(bioData.getReligion() != null ? bioData.getReligion().getId() : null);
		model.setRaceId(bioData.getRace() != null ? bioData.getRace().getId() : null);
		model.setPhoneNumber(bioData.getMobilePhone());
		model.setEmailAddress(bioData.getPersonalEmail());
		
		//set dual list for OrgTypeOfSpecList. Removed OrgTypeOfSpecList that already selected from "available" list
		try {
			List<String> specListName = model.getSpecListName();
	        List<DualListModel> specListDualModel = model.getSpecListDualModel();
			
			//Group selected list by OrgTypeOfSpec
			List<OrgTypeOfSpecList> allSelectedSpecList = Lambda.extract(applicant.getRecruitApplicantSpecLists(), Lambda.on(RecruitApplicantSpecList.class).getOrgTypeOfSpecList());
			Group<OrgTypeOfSpecList> groupSelectedSpecList = Lambda.group(allSelectedSpecList, Lambda.by(Lambda.on(OrgTypeOfSpecList.class).getOrgTypeOfSpec().getId()));
	        
	        //iterate each group list element
	        for (String key : groupSelectedSpecList.keySet()) {
	            OrgTypeOfSpec orgTypeOfSpec = orgTypeOfSpecService.getEntiyByPK(Long.parseLong(key));
	            int index = specListName.indexOf(orgTypeOfSpec.getName());

	            if (index != -1) {
	            	System.out.println("=========================");
	            	List<OrgTypeOfSpecList> selectedSpecList = groupSelectedSpecList.find(key);
	            	System.out.println("selected " + selectedSpecList.size());
	                List<OrgTypeOfSpecList> availableSpecList = specListDualModel.get(index).getSource();
	                System.out.println("available " + availableSpecList.size());
	                availableSpecList.removeAll(selectedSpecList);

	                System.out.println("selected " + selectedSpecList.size());
	                System.out.println("available " + availableSpecList.size());
	                specListDualModel.get(index).setTarget(selectedSpecList);
	                specListDualModel.get(index).setSource(availableSpecList);
	            }
	        }
	        
	        model.setSpecListDualModel(specListDualModel);
		} catch (Exception ex) {
			LOGGER.error("Error", ex);
		}		
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

	public CityService getCityService() {
		return cityService;
	}

	public void setCityService(CityService cityService) {
		this.cityService = cityService;
	}

	public OrgTypeOfSpecListService getOrgTypeOfSpecListService() {
		return orgTypeOfSpecListService;
	}

	public void setOrgTypeOfSpecListService(OrgTypeOfSpecListService orgTypeOfSpecListService) {
		this.orgTypeOfSpecListService = orgTypeOfSpecListService;
	}

	public OrgTypeOfSpecService getOrgTypeOfSpecService() {
		return orgTypeOfSpecService;
	}

	public void setOrgTypeOfSpecService(OrgTypeOfSpecService orgTypeOfSpecService) {
		this.orgTypeOfSpecService = orgTypeOfSpecService;
	}

	public RecruitVacancyAdvertisementService getRecruitVacancyAdvertisementService() {
		return recruitVacancyAdvertisementService;
	}

	public void setRecruitVacancyAdvertisementService(
			RecruitVacancyAdvertisementService recruitVacancyAdvertisementService) {
		this.recruitVacancyAdvertisementService = recruitVacancyAdvertisementService;
	}   
	
}
