package com.inkubator.hrm.web.personalia;

import ch.lambdaj.Lambda;

import com.inkubator.common.util.RandomNumberUtil;
import com.inkubator.exception.BussinessException;
import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.entity.BioAddress;
import com.inkubator.hrm.entity.BioData;
import com.inkubator.hrm.entity.BioEmergencyContact;
import com.inkubator.hrm.entity.City;
import com.inkubator.hrm.entity.Dialect;
import com.inkubator.hrm.entity.EmpData;
import com.inkubator.hrm.entity.FamilyRelation;
import com.inkubator.hrm.entity.LoanNewApplication;
import com.inkubator.hrm.entity.MaritalStatus;
import com.inkubator.hrm.entity.Nationality;
import com.inkubator.hrm.entity.Race;
import com.inkubator.hrm.entity.Religion;
import com.inkubator.hrm.service.BioAddressService;
import com.inkubator.hrm.service.BioDataService;
import com.inkubator.hrm.service.BioEmergencyContactService;
import com.inkubator.hrm.service.CityService;
import com.inkubator.hrm.service.DialectService;
import com.inkubator.hrm.service.EmpDataService;
import com.inkubator.hrm.service.FamilyRelationService;
import com.inkubator.hrm.service.MaritalStatusService;
import com.inkubator.hrm.service.NationalityService;
import com.inkubator.hrm.service.RaceService;
import com.inkubator.hrm.service.ReligionService;
import com.inkubator.hrm.util.HrmUserInfoUtil;
import com.inkubator.hrm.web.model.BioDataModel;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesIO;
import com.inkubator.webcore.util.FacesUtil;
import com.inkubator.webcore.util.MessagesResourceUtil;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.hamcrest.Matchers;
import org.hibernate.exception.ConstraintViolationException;
import org.primefaces.context.RequestContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.UploadedFile;
import org.springframework.dao.DataIntegrityViolationException;

/**
 *
 * @author Ahmad Mudzakkir Amal
 */
@ManagedBean(name = "bioDataRevisionRequestFormController")
@ViewScoped
public class BioDataRevisionRequestFormController extends BaseController {

    private BioDataModel bioDataModel;
    private Map<String, Long> tempatlahir = new HashMap<>();
    private Map<String, Long> mapReligions = new HashMap<>();
    private Map<String, Long> mapRas = new HashMap<>();
    private Map<String, Long> mapDialek = new HashMap<>();
    private Map<String, Long> mapMarital = new HashMap<>();
    private Map<String, Long> mapNationality = new HashMap<>();    
    @ManagedProperty(value = "#{cityService}")
    private CityService cityService;
    @ManagedProperty(value = "#{religionService}")
    private ReligionService religionService;
    @ManagedProperty(value = "#{raceService}")
    private RaceService raceService;
    @ManagedProperty(value = "#{dialectService}")
    private DialectService dialectService;
    @ManagedProperty(value = "#{maritalStatusService}")
    private MaritalStatusService maritalStatusService;
    @ManagedProperty(value = "#{nationalityService}")
    private NationalityService nationalityService;
    @ManagedProperty(value = "#{empDataService}")
    private EmpDataService empDataService;
    @ManagedProperty(value = "#{facesIO}")
    private FacesIO facesIO;
    @ManagedProperty(value = "#{bioDataService}")
    private BioDataService bioDataService;
    @ManagedProperty(value = "#{familyRelationService}")
    private FamilyRelationService familyRelationService;
    private UploadedFile fotoFile;
    private String fotoFileName;
    private UploadedFile fingerFile;
    private String fingerFileName;
    private UploadedFile signatureFile;
    private String signatureFileName;
    private String selectedJenisData;
    private EmpData empData;
    private BioData selectedBioData;
    
    //Start BioAddress
    private List<BioAddress> bioAddresses;
    @ManagedProperty(value = "#{bioAddressService}")
    private BioAddressService bioAddressService;
    private BioAddress selectedBioAddress;
    //End BioAddress
    
    //Start. bio Emergency Contact
    @ManagedProperty(value = "#{bioEmergencyContactService}")
    private BioEmergencyContactService bioEmergencyContactService;
    private List<BioEmergencyContact> dataBioEmergencyContacs;
    private BioEmergencyContact selectedBioEmergencyContact;
    //End. bio Emergency Contact
    
