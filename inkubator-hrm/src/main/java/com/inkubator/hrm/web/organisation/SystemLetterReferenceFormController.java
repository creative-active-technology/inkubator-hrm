/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.organisation;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

import com.inkubator.exception.BussinessException;
import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.entity.BioData;
import com.inkubator.hrm.entity.EmpData;
import com.inkubator.hrm.entity.SystemLetterReference;
import com.inkubator.hrm.service.BioDataService;
import com.inkubator.hrm.service.SystemLetterReferenceService;
import com.inkubator.hrm.util.HrmUserInfoUtil;
import com.inkubator.hrm.web.model.SystemLetterReferenceModel;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesIO;
import com.inkubator.webcore.util.FacesUtil;
import com.inkubator.webcore.util.MessagesResourceUtil;

/*--------------------------------------------------------------------
 *  Author:        Deni
 *  Written:       4/5/2015
 *  Finish:        5/5/2015
 *  Last updated:  11/11/2015
 *
 *  
 *  a class to run the process save and update System Letter Reference
 *
 *
 *--------------------------------------------------------------------*/
@ManagedBean(name = "systemLetterReferenceFormController")
@ViewScoped
public class SystemLetterReferenceFormController extends BaseController {

    @ManagedProperty(value = "#{systemLetterReferenceService}")
    private SystemLetterReferenceService systemLetterReferenceService;
    @ManagedProperty(value = "#{bioDataService}")
    private BioDataService bioDataService;
    @ManagedProperty(value = "#{facesIO}")
    private FacesIO facesIO;
    private BioData bioData;
    private SystemLetterReferenceModel model;
    private UploadedFile fileUpload;
    private Boolean isUpdate;
    private Boolean isDisabledDesign;
    private Boolean isDisabledUpload;
    
