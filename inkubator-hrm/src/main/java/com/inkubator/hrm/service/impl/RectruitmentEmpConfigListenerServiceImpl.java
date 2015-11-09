package com.inkubator.hrm.service.impl;

import com.inkubator.common.CommonUtilConstant;
import com.inkubator.common.util.DateTimeUtil;
import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.dao.RecruitSelectionApplicantPassedDao;
import com.inkubator.hrm.dao.RecruitSelectionApplicantSchedulleDao;
import com.inkubator.hrm.dao.RecruitSelectionApplicantSchedulleDetailDao;
import com.inkubator.hrm.dao.RecruitmenSelectionSeriesDetailDao;
import com.inkubator.hrm.entity.RecruitLetters;
import com.inkubator.hrm.entity.RecruitSelectionApplicantPassed;
import com.inkubator.hrm.entity.RecruitSelectionApplicantSchedulle;
import com.inkubator.hrm.entity.RecruitSelectionApplicantSchedulleDetail;
import com.inkubator.hrm.entity.RecruitmenSelectionSeriesDetail;
import com.inkubator.hrm.entity.RecruitmenSelectionSeriesDetailId;
import com.inkubator.hrm.entity.SchedulerLog;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author deni.fahri
 */
public class RectruitmentEmpConfigListenerServiceImpl extends BaseSchedulerDinamicListenerImpl implements MessageListener {
    
    @Autowired
    private RecruitSelectionApplicantPassedDao recruitSelectionApplicantPassedDao;
    @Autowired
    private RecruitSelectionApplicantSchedulleDetailDao recruitSelectionApplicantSchedulleDetailDao;
    @Autowired
    private RecruitSelectionApplicantSchedulleDao recruitSelectionApplicantSchedulleDao;
    @Autowired
    private RecruitmenSelectionSeriesDetailDao recruitmenSelectionSeriesDetailDao;
    
    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED, isolation = Isolation.REPEATABLE_READ, noRollbackFor = Exception.class)
    public void onMessage(Message msg) {
        System.out.println(" On messages do.........................................................................................");
        SchedulerLog log = null;
        try {
            TextMessage textMessage = (TextMessage) msg;
            log = schedulerLogDao.getEntiyByPK(Long.parseLong(textMessage.getText()));
            doCheckRectruitmentEmpConfig();
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
    
    private void doCheckRectruitmentEmpConfig() throws Exception {
        LOGGER.warn("---------------  Check Recruitmet Emp Config =====================");
        List<RecruitSelectionApplicantPassed> thatPendings = recruitSelectionApplicantPassedDao.getAllWithPlacementStatus(HRMConstant.SELECTION_APPLICANT_PASSED_STATUS_PENDING);
        for (RecruitSelectionApplicantPassed thatPending : thatPendings) {
          Date now = DateUtils.truncate(new Date(), Calendar.DATE);
            if(thatPending.getLetterExpired().equals(now)||thatPending.getLetterExpired().before(now)){
                  recruitSelectionApplicantPassedDao.delete(thatPending);
            }
        }
    }
    
}
