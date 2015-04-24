/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.organisation;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.apache.commons.fileupload.disk.DiskFileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.lang3.StringUtils;
import org.hamcrest.Matchers;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultUploadedFile;
import org.primefaces.model.DualListModel;
import org.primefaces.model.UploadedFile;

import ch.lambdaj.Lambda;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.inkubator.exception.BussinessException;
import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.entity.Announcement;
import com.inkubator.hrm.entity.ApprovalActivity;
import com.inkubator.hrm.entity.Company;
import com.inkubator.hrm.entity.EmployeeType;
import com.inkubator.hrm.entity.GolonganJabatan;
import com.inkubator.hrm.entity.RmbsDisbursement;
import com.inkubator.hrm.entity.UnitKerja;
import com.inkubator.hrm.json.util.JsonUtil;
import com.inkubator.hrm.service.AnnouncementService;
import com.inkubator.hrm.service.ApprovalActivityService;
import com.inkubator.hrm.service.CompanyService;
import com.inkubator.hrm.service.EmployeeTypeService;
import com.inkubator.hrm.service.GolonganJabatanService;
import com.inkubator.hrm.service.UnitKerjaService;
import com.inkubator.hrm.util.UploadFilesUtil;
import com.inkubator.hrm.web.model.AnnouncementModel;
import com.inkubator.securitycore.util.UserInfoUtil;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesUtil;
import com.inkubator.webcore.util.MessagesResourceUtil;

/**
 *
 * @author Deni
 */
@ManagedBean(name = "announcementFormController")
@ViewScoped
public class AnnouncementFormController extends BaseController {

	private Boolean isAdministator;
	private Boolean isRevised;
	private ApprovalActivity currentActivity;
    private ApprovalActivity askingRevisedActivity;    
    
	private AnnouncementModel model;
	private DualListModel<EmployeeType> dualListEmployeeType = new DualListModel<>();;
    private DualListModel<GolonganJabatan> dualListGolonganJabatan = new DualListModel<>();;
    private DualListModel<UnitKerja> dualListUnitKerja = new DualListModel<>();;
    private List<Company> dropDownCompany;
	
    @ManagedProperty(value = "#{companyService}")
    private CompanyService companyService;
    @ManagedProperty(value = "#{employeeTypeService}")
    private EmployeeTypeService employeeTypeService;
    @ManagedProperty(value = "#{golonganJabatanService}")
    private GolonganJabatanService golonganJabatanService;
    @ManagedProperty(value = "#{unitKerjaService}")
    private UnitKerjaService unitKerjaService;
    @ManagedProperty(value = "#{announcementService}")
    private AnnouncementService announcementService;
    @ManagedProperty(value = "#{uploadFilesUtil}")
	private UploadFilesUtil uploadFilesUtil;
    @ManagedProperty(value = "#{approvalActivityService}")
	private ApprovalActivityService approvalActivityService;

