package com.inkubator.hrm.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.time.DateUtils;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.google.gson.Gson;
import com.inkubator.common.util.RandomNumberUtil;
import com.inkubator.datacore.service.impl.IServiceImpl;
import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.dao.ApprovalActivityDao;
import com.inkubator.hrm.entity.ApprovalActivity;
import com.inkubator.hrm.entity.BusinessTravel;
import com.inkubator.hrm.entity.ImplementationOfOverTime;
import com.inkubator.hrm.entity.Leave;
import com.inkubator.hrm.entity.LeaveImplementation;
import com.inkubator.hrm.entity.LoanNewApplication;
import com.inkubator.hrm.entity.PermitImplementation;
import com.inkubator.hrm.json.util.DateJsonDeserializer;
import com.inkubator.hrm.json.util.JsonUtil;
import com.inkubator.hrm.service.ApprovalActivityService;
import com.inkubator.hrm.web.search.ApprovalActivitySearchParameter;

/**
 *
 * @author rizkykojek
 */
@Service(value = "approvalActivityService")
@Lazy
public class ApprovalActivityServiceImpl extends IServiceImpl implements ApprovalActivityService {

    @Autowired
    private ApprovalActivityDao approvalActivityDao;

    @Override
    public ApprovalActivity getEntiyByPK(String id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

    }

    @Override
    public ApprovalActivity getEntiyByPK(Integer id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 30)
    public ApprovalActivity getEntiyByPK(Long id) throws Exception {
        return approvalActivityDao.getEntiyByPK(id);

    }

    @Override
    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void save(ApprovalActivity entity) throws Exception {
        entity.setId(Long.parseLong(RandomNumberUtil.getRandomNumber(9)));
        approvalActivityDao.save(entity);
    }

    @Override
    public void update(ApprovalActivity entity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

    }

    @Override
    public void saveOrUpdate(ApprovalActivity enntity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

    }

    @Override
    public ApprovalActivity saveData(ApprovalActivity entity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

    }

