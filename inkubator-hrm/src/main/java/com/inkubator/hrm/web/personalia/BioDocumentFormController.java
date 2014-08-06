package com.inkubator.hrm.web.personalia;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
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
import com.inkubator.hrm.service.BioDocumentService;
import com.inkubator.hrm.web.model.BioDocumentModel;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesUtil;

/**
 *
 * @author rizkykojek
 */
@ManagedBean(name = "bioDocumentFormController")
@ViewScoped
public class BioDocumentFormController extends BaseController {

	private BioDocumentModel model;
	private Boolean isUpdate;
	private UploadedFile documentFile;
	@ManagedProperty(value = "#{bioDocumentService}")
	private BioDocumentService bioDocumentService;
	
	@PostConstruct
    @Override
    public void initialization() {
        super.initialization();
        try {
            isUpdate = Boolean.FALSE;
            model = new BioDocumentModel();
            String bioDataId = FacesUtil.getRequestParameter("bioDataId");
            model.setBioDataId(Long.parseLong(bioDataId));
            
            String bioDocumentId = FacesUtil.getRequestParameter("bioDocumentId");
            if (StringUtils.isNotEmpty(bioDocumentId)) {
            	BioDocument bioDocument = bioDocumentService.getEntiyByPK(Long.parseLong(bioDocumentId));
            	if(bioDocument != null){
            		model = getModelFromEntity(bioDocument);
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
		bioDocumentService = null;
		documentFile = null;
	}

	public BioDocumentModel getModel() {
		return model;
	}

	public void setModel(BioDocumentModel model) {
		this.model = model;
	}

	public Boolean getIsUpdate() {
		return isUpdate;
	}

	public void setIsUpdate(Boolean isUpdate) {
		this.isUpdate = isUpdate;
	}

	public void setBioDocumentService(BioDocumentService bioDocumentService) {
		this.bioDocumentService = bioDocumentService;
	}
	
	public UploadedFile getDocumentFile() {
		return documentFile;
	}

	public void setDocumentFile(UploadedFile documentFile) {
		this.documentFile = documentFile;
	}

	public void doSave() {
        BioDocument bioDocument = getEntityFromViewModel(model);
        try {
            if (isUpdate) {
                bioDocumentService.update(bioDocument, documentFile);
                RequestContext.getCurrentInstance().closeDialog(HRMConstant.UPDATE_CONDITION);
            } else {
            	bioDocumentService.save(bioDocument, documentFile);
                RequestContext.getCurrentInstance().closeDialog(HRMConstant.SAVE_CONDITION);
            }
            cleanAndExit();
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
    }
	
	public void handingFileUpload(FileUploadEvent fileUploadEvent) {
		documentFile = fileUploadEvent.getFile();
		model.setUploadFileName(documentFile.getFileName());
	}
	
	private BioDocument getEntityFromViewModel(BioDocumentModel model) {
		BioDocument entity = new BioDocument();
		if(model.getId() != null){
			entity.setId(model.getId());
		}
		entity.setBioData(new BioData(model.getBioDataId()));
		entity.setDocumentTitle(model.getDocumentTitle());
		entity.setDocumentNo(model.getDocumentNo());
		entity.setDescription(model.getDescription());
		return entity;
	}

	private BioDocumentModel getModelFromEntity(BioDocument entity) {
		BioDocumentModel bioDocumentModel = new BioDocumentModel();
		bioDocumentModel.setId(entity.getId());
		bioDocumentModel.setBioDataId(entity.getBioData().getId());
		bioDocumentModel.setDocumentTitle(entity.getDocumentTitle());
		bioDocumentModel.setDocumentNo(entity.getDocumentNo());
		bioDocumentModel.setDescription(entity.getDescription());
		bioDocumentModel.setUploadFileName(entity.getUploadFileName());
		return bioDocumentModel;
	}
}
