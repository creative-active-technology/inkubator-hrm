package com.inkubator.hrm.service.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.google.gson.JsonObject;
import com.inkubator.common.util.DateTimeUtil;
import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.dao.CompanyPolicyDao;
import com.inkubator.hrm.dao.EmpDataDao;
import com.inkubator.hrm.dao.HrmUserDao;
import com.inkubator.hrm.entity.CompanyPolicy;
import com.inkubator.hrm.entity.CompanyPolicyJabatan;
import com.inkubator.hrm.entity.EmpData;
import com.inkubator.hrm.entity.HrmUser;
import com.inkubator.hrm.entity.SchedulerConfig;
import com.inkubator.hrm.entity.SchedulerLog;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

/**
 *
 * @author rizkykojek
 */
public class CompanyPolicyCronListenerServiceImpl extends BaseSchedulerDinamicListenerImpl implements MessageListener {

    @Autowired
    private CompanyPolicyDao companyPolicyDao;
    @Autowired
    private EmpDataDao empDataDao;
    @Autowired
    private HrmUserDao hrmUserDao;
    @Autowired
    protected JmsTemplate jmsTemplateBroadcastPolicy;

    /**
     * <p>
     * Method untuk mem-broadcast email dari kebijakan perusahaan. Akan
     * dilakukan pengecekan berdasarkan waktu efektif berlakunya, dan opsi
     * pengirimannya ada yang Mingguan/Bulanan/Triwulan.
     *
     * </p>
     *
     * <pre>
     * This method will executed by periodical(using spring schedulling). Please refer to application.properties to see the actual schedule time.
     * </pre>
     * @throws java.lang.Exception
     */
//	@Override
//    @Scheduled(cron = "${cron.send.broadcast.email.company.policy}")
//    @Transactional(readOnly = false, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    public void executeCompanyPolicyBroadcast() throws Exception {
      
        LOGGER.warn("Company Poliscy Broadcast Running =====================================================");
        Date current = new Date();

        List<CompanyPolicy> companyPolicies = companyPolicyDao.getAllData();
        for (CompanyPolicy companyPolicy : companyPolicies) {
            if (companyPolicy.getIsBroadcast() && current.after(companyPolicy.getEffectiveDate())) {
                if (StringUtils.equals(companyPolicy.getRepeatOn(), HRMConstant.COMP_POLICY_REPEAT_ON_WEEKLY)) {
                    int effectiveDay = DateUtils.toCalendar(companyPolicy.getEffectiveDate()).get(Calendar.DAY_OF_WEEK);
                    int currentDay = DateUtils.toCalendar(current).get(Calendar.DAY_OF_WEEK);

                    /**
                     * jika hari-nya sama (rabu == rabu) maka execute proses
                     * sending email
                     */
                    if (effectiveDay == currentDay) {
                        this.sendingEmailProcess(companyPolicy);
                    }

                } else if (StringUtils.equals(companyPolicy.getRepeatOn(), HRMConstant.COMP_POLICY_REPEAT_ON_MONTHLY)) {
                    int effectiveDay = DateUtils.toCalendar(companyPolicy.getEffectiveDate()).get(Calendar.DATE);
                    int currentDay = DateUtils.toCalendar(current).get(Calendar.DATE);
                    int maximumDay = DateUtils.toCalendar(current).getActualMaximum(Calendar.DATE);

                    /**
                     * di cek jika ternyata effectiveDay nya 31 Maret, maka pada
                     * akhir bulan april yaitu 30 April harus dieksekusi process
                     * sending emailnya
                     */
                    if ((currentDay == effectiveDay) || ((effectiveDay > maximumDay) && (currentDay == maximumDay))) {
                        this.sendingEmailProcess(companyPolicy);
                    }

                } else if (StringUtils.equals(companyPolicy.getRepeatOn(), HRMConstant.COMP_POLICY_REPEAT_ON_QUARTERLY)) {
                    /**
                     * dieksekusi per 3 bulan sekali
                     */
                    int totalDiff = DateTimeUtil.getTotalMonthDifference(companyPolicy.getEffectiveDate(), current);
                    if (totalDiff % 3 == 0) {
                        int maximumDay = DateUtils.toCalendar(current).getActualMaximum(Calendar.DATE);
                        int effectiveDay = DateUtils.toCalendar(companyPolicy.getEffectiveDate()).get(Calendar.DATE);
                        int currentDay = DateUtils.toCalendar(current).get(Calendar.DATE);

                        /**
                         * di cek jika ternyata effectiveDay nya 31 Maret, maka
                         * pada akhir bulan april yaitu 30 April harus di
                         * execute process sending emailnya
                         */
                        if ((currentDay == effectiveDay) || ((effectiveDay > maximumDay) && (currentDay == maximumDay))) {
                            this.sendingEmailProcess(companyPolicy);
                        }
                    }
                }
            }
        }
    }

    /**
     * <p>
     * Method untuk mengirim broadcast email ke seluruh karyawan berdasarkan
     * departemen dan golongan jabatan tertentu. Hanya employee/karyawan yang
     * aktif dan yang memiliki alamat email yang valid yang akan di proses.
     *
     * </p>
     *
     * <pre>
     * this.sendingEmailProcess(companyPolicy);
     * </pre>
     *
     * @param companyPolicy object yang akan di proses untuk di broadcast
     */
    private void sendingEmailProcess(CompanyPolicy companyPolicy) {
        for (CompanyPolicyJabatan cpj : companyPolicy.getCompanyPolicyJabatans()) {
            List<EmpData> empployees = empDataDao.getAllDataByGolJabatanIdAndDepartmentId(cpj.getGolonganJabatan().getId(), companyPolicy.getDepartment().getId());
            for (EmpData empData : empployees) {
                HrmUser user = hrmUserDao.getByEmpDataId(empData.getId());

                /**
                 * hanya jika user dan email valid
                 */
                if (user != null && StringUtils.isNotEmpty(user.getEmailAddress())) {
                    final JsonObject jsonObj = new JsonObject();
                    jsonObj.addProperty("subject", companyPolicy.getSubjectTitle());
                    jsonObj.addProperty("content", companyPolicy.getContentPolicy());
                    jsonObj.addProperty("to", user.getEmailAddress());
                    //send messaging, to trigger sending email
                    jmsTemplateBroadcastPolicy.send(new MessageCreator() {
                        @Override
                        public Message createMessage(Session session) throws JMSException {
                            return session.createTextMessage(jsonObj.toString());
                        }
                    });
                }
            }
        }
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW, isolation = Isolation.READ_COMMITTED, rollbackFor = Exception.class)
    public void onMessage(Message msg) {
        SchedulerLog log = null;
        try {
            TextMessage textMessage = (TextMessage) msg;
            SchedulerLog schedulerLog = new SchedulerLog();
            schedulerLog.setSchedulerConfig(new SchedulerConfig(Long.parseLong(textMessage.getText())));
            log = super.doSaveSchedulerLogSchedulerLog(schedulerLog);
            executeCompanyPolicyBroadcast();
            log.setStatusMessages("FINISH");
            super.doUpdateSchedulerLogSchedulerLog(log);
        } catch (Exception ex) {
            if (log != null) {
                log.setStatusMessages(ex.getMessage());
                super.doUpdateSchedulerLogSchedulerLog(log);
            }
            LOGGER.error(ex, ex);
        }
    }
}
