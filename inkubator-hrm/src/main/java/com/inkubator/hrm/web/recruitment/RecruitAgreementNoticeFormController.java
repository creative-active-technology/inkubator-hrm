package com.inkubator.hrm.web.recruitment;

import java.io.File;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

import com.inkubator.common.util.RandomNumberUtil;
import com.inkubator.exception.BussinessException;
import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.entity.BioData;
import com.inkubator.hrm.entity.EmpData;
import com.inkubator.hrm.entity.RecruitAgreementNotice;
import com.inkubator.hrm.service.BioDataService;
import com.inkubator.hrm.service.EmpDataService;
import com.inkubator.hrm.service.RecruitAgreementNoticeService;
import com.inkubator.hrm.web.model.RecruitAgreementNoticeModel;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesIO;
import com.inkubator.webcore.util.FacesUtil;
import com.inkubator.webcore.util.MessagesResourceUtil;

@ManagedBean(name = "recruitAgreementNoticeFormController")
@ViewScoped
public class RecruitAgreementNoticeFormController extends BaseController {
	@ManagedProperty(value = "#{empDataService}")
    private EmpDataService empDataService;
	@ManagedProperty(value = "#{bioDataService}")
    private BioDataService bioDataService;
	@ManagedProperty(value = "#{recruitAgreementNoticeService}")
    private RecruitAgreementNoticeService recruitAgreementNoticeService;
	@ManagedProperty(value = "#{facesIO}")
    private FacesIO facesIO;
	private BioData selectedBioData;
	private RecruitAgreementNotice selectedRecruitAgreementNotice;
    private UploadedFile uploadFile;
    private String uploadFileName;
    private RecruitAgreementNoticeModel model;
    private Boolean isUpdate;
	
