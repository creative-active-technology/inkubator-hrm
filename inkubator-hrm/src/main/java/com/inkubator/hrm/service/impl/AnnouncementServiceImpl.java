package com.inkubator.hrm.service.impl;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.criterion.Order;
import org.primefaces.json.JSONException;
import org.primefaces.json.JSONObject;
import org.primefaces.model.UploadedFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.inkubator.common.util.RandomNumberUtil;
import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.dao.AnnouncementDao;
import com.inkubator.hrm.dao.AnnouncementEmpTypeDao;
import com.inkubator.hrm.dao.AnnouncementGoljabDao;
import com.inkubator.hrm.dao.AnnouncementUnitDao;
import com.inkubator.hrm.dao.ApprovalActivityDao;
import com.inkubator.hrm.dao.CompanyDao;
import com.inkubator.hrm.dao.EmpDataDao;
import com.inkubator.hrm.dao.EmployeeTypeDao;
import com.inkubator.hrm.dao.GolonganJabatanDao;
import com.inkubator.hrm.dao.UnitKerjaDao;
import com.inkubator.hrm.entity.Announcement;
import com.inkubator.hrm.entity.AnnouncementEmpType;
import com.inkubator.hrm.entity.AnnouncementEmpTypeId;
import com.inkubator.hrm.entity.AnnouncementGoljab;
import com.inkubator.hrm.entity.AnnouncementGoljabId;
import com.inkubator.hrm.entity.AnnouncementUnit;
import com.inkubator.hrm.entity.AnnouncementUnitId;
import com.inkubator.hrm.entity.ApprovalActivity;
import com.inkubator.hrm.entity.Company;
import com.inkubator.hrm.entity.EmployeeType;
import com.inkubator.hrm.entity.GolonganJabatan;
import com.inkubator.hrm.entity.UnitKerja;
import com.inkubator.hrm.json.util.JsonUtil;
import com.inkubator.hrm.service.AnnouncementService;
import com.inkubator.hrm.web.search.AnnouncementSearchParameter;
import com.inkubator.securitycore.util.UserInfoUtil;
import com.inkubator.webcore.util.FacesIO;

/**
 *
 * @author WebGenX
 */
@Service(value = "announcementService")
@Lazy
public class AnnouncementServiceImpl extends BaseApprovalServiceImpl implements AnnouncementService {

    @Autowired
    private AnnouncementDao announcementDao;
    @Autowired
    private AnnouncementGoljabDao announcementGoljabDao;
    @Autowired
    private AnnouncementEmpTypeDao announcementEmpTypeDao;
    @Autowired
    private AnnouncementUnitDao announcementUnitDao;
    @Autowired
    private GolonganJabatanDao golonganJabatanDao;
    @Autowired
    private EmployeeTypeDao employeeTypeDao;
    @Autowired
    private UnitKerjaDao unitKerjaDao;
    @Autowired
    private ApprovalActivityDao approvalActivityDao;
    @Autowired
    private CompanyDao companyDao;
    @Autowired
    private EmpDataDao empDataDao;
    @Autowired
    private FacesIO facesIO;

    @Override
    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void delete(Announcement announcement) throws Exception {
        announcementDao.delete(announcement);
    }

