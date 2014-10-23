/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.workingtime;

import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.entity.MecineFinger;
import com.inkubator.hrm.service.MecineFingerService;
import com.inkubator.hrm.web.lazymodel.MecineFingerLazyDataModel;
import com.inkubator.hrm.web.search.MecineFingerSearchParameter;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesUtil;
import com.inkubator.webcore.util.MessagesResourceUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import org.apache.commons.lang.StringUtils;
import org.hibernate.exception.ConstraintViolationException;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.LazyDataModel;
import org.springframework.dao.DataIntegrityViolationException;

/**
 *
 * @author Deni Husni FR
 */
@ManagedBean(name = "mecineFingerViewController")
@ViewScoped
public class MecineFingerViewController extends BaseController {

    private MecineFingerSearchParameter mecineFingerSearchParamater;
    private LazyDataModel<MecineFinger> mecineFingerLazyDataModel;
    @ManagedProperty(value = "#{mecineFingerService}")
    private MecineFingerService service;
    private MecineFinger seleced;


    @PostConstruct
    @Override
    public void initialization() {
        super.initialization();
        mecineFingerSearchParamater = new MecineFingerSearchParameter();

    }

    public void doSearch() {
        mecineFingerLazyDataModel = null;
    }

   
    public void doDetail() {
        try {
            seleced = service.getEntiyByPK(seleced.getId());
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
    }

    public void doDelete() {
        try {
            this.service.delete(seleced);
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_INFO, "global.delete", "global.delete_successfully",
                    FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());

        } catch (ConstraintViolationException | DataIntegrityViolationException ex) {
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_ERROR, "global.error", "error.delete_constraint",
                    FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
            LOGGER.error("Error", ex);
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
    }

    public String doConfigure(){
        String page = StringUtils.EMPTY;
        if(Objects.equals(seleced.getMecineMethode(), HRMConstant.METHOD_UPLOAD_MACINE)){
            page = "upload";
        }else if(Objects.equals(seleced.getMecineMethode(), HRMConstant.METHOD_SERVICE_MACINE)){
            page = "service";
        }else if(Objects.equals(seleced.getMecineMethode(), HRMConstant.METHOD_QUERY_MACINE)){
            page = "query";
        }
        return "/protected/working_time/mecine_finger_"+ page +".htm?faces-redirect=true&execution=e" + seleced.getId();
    }
    
    public void doAdd() {
        Map<String, Object> options = new HashMap<>();
        options.put("modal", true);
        options.put("draggable", true);
        options.put("resizable", false);
        options.put("contentWidth", 400);
        options.put("contentHeight", 440);
        RequestContext.getCurrentInstance().openDialog("mecine_finger_form", options, null);
    }

    public void doEdit() {
        Map<String, Object> options = new HashMap<>();
        options.put("modal", true);
        options.put("draggable", true);
        options.put("resizable", false);
        options.put("contentWidth", 400);
        options.put("contentHeight", 440);
        Map<String, List<String>> dataToSend = new HashMap<>();
        List<String> dataIsi = new ArrayList<>();
        dataIsi.add(String.valueOf(seleced.getId()));
        dataToSend.put("mecineFingerId", dataIsi);
        RequestContext.getCurrentInstance().openDialog("mecine_finger_form", options, dataToSend);
    }

    @Override
    public void onDialogReturn(SelectEvent event) {
        mecineFingerLazyDataModel = null;
       super.onDialogReturn(event);
    }

    public void onDelete() {
        try {
            seleced = service.getEntiyByPK(seleced.getId());
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
    }

    @PreDestroy
    private void cleanAndExit() {
        mecineFingerSearchParamater = null;
        mecineFingerLazyDataModel=null;
        service=null;
        seleced=null;  
    }

    public MecineFingerSearchParameter getMecineFingerSearchParamater() {
        return mecineFingerSearchParamater;
    }

    public void setMecineFingerSearchParamater(MecineFingerSearchParameter mecineFingerSearchParamater) {
        this.mecineFingerSearchParamater = mecineFingerSearchParamater;
    }

    public LazyDataModel<MecineFinger> getMecineFingerLazyDataModel() {
        if (mecineFingerLazyDataModel == null) {
            mecineFingerLazyDataModel = new MecineFingerLazyDataModel(mecineFingerSearchParamater, service);
        }
        return mecineFingerLazyDataModel;
    }

    public void setMecineFingerLazyDataModel(LazyDataModel<MecineFinger> mecineFingerLazyDataModel) {
        this.mecineFingerLazyDataModel = mecineFingerLazyDataModel;
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
    
    
}
