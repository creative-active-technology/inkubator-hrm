/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.service.impl;

import com.google.gson.Gson;
import com.inkubator.common.CommonUtilConstant;
import com.inkubator.common.util.DateTimeUtil;
import com.inkubator.common.util.RandomNumberUtil;
import com.inkubator.datacore.service.impl.IServiceImpl;
import com.inkubator.exception.BussinessException;
import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.dao.ApprovalActivityDao;
import com.inkubator.hrm.dao.EmpDataDao;
import com.inkubator.hrm.dao.HrmUserDao;
import com.inkubator.hrm.dao.PermitClassificationDao;
import com.inkubator.hrm.dao.PermitDistributionDao;
import com.inkubator.hrm.dao.NeracaPermitDao;
import com.inkubator.hrm.dao.PermitImplementationDao;
import com.inkubator.hrm.entity.ApprovalActivity;
import com.inkubator.hrm.entity.EmpData;
import com.inkubator.hrm.entity.HrmUser;
import com.inkubator.hrm.entity.LeaveImplementation;
import com.inkubator.hrm.entity.PermitClassification;
import com.inkubator.hrm.entity.PermitDistribution;
import com.inkubator.hrm.entity.NeracaPermit;
import com.inkubator.hrm.entity.PermitImplementation;
import com.inkubator.hrm.json.util.JsonUtil;
import com.inkubator.hrm.service.PermitDistributionService;
import com.inkubator.hrm.web.search.PermitDistributionSearchParameter;
import com.inkubator.securitycore.util.UserInfoUtil;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.hibernate.criterion.Order;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Taufik
 */
@Service(value = "permitDistributionService")
@Lazy
public class PermitDistributionServiceImpl extends IServiceImpl implements PermitDistributionService {

    @Autowired
    private PermitDistributionDao permitDistributionDao;
    @Autowired
    private EmpDataDao empDataDao;
    @Autowired
    private PermitClassificationDao permitDao;
    @Autowired
    private NeracaPermitDao neracaPermitDao;
	@Autowired
	private PermitImplementationDao permitImplementationDao;
	@Autowired
	private ApprovalActivityDao approvalActivityDao;
	@Autowired
	private HrmUserDao hrmUserDao;

