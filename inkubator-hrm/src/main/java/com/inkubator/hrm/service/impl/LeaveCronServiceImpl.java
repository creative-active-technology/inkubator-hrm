package com.inkubator.hrm.service.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.inkubator.common.util.RandomNumberUtil;
import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.dao.LeaveDao;
import com.inkubator.hrm.dao.LeaveDistributionDao;
import com.inkubator.hrm.dao.NeracaCutiDao;
import com.inkubator.hrm.entity.Leave;
import com.inkubator.hrm.entity.LeaveDistribution;
import com.inkubator.hrm.entity.NeracaCuti;
import com.inkubator.hrm.service.LeaveCronService;

/**
 *
 * @author rizkykojek
 */
public class LeaveCronServiceImpl implements LeaveCronService {
	
	@Autowired
	private LeaveDao leaveDao;
	@Autowired
	private LeaveDistributionDao leaveDistributionDao;
	@Autowired
	private NeracaCutiDao neracaCutiDao;
	
	@Override
    @Scheduled(cron = "${cron.process.of.adding.leave.balance}")
    @Transactional(readOnly = false, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
	public void processAddingOfLeaveBalance() throws Exception {
		/** tanggal sekarang dikurangi satu, karena pengecekan itu di tanggal end of leave*/
		Date current = DateUtils.addDays(new Date(), -1);
		
		/** Dapatkan semua data cuti yang statusnya aktif dan tidak berlaku untuk satu kali */
		List<Leave> leaves = leaveDao.getAllDataByIsActiveAndIsOnlyOncePerEmployee(true, false);
		for(Leave leave : leaves){
			/** Ambil dari Leave entity*/
			int quota = leave.getQuotaPerPeriod();
			String availability = leave.getAvailability();
			Boolean isTakingToNextYear = leave.getIsTakingLeaveToNextYear();
			int maxTakingToNextYear = leave.getMaxTakingLeaveToNextYear();
			
			/** Dapatkan semua data leaveDistribution berdasarkan yang aktif employee */
			List<LeaveDistribution> leaveDistributions = leaveDistributionDao.getAllDataByLeaveIdAndIsActiveEmployee(leave.getId());
			for(LeaveDistribution leaveDistribution : leaveDistributions ){
				 Date endLeave = leaveDistribution.getEndDate();				 
				 
				 if (availability == HRMConstant.LEAVE_AVAILABILITY_FULL){
					if(DateUtils.isSameDay(current, endLeave)){
						double balance = leaveDistribution.getBalance();
						balance = this.balanceTakingToNextYear(balance, isTakingToNextYear, maxTakingToNextYear, leaveDistribution);
								
						double debet = quota;
						this.debetNeracaCuti(debet, leaveDistribution);
							
						balance = balance + debet;							
						this.saveLeaveDistribution(balance, leaveDistribution);
					}
					
				} else  if (availability == HRMConstant.LEAVE_AVAILABILITY_INCREASES_MONTH){
					int quotaPerMonth = quota / 12;						
						
					Calendar currentCalendar = Calendar.getInstance();
					currentCalendar.setTime(current);			
					int currentMonth = currentCalendar.get(Calendar.MONTH);		
						
					Date monthsBeforeTheEndOfPeriod = DateUtils.addMonths(endLeave, -1);
					if(currentMonth == DateUtils.toCalendar(monthsBeforeTheEndOfPeriod).get(Calendar.MONTH)){
						int restQuota = quota % 12;
						quotaPerMonth = quotaPerMonth + restQuota;
					}
						
					Calendar endLeaveCalendar = DateUtils.toCalendar(endLeave);
					boolean isProceedTakingBalanceToNextYear = currentMonth == endLeaveCalendar.get(Calendar.MONTH);
						
					endLeaveCalendar.add(Calendar.MONTH, currentMonth);		
					if(DateUtils.isSameDay(currentCalendar, endLeaveCalendar)){
						double balance = leaveDistribution.getBalance();
						if(isProceedTakingBalanceToNextYear){
							balance = this.balanceTakingToNextYear(balance, isTakingToNextYear, maxTakingToNextYear, leaveDistribution);
						}
							
						double debet = quotaPerMonth;
						this.debetNeracaCuti(debet, leaveDistribution);
							
						balance = balance + debet;					
						this.saveLeaveDistribution(balance, leaveDistribution);
					} 		
					
				} else  if (availability == HRMConstant.LEAVE_AVAILABILITY_INCREASES_MONTH){
					if(DateUtils.isSameDay(current, endLeave)){
						double balance = leaveDistribution.getBalance();
						balance = this.balanceTakingToNextYear(balance, isTakingToNextYear, maxTakingToNextYear, leaveDistribution);
					}
					
					Calendar currentCalendar = Calendar.getInstance();
					currentCalendar.setTime(current);
					Calendar availabilityAtSpecificDate = DateUtils.toCalendar(leave.getAvailabilityAtSpecificDate());
				}
			}
		}
		
		
		/** Update start dan end date di leaveDistribution, jika current date equal dengan end date */
		
		
		
	}
	
	private double balanceTakingToNextYear(double balance, boolean isTakingToNextYear, int maxTakingToNextYear, LeaveDistribution leaveDistribution){
		
		if(isTakingToNextYear){
			if(balance > maxTakingToNextYear){
				double credit = balance - maxTakingToNextYear;									
				this.creditNeracaCuti(credit, leaveDistribution);
				
				balance = balance - credit;
			}
		} else {
			double credit = balance;
			this.creditNeracaCuti(credit, leaveDistribution);
			balance = 0;
		}
		
		return balance;
	}
	
	private void saveLeaveDistribution(double balance, LeaveDistribution leaveDistribution){		
		leaveDistribution.setBalance(balance);
		leaveDistribution.setUpdatedOn(new Date());
		leaveDistribution.setUpdatedBy(HRMConstant.SYSTEM_ADMIN);
		leaveDistributionDao.update(leaveDistribution);
	}
	
	private void debetNeracaCuti(double debet, LeaveDistribution leaveDistribution){
		NeracaCuti neracaCuti = new NeracaCuti();
		neracaCuti.setId(Long.parseLong(RandomNumberUtil.getRandomNumber(9)));
		neracaCuti.setLeaveDistribution(leaveDistribution);							
		neracaCuti.setDebet(debet);
		neracaCuti.setCreatedBy(HRMConstant.SYSTEM_ADMIN);
		neracaCuti.setCreatedOn(new Date());
		neracaCutiDao.save(neracaCuti);
	}
	
	private void creditNeracaCuti(double credit, LeaveDistribution leaveDistribution){
		NeracaCuti neracaCuti = new NeracaCuti();
		neracaCuti.setId(Long.parseLong(RandomNumberUtil.getRandomNumber(9)));
		neracaCuti.setLeaveDistribution(leaveDistribution);							
		neracaCuti.setKredit(credit);
		neracaCuti.setCreatedBy(HRMConstant.SYSTEM_ADMIN);
		neracaCuti.setCreatedOn(new Date());
		neracaCutiDao.save(neracaCuti);
	}
}