	@PostConstruct
    @Override
    public void initialization() {
        super.initialization();
        String bioDataId = FacesUtil.getRequestParameter("execution");
        
        model = new RecruitAgreementNoticeModel();
        try {
        	if(bioDataId.contains("a")){
            	isUpdate = Boolean.FALSE;
            	selectedBioData = bioDataService.getEntityByPKWithDetail(Long.valueOf(bioDataId.substring(1)));
    			if(selectedBioData != null){
    				model.setBioData(selectedBioData.getId());
    			}
            }else{
            	isUpdate = Boolean.TRUE;
            	selectedRecruitAgreementNotice = recruitAgreementNoticeService.getEntityByBioDataId(Long.valueOf(bioDataId.substring(1)));
            	if(selectedRecruitAgreementNotice != null){
            		model = getModelFromEntity(selectedRecruitAgreementNotice);
            		selectedBioData = bioDataService.getEntityByPKWithDetail(selectedRecruitAgreementNotice.getBioData().getId());
            	}
            }
			
        } catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
	
	@PreDestroy
    private void cleanAndExit() {
        empDataService = null;
        model = null;
        uploadFileName = null;
        uploadFile = null;
        facesIO = null;
        bioDataService = null;
        selectedRecruitAgreementNotice = null;
    }

	public String doSave() throws Exception{
		System.out.println(" disave guty");
		System.out.println(model.getLastSalary() + " " + model.getExpectedSalary());
		RecruitAgreementNotice recruitAgreementNotice = getEntityFromModel(model);
        try {
        	if(isUpdate){
        		recruitAgreementNoticeService.update(recruitAgreementNotice);
    			MessagesResourceUtil.setMessagesFlas(FacesMessage.SEVERITY_INFO, "global.save_info", "global.added_successfully",
                         FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
        	}else{
        		recruitAgreementNoticeService.save(recruitAgreementNotice);
        		MessagesResourceUtil.setMessagesFlas(FacesMessage.SEVERITY_INFO, "global.save_info", "global.added_successfully",
                        FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
        	}
            if (uploadFile != null) {
                facesIO.transferFile(uploadFile);
                File fotoOldFile = new File(facesIO.getPathUpload() + uploadFileName);
                fotoOldFile.renameTo(new File(recruitAgreementNotice.getUploadedCv()));
            }
           
            return "/protected/recruitment/recruit_agreement_notice_view.htm?faces-redirect=true";
            
        } catch (BussinessException ex) { 
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_ERROR, "global.error", ex.getErrorKeyMessage(), FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
		return null;
	}
	
    public void handingFileUpload(FileUploadEvent fileUploadEvent) {
        uploadFile = fileUploadEvent.getFile();
        uploadFileName = uploadFile.getFileName();
    }
    
	public RecruitAgreementNoticeModel getModelFromEntity(RecruitAgreementNotice entity){
		RecruitAgreementNoticeModel recruitAgreementNoticeModel = new RecruitAgreementNoticeModel();
		recruitAgreementNoticeModel.setDescription(entity.getDescription());
		recruitAgreementNoticeModel.setLastSalary(entity.getLastSalary());
		recruitAgreementNoticeModel.setExpectedSalary(entity.getExpectedSalary());
		if(entity.getBioData() != null){
			recruitAgreementNoticeModel.setBioData(entity.getBioData().getId());
		}
		recruitAgreementNoticeModel.setId(entity.getId());
		return recruitAgreementNoticeModel;
	}
	
	public RecruitAgreementNotice getEntityFromModel(RecruitAgreementNoticeModel model){
		System.out.println(model.getLastSalary() + " " + model.getExpectedSalary());
		RecruitAgreementNotice recruitAgreementNotice = new RecruitAgreementNotice();
		recruitAgreementNotice.setId(Long.parseLong(RandomNumberUtil.getRandomNumber(9)));
		if(uploadFile != null){
			recruitAgreementNotice.setUploadedCv(facesIO.getPathUpload() + recruitAgreementNotice.getId() + "_" + uploadFileName);
		}
		recruitAgreementNotice.setBioData(new BioData(model.getBioData()));
		recruitAgreementNotice.setLastSalary(model.getLastSalary());
		recruitAgreementNotice.setExpectedSalary(model.getExpectedSalary());
		recruitAgreementNotice.setDescription(model.getDescription());
		return recruitAgreementNotice;
	}
	
	public String doBack(){
		return "/protected/recruitment/recruit_agreement_notice_view.htm?faces-redirect=true";
	}
	
	public void doReset() throws Exception{
		if(isUpdate){
			selectedRecruitAgreementNotice = recruitAgreementNoticeService.getEntityByBioDataId(model.getBioData());
        	if(selectedRecruitAgreementNotice != null){
        		model = getModelFromEntity(selectedRecruitAgreementNotice);
        		selectedBioData = bioDataService.getEntityByPKWithDetail(selectedRecruitAgreementNotice.getBioData().getId());
        	}
		}else{
			model = new RecruitAgreementNoticeModel();
		}
	}
	
	public EmpDataService getEmpDataService() {
		return empDataService;
	}

	public void setEmpDataService(EmpDataService empDataService) {
		this.empDataService = empDataService;
	}


	public UploadedFile getUploadFile() {
		return uploadFile;
	}

	public void setUploadFile(UploadedFile uploadFile) {
		this.uploadFile = uploadFile;
	}

	public String getUploadFileName() {
		return uploadFileName;
	}

	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}

	public RecruitAgreementNoticeModel getModel() {
		return model;
	}

	public void setModel(RecruitAgreementNoticeModel model) {
		this.model = model;
	}

	public FacesIO getFacesIO() {
		return facesIO;
	}

	public void setFacesIO(FacesIO facesIO) {
		this.facesIO = facesIO;
	}

	public RecruitAgreementNoticeService getRecruitAgreementNoticeService() {
		return recruitAgreementNoticeService;
	}

	public void setRecruitAgreementNoticeService(
			RecruitAgreementNoticeService recruitAgreementNoticeService) {
		this.recruitAgreementNoticeService = recruitAgreementNoticeService;
	}

	public BioDataService getBioDataService() {
		return bioDataService;
	}

	public void setBioDataService(BioDataService bioDataService) {
		this.bioDataService = bioDataService;
	}

	public BioData getSelectedBioData() {
		return selectedBioData;
	}

	public void setSelectedBioData(BioData selectedBioData) {
		this.selectedBioData = selectedBioData;
	}

	public RecruitAgreementNotice getSelectedRecruitAgreementNotice() {
		return selectedRecruitAgreementNotice;
	}

	public void setSelectedRecruitAgreementNotice(
			RecruitAgreementNotice selectedRecruitAgreementNotice) {
		this.selectedRecruitAgreementNotice = selectedRecruitAgreementNotice;
	}

	public Boolean getIsUpdate() {
		return isUpdate;
	}

	public void setIsUpdate(Boolean isUpdate) {
		this.isUpdate = isUpdate;
	}
	
	
}
