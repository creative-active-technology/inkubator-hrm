/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.service.impl;

import ch.lambdaj.Lambda;

import com.inkubator.common.util.DateTimeUtil;
import com.inkubator.common.util.RandomNumberUtil;
import com.inkubator.datacore.service.impl.IServiceImpl;
import com.inkubator.exception.BussinessException;
import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.dao.EmpDataDao;
import com.inkubator.hrm.dao.MedicalCareDao;
import com.inkubator.hrm.dao.TempAttendanceRealizationDao;
import com.inkubator.hrm.dao.TempJadwalKaryawanDao;
import com.inkubator.hrm.dao.TempProcessReadFingerDao;
import com.inkubator.hrm.dao.WtGroupWorkingDao;
import com.inkubator.hrm.dao.WtPeriodeDao;
import com.inkubator.hrm.entity.EmpData;
import com.inkubator.hrm.entity.MedicalCare;
import com.inkubator.hrm.entity.PaySalaryComponent;
import com.inkubator.hrm.entity.PayTempUploadData;
import com.inkubator.hrm.entity.TempAttendanceRealization;
import com.inkubator.hrm.entity.TempJadwalKaryawan;
import com.inkubator.hrm.entity.WtGroupWorking;
import com.inkubator.hrm.entity.WtPeriode;
import com.inkubator.hrm.service.TempAttendanceRealizationService;
import com.inkubator.hrm.web.model.TempAttendanceRealizationViewModel;
import com.inkubator.securitycore.util.UserInfoUtil;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

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
@Service(value = "tempAttendanceRealizationService")
@Lazy
public class TempAttendanceRealizationServiceImpl extends IServiceImpl implements TempAttendanceRealizationService {
    
    @Autowired
    private TempAttendanceRealizationDao tempAttendanceRealizationDao;
    
    @Autowired
    private EmpDataDao empDataDao;
    
    @Autowired
    private WtPeriodeDao wtPeriodeDao;
    
    @Autowired
    private WtGroupWorkingDao wtGroupWorkingDao;
    
    @Autowired
    private TempProcessReadFingerDao tempProcessReadFingerDao;
    
    @Autowired
    private MedicalCareDao medicalCareDao;
    
    @Autowired
    private TempJadwalKaryawanDao tempJadwalKaryawanDao;

    @Override
    public TempAttendanceRealization getEntiyByPK(String string) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public TempAttendanceRealization getEntiyByPK(Integer intgr) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public TempAttendanceRealization getEntiyByPK(Long l) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void save(TempAttendanceRealization t) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(TempAttendanceRealization t) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void saveOrUpdate(TempAttendanceRealization t) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public TempAttendanceRealization saveData(TempAttendanceRealization t) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public TempAttendanceRealization updateData(TempAttendanceRealization t) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public TempAttendanceRealization saveOrUpdateData(TempAttendanceRealization t) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public TempAttendanceRealization getEntityByPkIsActive(String string, Integer intgr) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public TempAttendanceRealization getEntityByPkIsActive(String string, Byte b) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public TempAttendanceRealization getEntityByPkIsActive(String string, Boolean bln) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public TempAttendanceRealization getEntityByPkIsActive(Integer intgr, Integer intgr1) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public TempAttendanceRealization getEntityByPkIsActive(Integer intgr, Byte b) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public TempAttendanceRealization getEntityByPkIsActive(Integer intgr, Boolean bln) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public TempAttendanceRealization getEntityByPkIsActive(Long l, Integer intgr) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public TempAttendanceRealization getEntityByPkIsActive(Long l, Byte b) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public TempAttendanceRealization getEntityByPkIsActive(Long l, Boolean bln) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(TempAttendanceRealization t) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void softDelete(TempAttendanceRealization t) throws Exception {
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
    public List<TempAttendanceRealization> getAllData() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<TempAttendanceRealization> getAllData(Boolean bln) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<TempAttendanceRealization> getAllData(Integer intgr) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<TempAttendanceRealization> getAllData(Byte b) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<TempAttendanceRealization> getAllDataPageAble(int i, int i1, Order order) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<TempAttendanceRealization> getAllDataPageAbleIsActive(int i, int i1, Order order, Boolean bln) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<TempAttendanceRealization> getAllDataPageAbleIsActive(int i, int i1, Order order, Integer intgr) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<TempAttendanceRealization> getAllDataPageAbleIsActive(int i, int i1, Order order, Byte b) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 50)
    public List<TempAttendanceRealizationViewModel> getListTempAttendanceRealizationViewModelByWtPeriodId(Long wtPeriodId, int firstResult, int maxResults, Order orderable) throws Exception {
        return tempAttendanceRealizationDao.getListTempAttendanceRealizationViewModelByWtPeriodId(wtPeriodId, firstResult, maxResults, orderable);
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 30)
    public Long getTotalListTempAttendanceRealizationViewModelByWtPeriodId(Long wtPeriodId) throws Exception {
        return tempAttendanceRealizationDao.getTotalListTempAttendanceRealizationViewModelByWtPeriodId(wtPeriodId);
    }

