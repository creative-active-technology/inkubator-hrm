package com.inkubator.hrm.service.impl;

import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateUtils;
import org.hibernate.criterion.Order;
import org.primefaces.json.JSONException;
import org.primefaces.json.JSONObject;
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
import com.inkubator.exception.BussinessException;
import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.dao.ApprovalActivityDao;
import com.inkubator.hrm.dao.HrmUserDao;
import com.inkubator.hrm.dao.RmbsApplicationDao;
import com.inkubator.hrm.dao.RmbsApplicationDisbursementDao;
import com.inkubator.hrm.dao.RmbsDisbursementDao;
import com.inkubator.hrm.dao.TransactionCodeficationDao;
import com.inkubator.hrm.entity.ApprovalActivity;
import com.inkubator.hrm.entity.HrmUser;
import com.inkubator.hrm.entity.RmbsApplication;
import com.inkubator.hrm.entity.RmbsApplicationDisbursement;
import com.inkubator.hrm.entity.RmbsApplicationDisbursementId;
import com.inkubator.hrm.entity.RmbsDisbursement;
import com.inkubator.hrm.entity.TransactionCodefication;
import com.inkubator.hrm.json.util.JsonUtil;
import com.inkubator.hrm.service.RmbsDisbursementService;
import com.inkubator.hrm.util.KodefikasiUtil;
import com.inkubator.securitycore.util.UserInfoUtil;

/**
 *
 * @author rizkykojek
 */
@Service(value = "rmbsDisbursementService")
@Lazy
public class RmbsDisbursementServiceImpl extends BaseApprovalServiceImpl implements RmbsDisbursementService {

	@Autowired
	private RmbsDisbursementDao rmbsDisbursementDao;
	@Autowired
	private RmbsApplicationDisbursementDao rmbsApplicationDisbursementDao;
	@Autowired
	private RmbsApplicationDao rmbsApplicationDao;
	@Autowired
	private TransactionCodeficationDao transactionCodeficationDao;
	@Autowired
	private HrmUserDao hrmUserDao;
	@Autowired
	private ApprovalActivityDao approvalActivityDao;
	
	@Override
	public RmbsDisbursement getEntiyByPK(String id) throws Exception {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.
	}

	@Override
	public RmbsDisbursement getEntiyByPK(Integer id) throws Exception {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.
	}

	@Override
	public RmbsDisbursement getEntiyByPK(Long id) throws Exception {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.
	}

	@Override
	public void save(RmbsDisbursement entity) throws Exception {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.
	}

	@Override
	public void update(RmbsDisbursement entity) throws Exception {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.
	}

	@Override
	public void saveOrUpdate(RmbsDisbursement enntity) throws Exception {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.
	}

	@Override
	public RmbsDisbursement saveData(RmbsDisbursement entity) throws Exception {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.
	}

	@Override
	public RmbsDisbursement updateData(RmbsDisbursement entity)
			throws Exception {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.
	}

	@Override
	public RmbsDisbursement saveOrUpdateData(RmbsDisbursement entity)
			throws Exception {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.
	}

	@Override
	public RmbsDisbursement getEntityByPkIsActive(String id, Integer isActive)
			throws Exception {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.
	}

	@Override
	public RmbsDisbursement getEntityByPkIsActive(String id, Byte isActive)
			throws Exception {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.
	}

	@Override
	public RmbsDisbursement getEntityByPkIsActive(String id, Boolean isActive)
			throws Exception {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.
	}

	@Override
	public RmbsDisbursement getEntityByPkIsActive(Integer id, Integer isActive)
			throws Exception {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.
	}

	@Override
	public RmbsDisbursement getEntityByPkIsActive(Integer id, Byte isActive)
			throws Exception {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.
	}

	@Override
	public RmbsDisbursement getEntityByPkIsActive(Integer id, Boolean isActive)
			throws Exception {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.
	}

	@Override
	public RmbsDisbursement getEntityByPkIsActive(Long id, Integer isActive)
			throws Exception {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.
	}

	@Override
	public RmbsDisbursement getEntityByPkIsActive(Long id, Byte isActive)
			throws Exception {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.
	}

