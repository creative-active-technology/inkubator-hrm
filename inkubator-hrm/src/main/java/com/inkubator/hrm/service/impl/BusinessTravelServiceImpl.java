package com.inkubator.hrm.service.impl;

import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
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
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.inkubator.exception.BussinessException;
import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.dao.ApprovalActivityDao;
import com.inkubator.hrm.dao.BusinessTravelComponentDao;
import com.inkubator.hrm.dao.BusinessTravelDao;
import com.inkubator.hrm.dao.EmpDataDao;
import com.inkubator.hrm.dao.HrmUserDao;
import com.inkubator.hrm.dao.TransactionCodeficationDao;
import com.inkubator.hrm.dao.TravelTypeDao;
import com.inkubator.hrm.dao.TravelZoneDao;
import com.inkubator.hrm.entity.ApprovalActivity;
import com.inkubator.hrm.entity.BusinessTravel;
import com.inkubator.hrm.entity.BusinessTravelComponent;
import com.inkubator.hrm.entity.EmpData;
import com.inkubator.hrm.entity.HrmUser;
import com.inkubator.hrm.entity.TransactionCodefication;
import com.inkubator.hrm.entity.TravelType;
import com.inkubator.hrm.entity.TravelZone;
import com.inkubator.hrm.json.util.JsonUtil;
import com.inkubator.hrm.service.BusinessTravelService;
import com.inkubator.hrm.util.KodefikasiUtil;
import com.inkubator.hrm.web.model.BusinessTravelViewModel;
import com.inkubator.hrm.web.search.BusinessTravelSearchParameter;
import com.inkubator.securitycore.util.UserInfoUtil;

/**
 *
 * @author rizkykojek
 */
@Service(value = "businessTravelService")
@Lazy
public class BusinessTravelServiceImpl extends BaseApprovalServiceImpl implements BusinessTravelService {
	
	@Autowired
	private BusinessTravelDao businessTravelDao;
	@Autowired
	private BusinessTravelComponentDao businessTravelComponentDao;
	@Autowired
	private TravelTypeDao travelTypeDao;
	@Autowired
	private TravelZoneDao travelZoneDao;
	@Autowired
	private EmpDataDao empDataDao;
	@Autowired
	private ApprovalActivityDao approvalActivityDao;
	@Autowired
	private HrmUserDao hrmUserDao;
    @Autowired
    private TransactionCodeficationDao transactionCodeficationDao;

