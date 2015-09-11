/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.organisation;

import com.inkubator.hrm.entity.KlasifikasiKerjaLevel;
import com.inkubator.hrm.service.KlasifikasiKerjaLevelService;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesUtil;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author EKA
 */
@ManagedBean (name = "klasifikasiKerjaLevelDetailController")
@ViewScoped
public class KlasifikasiKerjaLevelDetailController extends BaseController{
    
    private KlasifikasiKerjaLevel selected;
    @ManagedProperty (value = "#{klasifikasiKerjaLevelService}")
    private KlasifikasiKerjaLevelService klasifikasiKerjaLevelService;
    
    @PostConstruct
    @Override
    public void initialization(){
        try{
            super.initialization();
            String execution = FacesUtil.getRequestParameter("execution");
            String param = execution.substring(0, 1);
            if(StringUtils.equals(param, "e")){
                selected = klasifikasiKerjaLevelService.getEntityWithDetail(Long.parseLong(execution.substring(1)));
            }
        } catch (Exception ex){
            LOGGER.error("error", ex);
        }
    }
    
    @PreDestroy
    public void cleanAndExit(){
        selected = null;
        klasifikasiKerjaLevelService = null;
    }

    public KlasifikasiKerjaLevel getSelected() {
        return selected;
    }

    public void setSelected(KlasifikasiKerjaLevel selected) {
        this.selected = selected;
    }

    public KlasifikasiKerjaLevelService getKlasifikasiKerjaLevelService() {
        return klasifikasiKerjaLevelService;
    }

    public void setKlasifikasiKerjaLevelService(KlasifikasiKerjaLevelService klasifikasiKerjaLevelService) {
        this.klasifikasiKerjaLevelService = klasifikasiKerjaLevelService;
    }
    
    public String doBack(){
        return "/protected/reference/klaskerja_level_view.htm?faces-redirect=true";
    }
    
    public String doUpdate(){
        return "/protected/reference/klaskerja_level_form.htm?faces-redirect=true&execution=e" + selected.getId();
    }
}
