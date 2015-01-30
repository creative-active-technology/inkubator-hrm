/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.workingtime;

import com.inkubator.exception.BussinessException;
import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.entity.MecineFinger;
import com.inkubator.hrm.service.MecineFingerService;
import com.inkubator.hrm.web.model.MecineFingerModel;
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
@ManagedBean(name = "machineFingerFormController")
@ViewScoped
public class MecineFingerFormController extends BaseController{
    @ManagedProperty(value = "#{mecineFingerService}")
    private MecineFingerService service;
    private MecineFinger seleced;
    private Boolean isUpdate;
    private MecineFingerModel model;
    
    @PostConstruct
    @Override
    public void initialization() {
        super.initialization();
        try{
            String mecineFingerId = FacesUtil.getRequestParameter("mecineFingerId");
            model = new MecineFingerModel();
            isUpdate = Boolean.FALSE;
            if (StringUtils.isNotEmpty(mecineFingerId)) {
                MecineFinger mecineFinger = service.getEntiyByPK(Long.parseLong(mecineFingerId));
                if(mecineFingerId != null){
                    model = getModelFromEntity(mecineFinger);
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
        service = null;
        model = null;
        seleced = null;
    }
    
    private MecineFingerModel getModelFromEntity(MecineFinger entity) {
        MecineFingerModel mecineFingerModel = new MecineFingerModel();
        mecineFingerModel.setId(entity.getId());
        mecineFingerModel.setMecineMethode(entity.getMecineMethode());
        mecineFingerModel.setName(entity.getName());
        mecineFingerModel.setCode(entity.getCode());
        mecineFingerModel.setDescription(entity.getDescription());
        return mecineFingerModel;
    }
    
    private MecineFinger getEntityFromViewModel(MecineFingerModel mecineFingerModel) {
        MecineFinger mecineFinger = new MecineFinger();
        if (mecineFingerModel.getId() != null) {
            mecineFinger.setId(mecineFingerModel.getId());
        }
        mecineFinger.setMecineMethode(mecineFingerModel.getMecineMethode());
        mecineFinger.setName(mecineFingerModel.getName());
        mecineFinger.setCode(mecineFingerModel.getCode());
        mecineFinger.setDescription(mecineFingerModel.getDescription());
        return mecineFinger;
    }
    
    public void doSave() {
        
        MecineFinger mecineFinger = getEntityFromViewModel(model);
        try {
            if (isUpdate) {
                service.update(mecineFinger);
                RequestContext.getCurrentInstance().closeDialog(HRMConstant.UPDATE_CONDITION);
            } else {
                service.save(mecineFinger);
                RequestContext.getCurrentInstance().closeDialog(HRMConstant.SAVE_CONDITION);
            }
            cleanAndExit();
        } catch (BussinessException ex) { 
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_ERROR, "global.error", ex.getErrorKeyMessage(), FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
    }

    public MecineFingerService getService() {
        return service;
    }

    public void setService(MecineFingerService service) {
        this.service = service;
    }

    public MecineFinger getSeleced() {
        return seleced;
    }

    public void setSeleced(MecineFinger seleced) {
        this.seleced = seleced;
    }

    public Boolean getIsUpdate() {
        return isUpdate;
    }

    public void setIsUpdate(Boolean isUpdate) {
        this.isUpdate = isUpdate;
    }

    public MecineFingerModel getModel() {
        return model;
    }

    public void setModel(MecineFingerModel model) {
        this.model = model;
    }
    
    
}