	@Override
	public BusinessTravel getEntiyByPK(String id) throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public BusinessTravel getEntiyByPK(Integer id) throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	@Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 30)
	public BusinessTravel getEntiyByPK(Long id) throws Exception {
		return businessTravelDao.getEntiyByPK(id);

	}

	@Override
	public void save(BusinessTravel entity) throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public void update(BusinessTravel entity) throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public void saveOrUpdate(BusinessTravel enntity) throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public BusinessTravel saveData(BusinessTravel entity) throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public BusinessTravel updateData(BusinessTravel entity) throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public BusinessTravel saveOrUpdateData(BusinessTravel entity)
			throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public BusinessTravel getEntityByPkIsActive(String id, Integer isActive)
			throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public BusinessTravel getEntityByPkIsActive(String id, Byte isActive)
			throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public BusinessTravel getEntityByPkIsActive(String id, Boolean isActive)
			throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public BusinessTravel getEntityByPkIsActive(Integer id, Integer isActive)
			throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public BusinessTravel getEntityByPkIsActive(Integer id, Byte isActive)
			throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public BusinessTravel getEntityByPkIsActive(Integer id, Boolean isActive)
			throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public BusinessTravel getEntityByPkIsActive(Long id, Integer isActive)
			throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public BusinessTravel getEntityByPkIsActive(Long id, Byte isActive)
			throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public BusinessTravel getEntityByPkIsActive(Long id, Boolean isActive)
			throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	@Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void delete(BusinessTravel entity) throws Exception {
		BusinessTravel businessTravel = businessTravelDao.getEntiyByPK(entity.getId());
		businessTravelDao.delete(businessTravel);
	}

	@Override
	public void softDelete(BusinessTravel entity) throws Exception {
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
	@Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 50)
	public List<BusinessTravel> getAllData() throws Exception {
		return businessTravelDao.getAllData();

	}

	@Override
	public List<BusinessTravel> getAllData(Boolean isActive) throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public List<BusinessTravel> getAllData(Integer isActive) throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public List<BusinessTravel> getAllData(Byte isActive) throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public List<BusinessTravel> getAllDataPageAble(int firstResult,
			int maxResults, Order order) throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public List<BusinessTravel> getAllDataPageAbleIsActive(int firstResult,
			int maxResults, Order order, Boolean isActive) throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public List<BusinessTravel> getAllDataPageAbleIsActive(int firstResult,
			int maxResults, Order order, Integer isActive) throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public List<BusinessTravel> getAllDataPageAbleIsActive(int firstResult,
			int maxResults, Order order, Byte isActive) throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}
	
	@Override
	@Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 50)
	public List<BusinessTravel> getByParam(BusinessTravelSearchParameter parameter, int firstResult, int maxResults, Order orderable) throws Exception {
		return businessTravelDao.getByParam(parameter, firstResult, maxResults, orderable);
	}

	@Override
	@Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 30)
	public Long getTotalByParam(BusinessTravelSearchParameter parameter) throws Exception {
		return businessTravelDao.getTotalByParam(parameter);
	}
	
	@Override
	@Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public String saveWithApproval(BusinessTravel businessTravel, List<BusinessTravelComponent> businessTravelComponents) throws Exception {
		return this.save(businessTravel, businessTravelComponents, Boolean.FALSE);
	}

	@Override
	@Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public String saveWithRevised(BusinessTravel businessTravel, List<BusinessTravelComponent> businessTravelComponents, Long approvalActivityId) throws Exception {
		String message = "error";
		
		//start binding and validation
		BusinessTravel entity = this.entityBindingAndValidation(businessTravel, businessTravelComponents);
		
		/** proceed of revising data */
    	String pendingData = this.convertToJsonData(entity);
    	this.revised(approvalActivityId, pendingData);
    	
    	message = "success_need_approval";
		
		return message;
	}
	
	private String save(BusinessTravel entity, List<BusinessTravelComponent> btcList, boolean isBypassApprovalChecking) throws Exception {
		String message = "error";
		
		//start binding and validation
		entity = this.entityBindingAndValidation(entity, btcList);
				
		/** start approval checking and saving data also */		
		ApprovalActivity approvalActivity = this.checkApprovalIfAny(entity.getEmpData(), isBypassApprovalChecking);		
		if(approvalActivity == null){			
			//generate number of code
			String nomor = this.generateBusinessTravelNumber();	        
            entity.setBusinessTravelNo(nomor);
            
        	businessTravelDao.save(entity);
        	
        	message = "success_without_approval";
        	
        } else {
        	/** proceed of saving approval activity */
			String pendingData = this.convertToJsonData(entity);
            approvalActivity.setPendingData(pendingData);
            approvalActivityDao.save(approvalActivity);
    		
    		//sending email notification
    		this.sendingApprovalNotification(approvalActivity);
    		
    		message = "success_need_approval";
        }
        
        return message;
	}
	
	private String convertToJsonData(BusinessTravel entity) throws IOException{
		//parsing object to json
    	JsonParser parser = new JsonParser();
		Gson gson = JsonUtil.getHibernateEntityGsonBuilder().create();
		JsonObject jsonObject = (JsonObject) parser.parse(gson.toJson(entity));    		
		
		JsonArray arrayComponents = new JsonArray();
		for(BusinessTravelComponent btc: entity.getBusinessTravelComponents()){
			JsonObject component = (JsonObject) parser.parse(gson.toJson(btc));
			arrayComponents.add(component);
		}
		jsonObject.add("businessTravelComponents", arrayComponents);
        
        return gson.toJson(jsonObject);
	}
	
	private ApprovalActivity checkApprovalIfAny(EmpData empData, Boolean isBypassApprovalChecking) throws Exception{
		/** check approval process if any,
		 *  return null if no need approval process */
		HrmUser requestUser = hrmUserDao.getByEmpDataId(empData.getId());
        return isBypassApprovalChecking ? null : super.checkApprovalProcess(HRMConstant.BUSINESS_TRAVEL, requestUser.getUserId());
	}
	
	private String generateBusinessTravelNumber(){
		/** generate number form codification, from business travel module */
		TransactionCodefication transactionCodefication = transactionCodeficationDao.getEntityByModulCode(HRMConstant.BUSINESS_TRAVEL_CODE);
        Long currentMaxId = businessTravelDao.getCurrentMaxId();
        currentMaxId = currentMaxId != null ? currentMaxId : 0;
        String nomor  = KodefikasiUtil.getKodefikasi(((int)currentMaxId.longValue()), transactionCodefication.getCode());
        return nomor;
	}

	private BusinessTravel entityBindingAndValidation(BusinessTravel entity, List<BusinessTravelComponent> btcList) throws BussinessException {		
		// check duplicate business travel number
        long totalDuplicates = businessTravelDao.getTotalByBusinessTravelNo(entity.getBusinessTravelNo());
        if (totalDuplicates > 0) {
            throw new BussinessException("businesstravel.error_duplicate_business_travel_no");
        }
        
		EmpData empData = empDataDao.getEntiyByPK(entity.getEmpData().getId());
		TravelZone travelZone = travelZoneDao.getEntiyByPK(entity.getTravelZone().getId());
		TravelType travelType = travelTypeDao.getEntiyByPK(entity.getTravelType().getId());
		entity.setEmpData(empData);
		entity.setTravelType(travelType);
		entity.setTravelZone(travelZone);
		
		String createdBy = StringUtils.isEmpty(entity.getCreatedBy()) ? UserInfoUtil.getUserName() : entity.getCreatedBy();
		Date createdOn = entity.getCreatedOn() == null ? new Date() : entity.getCreatedOn();
		entity.setCreatedBy(createdBy);
		entity.setCreatedOn(createdOn);
		
		Set<BusinessTravelComponent> businessTravelComponents =  new HashSet<BusinessTravelComponent>();
		for(BusinessTravelComponent btc : btcList){
			btc.setBusinessTravel(entity);
			btc.setCreatedBy(createdBy);
			btc.setCreatedOn(createdOn);
			
			businessTravelComponents.add(btc);
		}
		entity.setBusinessTravelComponents(businessTravelComponents);
		
		return entity;
	}

	@Override
	@Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void update(BusinessTravel bt, List<BusinessTravelComponent> btcList) throws Exception {
		// check duplicate business travel number
        long totalDuplicates = businessTravelDao.getTotalByBusinessTravelNoAndNotId(bt.getBusinessTravelNo(), bt.getId());
        if (totalDuplicates > 0) {
            throw new BussinessException("businesstravel.error_duplicate_business_travel_no");
        }
		
		BusinessTravel businessTravel = businessTravelDao.getEntiyByPK(bt.getId());
		businessTravel.setBusinessTravelNo(bt.getBusinessTravelNo());
		businessTravel.setDestination(bt.getDestination());
		businessTravel.setDescription(bt.getDescription());
		businessTravel.setProposeDate(bt.getProposeDate());
		businessTravel.setStartDate(bt.getStartDate());
		businessTravel.setEndDate(bt.getEndDate());
		EmpData empData = empDataDao.getEntiyByPK(bt.getEmpData().getId());
		TravelZone travelZone = travelZoneDao.getEntiyByPK(bt.getTravelZone().getId());
		TravelType travelType = travelTypeDao.getEntiyByPK(bt.getTravelType().getId());
		businessTravel.setEmpData(empData);
		businessTravel.setTravelType(travelType);
		businessTravel.setTravelZone(travelZone);
		businessTravel.setUpdatedBy(UserInfoUtil.getUserName());
		businessTravel.setUpdatedOn(new Date());
		businessTravel.getBusinessTravelComponents().clear(); //clear list one to many
		businessTravelDao.update(businessTravel);
		
		/**
		 * mekanismenya, clear list child-nya, lalu create ulang child-nya 
		 */
		for(BusinessTravelComponent btc : btcList){
			btc.setBusinessTravel(businessTravel);
			btc.setCreatedBy(UserInfoUtil.getUserName());
			btc.setCreatedOn(new Date());
			businessTravelComponentDao.save(btc);
		}		
	}

	@Override
	@Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 30)
	public BusinessTravel getEntityByPkWithDetail(Long id) throws Exception {
		return businessTravelDao.getEntityByPkWithDetail(id);
	}
	
	@Override
	@Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 30)
	public BusinessTravel getEntityByBusinessTravelNoWithDetail(String businessTravelNo) throws Exception {
		return businessTravelDao.getEntityByBusinessTravelNoWithDetail(businessTravelNo);
	}

	@Override
	@Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void approved(long approvalActivityId, String pendingDataUpdate, String comment) throws Exception {
		Map<String, Object> result = super.approvedAndCheckNextApproval(approvalActivityId, pendingDataUpdate, comment);
		ApprovalActivity appActivity = (ApprovalActivity) result.get("approvalActivity");
		if(StringUtils.equals((String) result.get("isEndOfApprovalProcess"), "true")){
			/** kalau status akhir sudah di approved dan tidak ada next approval, 
			 * berarti langsung insert ke database */
			Gson gson = JsonUtil.getHibernateEntityGsonBuilder().create();
			String pendingData = appActivity.getPendingData();
			JsonObject jsonObject =  gson.fromJson(pendingData, JsonObject.class);
			
			List<BusinessTravelComponent> businessTravelComponents = gson.fromJson(jsonObject.get("businessTravelComponents"), new TypeToken<List<BusinessTravelComponent>>(){}.getType());
			BusinessTravel businessTravel = gson.fromJson(pendingData, BusinessTravel.class);
			businessTravel.setApprovalActivityNumber(appActivity.getActivityNumber());  //set approval activity number, for history approval purpose
			
			this.save(businessTravel, businessTravelComponents, Boolean.TRUE);
		}
		
		//if there is no error, then sending the email notification
		sendingApprovalNotification(appActivity);
	}

	@Override
	@Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void rejected(long approvalActivityId, String comment)throws Exception {
		Map<String, Object> result = super.rejectedAndCheckNextApproval(approvalActivityId, comment);
		ApprovalActivity appActivity = (ApprovalActivity) result.get("approvalActivity");
		if(StringUtils.equals((String) result.get("isEndOfApprovalProcess"), "true")){
			/** kalau status akhir sudah di reject dan tidak ada next approval, 
			 * berarti langsung insert ke database */
			Gson gson = JsonUtil.getHibernateEntityGsonBuilder().create();
			String pendingData = appActivity.getPendingData();
			JsonObject jsonObject =  gson.fromJson(pendingData, JsonObject.class);
			
			List<BusinessTravelComponent> businessTravelComponents = gson.fromJson(jsonObject.get("businessTravelComponents"), new TypeToken<List<BusinessTravelComponent>>(){}.getType());
			BusinessTravel businessTravel = gson.fromJson(pendingData, BusinessTravel.class);
			businessTravel.setApprovalActivityNumber(appActivity.getActivityNumber());  //set approval activity number, for history approval purpose 
			
			this.save(businessTravel, businessTravelComponents, Boolean.TRUE);
		}
		
		//if there is no error, then sending the email notification
		sendingApprovalNotification(appActivity);
	}
	
	@Override
	@Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void diverted(long approvalActivityId) throws Exception {
		Map<String, Object> result = super.divertedAndCheckNextApproval(approvalActivityId);
		ApprovalActivity appActivity = (ApprovalActivity) result.get("approvalActivity");
		if(StringUtils.equals((String) result.get("isEndOfApprovalProcess"), "true")){
			/** kalau status akhir sudah di approved dan tidak ada next approval, 
			 * berarti langsung insert ke database */
			Gson gson = JsonUtil.getHibernateEntityGsonBuilder().create();
			String pendingData = appActivity.getPendingData();
			JsonObject jsonObject =  gson.fromJson(pendingData, JsonObject.class);
			
			List<BusinessTravelComponent> businessTravelComponents = gson.fromJson(jsonObject.get("businessTravelComponents"), new TypeToken<List<BusinessTravelComponent>>(){}.getType());
			BusinessTravel businessTravel = gson.fromJson(pendingData, BusinessTravel.class);
			businessTravel.setApprovalActivityNumber(appActivity.getActivityNumber());  //set approval activity number, for history approval purpose
			
			this.save(businessTravel, businessTravelComponents, Boolean.TRUE);
		}
		
		//if there is no error, then sending the email notification
		sendingApprovalNotification(appActivity);
	}
	
	@Override
	public void sendingApprovalNotification(ApprovalActivity appActivity) throws Exception{
		//send sms notification to approver if need approval OR
        //send sms notification to requester if need revision
		super.sendApprovalSmsnotif(appActivity);
		
		//initialization
		Gson gson = JsonUtil.getHibernateEntityGsonBuilder().create();
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMMM-yyyy");
		double totalAmount = 0;
		
		//get all sendCC email address on status approve OR reject
		List<String> ccEmailAddresses =  new ArrayList<String>();
		if((appActivity.getApprovalStatus() == HRMConstant.APPROVAL_STATUS_APPROVED)  || (appActivity.getApprovalStatus() == HRMConstant.APPROVAL_STATUS_REJECTED)){
			ccEmailAddresses = super.getCcEmailAddressesOnApproveOrReject(appActivity);
		}
		
		//parsing object data to json, for email purpose
		JsonObject businessTravelObj =  gson.fromJson(appActivity.getPendingData(), JsonObject.class);
		List<BusinessTravelComponent> businessTravelComponents = gson.fromJson(businessTravelObj.get("businessTravelComponents"), new TypeToken<List<BusinessTravelComponent>>(){}.getType());		
		for(BusinessTravelComponent btc:businessTravelComponents){
			totalAmount = totalAmount + btc.getPayByAmount();
		}
		BusinessTravel businessTravel = gson.fromJson(appActivity.getPendingData(), BusinessTravel.class);   	
		final JSONObject jsonObj = new JSONObject();
        try {        	
            jsonObj.put("approvalActivityId", appActivity.getId());
            jsonObj.put("ccEmailAddresses", ccEmailAddresses);
            jsonObj.put("locale", appActivity.getLocale());
            jsonObj.put("businessTravelNo", businessTravel.getBusinessTravelNo());
            jsonObj.put("proposeDate", dateFormat.format(businessTravel.getProposeDate()));
            jsonObj.put("destination", businessTravel.getDestination());
            jsonObj.put("startDate", dateFormat.format(businessTravel.getStartDate()));
            jsonObj.put("endDate", dateFormat.format(businessTravel.getEndDate()));
            jsonObj.put("description", businessTravel.getDescription());
            jsonObj.put("totalAmount", new DecimalFormat("###,###").format(totalAmount));
            Date deadline = DateUtils.addDays(appActivity.getCreatedTime(), appActivity.getApprovalDefinition().getDelayTime());  
            jsonObj.put("deadline", dateFormat.format(deadline));
            
        } catch (JSONException e) {
            LOGGER.error("Error when create json Object ", e);
        }

        //send messaging, to trigger sending email
        LOGGER.info("Process Sending Messages to ACTIVMQ");
        super.jmsTemplateApproval.send(new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                return session.createTextMessage(jsonObj.toString());
            }
        });
	}

	@Override
	@Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 30)
	public BusinessTravel getEntityByApprovalActivityNumberWithDetail(String approvalActivityNumber) throws Exception{
		return businessTravelDao.getEntityByApprovalActivityNumberWithDetail(approvalActivityNumber);
		
	}

    @Override
    @Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 30)
    public List<BusinessTravel> getAllDataByEmpDataId(Long empDataId) throws Exception {
        return businessTravelDao.getAllDataByEmpDataId(empDataId);
    }

	@Override
	protected String getDetailSmsContentOfActivity(ApprovalActivity appActivity) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yy");
		StringBuffer detail = new StringBuffer();
		HrmUser requester = hrmUserDao.getByUserId(appActivity.getRequestBy());
		Gson gson = JsonUtil.getHibernateEntityGsonBuilder().create();
		BusinessTravel entity = gson.fromJson(appActivity.getPendingData(), BusinessTravel.class);
		
		detail.append("Pengajuan perjalanan dinas oleh ").append(requester.getEmpData().getBioData().getFullName()).append(". ");
		detail.append("Jenis: ").append(entity.getTravelType().getName()).append(". ");
		detail.append("Tujuan ke ").append(entity.getDestination()).append(". ");
		detail.append("Dari tanggal ").append(dateFormat.format(entity.getStartDate())).append(" s/d ").append(dateFormat.format(entity.getEndDate()));
		return detail.toString();
	}

	@Override
	@Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 50)
	public List<BusinessTravelViewModel> getAllActivityByParam(BusinessTravelSearchParameter parameter, int first, int pageSize, Order orderable) throws Exception {
		Gson gson = JsonUtil.getHibernateEntityGsonBuilder().create();
		Date now = DateUtils.truncate(new Date(),Calendar.DAY_OF_MONTH);
		
		List<BusinessTravelViewModel> list = businessTravelDao.getAllActivityByParam(parameter, first, pageSize, orderable);
		for(BusinessTravelViewModel model : list){	
			System.out.println(model.getDestination() + "  " + model.getApprovalStatus());
			if(model.getApprovalStatus().equals(HRMConstant.APPROVAL_STATUS_APPROVED)){
				/** jika sudah di approved, maka di cek status perjalanan dinasnya, 
				 *  apakah "menunggu dilaksanakan", "sedang dilaksanakan", "sudah dilaksanakan" */
				if(now.before(model.getStartDate())){
					model.setBusinessTravelStatus(HRMConstant.BUSINESS_TRAVEL_STATUS_WAITING);
				} else if(now.after(model.getEndDate())) {
					model.setBusinessTravelStatus(HRMConstant.BUSINESS_TRAVEL_STATUS_DONE);
				} else {
					model.setBusinessTravelStatus(HRMConstant.BUSINESS_TRAVEL_STATUS_ON_GOING);
				}
			} else if(model.getApprovalStatus().equals(HRMConstant.APPROVAL_STATUS_WAITING_APPROVAL) || model.getApprovalStatus().equals(HRMConstant.APPROVAL_STATUS_WAITING_REVISED)) {
				/** Jika masih waiting approval/revisi, maka ambil value nya dari json */
				BusinessTravel entity = gson.fromJson(model.getJsonData(), BusinessTravel.class);
				
				/** set value from json */
				model.setDestination(entity.getDestination());
				model.setBusinessTravelNo(entity.getBusinessTravelNo());	
				
			} 
		}
		
		return list;
	}

	@Override
	@Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 30)
	public Long getTotalActivityByParam(BusinessTravelSearchParameter parameter) throws Exception {
		return businessTravelDao.getTotalActivityByParam(parameter);
	}

}
