package com.inkubator.hrm.web.organisation;

import java.util.ArrayList;
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

import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultUploadedFile;
import org.primefaces.model.DualListModel;
import org.primefaces.model.UploadedFile;

import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.entity.CompanyPolicy;
import com.inkubator.hrm.entity.Department;
import com.inkubator.hrm.entity.GolonganJabatan;
import com.inkubator.hrm.service.CompanyPolicyService;
import com.inkubator.hrm.service.DepartmentService;
import com.inkubator.hrm.service.GolonganJabatanService;
import com.inkubator.hrm.util.UploadFilesUtil;
import com.inkubator.hrm.web.model.CompanyPolicyModel;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesUtil;
import com.inkubator.webcore.util.MessagesResourceUtil;

/**
 *
 * @author rizkykojek
 */
@ManagedBean(name = "companyPolicyMainFormController")
@ViewScoped
public class CompanyPolicyMainFormController extends BaseController {

	private Boolean isDisabledBroadcastConf;
	private CompanyPolicyModel model;
	private UploadedFile attachmentFile;
	private DualListModel<Department> departmentsDualModel;
	private DualListModel<GolonganJabatan> golJabatansDualModel;
	@ManagedProperty(value = "#{companyPolicyService}")
	private CompanyPolicyService companyPolicyService;
	@ManagedProperty(value = "#{departmentService}")
	private DepartmentService departmentService;
	@ManagedProperty(value = "#{golonganJabatanService}")
	private GolonganJabatanService golonganJabatanService;
	@ManagedProperty(value = "#{uploadFilesUtil}")
	private UploadFilesUtil uploadFilesUtil;
	
	@PostConstruct
    @Override
    public void initialization() {
        super.initialization();
        model = new CompanyPolicyModel();
        isDisabledBroadcastConf = Boolean.TRUE;
        try {
	        List<Department> availableDepartments = departmentService.getAllData();
	        List<GolonganJabatan> availableGolJabatans = golonganJabatanService.getAllData();
	        departmentsDualModel = new DualListModel<Department>(availableDepartments, new ArrayList<Department>());
	        golJabatansDualModel = new DualListModel<GolonganJabatan>(availableGolJabatans, new ArrayList<GolonganJabatan>());
        } catch (Exception ex) {
            LOGGER.error("Error", ex);

        }
	}
	
	@PreDestroy
    public void cleanAndExit() {
		model = null;
		attachmentFile = null;
		companyPolicyService = null;
		departmentService = null;
		golonganJabatanService = null;
		departmentsDualModel = null;
		golJabatansDualModel = null;
		isDisabledBroadcastConf = null;
	}
	
	public CompanyPolicyModel getModel() {
		return model;
	}

	public void setModel(CompanyPolicyModel model) {
		this.model = model;
	}

	public UploadedFile getAttachmentFile() {
		return attachmentFile;
	}

	public void setAttachmentFile(UploadedFile attachmentFile) {
		this.attachmentFile = attachmentFile;
	}

	public DualListModel<Department> getDepartmentsDualModel() {
		return departmentsDualModel;
	}

	public void setDepartmentsDualModel(
			DualListModel<Department> departmentsDualModel) {
		this.departmentsDualModel = departmentsDualModel;
	}

	public DualListModel<GolonganJabatan> getGolJabatansDualModel() {
		return golJabatansDualModel;
	}

	public void setGolJabatansDualModel(
			DualListModel<GolonganJabatan> golJabatansDualModel) {
		this.golJabatansDualModel = golJabatansDualModel;
	}

	public UploadFilesUtil getUploadFilesUtil() {
		return uploadFilesUtil;
	}

	public void setUploadFilesUtil(UploadFilesUtil uploadFilesUtil) {
		this.uploadFilesUtil = uploadFilesUtil;
	}

	public void setCompanyPolicyService(CompanyPolicyService companyPolicyService) {
		this.companyPolicyService = companyPolicyService;
	}

	public void setDepartmentService(DepartmentService departmentService) {
		this.departmentService = departmentService;
	}

	public void setGolonganJabatanService(
			GolonganJabatanService golonganJabatanService) {
		this.golonganJabatanService = golonganJabatanService;
	}
	
	public Boolean getIsDisabledBroadcastConf() {
		return isDisabledBroadcastConf;
	}

	public void setIsDisabledBroadcastConf(Boolean isDisabledBroadcastConf) {
		this.isDisabledBroadcastConf = isDisabledBroadcastConf;
	}

	public void doReset() {
		try {
			model = new CompanyPolicyModel();
			attachmentFile = new DefaultUploadedFile();
			List<Department> availableDepartments = departmentService.getAllData();
	        List<GolonganJabatan> availableGolJabatans = golonganJabatanService.getAllData();
	        departmentsDualModel = new DualListModel<Department>(availableDepartments, new ArrayList<Department>());
	        golJabatansDualModel = new DualListModel<GolonganJabatan>(availableGolJabatans, new ArrayList<GolonganJabatan>());
		} catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
    }
	
	public String doBack() {
        return "/protected/organisation/company_policy_view.htm?faces-redirect=true";
    }

	public String doSave() {
        CompanyPolicy companyPolicy = getEntityFromViewModel(model);
        try {
            companyPolicyService.save(companyPolicy, attachmentFile, departmentsDualModel.getTarget(), golJabatansDualModel.getTarget());
            MessagesResourceUtil.setMessagesFlas(FacesMessage.SEVERITY_INFO, "global.save_info", "global.added_successfully",
                    FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
            return "/protected/organisation/company_policy_view.htm?faces-redirect=true";
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
        return null;
    }

	public void handingFileUpload(FileUploadEvent fileUploadEvent) {
		Map<String, String> results = uploadFilesUtil.checkUploadFileSizeLimit(fileUploadEvent.getFile());
		if(StringUtils.equals(results.get("result"),"true")){
			attachmentFile = fileUploadEvent.getFile();
			model.setAttachmentFileName(attachmentFile.getFileName());
		} else {
			ResourceBundle messages = ResourceBundle.getBundle("messages", new Locale(FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString()));
			String errorMsg = messages.getString("global.file_size_should_not_bigger_than") + " " + results.get("sizeMax");
			MessagesResourceUtil.setMessagesFromException(FacesMessage.SEVERITY_ERROR, "global.error", errorMsg, FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
		}
	}
	
	private CompanyPolicy getEntityFromViewModel(CompanyPolicyModel model) {
		CompanyPolicy companyPolicy = new CompanyPolicy();
		companyPolicy.setSubjectTitle(model.getSubjectTitle());
		companyPolicy.setEffectiveDate(model.getEffectiveDate());
		companyPolicy.setContentPolicy(model.getContentPolicy());
		companyPolicy.setIsBroadcast(model.getIsBroadcast());
		companyPolicy.setRepeatOn(model.getRepeatOn());
		companyPolicy.setIsUseAttachment(model.getIsUseAttachment());
		return companyPolicy;		
	}
	
	public void onChangeIsBroadcast() {
		isDisabledBroadcastConf = BooleanUtils.isNotTrue(model.getIsBroadcast());
        if (isDisabledBroadcastConf) {
        	model.setIsUseAttachment(null);
        	model.setRepeatOn(null);
        }
    }
}
