/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.payroll;

import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.util.UploadFilesUtil;
import com.inkubator.securitycore.util.UserInfoUtil;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesIO;
import com.inkubator.webcore.util.FacesUtil;
import com.inkubator.webcore.util.MessagesResourceUtil;
import java.io.File;
import java.io.IOException;
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

/**
 *
 * @author deni
 */
@ManagedBean(name = "payTempOvertimeFormController")
@ViewScoped
public class PayTempOvertimeFormController extends BaseController {

    private UploadedFile file;
    private String fileName;
    @ManagedProperty(value = "#{uploadFilesUtil}")
    private UploadFilesUtil uploadFilesUtil;
    @ManagedProperty(value = "#{jobLauncher}")
    private JobLauncher jobLauncher;
    @ManagedProperty(value = "#{jobPayTempOvertimeUpload}")
    private Job jobPayTempOvertimeUpload;
    @ManagedProperty(value = "#{facesIO}")
    private FacesIO facesIO;

    @PostConstruct
    @Override
    public void initialization() {
        super.initialization();
    }

    @PreDestroy
    public void cleanAndExit() {
        file = null;
        fileName = null;
        uploadFilesUtil = null;
        jobLauncher = null;
        jobPayTempOvertimeUpload = null;
        facesIO = null;
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

    public void doSave() throws IOException {
        
        String pathUpload = facesIO.getPathUpload() + fileName;
        if (file != null) {
            facesIO.transferFile(file);
            File fotoOldFile = new File(facesIO.getPathUpload() + fileName);
        }
        try {
            //running jobs batch to execute file upload
            JobParameters jobParameters = new JobParametersBuilder()
                    .addString("input.file.path", pathUpload)
                    .addString("createdBy", UserInfoUtil.getUserName())
                    .addString("timeInMilis", String.valueOf(System.currentTimeMillis())).toJobParameters();
            JobExecution jobExecution = jobLauncher.run(jobPayTempOvertimeUpload, jobParameters);
            

            //remove file upload
            //remove physical file
            try {
                File oldFile = new File(facesIO.getPathUpload() + fileName);
                oldFile.delete();
            } catch (Exception e) {
                //if any error when removing file, system will continue deleting the record
            }
            RequestContext.getCurrentInstance().closeDialog(HRMConstant.SAVE_CONDITION);
            cleanAndExit();
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
    }

    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
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

    public Job getJobPayTempOvertimeUpload() {
        return jobPayTempOvertimeUpload;
    }

    public void setJobPayTempOvertimeUpload(Job jobPayTempOvertimeUpload) {
        this.jobPayTempOvertimeUpload = jobPayTempOvertimeUpload;
    }

    public FacesIO getFacesIO() {
        return facesIO;
    }

    public void setFacesIO(FacesIO facesIO) {
        this.facesIO = facesIO;
    }

}
