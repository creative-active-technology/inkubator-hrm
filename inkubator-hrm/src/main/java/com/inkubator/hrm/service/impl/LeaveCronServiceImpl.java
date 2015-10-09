package com.inkubator.hrm.service.impl;

import com.inkubator.common.util.RandomNumberUtil;
import com.inkubator.datacore.service.impl.IServiceImpl;
import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.dao.LeaveDao;
import com.inkubator.hrm.dao.LeaveDistributionDao;
import com.inkubator.hrm.dao.NeracaCutiDao;
import com.inkubator.hrm.entity.Leave;
import com.inkubator.hrm.entity.LeaveDistribution;
import com.inkubator.hrm.entity.NeracaCuti;
import com.inkubator.hrm.service.LeaveCronService;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author rizkykojek
 */
public class LeaveCronServiceImpl extends IServiceImpl implements LeaveCronService {

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

        Date current = new Date();
        int currentYear = DateUtils.toCalendar(current).get(Calendar.YEAR);

        /**
         * tanggal sekarang(current) dikurangi satu, karena pengecekan scheduler
         * itu di tanggal endOfLeave ex: tanggal endOfLeave 15-01-2014, dan
         * sistem running scheduler setiap jam 02.00, maka ketika tanggal di
         * sistem 16-01-2014 02:00, barulah diexecute penambahan cuti-nya
         */
        Date oneDayBeforeCurrent = DateUtils.addDays(current, -1);
        int oneDayBeforeCurrentMonth = DateUtils.toCalendar(oneDayBeforeCurrent).get(Calendar.MONTH);
        int oneDayBeforeCurrentYear = DateUtils.toCalendar(oneDayBeforeCurrent).get(Calendar.YEAR);

        /**
         * Dapatkan semua data cuti yang statusnya aktif dan tidak berlaku untuk
         * satu kali
         */
        List<Leave> leaves = leaveDao.getAllDataByIsActiveAndIsOnlyOncePerEmployee(true, false);
        for (Leave leave : leaves) {
            /**
             * Ambil dari Leave entity
             */
            Integer quotaPerYear = leave.getQuotaPerPeriod();
            String availability = leave.getAvailability();
            Boolean isTakingToNextYear = leave.getIsTakingLeaveToNextYear();
            Integer maxTakingToNextYear = leave.getMaxTakingLeaveToNextYear();

            /**
             * Dapatkan semua data leaveDistribution berdasarkan yang aktif
             * employee
             */
            List<LeaveDistribution> leaveDistributions = leaveDistributionDao.getAllDataByLeaveIdAndIsActiveEmployee(leave.getId());
            for (LeaveDistribution leaveDistribution : leaveDistributions) {
                Date endLeave = leaveDistribution.getEndDate();

                if (StringUtils.equals(availability, HRMConstant.LEAVE_AVAILABILITY_FULL)) {

                    /**
                     * proses debet dan kredit neraca jika tanggal dari endDate
                     * periode tahun cuti sama dengan currentDate
                     */
                    if (DateUtils.isSameDay(oneDayBeforeCurrent, endLeave)) {
                        double balance = leaveDistribution.getBalance();
                        balance = this.getBalanceAllowedTakingToNextYear(balance, isTakingToNextYear, maxTakingToNextYear, leaveDistribution);

                        double debet = quotaPerYear;
                        balance = balance + debet;
                        
                        this.debetNeracaCuti(debet, balance, leaveDistribution);
                        this.saveLeaveDistribution(balance, leaveDistribution);
                    }

                } else if (StringUtils.equals(availability, HRMConstant.LEAVE_AVAILABILITY_INCREASES_MONTH)) {
                    int quotaPerMonth = quotaPerYear / 12;	//quota per bulan					

                    /**
                     * jika di bulan terakhir, maka quotaPerMonth ditambah sisa
                     * dari quotaPerYear dibagi 12 bulan ex: quotaPerTahun 15,
                     * endDate(10 Oktober 2014 ), maka jika currentDate 11
                     * September maka cuti yg ditambah 1+3=4 hari, selain bulan
                     * itu
                     */
                    Date monthsBeforeTheEndOfYearPeriod = DateUtils.addMonths(endLeave, -1);
                    if (oneDayBeforeCurrentMonth == DateUtils.toCalendar(monthsBeforeTheEndOfYearPeriod).get(Calendar.MONTH)) {
                        int restQuota = quotaPerYear % 12;
                        quotaPerMonth = quotaPerMonth + restQuota;
                    }

                    /**
                     * jika sudah masuk bulan terakhir dari satu tahun Periode,
                     * maka di cek balance yang akan dibawa ke tahun depan
                     */
                    boolean isProceedTakingBalanceToNextYear = (oneDayBeforeCurrentMonth == DateUtils.toCalendar(endLeave).get(Calendar.MONTH));

                    /**
                     * set bulan endDate ke bulan dan tahun yang current
                     */
                    endLeave = DateUtils.setMonths(endLeave, oneDayBeforeCurrentMonth);
                    endLeave = DateUtils.setYears(endLeave, oneDayBeforeCurrentYear);

                    /**
                     * proses debet dan kredit neraca jika tanggal dari endDate
                     * cuti per-bulan sama dengan tanggal dari currentDate ex:
                     * jika endLeave 20-10-2014, maka setiap tangal 20 harus di
                     * execute, itulah kenapa tahun&bulan endLeave di set ke
                     * tahun&bulan yg current
                     */
                    if (DateUtils.isSameDay(oneDayBeforeCurrent, endLeave)) {
                        double balance = leaveDistribution.getBalance();
                        if (isProceedTakingBalanceToNextYear) {
                            balance = this.getBalanceAllowedTakingToNextYear(balance, isTakingToNextYear, maxTakingToNextYear, leaveDistribution);
                        }

                        double debet = quotaPerMonth;
                        balance = balance + debet;
                        
                        this.debetNeracaCuti(debet, balance, leaveDistribution);
                        this.saveLeaveDistribution(balance, leaveDistribution);
                    }

                } else if (StringUtils.equals(availability, HRMConstant.LEAVE_AVAILABILITY_INCREASES_SPECIFIC_DATE)) {
                    if (DateUtils.isSameDay(oneDayBeforeCurrent, endLeave)) {
                        double balance = leaveDistribution.getBalance();
                        balance = this.getBalanceAllowedTakingToNextYear(balance, isTakingToNextYear, maxTakingToNextYear, leaveDistribution);
                        this.saveLeaveDistribution(balance, leaveDistribution);
                    }

                    /**
                     * proses debet dan kredit neraca jika tanggal spesifik dari
                     * penambahan cuti sama dengan tanggal currentDate ex: 7
                     * Oktober, maka jika 7-10-2014 atau 7-10-2018 harus
                     * diexecute, itulah kenapa tahun availabilityAtSpecificDate
                     * di set ke tahun current
                     */
                    Date availabilityAtSpecificDate = DateUtils.setYears(leave.getAvailabilityAtSpecificDate(), currentYear);
                    if (DateUtils.isSameDay(current, availabilityAtSpecificDate)) {
                        double debet = quotaPerYear;
                        double balance = leaveDistribution.getBalance() + debet;
                        
                        this.debetNeracaCuti(debet, balance, leaveDistribution);
                        this.saveLeaveDistribution(balance, leaveDistribution);
                    }
                }
            }
        }

