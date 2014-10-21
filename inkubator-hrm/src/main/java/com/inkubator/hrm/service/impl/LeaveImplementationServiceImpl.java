package com.inkubator.hrm.service.impl;

import java.util.Date;
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

import ch.lambdaj.Lambda;

import com.google.gson.Gson;
import com.inkubator.common.util.DateTimeUtil;
import com.inkubator.common.util.RandomNumberUtil;
import com.inkubator.exception.BussinessException;
import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.dao.ApprovalActivityDao;
import com.inkubator.hrm.dao.EmpDataDao;
import com.inkubator.hrm.dao.HrmUserDao;
import com.inkubator.hrm.dao.LeaveDao;
import com.inkubator.hrm.dao.LeaveDistributionDao;
import com.inkubator.hrm.dao.LeaveImplementationDao;
import com.inkubator.hrm.dao.NeracaCutiDao;
import com.inkubator.hrm.entity.ApprovalActivity;
import com.inkubator.hrm.entity.ApprovalDefinition;
import com.inkubator.hrm.entity.ApprovalDefinitionLeave;
import com.inkubator.hrm.entity.EmpData;
import com.inkubator.hrm.entity.HrmUser;
import com.inkubator.hrm.entity.Leave;
import com.inkubator.hrm.entity.LeaveDistribution;
import com.inkubator.hrm.entity.LeaveImplementation;
import com.inkubator.hrm.entity.NeracaCuti;
import com.inkubator.hrm.json.util.JsonUtil;
import com.inkubator.hrm.service.LeaveImplementationService;
import com.inkubator.hrm.service.WtScheduleShiftService;
import com.inkubator.hrm.web.search.LeaveImplementationSearchParameter;
import com.inkubator.securitycore.util.UserInfoUtil;

/**
 *
 * @author rizkykojek
 */
@Service(value = "leaveImplementationService")
@Lazy
public class LeaveImplementationServiceImpl extends BaseApprovalServiceImpl implements LeaveImplementationService {

	@Autowired
	private LeaveImplementationDao leaveImplementationDao;
	@Autowired
	private LeaveDao leaveDao;
	@Autowired
	private EmpDataDao empDataDao;
	@Autowired
	private WtScheduleShiftService wtScheduleShiftService;
	@Autowired
	private LeaveDistributionDao leaveDistributionDao;
	@Autowired
	private NeracaCutiDao neracaCutiDao;
	@Autowired
	private HrmUserDao hrmUserDao;
	@Autowired
	private ApprovalActivityDao approvalActivityDao;
	
