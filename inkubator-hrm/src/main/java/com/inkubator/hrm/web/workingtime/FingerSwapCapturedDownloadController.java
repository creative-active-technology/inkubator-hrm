package com.inkubator.hrm.web.workingtime;

import java.io.File;
import java.net.URL;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.primefaces.context.RequestContext;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;

import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.entity.MecineFinger;
import com.inkubator.hrm.service.MecineFingerService;
import com.inkubator.securitycore.util.UserInfoUtil;
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
    
    private void batchServiceXmlProcess(MecineFinger machineFinger) throws Exception{
    	//download file from url stream to disk before running jobs    	
    	Long currentTimeInMillis = System.currentTimeMillis();
    	String pathUpload = facesIO.getPathUpload() + "machine_" + machineFinger.getCode() + "_xml_" + currentTimeInMillis + ".xml";
    	String url = "http://" + machineFinger.getServiceHost()+ ":" + machineFinger.getServicePort();
    	Files.copy(new URL(url).openStream(), new File(pathUpload).toPath());
    	
    	
    	//running jobs batch to execute file upload
    	JobParameters jobParameters = new JobParametersBuilder()
	    	.addString("fragmentRootElementName", "Row")
	    	.addString("resourcePath", "file:///"+pathUpload)
	    	.addLong("machineId", machineFinger.getId())
	    	.addString("timeInMilis", String.valueOf(currentTimeInMillis)).toJobParameters();
    	JobExecution jobExecution = jobLauncher.run(jobFingerSwapCapturedDownloadXml, jobParameters);  
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
