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
import com.inkubator.hrm.dao.BioEmergencyContactDao;
import com.inkubator.hrm.dao.CityDao;
import com.inkubator.hrm.dao.DialectDao;
import com.inkubator.hrm.dao.EmpDataDao;
import com.inkubator.hrm.dao.HrmUserDao;
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
import com.inkubator.hrm.entity.BioEmergencyContact;
import com.inkubator.hrm.entity.City;
import com.inkubator.hrm.entity.EmpData;
import com.inkubator.hrm.entity.HrmUser;
import com.inkubator.hrm.entity.LoanNewApplication;
import com.inkubator.hrm.entity.LoanNewSchema;
import com.inkubator.hrm.entity.LoanNewType;
import com.inkubator.hrm.entity.RmbsApplication;
import com.inkubator.hrm.json.util.JsonUtil;
import com.inkubator.hrm.service.BioDataService;
import com.inkubator.hrm.util.CommonReportUtil;
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
        bioData.getCity().getCityName();
        bioData.getReligion().getName();
        bioData.getMaritalStatus().getName();
        bioData.getNationality().getNationalityName();
        bioData.getRace().getRaceName();
        bioData.getDialect().getDialectName();
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
    public List<BioData> getEntityByPKWithDetail(long id) throws Exception {
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
		        	//jika sebelumnya sudah pernah diajukan pada jenis kelompok formulir tsb, akan tetapi statusnya masih pending.
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
        			saveOrUpdateListBioAddress(modifiedListBioAddress);
        			break;
        			
        		case HRMConstant.BIO_REV_CONTACT:
        			List<BioEmergencyContact> modifiedListBioEmergencyContact = (List<BioEmergencyContact>) modifiedEntity;
        			updateBioEmergencyContact(modifiedListBioEmergencyContact);
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

			default:
				break;
			}

	        //parsing object to json         
	        return gson.toJson(jsonObject);
	 }
	 
	 private void saveOrUpdateListBioAddress(List<BioAddress> listBioAddress){
		 for(BioAddress bioAddress : listBioAddress){
			 
			 if(ObjectUtils.equals(null, bioAddressDao.getEntiyByPK(bioAddress.getId()))){
				 City city = cityDao.getEntiyByPK(bioAddress.getCity().getId());
				 BioData bioData = bioDataDao.getEntiyByPK(bioAddress.getBioData().getId());
				 
				 bioAddress.setCity(city);
				 bioAddress.setBioData(bioData);
				 bioAddress.setCreatedBy(UserInfoUtil.getUserName());
				 bioAddress.setCreatedOn(new Date());
				 
				 bioAddressDao.save(bioAddress);
			 }else{
				 
			 }
		 }
	 }
	 
	 private void updateBioEmergencyContact(List<BioEmergencyContact> listBioEmergencyContact){
		 for(BioEmergencyContact bioEmergencyContact : listBioEmergencyContact){
			 
		 }
	 }
	
}