        /**
         * Update start dan end date di leaveDistribution, jika endLEave date
         * equal or less than dengan current
         */
        List<LeaveDistribution> leaveDistributions = leaveDistributionDao.getAllDataByEndDateLessThan(current);
        for (LeaveDistribution leaveDistribution : leaveDistributions) {
			//set year endDate menjadi sama dengan current, lalu di cek lagi jika masih lebih kecil dari current tambahkan setahun
            //kenapa dibuat logic seperti di atas, untuk mengatasi end date(20 Februari 2010) sedangkan current(31 Maret 2014)
            Date endDate = DateUtils.setYears(leaveDistribution.getEndDate(), currentYear);
            if (current.after(endDate)) {
                endDate = DateUtils.addYears(endDate, 1);
            }

            //logic-nya adalah start date ditambah sehari dari endDate, lalu kurangi setahun
            Date startDate = DateUtils.addDays(endDate, 1);
            startDate = DateUtils.addYears(startDate, -1);

            leaveDistribution.setStartDate(startDate);
            leaveDistribution.setEndDate(endDate);
            leaveDistributionDao.update(leaveDistribution);
        }

    }

    private double getBalanceAllowedTakingToNextYear(Double balance, Boolean isTakingToNextYear, Integer maxTakingToNextYear, LeaveDistribution leaveDistribution) {
        /**
         * Check berapa balance cuti yang diperbolehkan dibawa ke tahun depan
         */
        if (isTakingToNextYear) {
            /**
             * jika balance lebih kecil daripada max yang boleh dibawa ke tahun
             * depan, maka lakukan operasi credit ke neraca cuti
             */
            if (balance > maxTakingToNextYear) {
                double credit = balance - maxTakingToNextYear;
                balance = balance - credit;
                
                this.creditNeracaCuti(credit, balance, leaveDistribution);
                
            }
        } else {
            /**
             * set balance ke 0(zero), lakukan operasi credit ke neraca cuti
             */
            double credit = balance;
            balance = 0.0;
            
            this.creditNeracaCuti(credit, balance, leaveDistribution);
            
        }

        return balance;
    }

    private void saveLeaveDistribution(double balance, LeaveDistribution leaveDistribution) {
        leaveDistribution.setBalance(balance);
        leaveDistribution.setUpdatedOn(new Date());
        leaveDistribution.setUpdatedBy(HRMConstant.SYSTEM_ADMIN);
        leaveDistributionDao.update(leaveDistribution);
    }

    private void debetNeracaCuti(double debet, double saldo, LeaveDistribution leaveDistribution) {
        NeracaCuti neracaCuti = new NeracaCuti();
        neracaCuti.setId(Long.parseLong(RandomNumberUtil.getRandomNumber(9)));
        neracaCuti.setLeaveDistribution(leaveDistribution);
        neracaCuti.setDebet(debet);
        neracaCuti.setSaldo(saldo);
        neracaCuti.setCreatedBy(HRMConstant.SYSTEM_ADMIN);
        neracaCuti.setCreatedOn(new Date());
        neracaCutiDao.save(neracaCuti);
    }

    private void creditNeracaCuti(double credit, double saldo, LeaveDistribution leaveDistribution) {
        NeracaCuti neracaCuti = new NeracaCuti();
        neracaCuti.setId(Long.parseLong(RandomNumberUtil.getRandomNumber(9)));
        neracaCuti.setLeaveDistribution(leaveDistribution);
        neracaCuti.setKredit(credit);
        neracaCuti.setSaldo(saldo);
        neracaCuti.setCreatedBy(HRMConstant.SYSTEM_ADMIN);
        neracaCuti.setCreatedOn(new Date());
        neracaCutiDao.save(neracaCuti);
    }
}
