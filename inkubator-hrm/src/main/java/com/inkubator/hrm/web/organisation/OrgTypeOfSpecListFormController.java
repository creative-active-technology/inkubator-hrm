/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.organisation;

import com.inkubator.exception.BussinessException;
import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.entity.OrgTypeOfSpec;
import com.inkubator.hrm.web.model.OrgTypeOfSpecListModel;
import com.inkubator.hrm.entity.OrgTypeOfSpecList;
import com.inkubator.hrm.service.OrgTypeOfSpecListService;
import com.inkubator.hrm.service.OrgTypeOfSpecService;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesUtil;
import com.inkubator.webcore.util.MessagesResourceUtil;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
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
 * @author EKA
 */
@ManagedBean(name = "orgTypeOfSpecListFormController")
@ViewScoped
public class OrgTypeOfSpecListFormController extends BaseController{
    @ManagedProperty(value = "#{orgTypeOfSpecListService}")
    private OrgTypeOfSpecListService service;
    private OrgTypeOfSpecList selected;
    private OrgTypeOfSpecListModel model;
    private Boolean isUpdate;
    @ManagedProperty(value = "#{orgTypeOfSpecService}")
    private OrgTypeOfSpecService orgTypeOfSpecService;
    
    private List<OrgTypeOfSpec> orgTypeOfSpec = new ArrayList<OrgTypeOfSpec>();
    private Map<String, Long> dropDownOrgTypeOfSpec = new TreeMap<String, Long>();
    
    @PostConstruct
    @Override
    public void initialization(){
        super.initialization();
        try{
            String orgTypeOfSpecListId = FacesUtil.getRequestParameter("orgTypeOfSpecListId");
            model = new OrgTypeOfSpecListModel();
            isUpdate = Boolean.FALSE;
            if(StringUtils.isNotEmpty(orgTypeOfSpecListId)){
                OrgTypeOfSpecList orgTypeOfSpecList = service.getEntiyByPK(Long.parseLong(orgTypeOfSpecListId));
                if(orgTypeOfSpecList != null){
                    model = getModelFromEntity(orgTypeOfSpecList);
                    isUpdate = Boolean.TRUE;
                }
            }
            doSelectOneMenuOrgTypeOfSpecList();
        } catch (Exception e){
            LOGGER.error("error", e);
        }
    }
    
    @PreDestroy
    public void cleanAndExit(){
        isUpdate = null;
        model = null;
        service = null;
        selected = null;
        orgTypeOfSpecService = null;
        orgTypeOfSpec = null;
        dropDownOrgTypeOfSpec = null;
    }
    
    private OrgTypeOfSpecListModel getModelFromEntity(OrgTypeOfSpecList entity){
        OrgTypeOfSpecListModel model = new OrgTypeOfSpecListModel();
        model.setId(entity.getId());
        model.setCode(entity.getCode());
        model.setName(entity.getName());
        model.setOrgTypeOfSpecId(entity.getOrgTypeOfSpec().getId());
        model.setDescription(entity.getDescription());
        return model;
    }
    
    private OrgTypeOfSpecList getEntityFromViewModel(OrgTypeOfSpecListModel model){
        OrgTypeOfSpecList orgTypeofSpecList = new OrgTypeOfSpecList();
        if(model.getId() != null){
            orgTypeofSpecList.setId(model.getId());
        }
        orgTypeofSpecList.setCode(model.getCode());
        orgTypeofSpecList.setName(model.getName());
        orgTypeofSpecList.setOrgTypeOfSpec(new OrgTypeOfSpec(model.getOrgTypeOfSpecId()));
        orgTypeofSpecList.setDescription(model.getDescription());
        return orgTypeofSpecList;
    }
    
    public void doSave(){
        OrgTypeOfSpecList orgTypeOfSpecList = getEntityFromViewModel(model);
        try{
            if(isUpdate){
                service.update(orgTypeOfSpecList);
                RequestContext.getCurrentInstance().closeDialog(HRMConstant.UPDATE_CONDITION);
            } else {
                service.save(orgTypeOfSpecList);
                RequestContext.getCurrentInstance().closeDialog(HRMConstant.SAVE_CONDITION);
            }
            cleanAndExit();
        } catch (BussinessException ex){
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_ERROR, "global.error", ex.getErrorKeyMessage(), FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
        } catch (Exception ex){
            LOGGER.error("Error", ex);
        }
    }

    public OrgTypeOfSpecListService getService() {
        return service;
    }

    public void setService(OrgTypeOfSpecListService service) {
        this.service = service;
    }

    public OrgTypeOfSpecList getSelected() {
        return selected;
    }

    public void setSelected(OrgTypeOfSpecList selected) {
        this.selected = selected;
    }

    public OrgTypeOfSpecListModel getModel() {
        return model;
    }

    public void setModel(OrgTypeOfSpecListModel model) {
        this.model = model;
    }

    public Boolean getIsUpdate() {
        return isUpdate;
    }

    public void setIsUpdate(Boolean isUpdate) {
        this.isUpdate = isUpdate;
    }

    public OrgTypeOfSpecService getOrgTypeOfSpecService() {
        return orgTypeOfSpecService;
    }

    public void setOrgTypeOfSpecService(OrgTypeOfSpecService orgTypeOfSpecService) {
        this.orgTypeOfSpecService = orgTypeOfSpecService;
    }

    public List<OrgTypeOfSpec> getOrgTypeOfSpec() {
        return orgTypeOfSpec;
    }

    public void setOrgTypeOfSpec(List<OrgTypeOfSpec> orgTypeOfSpec) {
        this.orgTypeOfSpec = orgTypeOfSpec;
    }

    public Map<String, Long> getDropDownOrgTypeOfSpec() {
        return dropDownOrgTypeOfSpec;
    }

    public void setDropDownOrgTypeOfSpec(Map<String, Long> dropDownOrgTypeOfSpec) {
        this.dropDownOrgTypeOfSpec = dropDownOrgTypeOfSpec;
    }
    
    public void doSelectOneMenuOrgTypeOfSpecList() throws Exception{
       orgTypeOfSpec = orgTypeOfSpecService.getAllData();
        System.out.println(orgTypeOfSpec + "asdfadfds");
       for(OrgTypeOfSpec orgTypeOfSpecs : orgTypeOfSpec){
           dropDownOrgTypeOfSpec.put(orgTypeOfSpecs.getName(), orgTypeOfSpecs.getId());
       }
    }
}
