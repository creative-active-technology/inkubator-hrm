package com.inkubator.hrm.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.criterion.Order;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import ch.lambdaj.Lambda;

import com.inkubator.common.util.DateTimeUtil;
import com.inkubator.common.util.RandomNumberUtil;
import com.inkubator.datacore.service.impl.IServiceImpl;
import com.inkubator.hrm.dao.CheckInAttendanceDao;
import com.inkubator.hrm.dao.EmpDataDao;
import com.inkubator.hrm.dao.FingerMatchEmpDao;
import com.inkubator.hrm.dao.FingerSwapCapturedDao;
import com.inkubator.hrm.dao.TempJadwalKaryawanDao;
import com.inkubator.hrm.dao.TempProcessReadFingerDao;
import com.inkubator.hrm.dao.WtPeriodeDao;
import com.inkubator.hrm.entity.CheckInAttendance;
import com.inkubator.hrm.entity.EmpData;
import com.inkubator.hrm.entity.FingerMatchEmp;
import com.inkubator.hrm.entity.FingerSwapCaptured;
import com.inkubator.hrm.entity.TempJadwalKaryawan;
import com.inkubator.hrm.entity.TempProcessReadFinger;
import com.inkubator.hrm.entity.WtPeriode;
import com.inkubator.hrm.service.TempProcessReadFingerService;
import com.inkubator.hrm.web.model.DataFingerRealizationModel;
import com.inkubator.hrm.web.search.DataFingerRealizationSearchParameter;
import com.inkubator.securitycore.util.UserInfoUtil;

/**
 *
 * @author rizkykojek
 */
@Service(value = "tempProcessReadFingerService")
@Lazy
public class TempProcessReadFingerServiceImpl extends IServiceImpl implements TempProcessReadFingerService {

	@Autowired
	private TempProcessReadFingerDao tempProcessReadFingerDao;
	@Autowired
	private WtPeriodeDao wtPeriodeDao;
	@Autowired
	private FingerSwapCapturedDao fingerSwapCapturedDao;
	@Autowired
	private CheckInAttendanceDao checkInAttendanceDao;
	@Autowired
	private FingerMatchEmpDao fingerMatchEmpDao;
	@Autowired
	private EmpDataDao empDataDao;
	@Autowired
	private TempJadwalKaryawanDao tempJadwalKaryawanDao;
	
	@Override
	public TempProcessReadFinger getEntiyByPK(String id) throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public TempProcessReadFinger getEntiyByPK(Integer id) throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public TempProcessReadFinger getEntiyByPK(Long id) throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public void save(TempProcessReadFinger entity) throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public void update(TempProcessReadFinger entity) throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public void saveOrUpdate(TempProcessReadFinger enntity) throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public TempProcessReadFinger saveData(TempProcessReadFinger entity)
			throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public TempProcessReadFinger updateData(TempProcessReadFinger entity)
			throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public TempProcessReadFinger saveOrUpdateData(TempProcessReadFinger entity)
			throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public TempProcessReadFinger getEntityByPkIsActive(String id,
			Integer isActive) throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public TempProcessReadFinger getEntityByPkIsActive(String id, Byte isActive)
			throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public TempProcessReadFinger getEntityByPkIsActive(String id,
			Boolean isActive) throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public TempProcessReadFinger getEntityByPkIsActive(Integer id,
			Integer isActive) throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public TempProcessReadFinger getEntityByPkIsActive(Integer id, Byte isActive)
			throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public TempProcessReadFinger getEntityByPkIsActive(Integer id,
			Boolean isActive) throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public TempProcessReadFinger getEntityByPkIsActive(Long id, Integer isActive)
			throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public TempProcessReadFinger getEntityByPkIsActive(Long id, Byte isActive)
			throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public TempProcessReadFinger getEntityByPkIsActive(Long id, Boolean isActive)
			throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public void delete(TempProcessReadFinger entity) throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public void softDelete(TempProcessReadFinger entity) throws Exception {
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
	public List<TempProcessReadFinger> getAllData() throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public List<TempProcessReadFinger> getAllData(Boolean isActive)
			throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public List<TempProcessReadFinger> getAllData(Integer isActive)
			throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public List<TempProcessReadFinger> getAllData(Byte isActive)
			throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public List<TempProcessReadFinger> getAllDataPageAble(int firstResult,
			int maxResults, Order order) throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public List<TempProcessReadFinger> getAllDataPageAbleIsActive(
			int firstResult, int maxResults, Order order, Boolean isActive)
			throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public List<TempProcessReadFinger> getAllDataPageAbleIsActive(
			int firstResult, int maxResults, Order order, Integer isActive)
			throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public List<TempProcessReadFinger> getAllDataPageAbleIsActive(
			int firstResult, int maxResults, Order order, Byte isActive)
			throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	@Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 50)
	public List<TempProcessReadFinger> getByParam(Long empDataId, Long periodeId, int firstResult, int maxResults, Order orderable) throws Exception {
		WtPeriode wtPeriode = wtPeriodeDao.getEntiyByPK(periodeId);
		return tempProcessReadFingerDao.getByParam(empDataId, wtPeriode.getFromPeriode(), wtPeriode.getUntilPeriode(), firstResult, maxResults, orderable);
	}

