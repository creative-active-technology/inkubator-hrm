/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.hamcrest.Matchers;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import ch.lambdaj.Lambda;

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
import com.inkubator.hrm.dao.LeaveDao;
import com.inkubator.hrm.dao.LeaveDistributionDao;
import com.inkubator.hrm.dao.LeaveImplementationDao;
import com.inkubator.hrm.dao.NeracaCutiDao;
import com.inkubator.hrm.entity.ApprovalActivity;
import com.inkubator.hrm.entity.EmpData;
import com.inkubator.hrm.entity.HrmUser;
import com.inkubator.hrm.entity.Leave;
import com.inkubator.hrm.entity.LeaveDistribution;
import com.inkubator.hrm.entity.LeaveImplementation;
import com.inkubator.hrm.entity.NeracaCuti;
import com.inkubator.hrm.json.util.JsonUtil;
import com.inkubator.hrm.service.LeaveDistributionService;
import com.inkubator.hrm.web.search.LeaveDistributionSearchParameter;
import com.inkubator.securitycore.util.UserInfoUtil;

/**
 *
 * @author Deni
 */
@Service(value = "leaveDistributionService")
@Lazy
public class LeaveDistributionServiceImpl extends IServiceImpl implements LeaveDistributionService {

    @Autowired
    private LeaveDistributionDao leaveDistributionDao;
    @Autowired
    private EmpDataDao empDataDao;
    @Autowired
    private LeaveDao leaveDao;
    @Autowired
    private NeracaCutiDao neracaCutiDao;
    @Autowired
    private LeaveImplementationDao leaveImplementationDao;
	@Autowired
	private HrmUserDao hrmUserDao;
	@Autowired
	private ApprovalActivityDao approvalActivityDao;

