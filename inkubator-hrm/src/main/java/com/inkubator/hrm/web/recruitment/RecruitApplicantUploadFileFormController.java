package com.inkubator.hrm.web.recruitment;

import java.io.File;
import java.io.IOException;
import java.util.Date;
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
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;

import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.entity.RecruitVacancyAdvertisement;
import com.inkubator.hrm.service.RecruitVacancyAdvertisementService;
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
@ManagedBean(name = "recruitApplicantUploadFileFormController")
@ViewScoped
public class RecruitApplicantUploadFileFormController extends BaseController {

	private Long vacancyAdvertisementId;
	private List<RecruitVacancyAdvertisement> listVacancyAdvertisement;
	private UploadedFile file;
	private String fileName;
	@ManagedProperty(value = "#{uploadFilesUtil}")
	private UploadFilesUtil uploadFilesUtil;
	@ManagedProperty(value = "#{jobLauncher}")
	private JobLauncher jobLauncher;
	@ManagedProperty(value = "#{jobRecruitApplicantUpload}")
	private Job jobRecruitApplicantUpload;
	@ManagedProperty(value = "#{facesIO}")
	private FacesIO facesIO;
	@ManagedProperty(value = "#{recruitVacancyAdvertisementService}")
	private RecruitVacancyAdvertisementService recruitVacancyAdvertisementService;

	@PostConstruct
	@Override
	public void initialization() {
		super.initialization();
		try {
			listVacancyAdvertisement = recruitVacancyAdvertisementService.getAllDataIsStillEffective();
		} catch (Exception e) {
			LOGGER.error("Error", e);
		}
	}

	@PreDestroy
	public void cleanAndExit() {
		file = null;
		uploadFilesUtil = null;
		fileName = null;
		jobLauncher = null;
		jobRecruitApplicantUpload = null;
		facesIO = null;
		listVacancyAdvertisement = null;
		vacancyAdvertisementId = null;
	}

	public void doUpload() {
		try {
			long timeInMilis = System.currentTimeMillis();

			// save upload file to disk before running jobs
			String pathUpload = this.saveFileUpload(timeInMilis, file);
			
			// running jobs batch to execute file upload
			JobParameters jobParameters = new JobParametersBuilder().addString("input.file.path", pathUpload)
					.addString("createdBy", UserInfoUtil.getUserName())
					.addDate("createdOn", new Date())
					.addLong("vacancyAdvertisementId", vacancyAdvertisementId)
					.addString("timeInMilis", String.valueOf(timeInMilis)).toJobParameters();
			JobExecution jobExecution = jobLauncher.run(jobRecruitApplicantUpload, jobParameters);

			//check batch process status
			if(jobExecution.getStatus() == BatchStatus.COMPLETED){
				RequestContext.getCurrentInstance().closeDialog(HRMConstant.SAVE_CONDITION);
				cleanAndExit();
			} else {
				MessagesResourceUtil.setMessagesFlas(FacesMessage.SEVERITY_ERROR, "global.error", "recruitment_applicant.upload_applicant_process_failed",
				        FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
			}
			
			
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
			ResourceBundle messages = ResourceBundle.getBundle("Messages",
					new Locale(FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString()));
			String errorMsg = messages.getString("global.file_size_should_not_bigger_than") + " "
					+ results.get("sizeMax");
			MessagesResourceUtil.setMessagesFromException(FacesMessage.SEVERITY_ERROR, "global.error", errorMsg,
					FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
		}
	}

	private String saveFileUpload(long timeInMilis, UploadedFile documentFile) throws IOException {
		String uploadPath = this.getUploadPath(timeInMilis, documentFile);

		if (documentFile != null) {
			// added new file
			facesIO.transferFile(documentFile);
			File file = new File(facesIO.getPathUpload() + documentFile.getFileName());
			file.renameTo(new File(uploadPath));
		}

		return uploadPath;
	}

	private String getUploadPath(long timeInMilis, UploadedFile documentFile) {
		String extension = StringUtils.substringAfterLast(documentFile.getFileName(), ".");
		String uploadPath = facesIO.getPathUpload() + "recruitapplicant" + timeInMilis + "." + extension;
		return uploadPath;
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

	public Job getJobRecruitApplicantUpload() {
		return jobRecruitApplicantUpload;
	}

	public void setJobRecruitApplicantUpload(Job jobRecruitApplicantUpload) {
		this.jobRecruitApplicantUpload = jobRecruitApplicantUpload;
	}

	public FacesIO getFacesIO() {
		return facesIO;
	}

	public void setFacesIO(FacesIO facesIO) {
		this.facesIO = facesIO;
	}

	public List<RecruitVacancyAdvertisement> getListVacancyAdvertisement() {
		return listVacancyAdvertisement;
	}

	public void setListVacancyAdvertisement(List<RecruitVacancyAdvertisement> listVacancyAdvertisement) {
		this.listVacancyAdvertisement = listVacancyAdvertisement;
	}

	public RecruitVacancyAdvertisementService getRecruitVacancyAdvertisementService() {
		return recruitVacancyAdvertisementService;
	}

	public void setRecruitVacancyAdvertisementService(
			RecruitVacancyAdvertisementService recruitVacancyAdvertisementService) {
		this.recruitVacancyAdvertisementService = recruitVacancyAdvertisementService;
	}

	public Long getVacancyAdvertisementId() {
		return vacancyAdvertisementId;
	}

	public void setVacancyAdvertisementId(Long vacancyAdvertisementId) {
		this.vacancyAdvertisementId = vacancyAdvertisementId;
	}
	
}
