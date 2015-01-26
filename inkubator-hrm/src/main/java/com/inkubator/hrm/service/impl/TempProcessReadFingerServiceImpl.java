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
	@Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 30)
	public TempProcessReadFinger getEntiyByPK(Long id) throws Exception {
		return tempProcessReadFingerDao.getEntiyByPK(id);

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
	public List<TempProcessReadFinger> getByParam(Long empDataId, int firstResult, int maxResults, Order orderable) throws Exception {
		return tempProcessReadFingerDao.getByParam(empDataId, firstResult, maxResults, orderable);
	}

	@Override
	@Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 50)
	public Long getTotalByParam(Long empDataId) throws Exception {
		return tempProcessReadFingerDao.getTotalByParam(empDataId);
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
		
		/** sync all record in that period, only saved the record that is working day schedule(exclude OFF day)*/
		for(EmpData empData : empDatas){
			List<String> listFingerIndexId = this.getFingerIndexIds(empData.getNik());
			List<TempJadwalKaryawan> listJadwalKaryawan = tempJadwalKaryawanDao.getAllDataByEmpIdAndPeriodDate(empData.getId(), periode.getFromPeriode(), periode.getUntilPeriode());
			
			for(TempJadwalKaryawan jadwalKaryawan: listJadwalKaryawan){
				if(!StringUtils.equals(jadwalKaryawan.getWtWorkingHour().getCode(), "OFF")){					
					CheckInAttendance checkInAttendance = checkInAttendanceDao.getEntityByEmpDataIdAndCheckDate(empData.getId(), jadwalKaryawan.getTanggalWaktuKerja());					
					this.savingEntity(empData, jadwalKaryawan, checkInAttendance, listFingerIndexId);
				}
			}
		}
	}
	
	private void savingEntity(EmpData empData, TempJadwalKaryawan jadwalKaryawan, CheckInAttendance checkInAttendance, List<String> listFingerIndexId){		
		/** initialization working schedule limit */
		DateTime workingHourBegin = this.getExactWorkingSchedule(jadwalKaryawan.getTanggalWaktuKerja(), jadwalKaryawan.getWtWorkingHour().getWorkingHourBegin());
		DateTime workingHourEnd = this.getExactWorkingSchedule(jadwalKaryawan.getTanggalWaktuKerja(), jadwalKaryawan.getWtWorkingHour().getWorkingHourEnd());		
		Date arriveLimitBegin = workingHourBegin.minusMinutes(jadwalKaryawan.getWtWorkingHour().getArriveLimitBegin()).toDate();
		Date arriveLimitEnd   = workingHourBegin.plusMinutes(jadwalKaryawan.getWtWorkingHour().getArriveLimitEnd()).toDate();
		Date goHomeLimitBegin = workingHourEnd.minusMinutes(jadwalKaryawan.getWtWorkingHour().getGoHomeLimitBegin()).toDate();
		Date goHomeLimitEnd   = workingHourEnd.plusMinutes(jadwalKaryawan.getWtWorkingHour().getGoHomeLimitEnd()).toDate();
			
		/** get finger swap captured from (several) data in (several) machine */
		FingerSwapCaptured fingerInCaptured = this.getFingerInCaptured(listFingerIndexId, arriveLimitBegin, arriveLimitEnd);
		FingerSwapCaptured fingerOutCaptured = this.getFingerOutCaptured(listFingerIndexId, goHomeLimitBegin, goHomeLimitEnd);
		
		/** Start saving tempProcessReadFinger entity.
		 *  webCheckIn or fingerCheckIn can be null
		 *  (yang berarti ada kemungkinan employee tidak masuk atau mereka checkIn nya di luar limit waktu workingHour)*/
		TempProcessReadFinger tempProcessReadFinger = new TempProcessReadFinger();
		tempProcessReadFinger.setId(Long.parseLong(RandomNumberUtil.getRandomNumber(9)));
		tempProcessReadFinger.setEmpData(empData);
		tempProcessReadFinger.setWorkingHourName(jadwalKaryawan.getWtWorkingHour().getName());
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

	@Override
	@Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void doCorrectionIn(Long id, Boolean isCorrection) throws Exception {
		TempProcessReadFinger tempProcessReadFinger = tempProcessReadFingerDao.getEntiyByPK(id);
		tempProcessReadFinger.setIsCorrectionIn(isCorrection);
		if(isCorrection){
			DateTime scheduleDate = new DateTime(tempProcessReadFinger.getScheduleDate());
			DateTime scheduleIn =  new DateTime(tempProcessReadFinger.getScheduleIn());
			scheduleDate = scheduleDate.plusHours(scheduleIn.getHourOfDay()).plusMinutes(scheduleIn.getMinuteOfHour()).plusSeconds(scheduleIn.getSecondOfMinute());
			
			tempProcessReadFinger.setFingerIn(scheduleDate.toDate());
			tempProcessReadFinger.setMarginIn(0);
			
		} else {			
			TempJadwalKaryawan jadwalKaryawan =  tempJadwalKaryawanDao.getEntityByEmpDataIdAndTanggalWaktuKerja(tempProcessReadFinger.getEmpData().getId(), tempProcessReadFinger.getScheduleDate());						
			DateTime workingHourBegin = this.getExactWorkingSchedule(jadwalKaryawan.getTanggalWaktuKerja(), jadwalKaryawan.getWtWorkingHour().getWorkingHourBegin());			
			Date arriveLimitBegin = workingHourBegin.minusMinutes(jadwalKaryawan.getWtWorkingHour().getArriveLimitBegin()).toDate();
			Date arriveLimitEnd   = workingHourBegin.plusMinutes(jadwalKaryawan.getWtWorkingHour().getArriveLimitEnd()).toDate();
			
			List<String> listFingerIndexId = this.getFingerIndexIds(tempProcessReadFinger.getEmpData().getNik());
			FingerSwapCaptured fingerInCaptured = this.getFingerInCaptured(listFingerIndexId, arriveLimitBegin, arriveLimitEnd);
			
			if(fingerInCaptured != null){
				tempProcessReadFinger.setFingerIn(fingerInCaptured.getSwapDatetimeLog());
				tempProcessReadFinger.setMarginIn(DateTimeUtil.getTotalMinutesDifference(fingerInCaptured.getSwapDatetimeLog(), workingHourBegin.toDate()));
			} else {
				tempProcessReadFinger.setFingerIn(null);
				tempProcessReadFinger.setMarginIn(null);
			}
		}
		
		tempProcessReadFinger.setUpdatedBy(UserInfoUtil.getUserName());
		tempProcessReadFinger.setUpdatedOn(new Date());
		
		tempProcessReadFingerDao.update(tempProcessReadFinger);
	}

	@Override
	@Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void doCorrectionOut(Long id, Boolean isCorrection) throws Exception {
		TempProcessReadFinger tempProcessReadFinger = tempProcessReadFingerDao.getEntiyByPK(id);
		tempProcessReadFinger.setIsCorrectionOut(isCorrection);
		if(isCorrection){
			DateTime scheduleDate = new DateTime(tempProcessReadFinger.getScheduleDate());
			DateTime scheduleOut =  new DateTime(tempProcessReadFinger.getScheduleOut());
			scheduleDate = scheduleDate.plusHours(scheduleOut.getHourOfDay()).plusMinutes(scheduleOut.getMinuteOfHour()).plusSeconds(scheduleOut.getSecondOfMinute());
			
			tempProcessReadFinger.setFingerOut(scheduleDate.toDate());
			tempProcessReadFinger.setMarginOut(0);
			
		} else {
			TempJadwalKaryawan jadwalKaryawan =  tempJadwalKaryawanDao.getEntityByEmpDataIdAndTanggalWaktuKerja(tempProcessReadFinger.getEmpData().getId(), tempProcessReadFinger.getScheduleDate());					
			DateTime workingHourEnd = this.getExactWorkingSchedule(jadwalKaryawan.getTanggalWaktuKerja(), jadwalKaryawan.getWtWorkingHour().getWorkingHourEnd());			
			Date goHomeLimitBegin = workingHourEnd.minusMinutes(jadwalKaryawan.getWtWorkingHour().getGoHomeLimitBegin()).toDate();
			Date goHomeLimitEnd   = workingHourEnd.plusMinutes(jadwalKaryawan.getWtWorkingHour().getGoHomeLimitEnd()).toDate();
			
			List<String> listFingerIndexId = this.getFingerIndexIds(tempProcessReadFinger.getEmpData().getNik());
			FingerSwapCaptured fingerOutCaptured = this.getFingerOutCaptured(listFingerIndexId, goHomeLimitBegin, goHomeLimitEnd);
			
			if(fingerOutCaptured != null) {
				tempProcessReadFinger.setFingerOut(fingerOutCaptured.getSwapDatetimeLog());
				tempProcessReadFinger.setMarginOut(DateTimeUtil.getTotalMinutesDifference(fingerOutCaptured.getSwapDatetimeLog(), workingHourEnd.toDate()));
			} else {
				tempProcessReadFinger.setFingerOut(null);
				tempProcessReadFinger.setMarginOut(null);
			}
		}
		
		tempProcessReadFinger.setUpdatedBy(UserInfoUtil.getUserName());
		tempProcessReadFinger.setUpdatedOn(new Date());
		
		tempProcessReadFingerDao.update(tempProcessReadFinger);
	}
	
	private DateTime getExactWorkingSchedule(Date scheduleDate, Date workingHour){		
		DateTime dtWorkingHour   = new DateTime(workingHour);		
		DateTime dtWorkingSchedule = new DateTime(scheduleDate).
				plusHours(dtWorkingHour.getHourOfDay()).
				plusMinutes(dtWorkingHour.getMinuteOfHour()).
				plusSeconds(dtWorkingHour.getSecondOfMinute());
		
		return dtWorkingSchedule;
	}
	
	private List<String> getFingerIndexIds(String nik){
		List<FingerMatchEmp> listFingerMatchEmp = fingerMatchEmpDao.getAllDataByNik(nik);
		return Lambda.extract(listFingerMatchEmp, Lambda.on(FingerMatchEmp.class).getFingerIndexId());
	}
	
	private FingerSwapCaptured getFingerInCaptured(List<String> listFingerIndexId, Date arriveLimitBegin, Date arriveLimitEnd){
		return this.getFingerSwapCaptured(listFingerIndexId, arriveLimitBegin, arriveLimitEnd, true);
	}
	
	private FingerSwapCaptured getFingerOutCaptured(List<String> listFingerIndexId, Date goHomeLimitBegin, Date goHomeLimitEnd){
		return this.getFingerSwapCaptured(listFingerIndexId, goHomeLimitBegin, goHomeLimitEnd, false);
	}
			
	private FingerSwapCaptured getFingerSwapCaptured(List<String> listFingerIndexId, Date limitBegin, Date limitEnd, Boolean isFingerInCaptured){
		FingerSwapCaptured fingerCaptured = null;
		List<FingerSwapCaptured> listFingerCaptured = fingerSwapCapturedDao.getAllDataByFingerIndexIdAndSwapDatetimeLogBetween(listFingerIndexId, limitBegin, limitEnd, Order.desc("swapDatetimeLog"));		
		if(isFingerInCaptured) {
			/** get FingerInCaptured, if employee checkIn several times, then pick the first one(select min) */
			fingerCaptured = Lambda.selectMin(listFingerCaptured, Lambda.on(FingerSwapCaptured.class).getSwapDatetimeLog());
		} else {
			/** get FingerOutCaptured, if employee checkOut several times, then pick the last one(select max) */
			fingerCaptured = Lambda.selectMax(listFingerCaptured, Lambda.on(FingerSwapCaptured.class).getSwapDatetimeLog());
		}
		
		return fingerCaptured;
	}

}
