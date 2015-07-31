/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.service.impl;

import ch.lambdaj.Lambda;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.inkubator.common.util.RandomNumberUtil;
import com.inkubator.datacore.service.impl.IServiceImpl;
import com.inkubator.exception.BussinessException;
import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.dao.ApprovalActivityDao;
import com.inkubator.hrm.dao.ApprovalDefinitionDao;
import com.inkubator.hrm.dao.BioAddressDao;
import com.inkubator.hrm.dao.BioDataDao;
import com.inkubator.hrm.dao.BioDocumentDao;
import com.inkubator.hrm.dao.BioEducationHistoryDao;
import com.inkubator.hrm.dao.BioEmergencyContactDao;
import com.inkubator.hrm.dao.BioFamilyRelationshipDao;
import com.inkubator.hrm.dao.BioIdCardDao;
import com.inkubator.hrm.dao.BioKeahlianDao;
import com.inkubator.hrm.dao.BioPeopleInterestDao;
import com.inkubator.hrm.dao.BioRelasiPerusahaanDao;
import com.inkubator.hrm.dao.BioSpesifikasiAbilityDao;
import com.inkubator.hrm.dao.CityDao;
import com.inkubator.hrm.dao.DialectDao;
import com.inkubator.hrm.dao.EducationLevelDao;
import com.inkubator.hrm.dao.EmpDataDao;
import com.inkubator.hrm.dao.FacultyDao;
import com.inkubator.hrm.dao.FamilyRelationDao;
import com.inkubator.hrm.dao.HrmUserDao;
import com.inkubator.hrm.dao.InstitutionEducationDao;
import com.inkubator.hrm.dao.MajorDao;
import com.inkubator.hrm.dao.MaritalStatusDao;
import com.inkubator.hrm.dao.NationalityDao;
import com.inkubator.hrm.dao.RaceDao;
import com.inkubator.hrm.dao.ReligionDao;
import com.inkubator.hrm.entity.ApprovalActivity;
import com.inkubator.hrm.entity.ApprovalDefinition;
import com.inkubator.hrm.entity.ApprovalDefinitionLoan;
import com.inkubator.hrm.entity.BioAddress;
import com.inkubator.hrm.entity.BioData;
import com.inkubator.hrm.entity.BioDocument;
import com.inkubator.hrm.entity.BioEducationHistory;
import com.inkubator.hrm.entity.BioEmergencyContact;
import com.inkubator.hrm.entity.BioFamilyRelationship;
import com.inkubator.hrm.entity.BioIdCard;
import com.inkubator.hrm.entity.BioKeahlian;
import com.inkubator.hrm.entity.BioRelasiPerusahaan;
import com.inkubator.hrm.entity.BioSpesifikasiAbility;
import com.inkubator.hrm.entity.BioSpesifikasiAbilityId;
import com.inkubator.hrm.entity.City;
import com.inkubator.hrm.entity.EducationLevel;
import com.inkubator.hrm.entity.EmpData;
import com.inkubator.hrm.entity.Faculty;
import com.inkubator.hrm.entity.FamilyRelation;
import com.inkubator.hrm.entity.HrmUser;
import com.inkubator.hrm.entity.InstitutionEducation;
import com.inkubator.hrm.entity.LoanNewApplication;
import com.inkubator.hrm.entity.LoanNewSchema;
import com.inkubator.hrm.entity.LoanNewType;
import com.inkubator.hrm.entity.Major;
import com.inkubator.hrm.entity.RmbsApplication;
import com.inkubator.hrm.json.util.JsonUtil;
import com.inkubator.hrm.service.BioDataService;
import com.inkubator.hrm.util.CommonReportUtil;
import com.inkubator.hrm.web.model.BioEducationHistoryModel;
import com.inkubator.hrm.web.search.BioDataSearchParameter;
import com.inkubator.securitycore.util.UserInfoUtil;
import com.inkubator.webcore.util.FacesIO;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.context.FacesContext;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.hamcrest.Matchers;
import org.hibernate.criterion.Order;
import org.primefaces.model.StreamedContent;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Deni Husni FR
 */
@Service(value = "bioDataService")
@Lazy
public class BioDataServiceImpl extends BaseApprovalServiceImpl implements BioDataService {

    @Autowired
    private BioDataDao bioDataDao;
    @Autowired
    private CityDao cityDao;
    @Autowired
    private MaritalStatusDao maritalStatusDao;
    @Autowired
    private DialectDao dialectDao;
    @Autowired
    private NationalityDao nationalityDao;
    @Autowired
    private RaceDao raceDao;
    @Autowired
    private ReligionDao religionDao;
    @Autowired
    private FacesIO facesIO;
    @Autowired
    private BioDocumentDao bioDocumentDao;
    @Autowired
    private EmpDataDao empDataDao;
    @Autowired
    private ApprovalDefinitionDao approvalDefinitionDao;
    @Autowired
    private HrmUserDao hrmUserDao;
    @Autowired
    private ApprovalActivityDao approvalActivityDao;
    @Autowired
    private BioAddressDao bioAddressDao;
    @Autowired
    private BioEmergencyContactDao bioEmergencyContactDao;
    @Autowired
    private FamilyRelationDao familyRelationDao;
    @Autowired
    private BioIdCardDao bioIdCardDao;
    @Autowired
    private BioFamilyRelationshipDao bioFamilyRelationshipDao;
    @Autowired
    private BioRelasiPerusahaanDao bioRelasiPerusahaanDao;
    @Autowired
    private BioEducationHistoryDao bioEducationHistoryDao;
    @Autowired
    private BioKeahlianDao bioKeahlianDao;
    @Autowired
    private BioSpesifikasiAbilityDao bioSpesifikasiAbilityDao;
    @Autowired
    private BioPeopleInterestDao bioPeopleInterestDao;
    @Autowired
    private EducationLevelDao educationLevelDao;
    @Autowired
    private InstitutionEducationDao institutionEducationDao;
    @Autowired
    private FacultyDao facultyDao;
    @Autowired
    private MajorDao majorDao;

