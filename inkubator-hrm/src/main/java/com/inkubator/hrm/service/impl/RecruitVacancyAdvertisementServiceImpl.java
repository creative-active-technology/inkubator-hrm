package com.inkubator.hrm.service.impl;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.dao.ApprovalActivityDao;
import com.inkubator.hrm.dao.HrmUserDao;
import com.inkubator.hrm.dao.RecruitAdvertisementMediaDao;
import com.inkubator.hrm.dao.RecruitHireApplyDao;
import com.inkubator.hrm.dao.RecruitVacancyAdvertisementDao;
import com.inkubator.hrm.dao.TransactionCodeficationDao;
import com.inkubator.hrm.entity.ApprovalActivity;
import com.inkubator.hrm.entity.HrmUser;
import com.inkubator.hrm.entity.RecruitAdvertisementMedia;
import com.inkubator.hrm.entity.RecruitHireApply;
import com.inkubator.hrm.entity.RecruitVacancyAdvertisement;
import com.inkubator.hrm.entity.RecruitVacancyAdvertisementDetail;
import com.inkubator.hrm.entity.TransactionCodefication;
import com.inkubator.hrm.json.util.JsonUtil;
import com.inkubator.hrm.service.RecruitVacancyAdvertisementService;
import com.inkubator.hrm.util.KodefikasiUtil;
import com.inkubator.hrm.web.search.VacancyAdvertisementSearchParameter;
import com.inkubator.securitycore.util.UserInfoUtil;

/**
 *
 * @author rizkykojek
 */
@Service(value = "recruitVacancyAdvertisementService")
@Lazy
public class RecruitVacancyAdvertisementServiceImpl extends BaseApprovalServiceImpl implements RecruitVacancyAdvertisementService {

	@Autowired
	private RecruitVacancyAdvertisementDao recruitVacancyAdvertisementDao;
	@Autowired
	private RecruitAdvertisementMediaDao recruitAdvertisementMediaDao;
	@Autowired
	private RecruitHireApplyDao recruitHireApplyDao;
	@Autowired
	private TransactionCodeficationDao transactionCodeficationDao;
	@Autowired
	private HrmUserDao hrmUserDao;
	@Autowired
	private ApprovalActivityDao approvalActivityDao;
	
	@Override
	public RecruitVacancyAdvertisement getEntiyByPK(String id) throws Exception {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.
	}

	@Override
	public RecruitVacancyAdvertisement getEntiyByPK(Integer id) throws Exception {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.
	}