    @PostConstruct
    @Override
    public void initialization() {
        try {
            super.initialization();
            bioDataModel = new BioDataModel();
            Long bioDataId = HrmUserInfoUtil.getBioDataId();
            empData = empDataService.getByEmpDataByBioDataId(bioDataId);
            
            selectedJenisData = HRMConstant.BIO_REV_DETAIL_BIO_DATA;
            if (bioDataId != null) {
                
            	selectedBioData = bioDataService.getEntiyByPK(bioDataId);
                bioAddresses = bioAddressService.getAllDataByBioDataId(selectedBioData.getId());
                dataBioEmergencyContacs = bioEmergencyContactService.getAllDataWithDetailByBioDataId(selectedBioData.getId());
                
                bioDataModel.setBloodType(selectedBioData.getBloodType());
                bioDataModel.setCityid(selectedBioData.getCity().getId());
                bioDataModel.setDateOfBirth(selectedBioData.getDateOfBirth());
                bioDataModel.setDoialekId(selectedBioData.getDialect().getId());
                bioDataModel.setFirstName(selectedBioData.getFirstName());
                bioDataModel.setGender(selectedBioData.getGender());
                bioDataModel.setId(selectedBioData.getId());
                bioDataModel.setJamsostek(selectedBioData.getJamsostek());
                bioDataModel.setLastName(selectedBioData.getLastName());
                bioDataModel.setMaritalStatusId(selectedBioData.getMaritalStatus().getId());
                bioDataModel.setMobilePhone(selectedBioData.getMobilePhone());
                bioDataModel.setNationalitiId(selectedBioData.getNationality().getId());
                bioDataModel.setNickname(selectedBioData.getNickname());
                bioDataModel.setNoKK(selectedBioData.getNoKK());
                bioDataModel.setNpwp(selectedBioData.getNpwp());
                bioDataModel.setPathFinger(selectedBioData.getPathFinger());
                bioDataModel.setPathFoto(selectedBioData.getPathFoto());
                bioDataModel.setPathSignature(selectedBioData.getPathSignature());
                bioDataModel.setPersonalEmail(selectedBioData.getPersonalEmail());
                bioDataModel.setRaceId(selectedBioData.getRace().getId());
                bioDataModel.setReligionId(selectedBioData.getReligion().getId());
                bioDataModel.setTitle(selectedBioData.getTitle());
                String fotoPath = StringUtils.reverse(bioDataModel.getPathFoto());
                String nameReverse = StringUtils.substringBefore(fotoPath, "/");
                fotoFileName = StringUtils.reverse(nameReverse);
                String fingetPath = StringUtils.reverse(bioDataModel.getPathFinger());
                String nameReverse1 = StringUtils.substringBefore(fingetPath, "/");
                fingerFileName = StringUtils.reverse(nameReverse1);
                String signaturPath = StringUtils.reverse(bioDataModel.getPathSignature());
                String nameReverse2 = StringUtils.substringBefore(signaturPath, "/");
                signatureFileName = StringUtils.reverse(nameReverse2);

            } 

            List<City> dataCitys = cityService.getAllData();
            for (City city : dataCitys) {
                tempatlahir.put(city.getCityName(), city.getId());
            }

            List<Religion> religions = religionService.getAllData();
            for (Religion religion : religions) {
                mapReligions.put(religion.getName(), religion.getId());
            }

            List<Race> races = raceService.getAllData();
            for (Race race : races) {
                mapRas.put(race.getRaceName(), race.getId());
            }
            List<Dialect> dialects = dialectService.getAllData();
            for (Dialect dialect : dialects) {
                mapDialek.put(dialect.getDialectName(), dialect.getId());
            }
            List<MaritalStatus> maritalStatuses = maritalStatusService.getAllData();
            for (MaritalStatus maritalStatus : maritalStatuses) {
                mapMarital.put(maritalStatus.getName(), maritalStatus.getId());
            }
            List<Nationality> nationalitys = nationalityService.getAllData();
            for (Nationality nationality : nationalitys) {
                mapNationality.put(nationality.getNationalityName(), nationality.getId());
            }
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }

    }

