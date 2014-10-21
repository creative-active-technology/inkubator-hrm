/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.workingtime;

import com.inkubator.webcore.controller.BaseController;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Deni
 */
@ManagedBean(name = "mecineFingerServiceFormController")
@ViewScoped
public class MecineFingerServiceFormController extends BaseController{
    
    @PostConstruct
    @Override
    public void initialization() {
        super.initialization();
        try{
            
            
        }catch (Exception e){
            LOGGER.error("Error", e);
        }
    }
    
    @PreDestroy
    public void cleanAndExit() {

    }
    
    public String doSave(){
        return "/protected/working_time/mecine_finger_view.htm?faces-redirect=true";
    }
    
    public void doReset() {
        cleanAndExit();
    }
    
    public String doBack(){
         return "/protected/working_time/mecine_finger_view.htm?faces-redirect=true";
    }
    
}