	@Override
	@Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED, propagation = Propagation.SUPPORTS, timeout = 30)
	public RecruitVacancyAdvertisement getEntiyByPK(Long id) throws Exception {
		return recruitVacancyAdvertisementDao.getEntiyByPK(id);
	}

	@Override
	public void save(RecruitVacancyAdvertisement entity) throws Exception {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.
	}

	@Override
	public void update(RecruitVacancyAdvertisement entity) throws Exception {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.
	}

	@Override
	public void saveOrUpdate(RecruitVacancyAdvertisement enntity) throws Exception {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.
	}

	@Override
	public RecruitVacancyAdvertisement saveData(RecruitVacancyAdvertisement entity) throws Exception {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.
	}

	@Override
	public RecruitVacancyAdvertisement updateData(RecruitVacancyAdvertisement entity) throws Exception {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.
	}

	@Override
	public RecruitVacancyAdvertisement saveOrUpdateData(RecruitVacancyAdvertisement entity) throws Exception {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.
	}

	@Override
	public RecruitVacancyAdvertisement getEntityByPkIsActive(String id, Integer isActive) throws Exception {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.
	}

	@Override
	public RecruitVacancyAdvertisement getEntityByPkIsActive(String id, Byte isActive) throws Exception {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.
	}

	@Override
	public RecruitVacancyAdvertisement getEntityByPkIsActive(String id, Boolean isActive) throws Exception {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.
	}

	@Override
	public RecruitVacancyAdvertisement getEntityByPkIsActive(Integer id, Integer isActive) throws Exception {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.
	}

	@Override
	public RecruitVacancyAdvertisement getEntityByPkIsActive(Integer id, Byte isActive) throws Exception {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.
	}

	@Override
	public RecruitVacancyAdvertisement getEntityByPkIsActive(Integer id, Boolean isActive) throws Exception {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.
	}

	@Override
	public RecruitVacancyAdvertisement getEntityByPkIsActive(Long id, Integer isActive) throws Exception {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.
	}

	@Override
	public RecruitVacancyAdvertisement getEntityByPkIsActive(Long id, Byte isActive) throws Exception {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.
	}

	@Override
	public RecruitVacancyAdvertisement getEntityByPkIsActive(Long id, Boolean isActive) throws Exception {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.
	}

	@Override
	@Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void delete(RecruitVacancyAdvertisement entity) throws Exception {
		recruitVacancyAdvertisementDao.delete(entity);
	}

	@Override
	public void softDelete(RecruitVacancyAdvertisement entity) throws Exception {
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
	public List<RecruitVacancyAdvertisement> getAllData() throws Exception {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.
	}

	@Override
	public List<RecruitVacancyAdvertisement> getAllData(Boolean isActive) throws Exception {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.
	}

	@Override
	public List<RecruitVacancyAdvertisement> getAllData(Integer isActive) throws Exception {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.
	}

	@Override
	public List<RecruitVacancyAdvertisement> getAllData(Byte isActive) throws Exception {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.
	}

	@Override
	public List<RecruitVacancyAdvertisement> getAllDataPageAble(int firstResult, int maxResults, Order order) throws Exception {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.
	}

	@Override
	public List<RecruitVacancyAdvertisement> getAllDataPageAbleIsActive(int firstResult, int maxResults, Order order, Boolean isActive)
			throws Exception {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.
	}

	@Override
	public List<RecruitVacancyAdvertisement> getAllDataPageAbleIsActive(int firstResult, int maxResults, Order order, Integer isActive)
			throws Exception {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.
	}

	@Override
	public List<RecruitVacancyAdvertisement> getAllDataPageAbleIsActive(int firstResult, int maxResults, Order order, Byte isActive) throws Exception {
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
            RecruitVacancyAdvertisement entity = this.convertJsonToEntity(appActivity.getPendingData());
    		entity.setApplicationStatus(HRMConstant.VACANCY_ADVERTISEMENT_STATUS_APPROVED); // set approved application status
            entity.setApprovalActivityNumber(appActivity.getActivityNumber()); //set approval activity number, for history approval purpose
            
            /** saving to DB */
            this.save(entity, Boolean.TRUE);
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
        	RecruitVacancyAdvertisement entity = this.convertJsonToEntity(appActivity.getPendingData());
            entity.setApplicationStatus(HRMConstant.VACANCY_ADVERTISEMENT_STATUS_REJECTED); //set rejected application status
            entity.setApprovalActivityNumber(appActivity.getActivityNumber());  //set approval activity number, for history approval purpose
            
            
            /** saving to DB */
            this.save(entity, Boolean.TRUE);
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
        	RecruitVacancyAdvertisement entity = this.convertJsonToEntity(appActivity.getPendingData());
            entity.setApplicationStatus(HRMConstant.VACANCY_ADVERTISEMENT_STATUS_APPROVED); // set approved application status
            entity.setApprovalActivityNumber(appActivity.getActivityNumber());  //set approval activity number, for history approval purpose
            
            /** saving to DB */
            this.save(entity, Boolean.TRUE);
        }

        //if there is no error, then sending the email notification
        sendingApprovalNotification(appActivity);
	}

	@Override
	protected void sendingApprovalNotification(ApprovalActivity appActivity) throws Exception {
		
	}

	@Override
	protected String getDetailSmsContentOfActivity(ApprovalActivity appActivity) {
		
		return StringUtils.EMPTY; 
	}

	@Override
	@Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public String saveWithApproval(RecruitVacancyAdvertisement entity) throws Exception {
		entity.setApplicationStatus(HRMConstant.VACANCY_ADVERTISEMENT_STATUS_APPROVED); // set approved application status
		return this.save(entity, Boolean.FALSE);
	}

	@Override
	@Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public String saveWithRevised(RecruitVacancyAdvertisement entity, Long approvalActivityId) throws Exception {

		String message = "error";
		
		//start binding and validation
		entity = this.entityBindingAndValidation(entity);
		
		/** proceed of revising data */
    	String pendingData = this.convertToJsonData(entity);
    	this.revised(approvalActivityId, pendingData);
    	
    	message = "success_need_approval";
		
		return message;
	}
	
	private String save(RecruitVacancyAdvertisement entity, Boolean isBypassApprovalChecking) throws Exception{
		String message = "error";
		
		//start binding and validation
		entity = this.entityBindingAndValidation(entity);
		
		/** start approval checking and saving data also */
        ApprovalActivity approvalActivity = this.checkApprovalIfAny(UserInfoUtil.getUserName(), isBypassApprovalChecking);
		if(approvalActivity == null){
			/** proceed of saving data(entity) to DB */
			
			//generate number of code
			String nomor = this.generateVacancyAdvertisementNumber();	        
            entity.setVacancyAdvCode(nomor);
            
            recruitVacancyAdvertisementDao.save(entity);
            
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
	
	private RecruitVacancyAdvertisement entityBindingAndValidation(RecruitVacancyAdvertisement entity){
		//initial
		RecruitAdvertisementMedia advertisementMedia =  recruitAdvertisementMediaDao.getEntiyByPK(entity.getAdvertisementMedia().getId());
		String createdBy = StringUtils.isEmpty(entity.getCreatedBy()) ? UserInfoUtil.getUserName() : entity.getCreatedBy();
        Date createdOn = entity.getCreatedOn() == null ? new Date() : entity.getCreatedOn();
		        
		//set data
		entity.setAdvertisementMedia(advertisementMedia);
		entity.setCreatedBy(createdBy);
        entity.setCreatedOn(createdOn);
        
        //set data detail
        for(RecruitVacancyAdvertisementDetail detail : entity.getRecruitVacancyAdvertisementDetails()){
        	RecruitHireApply hireApply =  recruitHireApplyDao.getEntiyByPK(detail.getHireApply().getId());
        	
        	detail.setVacancyAdvertisement(entity);
        	detail.setHireApply(hireApply);
        	detail.setCreatedBy(createdBy);
        	detail.setCreatedOn(createdOn);
        }
        
        return entity;
	}
	
	private String generateVacancyAdvertisementNumber(){
		/** generate number form codification, from vacancy advertisement module */
		TransactionCodefication transactionCodefication = transactionCodeficationDao.getEntityByModulCode(HRMConstant.VACANCY_ADVERTISEMENT_KODE);
        Long currentMaxId = recruitVacancyAdvertisementDao.getCurrentMaxId();
        currentMaxId = currentMaxId != null ? currentMaxId : 0;
        String nomor  = KodefikasiUtil.getKodefikasi(((int)currentMaxId.longValue()), transactionCodefication.getCode());
        return nomor;
	}
	
	private ApprovalActivity checkApprovalIfAny(String userId, Boolean isBypassApprovalChecking) throws Exception{
		/** check approval process if any,
		 *  return null if no need approval process */
		HrmUser requestUser = hrmUserDao.getByUserId(userId);
		
        return isBypassApprovalChecking ? null : super.checkApprovalProcess(HRMConstant.VACANCY_ADVERTISEMENT, requestUser.getUserId());
	}
	
	private RecruitVacancyAdvertisement convertJsonToEntity(String json){
		Gson gson = JsonUtil.getHibernateEntityGsonBuilder().create();
		RecruitVacancyAdvertisement entity = gson.fromJson(json, RecruitVacancyAdvertisement.class);
		
		JsonObject jsonObject =  gson.fromJson(json, JsonObject.class);
		List<RecruitVacancyAdvertisementDetail> recruitVacancyAdvertisementDetails = gson.fromJson(jsonObject.get("recruitVacancyAdvertisementDetails"), new TypeToken<List<RecruitVacancyAdvertisementDetail>>(){}.getType());		
		entity.setRecruitVacancyAdvertisementDetails(new HashSet<RecruitVacancyAdvertisementDetail>(recruitVacancyAdvertisementDetails));
		
        return entity;
	}
	
	private String convertToJsonData(RecruitVacancyAdvertisement entity) {
        
		//parsing object to json 
        Gson gson = JsonUtil.getHibernateEntityGsonBuilder().create();
        JsonParser parser = new JsonParser();
        JsonObject jsonObject = (JsonObject) parser.parse(gson.toJson(entity));
        
        //parsing list to json
        JsonArray arrayComponents = new JsonArray();
		for(RecruitVacancyAdvertisementDetail detail: entity.getRecruitVacancyAdvertisementDetails()){
			JsonObject component = (JsonObject) parser.parse(gson.toJson(detail));
			arrayComponents.add(component);
		}
		jsonObject.add("recruitVacancyAdvertisementDetails", arrayComponents);
		
        return gson.toJson(jsonObject);
	}

	@Override
	@Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 50)
	public List<RecruitVacancyAdvertisement> getByParam(VacancyAdvertisementSearchParameter parameter, int first, int pageSize, Order orderable) throws Exception {
		return recruitVacancyAdvertisementDao.getByParam(parameter, first, pageSize, orderable);
	}

	@Override
	@Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 30)
	public Long getTotalByParam(VacancyAdvertisementSearchParameter parameter) throws Exception {
		return recruitVacancyAdvertisementDao.getTotalByParam(parameter);
	}

	@Override
	@Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 30)
	public RecruitVacancyAdvertisement getEntityByPkWithDetail(Long id) throws Exception {
		return recruitVacancyAdvertisementDao.getEntityByPkWithDetail(id);
	}

}