	@Override
	@Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public TempAttendanceRealizationViewModel calculateEmpTempAttendanceRealization(Long empDataId, Long wtPeriodId) throws Exception {		
		
		TempAttendanceRealizationViewModel tempAttendanceRealizationViewModel = new TempAttendanceRealizationViewModel();
		EmpData empData = empDataDao.getByEmpIdWithDetail(empDataId);
		if(null == empData.getWtGroupWorking()){
			throw new BussinessException("workingTime.attendance_realization_calc_error_emp_with_null_wt_group_working_found");
		}
		WtGroupWorking wtGroupWorking = wtGroupWorkingDao.getEntiyByPK(empData.getWtGroupWorking().getId());
		WtPeriode wtPeriode = wtPeriodeDao.getEntiyByPK(wtPeriodId);
		
		Integer attendanceDaysSchedule = wtPeriode.getWorkingDays();
		Integer attendanceDaysPresent = tempProcessReadFingerDao.getEmpTotalAttendanceBetweenDateFromAndDateUntill(empDataId, wtPeriode.getFromPeriode(), wtPeriode.getUntilPeriode()).intValue();
		List<MedicalCare> listMedicalCares = medicalCareDao.getListWhereStartDateBetweenDateFromAndDateUntillByEmpId(empDataId, wtPeriode.getFromPeriode(), wtPeriode.getUntilPeriode());
		
		Integer sick = calculateAndCheckTotalSick(listMedicalCares, wtPeriode, empDataId);		
		Integer leave = 0;
		Float overtime = 0f;
		Integer duty = 0;
		Integer permit = 0;
		
		tempAttendanceRealizationViewModel.setId(Long.parseLong(RandomNumberUtil.getRandomNumber(9)));
		tempAttendanceRealizationViewModel.setAbsenStatus(HRMConstant.WT_PERIOD_STATUS_ACTIVE);
		tempAttendanceRealizationViewModel.setAttendanceDaysPresent(attendanceDaysPresent);
		tempAttendanceRealizationViewModel.setAttendanceDaysSchedule(attendanceDaysSchedule);
		//tempAttendanceRealizationViewModel.setCreatedBy(UserInfoUtil.getUserName());
		//tempAttendanceRealizationViewModel.setCreatedOn(new Date());
		tempAttendanceRealizationViewModel.setDuty(duty);
		tempAttendanceRealizationViewModel.setEmpId(empDataId);
		tempAttendanceRealizationViewModel.setEmpName(empData.getBioData().getFullName());
		tempAttendanceRealizationViewModel.setLeaves(leave);
		tempAttendanceRealizationViewModel.setNik(empData.getNik());
		tempAttendanceRealizationViewModel.setOverTime(overtime);
		tempAttendanceRealizationViewModel.setPermit(permit);
		tempAttendanceRealizationViewModel.setSick(sick);
		tempAttendanceRealizationViewModel.setWtGroupWorkingId(wtGroupWorking.getId());
		tempAttendanceRealizationViewModel.setWtGroupWorkingName(wtGroupWorking.getName());
		tempAttendanceRealizationViewModel.setWtPeriodId(wtPeriodId);
		
		return tempAttendanceRealizationViewModel;
	}
	
	
	private Integer calculateAndCheckTotalSick(List<MedicalCare> listMedicalCares, WtPeriode wtPeriode, Long empDataId){
		Integer totalSick = 0;
		List<Date> listDateInSelectedPeriod = getRangeOfDates(wtPeriode.getFromPeriode(), wtPeriode.getUntilPeriode());
		for(MedicalCare medicalCare : listMedicalCares){
			int totalDays = medicalCare.getTotalDays();
			
			int compareResult = medicalCare.getEndDate().compareTo(wtPeriode.getUntilPeriode());
			List<Date> listSickDates = getRangeOfDates(medicalCare.getStartDate(), medicalCare.getEndDate());
			
			if(compareResult <= 0){
				
				for(Date sickDate : listSickDates){
					List<TempJadwalKaryawan> listJadwalLibur = tempJadwalKaryawanDao.getAllDataByEmpIdAndPeriodDateAndOffDay(empDataId, wtPeriode.getFromPeriode(), wtPeriode.getUntilPeriode());
					if(!listJadwalLibur.isEmpty()){
						
						List<Date> listDateLibur = Lambda.extract(listJadwalLibur, Lambda.on(TempJadwalKaryawan.class).getTanggalWaktuKerja());
						Collections.sort(listDateLibur);
						int index = Collections.binarySearch(listDateLibur, sickDate, new MyDateComparator());
						
						if(index >= 0){
							totalDays --;
						}
						
					}else{
						
					}
				}
			}else{
				Collections.sort(listSickDates);
				for(Date sickDate : listSickDates){
					int index = Collections.binarySearch(listDateInSelectedPeriod, sickDate, new MyDateComparator());
					
					if(index < 0){
						totalDays --;
					}
				}
			}
			
			totalSick += totalDays;
		}
		
		return totalSick;
	}
	
	
	private static List<Date> getRangeOfDates(Date firstDate, Date secondDate) {
		List<Date> result = new ArrayList<Date>();
		Calendar cal = Calendar.getInstance();
		Date left;
		Date right;

		// Compare the dates to see which is less
		if (firstDate.compareTo(secondDate) < 0) {
			left = firstDate;
			right = secondDate;
		}
		else {
			right = firstDate;
			left = secondDate;
		}

		cal.clear();
		cal.setTime(left);  // Seed the calendar with the starting date

		for (int i = 0; left.compareTo(right) < 0; i++) {
			result.add(left);           // Add the date iterator value to the result set
			cal.add(Calendar.DATE, 1);  // Add one day
			left = cal.getTime();       // Update the date iterator to the new date
		}

		return result;
	}
	
