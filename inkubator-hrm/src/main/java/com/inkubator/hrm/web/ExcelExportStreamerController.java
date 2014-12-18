package com.inkubator.hrm.web;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;

import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesIO;
import com.inkubator.webcore.util.FacesUtil;

/**
 *
 * @author rizkykojek
 */
@ManagedBean(name = "excelExportStreamerController")
@ApplicationScoped
public class ExcelExportStreamerController extends BaseController {
	
	private static final String PAY_RECEIVER_ACCOUNT_FILE_NAME = "pay_receiver_account.xlsx";
	
	
	@ManagedProperty(value = "#{jobLauncher}")
    private JobLauncher jobLauncher;
    @ManagedProperty(value = "#{jobPayReceiverAccount}")
    private Job jobPayReceiverAccount;
    @ManagedProperty(value = "#{facesIO}")
    private FacesIO facesIO;

	public void setFacesIO(FacesIO facesIO) {
		this.facesIO = facesIO;
	}
	
	public FacesIO getFacesIO() {
		return facesIO;
	}
	
	public void setJobLauncher(JobLauncher jobLauncher) {
		this.jobLauncher = jobLauncher;
	}
	
	public void setJobPayReceiverAccount(Job jobPayReceiverAccount) {
		this.jobPayReceiverAccount = jobPayReceiverAccount;
	}
	
	public StreamedContent getPayReceiverAccountList() throws IOException {
        FacesContext context = FacesUtil.getFacesContext();
        StreamedContent streamedContent = new DefaultStreamedContent();
        
        if (!context.getRenderResponse()) {
            try {            	
            	String temporaryFileName = facesIO.getPathUpload() + PAY_RECEIVER_ACCOUNT_FILE_NAME;
            	
            	//delete old file temporary
        		Files.deleteIfExists(Paths.get(temporaryFileName));
        		
            	//running batch
            	JobParameters jobParameters = new JobParametersBuilder()
		            	.addString("fileName", temporaryFileName)
		            	.addString("timeInMilis", String.valueOf(System.currentTimeMillis()))
		            	.toJobParameters();
        		JobExecution jobExecution = jobLauncher.run(jobPayReceiverAccount, jobParameters);
        		
        		//if batch complete/succeed, then start download the excel
        		if(jobExecution.getStatus() == BatchStatus.COMPLETED){
        			InputStream is = facesIO.getInputStreamFromURL(temporaryFileName);
        			streamedContent = new DefaultStreamedContent(is, null, "List_Pay_Receiver_Account.xlsx");
        		}

            } catch (Exception ex) {
                LOGGER.error("Error ", ex);
            }
        }
        
        return streamedContent;
    }
}
