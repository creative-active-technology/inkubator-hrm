package com.inkubator.hrm.web.workingtime;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.primefaces.context.RequestContext;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;

import com.inkubator.exception.BussinessException;
import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.entity.MecineFinger;
import com.inkubator.hrm.service.MecineFingerService;
import com.inkubator.hrm.util.MachineFingerUtil;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesIO;
import com.inkubator.webcore.util.FacesUtil;
import com.inkubator.webcore.util.MessagesResourceUtil;

/**
 *
 * @author rizkykojek
 */
@ManagedBean(name = "fingerSwapCapturedDownloadController")
@ViewScoped
public class FingerSwapCapturedDownloadController extends BaseController {

    private Long machineId;
    private List<MecineFinger> listMachineFinger;
    
    @ManagedProperty(value = "#{jobLauncher}")
    private JobLauncher jobLauncher;
    @ManagedProperty(value = "#{jobFingerSwapCapturedDownloadXml}")
    private Job jobFingerSwapCapturedDownloadXml;
    @ManagedProperty(value = "#{facesIO}")
    private FacesIO facesIO;
    @ManagedProperty(value = "#{mecineFingerService}")
    private MecineFingerService mecineFingerService;

    @PostConstruct
    @Override
    public void initialization() {
        super.initialization();
        try {
        	listMachineFinger = new ArrayList<MecineFinger>();
			listMachineFinger.addAll(mecineFingerService.getAllDataByMachineMethod(HRMConstant.METHOD_QUERY_MACINE));
			listMachineFinger.addAll(mecineFingerService.getAllDataByMachineMethod(HRMConstant.METHOD_SERVICE_MACINE));
		} catch (Exception e) {
			LOGGER.error("Error ", e);
		}
    }

    @PreDestroy
    public void cleanAndExit() {
        jobLauncher = null;
        jobFingerSwapCapturedDownloadXml = null;
        facesIO = null;
        mecineFingerService = null;
        listMachineFinger = null;
        machineId = null;
    }

    public void doSave() {
        try {
        	MecineFinger machineFinger = mecineFingerService.getEntiyByPK(machineId);      	  	
			if(machineFinger.getServiceType() == HRMConstant.SERVICE_DATA_XML && machineFinger.getMecineMethode() == HRMConstant.METHOD_SERVICE_MACINE){
				this.batchServiceXmlProcess(machineFinger);
				RequestContext.getCurrentInstance().closeDialog(HRMConstant.SAVE_CONDITION);
				cleanAndExit();
			} else {
				MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_ERROR, "global.error", "global.error_machine_not_supported_yet", FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
			}
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
    }
    
    private void batchServiceXmlProcess(MecineFinger machineFinger) throws BussinessException {
    	try {
	    	/**
	    	 * Download file from service(url) as stream to disk(file) before running jobs
	    	 * */
    		String xmlResponse = MachineFingerUtil.getAllDataAttendanceLog(machineFinger.getServiceHost(), Integer.parseInt(machineFinger.getServicePort()));   
    		if(StringUtils.isEmpty(xmlResponse)){
				throw new BussinessException("global.error_data_finger_empty");
			}
    		Long currentTimeInMillis = System.currentTimeMillis();
	    	String pathUpload = facesIO.getPathUpload() + "machine_" + machineFinger.getCode() + "_xml_" + currentTimeInMillis + ".xml";
	    	FileUtils.writeStringToFile(new File(pathUpload), xmlResponse);
	    	
	    	//running jobs batch to execute file
	    	JobParameters jobParameters = new JobParametersBuilder()
		    	.addString("fragmentRootElementName", "Row")
		    	.addString("resourcePath", "file:///"+pathUpload)
		    	.addLong("machineId", machineFinger.getId())
		    	.addString("timeInMilis", String.valueOf(currentTimeInMillis)).toJobParameters();	    	
			JobExecution jobExecution = jobLauncher.run(jobFingerSwapCapturedDownloadXml, jobParameters);
			
			//check status jobs
			if(jobExecution.getStatus() == BatchStatus.FAILED){
				throw new BussinessException("global.error_batch_process");
			}
		} catch (IOException e) {
			LOGGER.error("Error", e);
			throw new BussinessException("global.error_connection_lost");
		} catch (JobExecutionAlreadyRunningException | JobRestartException | JobInstanceAlreadyCompleteException | JobParametersInvalidException e) {
			LOGGER.error("Error", e);
			throw new BussinessException("global.error_batch_process");
		}  
    }

	public Long getMachineId() {
		return machineId;
	}

	public void setMachineId(Long machineId) {
		this.machineId = machineId;
	}

	public List<MecineFinger> getListMachineFinger() {
		return listMachineFinger;
	}

	public void setListMachineFinger(List<MecineFinger> listMachineFinger) {
		this.listMachineFinger = listMachineFinger;
	}

	public JobLauncher getJobLauncher() {
		return jobLauncher;
	}

	public void setJobLauncher(JobLauncher jobLauncher) {
		this.jobLauncher = jobLauncher;
	}

	public Job getJobFingerSwapCapturedDownloadXml() {
		return jobFingerSwapCapturedDownloadXml;
	}

	public void setJobFingerSwapCapturedDownloadXml(Job jobFingerSwapCapturedDownloadXml) {
		this.jobFingerSwapCapturedDownloadXml = jobFingerSwapCapturedDownloadXml;
	}

	public FacesIO getFacesIO() {
		return facesIO;
	}

	public void setFacesIO(FacesIO facesIO) {
		this.facesIO = facesIO;
	}

	public MecineFingerService getMecineFingerService() {
		return mecineFingerService;
	}

	public void setMecineFingerService(MecineFingerService mecineFingerService) {
		this.mecineFingerService = mecineFingerService;
	}
	
}
