package com.inkubator.hrm.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.fileupload.disk.DiskFileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.hibernate.criterion.Order;
import org.joda.time.DateTime;
import org.primefaces.model.DefaultUploadedFile;
import org.primefaces.model.UploadedFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import ch.lambdaj.Lambda;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.inkubator.common.util.RandomNumberUtil;
import com.inkubator.exception.BussinessException;
import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.dao.ApprovalActivityDao;
import com.inkubator.hrm.dao.CurrencyDao;
import com.inkubator.hrm.dao.EmpDataDao;
import com.inkubator.hrm.dao.HrmUserDao;
import com.inkubator.hrm.dao.RmbsApplicationDao;
import com.inkubator.hrm.dao.RmbsSchemaListOfEmpDao;
import com.inkubator.hrm.dao.RmbsSchemaListOfTypeDao;
import com.inkubator.hrm.dao.RmbsTypeDao;
import com.inkubator.hrm.entity.ApprovalActivity;
import com.inkubator.hrm.entity.ApprovalDefinition;
import com.inkubator.hrm.entity.ApprovalDefinitionRmbsSchema;
import com.inkubator.hrm.entity.Currency;
import com.inkubator.hrm.entity.EmpData;
import com.inkubator.hrm.entity.HrmUser;
import com.inkubator.hrm.entity.RmbsApplication;
import com.inkubator.hrm.entity.RmbsSchema;
import com.inkubator.hrm.entity.RmbsSchemaListOfType;
import com.inkubator.hrm.entity.RmbsSchemaListOfTypeId;
import com.inkubator.hrm.entity.RmbsType;
import com.inkubator.hrm.json.util.JsonUtil;
import com.inkubator.hrm.service.RmbsApplicationService;
import com.inkubator.hrm.web.model.RmbsApplicationUndisbursedViewModel;
import com.inkubator.hrm.web.search.RmbsApplicationUndisbursedSearchParameter;
import com.inkubator.securitycore.util.UserInfoUtil;
import com.inkubator.webcore.util.FacesIO;

/**
 *
 * @author rizkykojek
 */
@Service(value = "rmbsApplicationService")
@Lazy
public class RmbsApplicationServiceImpl extends BaseApprovalServiceImpl implements RmbsApplicationService {

	@Autowired
	private RmbsApplicationDao rmbsApplicationDao;
	@Autowired
	private EmpDataDao empDataDao;
	@Autowired
	private RmbsTypeDao rmbsTypeDao;
	@Autowired
	private CurrencyDao currencyDao;
	@Autowired
	private HrmUserDao hrmUserDao;
	@Autowired
	private RmbsSchemaListOfEmpDao rmbsSchemaListOfEmpDao;
	@Autowired
	private ApprovalActivityDao approvalActivityDao;
	@Autowired
	private RmbsSchemaListOfTypeDao rmbsSchemaListOfTypeDao;
	@Autowired
	private FacesIO facesIO;
	
	@Override
	public RmbsApplication getEntiyByPK(String id) throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public RmbsApplication getEntiyByPK(Integer id) throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public RmbsApplication getEntiyByPK(Long id) throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public void save(RmbsApplication entity) throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public void update(RmbsApplication entity) throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public void saveOrUpdate(RmbsApplication enntity) throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public RmbsApplication saveData(RmbsApplication entity) throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public RmbsApplication updateData(RmbsApplication entity) throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public RmbsApplication saveOrUpdateData(RmbsApplication entity)
			throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public RmbsApplication getEntityByPkIsActive(String id, Integer isActive)
			throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public RmbsApplication getEntityByPkIsActive(String id, Byte isActive)
			throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public RmbsApplication getEntityByPkIsActive(String id, Boolean isActive)
			throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public RmbsApplication getEntityByPkIsActive(Integer id, Integer isActive)
			throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public RmbsApplication getEntityByPkIsActive(Integer id, Byte isActive)
			throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public RmbsApplication getEntityByPkIsActive(Integer id, Boolean isActive)
			throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public RmbsApplication getEntityByPkIsActive(Long id, Integer isActive)
			throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public RmbsApplication getEntityByPkIsActive(Long id, Byte isActive)
			throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public RmbsApplication getEntityByPkIsActive(Long id, Boolean isActive)
			throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public void delete(RmbsApplication entity) throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public void softDelete(RmbsApplication entity) throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public Long getTotalData() throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public Long getTotalDataIsActive(Boolean isActive) throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public Long getTotalDataIsActive(Integer isActive) throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public Long getTotalDataIsActive(Byte isActive) throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public List<RmbsApplication> getAllData() throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public List<RmbsApplication> getAllData(Boolean isActive) throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public List<RmbsApplication> getAllData(Integer isActive) throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public List<RmbsApplication> getAllData(Byte isActive) throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public List<RmbsApplication> getAllDataPageAble(int firstResult,
			int maxResults, Order order) throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public List<RmbsApplication> getAllDataPageAbleIsActive(int firstResult,
			int maxResults, Order order, Boolean isActive) throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public List<RmbsApplication> getAllDataPageAbleIsActive(int firstResult,
			int maxResults, Order order, Integer isActive) throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public List<RmbsApplication> getAllDataPageAbleIsActive(int firstResult,
			int maxResults, Order order, Byte isActive) throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

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
            Gson gson = JsonUtil.getHibernateEntityGsonBuilder().create();
            String pendingData = appActivity.getPendingData();
            RmbsApplication rmbsApplication = gson.fromJson(pendingData, RmbsApplication.class);
            rmbsApplication.setApprovalActivityNumber(appActivity.getActivityNumber());  //set approval activity number, for history approval purpose