	class MyDateComparator implements Comparator<Date> {
	      DateFormat DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy");

	    public int compare(Date d1, Date d2) {
	        return DATE_FORMAT.format(d1).compareTo(DATE_FORMAT.format(d2));
	    }
	}

	@Override
	@Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor =Exception.class)
	public void executeBatchFileUpload(TempAttendanceRealizationViewModel model) throws Exception {
		//Boolean isInsertable = this.payTempUploadDataDao.getEntityByNikAndComponentId(model.getNik(), model.getPaySalaryComponentId()) == null;
		Boolean isInsertable = Boolean.TRUE;
		//skip jika data sudah ada di database(tidak boleh duplikat)
		if(isInsertable) {
			EmpData empData = empDataDao.getEntiyByPK(model.getEmpId());
			WtGroupWorking wtGroupWorking = wtGroupWorkingDao.getEntiyByPK(model.getWtGroupWorkingId());
			WtPeriode wtPeriode = wtPeriodeDao.getEntiyByPK(model.getWtPeriodId());
			
			
			if(empData!= null ) {
				TempAttendanceRealization entity = new TempAttendanceRealization();
				entity.setId(Long.parseLong(RandomNumberUtil.getRandomNumber(9)));
				entity.setAttendanceDaysPresent(model.getAttendanceDaysPresent());
				entity.setAttendanceDaysSchedule(model.getAttendanceDaysSchedule());
				entity.setCreatedBy(model.getCreatedBy());
				entity.setCreatedOn(new Date());
				entity.setDuty(model.getDuty());
				entity.setEmpData(empData);
				entity.setLeave(model.getLeaves());
				entity.setOvertime(model.getOverTime());
				entity.setPermit(model.getPermit());
				entity.setSick(model.getSick());
				entity.setWtGroupWorking(wtGroupWorking);
				entity.setWtPeriod(wtPeriode);
				
		        this.tempAttendanceRealizationDao.save(entity);
			}
		}		
	}

    
}
