package com.inkubator.hrm.service.impl;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;

import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.entity.MecineFinger;
import com.inkubator.hrm.entity.WtPeriode;
import com.inkubator.hrm.service.MecineFingerService;
import com.inkubator.hrm.service.SchedulerLogService;
import com.inkubator.hrm.service.WtPeriodeService;
import com.inkubator.hrm.util.MachineFingerUtil;
import com.inkubator.webcore.util.FacesIO;

/**
 *
 * @author rizkykojek
 */
public class AttendanceCronListenerServiceImpl extends BaseSchedulerDinamicListenerImpl implements MessageListener {

    @Autowired
    private JobLauncher jobLauncher;
    @Autowired
    private Job jobSynchDataFingerRealization;
    @Autowired
    private Job jobFingerSwapCapturedDownloadXml;
    @Autowired
    private Job jobTempAttendanceRealizationCalculation;
    @Autowired
    private FacesIO facesIO;
    @Autowired
    private SchedulerLogService schedulerLogService;
    @Autowired
    private WtPeriodeService wtPeriodeService;
    @Autowired
    private MecineFingerService mecineFingerService;

    /**
     * <p>
     * Method untuk mengkalkulasi kehadiran secara harian. Urutan proses adalah
     * : 1. Ambil data dari service mesin, lakukan loping jika ada mesin lebih
     * dari satu. 2. Sinkronisasi data finger. 3. Kalkulasi data finger.
     *
     * </p>
     *
     * <pre>
     * This method will executed by periodical(using spring schedulling). Please refer to application.properties to see the actual schedule time.
     * This method doesn't have any transactional, because already handle in (each) of spring batch transaction.
     * </pre>
     *
     * @throws java.lang.Exception
     */
    public String calculateAttendanceDaily() throws Exception {
        LOGGER.warn("Calculate Attendance Running ==========================================================" + new Date());
        StringBuffer statusMessage = new StringBuffer();
        int stepProcess = 1;
        JobExecution jobExecution;
        JobParameters jobParameters;
        WtPeriode period = wtPeriodeService.getEntityByAbsentTypeActive();
        
        /**
         * Process no 1.
         */
        List<MecineFinger> listMachine = mecineFingerService.getAllDataByMachineMethod(HRMConstant.METHOD_SERVICE_MACINE);
        for (MecineFinger machine : listMachine) {
            if (machine.getServiceType() == HRMConstant.SERVICE_DATA_XML) {
                /**
                 * Download file from service(url) as stream to disk(file)
                 * before running jobs
                 */
            	String xmlResponse = StringUtils.EMPTY;
            	try {
            		xmlResponse = MachineFingerUtil.getAllDataAttendanceLog(machine.getServiceHost(), Integer.parseInt(machine.getServicePort()));
            	} catch (IOException e) {
        			//LOGGER.error("Error", e);
        		}
            	
                if (StringUtils.isNotEmpty(xmlResponse)) {                
	                Long currentTimeInMillis = new Date().getTime();
	                String pathUpload = facesIO.getPathUpload() + "machine_" + machine.getCode() + "_xml_" + currentTimeInMillis + ".xml";
	                FileUtils.writeStringToFile(new File(pathUpload), xmlResponse);
	
	                //running jobs batch to execute file
	                jobParameters = new JobParametersBuilder()
	                        .addString("fragmentRootElementName", "Row")
	                        .addString("resourcePath", "file:///" + pathUpload)
	                        .addLong("machineId", machine.getId())
	                        .addString("createdBy", HRMConstant.SYSTEM_ADMIN)
	                        .addDate("createdOn", new Timestamp(currentTimeInMillis)).toJobParameters();
	                jobExecution = jobLauncher.run(jobFingerSwapCapturedDownloadXml, jobParameters);
	                
	                if(jobExecution.getStatus() == BatchStatus.COMPLETED){
	                	statusMessage.append(stepProcess + ". " + machine.getName() + " Download Finger Absent = Success | ");
	                	stepProcess++;
	                } else {
	                	statusMessage.append(stepProcess + ". " + machine.getName() + " Download Finger Absent = Failed | ");
	                	stepProcess++;
	                }
	                
                } else {
                	statusMessage.append(stepProcess + ". " + machine.getName() + " Download Finger Machine = Failed | ");
                	stepProcess++;
                }
            }
        }

        /**
         * Process no 2.
         */
        jobParameters = new JobParametersBuilder()
                .addString("createdBy", HRMConstant.SYSTEM_ADMIN)
                .addDate("createdOn", new Timestamp(new Date().getTime()))
                .toJobParameters();
        jobExecution = jobLauncher.run(jobSynchDataFingerRealization, jobParameters);
        
        if(jobExecution.getStatus() == BatchStatus.COMPLETED){
        	statusMessage.append(stepProcess + ". Data Finger Synchronization = Success | ");
        	stepProcess++;
        } else {
        	statusMessage.append(stepProcess + ". Data Finger Synchronization = Failed | ");
        	stepProcess++;
        }

        /**
         * Process no 3.
         */
        jobParameters = new JobParametersBuilder()
                .addDate("periodUntillDate", period.getUntilPeriode())
                .addString("createdBy", HRMConstant.SYSTEM_ADMIN)
                .addDate("createdOn", new Timestamp(new Date().getTime()))
                .addLong("wtPeriodId", period.getId())
                .toJobParameters();
        jobExecution = jobLauncher.run(jobTempAttendanceRealizationCalculation, jobParameters);
        
        if(jobExecution.getStatus() == BatchStatus.COMPLETED){
        	statusMessage.append(stepProcess + ". Data Finger Calculation = Success | ");
        	stepProcess++;
        } else {
        	statusMessage.append(stepProcess + ". Data Finger Calculation = Failed | ");
        	stepProcess++;
        }
        
        LOGGER.warn("Calculate Attendance Finish ==========================================================" + new Date());
        
        return statusMessage.toString();
    }

    @Override
    public void onMessage(Message msg) {
        try {
            TextMessage textMessage = (TextMessage) msg;
            String statusMessage = calculateAttendanceDaily();
            String schedullerLogId = textMessage.getText();
            schedulerLogService.updateLogAndStatus(schedullerLogId, statusMessage);
            
        } catch (Exception ex) {
            LOGGER.error(ex, ex);
        }
    }
}