    @PreDestroy
    public void cleanAndExit() {
        bioDataModel = null;
        tempatlahir = null;
        mapReligions = null;
        mapRas = null;
        mapDialek = null;
        mapMarital = null;
        mapNationality = null;
        cityService = null;
        religionService = null;
        raceService = null;
        maritalStatusService = null;
        nationalityService = null;
        familyRelationService = null;
        bioAddressService = null;
        bioEmergencyContactService = null;
        facesIO = null;
        bioDataService = null;
        fotoFile = null;
        fotoFileName = null;
        fingerFile = null;
        fingerFileName = null;
        signatureFile = null;
        signatureFileName = null;

    }
    
    /**
     * START Bio Address method
     */
    public void doUpdateBioAddressMap() {
        Map<String, Object> options = new HashMap<>();
        options.put("modal", true);
        options.put("draggable", true);
        options.put("resizable", false);
        options.put("contentWidth", 800);
        options.put("contentHeight", 500);

        Map<String, List<String>> params = new HashMap<>();
        params.put("bioAddressId", Arrays.asList(String.valueOf(selectedBioAddress.getId())));

        RequestContext.getCurrentInstance().openDialog("bio_address_map", options, params);
    }

    public void doSelectBioAddress(BioAddress bioAddress) {
        try {
        	selectedBioAddress = bioAddress;
        } catch (Exception e) {
            LOGGER.error("Error", e);
        }
    }

    public void doUpdateBioAddress() {
        Map<String, List<String>> dataToSend = new HashMap<>();
        dataToSend.put("bioAddressId", Arrays.asList(String.valueOf(selectedBioAddress.getId())));
        dataToSend.put("bioDataId", Arrays.asList(String.valueOf(selectedBioData.getId())));
        dataToSend.put("isRevision", Arrays.asList("isRevision"));    
        dataToSend.put("isEditOnRevision", Arrays.asList("Yes"));    
        
        //Set Object selectedBioAddress into SessionMap
        Map<String, Object> sessionMap = FacesUtil.getExternalContext().getSessionMap();
        sessionMap.put("selectedBioAddress", selectedBioAddress);
        showDialogBioAddress(dataToSend);
    }

    public void doAddBioAddress() {
        Map<String, List<String>> dataToSend = new HashMap<>();
        dataToSend.put("bioDataId", Arrays.asList(String.valueOf(selectedBioData.getId())));
        dataToSend.put("isRevision", Arrays.asList("isRevision"));   
        dataToSend.put("isEditOnRevision", Arrays.asList("No"));    
        showDialogBioAddress(dataToSend);
    }

