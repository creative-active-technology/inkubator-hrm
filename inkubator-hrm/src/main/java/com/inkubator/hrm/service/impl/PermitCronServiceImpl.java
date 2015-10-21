package com.inkubator.hrm.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.time.DateUtils;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.inkubator.common.util.RandomNumberUtil;
import com.inkubator.datacore.service.impl.IServiceImpl;
import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.dao.NeracaPermitDao;
import com.inkubator.hrm.dao.PermitClassificationDao;
import com.inkubator.hrm.dao.PermitDistributionDao;
import com.inkubator.hrm.entity.NeracaPermit;
import com.inkubator.hrm.entity.PermitClassification;
import com.inkubator.hrm.entity.PermitDistribution;
import com.inkubator.hrm.service.PermitCronService;

public class PermitCronServiceImpl extends IServiceImpl implements PermitCronService {

	@Autowired
	private PermitClassificationDao permitClassificationDao;
	@Autowired
	private PermitDistributionDao permitDistributionDao;
	@Autowired
	private NeracaPermitDao neracaPermitDao;
	
	@Override
    @Scheduled(cron = "${cron.process.of.adding.permit.balance}")
    @Transactional(readOnly = false, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
	public void processAddingOfPermitBalance() throws Exception {	
		/** tanggal sekarang(current) dikurangi satu, karena pengecekan scheduler itu di tanggal endOfPermit 
		 *  ex: tanggal endOfPermit 15-01-2014, dan sistem running scheduler setiap jam 02.00, 
		 *  maka ketika tanggal di sistem 16-01-2014 02:00, barulah diexecute penambahan cuti-nya  */
		DateTime current = new DateTime();
		DateTime oneDayBeforeCurrent = current.minusDays(1); 
		
		/** Dapatkan semua data ijin yang statusnya aktif dan tidak berlaku untuk satu kali */
		List<PermitClassification> permitClassifications = permitClassificationDao.getAllDataByIsActiveAndOnePerEmployee(Boolean.TRUE, Boolean.FALSE);
		for(PermitClassification permit : permitClassifications){
			Integer availability = permit.getAvailibility();
			Integer qty = permit.getQuantity();
			
			List<PermitDistribution> listDistribution = permitDistributionDao.getAllDataByPermitClassificationIdAndIsActiveEmployee(permit.getId());
			for(PermitDistribution distribution : listDistribution){	
				DateTime endDate = new DateTime(distribution.getEndDate());
				DateTime startDate = new DateTime(distribution.getStartDate());
				
				if (availability == HRMConstant.PERMIT_AVALILIBILITY_PER_MONTH){					
					
					/** set balance to 0 jika sudah di akhir periode */					
					if(DateUtils.isSameDay(oneDayBeforeCurrent.toDate(), endDate.toDate())){
						this.creditNeracaPermit(distribution.getBalance(), 0, distribution);
						this.savePermitDistribution(0, distribution);
					}
					
					/** cek apakah tanggal start dan current merupakan tanggal di akhir bulan */					
					Boolean isLastDayOfStartMonth =  startDate.getDayOfMonth() == startDate.dayOfMonth().withMaximumValue().getDayOfMonth();
					Boolean isLastDayOfCurrentMonth =  current.getDayOfMonth() == current.dayOfMonth().withMaximumValue().getDayOfMonth();
					
					/** proses debet neraca 
					 *  1.jika tanggal startDate dari penambahan ijin sama dengan tanggal currentDate, atau
					 *  2.jika tanggal startDate dan current merupakan tanggal di akhir bulan */
					startDate = startDate.withMonthOfYear(current.getMonthOfYear()).withYear(current.getYear());
					if (DateUtils.isSameDay(current.toDate(), startDate.toDate())) {
						double debet = qty;
						double balance = distribution.getBalance() + debet;	
						
						this.debetNeracaPermit(debet, balance, distribution);
						this.savePermitDistribution(balance, distribution);
						
					} else if(isLastDayOfStartMonth && isLastDayOfCurrentMonth){
						double debet = qty;
						double balance = distribution.getBalance() + debet;	
						
						this.debetNeracaPermit(debet, balance, distribution);
						this.savePermitDistribution(balance, distribution);
					}
					
				} else  if (availability == HRMConstant.PERMIT_AVALILIBILITY_PER_DATE){				
					
					/** set balance to 0 jika sudah di akhir periode */					
					if(DateUtils.isSameDay(oneDayBeforeCurrent.toDate(), endDate.toDate())){
						this.creditNeracaPermit(distribution.getBalance(), 0, distribution);
						this.savePermitDistribution(0, distribution);
					}
					
					/** proses debet neraca jika tanggal spesifik dari penambahan ijin sama dengan tanggal currentDate */
					Date availabilityAtSpecificDate = current.withDayOfMonth(permit.getDateIncreased()).toDate();
					if(DateUtils.isSameDay(current.toDate(), availabilityAtSpecificDate)){
						double debet = qty;
						double balance = distribution.getBalance() + debet;	
						
						this.debetNeracaPermit(debet, balance, distribution);
						this.savePermitDistribution(balance, distribution);
					}
				}
			}			
		}	
		
		
		/** Update start dan end date di permitDistribution, jika endDate date equal or less than dengan current */
		List<PermitDistribution> listDistribution = permitDistributionDao.getAllDataByEndDateLessThan(current.toDate());		
		for(PermitDistribution distribution : listDistribution){
			//set year endDate menjadi sama dengan current, kenapa dibuat logic seperti itu
			//untuk mengatasi jika ada data end date(20 Februari 2010) sedangkan current(31 Maret 2014)
			DateTime endDate = new DateTime(distribution.getEndDate()).withYear(current.getYear());
			
			//logic-nya adalah start date ditambah sehari dari endDate
			//lalu endDate ditambah setahun
			DateTime startDate = endDate.plusDays(1);
			endDate = endDate.plusYears(1);
			
			distribution.setStartDate(startDate.toDate());
			distribution.setEndDate(endDate.toDate());
			permitDistributionDao.update(distribution);
		}
	}
	
	private void savePermitDistribution(double balance, PermitDistribution distribution){		
		distribution.setBalance(balance);
		distribution.setUpdatedOn(new Date());
		distribution.setUpdatedBy(HRMConstant.SYSTEM_ADMIN);
		permitDistributionDao.update(distribution);
	}
	
	private void debetNeracaPermit(double debet, double saldo, PermitDistribution distribution){
		NeracaPermit neracaPermit = new NeracaPermit();
		neracaPermit.setId(Long.parseLong(RandomNumberUtil.getRandomNumber(9)));
		neracaPermit.setPermitDistribution(distribution);							
		neracaPermit.setDebet(debet);
		neracaPermit.setSaldo(saldo);
		neracaPermit.setCreatedBy(HRMConstant.SYSTEM_ADMIN);
		neracaPermit.setCreatedOn(new Date());
		neracaPermitDao.save(neracaPermit);
	}
	
	private void creditNeracaPermit(double credit, double saldo, PermitDistribution distribution){
		NeracaPermit neracaPermit = new NeracaPermit();
		neracaPermit.setId(Long.parseLong(RandomNumberUtil.getRandomNumber(9)));
		neracaPermit.setPermitDistribution(distribution);							
		neracaPermit.setKredit(credit);
		neracaPermit.setSaldo(saldo);
		neracaPermit.setCreatedBy(HRMConstant.SYSTEM_ADMIN);
		neracaPermit.setCreatedOn(new Date());
		neracaPermitDao.save(neracaPermit);
	}
}
