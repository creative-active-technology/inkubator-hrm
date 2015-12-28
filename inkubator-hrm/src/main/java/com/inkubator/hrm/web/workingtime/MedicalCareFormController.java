package com.inkubator.hrm.web.workingtime;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.TreeMap;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.apache.commons.lang3.StringUtils;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

import com.inkubator.common.util.DateTimeUtil;
import com.inkubator.exception.BussinessException;
import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.entity.Disease;
import com.inkubator.hrm.entity.EmpData;
import com.inkubator.hrm.entity.Hospital;
import com.inkubator.hrm.entity.MedicalCare;
import com.inkubator.hrm.entity.PermitClassification;
import com.inkubator.hrm.entity.WtPeriode;
import com.inkubator.hrm.service.DiseaseService;
import com.inkubator.hrm.service.EmpDataService;
import com.inkubator.hrm.service.HospitalService;
import com.inkubator.hrm.service.MedicalCareService;
import com.inkubator.hrm.service.WtPeriodeService;
import com.inkubator.hrm.util.UploadFilesUtil;
import com.inkubator.hrm.web.model.MedicalCareModel;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesUtil;
import com.inkubator.webcore.util.MessagesResourceUtil;

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
    private Date minDate;
    private Date maxDate;
    private Boolean isUseHospital;
    
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
    @ManagedProperty(value = "#{wtPeriodeService}")
    private WtPeriodeService wtPeriodeService;

    private List<Hospital> hospitals = new ArrayList<Hospital>();
    private Map<String, Long> dropDownHospital = new TreeMap<String, Long>();
    private Map<String, Long> dropDownDisease = new TreeMap<String, Long>();
    private List<Disease> diseases = new ArrayList<>();

    public EmpDataService getEmpDataService() {
        return empDataService;
    }

    public void setEmpDataService(EmpDataService empDataService) {
        this.empDataService = empDataService;
    }

    public DiseaseService getDiseaseService() {
        return diseaseService;
    }

    public void setDiseaseService(DiseaseService diseaseService) {
        this.diseaseService = diseaseService;
    }

    public HospitalService getHospitalService() {
        return hospitalService;
    }

    public void setHospitalService(HospitalService hospitalService) {
        this.hospitalService = hospitalService;
    }

    public List<Hospital> getHospitals() {
        return hospitals;
    }

    public void setHospitals(List<Hospital> hospitals) {
        this.hospitals = hospitals;
    }

    public Map<String, Long> getDropDownHospital() {
        return dropDownHospital;
    }

    public void setDropDownHospital(Map<String, Long> dropDownHospital) {
        this.dropDownHospital = dropDownHospital;
    }

    @PostConstruct
    @Override
    public void initialization() {
        super.initialization();
        try {
            isUpdate = Boolean.FALSE;
            model = new MedicalCareModel();
            isUseHospital = Boolean.FALSE;

            String param = FacesUtil.getRequestParameter("execution");
            if (StringUtils.isNotEmpty(param)) {
                MedicalCare medicalCare = medicalCareService.getEntityWithDetail(Long.parseLong(param.substring(1)));
                if (medicalCare != null) {
                    getModelFromEntity(medicalCare);
                    isUpdate = Boolean.TRUE;

                }
            }
            
            //pembatasan waktu input di periode waktu kerja hanya ketika tambah data, bukan update data
            WtPeriode workingTimePeriod =  wtPeriodeService.getEntityByAbsentTypeActive();
            if (!isUpdate) {
				minDate = workingTimePeriod.getFromPeriode();
				maxDate = workingTimePeriod.getUntilPeriode();
			}

            doSelectOneMenuHospital();
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
        isUseHospital = null;
        hospitals = null;
        dropDownHospital = null;
        dropDownDisease = null;
        diseases = null;
        minDate = null;
        maxDate = null;
        wtPeriodeService = null;
    }

    public MedicalCareModel getModel() {
        return model;
    }

    public void setModel(MedicalCareModel model) {
        this.model = model;
    }

	public Boolean getIsUseHospital() {
		return isUseHospital;
	}

	public void setIsUseHospital(Boolean isUseHospital) {
		this.isUseHospital = isUseHospital;
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
    
	public Date getMinDate() {
		return minDate;
	}

	public void setMinDate(Date minDate) {
		this.minDate = minDate;
	}

	public Date getMaxDate() {
		return maxDate;
	}

	public void setMaxDate(Date maxDate) {
		this.maxDate = maxDate;
	}

	public WtPeriodeService getWtPeriodeService() {
		return wtPeriodeService;
	}

	public void setWtPeriodeService(WtPeriodeService wtPeriodeService) {
		this.wtPeriodeService = wtPeriodeService;
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
//        medicalCare.setDisease(model.getDiseaseId());
        medicalCare.setDisease(new Disease(model.getDisease()));
        medicalCare.setEmpData(model.getEmpDataByEmpDataId());
        medicalCare.setTemporaryActing(model.getEmpDataByTemporaryActingId());
//        medicalCare.setHospital(model.getHospitalId());
        medicalCare.setHospital(isUseHospital ? new Hospital(model.getHospital()) : null);
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
        model.setJabatan(medicalCare.getEmpData().getJabatanByJabatanId().getName());
        model.setId(medicalCare.getId());
        model.setDiseaseId(medicalCare.getDisease());
        model.setDisease(medicalCare.getDisease().getId());
        model.setDocterName(medicalCare.getDocterName());
        model.setEmpDataByEmpDataId(medicalCare.getEmpData());
        model.setEmpDataByTemporaryActingId(medicalCare.getTemporaryActing());
        model.setStartDate(medicalCare.getStartDate());
        model.setEndDate(medicalCare.getEndDate());
        if(medicalCare.getHospital() != null){
        	model.setHospitalId(medicalCare.getHospital());
            model.setHospital(medicalCare.getHospital().getId());
        }
        isUseHospital = medicalCare.getHospital() != null;
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

    public void doSelectOneMenuHospital() throws Exception {
        hospitals = hospitalService.getAllData();

        for (Hospital hospital : hospitals) {
            dropDownHospital.put(hospital.getName(), hospital.getId());
        }

        diseases = diseaseService.getAllData();

        for (Disease disease : diseases) {
            dropDownDisease.put(disease.getName(), disease.getId());
        }

    }

    public Map<String, Long> getDropDownDisease() {
        return dropDownDisease;
    }

    public void setDropDownDisease(Map<String, Long> dropDownDisease) {
        this.dropDownDisease = dropDownDisease;
    }

    public List<Disease> getDiseases() {
        return diseases;
    }

    public void setDiseases(List<Disease> diseases) {
        this.diseases = diseases;
    }

    public void handingFileUpload(FileUploadEvent fileUploadEvent) {
        Map<String, String> results = uploadFilesUtil.checkUploadFileSizeLimit(fileUploadEvent.getFile());
        if (StringUtils.equals(results.get("result"), "true")) {
            documentFile = fileUploadEvent.getFile();
            model.setUploadPath(documentFile.getFileName());
        } else {
            ResourceBundle messages = ResourceBundle.getBundle("Messages", new Locale(FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString()));
            String errorMsg = messages.getString("global.file_size_should_not_bigger_than") + " " + results.get("sizeMax");
            MessagesResourceUtil.setMessagesFromException(FacesMessage.SEVERITY_ERROR, "global.error", errorMsg, FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
        }
    }

    public void onChangeEmployee() {
        try {
            EmpData empData = empDataService.getByEmpIdWithDetail(model.getEmpDataByEmpDataId().getId());

            model.setJabatan(empData.getJabatanByJabatanId().getName());

        } catch (Exception e) {
            LOGGER.error("Error", e);
        }
    }

    public void onChangeStartOrEndDate() {
        try {

            if (model.getStartDate() != null && model.getEndDate() != null) {
                model.setTotalDays(DateTimeUtil.getTotalDay(model.getStartDate(), model.getEndDate()));
            }
        } catch (Exception e) {
            LOGGER.error("Error", e);
        }
    }
}
