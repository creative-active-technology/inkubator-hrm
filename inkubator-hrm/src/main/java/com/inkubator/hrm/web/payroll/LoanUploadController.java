package com.inkubator.hrm.web.payroll;

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
import com.inkubator.hrm.entity.LoanSchema;
import com.inkubator.hrm.service.LoanSchemaService;
import com.inkubator.hrm.service.LoanService;
import com.inkubator.hrm.util.CryptoUtils;
import com.inkubator.hrm.util.UploadFilesUtil;
import com.inkubator.securitycore.util.UserInfoUtil;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesUtil;
import com.inkubator.webcore.util.MessagesResourceUtil;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ahmad Mudzakkir Amal
 */
@ManagedBean(name = "loanUploadController")
@ViewScoped
public class LoanUploadController extends BaseController {

    private UploadedFile file;
    private String fileName;
    @ManagedProperty(value = "#{loanSchemaService}")
    private LoanSchemaService loanSchemaService;      
    @ManagedProperty(value = "#{loanService}")
    private LoanService loanService;
    @ManagedProperty(value = "#{uploadFilesUtil}")
    private UploadFilesUtil uploadFilesUtil;
    @ManagedProperty(value = "#{jobLauncher}")
    private JobLauncher jobLauncher;
    @ManagedProperty(value = "#{jobLoanUpload}")
    private Job jobLoanUpload;
    private List<LoanSchema> loanSchemeList;
    private LoanSchema selected;
    private Map<String, String> loanSchemeDropDown = new HashMap<String, String>();

    @PostConstruct
    @Override
    public void initialization() {
        try {
            super.initialization();
            loanSchemeList = loanSchemaService.getAllData();
            selected = new LoanSchema();
            System.out.println("loanSchemeList size : " + loanSchemeList.size());
            for(LoanSchema loanSchema : loanSchemeList){
                loanSchemeDropDown.put(loanSchema.getCode(), loanSchema.getName());
            }
            System.out.println("loanSchemeDropDown size : " + loanSchemeDropDown.size());
        } catch (Exception ex) {
            Logger.getLogger(LoanUploadController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @PreDestroy
    public void cleanAndExit() {       
        loanSchemaService = null;
        selected = null;
        file = null;
        uploadFilesUtil = null;
        fileName = null;
        jobLauncher = null;
        jobLoanUpload = null;
        loanSchemeList = null;
    }

    public UploadedFile getFile() {
        return file;
    }

    public Map<String, String> getLoanSchemeDropDown() {
        return loanSchemeDropDown;
    }

    public void setLoanSchemeDropDown(Map<String, String> loanSchemeDropDown) {
        this.loanSchemeDropDown = loanSchemeDropDown;
    }
    
    
    public void setFile(UploadedFile file) {
        this.file = file;
    }

    public LoanSchema getSelected() {
        return selected;
    }

    public void setSelected(LoanSchema selected) {
        this.selected = selected;
    }
    

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

    public Job getJobLoanUpload() {
        return jobLoanUpload;
    }

    public void setJobLoanUpload(Job jobLoanUpload) {
        this.jobLoanUpload = jobLoanUpload;
    }

    public LoanService getLoanService() {
        return loanService;
    }

    public void setLoanService(LoanService loanService) {
        this.loanService = loanService;
    }    

    public LoanSchemaService getLoanSchemaService() {
        return loanSchemaService;
    }

    public void setLoanSchemaService(LoanSchemaService loanSchemaService) {
        this.loanSchemaService = loanSchemaService;
    }

    public List<LoanSchema> getLoanSchemeList() {
        return loanSchemeList;
    }

    public void setLoanSchemeList(List<LoanSchema> loanSchemeList) {
        this.loanSchemeList = loanSchemeList;
    }

   

    public void doSave() {
        try {
        	//save upload file to disk and delete all data before running jobs
        	String pathUpload = this.loanService.updateFileAndDeleteData(file);
        	System.out.println("selected loanScheme : " + selected.getName());                
        	//running jobs batch to execute file upload
        	JobParameters jobParameters = new JobParametersBuilder()
		        	.addString("input.file.path", pathUpload)
		        	.addString("createdBy", UserInfoUtil.getUserName())
                                .addLong("loanSchemeId", selected.getId())
		        	.addString("timeInMilis", String.valueOf(System.currentTimeMillis())).toJobParameters();
        	JobExecution jobExecution = jobLauncher.run(jobLoanUpload, jobParameters);
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