	@Override
	@Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 50)
	public Long getTotalByParam(Long empDataId, Long periodeId) throws Exception {
		WtPeriode wtPeriode = wtPeriodeDao.getEntiyByPK(periodeId);
		return tempProcessReadFingerDao.getTotalByParam(empDataId, wtPeriode.getFromPeriode(), wtPeriode.getUntilPeriode());
	}

	@Override
	@Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 50)
	public List<DataFingerRealizationModel> getDataFingerRealizationByParam(DataFingerRealizationSearchParameter searchParameter, int firstResult, int maxResults, Order orderable) throws Exception {
		return tempProcessReadFingerDao.getDataFingerRealizationByParam(searchParameter, firstResult, maxResults, orderable);
		
	}

	@Override
	@Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 50)
	public Long getTotalDataFingerRealizationByParam(DataFingerRealizationSearchParameter searchParameter) throws Exception {
		return tempProcessReadFingerDao.getTotalDataFingerRealizationByParam(searchParameter);
		
	}

	@Override
	@Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void synchDataFingerRealization() throws Exception {		
		List<EmpData> empDatas =  empDataDao.getAllDataNotTerminate();
		WtPeriode periode = wtPeriodeDao.getEntityByAbsentTypeActive();

		/** delete all record in that period*/
		tempProcessReadFingerDao.deleteByScheduleDate(periode.getFromPeriode(), periode.getUntilPeriode());
		
		/** sync all record in that period, only saved the record that is working day(exclude OFF day)*/
		for(EmpData empData : empDatas){
			List<FingerMatchEmp> listFingerMatchEmp = fingerMatchEmpDao.getAllDataByNik(empData.getNik());
			List<String> listFingerIndexId = Lambda.extract(listFingerMatchEmp, Lambda.on(FingerMatchEmp.class).getFingerIndexId());
			List<TempJadwalKaryawan> listJadwalKaryawan = tempJadwalKaryawanDao.getAllDataByEmpIdAndPeriodDate(empData.getId(), periode.getFromPeriode(), periode.getUntilPeriode());
			
			for(TempJadwalKaryawan jadwalKaryawan: listJadwalKaryawan){
				if(!StringUtils.equals(jadwalKaryawan.getWtWorkingHour().getCode(), "OFF")){					
					CheckInAttendance checkInAttendance = checkInAttendanceDao.getEntityByEmpDataIdAndCheckDate(empData.getId(), jadwalKaryawan.getTanggalWaktuKerja());					
					this.savingEntity(empData, jadwalKaryawan, checkInAttendance, listFingerIndexId);
				}
			}
		}
		
		System.out.println("Selesai Sync " +  new Date());
	}
	
	private void savingEntity(EmpData empData, TempJadwalKaryawan jadwalKaryawan, CheckInAttendance checkInAttendance, List<String> listFingerIndexId){		
		/** initialization working schedule limit */
		DateTime dtWorkBegin = new DateTime(jadwalKaryawan.getWtWorkingHour().getWorkingHourBegin());
		DateTime dtWorkEnd   = new DateTime(jadwalKaryawan.getWtWorkingHour().getWorkingHourEnd());
		DateTime workingHourBegin = new DateTime(jadwalKaryawan.getTanggalWaktuKerja()).
				plusHours(dtWorkBegin.getHourOfDay()).
				plusMinutes(dtWorkBegin.getMinuteOfHour()).
				plusSeconds(dtWorkBegin.getSecondOfMinute());;
		DateTime workingHourEnd = new DateTime(jadwalKaryawan.getTanggalWaktuKerja()).
				plusHours(dtWorkEnd.getHourOfDay()).
				plusMinutes(dtWorkEnd.getMinuteOfHour()).
				plusSeconds(dtWorkEnd.getSecondOfMinute());
		Date arriveLimitBegin = workingHourBegin.minusMinutes(jadwalKaryawan.getWtWorkingHour().getArriveLimitBegin()).toDate();
		Date arriveLimitEnd   = workingHourBegin.plusMinutes(jadwalKaryawan.getWtWorkingHour().getArriveLimitEnd()).toDate();
		Date goHomeLimitBegin = workingHourEnd.minusMinutes(jadwalKaryawan.getWtWorkingHour().getGoHomeLimitBegin()).toDate();
		Date goHomeLimitEnd   = workingHourEnd.plusMinutes(jadwalKaryawan.getWtWorkingHour().getGoHomeLimitEnd()).toDate();
		
		/** get FingerInCaptured, if employee checkIn several times, then pick the first one(select min) */
		List<FingerSwapCaptured> listFingerInCaptured = fingerSwapCapturedDao.getAllDataByFingerIndexIdAndSwapDatetimeLogBetween(listFingerIndexId, arriveLimitBegin, arriveLimitEnd, Order.desc("swapDatetimeLog"));		
		FingerSwapCaptured fingerInCaptured = Lambda.selectMin(listFingerInCaptured, Lambda.on(FingerSwapCaptured.class).getSwapDatetimeLog());		
		
		/** get FingerOutCaptured, if employee checkOut several times, then pick the last one(select max) */
		List<FingerSwapCaptured> listFingerOutCaptured = fingerSwapCapturedDao.getAllDataByFingerIndexIdAndSwapDatetimeLogBetween(listFingerIndexId, goHomeLimitBegin, goHomeLimitEnd, Order.desc("swapDatetimeLog"));
		FingerSwapCaptured fingerOutCaptured = Lambda.selectMax(listFingerOutCaptured, Lambda.on(FingerSwapCaptured.class).getSwapDatetimeLog());
		
		TempProcessReadFinger tempProcessReadFinger = new TempProcessReadFinger();
		tempProcessReadFinger.setId(Long.parseLong(RandomNumberUtil.getRandomNumber(9)));
		tempProcessReadFinger.setEmpData(empData);
		tempProcessReadFinger.setScheduleDate(jadwalKaryawan.getTanggalWaktuKerja());
		tempProcessReadFinger.setScheduleIn(jadwalKaryawan.getWtWorkingHour().getWorkingHourBegin());
		tempProcessReadFinger.setScheduleOut(jadwalKaryawan.getWtWorkingHour().getWorkingHourEnd());
		if(checkInAttendance != null){
			if(arriveLimitBegin.after(checkInAttendance.getCheckInTime()) && arriveLimitEnd.before(checkInAttendance.getCheckOutTime())){
				tempProcessReadFinger.setWebCheckIn(checkInAttendance.getCheckInTime());
			}
			if(goHomeLimitBegin.after(checkInAttendance.getCheckInTime()) && goHomeLimitEnd.before(checkInAttendance.getCheckOutTime())){
				tempProcessReadFinger.setWebCheckOut(checkInAttendance.getCheckOutTime());
			}			
		}
		if(fingerInCaptured != null){
			tempProcessReadFinger.setFingerIn(fingerInCaptured.getSwapDatetimeLog());
			tempProcessReadFinger.setMarginIn(DateTimeUtil.getTotalMinutesDifference(fingerInCaptured.getSwapDatetimeLog(), workingHourBegin.toDate()));
		}
		if(fingerOutCaptured != null){
			tempProcessReadFinger.setFingerOut(fingerOutCaptured.getSwapDatetimeLog());
			tempProcessReadFinger.setMarginOut(DateTimeUtil.getTotalMinutesDifference(workingHourEnd.toDate(), fingerOutCaptured.getSwapDatetimeLog()));
		}
		tempProcessReadFinger.setIsCorrectionIn(false);
		tempProcessReadFinger.setIsCorrectionOut(false);
		tempProcessReadFinger.setCreatedBy(UserInfoUtil.getUserName());
		tempProcessReadFinger.setCreatedOn(new Date());
		
		tempProcessReadFingerDao.save(tempProcessReadFinger);
	}

}