    @Override
    @Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 50)
    public List<LeaveDistribution> getByParamWithDetail(LeaveDistributionSearchParameter searchParameter, int firstResult, int maxResults, Order order) throws Exception {
    	List<LeaveDistribution> listLeaveDistribution = leaveDistributionDao.getByParamWithDetail(searchParameter, firstResult, maxResults, order);
    	Boolean isDisabled = Boolean.FALSE;
    	Boolean isAppliedOrPending = Boolean.FALSE;
    	
    	for(LeaveDistribution leaveDistribution : listLeaveDistribution){
    		//Long totalEmployees = leaveImplementationDao.getTotalEmployeeByEmployeeId(leaveDistribution.getEmpData().getId());
    		isAppliedOrPending = isAppliedOrPending(leaveDistribution.getEmpData().getId(), leaveDistribution.getLeave().getName());
    		if(isAppliedOrPending == Boolean.TRUE){
    			leaveDistribution.setIsDisabled(isAppliedOrPending);
    		}
    	}
    	return listLeaveDistribution;
    }

    private Boolean isAppliedOrPending(Long id, String name){
   
    	Boolean isAppliedOrPending = Boolean.FALSE;
    	//check if employee has been applied leave and has been approved
    	if(isAppliedOrPending == Boolean.FALSE){
    		Long totalEmployees = leaveImplementationDao.getTotalEmployeeByEmployeeId(id);
    		if(totalEmployees > 0){
    			isAppliedOrPending = Boolean.TRUE;
    		}
    	}
    	
    	//check if employee has applied leave with status pending
    	if(isAppliedOrPending == Boolean.FALSE){
	    	
			HrmUser requester = hrmUserDao.getByEmpDataId(id);
			List<ApprovalActivity> listApprovalActivities = approvalActivityDao.getAllDataNotApprovedYet(requester.getUserId(), HRMConstant.LEAVE);
			for(ApprovalActivity appActivity : listApprovalActivities){
				
				Gson gson = JsonUtil.getHibernateEntityGsonBuilder().create();
				LeaveImplementation leaveImplementation = gson.fromJson(appActivity.getPendingData(), LeaveImplementation.class);
				if(leaveImplementation.getLeave().getName().equals(name)){
					isAppliedOrPending = Boolean.TRUE;
				}
			}
    	}
    	return isAppliedOrPending;
    }
    
    @Override
    @Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 30)
    public Long getTotalLeaveDistributionByParam(LeaveDistributionSearchParameter searchParameter) throws Exception {
        return leaveDistributionDao.getTotalLeaveDistributionByParam(searchParameter);
    }

    @Override
    public LeaveDistribution getEntiyByPK(String id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public LeaveDistribution getEntiyByPK(Integer id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public LeaveDistribution getEntiyByPK(Long id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void save(LeaveDistribution entity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void update(LeaveDistribution entity) throws Exception {
        Leave leaveNew = leaveDao.getEntiyByPK(entity.getLeave().getId());
    	Long totalDuplicate = leaveDistributionDao.getTotalLeaveDistributionNameAndEmpDataAndNotId(leaveNew.getName(), entity.getEmpData().getId(), entity.getId());
    	if(totalDuplicate > 0){
        	throw new BussinessException("leaveDistribution.leave_and_employee_already_exists");
        }
    	
    	LeaveDistribution update = leaveDistributionDao.getEntiyByPK(entity.getId());
        update.setEmpData(empDataDao.getEntiyByPK(entity.getEmpData().getId()));
        update.setLeave(leaveNew);

        //save neraca cuti
        saveNeracaCuti(update.getBalance(), entity.getBalance(), update);
        /*if(update.getBalance() > entity.getBalance()){
        	double balance = update.getBalance() - entity.getBalance();
        	NeracaCuti neracaCuti = new NeracaCuti();
            neracaCuti.setId(Long.parseLong(RandomNumberUtil.getRandomNumber(9)));
            neracaCuti.setLeaveDistribution(update);
            neracaCuti.setKredit(balance);
            neracaCuti.setCreatedBy(UserInfoUtil.getUserName());
            neracaCuti.setCreatedOn(new Date());
            neracaCutiDao.save(neracaCuti);
        }else{
        	double balance = entity.getBalance() - update.getBalance();
        	NeracaCuti neracaCuti = new NeracaCuti();
            neracaCuti.setId(Long.parseLong(RandomNumberUtil.getRandomNumber(9)));
            neracaCuti.setLeaveDistribution(update);
            neracaCuti.setDebet(balance);
            neracaCuti.setCreatedBy(UserInfoUtil.getUserName());
            neracaCuti.setCreatedOn(new Date());
            neracaCutiDao.save(neracaCuti);
        }*/
        update.setBalance(entity.getBalance());
        leaveDistributionDao.update(update);
    }

    private void saveNeracaCuti(double oldBalance, double newBalance, LeaveDistribution leaveDistribution){
    	NeracaCuti neracaCuti = new NeracaCuti();
    	if(oldBalance > newBalance){
        	double balance = oldBalance - newBalance;
            neracaCuti.setKredit(balance);
        }else{
        	double balance = newBalance - oldBalance;
            neracaCuti.setDebet(balance);
            
        }
    	
        neracaCuti.setId(Long.parseLong(RandomNumberUtil.getRandomNumber(9)));
        neracaCuti.setSaldo(newBalance);
        neracaCuti.setLeaveDistribution(leaveDistribution);
    	neracaCuti.setCreatedBy(UserInfoUtil.getUserName());
        neracaCuti.setCreatedOn(new Date());
        neracaCutiDao.save(neracaCuti);
    }
    
    @Override
    public void saveOrUpdate(LeaveDistribution enntity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public LeaveDistribution saveData(LeaveDistribution entity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public LeaveDistribution updateData(LeaveDistribution entity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public LeaveDistribution saveOrUpdateData(LeaveDistribution entity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public LeaveDistribution getEntityByPkIsActive(String id, Integer isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public LeaveDistribution getEntityByPkIsActive(String id, Byte isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public LeaveDistribution getEntityByPkIsActive(String id, Boolean isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public LeaveDistribution getEntityByPkIsActive(Integer id, Integer isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public LeaveDistribution getEntityByPkIsActive(Integer id, Byte isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public LeaveDistribution getEntityByPkIsActive(Integer id, Boolean isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public LeaveDistribution getEntityByPkIsActive(Long id, Integer isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public LeaveDistribution getEntityByPkIsActive(Long id, Byte isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public LeaveDistribution getEntityByPkIsActive(Long id, Boolean isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void delete(LeaveDistribution entity) throws Exception {
    	neracaCutiDao.deleteNeracaCutiByLeaveDistributionId(entity.getId());
        leaveDistributionDao.delete(entity);      
        
    }

    @Override
    public void softDelete(LeaveDistribution entity) throws Exception {
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
    public List<LeaveDistribution> getAllData() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<LeaveDistribution> getAllData(Boolean isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<LeaveDistribution> getAllData(Integer isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<LeaveDistribution> getAllData(Byte isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<LeaveDistribution> getAllDataPageAble(int firstResult, int maxResults, Order order) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<LeaveDistribution> getAllDataPageAbleIsActive(int firstResult, int maxResults, Order order, Boolean isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<LeaveDistribution> getAllDataPageAbleIsActive(int firstResult, int maxResults, Order order, Integer isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<LeaveDistribution> getAllDataPageAbleIsActive(int firstResult, int maxResults, Order order, Byte isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    @Transactional(readOnly = false, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    public void saveMassPenempatanCuti(List<EmpData> data, long leaveId, double startBalance) throws Exception {
        Leave leave = this.leaveDao.getEntiyByPK(leaveId);
        List<LeaveDistribution> listDistribution = new ArrayList<>();
        List<NeracaCuti> listNeracaCuti = new ArrayList<>();
        if (startBalance > leave.getQuotaPerPeriod()) {
            throw new BussinessException("leave_start_balance.error");
        }
        Date dateNow = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(dateNow);
        int year = cal.get(Calendar.YEAR);
        int yearToInput = year + 1;
        String tanggal0101 = "01-01-" + yearToInput;
        Date tanggalToInput0101 = new SimpleDateFormat("dd-MM-yyyy").parse(tanggal0101);
        for (EmpData empData : data) {
            Date starDateToInput = null;
            Date endDateToInput = null;
            LeaveDistribution distribution = new LeaveDistribution();
            if (leave.getPeriodBase().equals(HRMConstant.LEAVE_PERIOD_BASE_TMB)) {
                Date tmb = empData.getJoinDate();
                Date starDate = DateTimeUtil.getDateFrom(tmb, 1, CommonUtilConstant.DATE_FORMAT_YEAR);
                starDateToInput = DateTimeUtil.getDateFrom(starDate, leave.getEffectiveFrom(), CommonUtilConstant.DATE_FORMAT_DAY);
                Date endDate = DateTimeUtil.getDateFrom(starDate, 1, CommonUtilConstant.DATE_FORMAT_YEAR);
                endDateToInput = DateTimeUtil.getDateFrom(endDate, -1, CommonUtilConstant.DATE_FORMAT_DAY);
            }

            if (leave.getPeriodBase().equals(HRMConstant.LEAVE_PERIOD_BASE_0101)) {
                starDateToInput = DateTimeUtil.getDateFrom(tanggalToInput0101, leave.getEffectiveFrom(), CommonUtilConstant.DATE_FORMAT_DAY);
                Date endDate = DateTimeUtil.getDateFrom(tanggalToInput0101, 1, CommonUtilConstant.DATE_FORMAT_YEAR);
                endDateToInput = DateTimeUtil.getDateFrom(endDate, -1, CommonUtilConstant.DATE_FORMAT_DAY);
            }

            if (leave.getPeriodBase().equals(HRMConstant.LEAVE_PERIOD_BASE_TMB_TO_0101)) {
                Date starDate = DateTimeUtil.getDateFrom(tanggalToInput0101, -1, CommonUtilConstant.DATE_FORMAT_YEAR);
                starDateToInput = DateTimeUtil.getDateFrom(starDate, leave.getEffectiveFrom(), CommonUtilConstant.DATE_FORMAT_DAY);
                Date endDate = DateTimeUtil.getDateFrom(starDate, 1, CommonUtilConstant.DATE_FORMAT_YEAR);
                endDateToInput = DateTimeUtil.getDateFrom(endDate, -1, CommonUtilConstant.DATE_FORMAT_DAY);
            }

            distribution.setBalance(startBalance);
            distribution.setEmpData(empData);
            distribution.setEndDate(endDateToInput);
            distribution.setLeave(leave);
            distribution.setStartDate(starDateToInput);
            distribution.setCreatedBy(UserInfoUtil.getUserName());
            distribution.setCreatedOn(new Date());
            distribution.setId(Long.parseLong(RandomNumberUtil.getRandomNumber(9)));
            listDistribution.add(distribution);
            NeracaCuti neracaCuti = new NeracaCuti();
            neracaCuti.setId(Long.parseLong(RandomNumberUtil.getRandomNumber(9)));
            neracaCuti.setDebet(startBalance);
            neracaCuti.setSaldo(startBalance);
            neracaCuti.setLeaveDistribution(distribution);
            neracaCuti.setCreatedBy(UserInfoUtil.getUserName());
            neracaCuti.setCreatedOn(new Date());
            listNeracaCuti.add(neracaCuti);
//            leaveDistributionDao.save(distribution);
//            distribution.set
        }
        leaveDistributionDao.saveBatch(listDistribution);
        neracaCutiDao.saveBacth(listNeracaCuti);

    }

    @Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 30)
    @Override
    public LeaveDistribution getEntityByParamWithDetail(Long empId) throws Exception {
        return leaveDistributionDao.getEntityByParamWithDetail(empId);
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 50)
    public List<LeaveDistribution> getAllDataByEmpIdWithDetail() throws Exception {
        return leaveDistributionDao.getAllDataByEmpIdWithDetail();
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 50)
    public List<LeaveDistribution> getAllDataByEmpIdFetchLeave(Long empDataId) throws Exception {
        return leaveDistributionDao.getAllDataByEmpIdFetchLeave(empDataId);
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 30)
    public LeaveDistribution getEntityByLeaveIdAndEmpDataId(Long leaveId, Long empDataId) throws Exception {
        return leaveDistributionDao.getEntityByLeaveIdAndEmpDataId(leaveId, empDataId);

    }
	

}