	@Override
	public LeaveImplementation getEntiyByPK(String id) throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public LeaveImplementation getEntiyByPK(Integer id) throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	@Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 30)
	public LeaveImplementation getEntiyByPK(Long id) throws Exception {
		return leaveImplementationDao.getEntiyByPK(id);

	}

	@Override
	@Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void save(LeaveImplementation entity) throws Exception {
		// check duplicate number filling
		long totalDuplicates = leaveImplementationDao.getTotalByNumberFilling(entity.getNumberFilling());
		if (totalDuplicates > 0) {
			throw new BussinessException("leaveimplementation.error_duplicate_filling_number");
		}		
		
		EmpData empData = empDataDao.getEntiyByPK(entity.getEmpData().getId());
		Leave leave = leaveDao.getEntiyByPK(entity.getLeave().getId());
		EmpData temporaryActing = entity.getTemporaryActing() != null ? empDataDao.getEntiyByPK(entity.getTemporaryActing().getId()) : null;
		LeaveDistribution leaveDistribution = leaveDistributionDao.getEntityByLeaveIdAndEmpDataId(leave.getId(), empData.getId());
		
		// check submitted leave tidak boleh lebih besar dari batasPengajuan di leaveDefinition
		long differenceDaysOfFilling = DateTimeUtil.getTotalDayDifference (new Date(), entity.getStartDate());
		if(differenceDaysOfFilling > leave.getSubmittedLimit()){
			throw new BussinessException("leaveimplementation.error_submitted_limit");
		}
				
		// check actualLeave yg diambil, tidak boleh lebih besar dari balanceCuti yg tersedia
		Double actualLeave = this.getTotalActualLeave(empData.getId(), leave.getId(), entity.getStartDate(), entity.getEndDate());		
		if(actualLeave > leaveDistribution.getBalance()){
    		throw new BussinessException("leaveimplementation.error_leave_balance is insufficient");
    	}
				
		entity.setId(Long.parseLong(RandomNumberUtil.getRandomNumber(9)));
		entity.setEmpData(empData);
		entity.setLeave(leave);
		entity.setTemporaryActing(temporaryActing);
		
		String createdBy = StringUtils.isEmpty(entity.getCreatedBy()) ? UserInfoUtil.getUserName() : entity.getCreatedBy();
		Date createdOn = entity.getCreatedOn() == null ? new Date() : entity.getCreatedOn();
		entity.setCreatedBy(createdBy);
		entity.setCreatedOn(createdOn);
		
		leaveImplementationDao.save(entity);
		
		if(leave.getIsQuotaReduction()){
			this.creditLeaveBalance(leaveDistribution, actualLeave);
		}
	}

	@Override
	@Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void update(LeaveImplementation entity) throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.
		// check duplicate number filling
		/*long totalDuplicates = leaveImplementationDao.getTotalByNumberFillingAndNotId(entity.getNumberFilling(), entity.getId());
		if (totalDuplicates > 0) {
			throw new BussinessException("leaveimplementation.error_duplicate_filling_number");
		}		
		
		LeaveImplementation leaveImplementation = leaveImplementationDao.getEntiyByPK(entity.getId());		

		EmpData empData = empDataDao.getEntiyByPK(entity.getEmpData().getId());
		Leave leave = leaveDao.getEntiyByPK(entity.getLeave().getId());
		EmpData temporaryActing = entity.getTemporaryActing() != null ? empDataDao.getEntiyByPK(entity.getTemporaryActing().getId()) : null;

		leaveImplementation.setEmpData(empData);
		leaveImplementation.setLeave(leave);
		leaveImplementation.setTemporaryActing(temporaryActing);
		leaveImplementation.setNumberFilling(entity.getNumberFilling());
        leaveImplementation.setStartDate(entity.getStartDate());
        leaveImplementation.setEndDate(entity.getEndDate());
        leaveImplementation.setFillingDate(entity.getFillingDate());
        leaveImplementation.setAddress(entity.getAddress());
        leaveImplementation.setMobilePhone(entity.getMobilePhone());
        leaveImplementation.setMaterialJobsAbandoned(entity.getMaterialJobsAbandoned());
        leaveImplementation.setDescription(entity.getDescription());
		leaveImplementation.setUpdatedBy(UserInfoUtil.getUserName());
		leaveImplementation.setUpdatedOn(new Date());
		
		leaveImplementationDao.update(leaveImplementation);*/
	}
	
	private void creditLeaveBalance(LeaveDistribution leaveDistribution, double actualLeave){					
		double balance = leaveDistribution.getBalance() - actualLeave;
		leaveDistribution.setBalance(balance);
		leaveDistribution.setUpdatedOn(new Date());
		leaveDistribution.setUpdatedBy(UserInfoUtil.getUserName());
		leaveDistributionDao.update(leaveDistribution);
		
		NeracaCuti neracaCuti = new NeracaCuti();
		neracaCuti.setId(Long.parseLong(RandomNumberUtil.getRandomNumber(9)));
		neracaCuti.setLeaveDistribution(leaveDistribution);
		neracaCuti.setKredit(actualLeave);				
		neracaCuti.setCreatedBy(UserInfoUtil.getUserName());
		neracaCuti.setCreatedOn(new Date());
		neracaCutiDao.save(neracaCuti);
		
	}

	@Override
	public void saveOrUpdate(LeaveImplementation enntity) throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public LeaveImplementation saveData(LeaveImplementation entity)
			throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public LeaveImplementation updateData(LeaveImplementation entity)
			throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public LeaveImplementation saveOrUpdateData(LeaveImplementation entity)
			throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public LeaveImplementation getEntityByPkIsActive(String id, Integer isActive)
			throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public LeaveImplementation getEntityByPkIsActive(String id, Byte isActive)
			throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public LeaveImplementation getEntityByPkIsActive(String id, Boolean isActive)
			throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public LeaveImplementation getEntityByPkIsActive(Integer id,
			Integer isActive) throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public LeaveImplementation getEntityByPkIsActive(Integer id, Byte isActive)
			throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public LeaveImplementation getEntityByPkIsActive(Integer id,
			Boolean isActive) throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public LeaveImplementation getEntityByPkIsActive(Long id, Integer isActive)
			throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public LeaveImplementation getEntityByPkIsActive(Long id, Byte isActive)
			throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public LeaveImplementation getEntityByPkIsActive(Long id, Boolean isActive)
			throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	@Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void delete(LeaveImplementation entity) throws Exception {
		leaveImplementationDao.delete(entity);

	}

	@Override
	public void softDelete(LeaveImplementation entity) throws Exception {
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
	public List<LeaveImplementation> getAllData() throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public List<LeaveImplementation> getAllData(Boolean isActive)
			throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public List<LeaveImplementation> getAllData(Integer isActive)
			throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public List<LeaveImplementation> getAllData(Byte isActive) throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public List<LeaveImplementation> getAllDataPageAble(int firstResult,
			int maxResults, Order order) throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public List<LeaveImplementation> getAllDataPageAbleIsActive(
			int firstResult, int maxResults, Order order, Boolean isActive)
			throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public List<LeaveImplementation> getAllDataPageAbleIsActive(
			int firstResult, int maxResults, Order order, Integer isActive)
			throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public List<LeaveImplementation> getAllDataPageAbleIsActive(
			int firstResult, int maxResults, Order order, Byte isActive)
			throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

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
			LeaveImplementation leaveImplementation =  gson.fromJson(pendingData, LeaveImplementation.class);
			leaveImplementation.setApprovalActivityNumber(appActivity.getActivityNumber());  //set approval activity number, for history approval purpose
			
			this.save(leaveImplementation, true);
		}
		
		//if there is no error, then sending the email notification
		sendingEmailApprovalNotif(appActivity);
	}

	@Override
	@Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void rejected(long approvalActivityId, String comment) throws Exception {
		Map<String, Object> result = super.rejectedAndCheckNextApproval(approvalActivityId, comment);
		ApprovalActivity appActivity = (ApprovalActivity) result.get("approvalActivity");
		if(StringUtils.equals((String) result.get("isEndOfApprovalProcess"), "true")){
			/** kalau status akhir sudah di approved dan tidak ada next approval, 
			 * berarti langsung insert ke database */
			Gson gson = JsonUtil.getHibernateEntityGsonBuilder().create();
			String pendingData = appActivity.getPendingData();
			LeaveImplementation leaveImplementation =  gson.fromJson(pendingData, LeaveImplementation.class);
			leaveImplementation.setApprovalActivityNumber(appActivity.getActivityNumber());  //set approval activity number, for history approval purpose
			
			this.save(leaveImplementation, true);
		}
		
		//if there is no error, then sending the email notification
		sendingEmailApprovalNotif(appActivity);
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
			LeaveImplementation leaveImplementation =  gson.fromJson(pendingData, LeaveImplementation.class);
			leaveImplementation.setApprovalActivityNumber(appActivity.getActivityNumber());  //set approval activity number, for history approval purpose
			
			this.save(leaveImplementation, true);
		}
		
		//if there is no error, then sending the email notification
		sendingEmailApprovalNotif(appActivity);
	}

	@Override
	public void sendingEmailApprovalNotif(ApprovalActivity appActivity) throws Exception {
		//throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	@Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 50)
	public List<LeaveImplementation> getByParam(LeaveImplementationSearchParameter parameter, int firstResult, int maxResults, Order orderable) throws Exception {
		return leaveImplementationDao.getByParam(parameter, firstResult, maxResults, orderable);

	}

	@Override
	@Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 30)
	public Long getTotalByParam(LeaveImplementationSearchParameter parameter) throws Exception {
		return leaveImplementationDao.getTotalByParam(parameter);

	}

	@Override
	@Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 30)
	public LeaveImplementation getEntityByPkWithDetail(Long id) throws Exception {
		return leaveImplementationDao.getEntityByPkWithDetail(id);

	}

	@Override
	@Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 30)
	public LeaveImplementation getLatestEntityByEmpDataId(Long empDataId) throws Exception {
		LeaveImplementation latest = null;
		//order by tanggal pengajuan descending
		List<LeaveImplementation> leaveImplementations = leaveImplementationDao.getAllDataByEmpDataId(empDataId, Order.desc("fillingDate"));
		if(! leaveImplementations.isEmpty()){
			latest = leaveImplementations.get(0);
		}				 
		return latest;
	}

	@Override
	@Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 30)
	public Double getTotalActualLeave(Long empDataId, Long leaveId, Date startDate, Date endDate) throws Exception {
		EmpData empData = empDataDao.getEntiyByPK(empDataId);
		Leave leave = leaveDao.getEntiyByPK(leaveId);
		double actualLeave = 0.0;
		if(leave.getDayType() == HRMConstant.LEAVE_DAY_TYPE_WORKING){			
			actualLeave = wtScheduleShiftService.getTotalWorkingDaysBetween(empData.getId(), startDate, endDate);
		} else {
			actualLeave = DateTimeUtil.getTotalDay(startDate, endDate);
		}
		
		return actualLeave;
	}

	@Override
	@Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public String save(LeaveImplementation entity, boolean isBypassApprovalChecking) throws Exception {
		String message = "error";
		
		// check duplicate number filling
		long totalDuplicates = leaveImplementationDao.getTotalByNumberFilling(entity.getNumberFilling());
		if (totalDuplicates > 0) {
			throw new BussinessException("leaveimplementation.error_duplicate_filling_number");
		}		
		
		EmpData empData = empDataDao.getEntiyByPK(entity.getEmpData().getId());
		Leave leave = leaveDao.getEntiyByPK(entity.getLeave().getId());
		EmpData temporaryActing = entity.getTemporaryActing() != null ? empDataDao.getEntiyByPK(entity.getTemporaryActing().getId()) : null;
		LeaveDistribution leaveDistribution = leaveDistributionDao.getEntityByLeaveIdAndEmpDataId(leave.getId(), empData.getId());
		
		// check submitted leave tidak boleh lebih besar dari batasPengajuan di leaveDefinition
		long differenceDaysOfFilling = DateTimeUtil.getTotalDayDifference (new Date(), entity.getStartDate());
		if(differenceDaysOfFilling > leave.getSubmittedLimit()){
			throw new BussinessException("leaveimplementation.error_submitted_limit");
		}
				
		// check actualLeave yg diambil, tidak boleh lebih besar dari balanceCuti yg tersedia
		Double actualLeave = this.getTotalActualLeave(empData.getId(), leave.getId(), entity.getStartDate(), entity.getEndDate());		
		if(actualLeave > leaveDistribution.getBalance()){
    		throw new BussinessException("leaveimplementation.error_leave_balance is insufficient");
    	}
				
		entity.setId(Long.parseLong(RandomNumberUtil.getRandomNumber(9)));
		entity.setEmpData(empData);
		entity.setLeave(leave);
		entity.setTemporaryActing(temporaryActing);
		
		String createdBy = StringUtils.isEmpty(entity.getCreatedBy()) ? UserInfoUtil.getUserName() : entity.getCreatedBy();
		Date createdOn = entity.getCreatedOn() == null ? new Date() : entity.getCreatedOn();
		entity.setCreatedBy(createdBy);
		entity.setCreatedOn(createdOn);
		
		HrmUser requestUser = hrmUserDao.getByEmpDataId(empData.getId());
		List<ApprovalDefinition> appDefs = Lambda.extract(leave.getApprovalDefinitionLeaves(), Lambda.on(ApprovalDefinitionLeave.class).getApprovalDefinition());
		ApprovalActivity approvalActivity = isBypassApprovalChecking ? null : super.checkApprovalProcess(appDefs, requestUser.getUserId());
		if(approvalActivity == null){
			leaveImplementationDao.save(entity);			
			if(leave.getIsQuotaReduction()){
				this.creditLeaveBalance(leaveDistribution, actualLeave);
			}
			
			message = "success_without_approval";
		} else {
			//parsing object to json and save to approval activity 
        	Gson gson = JsonUtil.getHibernateEntityGsonBuilder().create();
    		approvalActivity.setPendingData( gson.toJson(entity));
    		approvalActivityDao.save(approvalActivity);
    		
    		message = "success_need_approval";
    		
    		//sending email notification
    		this.sendingEmailApprovalNotif(approvalActivity);
		}
		
		return message;
	}

	@Override
	@Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 30)
	public LeaveImplementation getEntityByApprovalActivityNumberWithDetail(String activityNumber) throws Exception {
		return leaveImplementationDao.getEntityByApprovalActivityNumberWithDetail(activityNumber);
		
	}
}