            /** convert to UploadedFile before saving */
            UploadedFile uploadedFile = this.convertFileToUploadedFile(gson, pendingData);
            
            /** saving to DB */
            this.save(rmbsApplication, uploadedFile, Boolean.TRUE, Boolean.FALSE, null);
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
             * kalau status akhir sudah di reject dan tidak ada next approval,
             * berarti langsung insert ke database
             */
            Gson gson = JsonUtil.getHibernateEntityGsonBuilder().create();
            String pendingData = appActivity.getPendingData();
            RmbsApplication rmbsApplication = gson.fromJson(pendingData, RmbsApplication.class);
            rmbsApplication.setApplicationStatus(HRMConstant.RMBS_STATUS_REJECTED);
            rmbsApplication.setApprovalActivityNumber(appActivity.getActivityNumber());  //set approval activity number, for history approval purpose
            
            /** convert to UploadedFile before saving */
            UploadedFile uploadedFile = this.convertFileToUploadedFile(gson, pendingData);
            
            /** saving to DB */
            this.save(rmbsApplication, uploadedFile, Boolean.TRUE, Boolean.FALSE, null);
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
            Gson gson = JsonUtil.getHibernateEntityGsonBuilder().create();
            String pendingData = appActivity.getPendingData();
            RmbsApplication rmbsApplication = gson.fromJson(pendingData, RmbsApplication.class);
            rmbsApplication.setApprovalActivityNumber(appActivity.getActivityNumber());  //set approval activity number, for history approval purpose

            /** convert to UploadedFile before saving */
            UploadedFile uploadedFile = this.convertFileToUploadedFile(gson, pendingData);
            
