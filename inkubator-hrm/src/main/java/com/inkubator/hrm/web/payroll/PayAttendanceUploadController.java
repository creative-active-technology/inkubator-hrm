package com.inkubator.hrm.web.payroll;

import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.entity.PaySalaryComponent;
import com.inkubator.hrm.service.PaySalaryComponentService;
import com.inkubator.hrm.service.PayTempUploadDataService;
import com.inkubator.hrm.util.UploadFilesUtil;
import com.inkubator.securitycore.util.UserInfoUtil;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesUtil;
import com.inkubator.webcore.util.MessagesResourceUtil;
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
import com.inkubator.hrm.entity.PaySalaryComponent;
import com.inkubator.hrm.service.PaySalaryComponentService;
import com.inkubator.hrm.service.PayTempAttendanceStatusService;
import com.inkubator.hrm.service.PayTempUploadDataService;
import com.inkubator.hrm.util.CryptoUtils;
import com.inkubator.hrm.util.UploadFilesUtil;
import com.inkubator.securitycore.util.UserInfoUtil;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesUtil;
import com.inkubator.webcore.util.MessagesResourceUtil;

/**
 *
 * @author Ahmad Mudzakkir Amal
 */
@ManagedBean(name = "payAttendanceUploadController")
@ViewScoped
public class PayAttendanceUploadController extends BaseController {

    private UploadedFile file;
    private String fileName;
    //private PaySalaryComponent selectedPaySalaryComponent;
    @ManagedProperty(value = "#{payTempAttendanceStatusService}")
    private PayTempAttendanceStatusService payTempAttendanceStatusService;    
    @ManagedProperty(value = "#{uploadFilesUtil}")
    private UploadFilesUtil uploadFilesUtil;
    @ManagedProperty(value = "#{jobLauncher}")
    private JobLauncher jobLauncher;
    @ManagedProperty(value = "#{jobPayAttendanceUpload}")
    private Job jobPayAttendanceUpload;

    @PostConstruct
    @Override
    public void initialization() {
        super.initialization();        
    }

    @PreDestroy
    public void cleanAndExit() {
       
        payTempAttendanceStatusService = null;
        //selectedPaySalaryComponent = null;
        file = null;
        uploadFilesUtil = null;
        fileName = null;
        jobLauncher = null;
        jobPayAttendanceUpload = null;
    }

    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }

//    public PaySalaryComponent getSelectedPaySalaryComponent() {
//        return selectedPaySalaryComponent;
//    }
//
//    public void setSelectedPaySalaryComponent(
//            PaySalaryComponent selectedPaySalaryComponent) {
//        this.selectedPaySalaryComponent = selectedPaySalaryComponent;
//    }

   

    public UploadFilesUtil getUploadFilesUtil() {
        return uploadFilesUtil;
    }

    public void setUploadFilesUtil(UploadFilesUtil uploadFilesUtil) {
        this.uploadFilesUtil = uploadFilesUtil;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public JobLauncher getJobLauncher() {
        return jobLauncher;
    }

    public void setJobLauncher(JobLauncher jobLauncher) {
        this.jobLauncher = jobLauncher;
    }

    public PayTempAttendanceStatusService getPayTempAttendanceStatusService() {
        return payTempAttendanceStatusService;
    }

    public void setPayTempAttendanceStatusService(PayTempAttendanceStatusService payTempAttendanceStatusService) {
        this.payTempAttendanceStatusService = payTempAttendanceStatusService;
    }

    public Job getJobPayAttendanceUpload() {
        return jobPayAttendanceUpload;
    }

    public void setJobPayAttendanceUpload(Job jobPayAttendanceUpload) {
        this.jobPayAttendanceUpload = jobPayAttendanceUpload;
    }

   

    public void doSave() {
        try {
        	//save upload file to disk and delete all data before running jobs
        	String pathUpload = this.payTempAttendanceStatusService.updateFileAndDeleteData(file);
        	
        	//running jobs batch to execute file upload
        	JobParameters jobParameters = new JobParametersBuilder()
		        	.addString("input.file.path", pathUpload)
		        	.addString("createdBy", UserInfoUtil.getUserName())        			
		        	.addString("timeInMilis", String.valueOf(System.currentTimeMillis())).toJobParameters();
        	JobExecution jobExecution = jobLauncher.run(jobPayAttendanceUpload, jobParameters);
        	System.out.println("Exit Status : " + jobExecution.getStatus());
        	
        	//encrypt file that already upload/save to disk
        	CryptoUtils.encrypt(HRMConstant.AES_ALGO, HRMConstant.KEYVALUE, pathUpload, pathUpload);        	
			
        	RequestContext.getCurrentInstance().closeDialog(HRMConstant.SAVE_CONDITION);
            cleanAndExit();
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
    }

    public void handingFileUpload(FileUploadEvent fileUploadEvent) {
        Map<String, String> results = uploadFilesUtil.checkUploadFileSizeLimit(fileUploadEvent.getFile());
        if (StringUtils.equals(results.get("result"), "true")) {
            file = fileUploadEvent.getFile();
            fileName = file.getFileName();
        } else {
            ResourceBundle messages = ResourceBundle.getBundle("messages", new Locale(FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString()));
            String errorMsg = messages.getString("global.file_size_should_not_bigger_than") + " " + results.get("sizeMax");
            MessagesResourceUtil.setMessagesFromException(FacesMessage.SEVERITY_ERROR, "global.error", errorMsg, FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
        }
    }
}