    @Override
    @Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 50)
    public List<PermitDistribution> getByParamWithDetail(PermitDistributionSearchParameter searchParameter, int firstResult, int maxResults, Order order) throws Exception {
    	List<PermitDistribution> listPermitDistribution = permitDistributionDao.getByParamWithDetail(searchParameter, firstResult, maxResults, order);
    	Boolean isAppliedOrPending = Boolean.FALSE;
    	
    	for(PermitDistribution permitDistribution : listPermitDistribution){
    		//Long totalEmployees = leaveImplementationDao.getTotalEmployeeByEmployeeId(leaveDistribution.getEmpData().getId());
    		isAppliedOrPending = isAppliedOrPending(permitDistribution.getEmpData().getId(), permitDistribution.getPermitClassification().getName());
    		if(isAppliedOrPending == Boolean.TRUE){
    			permitDistribution.setIsDisabled(isAppliedOrPending);
    		}
    	}
        return listPermitDistribution;
    }
    
    private Boolean isAppliedOrPending(Long id, String name){
    	Boolean isAppliedOrPending = Boolean.FALSE;
    	//check if employee has been applied leave and has been approved
    	if(isAppliedOrPending == Boolean.FALSE){
    		Long totalEmployees = permitImplementationDao.getTotalEmployeeByEmployeeId(id);
    		if(totalEmployees > 0){
    			isAppliedOrPending = Boolean.TRUE;
    		}
    	}
    	
    	//check if employee has applied leave with status pending
    	if(isAppliedOrPending == Boolean.FALSE){
	    	HrmUser requester = hrmUserDao.getByEmpDataId(id);
			List<ApprovalActivity> listApprovalActivities = new ArrayList<ApprovalActivity>();
			if(requester != null){
				listApprovalActivities = approvalActivityDao.getAllDataNotApprovedYet(requester.getUserId(), HRMConstant.PERMIT);
			}
			for(ApprovalActivity appActivity : listApprovalActivities){
				
				Gson gson = JsonUtil.getHibernateEntityGsonBuilder().create();
				PermitImplementation permitImplementation = gson.fromJson(appActivity.getPendingData(), PermitImplementation.class);
				if(permitImplementation.getPermitClassification().getName().equals(name)){
					isAppliedOrPending = Boolean.TRUE;
				}
			}
    	}
    	return isAppliedOrPending;
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 30)
    public Long getTotalPermitDistributionByParam(PermitDistributionSearchParameter searchParameter) throws Exception {
        return permitDistributionDao.getTotalPermitDistributionByParam(searchParameter);
    }

    @Override
    public PermitDistribution getEntiyByPK(String id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public PermitDistribution getEntiyByPK(Integer id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public PermitDistribution getEntiyByPK(Long id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void save(PermitDistribution entity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void update(PermitDistribution newData) throws Exception {
        PermitDistribution oldData = permitDistributionDao.getEntiyByPK(newData.getId());
        oldData.setEmpData(empDataDao.getEntiyByPK(newData.getEmpData().getId()));
        oldData.setPermitClassification(permitDao.getEntiyByPK(newData.getPermitClassification().getId()));
        //save neraca permit
        saveNeracaPermit(oldData.getBalance(), newData.getBalance(), oldData);
        oldData.setBalance(newData.getBalance());
        permitDistributionDao.update(oldData);
    }

    private void saveNeracaPermit(double oldBalance, double newBalance, PermitDistribution permitDistribution){
    	NeracaPermit neracaPermit = new NeracaPermit();
    	if(oldBalance > newBalance){
        	double balance = oldBalance - newBalance;
        	neracaPermit.setKredit(balance);
        }else{
        	double balance = newBalance - oldBalance;
        	neracaPermit.setDebet(balance);
            
        }
    	
    	neracaPermit.setId(Long.parseLong(RandomNumberUtil.getRandomNumber(9)));
    	/*neracaPermit.setSaldo(newBalance);*/
    	neracaPermit.setPermitDistribution(permitDistribution);
    	neracaPermit.setCreatedBy(UserInfoUtil.getUserName());
    	neracaPermit.setCreatedOn(new Date());
        neracaPermitDao.save(neracaPermit);
    }

    @Override
    public void saveOrUpdate(PermitDistribution enntity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public PermitDistribution saveData(PermitDistribution entity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public PermitDistribution updateData(PermitDistribution entity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public PermitDistribution saveOrUpdateData(PermitDistribution entity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public PermitDistribution getEntityByPkIsActive(String id, Integer isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public PermitDistribution getEntityByPkIsActive(String id, Byte isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public PermitDistribution getEntityByPkIsActive(String id, Boolean isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public PermitDistribution getEntityByPkIsActive(Integer id, Integer isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public PermitDistribution getEntityByPkIsActive(Integer id, Byte isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public PermitDistribution getEntityByPkIsActive(Integer id, Boolean isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public PermitDistribution getEntityByPkIsActive(Long id, Integer isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public PermitDistribution getEntityByPkIsActive(Long id, Byte isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public PermitDistribution getEntityByPkIsActive(Long id, Boolean isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    @Transactional(readOnly = false, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void delete(PermitDistribution entity) throws Exception {
        permitDistributionDao.delete(entity);
    }

    @Override
    public void softDelete(PermitDistribution entity) throws Exception {
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
    public List<PermitDistribution> getAllData() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<PermitDistribution> getAllData(Boolean isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<PermitDistribution> getAllData(Integer isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<PermitDistribution> getAllData(Byte isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<PermitDistribution> getAllDataPageAble(int firstResult, int maxResults, Order order) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<PermitDistribution> getAllDataPageAbleIsActive(int firstResult, int maxResults, Order order, Boolean isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<PermitDistribution> getAllDataPageAbleIsActive(int firstResult, int maxResults, Order order, Integer isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<PermitDistribution> getAllDataPageAbleIsActive(int firstResult, int maxResults, Order order, Byte isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    @Transactional(readOnly = false, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    public void saveMassPenempatanPermit(List<EmpData> data, long permitId, double startBalance) throws Exception {
        PermitClassification permit = this.permitDao.getEntiyByPK(permitId);
        List<PermitDistribution> listDistribution = new ArrayList<>();
        List<NeracaPermit> listNeracaPermit = new ArrayList<>();

        for (EmpData empData : data) {
        	
        	/** calculation of start and end date */
        	DateTime now = new DateTime();
        	DateTime dtStart = null;
        	DateTime dtEnd = null;          	
        	if(permit.getBasePeriod() == HRMConstant.PERMIT_BASE_FROM_JANUARY_NEXT_PERIOD){
        		dtStart = now.withDate(now.plusYears(1).getYear(), 1, 1);
        		dtEnd = now.withDate(now.plusYears(1).getYear(), 12, 31);
        		
        	} else if(permit.getBasePeriod() == HRMConstant.PERMIT_BASE_FROM_JOIN_DATE_NEXT_PERIOD){
        		DateTime dtJoinDate = new DateTime(empData.getJoinDate());
        		if(dtJoinDate.isAfter(now)){
        			dtStart = dtJoinDate;
            		dtEnd = dtStart.plusYears(1).minusDays(1);
        		} else {
        			dtJoinDate = dtJoinDate.withYear(now.getYear());
        			if(dtJoinDate.isAfter(now)){
        				dtStart = dtJoinDate;
                		dtEnd = dtStart.plusYears(1).minusDays(1);
        			} else {
        				dtStart = dtJoinDate.plusYears(1);
                		dtEnd = dtStart.plusYears(1).minusDays(1);
        			}
        		}        		
        		
        	} else if(permit.getBasePeriod() == HRMConstant.PERMIT_BASE_FROM_JOIN_DATE_THIS_PERIOD){
        		DateTime dtJoinDate = new DateTime(empData.getJoinDate());
        		if(dtJoinDate.isAfter(now)){
        			dtStart = dtJoinDate;
            		dtEnd = dtStart.plusYears(1).minusDays(1);
        		} else {
        			dtJoinDate = dtJoinDate.withYear(now.getYear());
        			if(dtJoinDate.isAfter(now)){
        				dtStart = dtJoinDate.minusYears(1);
                		dtEnd = dtStart.plusYears(1).minusDays(1);
        			} else {
        				dtStart = dtJoinDate;
                		dtEnd = dtStart.plusYears(1).minusDays(1);
        			}
        		}     
        	}
        	
        	/** create distribution */
            PermitDistribution distribution = new PermitDistribution();
            distribution.setId(Long.parseLong(RandomNumberUtil.getRandomNumber(9)));
            distribution.setBalance(startBalance);
            distribution.setStartDate(dtStart.toDate());
            distribution.setEndDate(dtEnd.toDate());
            distribution.setEmpData(empData);
            distribution.setPermitClassification(permit);
            distribution.setCreatedBy(UserInfoUtil.getUserName());
            distribution.setCreatedOn(new Date());            
            listDistribution.add(distribution);
            
            /** create neraca */
            NeracaPermit neracaPermit = new NeracaPermit();
            neracaPermit.setId(Long.parseLong(RandomNumberUtil.getRandomNumber(9)));
            neracaPermit.setDebet(startBalance);
            neracaPermit.setPermitDistribution(distribution);
            neracaPermit.setCreatedBy(UserInfoUtil.getUserName());
            neracaPermit.setCreatedOn(new Date());
            listNeracaPermit.add(neracaPermit);
        }
        permitDistributionDao.saveBatch(listDistribution);
        neracaPermitDao.saveBacth(listNeracaPermit);

    }

    @Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 30)
    @Override
    public PermitDistribution getEntityByParamWithDetail(Long empId) throws Exception {
        return permitDistributionDao.getEntityByParamWithDetail(empId);
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 50)
    public List<PermitDistribution> getAllDataByEmpIdWithDetail() throws Exception {
        return permitDistributionDao.getAllDataByEmpIdWithDetail();
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 50)
    public List<PermitDistribution> getAllDataByEmpIdFetchPermit(Long empDataId) throws Exception {
        return permitDistributionDao.getAllDataByEmpIdFetchPermit(empDataId);
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 30)
    public PermitDistribution getEntityByPermitClassificationIdAndEmpDataId(Long permitId, Long empDataId) throws Exception {
        return permitDistributionDao.getEntityByPermitClassificationIdAndEmpDataId(permitId, empDataId);

    }

}