    @PostConstruct
    @Override
    public void initialization() {
        super.initialization();
        model = new SystemLetterReferenceModel();
        isUpdate = Boolean.FALSE;
        isDisabledUpload = Boolean.TRUE;
        isDisabledDesign = Boolean.TRUE;
        String systemLetterReferenceId = FacesUtil.getRequestParameter("execution");
        if (systemLetterReferenceId != null) {
            try {
                SystemLetterReference systemLetterReference = systemLetterReferenceService.getEntiyByPK(Long.valueOf(systemLetterReferenceId.substring(1)));
                if (systemLetterReference != null) {
                    model = getModelFromEntity(systemLetterReference);
                    isUpdate = Boolean.TRUE;
                }
            } catch (Exception ex) {
                Logger.getLogger(SystemLetterReferenceFormController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @PreDestroy
    private void cleanAndExit() {
        model = null;
        systemLetterReferenceService = null;
        fileUpload = null;
        facesIO = null;
        isUpdate = null;
        bioDataService = null;
    }

    /*--------------------------------------------------------------------
     *  get entity value from method getEntityFromViewModel
     *  do save if isUpdate is false, and update if isUpdate is true
     *  if success will return to system_letter_view.htm
     *  
     *  return BisnisException if there are errors in bisnis process
     *  
     *  return exception if there are errors in coding
     *--------------------------------------------------------------------*/
    public String doSave() throws Exception {
        SystemLetterReference systemLetterReference = getEntityFromViewModel(model);
        try {
            if (isUpdate) {
                systemLetterReferenceService.update(systemLetterReference, fileUpload);
                MessagesResourceUtil.setMessagesFlas(FacesMessage.SEVERITY_INFO, "global.save_info", "global.update_successfully",
                        FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
            } else {
                systemLetterReferenceService.save(systemLetterReference, fileUpload);
                MessagesResourceUtil.setMessagesFlas(FacesMessage.SEVERITY_INFO, "global.save_info", "global.added_successfully",
                        FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
            }
            return "/protected/organisation/system_letter_view.htm?faces-redirect=true";

        } catch (BussinessException ex) {
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_ERROR, "global.error", ex.getErrorKeyMessage(), FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
        return null;
    }

    /*--------------------------------------------------------------------
     *  insert value from entity to model for form view component
     *  
     *  return SystemLetterReferenceModel
     *--------------------------------------------------------------------*/
    private SystemLetterReferenceModel getModelFromEntity(SystemLetterReference entity) {
        SystemLetterReferenceModel model = new SystemLetterReferenceModel();
        model.setId(entity.getId());
        model.setCode(entity.getCode());
        model.setName(entity.getName());
        model.setDescription(entity.getDescription());
        model.setContent(entity.getContent());
        model.setLetterSumary(entity.getLetterSumary());
        model.setEffectiveDate(entity.getEffectiveDate());
        model.setIsActive(entity.getIsActive());
        model.setFileUploadName(entity.getFileUploadName());
        model.setGroupReference(entity.getGroupReference());
        return model;
    }

    /*--------------------------------------------------------------------
     *  insert value from form view to class entity for save or update
     *  
     *  return SystemLetterReference
     *--------------------------------------------------------------------*/
    private SystemLetterReference getEntityFromViewModel(SystemLetterReferenceModel model) throws Exception {
        SystemLetterReference systemLetterReference = new SystemLetterReference();
        if (model.getId() != null) {
            systemLetterReference.setId(model.getId());
        }
        systemLetterReference.setCode(model.getCode());
        systemLetterReference.setName(model.getName());
        systemLetterReference.setContent(model.getContent());
        systemLetterReference.setDescription(model.getDescription());
        systemLetterReference.setLetterSumary(model.getLetterSumary());
        systemLetterReference.setEffectiveDate(model.getEffectiveDate());
        systemLetterReference.setIsActive(model.getIsActive());
        systemLetterReference.setFileUploadName(model.getFileUploadName());
        systemLetterReference.setGroupReference(model.getGroupReference());
        return systemLetterReference;
    }

    /*----------------------------------------------------------------------------
     *  handling FileUpload event from form view, 
     *  and get file upload name to model
     *----------------------------------------------------------------------------*/
    public void handleFileUpload(FileUploadEvent event) {
        fileUpload = event.getFile();
        model.setFileUploadName(fileUpload.getFileName());
    }

    /*----------------------------------------------------------------------------
     *  change disabled upload or design by typeContent choosen
     *  0 = upload
     *  1 = design
     *----------------------------------------------------------------------------*/
    public void doChangeUploadOrDesign(){
    	if(model.getTypeContent() == 0){
    		isDisabledUpload = Boolean.FALSE;
    		isDisabledDesign = Boolean.TRUE;
    	}else{
    		isDisabledUpload = Boolean.TRUE;
    		isDisabledDesign = Boolean.FALSE;
    	}
    }
    
    public void doGetBioData() throws Exception{
    	bioData = bioDataService.getEntiyByPK(HrmUserInfoUtil.getBioDataId());
    }
    
    public String doBack() {
        return "/protected/organisation/system_letter_view.htm?faces-redirect=true";
    }
    
    public void doReset() throws Exception {
        if(isUpdate){
            SystemLetterReference systemLetterReference = systemLetterReferenceService.getEntiyByPK(model.getId());
            model = getModelFromEntity(systemLetterReference);
        }else{
            model.setCode(null);
            model.setDescription(null);
            model.setEffectiveDate(null);
            model.setFileUpload(null);
            model.setFileUploadName(null);
            model.setIsActive(null);
            model.setName(null);
            model.setUploadData(null);
        }
    }
    
    public SystemLetterReferenceService getSystemLetterReferenceService() {
        return systemLetterReferenceService;
    }

    public void setSystemLetterReferenceService(SystemLetterReferenceService systemLetterReferenceService) {
        this.systemLetterReferenceService = systemLetterReferenceService;
    }

    public FacesIO getFacesIO() {
        return facesIO;
    }

    public void setFacesIO(FacesIO facesIO) {
        this.facesIO = facesIO;
    }

    public SystemLetterReferenceModel getModel() {
        return model;
    }

    public void setModel(SystemLetterReferenceModel model) {
        this.model = model;
    }

    public UploadedFile getFileUpload() {
        return fileUpload;
    }

    public void setFileUpload(UploadedFile fileUpload) {
        this.fileUpload = fileUpload;
    }

	public Boolean getIsDisabledDesign() {
		return isDisabledDesign;
	}

	public void setIsDisabledDesign(Boolean isDisabledDesign) {
		this.isDisabledDesign = isDisabledDesign;
	}

	public Boolean getIsDisabledUpload() {
		return isDisabledUpload;
	}

	public void setIsDisabledUpload(Boolean isDisabledUpload) {
		this.isDisabledUpload = isDisabledUpload;
	}

	public BioDataService getBioDataService() {
		return bioDataService;
	}

	public void setBioDataService(BioDataService bioDataService) {
		this.bioDataService = bioDataService;
	}

	public BioData getBioData() {
		return bioData;
	}

	public void setBioData(BioData bioData) {
		this.bioData = bioData;
	}

    
}