    public void doDeleteBioAddress() {
        try {
        	bioAddresses.remove(selectedBioAddress);
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_INFO, "global.delete", "global.delete_successfully", FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());

        } catch (ConstraintViolationException | DataIntegrityViolationException ex) {
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_ERROR, "global.error", "error.delete_constraint", FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
        } catch (Exception ex) {
            LOGGER.error("Error when doDelete bioAddress", ex);
        }
    }

    private void showDialogBioAddress(Map<String, List<String>> params) {
        Map<String, Object> options = new HashMap<>();
        options.put("modal", true);
        options.put("draggable", true);
        options.put("resizable", false);
        options.put("contentWidth", 930);
        options.put("contentHeight", 400);
        RequestContext.getCurrentInstance().openDialog("bio_address_form", options, params);
    }

    public void onDialogReturnBioAddress(SelectEvent event) {
        try {
        	
        	BioAddress bioAddress = (BioAddress) event.getObject();
        	if(ObjectUtils.notEqual(bioAddress, null)){
        		
        		City city = cityService.getCityByIdWithDetail(bioAddress.getCity().getId());
        		bioAddress.setCity(city);
        		
        		//Jika Id masih kosong maka itu berarti tambah baru
        		//karena Id BioAddress tipe nya primitive, sehingga jika tidak di set, nilainya bukan null tapi 0
        		// http://www.java2s.com/Tutorial/SCJP/0020__Java-Source-And-Data-Type/AutomaticInitializationDefaultValuesforPrimitiveTypes.htm
        		if(bioAddress.getId() == 0){
        			bioAddress.setId(Long.parseLong(RandomNumberUtil.getRandomNumber(9)));
        			bioAddresses.add(bioAddress);
                    
        		}else{//Jika tidak kosong berarti edit data yang sudah ada
        			Map<String, Object> sessionMap = FacesUtil.getExternalContext().getSessionMap();
        			sessionMap.remove("selectedBioAddress");
        			//Replace element dengan return value dari form dialog        			
        			BioAddress bioAddressOld = Lambda.selectFirst(bioAddresses, Lambda.having(Lambda.on(BioAddress.class).getId(), Matchers.equalTo(bioAddress.getId())));
        			int index = bioAddresses.indexOf(bioAddressOld);
        			
        			if(-1 != index){
        				bioAddresses.remove(index);
        				bioAddresses.add(index, bioAddress);
        			}
        			
        		}
        		
        	}
            
            
        } catch (Exception e) {
            LOGGER.error("Error", e);
        }
    }

    /**
     * END Bio Address method
     */
    
    /**
     * START Bio Contact method
     */
    public void doSelectBioContact(BioEmergencyContact contact) {
        try {
            selectedBioEmergencyContact = contact;
        } catch (Exception e) {
            LOGGER.error("Error", e);
        }
    }
    
    public void doDeleteBioContact() {
        try {
            dataBioEmergencyContacs.remove(selectedBioEmergencyContact);
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_INFO, "global.delete", "global.delete_successfully", FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());

        } catch (ConstraintViolationException | DataIntegrityViolationException ex) {
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_ERROR, "global.error", "error.delete_constraint", FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
            LOGGER.error("Error", ex);
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
    }
    
    public void doAddEmergencyContact() {
        Map<String, List<String>> dataToSend = new HashMap<>();
        dataToSend.put("param", Arrays.asList("i" + String.valueOf(selectedBioData.getId())));
        dataToSend.put("isRevision", Arrays.asList("isRevision"));   
        dataToSend.put("isEditOnRevision", Arrays.asList("No"));    
        showDialogBioContact(dataToSend);
    }
    
    public void doUpdateBioContact() {
        Map<String, List<String>> dataToSend = new HashMap<>();
        dataToSend.put("param",  Arrays.asList("e" + String.valueOf(selectedBioData.getId())));
        dataToSend.put("isRevision", Arrays.asList("isRevision"));   
        dataToSend.put("isEditOnRevision", Arrays.asList("Yes"));  
        
        //Set Object selectedBioAddress into SessionMap
        Map<String, Object> sessionMap = FacesUtil.getExternalContext().getSessionMap();
        sessionMap.put("selectedBioContact", selectedBioEmergencyContact);
        showDialogBioAddress(dataToSend);
        showDialogBioContact(dataToSend);
    }
    
    private void showDialogBioContact(Map<String, List<String>> params) {
    	 Map<String, Object> options = new HashMap<>();
         options.put("modal", true);
         options.put("draggable", true);
         options.put("resizable", false);
         options.put("contentWidth", 700);
         options.put("contentHeight", 450);
         RequestContext.getCurrentInstance().openDialog("bio_emerg_cont_form", options, params);
    }

    public void onDialogReturnContact(SelectEvent event) {
       
    	try {
        	
        	BioEmergencyContact bioEmergencyContact = (BioEmergencyContact) event.getObject();
        	if(ObjectUtils.notEqual(bioEmergencyContact, null)){
        		
        		City city = cityService.getCityByIdWithDetail(bioEmergencyContact.getCity().getId());
        		System.out.println("familyRelationService equal null ?? : " + (familyRelationService == null));
        		System.out.println("bioEmergencyContact.getFamilyRelation() equal null ?? : " + (bioEmergencyContact.getFamilyRelation() == null));
        		FamilyRelation familyRelation = familyRelationService.getEntiyByPK(bioEmergencyContact.getFamilyRelation().getId());
        		
        		bioEmergencyContact.setCity(city);
        		bioEmergencyContact.setFamilyRelation(familyRelation);
        		
        		//Jika Id masih kosong maka itu berarti tambah baru
        		//karena Id BioEmergencyContact tipe nya primitive, sehingga jika tidak di set, nilainya bukan null tapi 0
        		// http://www.java2s.com/Tutorial/SCJP/0020__Java-Source-And-Data-Type/AutomaticInitializationDefaultValuesforPrimitiveTypes.htm
        		if(bioEmergencyContact.getId() == 0){
        			bioEmergencyContact.setId(Long.parseLong(RandomNumberUtil.getRandomNumber(9)));
        			dataBioEmergencyContacs.add(bioEmergencyContact);
                    
        		}else{//Jika tidak kosong berarti edit data yang sudah ada
        			Map<String, Object> sessionMap = FacesUtil.getExternalContext().getSessionMap();
        			sessionMap.remove("selectedBioContact");
        			//Replace element dengan return value dari form dialog        			
        			BioEmergencyContact bioEmergencyContactOld = Lambda.selectFirst(dataBioEmergencyContacs, Lambda.having(Lambda.on(BioEmergencyContact.class).getId(), Matchers.equalTo(bioEmergencyContact.getId())));
        			int index = dataBioEmergencyContacs.indexOf(bioEmergencyContactOld);
        			
        			if(-1 != index){
        				dataBioEmergencyContacs.remove(index);
        				dataBioEmergencyContacs.add(index, bioEmergencyContact);
        			}
        			
        		}
        		
        	}
            
            
        } catch (Exception e) {
            LOGGER.error("Error", e);
        }
    }

    /**
     * END Bio Contact method
     */

    public BioDataModel getBioDataModel() {
        return bioDataModel;
    }

    public void setBioDataModel(BioDataModel bioDataModel) {
        this.bioDataModel = bioDataModel;
    }

    public Map<String, Long> getTempatlahir() {
        return tempatlahir;
    }

    public void setTempatlahir(Map<String, Long> tempatlahir) {
        this.tempatlahir = tempatlahir;
    }

    public void setCityService(CityService cityService) {
        this.cityService = cityService;
    }

    public Map<String, Long> getMapReligions() {
        return mapReligions;
    }

    public void setMapReligions(Map<String, Long> mapReligions) {
        this.mapReligions = mapReligions;
    }

    public void setReligionService(ReligionService religionService) {
        this.religionService = religionService;
    }

    public Map<String, Long> getMapRas() {
        return mapRas;
    }

    public void setMapRas(Map<String, Long> mapRas) {
        this.mapRas = mapRas;
    }

    public void setRaceService(RaceService raceService) {
        this.raceService = raceService;
    }

    public void setDialectService(DialectService dialectService) {
        this.dialectService = dialectService;
    }

    public Map<String, Long> getMapDialek() {
        return mapDialek;
    }

    public void setMapDialek(Map<String, Long> mapDialek) {
        this.mapDialek = mapDialek;
    }

    public void setMaritalStatusService(MaritalStatusService maritalStatusService) {
        this.maritalStatusService = maritalStatusService;
    }

    public Map<String, Long> getMapMarital() {
        return mapMarital;
    }

    public void setMapMarital(Map<String, Long> mapMarital) {
        this.mapMarital = mapMarital;
    }

    public void setNationalityService(NationalityService nationalityService) {
        this.nationalityService = nationalityService;
    }

    public Map<String, Long> getMapNationality() {
        return mapNationality;
    }

    public void setMapNationality(Map<String, Long> mapNationality) {
        this.mapNationality = mapNationality;
    }

   
    public String doApply() {

        if (isValidForm()) {
            String path = StringUtils.EMPTY;
            String result = StringUtils.EMPTY;
            try{
            	 switch (selectedJenisData) {
                 
     			case HRMConstant.BIO_REV_DETAIL_BIO_DATA:
     				 BioData bioData = getEntityFromView(bioDataModel);
     				 result = bioDataService.saveBiodataRevisionWithApproval(bioData, selectedJenisData, empData);
     				 
	     				if (fotoFile != null) {
	                        facesIO.transferFile(fotoFile);
	                        File fotoOldFile = new File(facesIO.getPathUpload() + fotoFileName);
	                        fotoOldFile.renameTo(new File(bioData.getPathFoto()));
	                    }
	
	                    if (fingerFile != null) {
	                        facesIO.transferFile(fingerFile);
	                        File fingerOldFile = new File(facesIO.getPathUpload() + fingerFileName);
	                        fingerOldFile.renameTo(new File(bioData.getPathFinger()));
	                    }
	
	                    if (signatureFile != null) {
	                        facesIO.transferFile(signatureFile);
	                        File sigtarueOldFile = new File(facesIO.getPathUpload() + signatureFileName);
	                        sigtarueOldFile.renameTo(new File(bioData.getPathSignature()));
	                    }
     				break;
     				
     			case HRMConstant.BIO_REV_ADDRESS:
     				 result = bioDataService.saveBiodataRevisionWithApproval(bioAddresses, selectedJenisData, empData);
     				break;
     				
     			case HRMConstant.BIO_REV_CONTACT:
    				 result = bioDataService.saveBiodataRevisionWithApproval(dataBioEmergencyContacs, selectedJenisData, empData);
    				break;
    				
     			default:
     				break;
     			}
            	 
            	 if (StringUtils.equals(result, "success_need_approval")) {
                     path = "/protected/personalia/loan_application_form.htm?faces-redirect=true";
                     MessagesResourceUtil.setMessagesFlas(FacesMessage.SEVERITY_INFO, "global.save_info", "global.added_successfully_and_requires_approval", FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());

                 } else {
                     path = "/protected/personalia/loan_application_form.htm?faces-redirect=true";
                     MessagesResourceUtil.setMessagesFlas(FacesMessage.SEVERITY_INFO, "global.save_info", "global.added_successfully", FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
                 }
            	 
            	 cleanAndExit();
            	 return path;
            } catch (BussinessException ex) {
                MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_ERROR, "global.error", ex.getErrorKeyMessage(), FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
            }catch(Exception ex){
            	LOGGER.error("Error", ex);
            }
           
        }
        return null;
    }
    
    private Boolean isValidForm() {
        try {
        	return Boolean.TRUE;
        }catch(Exception e){
        	
        }
        return Boolean.TRUE;
    }

    public void handingFotoUpload(FileUploadEvent fileUploadEvent) {
        fotoFile = fileUploadEvent.getFile();
        fotoFileName = fotoFile.getFileName();
    }

    public void handingFingerUpload(FileUploadEvent fileUploadEvent) {
        fingerFile = fileUploadEvent.getFile();
        fingerFileName = fingerFile.getFileName();
    }

    public void handingSignatureUpload(FileUploadEvent fileUploadEvent) {
        signatureFile = fileUploadEvent.getFile();
        signatureFileName = signatureFile.getFileName();
    }

    public UploadedFile getFotoFile() {
        return fotoFile;
    }

    public void setFotoFile(UploadedFile fotoFile) {
        this.fotoFile = fotoFile;
    }

    public String getFotoFileName() {
        return fotoFileName;
    }

    public void setFotoFileName(String fotoFileName) {
        this.fotoFileName = fotoFileName;
    }

    public UploadedFile getFingerFile() {
        return fingerFile;
    }

    public void setFingerFile(UploadedFile fingerFile) {
        this.fingerFile = fingerFile;
    }

    public String getFingerFileName() {
        return fingerFileName;
    }

    public void setFingerFileName(String fingerFileName) {
        this.fingerFileName = fingerFileName;
    }

    public UploadedFile getSignatureFile() {
        return signatureFile;
    }

    public void setSignatureFile(UploadedFile signatureFile) {
        this.signatureFile = signatureFile;
    }

    public String getSignatureFileName() {
        return signatureFileName;
    }

    public void setSignatureFileName(String signatureFileName) {
        this.signatureFileName = signatureFileName;
    }

    public void doReset() {
        fotoFile = null;
        fotoFileName = null;
        fingerFile = null;
        fingerFileName = null;
        signatureFile = null;
        signatureFileName = null;
        dialectService = null;

    }

    public void setFacesIO(FacesIO facesIO) {
        this.facesIO = facesIO;
    }

    public BioData getEntityFromView(BioDataModel bioDataModel) {
        BioData bioData = new BioData();
        if (bioDataModel.getId() != null) {
            bioData.setId(bioDataModel.getId());
        } else {
            bioData.setId(Long.parseLong(RandomNumberUtil.getRandomNumber(12)));
        }
        bioData.setBloodType(bioDataModel.getBloodType());
        bioData.setCity(new City(bioDataModel.getCityid()));
        bioData.setDateOfBirth(bioDataModel.getDateOfBirth());
        bioData.setDialect(new Dialect(bioDataModel.getDoialekId()));
        bioData.setFirstName(bioDataModel.getFirstName());
        bioData.setGender(bioDataModel.getGender());
        bioData.setJamsostek(bioDataModel.getJamsostek());
        bioData.setLastName(bioDataModel.getLastName());
        bioData.setMaritalStatus(new MaritalStatus(bioDataModel.getMaritalStatusId()));
        bioData.setMobilePhone(bioDataModel.getMobilePhone());
        bioData.setNationality(new Nationality(bioDataModel.getNationalitiId()));
        bioData.setNickname(bioDataModel.getNickname());
        bioData.setNoKK(bioDataModel.getNoKK());
        bioData.setNpwp(bioDataModel.getNpwp());
        if (fotoFile != null) {
            bioData.setPathFoto(facesIO.getPathUpload() + bioData.getId() + "_" + fotoFileName);
        }

        if (fingerFile != null) {
            bioData.setPathFinger(facesIO.getPathUpload() + bioData.getId() + "_" + fingerFileName);
        }
        if (signatureFile != null) {
            bioData.setPathSignature(facesIO.getPathUpload() + bioData.getId() + "_" + signatureFileName);
        }

        bioData.setPersonalEmail(bioDataModel.getPersonalEmail());
        bioData.setRace(new Race(bioDataModel.getRaceId()));
        bioData.setReligion(new Religion(bioDataModel.getReligionId()));
        bioData.setTitle(bioDataModel.getTitle());
        return bioData;
    }

    public void setBioDataService(BioDataService bioDataService) {
        this.bioDataService = bioDataService;
    }

    public String doBack() {
        return "/protected/personalia/biodata_view.htm?faces-redirect=true";
    }

    public String getSelectedJenisData() {
        return selectedJenisData;
    }

    public void setSelectedJenisData(String selectedJenisData) {
        this.selectedJenisData = selectedJenisData;
    }

	public EmpData getEmpData() {
		return empData;
	}

	public void setEmpData(EmpData empData) {
		this.empData = empData;
	}

	public void setEmpDataService(EmpDataService empDataService) {
		this.empDataService = empDataService;
	}

	public List<BioAddress> getBioAddresses() {
		return bioAddresses;
	}

	public void setBioAddresses(List<BioAddress> bioAddresses) {
		this.bioAddresses = bioAddresses;
	}

	public BioAddress getSelectedBioAddress() {
		return selectedBioAddress;
	}

	public void setSelectedBioAddress(BioAddress selectedBioAddress) {
		this.selectedBioAddress = selectedBioAddress;
	}

	public void setBioAddressService(BioAddressService bioAddressService) {
		this.bioAddressService = bioAddressService;
	}

	public List<BioEmergencyContact> getDataBioEmergencyContacs() {
		return dataBioEmergencyContacs;
	}

	public void setDataBioEmergencyContacs(
			List<BioEmergencyContact> dataBioEmergencyContacs) {
		this.dataBioEmergencyContacs = dataBioEmergencyContacs;
	}

	public BioEmergencyContact getSelectedBioEmergencyContact() {
		return selectedBioEmergencyContact;
	}

	public void setSelectedBioEmergencyContact(
			BioEmergencyContact selectedBioEmergencyContact) {
		this.selectedBioEmergencyContact = selectedBioEmergencyContact;
	}

	public void setBioEmergencyContactService(
			BioEmergencyContactService bioEmergencyContactService) {
		this.bioEmergencyContactService = bioEmergencyContactService;
	}

	public void setFamilyRelationService(FamilyRelationService familyRelationService) {
		this.familyRelationService = familyRelationService;
	}
    
    

}