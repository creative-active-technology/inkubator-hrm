package com.inkubator.hrm.service.impl;

import java.io.File;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

import com.inkubator.exception.BussinessException;
import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.dao.MecineFingerDao;
import com.inkubator.hrm.dao.WtPeriodeDao;
import com.inkubator.hrm.entity.MecineFinger;
import com.inkubator.hrm.entity.WtPeriode;
import com.inkubator.hrm.service.AttendanceCronService;
import com.inkubator.hrm.util.MachineFingerUtil;
import com.inkubator.webcore.util.FacesIO;

/**
 *
 * @author rizkykojek
 */
public class AttendanceCronServiceImpl implements AttendanceCronService {

	@Autowired
	private JobLauncher jobLauncher;
	@Autowired
	private Job jobSynchDataFingerRealization;
	@Autowired
	private Job jobFingerSwapCapturedDownloadXml;
	@Autowired
	private Job jobTempAttendanceRealizationCalculation;
	@Autowired
	private MecineFingerDao mecineFingerDao;
	@Autowired
	private FacesIO facesIO;
	@Autowired
	private WtPeriodeDao wtPeriodeDao;
	
	/**
     * <p>Method untuk mengkalkulasi kehadiran secara harian. 
     * Urutan proses adalah : 
     * 1. Ambil data dari service mesin, lakukan loping jika ada mesin lebih dari satu. 
     * 2. Sinkronisasi data finger.
     * 3. Kalkulasi data finger.
     * 
     * </p>
     *
     * <pre>
     * This method will executed by periodical(using spring schedulling). Please refer to application.properties to see the actual schedule time.
     * This method doesn't have any transactional, because already handle in (each) of spring batch transaction.
     * </pre>
     */
	@Override
    @Scheduled(cron = "${cron.calculate.attendance.daily}")
	public void calculateAttendanceDaily() throws Exception {
		JobExecution jobExecution;
		JobParameters jobParameters;
		WtPeriode period = wtPeriodeDao.getEntityByAbsentTypeActive();
		
		/**
		 * Process no 1. */
		List<MecineFinger> listMachine = mecineFingerDao.getAllDataByMachineMethod(HRMConstant.METHOD_SERVICE_MACINE);
		for(MecineFinger machine : listMachine){
			if(machine.getServiceType() == HRMConstant.SERVICE_DATA_XML){
				/**
		    	 * Download file from service(url) as stream to disk(file) before running jobs
		    	 * */
	    		String xmlResponse = MachineFingerUtil.getAllDataAttendanceLog(machine.getServiceHost(), Integer.parseInt(machine.getServicePort()));   
	    		if(StringUtils.isEmpty(xmlResponse)){
					throw new BussinessException("global.error_data_finger_empty");
				}
	    		Long currentTimeInMillis = new Date().getTime();
		    	String pathUpload = facesIO.getPathUpload() + "machine_" + machine.getCode() + "_xml_" + currentTimeInMillis + ".xml";
		    	FileUtils.writeStringToFile(new File(pathUpload), xmlResponse);
		    	
		    	//running jobs batch to execute file
		    	jobParameters = new JobParametersBuilder()
			    	.addString("fragmentRootElementName", "Row")
			    	.addString("resourcePath", "file:///"+pathUpload)
			    	.addLong("machineId", machine.getId())
			    	.addString("createdBy", HRMConstant.SYSTEM_ADMIN)
			    	.addDate("createdOn", new Timestamp(currentTimeInMillis)).toJobParameters();	    	
				jobExecution = jobLauncher.run(jobFingerSwapCapturedDownloadXml, jobParameters);
			}
		}
		
		/**
		 * Process no 2. */
		jobParameters = new JobParametersBuilder()
			.addString("createdBy", HRMConstant.SYSTEM_ADMIN)
			.addDate("createdOn", new Timestamp(new Date().getTime()))
			.toJobParameters();
		jobExecution = jobLauncher.run(jobSynchDataFingerRealization, jobParameters);
		
		/**
		 * Process no 3. */
		jobParameters = new JobParametersBuilder()	                    
        	.addDate("periodUntillDate", period.getUntilPeriode())
        	.addString("createdBy", HRMConstant.SYSTEM_ADMIN)
        	.addDate("createdOn", new Timestamp(new Date().getTime()))
        	.addLong("wtPeriodId", period.getId())
        	.toJobParameters();	                    
		jobExecution = jobLauncher.run(jobTempAttendanceRealizationCalculation, jobParameters);
	}

}