	@Override
	public RmbsDisbursement getEntityByPkIsActive(Long id, Boolean isActive)
			throws Exception {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.
	}

	@Override
	public void delete(RmbsDisbursement entity) throws Exception {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.
	}

	@Override
	public void softDelete(RmbsDisbursement entity) throws Exception {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.
	}

	@Override
	public Long getTotalData() throws Exception {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.
	}

	@Override
	public Long getTotalDataIsActive(Boolean isActive) throws Exception {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.
	}

	@Override
	public Long getTotalDataIsActive(Integer isActive) throws Exception {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.
	}

	@Override
	public Long getTotalDataIsActive(Byte isActive) throws Exception {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.
	}

	@Override
	public List<RmbsDisbursement> getAllData() throws Exception {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.
	}

	@Override
	public List<RmbsDisbursement> getAllData(Boolean isActive) throws Exception {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.
	}

	@Override
	public List<RmbsDisbursement> getAllData(Integer isActive) throws Exception {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.
	}

	@Override
	public List<RmbsDisbursement> getAllData(Byte isActive) throws Exception {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.
	}

	@Override
	public List<RmbsDisbursement> getAllDataPageAble(int firstResult,
			int maxResults, Order order) throws Exception {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.
	}

	@Override
	public List<RmbsDisbursement> getAllDataPageAbleIsActive(int firstResult,
			int maxResults, Order order, Boolean isActive) throws Exception {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.
	}

	@Override
	public List<RmbsDisbursement> getAllDataPageAbleIsActive(int firstResult,
			int maxResults, Order order, Integer isActive) throws Exception {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.
	}

	@Override
	public List<RmbsDisbursement> getAllDataPageAbleIsActive(int firstResult,
			int maxResults, Order order, Byte isActive) throws Exception {
		// TODO Auto-generated method stub
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
        	Gson gson = new GsonBuilder().create();
        	JsonObject jsonObject = (JsonObject) gson.fromJson(appActivity.getPendingData(), JsonObject.class);
        	List<Long> listRmbsApplicationId = gson.fromJson(jsonObject.get("listRmbsApplicationId").getAsString(), new TypeToken<List<Long>>() {}.getType());
            RmbsDisbursement disbursement = this.convertJsonToEntity(appActivity.getPendingData());
            disbursement.setStatus(HRMConstant.RMBS_DISBURSEMENT_STATUS_APPROVED); // set approved status
            disbursement.setApprovalActivityNumber(appActivity.getActivityNumber()); //set approval activity number, for history approval purpose
            
            /** saving to DB */
            this.disbursement(listRmbsApplicationId, disbursement, Boolean.TRUE, disbursement.getStatus());
        }

