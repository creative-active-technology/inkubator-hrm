/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.workingtime;

import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.entity.MecineFinger;
import com.inkubator.hrm.service.MecineFingerService;
import com.inkubator.hrm.web.model.MecineFingerQueryModel;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesUtil;
import com.inkubator.webcore.util.MessagesResourceUtil;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Deni Husni FR
 */
@ManagedBean(name = "fingerQueryFormController")
@ViewScoped
public class FingerQueryFormController extends BaseController {
    
    private MecineFingerQueryModel mecineFingerQueryModel;
    private List<String> dbTypes = new ArrayList<>();
    @ManagedProperty(value = "#{mecineFingerService}")
    private MecineFingerService mecineFingerService;
    private MecineFinger mecineFinger;
    
    @PostConstruct
    @Override
    public void initialization() {
        try {
            super.initialization();
            String param = FacesUtil.getRequestParameter("execution");
            mecineFinger = mecineFingerService.getMecineFingerAndDetaiUploadByFK(Long.parseLong(param.substring(1)));
            dbTypes.add("MySQL");
            dbTypes.add("Postgresql");
            dbTypes.add("SQLServer");
            dbTypes.add("MSAcess");
            dbTypes.add("Oracle");
            dbTypes.add("IBM-DB2");
            mecineFingerQueryModel = new MecineFingerQueryModel();
            if(mecineFinger!=null){
                mecineFingerQueryModel.setDbHost(mecineFinger.getDbHost());
                mecineFingerQueryModel.setDbPassword(mecineFinger.getDbPass());
                mecineFingerQueryModel.setDbQuery(mecineFinger.getQuery());
                mecineFingerQueryModel.setDbType(mecineFinger.getDbType());
                mecineFingerQueryModel.setSwapTimeFieldName(mecineFinger.getDbSwapBase());
                mecineFingerQueryModel.setEmployeeIdFieldName(mecineFinger.getMatchBase());
                mecineFingerQueryModel.setDbUserName(mecineFinger.getDbUser());
            }
            mecineFingerQueryModel.setId(mecineFinger.getId());
        } catch (Exception ex) {
            LOGGER.error("Errot", ex);
        }
        
    }
    
    @PreDestroy
    private void cleanAndExit() {
        
    }
    
    public void doDeleteData() {
        
    }
    
    public void setMecineFingerService(MecineFingerService mecineFingerService) {
        this.mecineFingerService = mecineFingerService;
    }
    
    public String doSave() {
        try {
            mecineFingerService.saveMesineQuery(mecineFingerQueryModel);
            MessagesResourceUtil.setMessagesFlas(FacesMessage.SEVERITY_INFO, "global.save_info", "global.added_successfully",
                    FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
            return "/protected/working_time/mecine_finger_view.htm?faces-redirect=true";
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
        
        return null;
    }
    
    public String doBack() {
        return "/protected/working_time/mecine_finger_view.htm?faces-redirect=true";
    }
    
    public MecineFingerQueryModel getMecineFingerQueryModel() {
        return mecineFingerQueryModel;
    }
    
    public void setMecineFingerQueryModel(MecineFingerQueryModel mecineFingerQueryModel) {
        this.mecineFingerQueryModel = mecineFingerQueryModel;
    }
    
    public List<String> getDbTypes() {
        return dbTypes;
    }
    
    public void setDbTypes(List<String> dbTypes) {
        this.dbTypes = dbTypes;
    }
    
    public MecineFinger getMecineFinger() {
        return mecineFinger;
    }
    
    public void setMecineFinger(MecineFinger mecineFinger) {
        this.mecineFinger = mecineFinger;
    }
    
    
    
    
}

