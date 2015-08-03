/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.inkubator.common.util.RandomNumberUtil;
import com.inkubator.datacore.service.impl.IServiceImpl;
import com.inkubator.exception.BussinessException;
import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.dao.EmpDataDao;
import com.inkubator.hrm.dao.LogWtAttendanceRealizationDao;
import com.inkubator.hrm.dao.PayTempOvertimeDao;
import com.inkubator.hrm.dao.WtPeriodeDao;
import com.inkubator.hrm.entity.EmpData;
import com.inkubator.hrm.entity.LogWtAttendanceRealization;
import com.inkubator.hrm.entity.PayTempOvertime;
import com.inkubator.hrm.entity.WtPeriode;
import com.inkubator.hrm.service.PayTempOvertimeService;
import com.inkubator.hrm.web.model.PayTempOvertimeFileModel;
import com.inkubator.hrm.web.search.PayTempOvertimeSearchParameter;
import com.inkubator.securitycore.util.UserInfoUtil;

/**
 *
 * @author deni
 */
@Service(value = "payTempOvertimeService")
@Lazy
public class PayTempOvertimeServiceImpl extends IServiceImpl implements PayTempOvertimeService{

    @Autowired
    private PayTempOvertimeDao payTempOvertimeDao;
    @Autowired
    private EmpDataDao empDataDao;
    @Autowired
    private WtPeriodeDao wtPeriodeDao;
    @Autowired
    private LogWtAttendanceRealizationDao logWtAttendanceRealizationDao;
    
    @Override
    @Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 50)
    public List<PayTempOvertime> getByParam(PayTempOvertimeSearchParameter searchParameter, int firstResult, int maxResults, Order order) throws Exception {
        return payTempOvertimeDao.getByParam(searchParameter, firstResult, maxResults, order);
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 50)
    public Long getTotalByParam(PayTempOvertimeSearchParameter searchParameter) throws Exception {
        return payTempOvertimeDao.getTotalByParam(searchParameter);
    }

    @Override
    public PayTempOvertime getEntiyByPK(String id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public PayTempOvertime getEntiyByPK(Integer id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public PayTempOvertime getEntiyByPK(Long id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void save(PayTempOvertime entity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(PayTempOvertime entity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void saveOrUpdate(PayTempOvertime enntity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public PayTempOvertime saveData(PayTempOvertime entity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public PayTempOvertime updateData(PayTempOvertime entity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public PayTempOvertime saveOrUpdateData(PayTempOvertime entity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public PayTempOvertime getEntityByPkIsActive(String id, Integer isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public PayTempOvertime getEntityByPkIsActive(String id, Byte isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public PayTempOvertime getEntityByPkIsActive(String id, Boolean isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public PayTempOvertime getEntityByPkIsActive(Integer id, Integer isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public PayTempOvertime getEntityByPkIsActive(Integer id, Byte isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public PayTempOvertime getEntityByPkIsActive(Integer id, Boolean isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public PayTempOvertime getEntityByPkIsActive(Long id, Integer isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public PayTempOvertime getEntityByPkIsActive(Long id, Byte isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public PayTempOvertime getEntityByPkIsActive(Long id, Boolean isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void delete(PayTempOvertime entity) throws Exception {
        this.payTempOvertimeDao.delete(entity);
    }

    @Override
    public void softDelete(PayTempOvertime entity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 30)
    public Long getTotalData() throws Exception {
        return payTempOvertimeDao.getTotalData();
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
    public List<PayTempOvertime> getAllData() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<PayTempOvertime> getAllData(Boolean isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<PayTempOvertime> getAllData(Integer isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<PayTempOvertime> getAllData(Byte isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<PayTempOvertime> getAllDataPageAble(int firstResult, int maxResults, Order order) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<PayTempOvertime> getAllDataPageAbleIsActive(int firstResult, int maxResults, Order order, Boolean isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<PayTempOvertime> getAllDataPageAbleIsActive(int firstResult, int maxResults, Order order, Integer isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<PayTempOvertime> getAllDataPageAbleIsActive(int firstResult, int maxResults, Order order, Byte isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void executeBatchFileUpload(PayTempOvertimeFileModel entity) throws Exception {
        //cek apakah data terdapat di db / tidak (tidak boleh duplikat)
        Boolean isInsertable = (payTempOvertimeDao.getAllDataByNik(entity.getNik()) != null ) ? Boolean.FALSE : Boolean.TRUE;
        
        if(isInsertable){
            PayTempOvertime payTempOvertime = new PayTempOvertime();
            payTempOvertime.setId(Long.parseLong(RandomNumberUtil.getRandomNumber(9)));
            payTempOvertime.setOvertime(entity.getOvertime());
            payTempOvertime.setEmpData(empDataDao.getEntityByNik(entity.getNik()));
            payTempOvertime.setCreatedBy(UserInfoUtil.getUserName());
            payTempOvertime.setCreatedOn(new Date());
            payTempOvertimeDao.save(payTempOvertime);
        }
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 50)
    public PayTempOvertime getEntityByPkWithDetail(Long id) throws Exception {
        return payTempOvertimeDao.getEntityByPkWithDetail(id);
    }

	@Override
	@Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void synchronizedOvertimeStatus() throws Exception {
		//validate
		WtPeriode periode = wtPeriodeDao.getEntityByPayrollTypeActive();
		List<LogWtAttendanceRealization> listAttendanceRealization = logWtAttendanceRealizationDao.getAllDataByPeriodId(periode.getId());
		if(StringUtils.equals(periode.getAbsen(), HRMConstant.PERIODE_ABSEN_ACTIVE)){
			throw new BussinessException("payTempAttendanceStatus.error_absent_period_still_active");
		} else if(listAttendanceRealization.isEmpty()){
			throw new BussinessException("payTempAttendanceStatus.error_attendance_realization_empty");
		}
		
		//delete all data PayTempOvertime
		payTempOvertimeDao.deleteAllData();
		
		//synchronize All Data based on active payroll wtPeriode		
		for(LogWtAttendanceRealization attendanceRealization : listAttendanceRealization){
			EmpData empData = empDataDao.getEntiyByPK(attendanceRealization.getEmpDataId());
			PayTempOvertime payTempOvertime =  new PayTempOvertime();
			payTempOvertime.setId(Long.parseLong(RandomNumberUtil.getRandomNumber(9)));
			payTempOvertime.setEmpData(empData);
			payTempOvertime.setOvertime((double) attendanceRealization.getOvertime());
			payTempOvertime.setCreatedBy(UserInfoUtil.getUserName());
			payTempOvertime.setCreatedOn(new Date());
			payTempOvertimeDao.save(payTempOvertime);
		}		
	}
    
}
