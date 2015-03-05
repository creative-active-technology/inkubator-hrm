/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.personalia;

import com.inkubator.exception.BussinessException;
import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.entity.BioData;
import com.inkubator.hrm.entity.BioPotensiSwot;
import com.inkubator.hrm.service.BioPotensiSwotService;
import com.inkubator.hrm.web.model.BioPotensiSwotModel;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesUtil;
import com.inkubator.webcore.util.MessagesResourceUtil;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import org.apache.commons.lang3.StringUtils;
import org.primefaces.context.RequestContext;

/**
 *
 * @author Deni
 */
@ManagedBean(name = "bioPotensiSwotFormController")
@ViewScoped
public class BioPotensiSwotFormController extends BaseController{

    @ManagedProperty(value = "#{bioPotensiSwotService}")
    private BioPotensiSwotService service;
    private BioPotensiSwot selected;
    private BioPotensiSwotModel model;
    private Boolean isUpdate;
    private Long bioDataId;
    
    @PostConstruct
    @Override
    public void initialization() {
        super.initialization();
        try{
            String bioPotensiSwotId = FacesUtil.getRequestParameter("bioPotensiSwotId");
            bioDataId = Long.parseLong(FacesUtil.getRequestParameter("bioDataId"));
            model = new BioPotensiSwotModel();
            isUpdate = Boolean.FALSE;
            if (StringUtils.isNotEmpty(bioPotensiSwotId)) {
                BioPotensiSwot bioPotensiSwot = service.getEntiyByPK(Long.parseLong(bioPotensiSwotId));
                if(bioPotensiSwotId != null){
                    model = getModelFromEntity(bioPotensiSwot);
                    isUpdate = Boolean.TRUE;
                }
            }
            
        }catch (Exception e){
            LOGGER.error("Error", e);
        }
    }
    
    @PreDestroy
    public void cleanAndExit() {
        isUpdate = null;
        model = null;
        bioDataId = null;
        service = null;
        selected = null;
    }
    
    private BioPotensiSwotModel getModelFromEntity(BioPotensiSwot entity) {
        BioPotensiSwotModel model = new BioPotensiSwotModel();
        model.setId(entity.getId());
        model.setLabelPotensi(entity.getLabelPotensi());
        model.setBioDataId(entity.getBioData().getId());
        model.setDescription(entity.getDescription());
        model.setKlasifikasi(entity.getKlasifikasi());
        model.setPotensiPoint(entity.getPotensiPoint());
        return model;
    }
    
    private BioPotensiSwot getEntityFromViewModel(BioPotensiSwotModel model) {
        BioPotensiSwot bioPotensiSwot = new BioPotensiSwot();
        if (model.getId() != null) {
            bioPotensiSwot.setId(model.getId());
        }
        bioPotensiSwot.setLabelPotensi(model.getLabelPotensi());
        bioPotensiSwot.setBioData(new BioData(bioDataId));
        bioPotensiSwot.setDescription(model.getDescription());
        bioPotensiSwot.setKlasifikasi(model.getKlasifikasi());
        bioPotensiSwot.setPotensiPoint(model.getPotensiPoint());
        return bioPotensiSwot;
    }
    
    public void doSave() {
        
        BioPotensiSwot bioPotensiSwot = getEntityFromViewModel(model);
        try {
            if (isUpdate) {
                service.update(bioPotensiSwot);
                RequestContext.getCurrentInstance().closeDialog(HRMConstant.UPDATE_CONDITION);
            } else {
                service.save(bioPotensiSwot);
                RequestContext.getCurrentInstance().closeDialog(HRMConstant.SAVE_CONDITION);
            }
            cleanAndExit();
        } catch (BussinessException ex) { 
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_ERROR, "global.error", ex.getErrorKeyMessage(), FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
    }

    public BioPotensiSwotService getService() {
        return service;
    }

    public void setService(BioPotensiSwotService service) {
        this.service = service;
    }

    public BioPotensiSwot getSelected() {
        return selected;
    }

    public void setSelected(BioPotensiSwot selected) {
        this.selected = selected;
    }

    public BioPotensiSwotModel getModel() {
        return model;
    }

    public void setModel(BioPotensiSwotModel model) {
        this.model = model;
    }

    public Boolean getIsUpdate() {
        return isUpdate;
    }

    public void setIsUpdate(Boolean isUpdate) {
        this.isUpdate = isUpdate;
    }

    public Long getBioDataId() {
        return bioDataId;
    }

    public void setBioDataId(Long bioDataId) {
        this.bioDataId = bioDataId;
    }
    
    
}
