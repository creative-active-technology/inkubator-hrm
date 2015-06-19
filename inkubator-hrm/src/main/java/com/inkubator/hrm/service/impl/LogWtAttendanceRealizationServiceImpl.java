/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.service.impl;

import com.inkubator.common.util.DateTimeUtil;
import com.inkubator.common.util.RandomNumberUtil;
import com.inkubator.datacore.service.impl.IServiceImpl;
import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.dao.LogWtAttendanceRealizationDao;
import com.inkubator.hrm.dao.TempAttendanceRealizationDao;
import com.inkubator.hrm.dao.TempProcessReadFingerDao;
import com.inkubator.hrm.dao.WtHolidayDao;
import com.inkubator.hrm.dao.WtPeriodeDao;
import com.inkubator.hrm.entity.LogWtAttendanceRealization;
import com.inkubator.hrm.entity.WtPeriode;
import com.inkubator.hrm.service.LogWtAttendanceRealizationService;
import com.inkubator.hrm.web.model.TempAttendanceRealizationViewModel;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.time.DateUtils;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Ahmad Mudzakkir Amal
 */
@Service(value = "logWtAttendanceRealizationService")
@Lazy
public class LogWtAttendanceRealizationServiceImpl extends IServiceImpl implements LogWtAttendanceRealizationService {
    
    @Autowired
    private LogWtAttendanceRealizationDao logWtAttendanceRealizationDao;
    @Autowired
    private TempProcessReadFingerDao tempProcessReadFingerDao;
    @Autowired
    private TempAttendanceRealizationDao tempAttendanceRealizationDao;
    @Autowired
    private WtHolidayDao wtHolidayDao;    
    @Autowired
    private WtPeriodeDao wtPeriodeDao;