    @Override
    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void save(Announcement announcement) throws Exception {
    	throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void update(Announcement announcement) throws Exception {
    	throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED, propagation = Propagation.SUPPORTS, timeout = 30)
    public Announcement getEntiyByPK(Long id) {
        return this.announcementDao.getEntiyByPK(id);
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 50)
    public List<Announcement> getByParam(AnnouncementSearchParameter searchParameter, int firstResult, int maxResults, Order order) throws Exception {
        return this.announcementDao.getByParam(searchParameter, firstResult, maxResults, order);
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED, propagation = Propagation.SUPPORTS, timeout = 30)
    public Long getTotalByParam(AnnouncementSearchParameter searchParameter) throws Exception {
        return this.announcementDao.getTotalByParam(searchParameter);
    }

    @Override
    public Announcement getEntiyByPK(String id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Announcement getEntiyByPK(Integer id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void saveOrUpdate(Announcement enntity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Announcement saveData(Announcement entity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Announcement updateData(Announcement entity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Announcement saveOrUpdateData(Announcement entity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Announcement getEntityByPkIsActive(String id, Integer isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Announcement getEntityByPkIsActive(String id, Byte isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Announcement getEntityByPkIsActive(String id, Boolean isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Announcement getEntityByPkIsActive(Integer id, Integer isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Announcement getEntityByPkIsActive(Integer id, Byte isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Announcement getEntityByPkIsActive(Integer id, Boolean isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Announcement getEntityByPkIsActive(Long id, Integer isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Announcement getEntityByPkIsActive(Long id, Byte isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Announcement getEntityByPkIsActive(Long id, Boolean isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void softDelete(Announcement entity) throws Exception {
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
    public List<Announcement> getAllData() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Announcement> getAllData(Boolean isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Announcement> getAllData(Integer isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Announcement> getAllData(Byte isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Announcement> getAllDataPageAble(int firstResult, int maxResults, Order order) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Announcement> getAllDataPageAbleIsActive(int firstResult, int maxResults, Order order, Boolean isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Announcement> getAllDataPageAbleIsActive(int firstResult, int maxResults, Order order, Integer isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Announcement> getAllDataPageAbleIsActive(int firstResult, int maxResults, Order order, Byte isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected void sendingEmailApprovalNotif(ApprovalActivity appActivity) throws Exception {
    	//initialization
        Gson gson = JsonUtil.getHibernateEntityGsonBuilder().create();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMMM-yyyy", new Locale(appActivity.getLocale()));
        
        //get all sendCC email address on status approve OR reject
        List<String> ccEmailAddresses = new ArrayList<String>();
        if ((appActivity.getApprovalStatus() == HRMConstant.APPROVAL_STATUS_APPROVED) || (appActivity.getApprovalStatus() == HRMConstant.APPROVAL_STATUS_REJECTED)) {
            ccEmailAddresses = super.getCcEmailAddressesOnApproveOrReject(appActivity);
        }
        
        Announcement announcement = gson.fromJson(appActivity.getPendingData(), Announcement.class);
        Company company = companyDao.getEntiyByPK(announcement.getCompany().getId());
        JsonObject jsonObject = (JsonObject) gson.fromJson(appActivity.getPendingData(), JsonObject.class);        
    	List<Long> listEmployeeTypeId = gson.fromJson(jsonObject.get("listEmployeeTypeId").getAsString(), new TypeToken<List<Long>>() {}.getType());
    	List<Long> listGolonganJabatanId = gson.fromJson(jsonObject.get("listGolonganJabatanId").getAsString(), new TypeToken<List<Long>>() {}.getType());
    	List<Long> listUnitKerjaId = gson.fromJson(jsonObject.get("listUnitKerjaId").getAsString(), new TypeToken<List<Long>>() {}.getType());
    	
    	List<String> listEmployeeType =  new ArrayList<String>();
    	for(Long id : listEmployeeTypeId){
    		EmployeeType empType = employeeTypeDao.getEntiyByPK(id);
    		if(empType != null) {
    			listEmployeeType.add(empType.getName());
    		}
    	}
    	
        List<String> listGolonganJabatan =  new ArrayList<String>();
        for(Long id : listGolonganJabatanId){
    		GolonganJabatan golonganJabatan = golonganJabatanDao.getEntiyByPK(id);
    		if(golonganJabatan != null) {
    			listGolonganJabatan.add(golonganJabatan.getCode());
    		}
    	}
        
        List<String> listUnitKerja =  new ArrayList<String>();
        for(Long id : listUnitKerjaId){
    		UnitKerja unitKerja = unitKerjaDao.getEntiyByPK(id);
    		if(unitKerja != null) {
    			listUnitKerja.add(unitKerja.getName());
    		}
    	}
    	
        final JSONObject jsonObj = new JSONObject();
        try {
            jsonObj.put("approvalActivityId", appActivity.getId());
            jsonObj.put("ccEmailAddresses", ccEmailAddresses);
            jsonObj.put("proposeDate", dateFormat.format(announcement.getCreatedOn()));
            jsonObj.put("subjek", announcement.getSubject());
            jsonObj.put("content", announcement.getAnnouncementContent());
            jsonObj.put("company", company.getName());
            jsonObj.put("locale", appActivity.getLocale());
            jsonObj.put("listEmployeeType", listEmployeeType);
            jsonObj.put("listUnitKerja", listUnitKerja);
            jsonObj.put("listGolonganJabatan", listGolonganJabatan);

        } catch (JSONException e) {
            LOGGER.error("Error when create json Object ", e);
        }

        //send messaging, to trigger sending email
        super.jmsTemplateApproval.send(new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                return session.createTextMessage(jsonObj.toString());
            }
        });
    }

    @Override
    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void approved(long approvalActivityId, String pendingDataUpdate, String comment) throws Exception {
    	Map<String, Object> result = super.approvedAndCheckNextApproval(approvalActivityId, pendingDataUpdate, comment);
        ApprovalActivity appActivity = (ApprovalActivity) result.get("approvalActivity");
        if (StringUtils.equals((String) result.get("isEndOfApprovalProcess"), "true")) {
            /**
             * kalau status akhir sudah di approved dan tidak ada next approval,
             * berarti langsung insert ke database
             */
        	Gson gson = new GsonBuilder().create();
        	JsonObject jsonObject = (JsonObject) gson.fromJson(appActivity.getPendingData(), JsonObject.class);
        	List<Long> listEmployeeTypeId = gson.fromJson(jsonObject.get("listEmployeeTypeId").getAsString(), new TypeToken<List<Long>>() {}.getType());
        	List<Long> listUnitKerja = gson.fromJson(jsonObject.get("listUnitKerjaId").getAsString(), new TypeToken<List<Long>>() {}.getType());
        	List<Long> listGolonganJabatanId = gson.fromJson(jsonObject.get("listGolonganJabatanId").getAsString(), new TypeToken<List<Long>>() {}.getType());
        	Announcement announcement = this.convertJsonToEntity(appActivity.getPendingData());
        	announcement.setStatus(HRMConstant.ANNOUNCEMENT_STATUS_APPROVED); // set approved status
        	announcement.setApprovalActivityNumber(appActivity.getActivityNumber()); //set approval activity number, for history approval purpose
            
            /** saving to DB */
            this.save(announcement, null, listEmployeeTypeId, listGolonganJabatanId, listUnitKerja, Boolean.TRUE);
        }

        //if there is no error, then sending the email notification
        sendingEmailApprovalNotif(appActivity);
    }

    @Override
    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void rejected(long approvalActivityId, String comment) throws Exception {
    	Map<String, Object> result = super.rejectedAndCheckNextApproval(approvalActivityId, comment);
        ApprovalActivity appActivity = (ApprovalActivity) result.get("approvalActivity");
        if (StringUtils.equals((String) result.get("isEndOfApprovalProcess"), "true")) {
            /**
             * kalau status akhir sudah di rejected dan tidak ada next approval,
             * berarti langsung insert ke database
             */
        	Gson gson = new GsonBuilder().create();
        	JsonObject jsonObject = (JsonObject) gson.fromJson(appActivity.getPendingData(), JsonObject.class);
        	List<Long> listEmployeeTypeId = gson.fromJson(jsonObject.get("listEmployeeTypeId").getAsString(), new TypeToken<List<Long>>() {}.getType());
        	List<Long> listUnitKerja = gson.fromJson(jsonObject.get("listUnitKerjaId").getAsString(), new TypeToken<List<Long>>() {}.getType());
        	List<Long> listGolonganJabatanId = gson.fromJson(jsonObject.get("listGolonganJabatanId").getAsString(), new TypeToken<List<Long>>() {}.getType());
        	Announcement announcement = this.convertJsonToEntity(appActivity.getPendingData());
        	announcement.setStatus(HRMConstant.ANNOUNCEMENT_STATUS_REJECTED); // set rejected status
        	announcement.setApprovalActivityNumber(appActivity.getActivityNumber()); //set approval activity number, for history approval purpose
            
            /** saving to DB */
            this.save(announcement, null, listEmployeeTypeId, listGolonganJabatanId, listUnitKerja, Boolean.TRUE);
        }

        //if there is no error, then sending the email notification
        sendingEmailApprovalNotif(appActivity);
    }

    @Override
    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void diverted(long approvalActivityId) throws Exception {
    	Map<String, Object> result = super.divertedAndCheckNextApproval(approvalActivityId);
        ApprovalActivity appActivity = (ApprovalActivity) result.get("approvalActivity");
        if (StringUtils.equals((String) result.get("isEndOfApprovalProcess"), "true")) {
            /**
             * kalau status akhir sudah di approved dan tidak ada next approval,
             * berarti langsung insert ke database
             */
        	Gson gson = new GsonBuilder().create();
        	JsonObject jsonObject = (JsonObject) gson.fromJson(appActivity.getPendingData(), JsonObject.class);
        	List<Long> listEmployeeTypeId = gson.fromJson(jsonObject.get("listEmployeeTypeId").getAsString(), new TypeToken<List<Long>>() {}.getType());
        	List<Long> listUnitKerja = gson.fromJson(jsonObject.get("listUnitKerjaId").getAsString(), new TypeToken<List<Long>>() {}.getType());
        	List<Long> listGolonganJabatanId = gson.fromJson(jsonObject.get("listGolonganJabatanId").getAsString(), new TypeToken<List<Long>>() {}.getType());
        	Announcement announcement = this.convertJsonToEntity(appActivity.getPendingData());
        	announcement.setStatus(HRMConstant.ANNOUNCEMENT_STATUS_APPROVED); // set approved status
        	announcement.setApprovalActivityNumber(appActivity.getActivityNumber()); //set approval activity number, for history approval purpose
            
            /** saving to DB */
            this.save(announcement, null, listEmployeeTypeId, listGolonganJabatanId, listUnitKerja, Boolean.TRUE);
        }

        //if there is no error, then sending the email notification
        sendingEmailApprovalNotif(appActivity);
    }

	@Override
	@Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public String saveWithApproval(Announcement entity, UploadedFile attachmentFile, List<Long> listEmployeeTypeId, List<Long> listGolonganJabatanId, 
			List<Long> listUnitKerja) throws Exception {
		
		entity.setStatus(HRMConstant.ANNOUNCEMENT_STATUS_APPROVED);// set approved status, as default
		return this.save(entity, attachmentFile, listEmployeeTypeId, listGolonganJabatanId, listUnitKerja, Boolean.FALSE);
	}

	@Override
	@Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public String saveWithRevised(Announcement entity, UploadedFile attachmentFile, List<Long> listEmployeeTypeId, List<Long> listGolonganJabatanId, 
			List<Long> listUnitKerja, Long approvalActivityId) throws Exception {
		String message = "error";
		
		//start binding and validation
		entity = this.entityBindingAndValidation(entity);
		
		/** proceed of revising data */
    	String pendingData = this.convertToJsonData(entity, attachmentFile, listEmployeeTypeId, listGolonganJabatanId, listUnitKerja);
    	super.revised(approvalActivityId, pendingData);
    	
    	message = "success_need_approval";
		
		return message;
	}

	private String save(Announcement entity, UploadedFile attachmentFile, List<Long> listEmployeeTypeId, List<Long> listGolonganJabatanId, 
			List<Long> listUnitKerjaId, Boolean isBypassApprovalChecking) throws Exception{
		String message = "error";

		//start binding and validation
		entity = this.entityBindingAndValidation(entity);
		
		/** start approval checking and saving data also */
		ApprovalActivity approvalActivity = isBypassApprovalChecking ? null : super.checkApprovalProcess(HRMConstant.ANNOUNCEMENT, UserInfoUtil.getUserName());
		if(approvalActivity == null){				
			/** proceed of saving data(entity) to DB */   
			entity.setId(Long.parseLong(RandomNumberUtil.getRandomNumber(9)));
	        announcementDao.save(entity);
	        
	        for(Long id : listEmployeeTypeId){
	        	EmployeeType employeeType = employeeTypeDao.getEntiyByPK(id);
	        	AnnouncementEmpType announcementEmpType =  new AnnouncementEmpType();
	        	announcementEmpType.setId(new AnnouncementEmpTypeId(entity.getId(), employeeType.getId()));
	        	announcementEmpType.setAnnouncement(entity);
	        	announcementEmpType.setEmployeeType(employeeType);
	        	announcementEmpType.setCreatedBy(entity.getCreatedBy());
	        	announcementEmpType.setCreatedOn(entity.getCreatedOn());
	        	announcementEmpTypeDao.save(announcementEmpType);
	        }
	        
	        for(Long id : listGolonganJabatanId){
	        	GolonganJabatan golJab = golonganJabatanDao.getEntiyByPK(id);
	        	AnnouncementGoljab announcementGoljab =  new AnnouncementGoljab();
	        	announcementGoljab.setId(new AnnouncementGoljabId(entity.getId(), golJab.getId()));
	        	announcementGoljab.setAnnouncement(entity);
	        	announcementGoljab.setGolonganJabatan(golJab);
	        	announcementGoljab.setCreatedBy(entity.getCreatedBy());
	        	announcementGoljab.setCreatedOn(entity.getCreatedOn());
	        	announcementGoljabDao.save(announcementGoljab);
	        }
	        
	        for(Long id : listUnitKerjaId){
	        	UnitKerja unitKerja = unitKerjaDao.getEntiyByPK(id);
	        	AnnouncementUnit announcementUnit =  new AnnouncementUnit();
	        	announcementUnit.setId(new AnnouncementUnitId(entity.getId(), unitKerja.getId()));
	        	announcementUnit.setAnnouncement(entity);
	        	announcementUnit.setUnitKerja(unitKerja);
	        	announcementUnit.setCreatedBy(entity.getCreatedBy());
	        	announcementUnit.setCreatedOn(entity.getCreatedOn());
	        	announcementUnitDao.save(announcementUnit);
	        }
            
            message = "success_without_approval";
            
		} else {	
			/** proceed of saving approval activity */
			String pendingData = this.convertToJsonData(entity, attachmentFile, listEmployeeTypeId, listGolonganJabatanId, listUnitKerjaId);
            approvalActivity.setPendingData(pendingData);
            approvalActivityDao.save(approvalActivity);

            //sending email notification
            this.sendingEmailApprovalNotif(approvalActivity);
            
            message = "success_need_approval";
		}    
		
		return message;
	}

	private Announcement entityBindingAndValidation(Announcement entity) {
		
		Company company = companyDao.getEntiyByPK(entity.getCompany().getId());
		String createdBy = StringUtils.isEmpty(entity.getCreatedBy()) ? UserInfoUtil.getUserName() : entity.getCreatedBy();
        Date createdOn = entity.getCreatedOn() == null ? new Date() : entity.getCreatedOn();
        
		entity.setCompany(company);		
        entity.setCreatedBy(createdBy);
        entity.setCreatedOn(createdOn);
		
		return entity;
	}
	
	private String convertToJsonData(Announcement entity, UploadedFile attachmentFile, List<Long> listEmployeeTypeId, List<Long> listGolonganJabatanId, 
			List<Long> listUnitKerjaId) throws IOException {
		
		//saving file attachment
		String attachmentPath = null;
        if (attachmentFile != null) {
        	attachmentPath = this.getUploadPath(attachmentFile);
        	
        	//remove old file(if any)
        	if(StringUtils.isNotEmpty(entity.getAttachmentPath())){
        		File oldFile = new File(entity.getAttachmentPath());            
        		FileUtils.deleteQuietly(oldFile);
        	}
            
            //added new file
            facesIO.transferFile(attachmentFile);
            File file = new File(facesIO.getPathUpload() + attachmentFile.getFileName());
            file.renameTo(new File(attachmentPath));
            entity.setAttachmentPath(attachmentPath);
        }		
		        
		/** parsing object to json */ 
        Gson gson = JsonUtil.getHibernateEntityGsonBuilder().create();
        JsonParser parser = new JsonParser();
        JsonObject jsonObject = (JsonObject) parser.parse(gson.toJson(entity));
        
        String jsonEmployeeType = gson.toJson(listEmployeeTypeId.toArray(new Long[listEmployeeTypeId.size()])).toString();
        jsonObject.addProperty("listEmployeeTypeId", jsonEmployeeType);
        String jsonGolonganJabatan = gson.toJson(listGolonganJabatanId.toArray(new Long[listGolonganJabatanId.size()])).toString();
        jsonObject.addProperty("listGolonganJabatanId", jsonGolonganJabatan);
        String jsonUnitKerja = gson.toJson(listUnitKerjaId.toArray(new Long[listUnitKerjaId.size()])).toString();
        jsonObject.addProperty("listUnitKerjaId", jsonUnitKerja);
        
        return gson.toJson(jsonObject);
	}
	
	private String getUploadPath(UploadedFile attachmentFile) {
        String extension = StringUtils.substringAfterLast(attachmentFile.getFileName(), ".");
        String uploadPath = facesIO.getPathUpload() + "announcement_" + RandomNumberUtil.getRandomNumber(9) + "." + extension;
        return uploadPath;
    }
	
	private Announcement convertJsonToEntity(String json){
		Gson gson = JsonUtil.getHibernateEntityGsonBuilder().create();
		Announcement entity = gson.fromJson(json, Announcement.class);
        return entity;
	}

	@Override
	@Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED, propagation = Propagation.SUPPORTS, timeout = 30)
	public Announcement getEntityByPkWithDetail(Long id) throws Exception {
		return announcementDao.getEntityByPkWithDetail(id);
	}
}