        //if there is no error, then sending the email notification
        sendingApprovalNotification(appActivity);
	}

	@Override
	public void rejected(long approvalActivityId, String comment) throws Exception {
		Map<String, Object> result = super.rejectedAndCheckNextApproval(approvalActivityId, comment);
        ApprovalActivity appActivity = (ApprovalActivity) result.get("approvalActivity");
        if (StringUtils.equals((String) result.get("isEndOfApprovalProcess"), "true")) {
        	/**
             * kalau status akhir sudah di reject dan tidak ada next approval,
             * berarti langsung insert ke database
             */
        	Gson gson = new GsonBuilder().create();
        	JsonObject jsonObject = (JsonObject) gson.fromJson(appActivity.getPendingData(), JsonObject.class);
        	List<Long> listRmbsApplicationId = gson.fromJson(jsonObject.get("listRmbsApplicationId").getAsString(), new TypeToken<List<Long>>() {}.getType());
            RmbsDisbursement disbursement = this.convertJsonToEntity(appActivity.getPendingData());
            disbursement.setStatus(HRMConstant.RMBS_DISBURSEMENT_STATUS_REJECTED); // set rejected status
            disbursement.setApprovalActivityNumber(appActivity.getActivityNumber()); //set approval activity number, for history approval purpose
            
            /** saving to DB */
            this.disbursement(listRmbsApplicationId, disbursement, Boolean.TRUE, disbursement.getStatus());
        }

        //if there is no error, then sending the email notification
        sendingApprovalNotification(appActivity);
	}

	@Override
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
        	List<Long> listRmbsApplicationId = gson.fromJson(jsonObject.get("listRmbsApplicationId").getAsString(), new TypeToken<List<Long>>() {}.getType());
            RmbsDisbursement disbursement = this.convertJsonToEntity(appActivity.getPendingData());
            disbursement.setStatus(HRMConstant.RMBS_DISBURSEMENT_STATUS_APPROVED); // set approved status
            disbursement.setApprovalActivityNumber(appActivity.getActivityNumber()); //set approval activity number, for history approval purpose
            
            /** saving to DB */
            this.disbursement(listRmbsApplicationId, disbursement, Boolean.TRUE, disbursement.getStatus());
        }

        //if there is no error, then sending the email notification
        sendingApprovalNotification(appActivity);
	}

	@Override
	protected void sendingApprovalNotification(ApprovalActivity appActivity) throws Exception {
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
        
        RmbsDisbursement disbursement = gson.fromJson(appActivity.getPendingData(), RmbsDisbursement.class);
        
        final JSONObject jsonObj = new JSONObject();
        try {
            jsonObj.put("approvalActivityId", appActivity.getId());
            jsonObj.put("ccEmailAddresses", ccEmailAddresses);
            jsonObj.put("locale", appActivity.getLocale());
            jsonObj.put("proposeDate", dateFormat.format(disbursement.getCreatedOn()));
            Date deadline = DateUtils.addDays(appActivity.getCreatedTime(), appActivity.getApprovalDefinition().getDelayTime());  
            jsonObj.put("deadline", dateFormat.format(deadline));

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
	public String disbursementWithApproval(List<Long> listRmbsApplicationId, RmbsDisbursement disbursement) throws Exception {
		disbursement.setStatus(HRMConstant.RMBS_DISBURSEMENT_STATUS_APPROVED); // set default status
		return this.disbursement(listRmbsApplicationId, disbursement, Boolean.FALSE, disbursement.getStatus());
	}

	@Override
	@Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public String disbursementWithRevised(List<Long> listRmbsApplicationId, RmbsDisbursement disbursement, Long approvalActivityId) throws Exception {
		String message = "error";
		
		//start binding and validation
		disbursement = this.entityBindingAndValidation(listRmbsApplicationId, disbursement);
		
		/** proceed of revising data */
    	String pendingData = this.convertToJsonData(listRmbsApplicationId, disbursement);
    	super.revised(approvalActivityId, pendingData);
    	
    	message = "success_need_approval";
		
		return message;
	}
	
	
	private String disbursement(List<Long> listRmbsApplicationId, RmbsDisbursement disbursement, Boolean isBypassApprovalChecking, Integer approvalStatus) throws Exception {
		String message = "error";
		
		//start binding and validation
		disbursement = this.entityBindingAndValidation(listRmbsApplicationId,disbursement);				
		
		/** start approval checking and saving data also */
		ApprovalActivity approvalActivity = isBypassApprovalChecking ? null : super.checkApprovalProcess(HRMConstant.REIMBURSEMENT_DISBURSEMENT, UserInfoUtil.getUserName());
		if(approvalActivity == null){				
			/** proceed of saving data(entity) to DB */
			//generate number of code
			String code = this.generateDisbursedReimbursementNumber();	        
			disbursement.setCode(code);            
            
			rmbsDisbursementDao.save(disbursement);			
			if(approvalStatus == HRMConstant.RMBS_DISBURSEMENT_STATUS_APPROVED){
				//update rmbs application dan relasinya, hanya jika di approved
				this.updateApplicationAndRelations(listRmbsApplicationId, disbursement);
			}
			
            message = "success_without_approval";
            
		} else {	
			/** proceed of saving approval activity */
			String pendingData = this.convertToJsonData(listRmbsApplicationId, disbursement);
            approvalActivity.setPendingData(pendingData);
            approvalActivityDao.save(approvalActivity);

            //sending email notification
            this.sendingApprovalNotification(approvalActivity);
            
            message = "success_need_approval";
		}    
		
		return message;
	}
	
	private String convertToJsonData(List<Long> listRmbsApplicationId, RmbsDisbursement disbursement) throws IOException{        
		/** parsing object to json */ 
        Gson gson = JsonUtil.getHibernateEntityGsonBuilder().create();
        JsonParser parser = new JsonParser();
        JsonObject jsonObject = (JsonObject) parser.parse(gson.toJson(disbursement));
        String jsonIds = gson.toJson(listRmbsApplicationId.toArray(new Long[listRmbsApplicationId.size()])).toString();
        jsonObject.addProperty("listRmbsApplicationId", jsonIds);
        
        return gson.toJson(jsonObject);
	}
	
	private RmbsDisbursement convertJsonToEntity(String json){
		Gson gson = JsonUtil.getHibernateEntityGsonBuilder().create();
		RmbsDisbursement entity = gson.fromJson(json, RmbsDisbursement.class);
        return entity;
	}
	
	private RmbsDisbursement entityBindingAndValidation(List<Long> listRmbsApplicationId, RmbsDisbursement disbursement) throws BussinessException {
		if(listRmbsApplicationId.isEmpty()){
			throw new BussinessException("rmbs_disbursement.error_no_application_selected");
		}
				
		String createdBy = StringUtils.isEmpty(disbursement.getCreatedBy()) ? UserInfoUtil.getUserName() : disbursement.getCreatedBy();
        Date createdOn = disbursement.getCreatedOn() == null ? new Date() : disbursement.getCreatedOn();
		disbursement.setCreatedBy(createdBy);
		disbursement.setCreatedOn(createdOn);
		
		return disbursement;
	}

	private void updateApplicationAndRelations(List<Long> listRmbsApplicationId, RmbsDisbursement disbursement) throws BussinessException{
		for(Long id : listRmbsApplicationId){
			RmbsApplication application = rmbsApplicationDao.getEntiyByPK(id);
			
			/** Jika status (bukan undisbursed), artinya sudah berubah karena sudah di eksekusi oleh user yang lain */
			if(application.getApplicationStatus() != HRMConstant.RMBS_APPLICATION_STATUS_UNDISBURSED){
				throw new BussinessException("rmbs_disbursement.error_application_status_is_not_valid");
			}
			
			application.setApplicationStatus(HRMConstant.RMBS_APPLICATION_STATUS_DISBURSED);
			application.setUpdatedBy(UserInfoUtil.getUserName());
			application.setUpdatedOn(new Date());
			rmbsApplicationDao.update(application);
			
			RmbsApplicationDisbursement rmbsApplicationDisbursement = new RmbsApplicationDisbursement();
			rmbsApplicationDisbursement.setId(new RmbsApplicationDisbursementId(disbursement.getId(), application.getId()));
			rmbsApplicationDisbursement.setRmbsApplication(application);
			rmbsApplicationDisbursement.setRmbsDisbursement(disbursement);
			rmbsApplicationDisbursementDao.save(rmbsApplicationDisbursement);
		}	
	}
	
	private String generateDisbursedReimbursementNumber(){
		/** generate disbursed number form codification, from reimbursement module */
		TransactionCodefication transactionCodefication = transactionCodeficationDao.getEntityByModulCode(HRMConstant.REIMBURSEMENT_DISBURSED_KODE);
        Long currentMaxId = rmbsDisbursementDao.getCurrentMaxId();
        currentMaxId = currentMaxId != null ? currentMaxId : 0;
        String nomor  = KodefikasiUtil.getKodefikasi(((int)currentMaxId.longValue()), transactionCodefication.getCode());
        return nomor;
	}

	@Override
	protected String getDetailSmsContentOfActivity(ApprovalActivity appActivity) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yy");
		StringBuffer detail = new StringBuffer();
		HrmUser requester = hrmUserDao.getByUserId(appActivity.getRequestBy());
		RmbsDisbursement entity = this.convertJsonToEntity(appActivity.getPendingData());
		
		detail.append("Pengajuan pencairan penggantian oleh " + requester.getEmpData().getBioData().getFullName() + ". ");
		detail.append("Tanggal pencairan  " + dateFormat.format(entity.getDisbursementDate()) + ". ");
		detail.append("Periode " + dateFormat.format(entity.getPayrollPeriodDate()));
		return detail.toString();
	}

}