    @Override
    public LogWtAttendanceRealization getEntiyByPK(String string) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public LogWtAttendanceRealization getEntiyByPK(Integer intgr) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public LogWtAttendanceRealization getEntiyByPK(Long l) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void save(LogWtAttendanceRealization t) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(LogWtAttendanceRealization t) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void saveOrUpdate(LogWtAttendanceRealization t) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public LogWtAttendanceRealization saveData(LogWtAttendanceRealization t) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public LogWtAttendanceRealization updateData(LogWtAttendanceRealization t) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public LogWtAttendanceRealization saveOrUpdateData(LogWtAttendanceRealization t) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public LogWtAttendanceRealization getEntityByPkIsActive(String string, Integer intgr) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public LogWtAttendanceRealization getEntityByPkIsActive(String string, Byte b) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public LogWtAttendanceRealization getEntityByPkIsActive(String string, Boolean bln) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public LogWtAttendanceRealization getEntityByPkIsActive(Integer intgr, Integer intgr1) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public LogWtAttendanceRealization getEntityByPkIsActive(Integer intgr, Byte b) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public LogWtAttendanceRealization getEntityByPkIsActive(Integer intgr, Boolean bln) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public LogWtAttendanceRealization getEntityByPkIsActive(Long l, Integer intgr) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public LogWtAttendanceRealization getEntityByPkIsActive(Long l, Byte b) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public LogWtAttendanceRealization getEntityByPkIsActive(Long l, Boolean bln) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(LogWtAttendanceRealization t) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void softDelete(LogWtAttendanceRealization t) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Long getTotalData() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Long getTotalDataIsActive(Boolean bln) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Long getTotalDataIsActive(Integer intgr) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Long getTotalDataIsActive(Byte b) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<LogWtAttendanceRealization> getAllData() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<LogWtAttendanceRealization> getAllData(Boolean bln) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<LogWtAttendanceRealization> getAllData(Integer intgr) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<LogWtAttendanceRealization> getAllData(Byte b) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<LogWtAttendanceRealization> getAllDataPageAble(int i, int i1, Order order) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<LogWtAttendanceRealization> getAllDataPageAbleIsActive(int i, int i1, Order order, Boolean bln) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<LogWtAttendanceRealization> getAllDataPageAbleIsActive(int i, int i1, Order order, Integer intgr) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<LogWtAttendanceRealization> getAllDataPageAbleIsActive(int i, int i1, Order order, Byte b) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 50)
    public List<TempAttendanceRealizationViewModel> getListTempAttendanceRealizationViewModelByWtPeriodId(Long wtPeriodId, int firstResult, int maxResults, Order orderable) throws Exception {
        WtPeriode wtPeriode = wtPeriodeDao.getEntiyByPK(wtPeriodId);        
        List<TempAttendanceRealizationViewModel> listTempAttendanceRealizationViewModel = logWtAttendanceRealizationDao.getListTempAttendanceRealizationViewModelByWtPeriodId(wtPeriodId, firstResult, maxResults, orderable);
        for(TempAttendanceRealizationViewModel tempModel : listTempAttendanceRealizationViewModel){
            tempModel.setAbsenStatus(wtPeriode.getAbsen());
        }
        return listTempAttendanceRealizationViewModel;
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 30)
    public Long getTotalListTempAttendanceRealizationViewModelByWtPeriodId(Long wtPeriodId) throws Exception {
        return logWtAttendanceRealizationDao.getTotalListTempAttendanceRealizationViewModelByWtPeriodId(wtPeriodId);
    }

	@Override
	@Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void deleteByPeriodId(Long periodId) throws Exception {
		logWtAttendanceRealizationDao.deleteByPeriodId(periodId);
		
	}
	
	@Override
	@Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void afterMonthEndProcess() throws Exception {
		
		/** update payrollType status di periode yg lama menjadi VOID */
		WtPeriode wtPeriode = wtPeriodeDao.getEntityByAbsentTypeActive();
		wtPeriode.setAbsen(HRMConstant.PERIODE_ABSEN_VOID);
		wtPeriode.setUpdatedOn(new Date());
		wtPeriode.setUpdatedBy(HRMConstant.SYSTEM_ADMIN);
		wtPeriodeDao.update(wtPeriode);		
		
		/** dapatkan range (untilPeriode dan fromPeriode) untuk periode yg baru */
		Date untilPeriode = wtPeriode.getUntilPeriode();
		Date fromPeriode = DateUtils.addDays(untilPeriode, 1);
		int lastDateOfMonth = DateUtils.toCalendar(untilPeriode).getActualMaximum(Calendar.DAY_OF_MONTH);
		if(lastDateOfMonth == DateUtils.toCalendar(untilPeriode).get(Calendar.DATE)){			
			untilPeriode = DateUtils.addMonths(untilPeriode, 1);
			lastDateOfMonth = DateUtils.toCalendar(untilPeriode).getActualMaximum(Calendar.DAY_OF_MONTH);
			untilPeriode = DateUtils.setDays(untilPeriode, lastDateOfMonth);
		} else {
			untilPeriode = DateUtils.addMonths(untilPeriode, 1);
		}
		
		/** adding process or update the entity if already exist */
		WtPeriode wtp = wtPeriodeDao.getEntityByFromPeriodeAndUntilPeriode(fromPeriode, untilPeriode);
		if(wtp == null) {
			wtp = new WtPeriode();
			wtp.setId(Long.parseLong(RandomNumberUtil.getRandomNumber(9)));
			wtp.setTahun(String.valueOf(DateUtils.toCalendar(fromPeriode).get(Calendar.YEAR)));
			wtp.setBulan(DateUtils.toCalendar(fromPeriode).get(Calendar.MONTH)+1);
			wtp.setFromPeriode(fromPeriode);
			wtp.setUntilPeriode(untilPeriode);
			wtp.setAbsen(HRMConstant.PERIODE_ABSEN_ACTIVE);
			wtp.setPayrollType(HRMConstant.PERIODE_PAYROLL_NOT_ACTIVE);
			long totalHoliday = wtHolidayDao.getTotalBetweenDate(fromPeriode, untilPeriode);
			int workingDays = DateTimeUtil.getTotalWorkingDay(fromPeriode, untilPeriode, (int)totalHoliday, 0);
			wtp.setWorkingDays(workingDays);
			wtp.setCreatedOn(new Date());
			wtp.setCreatedBy(HRMConstant.SYSTEM_ADMIN);
			wtPeriodeDao.save(wtp);
		} else {
			wtp.setAbsen(HRMConstant.PERIODE_ABSEN_ACTIVE);
			wtp.setUpdatedOn(new Date());
			wtp.setUpdatedBy(HRMConstant.SYSTEM_ADMIN);
			wtPeriodeDao.update(wtp);
		}		
		
		/** delete all the record in the temporary table **/
		tempProcessReadFingerDao.deleteAllData();
		tempAttendanceRealizationDao.deleteAllData();
	}
     
}
