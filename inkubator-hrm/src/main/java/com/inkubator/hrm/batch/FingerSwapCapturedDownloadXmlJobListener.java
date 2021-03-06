package com.inkubator.hrm.batch;

import com.inkubator.common.util.RandomNumberUtil;
import java.io.PrintWriter;
import java.io.StringWriter;

import org.apache.log4j.Logger;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;

import com.inkubator.hrm.entity.MecineFinger;
import com.inkubator.hrm.entity.SchedulerLog;
import com.inkubator.hrm.service.MecineFingerService;
import com.inkubator.hrm.service.SchedulerLogService;
import com.inkubator.hrm.util.MachineFingerUtil;
import java.util.Date;
import java.util.logging.Level;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author rizkykojek
 */
public class FingerSwapCapturedDownloadXmlJobListener implements JobExecutionListener {

    private transient Logger LOGGER = Logger.getLogger(getClass());
    private MecineFingerService mecineFingerService;
    @Autowired
    private SchedulerLogService schedulerLogService;

    @Override
    public void beforeJob(JobExecution jobExecution) {
        try {
            schedulerLogService.saveBeforeJobBatch();
        } catch (Exception ex) {
          LOGGER.error(ex, ex);
        }
    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        if (jobExecution.getAllFailureExceptions().isEmpty()) {
            try {
                /**
                 * delete all data attendance log in machine
                 */
                Long machineId = jobExecution.getJobParameters().getLong("machineId");
                MecineFinger mecineFinger = mecineFingerService.getEntiyByPK(machineId);
                MachineFingerUtil.deleteAllDataAttendanceLog(mecineFinger.getServiceHost(), Integer.parseInt(mecineFinger.getServicePort()));

            } catch (Exception e) {
                StringWriter errorMessage = new StringWriter();
                e.printStackTrace(new PrintWriter(errorMessage));

                ExitStatus exitStatus = new ExitStatus(BatchStatus.FAILED.toString(), errorMessage.toString());
                jobExecution.setExitStatus(exitStatus);
                jobExecution.setStatus(BatchStatus.FAILED);
            }
        }
    }

    public MecineFingerService getMecineFingerService() {
        return mecineFingerService;
    }

    public void setMecineFingerService(MecineFingerService mecineFingerService) {
        this.mecineFingerService = mecineFingerService;
    }

}
