package com.inkubator.hrm.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.faces.context.ExternalContext;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import org.apache.commons.fileupload.disk.DiskFileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.hibernate.criterion.Order;
import org.joda.time.DateTime;
import org.primefaces.json.JSONException;
import org.primefaces.json.JSONObject;
import org.primefaces.model.DefaultUploadedFile;
import org.primefaces.model.UploadedFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import ch.lambdaj.Lambda;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.inkubator.exception.BussinessException;
import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.dao.ApprovalActivityDao;
import com.inkubator.hrm.dao.CurrencyDao;
import com.inkubator.hrm.dao.EmpDataDao;
import com.inkubator.hrm.dao.HrmUserDao;
import com.inkubator.hrm.dao.RmbsApplicationDao;
import com.inkubator.hrm.dao.RmbsCancelationDao;
import com.inkubator.hrm.dao.RmbsSchemaListOfEmpDao;
import com.inkubator.hrm.dao.RmbsSchemaListOfTypeDao;
import com.inkubator.hrm.dao.RmbsTypeDao;
import com.inkubator.hrm.dao.TransactionCodeficationDao;
import com.inkubator.hrm.entity.ApprovalActivity;
import com.inkubator.hrm.entity.ApprovalDefinition;
import com.inkubator.hrm.entity.ApprovalDefinitionRmbsSchema;
import com.inkubator.hrm.entity.Currency;
import com.inkubator.hrm.entity.EmpData;
import com.inkubator.hrm.entity.HrmUser;
import com.inkubator.hrm.entity.RmbsApplication;
import com.inkubator.hrm.entity.RmbsCancelation;
import com.inkubator.hrm.entity.RmbsSchema;
import com.inkubator.hrm.entity.RmbsSchemaListOfType;
import com.inkubator.hrm.entity.RmbsSchemaListOfTypeId;
import com.inkubator.hrm.entity.RmbsType;
import com.inkubator.hrm.entity.TransactionCodefication;
import com.inkubator.hrm.json.util.JsonUtil;
import com.inkubator.hrm.service.RmbsApplicationService;
import com.inkubator.hrm.util.KodefikasiUtil;
import com.inkubator.hrm.web.model.RmbsApplicationUndisbursedViewModel;
import com.inkubator.hrm.web.model.RmbsHistoryViewModel;
import com.inkubator.hrm.web.search.RmbsApplicationUndisbursedSearchParameter;
import com.inkubator.securitycore.util.UserInfoUtil;
import com.inkubator.webcore.util.FacesIO;
import com.inkubator.webcore.util.FacesUtil;

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
	@Autowired
	private TransactionCodeficationDao transactionCodeficationDao;
	@Autowired
	private RmbsCancelationDao rmbsCancelationDao;	
	
	@Override
	public RmbsApplication getEntiyByPK(String id) throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public RmbsApplication getEntiyByPK(Integer id) throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	@Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 30)
	public RmbsApplication getEntiyByPK(Long id) throws Exception {
		return rmbsApplicationDao.getEntiyByPK(id);

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
            RmbsApplication entity = this.convertJsonToEntity(appActivity.getPendingData());
    		entity.setApplicationStatus(HRMConstant.RMBS_APPLICATION_STATUS_UNDISBURSED); // set approved application status
            entity.setApprovalActivityNumber(appActivity.getActivityNumber()); //set approval activity number, for history approval purpose

            /** convert to UploadedFile before saving */
            UploadedFile uploadedFile = this.convertFileToUploadedFile(appActivity.getPendingData());
            
            /** saving to DB */
            this.save(entity, uploadedFile, Boolean.TRUE);
        }

        //if there is no error, then sending the email notification
        sendingApprovalNotification(appActivity);
		
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
            RmbsApplication entity = this.convertJsonToEntity(appActivity.getPendingData());
            entity.setApplicationStatus(HRMConstant.RMBS_APPLICATION_STATUS_REJECTED); //set rejected application status
            entity.setApprovalActivityNumber(appActivity.getActivityNumber());  //set approval activity number, for history approval purpose
            
            /** convert to UploadedFile before saving */
            UploadedFile uploadedFile = this.convertFileToUploadedFile(appActivity.getPendingData());
            
            /** saving to DB */
            this.save(entity, uploadedFile, Boolean.TRUE);
        }

        //if there is no error, then sending the email notification
        sendingApprovalNotification(appActivity);
		
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
            RmbsApplication entity = this.convertJsonToEntity(appActivity.getPendingData());
            entity.setApplicationStatus(HRMConstant.RMBS_APPLICATION_STATUS_UNDISBURSED); // set approved application status
            entity.setApprovalActivityNumber(appActivity.getActivityNumber());  //set approval activity number, for history approval purpose

            /** convert to UploadedFile before saving */
            UploadedFile uploadedFile = this.convertFileToUploadedFile(appActivity.getPendingData());
            
            /** saving to DB */
            this.save(entity, uploadedFile, Boolean.TRUE);
        }

        //if there is no error, then sending the email notification
        sendingApprovalNotification(appActivity);
		
	}
	
	@Override
	@Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void cancelled(long approvalActivityId, RmbsCancelation cancelation) throws Exception {		
		ApprovalActivity appActivity = approvalActivityDao.getEntiyByPK(approvalActivityId);
		
		//check if there is already approved by someone(approver), then it shouldn't be cancelled
    	if(approvalActivityDao.isAlreadyHaveApprovedStatus(appActivity.getActivityNumber())){
    		throw new BussinessException("approval.error_cancelled_already_approved");
    	}
    	
    	/** convert to UploadedFile before saving */
        UploadedFile uploadedFile = this.convertFileToUploadedFile(appActivity.getPendingData());
        
        /** saving entity RmbsApplication to DB */
    	RmbsApplication application = this.convertJsonToEntity(appActivity.getPendingData());
    	application.setApplicationStatus(HRMConstant.RMBS_APPLICATION_STATUS_CANCELED); // set cancelled application status
    	application.setApprovalActivityNumber(appActivity.getActivityNumber());  //set approval activity number, for history approval purpose
        this.save(application, uploadedFile, Boolean.TRUE);
    	
        /** saving entity RmbsCancelation to DB */
        this.savingCancelation(cancelation, application);
        
        /** cancel this approval activity and saving log approver history */
        RmbsSchema rmbsSchema = rmbsSchemaListOfEmpDao.getAllDataByEmpDataId(application.getEmpData().getId()).get(0).getRmbsSchema();
        List<ApprovalDefinition> appDefs = Lambda.extract(rmbsSchema.getApprovalDefinitionRmbsSchemas(), Lambda.on(ApprovalDefinitionRmbsSchema.class).getApprovalDefinition());        
    	super.cancelled(approvalActivityId, cancelation.getReason(), appDefs);
	}
	
	private void savingCancelation(RmbsCancelation cancelation, RmbsApplication application) throws BussinessException{
		// check duplicate code
     	Long totalDuplicates = rmbsCancelationDao.getTotalByCode(cancelation.getCode());
     	if (totalDuplicates > 0) {
     		throw new BussinessException("rmbs_application.error_duplicate_number");
     	}
     	
     	//generate number of code
		String nomor = this.generateCancelationReimbursementNumber();	        
		
		cancelation.setCode(nomor);
     	cancelation.setRmbsApplication(application);
     	cancelation.setCreatedBy(UserInfoUtil.getUserName());
     	cancelation.setCreatedOn(new Date());
     	rmbsCancelationDao.save(cancelation);
	}
	
	@Override
	public UploadedFile convertFileToUploadedFile(String pendingData) {
		UploadedFile uploadedFile = null;
		
		try {
			/** converting process from file physics (which is get the path file from json) to UploadedFile object*/
			Gson gson = JsonUtil.getHibernateEntityGsonBuilder().create();
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
		} catch (IOException e) {
			LOGGER.error("Error", e);
		}
        
        return uploadedFile;
	}

	@Override
	protected void sendingApprovalNotification(ApprovalActivity appActivity)throws Exception {
		//send sms notification to approver if need approval OR
        //send sms notification to requester if need revision
		super.sendApprovalSmsnotif(appActivity);
		
		//initialization
        Gson gson = JsonUtil.getHibernateEntityGsonBuilder().create();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMMM-yyyy", new Locale(appActivity.getLocale()));
        DecimalFormat decimalFormat = new DecimalFormat("###,###");
        
        //get all sendCC email address on status approve OR reject
        List<String> ccEmailAddresses = new ArrayList<String>();
        if ((appActivity.getApprovalStatus() == HRMConstant.APPROVAL_STATUS_APPROVED) || (appActivity.getApprovalStatus() == HRMConstant.APPROVAL_STATUS_REJECTED)) {
            ccEmailAddresses = super.getCcEmailAddressesOnApproveOrReject(appActivity);
        }
        
        RmbsApplication application = gson.fromJson(appActivity.getPendingData(), RmbsApplication.class);
        RmbsType rmbsType = rmbsTypeDao.getEntiyByPK(application.getRmbsType().getId());
        
        final JSONObject jsonObj = new JSONObject();
        try {
            jsonObj.put("approvalActivityId", appActivity.getId());
            jsonObj.put("ccEmailAddresses", ccEmailAddresses);
            jsonObj.put("locale", appActivity.getLocale());
            jsonObj.put("proposeDate", dateFormat.format(application.getCreatedOn()));
            jsonObj.put("reimbursementType", rmbsType.getName());
            jsonObj.put("applicationDate", dateFormat.format(application.getApplicationDate()));
            jsonObj.put("nominal", decimalFormat.format(application.getNominal()));
            Date deadline = DateUtils.addDays(appActivity.getCreatedTime(), appActivity.getApprovalDefinition().getDelayTime());  
            jsonObj.put("deadline", dateFormat.format(deadline));
            jsonObj.put("urlLinkToApprove", FacesUtil.getRequest().getContextPath() + "" + HRMConstant.REIMBURSMENT_APPROVAL_PAGE + "" +"?faces-redirect=true&execution=e" + appActivity.getId());

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
	public String saveWithApproval(RmbsApplication entity, UploadedFile reimbursmentFile) throws Exception {
		
		entity.setApplicationStatus(HRMConstant.RMBS_APPLICATION_STATUS_UNDISBURSED); // set default application status
		return this.save(entity, reimbursmentFile, Boolean.FALSE);
		
	}

	@Override
	@Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public String saveWithRevised(RmbsApplication entity, UploadedFile reimbursmentFile, Long approvalActivityId) throws Exception {
		String message = "error";
		
		//start binding and validation
		entity = this.entityBindingAndValidation(entity);
		
		/** proceed of revising data */
    	String pendingData = this.convertToJsonData(entity, reimbursmentFile);
    	this.revised(approvalActivityId, pendingData);
    	
    	message = "success_need_approval";
		
		return message;
		
	}	
	
	private String save(RmbsApplication entity, UploadedFile reimbursmentFile, Boolean isBypassApprovalChecking) throws Exception {
		String message = "error";
        
		//start binding and validation
		entity = this.entityBindingAndValidation(entity);
		
		/** start approval checking and saving data also */
        ApprovalActivity approvalActivity = this.checkApprovalIfAny(entity.getEmpData(), isBypassApprovalChecking);
		if(approvalActivity == null){				
			/** proceed of saving data(entity) to DB */
			
			//generate number of code
			String nomor = this.generateReimbursementNumber();	        
            entity.setCode(nomor);
            
			//set reimbursement file to blob
			if (reimbursmentFile != null) {
                InputStream inputStream = reimbursmentFile.getInputstream();
                byte[] buffer = IOUtils.toByteArray(inputStream);
                entity.setReceiptAttachment(buffer);
                String extension = StringUtils.substringAfterLast(reimbursmentFile.getFileName(), ".");
                entity.setReceiptAttachmentName(entity.getCode()+"."+extension);
            }
            
	        rmbsApplicationDao.save(entity);
            
            message = "success_without_approval";
            
		} else {	
			/** proceed of saving approval activity */
			String pendingData = this.convertToJsonData(entity, reimbursmentFile);
            approvalActivity.setPendingData(pendingData);
            approvalActivity.setTypeSpecific(entity.getRmbsType().getId()); //set rmbs type
            approvalActivityDao.save(approvalActivity);

            //sending email notification
            this.sendingApprovalNotification(approvalActivity);
            
            message = "success_need_approval";
		}    
		
		return message; 		
	}
	
	private RmbsApplication entityBindingAndValidation(RmbsApplication entity) throws Exception{
		
		//initial
		EmpData empData = empDataDao.getEntiyByPK(entity.getEmpData().getId());
		RmbsType rmbsType = rmbsTypeDao.getEntiyByPK(entity.getRmbsType().getId());
		Currency currency = currencyDao.getEntiyByPK(entity.getCurrency().getId());
		String createdBy = StringUtils.isEmpty(entity.getCreatedBy()) ? UserInfoUtil.getUserName() : entity.getCreatedBy();
        Date createdOn = entity.getCreatedOn() == null ? new Date() : entity.getCreatedOn();		
		
		// check duplicate code
		Long totalDuplicates = rmbsApplicationDao.getTotalByCode(entity.getCode());
		if (totalDuplicates > 0) {
			throw new BussinessException("rmbs_application.error_duplicate_number");
		} 
		//check submission date, based on rmbsSchema
		RmbsSchema rmbsSchema = rmbsSchemaListOfEmpDao.getAllDataByEmpDataId(entity.getEmpData().getId()).get(0).getRmbsSchema();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");      
        Date currentWhenCreated = formatter.parse(formatter.format(createdOn));
        Date deadline = new DateTime(currentWhenCreated).minusDays(rmbsSchema.getSubmissionDeadline()).toDate();	        
    	if(entity.getApplicationDate().before(deadline)){
    		throw new BussinessException("rmbs_application.error_request_date_exceed");
    	} 
    	//check nominal limit should not exceed	
    	RmbsSchemaListOfType rmbsSchemaListOfType = rmbsSchemaListOfTypeDao.getEntityByPk(new RmbsSchemaListOfTypeId(entity.getRmbsType().getId(), rmbsSchema.getId()));
		if(entity.getNominal().doubleValue() > rmbsSchemaListOfType.getLimitPerClaim()){
    		throw new BussinessException("rmbs_application.error_nominal_exceed");
    	}
		//check total nominal this month + nominal, should not exceed maxPerMonth	
    	BigDecimal totalPerMonth = this.getTotalNominalForOneMonth(entity.getApplicationDate(), entity.getEmpData().getId(), rmbsType.getId());
		if(entity.getNominal().add(totalPerMonth).doubleValue() > rmbsSchemaListOfType.getMaxPerMonth()){
    		throw new BussinessException("rmbs_application.error_nominal_per_month_exceed");
    	}
        
        //set data        
        entity.setEmpData(empData);
        entity.setRmbsType(rmbsType);
        entity.setCurrency(currency);        
        entity.setCreatedBy(createdBy);
        entity.setCreatedOn(createdOn);
        
        return entity;
	}
	
	private String generateReimbursementNumber(){
		/** generate number form codification, from reimbursement module */
		TransactionCodefication transactionCodefication = transactionCodeficationDao.getEntityByModulCode(HRMConstant.REIMBURSEMENT_KODE);
        Long currentMaxId = rmbsApplicationDao.getCurrentMaxId();
        currentMaxId = currentMaxId != null ? currentMaxId : 0;
        String nomor  = KodefikasiUtil.getKodefikasi(((int)currentMaxId.longValue()), transactionCodefication.getCode());
        return nomor;
	}
	
	private String generateCancelationReimbursementNumber(){
		/** generate cancelation number form codification, from reimbursement module */
		TransactionCodefication transactionCodefication = transactionCodeficationDao.getEntityByModulCode(HRMConstant.REIMBURSEMENT_CANCEL_KODE);
        Long currentMaxId = rmbsCancelationDao.getCurrentMaxId();
        currentMaxId = currentMaxId != null ? currentMaxId : 0;
        String nomor  = KodefikasiUtil.getKodefikasi(((int)currentMaxId.longValue()), transactionCodefication.getCode());
        return nomor;
	}
	
	private ApprovalActivity checkApprovalIfAny(EmpData empData, Boolean isBypassApprovalChecking) throws Exception{
		/** check approval process if any,
		 *  return null if no need approval process */
		HrmUser requestUser = hrmUserDao.getByEmpDataId(empData.getId());
        RmbsSchema rmbsSchema = rmbsSchemaListOfEmpDao.getAllDataByEmpDataId(empData.getId()).get(0).getRmbsSchema();
        List<ApprovalDefinition> appDefs = Lambda.extract(rmbsSchema.getApprovalDefinitionRmbsSchemas(), Lambda.on(ApprovalDefinitionRmbsSchema.class).getApprovalDefinition());
        
        return isBypassApprovalChecking ? null : super.checkApprovalProcess(appDefs, requestUser.getUserId());
	}
	
	private String convertToJsonData(RmbsApplication entity, UploadedFile reimbursmentFile) throws IOException{
		//saving file uploaded temporary
		String uploadPath = null;
        if (reimbursmentFile != null) {
        	uploadPath = getUploadPath(entity.getCode(), reimbursmentFile);
        	
        	//remove old file        	
            File oldFile = new File(uploadPath);            
            FileUtils.deleteQuietly(oldFile);
            
            //added new file
            facesIO.transferFile(reimbursmentFile);
            File file = new File(facesIO.getPathUpload() + reimbursmentFile.getFileName());
            file.renameTo(new File(uploadPath));
        }
        
		//parsing object to json 
        Gson gson = JsonUtil.getHibernateEntityGsonBuilder().create();
        JsonParser parser = new JsonParser();
        JsonObject jsonObject = (JsonObject) parser.parse(gson.toJson(entity));
        jsonObject.addProperty("reimbursementFileName", uploadPath);
        jsonObject.addProperty(HRMConstant.CONTEXT_PATH, FacesUtil.getRequest().getContextPath());
        
        return gson.toJson(jsonObject);
	}
	
	private RmbsApplication convertJsonToEntity(String json){
		Gson gson = JsonUtil.getHibernateEntityGsonBuilder().create();
        RmbsApplication entity = gson.fromJson(json, RmbsApplication.class);
        return entity;
	}
	
	private String getUploadPath(String code, UploadedFile documentFile) {
        String extension = StringUtils.substringAfterLast(documentFile.getFileName(), ".");
        String uploadPath = facesIO.getPathUpload() + "reimbursement_" + code + "." + extension;
        return uploadPath;
    }

	@Override
	@Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 30)
	public BigDecimal getTotalNominalForOneMonth(Date date, Long empDataId, Long rmbsTypeId) throws Exception {
		BigDecimal total = new BigDecimal(0);
		
		if(date != null && empDataId != null && rmbsTypeId != null) {
			DateTime dt = new DateTime(date);
			DateTime startDate = new DateTime(dt.getYear(), dt.getMonthOfYear(), dt.dayOfMonth().getMinimumValue(), 0, 0);
			DateTime endDate = new DateTime(dt.getYear(), dt.getMonthOfYear(), dt.dayOfMonth().getMaximumValue(), 23, 59);
			
			/** getTotal Nominal yg sudah disetujui */
			BigDecimal nominalAlreadyApproved = rmbsApplicationDao.getTotalNominalByEmpDataIdAndRmbsTypeIdAndDateBetween(empDataId, rmbsTypeId, startDate.toDate() , endDate.toDate());
			
			/** getTotal Nominal yg masih menunggu persetujuan */
			BigDecimal nominalWaitingApproval = new BigDecimal(0);
			HrmUser requester = hrmUserDao.getByEmpDataId(empDataId);
			List<ApprovalActivity> listApprovalActivities = approvalActivityDao.getAllDataNotApprovedYet(requester.getUserId(), HRMConstant.REIMBURSEMENT);
			for(ApprovalActivity appActivity : listApprovalActivities){
				RmbsApplication application = this.convertJsonToEntity(appActivity.getPendingData());
				
				/** dapatkan nominal berdasarkan type nya
				 * DAN hanya yang diantara startDate dan endDate*/
				dt = new DateTime(application.getApplicationDate());
				if( application.getRmbsType().getId().intValue() == rmbsTypeId &&
						(startDate.isBefore(dt) || DateUtils.isSameDay(startDate.toDate(), dt.toDate()))  && 
						(endDate.isAfter(dt) ||  DateUtils.isSameDay(endDate.toDate(), dt.toDate()))) {
					nominalWaitingApproval = nominalWaitingApproval.add(application.getNominal());
				}
			}
			
			/** jumlahkan total yg sudah disetujui dan yang masih menunggu persetujuan */
			total = nominalAlreadyApproved.add(nominalWaitingApproval);
		}
		
		
		return total;
	}

	@Override
	@Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 50)
	public List<EmpData> getListApproverByEmpDataId(Long empDataId) throws Exception {
		HrmUser requester = hrmUserDao.getByEmpDataId(empDataId);
		RmbsSchema rmbsSchema = rmbsSchemaListOfEmpDao.getAllDataByEmpDataId(empDataId).get(0).getRmbsSchema();
        List<ApprovalDefinition> appDefs = Lambda.extract(rmbsSchema.getApprovalDefinitionRmbsSchemas(), Lambda.on(ApprovalDefinitionRmbsSchema.class).getApprovalDefinition());
        
		return super.getListApproverByListAppDef(appDefs, requester.getUserId());
	}

	@Override
	@Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 50)
	public List<RmbsApplicationUndisbursedViewModel> getUndisbursedActivityByParam(RmbsApplicationUndisbursedSearchParameter parameter, int firstResult, int maxResults, Order orderable) throws Exception {
		Gson gson = JsonUtil.getHibernateEntityGsonBuilder().create();
		
		List<RmbsApplicationUndisbursedViewModel> listModel = rmbsApplicationDao.getUndisbursedActivityByParam(parameter, firstResult, maxResults, orderable);
		for(RmbsApplicationUndisbursedViewModel model : listModel){
			JsonElement elReimbursment = gson.fromJson(model.getJsonData(), JsonObject.class).get("reimbursementFileName");
			model.setIsHaveAttachment(!elReimbursment.isJsonNull());
			
			RmbsApplication rmbsApplication = gson.fromJson(model.getJsonData(), RmbsApplication.class);			
			model.setNominal(rmbsApplication.getNominal());			
			if(StringUtils.isEmpty(model.getRmbsApplicationCode())){
				model.setRmbsApplicationCode(rmbsApplication.getCode());
			}
		}
		        
		return listModel;
		
	}

	@Override
	@Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 30)
	public Long getTotalUndisbursedActivityByParam(RmbsApplicationUndisbursedSearchParameter parameter) throws Exception {
		return rmbsApplicationDao.getTotalUndisbursedActivityByParam(parameter);
		
	}

	@Override
	@Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 30)
	public RmbsApplication getEntityByPkWithDetail(Long id) {
		return rmbsApplicationDao.getEntityByPkWithDetail(id);
	}

	@Override
	@Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 50)
	public HashMap<Long, String> getAllDataNotApprovedYet(String userId) throws Exception {
		HashMap<Long, String> map =  new HashMap<Long, String>();
		Gson gson = JsonUtil.getHibernateEntityGsonBuilder().create();
		
		List<ApprovalActivity> appActivities = approvalActivityDao.getAllDataNotApprovedYet(userId, HRMConstant.REIMBURSEMENT);
		for(ApprovalActivity app : appActivities){
			RmbsApplication rmbsApplication = gson.fromJson(app.getPendingData(), RmbsApplication.class);
			map.put(app.getId(), rmbsApplication.getCode());
		}	
		
		return map;
	}

	@Override
	@Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 50)
	public List<RmbsApplication> getUndisbursedByParam(int firstResult, int maxResults, Order orderable) {
		return rmbsApplicationDao.getUndisbursedByParam(firstResult, maxResults, orderable);
	}

	@Override
	@Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 30)
	public Long getTotalUndisbursedByParam() {
		return rmbsApplicationDao.getTotalUndisbursedByParam();
	}

	@Override
	protected String getDetailSmsContentOfActivity(ApprovalActivity appActivity) {
		DecimalFormat decimalFormat = new DecimalFormat("###,###");
		StringBuffer detail = new StringBuffer();
		HrmUser requester = hrmUserDao.getByUserId(appActivity.getRequestBy());
		RmbsApplication entity = this.convertJsonToEntity(appActivity.getPendingData());
		
		detail.append("Pengajuan penggantian oleh " + requester.getEmpData().getBioData().getFullName() + ". ");
		detail.append("Tipe " + entity.getRmbsType().getName() + ". ");
		detail.append("Sejumlah " + decimalFormat.format(entity.getNominal()) + ", mata uang " + entity.getCurrency().getName());
		return detail.toString();
	}

	@Override
	@Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 50)
	public List<RmbsApplication> getReimbursementHistoryByParam(Long empDataId, int first, int pageSize, Order orderable) {
		List<RmbsApplication> listApplications = rmbsApplicationDao.getReimbursementHistoryByParam(empDataId, first, pageSize, orderable);
		for(RmbsApplication application : listApplications) {
			if(application.getRmbsApplicationDisbursements().size() != 0){
				Date disbursementDate = application.getRmbsApplicationDisbursements().iterator().next().getRmbsDisbursement().getDisbursementDate();
				application.setDisbursementDate(disbursementDate);
			}
		}
		return listApplications;
	}

	@Override
	@Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 30)
	public Long getTotalReimbursementHistoryByParam(Long empDataId) {
		return rmbsApplicationDao.getTotalReimbursementHistoryByParam(empDataId);
	}

	@Override
	@Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 30)
	public List<RmbsHistoryViewModel> getAllDataHistoryThisYear(Long empDataId) {
		DateTime now = DateTime.now();
		int month = now.getMonthOfYear();
		DateTime startYear = new DateTime(now.getYear(), 1, 1, 0, 0);
		DateTime endYear = new DateTime(now.getYear(), 12, 31, 23, 59);
		
		List<RmbsHistoryViewModel> listViewModel = rmbsApplicationDao.getAllDataHistoryBetweenDate(empDataId, startYear.toDate(), endYear.toDate());
		
		for(RmbsHistoryViewModel viewModel : listViewModel){
			RmbsSchema rmbsSchema = rmbsSchemaListOfEmpDao.getAllDataByEmpDataId(empDataId).get(0).getRmbsSchema();
			RmbsSchemaListOfType rmbsSchemaListOfType = rmbsSchemaListOfTypeDao.getEntityByPk(new RmbsSchemaListOfTypeId(viewModel.getRmbsTypeId(), rmbsSchema.getId()));
			double maxNominal = 0L;
			if(rmbsSchemaListOfType != null){
				maxNominal = rmbsSchemaListOfType.getMaxPerMonth() * month;
			}
			viewModel.setMaxNominal(new BigDecimal(maxNominal));
		}
		
		return listViewModel;
	}
	
}
