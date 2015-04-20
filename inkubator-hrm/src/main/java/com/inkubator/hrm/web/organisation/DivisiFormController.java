/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.organisation;

import com.inkubator.exception.BussinessException;
import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.entity.Department;
import com.inkubator.hrm.entity.Divisi;
import com.inkubator.hrm.service.DepartmentService;
import com.inkubator.hrm.service.DivisiService;
import com.inkubator.hrm.web.model.DivisiModel;
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
@ManagedBean(name = "divisiFormController")
@ViewScoped
public class DivisiFormController extends BaseController{
    @ManagedProperty(value = "#{divisiService}")
    private DivisiService service;
    private Divisi selected;
    private DivisiModel model;
    private Boolean isUpdate;
    @ManagedProperty(value = "#{departmentService}")
    private DepartmentService departmentService;
    
    private List<Department> department = new ArrayList<Department>();
    private Map<String, Long> dropDownDepartment = new TreeMap<String, Long>();

    @PostConstruct
    @Override
    public void initialization(){
        super.initialization();
        try{
            String divisiId = FacesUtil.getRequestParameter("divisiId");
            model = new DivisiModel();
            isUpdate = Boolean.FALSE;
            if( StringUtils.isNotEmpty(divisiId)){
                Divisi divisi = service.getEntiyByPK(Long.parseLong(divisiId));
                if(divisiId != null){
                    model = getModelFromEntity(divisi);
                    isUpdate = Boolean.TRUE;
                }
            }
            doSelectOneMenuDepartment();
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
        departmentService = null;
        department = null;
        dropDownDepartment = null;
    }
    
    private DivisiModel getModelFromEntity(Divisi entity){
        DivisiModel model = new DivisiModel();
        model.setId(entity.getId());
        model.setCode(entity.getCode());
        model.setName(entity.getName());
        model.setDepartmentId(entity.getDepartment().getId());
        model.setDescription(entity.getDescription());
        return model;
    }
    
    private Divisi getEntityFromViewModel(DivisiModel model){
        Divisi divisi = new Divisi();
//        Department dp = new Department();
        if(model.getId() != null){
            divisi.setId(model.getId());
        }
        divisi.setCode(model.getCode());
        divisi.setName(model.getName());
        divisi.setDepartment(new Department(model.getDepartmentId()));
        divisi.setDescription(model.getDescription());
        return divisi;
    }
           
    public void doSave(){
        Divisi divisi = getEntityFromViewModel(model);
        try{
            if(isUpdate){
                service.update(divisi);
                RequestContext.getCurrentInstance().closeDialog(HRMConstant.UPDATE_CONDITION);
            } else {
                service.save(divisi);
                RequestContext.getCurrentInstance().closeDialog(HRMConstant.SAVE_CONDITION);
            }
            cleanAndExit();
        } catch (BussinessException ex){
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_ERROR, "global.error", ex.getErrorKeyMessage(), FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
        } catch (Exception ex){
            LOGGER.error("Error", ex);
        }
    }

    public DivisiService getService() {
        return service;
    }

    public void setService(DivisiService service) {
        this.service = service;
    }

    public Divisi getSelected() {
        return selected;
    }

    public void setSelected(Divisi selected) {
        this.selected = selected;
    }

    public DivisiModel getModel() {
        return model;
    }

    public void setModel(DivisiModel model) {
        this.model = model;
    }

    public Boolean getIsUpdate() {
        return isUpdate;
    }

    public void setIsUpdate(Boolean isUpdate) {
        this.isUpdate = isUpdate;
    }
    
    public DepartmentService getDepartmentService() {
        return departmentService;
    }

    public void setDepartmentService(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    public Map<String, Long> getDropDownDepartment() {
        return dropDownDepartment;
    }

    public void setDropDownDepartment(Map<String, Long> dropDownDepartment) {
        this.dropDownDepartment = dropDownDepartment;
    }
    
    public void doSelectOneMenuDepartment() throws Exception{
        department = departmentService.getAllData();
        System.out.println(department + " jkasdjkfkjsa");
        for(Department departments : department){
            dropDownDepartment.put(departments.getDepartmentName(), departments.getId());
        }
    }
            
}
