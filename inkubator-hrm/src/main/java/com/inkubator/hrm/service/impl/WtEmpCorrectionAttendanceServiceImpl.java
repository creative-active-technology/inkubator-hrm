
package com.inkubator.hrm.service.impl;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.hamcrest.Matchers;
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

import ch.lambdaj.Lambda;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.inkubator.exception.BussinessException;
import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.dao.ApprovalActivityDao;
import com.inkubator.hrm.dao.ApprovalDefinitionDao;
import com.inkubator.hrm.dao.EmpDataDao;
import com.inkubator.hrm.dao.HrmUserDao;
import com.inkubator.hrm.dao.TransactionCodeficationDao;
import com.inkubator.hrm.dao.WtEmpCorrectionAttendanceDao;
import com.inkubator.hrm.entity.ApprovalActivity;
import com.inkubator.hrm.entity.ApprovalDefinition;
import com.inkubator.hrm.entity.EmpData;
import com.inkubator.hrm.entity.HrmUser;
import com.inkubator.hrm.entity.TransactionCodefication;
import com.inkubator.hrm.entity.WtEmpCorrectionAttendance;
import com.inkubator.hrm.entity.WtEmpCorrectionAttendanceDetail;
import com.inkubator.hrm.json.util.DateJsonDeserializer;
import com.inkubator.hrm.json.util.JsonUtil;
import com.inkubator.hrm.service.WtEmpCorrectionAttendanceService;
import com.inkubator.hrm.util.KodefikasiUtil;
import com.inkubator.hrm.web.search.EmpCorrectionAttendanceSearchParameter;
import com.inkubator.securitycore.util.UserInfoUtil;
import com.inkubator.webcore.util.FacesUtil;

/**
 *
 * @author rizkykojek
 */
@Service(value = "wtEmpCorrectionAttendanceService")
@Lazy
public class WtEmpCorrectionAttendanceServiceImpl extends BaseApprovalServiceImpl implements WtEmpCorrectionAttendanceService {

	@Autowired
	private TransactionCodeficationDao transactionCodeficationDao;
	@Autowired
	private WtEmpCorrectionAttendanceDao wtEmpCorrectionAttendanceDao;
	@Autowired
	private HrmUserDao hrmUserDao;
	@Autowired
	private ApprovalActivityDao approvalActivityDao;
	@Autowired
	private ApprovalDefinitionDao approvalDefinitionDao;
	@Autowired
	private EmpDataDao empDataDao;
	
	@Override
	public WtEmpCorrectionAttendance getEntiyByPK(String id) throws Exception {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.
	}

	@Override
	public WtEmpCorrectionAttendance getEntiyByPK(Integer id) throws Exception {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.
	}

	@Override
	public WtEmpCorrectionAttendance getEntiyByPK(Long id) throws Exception {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.
	}

	@Override
	public void save(WtEmpCorrectionAttendance entity) throws Exception {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.
	}

	@Override
	public void update(WtEmpCorrectionAttendance entity) throws Exception {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.
	}

	@Override
	public void saveOrUpdate(WtEmpCorrectionAttendance enntity)
			throws Exception {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.
	}

	@Override
	public WtEmpCorrectionAttendance saveData(WtEmpCorrectionAttendance entity)
			throws Exception {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.
	}

	@Override
	public WtEmpCorrectionAttendance updateData(WtEmpCorrectionAttendance entity)
			throws Exception {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.
	}

	@Override
	public WtEmpCorrectionAttendance saveOrUpdateData(
			WtEmpCorrectionAttendance entity) throws Exception {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.
	}

	@Override
	public WtEmpCorrectionAttendance getEntityByPkIsActive(String id,
			Integer isActive) throws Exception {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.
	}

	@Override
	public WtEmpCorrectionAttendance getEntityByPkIsActive(String id,
			Byte isActive) throws Exception {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.
	}

	@Override
	public WtEmpCorrectionAttendance getEntityByPkIsActive(String id,
			Boolean isActive) throws Exception {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.
	}

	@Override
	public WtEmpCorrectionAttendance getEntityByPkIsActive(Integer id,
			Integer isActive) throws Exception {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.
	}

	@Override
	public WtEmpCorrectionAttendance getEntityByPkIsActive(Integer id,
			Byte isActive) throws Exception {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.
	}

	@Override
	public WtEmpCorrectionAttendance getEntityByPkIsActive(Integer id,
			Boolean isActive) throws Exception {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.
	}

	@Override
	public WtEmpCorrectionAttendance getEntityByPkIsActive(Long id,
			Integer isActive) throws Exception {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.
	}

	@Override
	public WtEmpCorrectionAttendance getEntityByPkIsActive(Long id,
			Byte isActive) throws Exception {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.
	}

