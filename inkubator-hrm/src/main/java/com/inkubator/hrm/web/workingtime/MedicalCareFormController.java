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
import com.inkubator.hrm.entity.Disease;
import com.inkubator.hrm.entity.EmpData;
import com.inkubator.hrm.entity.Hospital;
import com.inkubator.hrm.entity.PermitClassification;
import com.inkubator.hrm.entity.PermitDistribution;
import com.inkubator.hrm.entity.MedicalCare;
import com.inkubator.hrm.service.DiseaseService;
import com.inkubator.hrm.service.EmpDataService;
import com.inkubator.hrm.service.HospitalService;
import com.inkubator.hrm.service.PermitDistributionService;
import com.inkubator.hrm.service.MedicalCareService;
import com.inkubator.hrm.util.UploadFilesUtil;
import com.inkubator.hrm.web.model.MedicalCareModel;
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
@ManagedBean(name = "medicalCareFormController")
@ViewScoped
public class MedicalCareFormController extends BaseController {

    private MedicalCareModel model;
    private Boolean isUpdate;
    private List<PermitClassification> permits;
    @ManagedProperty(value = "#{medicalCareService}")
    private MedicalCareService medicalCareService;
    @ManagedProperty(value = "#{empDataService}")
    private EmpDataService empDataService;
    @ManagedProperty(value = "#{diseaseService}")
    private DiseaseService diseaseService;
    @ManagedProperty(value = "#{hospitalService}")
    private HospitalService hospitalService;
    private UploadedFile documentFile;
    @ManagedProperty(value = "#{uploadFilesUtil}")
    private UploadFilesUtil uploadFilesUtil;
    private Boolean disabledHospital;