    @Override
    public BioData getEntiyByPK(String id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public BioData getEntiyByPK(Integer id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED, propagation = Propagation.SUPPORTS, timeout = 30)
    public BioData getEntiyByPK(Long id) throws Exception {
        BioData bioData = bioDataDao.getEntiyByPK(id);
        /*bioData.getCity().getCityName();
        bioData.getReligion().getName();
        bioData.getMaritalStatus().getName();
        bioData.getNationality().getNationalityName();
        bioData.getRace().getRaceName();
        bioData.getDialect().getDialectName();*/
        return bioData;
    }

    @Override
    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void save(BioData entity) throws Exception {

        entity.setCreatedBy(UserInfoUtil.getUserName());
        entity.setCreatedOn(new Date());
        entity.setCity(this.cityDao.getEntiyByPK(entity.getCity().getId()));
        entity.setDialect(this.dialectDao.getEntiyByPK(entity.getDialect().getId()));
        entity.setMaritalStatus(this.maritalStatusDao.getEntiyByPK(entity.getMaritalStatus().getId()));
        entity.setNationality(nationalityDao.getEntiyByPK(entity.getNationality().getId()));
        entity.setRace(this.raceDao.getEntiyByPK(entity.getRace().getId()));
        entity.setReligion(this.religionDao.getEntiyByPK(entity.getReligion().getId()));
        this.bioDataDao.save(entity);
    }

    @Override
    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void update(BioData entity) throws Exception {
        BioData bioData = this.bioDataDao.getEntiyByPK(entity.getId());
        bioData.setBloodType(entity.getBloodType());
        bioData.setCity(this.cityDao.getEntiyByPK(entity.getCity().getId()));
        bioData.setDateOfBirth(entity.getDateOfBirth());
        bioData.setDialect(this.dialectDao.getEntiyByPK(entity.getDialect().getId()));
        bioData.setFirstName(entity.getFirstName());
        bioData.setGender(entity.getGender());
        bioData.setJamsostek(entity.getJamsostek());
        bioData.setLastName(entity.getLastName());
        bioData.setMaritalStatus(this.maritalStatusDao.getEntiyByPK(entity.getMaritalStatus().getId()));
        bioData.setMobilePhone(entity.getMobilePhone());
        bioData.setNationality(nationalityDao.getEntiyByPK(entity.getNationality().getId()));
        bioData.setNickname(entity.getNickname());
        bioData.setNoKK(entity.getNoKK());
        bioData.setNpwp(entity.getNpwp());
        if (entity.getPathFinger() != null) {
            bioData.setPathFinger(entity.getPathFinger());
        }
        if (entity.getPathFoto() != null) {
            bioData.setPathFoto(entity.getPathFoto());
        }
        if (entity.getPathSignature() != null) {
            bioData.setPathSignature(entity.getPathSignature());
        }
        bioData.setPersonalEmail(entity.getPersonalEmail());
        bioData.setRace(this.raceDao.getEntiyByPK(entity.getRace().getId()));
        bioData.setReligion(this.religionDao.getEntiyByPK(entity.getReligion().getId()));
        bioData.setTitle(entity.getTitle());
        bioData.setUpdatedBy(UserInfoUtil.getUserName());
        bioData.setUpdatedOn(new Date());
        this.bioDataDao.update(bioData);
    }

    @Override
    public void saveOrUpdate(BioData enntity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public BioData saveData(BioData entity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public BioData updateData(BioData entity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public BioData saveOrUpdateData(BioData entity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public BioData getEntityByPkIsActive(String id, Integer isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public BioData getEntityByPkIsActive(String id, Byte isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public BioData getEntityByPkIsActive(String id, Boolean isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public BioData getEntityByPkIsActive(Integer id, Integer isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public BioData getEntityByPkIsActive(Integer id, Byte isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public BioData getEntityByPkIsActive(Integer id, Boolean isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public BioData getEntityByPkIsActive(Long id, Integer isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public BioData getEntityByPkIsActive(Long id, Byte isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public BioData getEntityByPkIsActive(Long id, Boolean isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = DataIntegrityViolationException.class, noRollbackFor = IOException.class)
    public void delete(BioData entity) throws Exception {
        this.bioDataDao.delete(entity);
//        if (entity.getPathFoto() != null || entity.getPathFoto().isEmpty()) {

//        }
    }

    @Override
    public void softDelete(BioData entity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Long getTotalData() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Long getTotalDataIsActive(Boolean isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Long getTotalDataIsActive(Integer isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Long getTotalDataIsActive(Byte isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 30)
    public List<BioData> getAllData() throws Exception {
        return bioDataDao.getAllData();
    }

    @Override
    public List<BioData> getAllData(Boolean isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<BioData> getAllData(Integer isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<BioData> getAllData(Byte isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<BioData> getAllDataPageAble(int firstResult, int maxResults, Order order) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<BioData> getAllDataPageAbleIsActive(int firstResult, int maxResults, Order order, Boolean isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<BioData> getAllDataPageAbleIsActive(int firstResult, int maxResults, Order order, Integer isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<BioData> getAllDataPageAbleIsActive(int firstResult, int maxResults, Order order, Byte isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 50)
    public List<BioData> getByParam(BioDataSearchParameter parameter, int firstResult, int maxResults, Order orderable) throws Exception {
        return this.bioDataDao.getByParam(parameter, firstResult, maxResults, orderable);
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED, propagation = Propagation.SUPPORTS, timeout = 30)
    public Long getTotalByParam(BioDataSearchParameter parameter) throws Exception {
        return this.bioDataDao.getTotalByParam(parameter);
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 30)
    public BioData getEntityByPKWithDetail(long id) throws Exception {
        return bioDataDao.getEntityByPKWithDetail(id);
    }

    @Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 50)
    @Override
    public List<BioData> getByName(String name) throws Exception {
        return this.bioDataDao.getByName(name);
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 50)
    public StreamedContent generateCV(long id) throws Exception {
        /*GsonBuilder gsonBuilder = new GsonBuilder();
         gsonBuilder.setDateFormat("dd MMMM yyyy");
         gsonBuilder.registerTypeAdapterFactory(HibernateProxyTypeAdapter.FACTORY);
         gsonBuilder.setExclusionStrategies(new EntityExclusionStrategy());
         Gson gson = gsonBuilder.create();
         JsonParser parser = new JsonParser();		
         JsonObject json = new JsonObject();
		
         BioData bioData = bioDataDao.getEntiyByPK(id);
         boolean isBioDataExist = bioData != null;		
         json.addProperty("isBioDataExist", isBioDataExist);
         if(isBioDataExist){
         json.add("bioData", parser.parse(gson.toJson(bioData)));
         }*/

        BioData bioData = bioDataDao.getEntiyByPK(id);
        List<BioDocument> bioDocuments = bioDocumentDao.getAllDataByBioDataId(id);
        List<String> attachments = new ArrayList<String>();
        for (BioDocument document : bioDocuments) {
            if (StringUtils.isNotEmpty(document.getUploadPath())) {
                attachments.add(document.getUploadPath());
            }
        }
        Map<String, Object> params = new HashMap<>();
        params.put("BIODATA_ID", id);
        params.put("IS_RENDER_ADDRESS", !bioData.getBioAddresses().isEmpty());
        params.put("IS_RENDER_EDU_HISTORY", !bioData.getEducationHistories().isEmpty());
        params.put("IS_RENDER_ID_CARD", !bioData.getBioIdCards().isEmpty());
        params.put("IS_RENDER_EMP_HISTORY", !bioData.getBioEmploymentHistories().isEmpty());
        params.put("SUBREPORT_DIR", FacesContext.getCurrentInstance().getExternalContext().getRealPath("/resources/reports/"));
        StreamedContent file = CommonReportUtil.exportReportToPDFStreamWithAttachment("cv_builder.jasper", params, bioData.getFirstName() + ".pdf", attachments);
        return file;
    }

	@Override
	@Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void updatePhoto(String nik, MultipartFile multipart) throws Exception {
		EmpData empData = empDataDao.getEntityByNik(nik);
		BioData bioData = empData.getBioData();
		
		//delete old photo (if any)
		if(bioData.getPathFoto() != null) {
			File oldFile = new File(bioData.getPathFoto());            
			FileUtils.deleteQuietly(oldFile);
		}
        
        //save new photo to disk
		String pathPhoto = facesIO.getPathUpload() + bioData.getId() + "_" + multipart.getOriginalFilename();
		System.out.println(pathPhoto);
		File file = new File(pathPhoto);
		multipart.transferTo(file);		
		
		//save new path photo
		bioData.setPathFoto(pathPhoto);
		bioDataDao.update(bioData);
		
	}
	
	@Override
	@Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void updateSignature(String nik, MultipartFile signatureFile) throws Exception {
		EmpData empData = empDataDao.getEntityByNik(nik);
		BioData bioData = empData.getBioData();
		
		//delete old signature (if any)
		if(bioData.getPathSignature() != null) {
			File oldFile = new File(bioData.getPathSignature());            
			FileUtils.deleteQuietly(oldFile);
		}
        
        //save new signature to disk
		String pathSignature = facesIO.getPathUpload() + bioData.getId() + "_" + signatureFile.getOriginalFilename();
		File file = new File(pathSignature);
		signatureFile.transferTo(file);		
		
		//save new path signature
		bioData.setPathSignature(pathSignature);
		bioDataDao.update(bioData);
		
	}
    
    @Override
	@Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void updateFingerPrint(String nik, MultipartFile fingerPrintFile) throws Exception {
		EmpData empData = empDataDao.getEntityByNik(nik);
		BioData bioData = empData.getBioData();
		
		//delete old fingerPrint (if any)
		if(bioData.getPathFinger() != null) {
			File oldFile = new File(bioData.getPathFinger());            
			FileUtils.deleteQuietly(oldFile);
		}
        
        //save new fingerPrint to disk
		String pathFingerPrint = facesIO.getPathUpload() + bioData.getId() + "_" + fingerPrintFile.getOriginalFilename();
		File file = new File(pathFingerPrint);
		fingerPrintFile.transferTo(file);		
		
		//save new path fingerPrint
		bioData.setPathFinger(pathFingerPrint);
		bioDataDao.update(bioData);
		
	}

	@Override
	public void approved(long approvalActivityId, String pendingDataUpdate, String comment) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void rejected(long approvalActivityId, String comment) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void diverted(long approvalActivityId) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void sendingEmailApprovalNotif(ApprovalActivity appActivity)	throws Exception {
		// TODO Auto-generated method stub
		
	}
	
	private Boolean isApprovalDefinitionNotFound(){		
		Long total = approvalDefinitionDao.getTotalApprovalExistWithSequenceOne(HRMConstant.BIO_DATA_EDIT);
		return ObjectUtils.equals(total, null) ? Boolean.TRUE : total == 0 ? Boolean.TRUE : Boolean.FALSE;
	}

	@Override
	@Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public String saveBiodataRevisionWithApproval(Object modifiedEntity, String dataType, EmpData empData) throws Exception {
		
		//Jika Approval Definition untuk proses revisi biodata belum di buat, lempar BussinessException
		if(isApprovalDefinitionNotFound()){
			throw new BussinessException("biodata.biodata_revision_doesnt_have_approval_def");
			
		}
		
		HrmUser requestUser = hrmUserDao.getByEmpDataId(empData.getId());
		
		//Dapatkan List Pending Approval Activity
		List<ApprovalActivity> listPreviousActivities = approvalActivityDao.getPendingRequest(requestUser.getUserId());
		
		//Filter hanya yang berasal dari proses request revisi biodata
		listPreviousActivities = Lambda.select(listPreviousActivities, Lambda.having(Lambda.on(ApprovalActivity.class).getApprovalDefinition().getName(), Matchers.equalTo(HRMConstant.BIO_DATA_EDIT)));
		
		//Looping satu persatu
		for(ApprovalActivity approvalActivity : listPreviousActivities){
			String pendingData = approvalActivity.getPendingData();
			Gson gson = JsonUtil.getHibernateEntityGsonBuilder().create();		
			
			// dapatkan nilai dataType (jenis kelompok formulir biodata) dari pending approval activity
			JsonElement jsonElementDataType = gson.fromJson(pendingData, JsonObject.class).get("dataType");
		        if (!jsonElementDataType.isJsonNull()) {
		        	
		        	String dataTypeFromJson = jsonElementDataType.getAsString();
		        	
		        	//Jika kelompok formulir dari pending Request sama dengan yang akan di ajukan sekarang, maka lempar BusinessException,
		        	//karena tidak boleh mengajukan perubahan biodata pada satu jenis kelompok formulir, 
		        	//jika sebelumnya sudah pernah ada pengajuan revisi pada jenis kelompok formulir tsb dan statusnya masih pending.
		        	if(StringUtils.equals(dataType, dataTypeFromJson)){
		        		throw new BussinessException("biodata.error_revision_same_data_type_which_still_pending_found");
		        	}
		        	
		        }
	       
		}
		
		return this.saveRevision(modifiedEntity, dataType, empData, Boolean.FALSE, null);
		
	}
	
	@SuppressWarnings("unchecked")
	private String saveRevision(Object modifiedEntity, String dataType, EmpData empData, Boolean isBypassApprovalChecking,  Long revisedApprActivityId) throws Exception {
        String result = "error";
        
        //Check Next Approval
        ApprovalActivity approvalActivity = this.checkApprovalIfAny(empData, isBypassApprovalChecking);
        
        // jika null berarti tidak ada next approval, langsung simpan/update ke DB
        if (approvalActivity == null) {
        	
        	switch (dataType) {
        	
        		case HRMConstant.BIO_REV_DETAIL_BIO_DATA:
        			BioData modifiedBiodata = (BioData) modifiedEntity;
        			update(modifiedBiodata);
        			break;
        			
        		case HRMConstant.BIO_REV_ADDRESS:
        			List<BioAddress> modifiedListBioAddress = (List<BioAddress>) modifiedEntity;
        			saveOrUpdateListBioAddress(modifiedListBioAddress, empData.getBioData().getId());
        			break;
        			
        		case HRMConstant.BIO_REV_CONTACT:
        			List<BioEmergencyContact> modifiedListBioEmergencyContact = (List<BioEmergencyContact>) modifiedEntity;
        			saveOrUpdateBioEmergencyContact(modifiedListBioEmergencyContact, empData.getBioData().getId());
        			break;
        			
        		case HRMConstant.BIO_REV_ID_CARD:
        			List<BioIdCard> modifiedListBioIdCard = (List<BioIdCard>) modifiedEntity;
        			saveOrUpdateBioIdCard(modifiedListBioIdCard, empData.getBioData().getId());
        			break;
        			
        		case HRMConstant.BIO_REV_FAMILY:
        			List<BioFamilyRelationship> modifiedListBioFamilyRelationship = (List<BioFamilyRelationship>) modifiedEntity;
        			saveOrUpdateBioFamilyRelationship(modifiedListBioFamilyRelationship, empData.getBioData().getId());
        			break;
        			
        		case HRMConstant.BIO_REV_COMPANY_RELATION:
        			List<BioRelasiPerusahaan> modifiedListBioRelasiPerusahaan = (List<BioRelasiPerusahaan>) modifiedEntity;
        			saveOrUpdateBioRelasiPerusahaan(modifiedListBioRelasiPerusahaan, empData.getBioData().getId());
        			break;
        			
        		case HRMConstant.BIO_REV_EDUCATION:
        			List<BioEducationHistoryModel> modifiedListBioEducationHistoryModel = (List<BioEducationHistoryModel>) modifiedEntity;
        			saveOrUpdateBioEducationHistory(modifiedListBioEducationHistoryModel, empData.getBioData().getId());
        			break;
        			
        		case HRMConstant.BIO_REV_SKILL:
        			List<BioKeahlian> modifiedListBioKeahlian = (List<BioKeahlian>) modifiedEntity;
        			saveOrUpdateBioKeahlian(modifiedListBioKeahlian, empData.getBioData().getId());
        			break;
        			
        		case HRMConstant.BIO_REV_SPESIFICATION_ABILITY:
        			List<BioSpesifikasiAbility> modifiedListBioSpesifikasiAbility = (List<BioSpesifikasiAbility>) modifiedEntity;
        			saveOrUpdateBioSpesifikasiAbility(modifiedListBioSpesifikasiAbility, empData.getBioData().getId());
        			break;
        			
        		default:
    				break;
        	}
        	
            result = "success_without_approval";

        } else {
        	
        	switch (dataType) {
        	
				case HRMConstant.BIO_REV_DETAIL_BIO_DATA:
					BioData modifiedBiodata = (BioData) modifiedEntity;
					approvalActivity.setPendingData(getJsonPendingData(modifiedBiodata, dataType));
		            approvalActivity.setTypeSpecific(null);
		            approvalActivityDao.save(approvalActivity);
		            result = "success_need_approval";
					break;
				
				case HRMConstant.BIO_REV_ADDRESS:
					List<BioAddress> listModifiedAddress = (List<BioAddress>) modifiedEntity;
					approvalActivity.setPendingData(getJsonPendingData(listModifiedAddress, dataType));
		            approvalActivity.setTypeSpecific(null);
		            approvalActivityDao.save(approvalActivity);
		            result = "success_need_approval";
					break;
				
				case HRMConstant.BIO_REV_CONTACT:
					List<BioEmergencyContact> listModifiedBioContact = (List<BioEmergencyContact>) modifiedEntity;
					approvalActivity.setPendingData(getJsonPendingData(listModifiedBioContact, dataType));
		            approvalActivity.setTypeSpecific(null);
		            approvalActivityDao.save(approvalActivity);
		            result = "success_need_approval";
					break;
					
				case HRMConstant.BIO_REV_ID_CARD:
					List<BioIdCard> listModifiedBioIdCard = (List<BioIdCard>) modifiedEntity;
					approvalActivity.setPendingData(getJsonPendingData(listModifiedBioIdCard, dataType));
		            approvalActivity.setTypeSpecific(null);
		            approvalActivityDao.save(approvalActivity);
		            result = "success_need_approval";
					break;
					
				case HRMConstant.BIO_REV_FAMILY:
					List<BioFamilyRelationship> listModifiedBioFamilyRelationship = (List<BioFamilyRelationship>) modifiedEntity;
					approvalActivity.setPendingData(getJsonPendingData(listModifiedBioFamilyRelationship, dataType));
		            approvalActivity.setTypeSpecific(null);
		            approvalActivityDao.save(approvalActivity);
		            result = "success_need_approval";
					break;
					
				case HRMConstant.BIO_REV_COMPANY_RELATION:
					List<BioRelasiPerusahaan> listModifiedBioRelasiPerusahaan = (List<BioRelasiPerusahaan>) modifiedEntity;
					approvalActivity.setPendingData(getJsonPendingData(listModifiedBioRelasiPerusahaan, dataType));
		            approvalActivity.setTypeSpecific(null);
		            approvalActivityDao.save(approvalActivity);
		            result = "success_need_approval";
					break;
					
				case HRMConstant.BIO_REV_EDUCATION:
					List<BioEducationHistoryModel> listModifiedBioEducationHistoryModel = (List<BioEducationHistoryModel>) modifiedEntity;
					approvalActivity.setPendingData(getJsonPendingData(listModifiedBioEducationHistoryModel, dataType));
		            approvalActivity.setTypeSpecific(null);
		            approvalActivityDao.save(approvalActivity);
		            result = "success_need_approval";
					break;
					
				case HRMConstant.BIO_REV_SKILL:
					List<BioKeahlian> listModifiedBioKeahlian = (List<BioKeahlian>) modifiedEntity;
					approvalActivity.setPendingData(getJsonPendingData(listModifiedBioKeahlian, dataType));
		            approvalActivity.setTypeSpecific(null);
		            approvalActivityDao.save(approvalActivity);
		            result = "success_need_approval";
					break;
					
				default:
					break;
				
			}
            
            //sending email notification
            this.sendingEmailApprovalNotif(approvalActivity);
        }

        return result;
    }
	
	private ApprovalActivity checkApprovalIfAny(EmpData empData, Boolean isBypassApprovalChecking) throws Exception{
		/** check approval process if any,
		 *  return null if no need approval process */
		HrmUser requestUser = hrmUserDao.getByEmpDataId(empData.getId());
        List<ApprovalDefinition> appDefs = approvalDefinitionDao.getAllDataByName(HRMConstant.BIO_DATA_EDIT);
        return isBypassApprovalChecking ? null : super.checkApprovalProcess(appDefs, requestUser.getUserId());
	}
	
	 private String getJsonPendingData(Object entity, String dataType) throws IOException {
		 	
		 	Gson gson = JsonUtil.getHibernateEntityGsonBuilder().create();
	        JsonParser parser = new JsonParser();
	        JsonObject jsonObject = new JsonObject();
	        
		 	switch (dataType) {
			case HRMConstant.BIO_REV_DETAIL_BIO_DATA:
				JsonObject jsonObjectBioData = (JsonObject) parser.parse(gson.toJson(entity));
				jsonObject.add("modifiedEntity", jsonObjectBioData);
		        jsonObject.addProperty("dataType", dataType);
				break;
				
			case HRMConstant.BIO_REV_ADDRESS:				
				JsonArray jsonArrayBioAddress = (JsonArray) parser.parse(gson.toJson(entity));
				jsonObject.add("modifiedEntity", jsonArrayBioAddress);
				jsonObject.addProperty("dataType", dataType);
				break;
				
			case HRMConstant.BIO_REV_CONTACT:				
				JsonArray jsonArrayBioEmergencyContact = (JsonArray) parser.parse(gson.toJson(entity));
				jsonObject.add("modifiedEntity", jsonArrayBioEmergencyContact);
				jsonObject.addProperty("dataType", dataType);
				break;
				
			case HRMConstant.BIO_REV_ID_CARD:				
				JsonArray jsonArrayBioIdCard = (JsonArray) parser.parse(gson.toJson(entity));
				jsonObject.add("modifiedEntity", jsonArrayBioIdCard);
				jsonObject.addProperty("dataType", dataType);
				break;
				
			case HRMConstant.BIO_REV_FAMILY:				
				JsonArray jsonArrayFamily = (JsonArray) parser.parse(gson.toJson(entity));
				jsonObject.add("modifiedEntity", jsonArrayFamily);
				jsonObject.addProperty("dataType", dataType);
				break;
				
			case HRMConstant.BIO_REV_COMPANY_RELATION:				
				JsonArray jsonArrayCompanyRelation = (JsonArray) parser.parse(gson.toJson(entity));
				jsonObject.add("modifiedEntity", jsonArrayCompanyRelation);
				jsonObject.addProperty("dataType", dataType);
				break;
				
			case HRMConstant.BIO_REV_EDUCATION:				
				JsonArray jsonArrayBioEducationHistoryModel = (JsonArray) parser.parse(gson.toJson(entity));
				jsonObject.add("modifiedEntity", jsonArrayBioEducationHistoryModel);
				jsonObject.addProperty("dataType", dataType);
				break;
				
			case HRMConstant.BIO_REV_SKILL:				
				JsonArray jsonArrayBioKeahlian = (JsonArray) parser.parse(gson.toJson(entity));
				jsonObject.add("modifiedEntity", jsonArrayBioKeahlian);
				jsonObject.addProperty("dataType", dataType);
				break;

			default:
				break;
			}

	        //parsing object to json         
	        return gson.toJson(jsonObject);
	 }
	 
	 private void saveOrUpdateListBioAddress(List<BioAddress> listBioAddressRevision, Long bioDataId){
		 
		 BioData bioData = bioDataDao.getEntiyByPK(bioDataId);
		 
		 //Dapatkan List BioAddress dari DB dari bioDataId yang mengajukan proses Revisi
		 List<BioAddress> listBioAddressFromDb = bioAddressDao.getAllDataByBioDataId(bioDataId);
		 
		 //Looping Data Existing BioAddress dari database
		 for(BioAddress bioAddressFromDb : listBioAddressFromDb){
			 
			 //jika data bioAddress dari db, tidak ada dalam list BioAddress dari proses revisi, berarti revisi nya itu hapus data yang sudah ada, jadi langsung hapus dari database
			 if(!Lambda.exists(listBioAddressRevision, Lambda.having(Lambda.on(BioAddress.class).getId() == bioAddressFromDb.getId()))){
				 bioAddressDao.delete(bioAddressFromDb);
			 }
		 }
		 
		//Looping Data  BioAddress dari list BioAddress hasil revisi
		 for(BioAddress bioAddressRevision : listBioAddressRevision){
			 
			 //Cek apakah data BioAddress dari proses revisi biodata, sudah ada di database
			 BioAddress bioAddressToUpdate = bioAddressDao.getEntiyByPK(bioAddressRevision.getId());
			 City city = cityDao.getEntiyByPK(bioAddressRevision.getCity().getId());
			 
			//Jika belum ada, berarti revisinya adalah tambah data baru.
			 if(ObjectUtils.equals(null, bioAddressToUpdate)){
				 
				 bioAddressRevision.setCity(city);
				 bioAddressRevision.setBioData(bioData);
				 bioAddressRevision.setCreatedBy(UserInfoUtil.getUserName());
				 bioAddressRevision.setCreatedOn(new Date());
				 bioAddressDao.save(bioAddressRevision);
			 }else{//Jika sudah ada, berarti revisinya adalah edit data yang sudah ada.
				 
				 BeanUtils.copyProperties(bioAddressRevision, bioAddressToUpdate, new String[]{"id","version","city","bioData","createdBy","createdOn"});
				 bioAddressToUpdate.setCity(city);
				 bioAddressToUpdate.setBioData(bioData);
				 bioAddressToUpdate.setUpdatedBy(UserInfoUtil.getUserName());
				 bioAddressToUpdate.setUpdatedOn(new Date());
				 bioAddressDao.update(bioAddressToUpdate);
				 
			 }
		 }
	 }
	 
	 private void saveOrUpdateBioEmergencyContact(List<BioEmergencyContact> listBioEmergencyContactRevision, Long bioDataId){
		 
		 BioData bioData = bioDataDao.getEntiyByPK(bioDataId);
		 
		//Dapatkan List BioEmergencyContact dari DB dari bioDataId yang mengajukan proses Revisi
		 List<BioEmergencyContact> listBioEmergencyContactFromDb = bioEmergencyContactDao.getAllDataByBioDataId(bioDataId);
		 
		//Looping Data Existing BioEmergencyContact dari database
		 for(BioEmergencyContact bioEmergencyContactFromDb : listBioEmergencyContactFromDb){
			 
			 //jika data BioEmergencyContact dari db, tidak ada dalam list BioEmergencyContact dari proses revisi, berarti revisi nya itu hapus data yang sudah ada, jadi langsung hapus dari database
			 if(!Lambda.exists(listBioEmergencyContactRevision, Lambda.having(Lambda.on(BioEmergencyContact.class).getId() == bioEmergencyContactFromDb.getId()))){
				 bioEmergencyContactDao.delete(bioEmergencyContactFromDb);
			 }
		 }
		 
		//Looping Data  BioEmergencyContact dari list BioEmergencyContact hasil revisi
		 for(BioEmergencyContact bioEmergencyContactRevision : listBioEmergencyContactRevision){
			 
			//Cek apakah data BioAddress dari proses revisi biodata, sudah ada di database
			 BioEmergencyContact bioEmergencyContactToUpdate = bioEmergencyContactDao.getEntiyByPK(bioEmergencyContactRevision.getId());
			 City city = cityDao.getEntiyByPK(bioEmergencyContactRevision.getCity().getId());
			 FamilyRelation familyRelation = familyRelationDao.getEntiyByPK(bioEmergencyContactRevision.getFamilyRelation().getId());
			 
			//Jika belum ada, berarti revisinya adalah tambah data baru.
			 if(ObjectUtils.equals(null, bioEmergencyContactToUpdate)){
				 
				 bioEmergencyContactRevision.setCity(city);
				 bioEmergencyContactRevision.setFamilyRelation(familyRelation);
				 bioEmergencyContactRevision.setBioData(bioData);
				 bioEmergencyContactRevision.setCreatedBy(UserInfoUtil.getUserName());
				 bioEmergencyContactRevision.setCreatedOn(new Date());
				 bioEmergencyContactDao.save(bioEmergencyContactRevision);
				 
			 }else{//Jika sudah ada, berarti revisinya adalah edit data yang sudah ada.
				 
				 BeanUtils.copyProperties(bioEmergencyContactRevision, bioEmergencyContactToUpdate, new String[]{"id","version","city","bioData","familyRelation","createdBy","createdOn"});
				 bioEmergencyContactToUpdate.setCity(city);
				 bioEmergencyContactToUpdate.setFamilyRelation(familyRelation);
				 bioEmergencyContactToUpdate.setBioData(bioData);
				 bioEmergencyContactToUpdate.setUpdatedBy(UserInfoUtil.getUserName());
				 bioEmergencyContactToUpdate.setUpdatedOn(new Date());
				 bioEmergencyContactDao.update(bioEmergencyContactToUpdate);
				 
			 }
			 
		 }
	 }
	 
	 private void saveOrUpdateBioIdCard(List<BioIdCard> listBioIdCardRevision, Long bioDataId){
		 
		 BioData bioData = bioDataDao.getEntiyByPK(bioDataId);
		 
		 //Dapatkan List BioIdCard dari DB dari bioDataId yang mengajukan proses Revisi
		 List<BioIdCard> listBioIdCardFromDb = bioIdCardDao.getAllDataByBioDataId(bioDataId);
		 
		//Looping Data Existing BioIdCard dari database
		 for(BioIdCard bioIdCardFromDb : listBioIdCardFromDb){
			 
			//jika data BioIdCard dari db, tidak ada dalam list BioIdCard dari proses revisi, berarti revisi nya itu hapus data yang sudah ada, jadi langsung hapus dari database
			 if(!Lambda.exists(listBioIdCardRevision, Lambda.having(Lambda.on(BioIdCard.class).getId() == bioIdCardFromDb.getId()))){
				 bioIdCardDao.delete(bioIdCardFromDb);
			 }
		 }
		 
		 //Looping Data BioIdCard dari list BioIdCard hasil revisi
		 for(BioIdCard bioIdCardRevision : listBioIdCardRevision){
			 
			 //Cek apakah data BioIdCard dari proses revisi biodata, sudah ada di database
			 BioIdCard bioIdCardToUpdate = bioIdCardDao.getEntiyByPK(bioIdCardRevision.getId());
			 City city = cityDao.getEntiyByPK(bioIdCardRevision.getCity().getId());
			 
			//Jika belum ada, berarti revisinya adalah tambah data baru.
			 if(ObjectUtils.equals(null, bioIdCardToUpdate)){
				 
				 bioIdCardRevision.setCity(city);
				 bioIdCardRevision.setBioData(bioData);
				 bioIdCardRevision.setCreatedBy(UserInfoUtil.getUserName());
				 bioIdCardRevision.setCreatedOn(new Date());
				 bioIdCardDao.save(bioIdCardRevision);
				 
			 }else{//Jika sudah ada, berarti revisinya adalah edit data yang sudah ada.
				 
				 BeanUtils.copyProperties(bioIdCardRevision, bioIdCardToUpdate, new String[]{"id","city","bioData","createdBy","createdOn"});
				 bioIdCardToUpdate.setCity(city);
				 bioIdCardToUpdate.setBioData(bioData);
				 bioIdCardToUpdate.setUpdatedBy(UserInfoUtil.getUserName());
				 bioIdCardToUpdate.setUpdatedOn(new Date());
				 bioIdCardDao.update(bioIdCardToUpdate);
				 
			 }
		 }
	 }
	 
	 private void saveOrUpdateBioFamilyRelationship(List<BioFamilyRelationship> listBioFamilyRelationshipRevision, Long bioDataId){
		 BioData bioData = bioDataDao.getEntiyByPK(bioDataId);
		 
		 //Dapatkan List BioFamilyRelationship dari DB dari bioDataId yang mengajukan proses Revisi
		 List<BioFamilyRelationship> listBioFamilyRelationshipFromDb = bioFamilyRelationshipDao.getAllDataByBioDataId(bioDataId);
		 
		//Looping Data Existing BioFamilyRelationship dari database
		 for(BioFamilyRelationship bioFamilyRelationshipFromDb : listBioFamilyRelationshipFromDb){
			 
			//jika data BioFamilyRelationship dari db, tidak ada dalam list BioFamilyRelationship dari proses revisi, berarti revisi nya itu hapus data yang sudah ada, jadi langsung hapus dari database
			 if(!Lambda.exists(listBioFamilyRelationshipRevision, Lambda.having(Lambda.on(BioIdCard.class).getId() == bioFamilyRelationshipFromDb.getId()))){
				 bioFamilyRelationshipDao.delete(bioFamilyRelationshipFromDb);
			 }
		 }
		 
		 //Looping Data BioFamilyRelationship dari list BioFamilyRelationship hasil revisi
		 for(BioFamilyRelationship bioFamilyRelationshipRevision : listBioFamilyRelationshipRevision){
			 
			//Cek apakah data BioFamilyRelationship dari proses revisi biodata, sudah ada di database
			 BioFamilyRelationship bioFamilyRelationshipToUpdate = bioFamilyRelationshipDao.getEntiyByPK(bioFamilyRelationshipRevision.getId());
			 EducationLevel educationLevel = educationLevelDao.getEntiyByPK(bioFamilyRelationshipRevision.getEducationLevel().getId());
			 FamilyRelation familyRelation = familyRelationDao.getEntiyByPK(bioFamilyRelationshipRevision.getFamilyRelation().getId());
			 
			//Jika belum ada, berarti revisinya adalah tambah data baru.
			 if(ObjectUtils.equals(null, bioFamilyRelationshipToUpdate)){
				 bioFamilyRelationshipRevision.setEducationLevel(educationLevel);
				 bioFamilyRelationshipRevision.setFamilyRelation(familyRelation);
				 bioFamilyRelationshipRevision.setBioData(bioData);
				 bioFamilyRelationshipRevision.setCreatedBy(UserInfoUtil.getUserName());
				 bioFamilyRelationshipRevision.setCreatedOn(new Date());
				 bioFamilyRelationshipDao.save(bioFamilyRelationshipRevision);
				 
			 }else{//Jika sudah ada, berarti revisinya adalah edit data yang sudah ada.
				 
				 BeanUtils.copyProperties(bioFamilyRelationshipRevision, bioFamilyRelationshipToUpdate, new String[]{"id","educationLevel","familyRelation","bioData","createdBy","createdOn"});
				 bioFamilyRelationshipToUpdate.setEducationLevel(educationLevel);
				 bioFamilyRelationshipToUpdate.setFamilyRelation(familyRelation);
				 bioFamilyRelationshipToUpdate.setBioData(bioData);
				 bioFamilyRelationshipToUpdate.setUpdatedBy(UserInfoUtil.getUserName());
				 bioFamilyRelationshipToUpdate.setUpdatedOn(new Date());
				 bioFamilyRelationshipDao.update(bioFamilyRelationshipToUpdate);
				 
			 }
		 }
	 }
	 
	 private void saveOrUpdateBioRelasiPerusahaan(List<BioRelasiPerusahaan> listBioRelasiPerusahaanRevision, Long bioDataId){
		 BioData bioData = bioDataDao.getEntiyByPK(bioDataId);
		 
		 //Dapatkan List BioRelasiPerusahaan dari DB dari bioDataId yang mengajukan proses Revisi
		 List<BioRelasiPerusahaan> listBioFamilyRelationshipFromDb = bioRelasiPerusahaanDao.getAllDataByBioDataId(bioDataId);
		 
		//Looping Data Existing BioRelasiPerusahaan dari database
		 for(BioRelasiPerusahaan bioRelasiPerusahaanFromDb : listBioFamilyRelationshipFromDb){
			 
			//jika data BioFamilyRelationship dari db, tidak ada dalam list BioFamilyRelationship dari proses revisi, berarti revisi nya itu hapus data yang sudah ada, jadi langsung hapus dari database
			 if(!Lambda.exists(listBioRelasiPerusahaanRevision, Lambda.having(Lambda.on(BioRelasiPerusahaan.class).getId() == bioRelasiPerusahaanFromDb.getId()))){
				 bioRelasiPerusahaanDao.delete(bioRelasiPerusahaanFromDb);
			 }
		 }
		 
		 //Looping Data BioRelasiPerusahaan dari list BioRelasiPerusahaan hasil revisi
		 for(BioRelasiPerusahaan bioRelasiPerusahaanRevision : listBioRelasiPerusahaanRevision){
			 
			 //Cek apakah data BioRelasiPerusahaan dari proses revisi BioRelasiPerusahaan, sudah ada di database
			 BioRelasiPerusahaan bioRelasiPerusahaanToUpdate = bioRelasiPerusahaanDao.getEntiyByPK(bioRelasiPerusahaanRevision.getId());
			 City city = cityDao.getEntiyByPK(bioRelasiPerusahaanRevision.getCity().getId());
			 
			//Jika belum ada, berarti revisinya adalah tambah data baru.
			 if(ObjectUtils.equals(null, bioRelasiPerusahaanToUpdate)){
				 
				 bioRelasiPerusahaanRevision.setCity(city);
				 bioRelasiPerusahaanRevision.setBioData(bioData);
				 bioRelasiPerusahaanRevision.setCreatedBy(UserInfoUtil.getUserName());
				 bioRelasiPerusahaanRevision.setCreatedOn(new Date());
				 bioRelasiPerusahaanDao.save(bioRelasiPerusahaanRevision);
				 
			 }else{//Jika sudah ada, berarti revisinya adalah edit data yang sudah ada.
				 
				 BeanUtils.copyProperties(bioRelasiPerusahaanRevision, bioRelasiPerusahaanToUpdate, new String[]{"id","version","city","bioData","createdBy","createdOn"});
				 bioRelasiPerusahaanToUpdate.setCity(city);
				 bioRelasiPerusahaanToUpdate.setBioData(bioData);
				 bioRelasiPerusahaanToUpdate.setUpdatedBy(UserInfoUtil.getUserName());
				 bioRelasiPerusahaanToUpdate.setUpdatedOn(new Date());
				 bioRelasiPerusahaanDao.update(bioRelasiPerusahaanToUpdate);
				 
			 }
		 }
		 
	 }

	 
	 private void saveOrUpdateBioEducationHistory(List<BioEducationHistoryModel> listBioEducationHistoryModelRevision, Long bioDataId){
		 BioData bioData = bioDataDao.getEntiyByPK(bioDataId);
		 
		 //Dapatkan List BioEducationHistory dari DB dari bioDataId yang mengajukan proses Revisi
		 List<BioEducationHistory> listBioEducationHistoryFromDb = bioEducationHistoryDao.getAllDataByBioDataId(bioDataId);
		 
		//Looping Data Existing BioEducationHistory dari database
		 for(BioEducationHistory bioEducationHistoryFromDb : listBioEducationHistoryFromDb){
			 
			//jika data BioEducationHistory dari db, tidak ada dalam list BioEducationHistoryModel dari proses revisi, berarti revisi nya itu hapus data yang sudah ada, jadi langsung hapus dari database
			 if(!Lambda.exists(listBioEducationHistoryModelRevision, Lambda.having(Lambda.on(BioEducationHistoryModel.class).getId() == bioEducationHistoryFromDb.getId()))){
				 bioEducationHistoryDao.delete(bioEducationHistoryFromDb);
			 }
		 }
		 
		//Looping Data BioEducationHistoryModel dari list BioEducationHistoryModel hasil revisi
		 for(BioEducationHistoryModel bioEducationHistoryModelRevision : listBioEducationHistoryModelRevision){
			 
			//Cek apakah data BioEducationHistory dari proses revisi BioEducationHistoryModel, sudah ada di database
			 BioEducationHistory bioEducationHistoryToUpdate = bioEducationHistoryDao.getEntiyByPK(bioEducationHistoryModelRevision.getId());
			 City city = cityDao.getEntiyByPK(bioEducationHistoryModelRevision.getCity().getId());
			 EducationLevel educationLevel = educationLevelDao.getEntiyByPK(bioEducationHistoryModelRevision.getEducationLevelId());
			 InstitutionEducation institutionEducation = institutionEducationDao.getEntiyByPK(bioEducationHistoryModelRevision.getInstitutionEducationId());
			 Faculty faculty = facultyDao.getEntiyByPK(bioEducationHistoryModelRevision.getFacultyId());
			 Major major = majorDao.getEntiyByPK(bioEducationHistoryModelRevision.getMajorId());
			 
			//Jika belum ada, berarti revisinya adalah tambah data baru.
			 if(ObjectUtils.equals(null, bioEducationHistoryToUpdate)){
				 
				 BioEducationHistory newBioEducationHistory = convertBioEduModelToBioEducationHistory(bioEducationHistoryModelRevision, Boolean.FALSE);
				 newBioEducationHistory.setCity(city);
				 newBioEducationHistory.setEducationLevel(educationLevel);
				 newBioEducationHistory.setInstitutionEducation(institutionEducation);
				 newBioEducationHistory.setFaculty(faculty);
				 newBioEducationHistory.setMajor(major);
				 newBioEducationHistory.setBiodata(bioData);
				 newBioEducationHistory.setCreatedBy(UserInfoUtil.getUserName());
				 newBioEducationHistory.setCreatedOn(new Date());
				 bioEducationHistoryDao.save(newBioEducationHistory);
				 
			 }else{//Jika sudah ada, berarti revisinya adalah edit data yang sudah ada.
				 
				 bioEducationHistoryToUpdate = convertBioEduModelToBioEducationHistory(bioEducationHistoryModelRevision, Boolean.TRUE);
				 bioEducationHistoryToUpdate.setCity(city);
				 bioEducationHistoryToUpdate.setEducationLevel(educationLevel);
				 bioEducationHistoryToUpdate.setInstitutionEducation(institutionEducation);
				 bioEducationHistoryToUpdate.setFaculty(faculty);
				 bioEducationHistoryToUpdate.setMajor(major);
				 bioEducationHistoryToUpdate.setBiodata(bioData);
				 bioEducationHistoryToUpdate.setUpdatedBy(UserInfoUtil.getUserName());
				 bioEducationHistoryToUpdate.setUpdatedOn(new Date());
				 bioEducationHistoryDao.update(bioEducationHistoryToUpdate);
			 }
		 }
	 }
	 
	 private BioEducationHistory convertBioEduModelToBioEducationHistory(BioEducationHistoryModel bioEducationHistoryModel, Boolean isUpdate){
		 BioEducationHistory bioEducationHistory = new BioEducationHistory();
		 
		 if(!isUpdate){
			 bioEducationHistory.setId(bioEducationHistoryModel.getId());
		 }
		 
		 bioEducationHistory.setCertificateNumber(bioEducationHistoryModel.getCertificateNumber());
		 bioEducationHistory.setPathFoto(bioEducationHistoryModel.getPathFoto());
		 bioEducationHistory.setScore(bioEducationHistoryModel.getScore());
		 bioEducationHistory.setYearIn(bioEducationHistoryModel.getYearIn());
		 bioEducationHistory.setYearOut(bioEducationHistoryModel.getYearOut());
		 return bioEducationHistory;
	 }
	 
	 private void saveOrUpdateBioKeahlian(List<BioKeahlian> listBioKeahlianRevision, Long bioDataId){
		 BioData bioData = bioDataDao.getEntiyByPK(bioDataId);
		 
		 //Dapatkan List BioKeahlian dari DB dari bioDataId yang mengajukan proses Revisi
		 List<BioKeahlian> listBioKeahlianFromDb = bioKeahlianDao.getAllDataByBioDataId(bioDataId);
		 
		 //Looping Data Existing BioKeahlian dari database
		 for(BioKeahlian bioKeahlianFromDb : listBioKeahlianFromDb){
			 
			//jika data BioKeahlian dari db, tidak ada dalam list BioKeahlian dari proses revisi, berarti revisi nya itu hapus data yang sudah ada, jadi langsung hapus dari database
			 if(!Lambda.exists(listBioKeahlianRevision, Lambda.having(Lambda.on(BioKeahlian.class).getId() == bioKeahlianFromDb.getId()))){
				 bioKeahlianDao.delete(bioKeahlianFromDb);
			 }
		 }
		 
		//Looping Data BioKeahlian dari list BioKeahlian hasil revisi
		 for(BioKeahlian bioKeahlianRevision : listBioKeahlianRevision){
			 
			//Cek apakah data BioKeahlian dari proses revisi BioKeahlian, sudah ada di database
			 BioKeahlian bioKeahlianToUpdate = bioKeahlianDao.getEntiyByPK(bioKeahlianRevision.getId());
			 
			 
			//Jika belum ada, berarti revisinya adalah tambah data baru.
			 if(ObjectUtils.equals(null, bioKeahlianToUpdate)){
				 
				 bioKeahlianRevision.setBiodata(bioData);
				 bioKeahlianRevision.setCreatedBy(UserInfoUtil.getUserName());
				 bioKeahlianRevision.setCreatedOn(new Date());
				 bioKeahlianDao.save(bioKeahlianRevision);
				 
			 }else{//Jika sudah ada, berarti revisinya adalah edit data yang sudah ada.
				 
				 BeanUtils.copyProperties(bioKeahlianRevision, bioKeahlianToUpdate, new String[]{"id","bioData","createdBy","createdOn"});
				 bioKeahlianToUpdate.setBiodata(bioData);
				 bioKeahlianToUpdate.setUpdatedBy(UserInfoUtil.getUserName());
				 bioKeahlianToUpdate.setUpdatedOn(new Date());
				 bioKeahlianDao.update(bioKeahlianToUpdate);
				 
			 }
		 }
	 }
	 
	 private void saveOrUpdateBioSpesifikasiAbility(List<BioSpesifikasiAbility> listBioSpesifikasiAbilityRevision, Long bioDataId){
		 BioData bioData = bioDataDao.getEntiyByPK(bioDataId);
		 
		 //Dapatkan List BioSpesifikasiAbility dari DB dari bioDataId yang mengajukan proses Revisi
		 List<BioSpesifikasiAbility> listBioSpesifikasiAbilityFromDb = bioSpesifikasiAbilityDao.getAllDataByBiodataId(bioDataId);
		 
		//Looping Data Existing BioSpesifikasiAbility dari database
		 for(BioSpesifikasiAbility bioSpesifikasiAbilityFromDb : listBioSpesifikasiAbilityFromDb){
			 
			//jika data BioSpesifikasiAbility dari db, tidak ada dalam list BioSpesifikasiAbility dari proses revisi, berarti revisi nya itu hapus data yang sudah ada, jadi langsung hapus dari database
			 if(!Lambda.exists(listBioSpesifikasiAbilityRevision, Lambda.having(Lambda.on(BioSpesifikasiAbility.class).getId().equals(bioSpesifikasiAbilityFromDb.getId())))){
				 bioSpesifikasiAbilityDao.delete(bioSpesifikasiAbilityFromDb);
			 }
		 }
		 
		//Looping Data BioSpesifikasiAbility dari list BioKeahlian hasil revisi
		 for(BioSpesifikasiAbility BioSpesifikasiAbilityRevision : listBioSpesifikasiAbilityRevision){
			 
			//Cek apakah data BioSpesifikasiAbility dari proses revisi BioSpesifikasiAbility, sudah ada di database
			 BioSpesifikasiAbility bioSpesifikasiAbilityToUpdate = bioSpesifikasiAbilityDao.getEntityByBioSpesifikasiAbilityId(new BioSpesifikasiAbilityId(bioDataId, BioSpesifikasiAbilityRevision.getSpecificationAbility().getId()));
			 
			//Jika belum ada, berarti revisinya adalah tambah data baru.
			 if(ObjectUtils.equals(null, bioSpesifikasiAbilityToUpdate)){
				 
				 BioSpesifikasiAbilityRevision.setBioData(bioData);
				 BioSpesifikasiAbilityRevision.setCreatedBy(UserInfoUtil.getUserName());
				 BioSpesifikasiAbilityRevision.setCreatedOn(new Date());
				 bioSpesifikasiAbilityDao.save(BioSpesifikasiAbilityRevision);
				 
			 }else{//Jika sudah ada, berarti revisinya adalah edit data yang sudah ada.
				 
				 BeanUtils.copyProperties(BioSpesifikasiAbilityRevision, bioSpesifikasiAbilityToUpdate, new String[]{"id","bioData","createdBy","createdOn"});
				 bioSpesifikasiAbilityToUpdate.setBioData(bioData);
				 bioSpesifikasiAbilityToUpdate.setUpdatedBy(UserInfoUtil.getUserName());
				 bioSpesifikasiAbilityToUpdate.setUpdatedOn(new Date());
				 bioSpesifikasiAbilityDao.update(bioSpesifikasiAbilityToUpdate);
				 
			 }
		 }
	 }


	@Override
	protected String getDetailSmsContentOfActivity(ApprovalActivity appActivity) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.
	}

	
}