    @PostConstruct
    @Override
    public void initialization() {
        super.initialization();
        model = new AnnouncementModel();
        isAdministator = Lambda.exists(UserInfoUtil.getRoles(), Matchers.containsString(HRMConstant.ADMINISTRATOR_ROLE));
        isRevised = Boolean.FALSE;
        try {
            //get list data
            dropDownCompany = companyService.getAllData();
            List<EmployeeType> listEmployeeType = employeeTypeService.getAllData();
            List<GolonganJabatan> listGolonganJabatan = golonganJabatanService.getAllData();
            List<UnitKerja> listUnitKerja = unitKerjaService.getAllData();
            
            //di cek terlebih dahulu, jika datangnya dari proses approval, artinya user akan melakukan revisi data yg masih dalam bentuk json	        
	        String appActivityId = FacesUtil.getRequestParameter("activity");
        	if(StringUtils.isNotEmpty(appActivityId)) {
        		//parsing data from json to object 
        		isRevised = Boolean.TRUE;
        		currentActivity = approvalActivityService.getEntityByPkWithDetail(Long.parseLong(appActivityId.substring(1)));
        		this.getModelFromJson(currentActivity.getPendingData(), listEmployeeType, listGolonganJabatan, listUnitKerja);
        		askingRevisedActivity = approvalActivityService.getEntityByActivityNumberAndSequence(currentActivity.getActivityNumber(), 
        				currentActivity.getSequence()-1);        	
        	
        	} else {
        		//set default value          
                dualListEmployeeType.setSource(listEmployeeType);
                dualListGolonganJabatan.setSource(listGolonganJabatan);
                dualListUnitKerja.setSource(listUnitKerja);
        	}
        } catch (Exception ex) {
            Logger.getLogger(AnnouncementFormController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

	@PreDestroy
    private void cleanAndExit() {
        companyService = null;
        employeeTypeService = null;
        golonganJabatanService = null;
        unitKerjaService = null;
        announcementService = null;
        uploadFilesUtil = null;
        isAdministator = null;
        isRevised = null;
        currentActivity = null;
        askingRevisedActivity = null;
        approvalActivityService = null;
        dualListEmployeeType = null;
        dualListGolonganJabatan = null;
        dualListUnitKerja = null;
        dropDownCompany = null;
        isAdministator = null;
        isRevised = null;
        currentActivity = null;
        askingRevisedActivity = null;
    }

    public void handingAttachmentUpload(FileUploadEvent fileUploadEvent) {
    	Map<String, String> results = uploadFilesUtil.checkUploadFileSizeLimit(fileUploadEvent.getFile());
		if(StringUtils.equals(results.get("result"),"true")){
			UploadedFile attachmentFile = fileUploadEvent.getFile();
			model.setAttachmentFile(attachmentFile);
			model.setAttachmentFileName(attachmentFile.getFileName());                        
		} else {
			ResourceBundle messages = ResourceBundle.getBundle("Messages", new Locale(FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString()));
			String errorMsg = messages.getString("global.file_size_should_not_bigger_than") + " " + results.get("sizeMax");
			MessagesResourceUtil.setMessagesFromException(FacesMessage.SEVERITY_ERROR, "global.error", errorMsg, FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
		}
    }
    
    public void onChangeTimeModel(){
    	if(model.getTimeModel() == HRMConstant.ANNOUNCEMENT_TIME_DAILY){
    		model.setPeriodeEndDate(null);
    		model.setPeriodeStartDate(null);
    	}
    }

    public String doSaved() {
    	try {
    		Announcement announcement = getEntityFromViewModel(model);
    		List<Long> listEmployeeTypeId = Lambda.extract(dualListEmployeeType.getTarget(), Lambda.on(EmployeeType.class).getId());
    		List<Long> listGolonganJabatanId = Lambda.extract(dualListGolonganJabatan.getTarget(), Lambda.on(GolonganJabatan.class).getId());
    		List<Long> listUnitKerjaId = Lambda.extract(dualListUnitKerja.getTarget(), Lambda.on(UnitKerja.class).getId());
        
        	String message = announcementService.saveWithApproval(announcement, model.getAttachmentFile(), listEmployeeTypeId, listGolonganJabatanId, listUnitKerjaId);
            if (StringUtils.equals(message, "success_need_approval")) {
            	MessagesResourceUtil.setMessagesFlas(FacesMessage.SEVERITY_INFO, "global.save_info", "global.added_successfully_and_requires_approval",
            		FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
            } else {
            	MessagesResourceUtil.setMessagesFlas(FacesMessage.SEVERITY_INFO, "global.save_info", "global.added_successfully",
            		FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
            }
            return "/protected/organisation/announcement_view.htm?faces-redirect=true";
            
        } catch (BussinessException ex) {
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_ERROR, "global.error", ex.getErrorKeyMessage(), FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
        return null;
    }
    
    public String doRevised() {
    	try {
    		Announcement announcement = getEntityFromViewModel(model);
    		List<Long> listEmployeeTypeId = Lambda.extract(dualListEmployeeType.getTarget(), Lambda.on(EmployeeType.class).getId());
    		List<Long> listGolonganJabatanId = Lambda.extract(dualListGolonganJabatan.getTarget(), Lambda.on(GolonganJabatan.class).getId());
    		List<Long> listUnitKerjaId = Lambda.extract(dualListUnitKerja.getTarget(), Lambda.on(UnitKerja.class).getId());
    		
	    	String message = announcementService.saveWithRevised(announcement, model.getAttachmentFile(), listEmployeeTypeId, listGolonganJabatanId, listUnitKerjaId, currentActivity.getId());
	        if(StringUtils.equals(message, "success_need_approval")){
	        	MessagesResourceUtil.setMessagesFlas(FacesMessage.SEVERITY_INFO, "global.save_info", "global.added_successfully_and_requires_approval",FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
	        } else {
	        	MessagesResourceUtil.setMessagesFlas(FacesMessage.SEVERITY_INFO, "global.save_info", "global.added_successfully",FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());	        
	        }
	        return "/protected/organisation/announcement_view.htm?faces-redirect=true";
	            
	    } catch (BussinessException ex) { 
	    	MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_ERROR, "global.error", ex.getErrorKeyMessage(), FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
	    } catch (Exception ex) {
	    	LOGGER.error("Error", ex);
	    }       
        return null;
    }

    private Announcement getEntityFromViewModel(AnnouncementModel model) throws Exception {
        Announcement announcement = new Announcement();
        if (model.getId() != null) {
            announcement.setId(model.getId());
        }
        announcement.setAnnouncementContent(model.getAnnouncementContent());
        announcement.setCompany(new Company(model.getCompanyId()));
        announcement.setSubject(model.getSubject());
        announcement.setInternetPublish(model.getInternetPublish());
        announcement.setPeriodeEndDate(model.getPeriodeEndDate());
        announcement.setPeriodeStartDate(model.getPeriodeStartDate());
        announcement.setTimeModel(model.getTimeModel());
        announcement.setViewModel(model.getViewModel());
        return announcement;
    }
    
    private void getModelFromJson(String pendingData, List<EmployeeType> sourceEmployeeType, List<GolonganJabatan> sourceGolonganJabatan, 
    		List<UnitKerja> sourceUnitKerja) {
		
    	Gson gson = JsonUtil.getHibernateEntityGsonBuilder().create();
    	JsonObject jsonObject = (JsonObject) gson.fromJson(pendingData, JsonObject.class);
    	Announcement announcement = gson.fromJson(pendingData, Announcement.class);
    	model.setAnnouncementContent(announcement.getAnnouncementContent());
    	model.setCompanyId(announcement.getCompany().getId());
    	model.setSubject(announcement.getSubject());
    	model.setInternetPublish(announcement.getInternetPublish());
    	model.setPeriodeStartDate(announcement.getPeriodeStartDate());
    	model.setPeriodeEndDate(announcement.getPeriodeEndDate());
    	model.setTimeModel(announcement.getTimeModel());
    	model.setViewModel(announcement.getViewModel());
    	UploadedFile attachmentFile = this.convertFileToUploadedFile(announcement.getAttachmentPath());
    	if(attachmentFile != null){
    		model.setAttachmentFile(attachmentFile);
    		model.setAttachmentFileName(attachmentFile.getFileName());
    	}
    	    	
    	//bind list employee type
    	List<Long> listEmployeeTypeId = new GsonBuilder().create().fromJson(jsonObject.get("listEmployeeTypeId").getAsString(), new TypeToken<List<Long>>() {}.getType());
    	List<EmployeeType> targetEmployeeType = new ArrayList<EmployeeType>();
    	for(Long id : listEmployeeTypeId){
    		EmployeeType target = Lambda.selectFirst(sourceEmployeeType, Lambda.having(Lambda.on(EmployeeType.class).getId(), Matchers.equalTo(id)));
    		if(target != null){
    			targetEmployeeType.add(target);
    			sourceEmployeeType.remove(target);
    		}
    	}
    	dualListEmployeeType.setSource(sourceEmployeeType);
    	dualListEmployeeType.setTarget(targetEmployeeType);
    	
    	//bind list golongan jabatan
    	List<Long> listGolonganJabatanId = new GsonBuilder().create().fromJson(jsonObject.get("listGolonganJabatanId").getAsString(), new TypeToken<List<Long>>() {}.getType());
    	List<GolonganJabatan> targetGolonganJabatan = new ArrayList<GolonganJabatan>();
    	for(Long id : listGolonganJabatanId){
    		GolonganJabatan target = Lambda.selectFirst(sourceGolonganJabatan, Lambda.having(Lambda.on(GolonganJabatan.class).getId(), Matchers.equalTo(id)));
    		if(target != null){
    			targetGolonganJabatan.add(target);
    			sourceGolonganJabatan.remove(target);
    		}
    	}
    	dualListGolonganJabatan.setSource(sourceGolonganJabatan);
    	dualListGolonganJabatan.setTarget(targetGolonganJabatan);
    	
    	//bind list unit kerja
    	List<Long> listUnitKerjaId = new GsonBuilder().create().fromJson(jsonObject.get("listUnitKerjaId").getAsString(), new TypeToken<List<Long>>() {}.getType());
    	List<UnitKerja> targetUnitKerja = new ArrayList<UnitKerja>();
    	for(Long id : listUnitKerjaId){
    		UnitKerja target = Lambda.selectFirst(sourceUnitKerja, Lambda.having(Lambda.on(UnitKerja.class).getId(), Matchers.equalTo(id)));
    		if(target != null){
    			targetUnitKerja.add(target);
    			sourceUnitKerja.remove(target);
    		}
    	}
    	dualListUnitKerja.setSource(sourceUnitKerja);
    	dualListUnitKerja.setTarget(targetUnitKerja);
	}
    
    private UploadedFile convertFileToUploadedFile(String path) {
		UploadedFile uploadedFile = null;
		try {
			/** converting process from file physics to UploadedFile object*/
	        if (StringUtils.isNotEmpty(path)) {
	            File file = new File(path);
	            DiskFileItem fileItem = (DiskFileItem) new DiskFileItemFactory().createItem("fileData", "text/plain", true, file.getName());
	            InputStream input = new FileInputStream(file);
	            OutputStream os = fileItem.getOutputStream();
	            int ret = input.read();
	            while (ret != -1) {
	                os.write(ret);
	                ret = input.read();
	            }
	            os.flush();
	
	            uploadedFile = new DefaultUploadedFile(fileItem);
	        }
		} catch (IOException e) {
			LOGGER.error("Error", e);
		}        
        return uploadedFile;
	}

	public CompanyService getCompanyService() {
		return companyService;
	}

	public void setCompanyService(CompanyService companyService) {
		this.companyService = companyService;
	}

	public EmployeeTypeService getEmployeeTypeService() {
		return employeeTypeService;
	}

	public void setEmployeeTypeService(EmployeeTypeService employeeTypeService) {
		this.employeeTypeService = employeeTypeService;
	}

	public GolonganJabatanService getGolonganJabatanService() {
		return golonganJabatanService;
	}

	public void setGolonganJabatanService(
			GolonganJabatanService golonganJabatanService) {
		this.golonganJabatanService = golonganJabatanService;
	}

	public UnitKerjaService getUnitKerjaService() {
		return unitKerjaService;
	}

	public void setUnitKerjaService(UnitKerjaService unitKerjaService) {
		this.unitKerjaService = unitKerjaService;
	}

	public AnnouncementService getAnnouncementService() {
		return announcementService;
	}

	public void setAnnouncementService(AnnouncementService announcementService) {
		this.announcementService = announcementService;
	}

	public UploadFilesUtil getUploadFilesUtil() {
		return uploadFilesUtil;
	}

	public void setUploadFilesUtil(UploadFilesUtil uploadFilesUtil) {
		this.uploadFilesUtil = uploadFilesUtil;
	}

	public AnnouncementModel getModel() {
		return model;
	}

	public void setModel(AnnouncementModel model) {
		this.model = model;
	}

	public Boolean getIsAdministator() {
		return isAdministator;
	}

	public void setIsAdministator(Boolean isAdministator) {
		this.isAdministator = isAdministator;
	}

	public Boolean getIsRevised() {
		return isRevised;
	}

	public void setIsRevised(Boolean isRevised) {
		this.isRevised = isRevised;
	}

	public ApprovalActivity getCurrentActivity() {
		return currentActivity;
	}

	public void setCurrentActivity(ApprovalActivity currentActivity) {
		this.currentActivity = currentActivity;
	}

	public ApprovalActivity getAskingRevisedActivity() {
		return askingRevisedActivity;
	}

	public void setAskingRevisedActivity(ApprovalActivity askingRevisedActivity) {
		this.askingRevisedActivity = askingRevisedActivity;
	}

	public DualListModel<EmployeeType> getDualListEmployeeType() {
		return dualListEmployeeType;
	}

	public void setDualListEmployeeType(
			DualListModel<EmployeeType> dualListEmployeeType) {
		this.dualListEmployeeType = dualListEmployeeType;
	}

	public DualListModel<GolonganJabatan> getDualListGolonganJabatan() {
		return dualListGolonganJabatan;
	}

	public void setDualListGolonganJabatan(
			DualListModel<GolonganJabatan> dualListGolonganJabatan) {
		this.dualListGolonganJabatan = dualListGolonganJabatan;
	}

	public DualListModel<UnitKerja> getDualListUnitKerja() {
		return dualListUnitKerja;
	}

	public void setDualListUnitKerja(DualListModel<UnitKerja> dualListUnitKerja) {
		this.dualListUnitKerja = dualListUnitKerja;
	}

	public List<Company> getDropDownCompany() {
		return dropDownCompany;
	}

	public void setDropDownCompany(List<Company> dropDownCompany) {
		this.dropDownCompany = dropDownCompany;
	}

	public ApprovalActivityService getApprovalActivityService() {
		return approvalActivityService;
	}

	public void setApprovalActivityService(
			ApprovalActivityService approvalActivityService) {
		this.approvalActivityService = approvalActivityService;
	}

}