    @PostConstruct
    @Override
    public void initialization() {
        super.initialization();
        try {
            isUpdate = Boolean.FALSE;
            model = new MedicalCareModel();
            disabledHospital = Boolean.FALSE;
            
            String param = FacesUtil.getRequestParameter("execution");
            if (StringUtils.isNotEmpty(param)) {
                MedicalCare medicalCare = medicalCareService.getEntityWithDetail(Long.parseLong(param.substring(1)));
                if (medicalCare != null) {
                    getModelFromEntity(medicalCare);

                    isUpdate = Boolean.TRUE;

                    
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
        medicalCareService = null;
        documentFile = null;
        disabledHospital = null;
    }

    public MedicalCareModel getModel() {
        return model;
    }

    public void setModel(MedicalCareModel model) {
        this.model = model;
    }

    public Boolean getDisabledHospital() {
        return disabledHospital;
    }

    public void setDisabledHospital(Boolean disabledHospital) {
        this.disabledHospital = disabledHospital;
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

    public void setMedicalCareService(
            MedicalCareService medicalCareService) {
        this.medicalCareService = medicalCareService;
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
                MedicalCare medicalCare = medicalCareService.getEntiyByPK(model.getId());
                if (medicalCare != null) {
                    getModelFromEntity(medicalCare);
                }
            } catch (Exception ex) {
                LOGGER.error("Error", ex);
            }
        } else {
            model = new MedicalCareModel();
        }
    }

    public String doSave() {
        MedicalCare medicalCare = getEntityFromViewModel(model);

        try {
            String path = "";

            if (isUpdate) {
                medicalCareService.update(medicalCare, documentFile);
                path = "/protected/working_time/medical_care_detail.htm?faces-redirect=true&execution=e" + medicalCare.getId();
                MessagesResourceUtil.setMessagesFlas(FacesMessage.SEVERITY_INFO, "global.save_info", "global.update_successfully",
                        FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());

            } else {
                medicalCareService.save(medicalCare, documentFile);
//                if (StringUtils.equals(message, "success_need_approval")) {
//                    path = "/protected/working_time/medical_care_view.htm?faces-redirect=true";
//                    MessagesResourceUtil.setMessagesFlas(FacesMessage.SEVERITY_INFO, "global.save_info", "global.added_successfully_and_requires_approval",
//                            FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
//                } else {
                path = "/protected/working_time/medical_care_detail.htm?faces-redirect=true&execution=e" + medicalCare.getId();
                MessagesResourceUtil.setMessagesFlas(FacesMessage.SEVERITY_INFO, "global.save_info", "global.added_successfully",
                        FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
//                }
            }

            return path;
        } catch (BussinessException ex) {
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_ERROR, "global.error", ex.getErrorKeyMessage(), FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
        return null;
    }

    private MedicalCare getEntityFromViewModel(MedicalCareModel model) {
        MedicalCare medicalCare = new MedicalCare();
        if (model.getId() != null) {
            medicalCare.setId(model.getId());
        }
        medicalCare.setDisease(new Disease(model.getDiseaseId()));
        medicalCare.setEmpDataByEmpDataId(new EmpData(model.getEmpDataByEmpDataId()));
        medicalCare.setEmpDataByTemporaryActingId(new EmpData(model.getEmpDataByTemporaryActingId()));
        medicalCare.setHospital(new Hospital(model.getHospitalId()));
        medicalCare.setDocterName(model.getDocterName());
        medicalCare.setStartDate(model.getStartDate());
        medicalCare.setEndDate(model.getEndDate());
        medicalCare.setMaterialJobsAbandoned(model.getMaterialJobsAbandoned());
        medicalCare.setMedicalNotes(model.getMedicalNotes());
        medicalCare.setRequestDate(model.getRequestDate());
        medicalCare.setTotalDays(model.getTotalDays());
        return medicalCare;
    }

    private void getModelFromEntity(MedicalCare medicalCare) throws Exception {
        model.setId(medicalCare.getId());
        model.setDiseaseId(medicalCare.getDisease().getId());
        model.setDocterName(medicalCare.getDocterName());
        model.setEmpDataByEmpDataId(medicalCare.getEmpDataByEmpDataId().getId());
        model.setEmpDataByTemporaryActingId(medicalCare.getEmpDataByTemporaryActingId().getId());
        model.setStartDate(medicalCare.getStartDate());
        model.setEndDate(medicalCare.getEndDate());
        model.setHospitalId(medicalCare.getHospital().getId());
        model.setMaterialJobsAbandoned(medicalCare.getMaterialJobsAbandoned());
        model.setRequestDate(medicalCare.getRequestDate());
        model.setTotalDays(medicalCare.getTotalDays());
        model.setUploadPath(medicalCare.getUploadPath());
    }

    public String doBack() {
        return "/protected/working_time/medical_care_view.htm?faces-redirect=true";
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
    
    public List<EmpData> doAutoCompleteTemporary(String param) {
        List<EmpData> empDatas = new ArrayList<EmpData>();
        try {
            empDatas = empDataService.getAllDataByNameOrNik(StringUtils.stripToEmpty(param));
        } catch (Exception e) {
            LOGGER.error("Error", e);
        }
        return empDatas;
    }
    
    public List<Disease> doAutoCompleteDisease(String param) {
        List<Disease> diseases = new ArrayList<Disease>();
        try {
            diseases = diseaseService.getAllData();
        } catch (Exception e) {
            LOGGER.error("Error", e);
        }
        return diseases;
    }
    
    public List<Hospital> doAutoCompleteHospital(String param) {
        List<Hospital> hospitals = new ArrayList<Hospital>();
        try {
            hospitals = hospitalService.getAllData();
        } catch (Exception e) {
            LOGGER.error("Error", e);
        }
        return hospitals;
    }


    public void handingFileUpload(FileUploadEvent fileUploadEvent) {
        Map<String, String> results = uploadFilesUtil.checkUploadFileSizeLimit(fileUploadEvent.getFile());
        if (StringUtils.equals(results.get("result"), "true")) {
            documentFile = fileUploadEvent.getFile();
            model.setUploadPath(documentFile.getFileName());
        } else {
            ResourceBundle messages = ResourceBundle.getBundle("messages", new Locale(FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString()));
            String errorMsg = messages.getString("global.file_size_should_not_bigger_than") + " " + results.get("sizeMax");
            MessagesResourceUtil.setMessagesFromException(FacesMessage.SEVERITY_ERROR, "global.error", errorMsg, FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
        }
    }
    
    public void onChangeEmployee() {
        try {
            EmpData empData = empDataService.getByEmpIdWithDetail(model.getEmpDataByEmpDataId());
            
            model.setJabatan(empData.getJabatanByJabatanId().getName());

        } catch (Exception e) {
            LOGGER.error("Error", e);
        }
    }
    
    public void onChangeHospitalCheckbox() {
        
    }
}