            /** saving to DB */
            this.save(rmbsApplication, uploadedFile, Boolean.TRUE, Boolean.FALSE, null);
        }

        //if there is no error, then sending the email notification
        sendingEmailApprovalNotif(appActivity);
		
	}
	
	@Override
	public UploadedFile convertFileToUploadedFile(Gson gson, String pendingData) throws IOException{
		
		/** converting process from file physics (which is get the path file from json) to UploadedFile object*/
		UploadedFile uploadedFile = null;
        JsonElement elReimbursment = gson.fromJson(pendingData, JsonObject.class).get("reimbursementFileName");
        if (!elReimbursment.isJsonNull()) {
            String reimbursmentFilePath = elReimbursment.getAsString();
            File file = new File(reimbursmentFilePath);
            DiskFileItem fileItem = (DiskFileItem) new DiskFileItemFactory().createItem("fileData", "text/plain", true, file.getName());
            InputStream input = new FileInputStream(file);
            OutputStream os = fileItem.getOutputStream();
            int ret = input.read();
            while (ret != -1) {
                os.write(ret);
                ret = input.read();
            }
            os.flush();

            uploadedFile = new DefaultUploadedFile(fileItem);
        }
        
        return uploadedFile;
	}

	@Override
	protected void sendingEmailApprovalNotif(ApprovalActivity appActivity)throws Exception {
		
		
	}

	@Override
	@Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public String saveWithApproval(RmbsApplication entity, UploadedFile reimbursmentFile) throws Exception {
		return this.save(entity, reimbursmentFile, Boolean.FALSE, Boolean.FALSE, null);
		
	}

	@Override
	@Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public String saveWithRevised(RmbsApplication entity, UploadedFile reimbursmentFile, Long approvalActivityId) throws Exception {
		return this.save(entity, reimbursmentFile, null, Boolean.TRUE, approvalActivityId);
		
	}	
	
	private String save(RmbsApplication entity, UploadedFile reimbursmentFile, Boolean isBypassApprovalChecking, Boolean isRevised, Long revisedApprActivityId) throws Exception {
		String message = "error";
		
		// check duplicate code
		Long totalDuplicates = rmbsApplicationDao.getTotalByCode(entity.getCode());
		if (totalDuplicates > 0) {
			throw new BussinessException("rmbs_type.error_duplicate_code");
		} 
		//check submission date, based on rmbsSchema
		RmbsSchema rmbsSchema = rmbsSchemaListOfEmpDao.getAllDataByEmpDataId(entity.getEmpData().getId()).get(0).getRmbsSchema();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");      
        Date now = formatter.parse(formatter.format(new Date()));
        Date deadline = new DateTime(now).minusDays(rmbsSchema.getSubmissionDeadline()).toDate();	        
    	if(entity.getApplicationDate().before(deadline)){
    		throw new BussinessException("rmbs_application.error_request_date_exceed");
    	} 
    	//check nominal limit should not exceed	
    	RmbsSchemaListOfType rmbsSchemaListOfType = rmbsSchemaListOfTypeDao.getEntityByPk(new RmbsSchemaListOfTypeId(entity.getRmbsType().getId(), rmbsSchema.getId()));
		if(entity.getNominal().doubleValue() > rmbsSchemaListOfType.getLimitPerClaim()){
    		throw new BussinessException("rmbs_application.error_nominal_exceed");
    	}		
		
		
		//initial
		EmpData empData = empDataDao.getEntiyByPK(entity.getEmpData().getId());
		RmbsType rmbsType = rmbsTypeDao.getEntiyByPK(entity.getRmbsType().getId());
		Currency currency = currencyDao.getEntiyByPK(entity.getCurrency().getId());
		String createdBy = StringUtils.isEmpty(entity.getCreatedBy()) ? UserInfoUtil.getUserName() : entity.getCreatedBy();
        Date createdOn = entity.getCreatedOn() == null ? new Date() : entity.getCreatedOn();       
        
        //set data
        entity.setEmpData(empData);
        entity.setRmbsType(rmbsType);
        entity.setCurrency(currency);
        entity.setApplicationStatus(HRMConstant.RMBS_STATUS_UNDISBURSED);
        entity.setCreatedBy(createdBy);
        entity.setCreatedOn(createdOn);
        
        if(isRevised){
        	//revising process
        	String pendingData = this.getJsonPendingData(entity, reimbursmentFile);
        	this.revised(revisedApprActivityId, pendingData);
        	
        	message = "success_need_approval";
        } else {
	        //start saving data and approval checking
	        ApprovalActivity approvalActivity = this.checkApprovalIfAny(empData, isBypassApprovalChecking);
			if(approvalActivity == null){
				//save entity to DB
				if (reimbursmentFile != null) {
	                InputStream inputStream = reimbursmentFile.getInputstream();
	                byte[] buffer = IOUtils.toByteArray(inputStream);
	                entity.setReceiptAttachment(buffer);
	            }
	            rmbsApplicationDao.save(entity);
	            
	            message = "success_without_approval";
	            
			} else {	
				//save to approval activity
				String pendingData = this.getJsonPendingData(entity, reimbursmentFile);
	            approvalActivity.setPendingData(pendingData);
	            approvalActivity.setTypeSpecific(rmbsType.getId()); //set rmbs type
	            approvalActivityDao.save(approvalActivity);
	
	            //sending email notification
	            this.sendingEmailApprovalNotif(approvalActivity);
	            
	            message = "success_need_approval";
			}
        }
		
		return message; 		
	}
	
	private ApprovalActivity checkApprovalIfAny(EmpData empData, Boolean isBypassApprovalChecking) throws Exception{
		/** check approval process if any,
		 *  return null if no need approval process */
		HrmUser requestUser = hrmUserDao.getByEmpDataId(empData.getId());
        RmbsSchema rmbsSchema = rmbsSchemaListOfEmpDao.getAllDataByEmpDataId(empData.getId()).get(0).getRmbsSchema();
        List<ApprovalDefinition> appDefs = Lambda.extract(rmbsSchema.getApprovalDefinitionRmbsSchemas(), Lambda.on(ApprovalDefinitionRmbsSchema.class).getApprovalDefinition());
        
        return isBypassApprovalChecking ? null : super.checkApprovalProcess(appDefs, requestUser.getUserId());
	}
	
	private String getJsonPendingData(RmbsApplication entity, UploadedFile reimbursmentFile) throws IOException{
		//saving file uploaded temporary
		String uploadPath = null;
        if (reimbursmentFile != null) {
            uploadPath = getUploadPath(Long.parseLong(RandomNumberUtil.getRandomNumber(9)), reimbursmentFile);
            facesIO.transferFile(reimbursmentFile);
            File file = new File(facesIO.getPathUpload() + reimbursmentFile.getFileName());
            file.renameTo(new File(uploadPath));
        }
        
		//parsing object to json 
        Gson gson = JsonUtil.getHibernateEntityGsonBuilder().create();
        JsonParser parser = new JsonParser();
        JsonObject jsonObject = (JsonObject) parser.parse(gson.toJson(entity));
        jsonObject.addProperty("reimbursementFileName", uploadPath);
        
        return gson.toJson(jsonObject);
	}
	
	private String getUploadPath(Long id, UploadedFile documentFile) {
        String extension = StringUtils.substringAfterLast(documentFile.getFileName(), ".");
        String uploadPath = facesIO.getPathUpload() + "reimbursement_" + id + "." + extension;
        return uploadPath;
    }

	@Override
	@Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 30)
	public BigDecimal getTotalNominalByThisMonth(Long empDataId, Long rmbsTypeId) throws Exception {
		DateTime now = new DateTime();
		DateTime startDate = new DateTime(now.getYear(), now.getMonthOfYear(), now.dayOfMonth().getMinimumValue(), 0, 0);
		DateTime endDate = new DateTime(now.getYear(), now.getMonthOfYear(), now.dayOfMonth().getMaximumValue(), 23, 59);
		return rmbsApplicationDao.getTotalNominalByEmpDataIdAndRmbsTypeIdAndDateBetween(empDataId, rmbsTypeId, startDate.toDate() , endDate.toDate());
		
	}

	@Override
	@Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 50)
	public List<EmpData> getListApproverByEmpDataId(Long empDataId) throws IOException {
		RmbsSchema rmbsSchema = rmbsSchemaListOfEmpDao.getAllDataByEmpDataId(empDataId).get(0).getRmbsSchema();
        List<ApprovalDefinition> appDefs = Lambda.extract(rmbsSchema.getApprovalDefinitionRmbsSchemas(), Lambda.on(ApprovalDefinitionRmbsSchema.class).getApprovalDefinition());
        
		return super.getListApproverByListAppDef(appDefs);
	}

	@Override
	@Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 50)
	public List<RmbsApplicationUndisbursedViewModel> getUndisbursedByParam(RmbsApplicationUndisbursedSearchParameter parameter, int firstResult, int maxResults, Order orderable) throws IOException {
		Gson gson = JsonUtil.getHibernateEntityGsonBuilder().create();
		
		List<RmbsApplicationUndisbursedViewModel> listModel = rmbsApplicationDao.getUndisbursedByParam(parameter, firstResult, maxResults, orderable);
		for(RmbsApplicationUndisbursedViewModel model : listModel){
			JsonElement elReimbursment = gson.fromJson(model.getJsonData(), JsonObject.class).get("reimbursementFileName");
			model.setIsHaveAttachment(!elReimbursment.isJsonNull());
			
			RmbsApplication rmbsApplication = gson.fromJson(model.getJsonData(), RmbsApplication.class);
			model.setRmbsApplicationCode(rmbsApplication.getCode());
			model.setNominal(rmbsApplication.getNominal());
		}
		        
		return listModel;
		
	}

	@Override
	@Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 30)
	public Long getTotalUndisbursedByParam(RmbsApplicationUndisbursedSearchParameter parameter) throws IOException {
		return rmbsApplicationDao.getTotalUndisbursedByParam(parameter);
		
	}
	

}
