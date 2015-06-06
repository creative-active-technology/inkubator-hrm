package com.inkubator.hrm.web.workingtime;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.apache.commons.lang3.StringUtils;
import org.hamcrest.Matchers;

import ch.lambdaj.Lambda;

import com.inkubator.exception.BussinessException;
import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.entity.EmpData;
import com.inkubator.hrm.entity.PermitClassification;
import com.inkubator.hrm.entity.PermitDistribution;
import com.inkubator.hrm.entity.PermitImplementation;
import com.inkubator.hrm.service.EmpDataService;
import com.inkubator.hrm.service.PermitDistributionService;
import com.inkubator.hrm.service.PermitImplementationService;
import com.inkubator.hrm.util.UploadFilesUtil;
import com.inkubator.hrm.web.model.PermitImplementationModel;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesUtil;
import com.inkubator.webcore.util.MessagesResourceUtil;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author Taufik
 */
@ManagedBean(name = "permitImplementationFormController")
@ViewScoped
public class PermitImplementationFormController extends BaseController {

    private PermitImplementationModel model;
    private Boolean isUpdate;
    private List<PermitClassification> permits;
    @ManagedProperty(value = "#{permitImplementationService}")
    private PermitImplementationService permitImplementationService;
    @ManagedProperty(value = "#{permitDistributionService}")
    private PermitDistributionService permitDistributionService;
    @ManagedProperty(value = "#{empDataService}")
    private EmpDataService empDataService;
    private UploadedFile documentFile;
    @ManagedProperty(value = "#{uploadFilesUtil}")
    private UploadFilesUtil uploadFilesUtil;
    private Boolean isRequiredAttachment;

    @PostConstruct
    @Override
    public void initialization() {
        super.initialization();
        try {
            isUpdate = Boolean.FALSE;
            model = new PermitImplementationModel();
            isRequiredAttachment = Boolean.TRUE;
            String param = FacesUtil.getRequestParameter("execution");
            if (StringUtils.isNotEmpty(param)) {
                PermitImplementation permitImplementation = permitImplementationService.getEntityByPkWithDetail(Long.parseLong(param.substring(1)));
                if (permitImplementation != null) {
                    getModelFromEntity(permitImplementation);
                    List<PermitDistribution> permitDistributions = permitDistributionService.getAllDataByEmpIdFetchPermit(permitImplementation.getEmpData().getId());
                    permits = Lambda.extract(permitDistributions, Lambda.on(PermitDistribution.class).getPermitClassification());
                    isUpdate = Boolean.TRUE;
                    if(StringUtils.isNotEmpty(permitImplementation.getUploadPath())){
                        isRequiredAttachment = Boolean.FALSE;
                    }
                }
            }
        } catch (Exception e) {
            LOGGER.error("Error", e);
        }
    }

    @PreDestroy
    public void cleanAndExit() {
        model = null;
        isUpdate = null;
        permits = null;
        permitImplementationService = null;
        permitDistributionService = null;
        empDataService = null;
        documentFile = null;
        isRequiredAttachment = null;
    }

    public PermitImplementationModel getModel() {
        return model;
    }

    public void setModel(PermitImplementationModel model) {
        this.model = model;
    }

    public Boolean getIsRequiredAttachment() {
        return isRequiredAttachment;
    }

    public void setIsRequiredAttachment(Boolean isRequiredAttachment) {
        this.isRequiredAttachment = isRequiredAttachment;
    }
    
    

    public Boolean getIsUpdate() {
        return isUpdate;
    }

    public void setIsUpdate(Boolean isUpdate) {
        this.isUpdate = isUpdate;
    }

    public List<PermitClassification> getPermits() {
        return permits;
    }

    public void setPermits(List<PermitClassification> permits) {
        this.permits = permits;
    }

    public void setPermitImplementationService(
            PermitImplementationService permitImplementationService) {
        this.permitImplementationService = permitImplementationService;
    }

    public void setPermitDistributionService(PermitDistributionService permitDistributionService) {
        this.permitDistributionService = permitDistributionService;
    }

    public void setEmpDataService(EmpDataService empDataService) {
        this.empDataService = empDataService;
    }

    public UploadedFile getDocumentFile() {
        return documentFile;
    }

    public void setDocumentFile(UploadedFile documentFile) {
        this.documentFile = documentFile;
    }

    public UploadFilesUtil getUploadFilesUtil() {
        return uploadFilesUtil;
    }

    public void setUploadFilesUtil(UploadFilesUtil uploadFilesUtil) {
        this.uploadFilesUtil = uploadFilesUtil;
    }

    public void doReset() {
        if (isUpdate) {
            try {
                PermitImplementation permitImplementation = permitImplementationService.getEntiyByPK(model.getId());
                if (permitImplementation != null) {
                    getModelFromEntity(permitImplementation);
                }
            } catch (Exception ex) {
                LOGGER.error("Error", ex);
            }
        } else {
            model = new PermitImplementationModel();
        }
    }