	@Override
	public WtEmpCorrectionAttendance getEntityByPkIsActive(Long id,
			Boolean isActive) throws Exception {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.
	}

	@Override
	public void delete(WtEmpCorrectionAttendance entity) throws Exception {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.
	}

	@Override
	public void softDelete(WtEmpCorrectionAttendance entity) throws Exception {
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
	public List<WtEmpCorrectionAttendance> getAllData() throws Exception {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.
	}

	@Override
	public List<WtEmpCorrectionAttendance> getAllData(Boolean isActive)
			throws Exception {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.
	}

	@Override
	public List<WtEmpCorrectionAttendance> getAllData(Integer isActive)
			throws Exception {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.
	}

	@Override
	public List<WtEmpCorrectionAttendance> getAllData(Byte isActive)
			throws Exception {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.
	}

	@Override
	public List<WtEmpCorrectionAttendance> getAllDataPageAble(int firstResult,
			int maxResults, Order order) throws Exception {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.
	}

	@Override
	public List<WtEmpCorrectionAttendance> getAllDataPageAbleIsActive(
			int firstResult, int maxResults, Order order, Boolean isActive)
			throws Exception {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.
	}

	@Override
	public List<WtEmpCorrectionAttendance> getAllDataPageAbleIsActive(
			int firstResult, int maxResults, Order order, Integer isActive)
			throws Exception {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.
	}

	@Override
	public List<WtEmpCorrectionAttendance> getAllDataPageAbleIsActive(
			int firstResult, int maxResults, Order order, Byte isActive)
			throws Exception {
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
        	Gson gson = JsonUtil.getHibernateEntityGsonBuilder().registerTypeAdapter(Date.class, new DateJsonDeserializer()).create();
    		WtEmpCorrectionAttendance entity = gson.fromJson(appActivity.getPendingData(), WtEmpCorrectionAttendance.class); 
    		entity.setApprovalActivityNumber(appActivity.getActivityNumber());  //set approval activity number, for history approval purpose
    		entity.setApprovalStatus(HRMConstant.EMP_CORRECTION_ATTENDANCE_STATUS_APPROVED); //set approval status approved
    		
    		JsonObject jsonObject = gson.fromJson(appActivity.getPendingData(), JsonObject.class);
            List<WtEmpCorrectionAttendanceDetail> listDetail = gson.fromJson(jsonObject.get("listDetail").getAsString(), new TypeToken<List<WtEmpCorrectionAttendanceDetail>>() {}.getType());            
            
            /** saving to DB */
            this.save(entity, listDetail, Boolean.TRUE);
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
             * kalau status akhir sudah di rejected dan tidak ada next approval,
             * berarti langsung insert ke database
             */                        
        	Gson gson = JsonUtil.getHibernateEntityGsonBuilder().registerTypeAdapter(Date.class, new DateJsonDeserializer()).create();
    		WtEmpCorrectionAttendance entity = gson.fromJson(appActivity.getPendingData(), WtEmpCorrectionAttendance.class); 
    		entity.setApprovalActivityNumber(appActivity.getActivityNumber());  //set approval activity number, for history approval purpose
    		entity.setApprovalStatus(HRMConstant.EMP_CORRECTION_ATTENDANCE_STATUS_REJECTED); //set approval status rejected
    		
    		JsonObject jsonObject = gson.fromJson(appActivity.getPendingData(), JsonObject.class);
            List<WtEmpCorrectionAttendanceDetail> listDetail = gson.fromJson(jsonObject.get("listDetail").getAsString(), new TypeToken<List<WtEmpCorrectionAttendanceDetail>>() {}.getType());            
            
            /** saving to DB */
            this.save(entity, listDetail, Boolean.TRUE);
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
        	Gson gson = JsonUtil.getHibernateEntityGsonBuilder().registerTypeAdapter(Date.class, new DateJsonDeserializer()).create();
    		WtEmpCorrectionAttendance entity = gson.fromJson(appActivity.getPendingData(), WtEmpCorrectionAttendance.class); 
    		entity.setApprovalActivityNumber(appActivity.getActivityNumber());  //set approval activity number, for history approval purpose
    		entity.setApprovalStatus(HRMConstant.EMP_CORRECTION_ATTENDANCE_STATUS_APPROVED); //set approval status approved
    		
    		JsonObject jsonObject = gson.fromJson(appActivity.getPendingData(), JsonObject.class);
            List<WtEmpCorrectionAttendanceDetail> listDetail = gson.fromJson(jsonObject.get("listDetail").getAsString(), new TypeToken<List<WtEmpCorrectionAttendanceDetail>>() {}.getType());            
            
            /** saving to DB */
            this.save(entity, listDetail, Boolean.TRUE);
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
		Gson gson = JsonUtil.getHibernateEntityGsonBuilder().registerTypeAdapter(Date.class, new DateJsonDeserializer()).create();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMMM-yyyy", new Locale(appActivity.getLocale()));
        
        //get all sendCC email address on status approve OR reject
        List<String> ccEmailAddresses = new ArrayList<String>();
        if ((appActivity.getApprovalStatus() == HRMConstant.APPROVAL_STATUS_APPROVED) || (appActivity.getApprovalStatus() == HRMConstant.APPROVAL_STATUS_REJECTED)) {
            ccEmailAddresses = super.getCcEmailAddressesOnApproveOrReject(appActivity);
        }
		
        WtEmpCorrectionAttendance entity = gson.fromJson(appActivity.getPendingData(), WtEmpCorrectionAttendance.class);
        JsonObject jsonObject = gson.fromJson(appActivity.getPendingData(), JsonObject.class);
        
        final JSONObject jsonObj = new JSONObject();
        try {
            jsonObj.put("approvalActivityId", appActivity.getId());
            jsonObj.put("ccEmailAddresses", ccEmailAddresses);
            jsonObj.put("locale", appActivity.getLocale());
            jsonObj.put("applyDate", dateFormat.format(entity.getCreatedOn()));
            jsonObj.put("startDate", dateFormat.format(entity.getStartDate()));
            jsonObj.put("endDate", dateFormat.format(entity.getEndDate()));
            jsonObj.put("listCorrectionAttendance", jsonObject.get("listDetail").getAsString());
            jsonObj.put("urlLinkToApprove", FacesUtil.getRequest().getContextPath() + "" + HRMConstant.EMP_CORRECTION_ATTENDANCE_APPROVAL_PAGE + "" +"?faces-redirect=true&execution=e" + appActivity.getId());

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
	public String saveWithApproval(WtEmpCorrectionAttendance entity, List<WtEmpCorrectionAttendanceDetail> listDetail) throws Exception {
		/** check jika ada permintaan koreksi yang masih diproses approval, 
		 *  hanya boleh mengajukan permintaan koreksi jika tidak ada approval yang pending */
		HrmUser hrmUser = hrmUserDao.getByEmpDataId(entity.getEmpData().getId());
		//Get All pending request of employee
        List<ApprovalActivity> listPendingRequest = approvalActivityDao.getPendingRequest(hrmUser.getUserId()); 
        //filter only activity that comes from EMP_CORRECTION_ATTENDANCE
        listPendingRequest = Lambda.select(listPendingRequest, Lambda.having(Lambda.on(ApprovalActivity.class).getApprovalDefinition().getName(), Matchers.equalTo(HRMConstant.EMP_CORRECTION_ATTENDANCE)));
        if(!listPendingRequest.isEmpty()){
        	throw new BussinessException("emp_correction_attendance.error_still_have_waiting_status");
        }
        
		entity.setApprovalStatus(HRMConstant.EMP_CORRECTION_ATTENDANCE_STATUS_APPROVED); //set approval status approved		
		return this.save(entity, listDetail, Boolean.FALSE);
	}

	@Override
	@Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public String saveWithRevised(WtEmpCorrectionAttendance entity, List<WtEmpCorrectionAttendanceDetail> listDetail, Long approvalActivityId) throws Exception {
		String message = "error";
		
		//start binding and validation
		entity = this.entityBindingAndValidation(entity, listDetail);
		
		/** proceed of revising data */
    	String pendingData = this.convertToJsonData(entity, listDetail);
    	this.revised(approvalActivityId, pendingData);
    	
    	message = "success_need_approval";
		
		return message;
	}
	
	private String save(WtEmpCorrectionAttendance entity, List<WtEmpCorrectionAttendanceDetail> listDetail, Boolean isBypassApprovalChecking) throws Exception {
		String message = "error";
        
		//start binding and validation
		entity = this.entityBindingAndValidation(entity, listDetail);
		
		/** start approval checking and saving data also */
        ApprovalActivity approvalActivity = this.checkApprovalIfAny(entity.getEmpData(), isBypassApprovalChecking);
		if(approvalActivity == null){				
			/** proceed of saving data(entity) to DB */			
			//generate number of code
			String nomor = this.generateEmpCorrectionAttendanceNumber();	        
            entity.setRequestCode(nomor);            
			wtEmpCorrectionAttendanceDao.save(entity);
            
            message = "success_without_approval";
            
		} else {	
			/** proceed of saving approval activity */
			String pendingData = this.convertToJsonData(entity, listDetail);
            approvalActivity.setPendingData(pendingData);
            approvalActivityDao.save(approvalActivity);

            //sending email notification
            this.sendingApprovalNotification(approvalActivity);
            
            message = "success_need_approval";
		}    
		
		return message; 		
	}
	
	private WtEmpCorrectionAttendance entityBindingAndValidation(WtEmpCorrectionAttendance entity, List<WtEmpCorrectionAttendanceDetail> listDetail) throws Exception {			
        
		//initial
		EmpData empData = empDataDao.getEntiyByPK(entity.getEmpData().getId());
		String createdBy = StringUtils.isEmpty(entity.getCreatedBy()) ? UserInfoUtil.getUserName() : entity.getCreatedBy();
        Date createdOn = entity.getCreatedOn() == null ? new Date() : entity.getCreatedOn();
        
        //set detail
        for(WtEmpCorrectionAttendanceDetail detail : listDetail){
			detail.setWtEmpCorrectionAttendance(entity);
        	detail.setCreatedBy(createdBy);
			detail.setCreatedOn(createdOn);
		}
        
        //set data
        entity.setEmpData(empData);
        entity.setRequestDate(createdOn);
        entity.setCreatedBy(createdBy);
        entity.setCreatedOn(createdOn);
        entity.setWtEmpCorrectionAttendanceDetails(new HashSet<WtEmpCorrectionAttendanceDetail>(listDetail));
        
        return entity;
	}

	private String generateEmpCorrectionAttendanceNumber(){
		/** generate number form codification, from empCorrectionAttendance module */
		TransactionCodefication transactionCodefication = transactionCodeficationDao.getEntityByModulCode(HRMConstant.EMP_CORRECTION_ATTENDANCE_KODE);
        Long currentMaxId = wtEmpCorrectionAttendanceDao.getCurrentMaxId();
        currentMaxId = currentMaxId != null ? currentMaxId : 0;
        String nomor  = KodefikasiUtil.getKodefikasi(((int)currentMaxId.longValue()), transactionCodefication.getCode());
        return nomor;
	}
	
	private ApprovalActivity checkApprovalIfAny(EmpData empData, Boolean isBypassApprovalChecking) throws Exception{
		/** check approval process if any,
		 *  return null if no need approval process */
		HrmUser requestUser = hrmUserDao.getByEmpDataId(empData.getId());
        
        return isBypassApprovalChecking ? null : super.checkApprovalProcess(HRMConstant.EMP_CORRECTION_ATTENDANCE, requestUser.getUserId());
	}
	
	private String convertToJsonData(WtEmpCorrectionAttendance entity, List<WtEmpCorrectionAttendanceDetail> listDetail) throws IOException{ 
		//parsing object to json 
        Gson gson = JsonUtil.getHibernateEntityGsonBuilder().registerTypeAdapter(Date.class, new DateJsonDeserializer()).create();
        JsonParser parser = new JsonParser();
        JsonObject jsonObject = (JsonObject) parser.parse(gson.toJson(entity));
        jsonObject.addProperty("listDetail", gson.toJson(listDetail));
        jsonObject.addProperty(HRMConstant.CONTEXT_PATH, FacesUtil.getRequest().getContextPath());
        return gson.toJson(jsonObject);
	}

	@Override
	@Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 30)
	public WtEmpCorrectionAttendance getEntityByPkWithDetail(Long id) throws Exception {
		return wtEmpCorrectionAttendanceDao.getEntityByPkWithDetail(id);
	}

	@Override
	@Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 50)
	public List<WtEmpCorrectionAttendance> getByParam(EmpCorrectionAttendanceSearchParameter parameter, int firstResult, int maxResult, Order orderable) throws Exception {
		return wtEmpCorrectionAttendanceDao.getByParam(parameter, firstResult, maxResult, orderable);
	}

	@Override
	@Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 30)
	public Long getTotalByParam(EmpCorrectionAttendanceSearchParameter parameter) throws Exception {
		return wtEmpCorrectionAttendanceDao.getTotalByParam(parameter);
	}

	@Override
	protected String getDetailSmsContentOfActivity(ApprovalActivity appActivity) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yy");
		StringBuffer detail = new StringBuffer();
		HrmUser requester = hrmUserDao.getByUserId(appActivity.getRequestBy());
		Gson gson = JsonUtil.getHibernateEntityGsonBuilder().registerTypeAdapter(Date.class, new DateJsonDeserializer()).create();
		WtEmpCorrectionAttendance entity = gson.fromJson(appActivity.getPendingData(), WtEmpCorrectionAttendance.class);
		
		detail.append("Pengajuan koreksi kehadiran oleh " + requester.getEmpData().getBioData().getFullName() + ". ");
		detail.append("Tanggal kerja  " + dateFormat.format(entity.getStartDate()) + " s/d " + dateFormat.format(entity.getEndDate()) );
		return detail.toString();
	}

	@Override
	@Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 50)
	public List<EmpData> getListApproverByListAppDefintion(List<ApprovalDefinition> listAppDef, Long empDataId)	throws Exception {
		HrmUser requester = hrmUserDao.getByEmpDataId(empDataId);
    	return super.getListApproverByListAppDef(listAppDef,requester.getUserId());
	}

}
