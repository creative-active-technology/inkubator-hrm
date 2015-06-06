package com.inkubator.hrm.web.workingtime;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.apache.commons.lang3.StringUtils;
import org.primefaces.context.RequestContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;

import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.entity.MecineFinger;
import com.inkubator.hrm.service.MecineFingerService;
import com.inkubator.hrm.util.UploadFilesUtil;
import com.inkubator.securitycore.util.UserInfoUtil;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesIO;
import com.inkubator.webcore.util.FacesUtil;
import com.inkubator.webcore.util.MessagesResourceUtil;

/**
 *
 * @author rizkykojek
 */
@ManagedBean(name = "fingerSwapCapturedUploadFileController")
@ViewScoped
public class FingerSwapCapturedUploadFileController extends BaseController {

    private UploadedFile uploadFile;
    private String fileName;
    private Long machineId;
    private List<MecineFinger> listMachineFinger;
    
    @ManagedProperty(value = "#{uploadFilesUtil}")
    private UploadFilesUtil uploadFilesUtil;
    @ManagedProperty(value = "#{jobLauncher}")
    private JobLauncher jobLauncher;
    @ManagedProperty(value = "#{jobFingerSwapCapturedUpload}")
    private Job jobFingerSwapCapturedUpload;
    @ManagedProperty(value = "#{facesIO}")
    private FacesIO facesIO;
    @ManagedProperty(value = "#{mecineFingerService}")
    private MecineFingerService mecineFingerService;

    @PostConstruct
    @Override
    public void initialization() {
        super.initialization();
        try {
			listMachineFinger = mecineFingerService.getAllDataByMachineMethod(HRMConstant.METHOD_UPLOAD_MACINE);
		} catch (Exception e) {
			LOGGER.error("Error ", e);
		}
    }

    @PreDestroy
    public void cleanAndExit() {
        uploadFile = null;
        uploadFilesUtil = null;
        fileName = null;
        jobLauncher = null;
        jobFingerSwapCapturedUpload = null;
        facesIO = null;
        mecineFingerService = null;
        listMachineFinger = null;
        machineId = null;
    }

    public void doSave() {
        try {
        	//save upload file to disk before running jobs
        	Long currentTimeInMillis = System.currentTimeMillis();
        	String extension = StringUtils.substringAfterLast(uploadFile.getFileName(), ".");
            String pathUpload = facesIO.getPathUpload() + "fingerSwapCapturedUpload_" + currentTimeInMillis + "." + extension;
        	facesIO.transferFile(uploadFile);
            File file = new File(facesIO.getPathUpload() + uploadFile.getFileName());
            file.renameTo(new File(pathUpload));
        	
        	//running jobs batch to execute file upload
        	JobParameters jobParameters = new JobParametersBuilder()
		        	.addString("input.file.path", pathUpload)
		        	.addString("createdBy", UserInfoUtil.getUserName())
		        	.addLong("machineId", machineId)
		        	.addString("timeInMilis", String.valueOf(currentTimeInMillis)).toJobParameters();
        	JobExecution jobExecution = jobLauncher.run(jobFingerSwapCapturedUpload, jobParameters);        	  	
			
        	//removed file
            Files.deleteIfExists(Paths.get(pathUpload));
            
        	RequestContext.getCurrentInstance().closeDialog(HRMConstant.SAVE_CONDITION);
            cleanAndExit();
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
    }

    public void handingFileUpload(FileUploadEvent fileUploadEvent) {
        Map<String, String> results = uploadFilesUtil.checkUploadFileSizeLimit(fileUploadEvent.getFile());
        if (StringUtils.equals(results.get("result"), "true")) {
        	uploadFile = fileUploadEvent.getFile();
            fileName = uploadFile.getFileName();
        } else {
            ResourceBundle messages = ResourceBundle.getBundle("messages", new Locale(FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString()));
            String errorMsg = messages.getString("global.file_size_should_not_bigger_than") + " " + results.get("sizeMax");
            MessagesResourceUtil.setMessagesFromException(FacesMessage.SEVERITY_ERROR, "global.error", errorMsg, FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
        }
    }

	public UploadedFile getUploadFile() {
		return uploadFile;
	}

	public void setUploadFile(UploadedFile uploadFile) {
		this.uploadFile = uploadFile;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public UploadFilesUtil getUploadFilesUtil() {
		return uploadFilesUtil;
	}

	public void setUploadFilesUtil(UploadFilesUtil uploadFilesUtil) {
		this.uploadFilesUtil = uploadFilesUtil;
	}

	public JobLauncher getJobLauncher() {
		return jobLauncher;
	}

	public void setJobLauncher(JobLauncher jobLauncher) {
		this.jobLauncher = jobLauncher;
	}

	public Job getJobFingerSwapCapturedUpload() {
		return jobFingerSwapCapturedUpload;
	}

	public void setJobFingerSwapCapturedUpload(Job jobFingerSwapCapturedUpload) {
		this.jobFingerSwapCapturedUpload = jobFingerSwapCapturedUpload;
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

	public List<MecineFinger> getListMachineFinger() {
		return listMachineFinger;
	}

	public void setListMachineFinger(List<MecineFinger> listMachineFinger) {
		this.listMachineFinger = listMachineFinger;
	}

	public Long getMachineId() {
		return machineId;
	}

	public void setMachineId(Long machineId) {
		this.machineId = machineId;
	}   	
	
}