    public String doSave() {
        PermitImplementation permitImplementation = getEntityFromViewModel(model);

        try {
            String message = "";

            if (isUpdate) {
                permitImplementationService.update(permitImplementation, documentFile);
                MessagesResourceUtil.setMessagesFlas(FacesMessage.SEVERITY_INFO, "global.save_info", "global.update_successfully",
                        FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());

            } else {
            	message = permitImplementationService.save(permitImplementation, documentFile, false);
                if (StringUtils.equals(message, "success_need_approval")) {
                    MessagesResourceUtil.setMessagesFlas(FacesMessage.SEVERITY_INFO, "global.save_info", "global.added_successfully_and_requires_approval",
                            FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
                } else {
                MessagesResourceUtil.setMessagesFlas(FacesMessage.SEVERITY_INFO, "global.save_info", "global.added_successfully",
                        FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
                }
            }
            cleanAndExit();
            return "/protected/working_time/permit_impl_view.htm?faces-redirect=true";
        } catch (BussinessException ex) { 
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_ERROR, "global.error", ex.getErrorKeyMessage(), FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
        return null;
    }

    private PermitImplementation getEntityFromViewModel(PermitImplementationModel model) {
        PermitImplementation permitImplementation = new PermitImplementation();
        if (model.getId() != null) {
            permitImplementation.setId(model.getId());
        }
        permitImplementation.setNumberFilling(model.getNumberFilling());
        permitImplementation.setPermitClassification(new PermitClassification(model.getPermitId()));
        permitImplementation.setEmpData(new EmpData(model.getEmpData().getId()));
        permitImplementation.setStartDate(model.getStartDate());
        permitImplementation.setEndDate(model.getEndDate());
        permitImplementation.setFillingDate(model.getFillingDate());
        permitImplementation.setDescription(model.getDescription());
        return permitImplementation;
    }

    private void getModelFromEntity(PermitImplementation permitImplementation) throws Exception {
        model.setId(permitImplementation.getId());
        model.setNumberFilling(permitImplementation.getNumberFilling());
        model.setEmpData(permitImplementation.getEmpData());
        model.setPermitId(permitImplementation.getPermitClassification().getId());
        PermitImplementation latestByFillingDate = permitImplementationService.getLatestEntityByEmpDataId(permitImplementation.getEmpData().getId());
        model.setLatestPermitDate(latestByFillingDate.getEndDate());
        PermitDistribution permitDistribution = permitDistributionService.getEntityByPermitClassificationIdAndEmpDataId(permitImplementation.getPermitClassification().getId(), permitImplementation.getEmpData().getId());
        model.setRemainingPermit(permitDistribution.getBalance());
        model.setStartDate(permitImplementation.getStartDate());
        model.setEndDate(permitImplementation.getEndDate());
        model.setFillingDate(permitImplementation.getFillingDate());
        model.setDescription(permitImplementation.getDescription());
        double actualPermit = permitImplementationService.getTotalActualPermit(permitImplementation.getEmpData().getId(), permitImplementation.getPermitClassification().getId(), permitImplementation.getStartDate(), permitImplementation.getEndDate());
        model.setActualPermitTaken(actualPermit);
        model.setUploadFileName(permitImplementation.getUploadPath());
    }

    public String doBack() {
        return "/protected/working_time/permit_impl_view.htm?faces-redirect=true";
    }

    public List<EmpData> doAutoCompleteEmployee(String param) {
        List<EmpData> empDatas = new ArrayList<EmpData>();
        try {
            empDatas = empDataService.getAllDataByNameOrNik(StringUtils.stripToEmpty(param));
        } catch (Exception e) {
            LOGGER.error("Error", e);
        }
        return empDatas;
    }

    public void onChangeEmployee() {
        try {
        	System.out.println(model.getEmpData().getId() + " employee");
            List<PermitDistribution> permitDistributions = permitDistributionService.getAllDataByEmpIdFetchPermit(model.getEmpData().getId());
            System.out.println(permitDistributions.size() + " size permit by employee");
            //filter list hanya untuk permit yang isActive = true
            permitDistributions = Lambda.select(permitDistributions, Lambda.having(Lambda.on(PermitDistribution.class).getPermitClassification().getIsActive(), Matchers.equalTo(true)));
            permits = Lambda.extract(permitDistributions, Lambda.on(PermitDistribution.class).getPermitClassification());
            model.setPermitId(null);

            //cek juga actual permit taken-nya
            this.onChangeStartOrEndDate();
        } catch (Exception e) {
            LOGGER.error("Error", e);
        }
    }

    public void onChangePermitClassification() {
        try {
            PermitDistribution permitDistribution = permitDistributionService.getEntityByPermitClassificationIdAndEmpDataId(model.getPermitId(), model.getEmpData().getId());
            PermitImplementation latestByFillingDate = permitImplementationService.getLatestEntityByEmpDataId(model.getEmpData().getId());
            model.setRemainingPermit(permitDistribution.getBalance());
            Date latestPermitDate = latestByFillingDate != null ? latestByFillingDate.getEndDate() : null;
            model.setLatestPermitDate(latestPermitDate);

            isRequiredAttachment = !permitDistribution.getPermitClassification().getAttachmentRequired();
            //cek juga actual permit taken-nya
            this.onChangeStartOrEndDate();
        } catch (Exception e) {
            LOGGER.error("Error", e);
        }
    }

    public void onChangeStartOrEndDate() {
        try {
            if (model.getStartDate() != null && model.getEndDate() != null && model.getEmpData() != null && model.getPermitId() != null) {
                double actualPermit = permitImplementationService.getTotalActualPermit(model.getEmpData().getId(), model.getPermitId(), model.getStartDate(), model.getEndDate());
                model.setActualPermitTaken(actualPermit);
            }
        } catch (Exception e) {
            LOGGER.error("Error", e);
        }
    }

    public void handingFileUpload(FileUploadEvent fileUploadEvent) {
        Map<String, String> results = uploadFilesUtil.checkUploadFileSizeLimit(fileUploadEvent.getFile());
        if (StringUtils.equals(results.get("result"), "true")) {
            documentFile = fileUploadEvent.getFile();
            model.setUploadFileName(documentFile.getFileName());
        } else {
            ResourceBundle messages = ResourceBundle.getBundle("messages", new Locale(FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString()));
            String errorMsg = messages.getString("global.file_size_should_not_bigger_than") + " " + results.get("sizeMax");
            MessagesResourceUtil.setMessagesFromException(FacesMessage.SEVERITY_ERROR, "global.error", errorMsg, FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
        }
    }
}
