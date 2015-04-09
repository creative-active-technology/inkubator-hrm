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
import com.inkubator.hrm.entity.OhsaIncident;
import com.inkubator.hrm.entity.OhsaIncidentDocument;
import com.inkubator.hrm.service.BioDocumentService;
import com.inkubator.hrm.service.BioSertifikasiService;
import com.inkubator.hrm.service.EducationNonFormalService;
import com.inkubator.hrm.service.OccupationTypeService;
import com.inkubator.hrm.service.OhsaIncidentDocumentService;
import com.inkubator.hrm.service.OhsaIncidentService;
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
 * @author Ahmad Mudzakkir Amal
 */
@ManagedBean(name = "ohsaIncidentDocumentFormController")
@ViewScoped
public class OhsaIncidentDocumentFormController extends BaseController {

    private OhsaIncidentDocument ohsaIncidentDocument;
    private Boolean isUpdate;
    private UploadedFile documentFile;
    @ManagedProperty(value = "#{ohsaIncidentDocumentService}")
    private OhsaIncidentDocumentService ohsaIncidentDocumentService;
    @ManagedProperty(value = "#{ohsaIncidentService}")
    private OhsaIncidentService ohsaIncidentService;
    @ManagedProperty(value = "#{uploadFilesUtil}")
    private UploadFilesUtil uploadFilesUtil;

    @PostConstruct
    @Override
    public void initialization() {
        super.initialization();
        try {

            isUpdate = Boolean.FALSE;
            ohsaIncidentDocument = new OhsaIncidentDocument();

            String ohsaIncidentId = FacesUtil.getRequestParameter("ohsaIncidentId");
            OhsaIncident ohsaIncident = ohsaIncidentService.getEntiyByPK(Long.parseLong(ohsaIncidentId));
            ohsaIncidentDocument.setOhsaIncident(ohsaIncident);

        } catch (Exception e) {
            LOGGER.error("Error", e);
        }
    }

    @PreDestroy
    public void cleanAndExit() {
        ohsaIncidentDocument = null;
        isUpdate = null;
        ohsaIncidentDocumentService = null;
        ohsaIncidentService = null;
        documentFile = null;
        uploadFilesUtil = null;
    }

    public Boolean getIsUpdate() {
        return isUpdate;
    }

    public void setIsUpdate(Boolean isUpdate) {
        this.isUpdate = isUpdate;
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

    public OhsaIncidentDocument getOhsaIncidentDocument() {
        return ohsaIncidentDocument;
    }

    public void setOhsaIncidentDocument(OhsaIncidentDocument ohsaIncidentDocument) {
        this.ohsaIncidentDocument = ohsaIncidentDocument;
    }

    public OhsaIncidentDocumentService getOhsaIncidentDocumentService() {
        return ohsaIncidentDocumentService;
    }

    public void setOhsaIncidentDocumentService(OhsaIncidentDocumentService ohsaIncidentDocumentService) {
        this.ohsaIncidentDocumentService = ohsaIncidentDocumentService;
    }

    public OhsaIncidentService getOhsaIncidentService() {
        return ohsaIncidentService;
    }

    public void setOhsaIncidentService(OhsaIncidentService ohsaIncidentService) {
        this.ohsaIncidentService = ohsaIncidentService;
    }
    
    
   
    public void doSave() {
      
        try {
            if (isUpdate) {
                ohsaIncidentDocumentService.update(ohsaIncidentDocument, documentFile);
                RequestContext.getCurrentInstance().closeDialog(HRMConstant.UPDATE_CONDITION);
            } else {
                ohsaIncidentDocumentService.save(ohsaIncidentDocument, documentFile);
                RequestContext.getCurrentInstance().closeDialog(HRMConstant.SAVE_CONDITION);
            }
            cleanAndExit();
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
    }

    public void handingFileUpload(FileUploadEvent fileUploadEvent) {
        Map<String, String> results = uploadFilesUtil.checkUploadFileSizeLimit(fileUploadEvent.getFile());
        if (StringUtils.equals(results.get("result"), "true")) {
            documentFile = fileUploadEvent.getFile();             
            ohsaIncidentDocument.setDocumentLabel(documentFile.getFileName());
        } else {
            ResourceBundle messages = ResourceBundle.getBundle("Messages", new Locale(FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString()));
            String errorMsg = messages.getString("global.file_size_should_not_bigger_than") + " " + results.get("sizeMax");
            MessagesResourceUtil.setMessagesFromException(FacesMessage.SEVERITY_ERROR, "global.error", errorMsg, FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
        }
    }

//    private BioSertifikasi getEntityFromViewModel(BioSertifikasiModel model) {
//        BioSertifikasi entity = new BioSertifikasi();
//        if (model.getId() != null) {
//            entity.setId(model.getId());
//        }
//        entity.setBioData(new BioData(model.getBioDataId()));
//        entity.setOccupationType(new OccupationType(occupationTypeId));
//        entity.setEducationNonFormal(new EducationNonFormal(eduNonFormalId));
//        entity.setDocumentTitle(model.getDocumentTitle());
//        entity.setNomorDokumen(model.getDocumentNo());
//        entity.setNamaSertifikasi(model.getNamaSertifikasi());
//        return entity;
//    }
//
//    private BioSertifikasiModel getModelFromEntity(BioSertifikasi entity) {
//        BioSertifikasiModel bioSertifikasiModel = new BioSertifikasiModel();
//        bioSertifikasiModel.setId(entity.getId());
//        bioSertifikasiModel.setBioDataId(entity.getBioData().getId());
//        bioSertifikasiModel.setDocumentTitle(entity.getDocumentTitle());
//        bioSertifikasiModel.setDocumentNo(entity.getNomorDokumen());
//        bioSertifikasiModel.setNamaSertifikasi(entity.getNamaSertifikasi());
//        bioSertifikasiModel.setUploadFileName(entity.getUploadFileName());
//        return bioSertifikasiModel;
//    }

}
