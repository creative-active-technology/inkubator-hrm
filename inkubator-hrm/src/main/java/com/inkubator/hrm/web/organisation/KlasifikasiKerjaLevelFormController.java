/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.organisation;

import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.entity.KlasifikasiKerja;
import com.inkubator.hrm.entity.KlasifikasiKerjaLevel;
import com.inkubator.hrm.service.KlasifikasiKerjaLevelService;
import com.inkubator.hrm.service.KlasifikasiKerjaService;
import com.inkubator.hrm.web.model.KlasifikasiKerjaLevelModel;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesUtil;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import org.apache.commons.lang3.StringUtils;
import org.primefaces.context.RequestContext;

/**
 *
 * @author EKA
 */
@ManagedBean(name = "klasifikasiKerjaLevelFormController")
@ViewScoped
public class KlasifikasiKerjaLevelFormController extends BaseController{
    @ManagedProperty(value = "#{klasifikasiKerjaLevelService}")
    private KlasifikasiKerjaLevelService service;
    private KlasifikasiKerjaLevel selected;
    private KlasifikasiKerjaLevelModel model;
    private Boolean isUpdate;
    @ManagedProperty(value = "#{klasifikasiKerjaService}")
    private KlasifikasiKerjaService klasifikasiKerjaService;
    
    private List<KlasifikasiKerja> klasifikasiKerjaList = new ArrayList<KlasifikasiKerja>();
    //private Map<String, Long> dropDownKlasifikasiKerja = new TreeMap<String, Long>();
    
    @PostConstruct
    @Override
    public void initialization(){
        super.initialization();
        try{
            String klasifikasiKerjaLevelId = FacesUtil.getRequestParameter("klasifikasiKerjaLevelId");
            model = new KlasifikasiKerjaLevelModel();
            klasifikasiKerjaList = klasifikasiKerjaService.getAllData();
            isUpdate = Boolean.FALSE;
            if(StringUtils.isNotEmpty(klasifikasiKerjaLevelId)){
                KlasifikasiKerjaLevel klasifikasiKerjaLevel = service.getEntiyByPK(Long.parseLong(klasifikasiKerjaLevelId));
                if(klasifikasiKerjaLevelId != null){
                    model = getModelFromEntity(klasifikasiKerjaLevel);
                    isUpdate = Boolean.TRUE;
                }
            }
//            doSelectOneMenuKlasifikasiKerja();
        } catch (Exception e){
            LOGGER.error("error", e);
        }
    }
    
    @PreDestroy
    public void cleanAndExit(){
        isUpdate = null;
        model = null;
        selected = null;
        klasifikasiKerjaService = null;
        klasifikasiKerjaList = null;
       // dropDownKlasifikasiKerja = null;
    }
    
    private KlasifikasiKerjaLevelModel getModelFromEntity(KlasifikasiKerjaLevel entity){
        KlasifikasiKerjaLevelModel model = new KlasifikasiKerjaLevelModel();
        model.setId(entity.getId());
        model.setCode(entity.getCode());
        model.setName(entity.getName());
        model.setKlasifikasiKerjaId(entity.getKlasifikasiKerja().getId());
        model.setDescription(entity.getDescription());
        return model;
    }
    
    private KlasifikasiKerjaLevel getEntityFromViewModel(KlasifikasiKerjaLevelModel model){
        KlasifikasiKerjaLevel klasifikasiKerjaLevel = new KlasifikasiKerjaLevel();
        if(model.getId() != null){
            klasifikasiKerjaLevel.setId(model.getId());
        }
        klasifikasiKerjaLevel.setCode(model.getCode());
        klasifikasiKerjaLevel.setName(model.getName());
        klasifikasiKerjaLevel.setKlasifikasiKerja(new KlasifikasiKerja(model.getKlasifikasiKerjaId()));
        klasifikasiKerjaLevel.setDescription(model.getDescription());
        return klasifikasiKerjaLevel;
    }
    
    public void doSave(){
        KlasifikasiKerjaLevel klasifikasiKerjaLevel = getEntityFromViewModel(model);
        try{
            if(isUpdate){
                service.update(klasifikasiKerjaLevel);
                RequestContext.getCurrentInstance().closeDialog(HRMConstant.UPDATE_CONDITION);
            } else {
                service.save(klasifikasiKerjaLevel);
                RequestContext.getCurrentInstance().closeDialog(HRMConstant.SAVE_CONDITION);
            }
            cleanAndExit();
        } catch (Exception ex){
            LOGGER.error("Error", ex);
        }
    }

    public KlasifikasiKerjaLevelService getService() {
        return service;
    }

    public void setService(KlasifikasiKerjaLevelService service) {
        this.service = service;
    }

    public KlasifikasiKerjaLevel getSelected() {
        return selected;
    }

    public void setSelected(KlasifikasiKerjaLevel selected) {
        this.selected = selected;
    }

    public KlasifikasiKerjaLevelModel getModel() {
        return model;
    }

    public void setModel(KlasifikasiKerjaLevelModel model) {
        this.model = model;
    }

    public Boolean getIsUpdate() {
        return isUpdate;
    }

    public void setIsUpdate(Boolean isUpdate) {
        this.isUpdate = isUpdate;
    }

    public KlasifikasiKerjaService getKlasifikasiKerjaService() {
        return klasifikasiKerjaService;
    }

    public void setKlasifikasiKerjaService(KlasifikasiKerjaService klasifikasiKerjaService) {
        this.klasifikasiKerjaService = klasifikasiKerjaService;
    }

    public List<KlasifikasiKerja> getKlasifikasiKerjaList() {
        return klasifikasiKerjaList;
    }

    public void setKlasifikasiKerjaList(List<KlasifikasiKerja> klasifikasiKerjaList) {
        this.klasifikasiKerjaList = klasifikasiKerjaList;
    }

//    public Map<String, Long> getDropDownKlasifikasiKerja() {
//        return dropDownKlasifikasiKerja;
//    }
//
//    public void setDropDownKlasifikasiKerja(Map<String, Long> dropDownKlasifikasiKerja) {
//        this.dropDownKlasifikasiKerja = dropDownKlasifikasiKerja;
//    }
    
//    public void doSelectOneMenuKlasifikasiKerja() throws Exception{
//        klasifikasiKerja = klasifikasiKerjaService.getAllData();
//        
//        for(KlasifikasiKerja klasifikasiKerjaList : klasifikasiKerja){
//            dropDownKlasifikasiKerja.put(klasifikasiKerjaList.getKlasifikasiKerjaName(), klasifikasiKerjaList.getId());
//        }
//    }
}
