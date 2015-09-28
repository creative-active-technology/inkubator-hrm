package com.inkubator.hrm.web.reference;

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

import com.inkubator.exception.BussinessException;
import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.entity.Document;
import com.inkubator.hrm.service.DocumentService;
import com.inkubator.hrm.util.UploadFilesUtil;
import com.inkubator.hrm.web.model.DocumentModel;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesUtil;
import com.inkubator.webcore.util.MessagesResourceUtil;

@ManagedBean(name = "documentFormController")
@ViewScoped
public class DocumentFormController extends BaseController{
	@ManagedProperty(value = "#{documentService}")
	private DocumentService service;
	private Document selected;
	private DocumentModel model;
	private Boolean isUpdate;
	private UploadedFile documentFile;
	@ManagedProperty(value = "#{uploadFilesUtil}")
	private UploadFilesUtil uploadFilesUtil;
	
	
	@PostConstruct
    @Override
    public void initialization() {
        super.initialization();
        try{
            String documentId = FacesUtil.getRequestParameter("documentId");
            model = new DocumentModel();
            isUpdate = Boolean.FALSE;
            if (StringUtils.isNotEmpty(documentId)) {
                Document document = service.getEntiyByPK(Long.parseLong(documentId));
                if(documentId != null){
                    model = getModelFromEntity(document);
                    isUpdate = Boolean.TRUE;
                }
            }
            
        }catch (Exception e){
            LOGGER.error("Error", e);
        }
    }
	
	@PreDestroy
	public void cleanAndExit(){
		isUpdate = null;
		model = null;
		service = null;
		selected =null;
	}
	
	private DocumentModel getModelFromEntity(Document entity) {
        DocumentModel model = new DocumentModel();
        model.setId(entity.getId());
        model.setCode(entity.getCode());
        model.setName(entity.getName());
        model.setDescription(entity.getDescription());
        model.setDocumentTitle(entity.getDocumentTitle());
        model.setUploadFileName(entity.getUploadFileName());
        return model;
    }
	
	private Document getEntityFromViewModel(DocumentModel model) {
        Document document = new Document();
        if (model.getId() != null) {
            document.setId(model.getId());
        }
        document.setCode(model.getCode());
        document.setName(model.getName());
        document.setDescription(model.getDescription());
        document.setDocumentTitle(model.getDocumentTitle());
        return document;
    }
	
	public void doSave() {
        
        Document document = getEntityFromViewModel(model);
        try {
            if (isUpdate) {
                service.update(document, documentFile);
                RequestContext.getCurrentInstance().closeDialog(HRMConstant.UPDATE_CONDITION);
            } else {
                service.save(document, documentFile);
                RequestContext.getCurrentInstance().closeDialog(HRMConstant.SAVE_CONDITION);
            }
            cleanAndExit();
        } catch (BussinessException ex) { 
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_ERROR, "global.error", ex.getErrorKeyMessage(), FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
    }
	
	public void handingFileUpload(FileUploadEvent fileUploadEvent) {
		Map<String, String> results = uploadFilesUtil.checkUploadFileSizeLimit(fileUploadEvent.getFile());
		if(StringUtils.equals(results.get("result"),"true")){
			documentFile = fileUploadEvent.getFile();
			model.setUploadFileName(documentFile.getFileName());     
			model.setDocumentTitle(documentFile.getFileName());
		} else {
			ResourceBundle messages = ResourceBundle.getBundle("Messages", new Locale(FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString()));
			String errorMsg = messages.getString("global.file_size_should_not_bigger_than") + " " + results.get("sizeMax");
			MessagesResourceUtil.setMessagesFromException(FacesMessage.SEVERITY_ERROR, "global.error", errorMsg, FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
		}
	}

	public DocumentService getService() {
		return service;
	}

	public void setService(DocumentService service) {
		this.service = service;
	}

	public Document getSelected() {
		return selected;
	}

	public void setSelected(Document selected) {
		this.selected = selected;
	}

	public DocumentModel getModel() {
		return model;
	}

	public void setModel(DocumentModel model) {
		this.model = model;
	}

	public Boolean getIsUpdate() {
		return isUpdate;
	}

	public void setIsUpdate(Boolean isUpdate) {
		this.isUpdate = isUpdate;
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
	
	
}
