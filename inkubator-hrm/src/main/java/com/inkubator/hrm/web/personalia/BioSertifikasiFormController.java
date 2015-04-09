package com.inkubator.hrm.web.personalia;

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

import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.entity.BioData;
import com.inkubator.hrm.entity.BioDocument;
import com.inkubator.hrm.entity.BioSertifikasi;
import com.inkubator.hrm.entity.EducationNonFormal;
import com.inkubator.hrm.entity.OccupationType;
import com.inkubator.hrm.service.BioDocumentService;
import com.inkubator.hrm.service.BioSertifikasiService;
import com.inkubator.hrm.service.EducationNonFormalService;
import com.inkubator.hrm.service.OccupationTypeService;
import com.inkubator.hrm.util.UploadFilesUtil;
import com.inkubator.hrm.web.model.BioDocumentModel;
import com.inkubator.hrm.web.model.BioSertifikasiModel;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesUtil;
import com.inkubator.webcore.util.MessagesResourceUtil;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author rizkykojek
 */
@ManagedBean(name = "bioSertifikasiFormController")
@ViewScoped
public class BioSertifikasiFormController extends BaseController {

	private BioSertifikasiModel model;
	private Boolean isUpdate;
	private UploadedFile documentFile;	
        @ManagedProperty(value = "#{bioSertifikasiService}")
	private BioSertifikasiService bioSertifikasiService;
        @ManagedProperty(value = "#{educationNonFormalService}")
	private EducationNonFormalService educationNonFormalService;
        @ManagedProperty(value = "#{occupationTypeService}")
	private OccupationTypeService occupationTypeService;
	@ManagedProperty(value = "#{uploadFilesUtil}")
	private UploadFilesUtil uploadFilesUtil;
        private Map<String, Long> mapEduNonFormal = new HashMap<String, Long>();
        private Map<String, Long> mapOccupType = new HashMap<String, Long>();
	private Long eduNonFormalId;
        private Long occupationTypeId;
	@PostConstruct
    @Override
    public void initialization() {
        super.initialization();
        try {
            isUpdate = Boolean.FALSE;
            model = new BioSertifikasiModel();
            String bioDataId = FacesUtil.getRequestParameter("bioDataId");
            model.setBioDataId(Long.parseLong(bioDataId));
            
            List<EducationNonFormal> eduNonFormalList = educationNonFormalService.getAllData();
            for(EducationNonFormal eduNonFormal : eduNonFormalList){
                mapEduNonFormal.put(eduNonFormal.getName(), eduNonFormal.getId());
            }
            
            List<OccupationType> occupationTypeList = occupationTypeService.getAllData();
            for(OccupationType occupationType : occupationTypeList){
                mapOccupType.put(occupationType.getOccupationTypeName(), occupationType.getId());
            }
            
            String bioSertifikasiId = FacesUtil.getRequestParameter("bioSertifikasiId");
            if (StringUtils.isNotEmpty(bioSertifikasiId)) {
            	BioSertifikasi bioSertifikasi = bioSertifikasiService.getEntiyByPK(Long.parseLong(bioSertifikasiId));
            	if(bioSertifikasi != null){
            		model = getModelFromEntity(bioSertifikasi);
            		isUpdate = Boolean.TRUE;
                        eduNonFormalId = bioSertifikasi.getEducationNonFormal().getId();
                        occupationTypeId = bioSertifikasi.getOccupationType().getId();
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
		bioSertifikasiService = null;
		documentFile = null;
                uploadFilesUtil = null;
	}

	public BioSertifikasiModel getModel() {
		return model;
	}

	public void setModel(BioSertifikasiModel model) {
		this.model = model;
	}

	public Boolean getIsUpdate() {
		return isUpdate;
	}

	public void setIsUpdate(Boolean isUpdate) {
		this.isUpdate = isUpdate;
	}

    public BioSertifikasiService getBioSertifikasiService() {
        return bioSertifikasiService;
    }

    public void setBioSertifikasiService(BioSertifikasiService bioSertifikasiService) {
        this.bioSertifikasiService = bioSertifikasiService;
    }

    public EducationNonFormalService getEducationNonFormalService() {
        return educationNonFormalService;
    }

    public void setEducationNonFormalService(EducationNonFormalService educationNonFormalService) {
        this.educationNonFormalService = educationNonFormalService;
    }

    public OccupationTypeService getOccupationTypeService() {
        return occupationTypeService;
    }

    public void setOccupationTypeService(OccupationTypeService occupationTypeService) {
        this.occupationTypeService = occupationTypeService;
    }

	
	
	public void setUploadFilesUtil(UploadFilesUtil uploadFilesUtil) {
		this.uploadFilesUtil = uploadFilesUtil;
	}

	public UploadedFile getDocumentFile() {
		return documentFile;
	}

	public void setDocumentFile(UploadedFile documentFile) {
		this.documentFile = documentFile;
	}

    public Map<String, Long> getMapEduNonFormal() {
        return mapEduNonFormal;
    }

    public void setMapEduNonFormal(Map<String, Long> mapEduNonFormal) {
        this.mapEduNonFormal = mapEduNonFormal;
    }

    public Map<String, Long> getMapOccupType() {
        return mapOccupType;
    }

    public void setMapOccupType(Map<String, Long> mapOccupType) {
        this.mapOccupType = mapOccupType;
    }

    public Long getEduNonFormalId() {
        return eduNonFormalId;
    }

    public void setEduNonFormalId(Long eduNonFormalId) {
        this.eduNonFormalId = eduNonFormalId;
    }

    public Long getOccupationTypeId() {
        return occupationTypeId;
    }

    public void setOccupationTypeId(Long occupationTypeId) {
        this.occupationTypeId = occupationTypeId;
    }
        
       
	public void doSave() {
            BioSertifikasi bioSertifikasi = getEntityFromViewModel(model);
        try {
            if (isUpdate) {
                bioSertifikasiService.update(bioSertifikasi, documentFile);
                RequestContext.getCurrentInstance().closeDialog(HRMConstant.UPDATE_CONDITION);
            } else {
            	bioSertifikasiService.save(bioSertifikasi, documentFile);
                RequestContext.getCurrentInstance().closeDialog(HRMConstant.SAVE_CONDITION);
            }
            cleanAndExit();
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
    }
	
	public void handingFileUpload(FileUploadEvent fileUploadEvent) {
		Map<String, String> results = uploadFilesUtil.checkUploadFileSizeLimit(fileUploadEvent.getFile());
		if(StringUtils.equals(results.get("result"),"true")){
			documentFile = fileUploadEvent.getFile();
			model.setUploadFileName(documentFile.getFileName());
                        System.out.println("fileName ; " + documentFile.getFileName());
		} else {
			ResourceBundle messages = ResourceBundle.getBundle("Messages", new Locale(FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString()));
			String errorMsg = messages.getString("global.file_size_should_not_bigger_than") + " " + results.get("sizeMax");
			MessagesResourceUtil.setMessagesFromException(FacesMessage.SEVERITY_ERROR, "global.error", errorMsg, FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
		}
	}
	
	private BioSertifikasi getEntityFromViewModel(BioSertifikasiModel model) {
		BioSertifikasi entity = new BioSertifikasi();
		if(model.getId() != null){
			entity.setId(model.getId());
		}
		entity.setBioData(new BioData(model.getBioDataId()));
                entity.setOccupationType(new OccupationType(occupationTypeId));
                entity.setEducationNonFormal(new EducationNonFormal(eduNonFormalId));
		entity.setDocumentTitle(model.getDocumentTitle());
		entity.setNomorDokumen(model.getDocumentNo());
		entity.setNamaSertifikasi(model.getNamaSertifikasi());
		return entity;
	}

	private BioSertifikasiModel getModelFromEntity(BioSertifikasi entity) {
		BioSertifikasiModel bioSertifikasiModel = new BioSertifikasiModel();
		bioSertifikasiModel.setId(entity.getId());
		bioSertifikasiModel.setBioDataId(entity.getBioData().getId());
		bioSertifikasiModel.setDocumentTitle(entity.getDocumentTitle());
		bioSertifikasiModel.setDocumentNo(entity.getNomorDokumen());
		bioSertifikasiModel.setNamaSertifikasi(entity.getNamaSertifikasi());
		bioSertifikasiModel.setUploadFileName(entity.getUploadFileName());
		return bioSertifikasiModel;
	}
        
        
}