    @Override
    public ApprovalActivity updateData(ApprovalActivity entity)
            throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

    }

    @Override
    public ApprovalActivity saveOrUpdateData(ApprovalActivity entity)
            throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

    }

    @Override
    public ApprovalActivity getEntityByPkIsActive(String id, Integer isActive)
            throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

    }

    @Override
    public ApprovalActivity getEntityByPkIsActive(String id, Byte isActive)
            throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

    }

    @Override
    public ApprovalActivity getEntityByPkIsActive(String id, Boolean isActive)
            throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

    }

    @Override
    public ApprovalActivity getEntityByPkIsActive(Integer id, Integer isActive)
            throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

    }

    @Override
    public ApprovalActivity getEntityByPkIsActive(Integer id, Byte isActive)
            throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

    }

    @Override
    public ApprovalActivity getEntityByPkIsActive(Integer id, Boolean isActive)
            throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

    }

    @Override
    public ApprovalActivity getEntityByPkIsActive(Long id, Integer isActive)
            throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

    }

    @Override
    public ApprovalActivity getEntityByPkIsActive(Long id, Byte isActive)
            throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

    }

    @Override
    public ApprovalActivity getEntityByPkIsActive(Long id, Boolean isActive)
            throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

    }

    @Override
    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void delete(ApprovalActivity entity) throws Exception {
        this.approvalActivityDao.delete(entity);
    }

    @Override
    public void softDelete(ApprovalActivity entity) throws Exception {
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
    public List<ApprovalActivity> getAllData() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

    }

    @Override
    public List<ApprovalActivity> getAllData(Boolean isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

    }

    @Override
    public List<ApprovalActivity> getAllData(Integer isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

    }

    @Override
    public List<ApprovalActivity> getAllData(Byte isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

    }

    @Override
    public List<ApprovalActivity> getAllDataPageAble(int firstResult,
            int maxResults, Order order) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

    }

    @Override
    public List<ApprovalActivity> getAllDataPageAbleIsActive(int firstResult,
            int maxResults, Order order, Boolean isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

    }

    @Override
    public List<ApprovalActivity> getAllDataPageAbleIsActive(int firstResult,
            int maxResults, Order order, Integer isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

    }

    @Override
    public List<ApprovalActivity> getAllDataPageAbleIsActive(int firstResult,
            int maxResults, Order order, Byte isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 50)
    public List<ApprovalActivity> getRequestHistory(String userName) throws Exception {
        return this.approvalActivityDao.getRequestHistory(userName, 0, 5, Order.desc("requestTime"));
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 50)
    public List<ApprovalActivity> getAllDataWithAllRelation(ApprovalActivitySearchParameter searchParameter, int firstResult, int maxResults, Order order) throws Exception {
        return approvalActivityDao.getAllDataWithAllRelation(searchParameter, firstResult, maxResults, order);
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 50)
    public Long getTotalByParam(ApprovalActivitySearchParameter searchParameter) throws Exception {
        return approvalActivityDao.getTotalByParam(searchParameter);
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 30)
    public ApprovalActivity getEntityByPkWithAllRelation(Long id) throws Exception {
        return approvalActivityDao.getEntityByPkWithAllRelation(id);
    }

    @Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 50)
    public List<ApprovalActivity> getReguestHistoryById(long id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 50)
    public List<ApprovalActivity> getPendingRequest(String userName) throws Exception {
        return this.approvalActivityDao.getPendingRequest(userName);
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 50)
    public List<ApprovalActivity> getPendingTask(String userName) throws Exception {
        return this.approvalActivityDao.getPendingTask(userName);
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 30)
    public ApprovalActivity getEntityByPkWithDetail(Long id) throws Exception {
        return this.approvalActivityDao.getEntityByPkWithDetail(id);

    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 50)
    public List<ApprovalActivity> getAllDataByActivityNumberWithDetail(String activityNumber) throws Exception {
        return this.approvalActivityDao.getAllDataByActivityNumberWithDetail(activityNumber, Order.asc("sequence"));

    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 50)
    public ApprovalActivity getEntityByActivityNumberLastSequence(String activityNumber) throws Exception {
        List<ApprovalActivity> approvalActivities = this.approvalActivityDao.getAllDataByActivityNumberWithDetail(activityNumber, Order.desc("sequence"));
        ApprovalActivity lastApprovalActivity = null;
        if (!approvalActivities.isEmpty()) {
            lastApprovalActivity = approvalActivities.get(0);
        }
        return lastApprovalActivity;
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 50)
    public ApprovalActivity getEntityByPreviousActivityNumberLastSequence(String previousActivityNumber) throws Exception {
        List<ApprovalActivity> approvalActivities = this.approvalActivityDao.getAllDataByPreviousActivityNumber(previousActivityNumber, Order.desc("sequence"));
        ApprovalActivity lastApprovalActivity = null;
        if (!approvalActivities.isEmpty()) {
            lastApprovalActivity = approvalActivities.get(0);
        }
        return lastApprovalActivity;
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 50)
    public Boolean isStillHaveWaitingStatus(String activityNumber) throws Exception {
        return this.approvalActivityDao.isStillHaveWaitingStatus(activityNumber);

    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 50)
    public List<ApprovalActivity> getByApprovalStatus(Integer approvalStatus) {
        return this.approvalActivityDao.getByApprovalStatus(approvalStatus);

    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 50)
    public ApprovalActivity getApprovalTimeByApprovalActivityNumber(String activityNumber) throws Exception {
        return approvalActivityDao.getApprovalTimeByApprovalActivityNumber(activityNumber);
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 30)
    public ApprovalActivity getEntityByActivityNumberAndSequence(String activityNumber, Integer sequence) throws Exception {
        return approvalActivityDao.getEntityByActivityNumberAndSequence(activityNumber, sequence);

    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 30)
    public Boolean isAlreadyHaveApprovedStatus(String activityNumber) throws Exception {
        return approvalActivityDao.isAlreadyHaveApprovedStatus(activityNumber);

    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 50)
    public List<ApprovalActivity> getAllDataWaitingStatusApproval() throws Exception {
        return approvalActivityDao.getAllDataWaitingStatusApproval();
    }

	@Override
	@Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 50)
	public List<ApprovalActivity> getListLeavePendingWithImplDateBetweenRange(Date startDate, Date endDate)	throws Exception {
		List<ApprovalActivity> listPendingApprovalLeave = approvalActivityDao.getAllDataPendingRequestByApprovalDefName(HRMConstant.LEAVE);
		List<ApprovalActivity> listPendingApprovalLeaveFiltered = listPendingApprovalLeave.stream()
				.filter(appActivity -> ispendingLeaveImplDateBetweenRange(appActivity,startDate,endDate))
				.collect(Collectors.toList());
		
		return listPendingApprovalLeaveFiltered;
	}
	
	private Boolean ispendingLeaveImplDateBetweenRange(ApprovalActivity approvalActivity, Date startDate, Date endDate){
		Gson gson = JsonUtil.getHibernateEntityGsonBuilder().create();
        LeaveImplementation entity = gson.fromJson(approvalActivity.getPendingData(), LeaveImplementation.class);
		return entity.getStartDate().after(startDate) && entity.getStartDate().before(endDate);
	}

	@Override
	@Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 50)
	public List<ApprovalActivity> getListBusinessTravelPendingWithImplDateBetweenRange(Date startDate, Date endDate)
			throws Exception {
		List<ApprovalActivity> listPendingApprovalBusinessTravel = approvalActivityDao.getAllDataPendingRequestByApprovalDefName(HRMConstant.BUSINESS_TRAVEL);
		List<ApprovalActivity> listPendingApprovalBusinessTravelFiltered = listPendingApprovalBusinessTravel.stream()
				.filter(appActivity -> ispendingBusinessTravelImplDateBetweenRange(appActivity,startDate,endDate))
				.collect(Collectors.toList());
		
		return listPendingApprovalBusinessTravelFiltered;
	}
	
	private Boolean ispendingBusinessTravelImplDateBetweenRange(ApprovalActivity approvalActivity, Date startDate, Date endDate){
		Gson gson = JsonUtil.getHibernateEntityGsonBuilder().create();
        BusinessTravel entity = gson.fromJson(approvalActivity.getPendingData(), BusinessTravel.class);
		return entity.getStartDate().after(startDate) && entity.getStartDate().before(endDate);
	}

	@Override
	@Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 50)
	public List<ApprovalActivity> getListPermitPendingWithImplDateBetweenRange(Date startDate, Date endDate)
			throws Exception {
		List<ApprovalActivity> listPendingApprovalPermit = approvalActivityDao.getAllDataPendingRequestByApprovalDefName(HRMConstant.PERMIT);
		List<ApprovalActivity> listPendingApprovalPermitFiltered = listPendingApprovalPermit.stream()
				.filter(appActivity -> ispendingPermitImplDateBetweenRange(appActivity,startDate,endDate))
				.collect(Collectors.toList());
		
		return listPendingApprovalPermitFiltered;
	}
	
	private Boolean ispendingPermitImplDateBetweenRange(ApprovalActivity approvalActivity, Date startDate, Date endDate){
		Gson gson = JsonUtil.getHibernateEntityGsonBuilder().create();
        PermitImplementation entity = gson.fromJson(approvalActivity.getPendingData(), PermitImplementation.class);
		return entity.getStartDate().after(startDate) && entity.getStartDate().before(endDate);
	}

	@Override
	@Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 50)
	public List<ApprovalActivity> getListOvertimePendingWithImplDateBetweenRange(Date startDate, Date endDate)
			throws Exception {
		List<ApprovalActivity> listPendingApprovalOvertime = approvalActivityDao.getAllDataPendingRequestByApprovalDefName(HRMConstant.OVERTIME);
		List<ApprovalActivity> listPendingApprovalOvertimeFiltered = listPendingApprovalOvertime.stream()
				.filter(appActivity -> ispendingOvertimeImplDateBetweenRange(appActivity,startDate,endDate))
				.collect(Collectors.toList());
		
		return listPendingApprovalOvertimeFiltered;
	}
	
	private Boolean ispendingOvertimeImplDateBetweenRange(ApprovalActivity approvalActivity, Date startDate, Date endDate){
		Gson gson = JsonUtil.getHibernateEntityGsonBuilder().registerTypeAdapter(Date.class, new DateJsonDeserializer()).create();
        ImplementationOfOverTime entity = gson.fromJson(approvalActivity.getPendingData(), ImplementationOfOverTime.class);
		return entity.getImplementationDate().after(startDate) && entity.getImplementationDate().before(endDate);
	}

}
