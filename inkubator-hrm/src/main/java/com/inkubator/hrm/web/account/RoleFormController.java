/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.account;

import com.inkubator.hrm.web.model.RoleModel;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesUtil;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import org.primefaces.context.RequestContext;

/**
 *
 * @author Deni Husni FR
 */
@ManagedBean(name = "roleFormController")
@RequestScoped
public class RoleFormController extends BaseController {

    private RoleModel roleModel;
    private Boolean isEdit;

    @PostConstruct
    @Override
    public void initialization() {
        String data=FacesUtil.getRequestParameter("data1");
        System.out.println(" Nila "+data);
        super.initialization();
        roleModel=new RoleModel();
    }

    public RoleModel getRoleModel() {
        return roleModel;
    }

    public void setRoleModel(RoleModel roleModel) {
        this.roleModel = roleModel;
    }

    public Boolean getIsEdit() {
        return isEdit;
    }

    public void setIsEdit(Boolean isEdit) {
        this.isEdit = isEdit;
    }

   
    
    public void doSave(){
          RequestContext.getCurrentInstance().closeDialog("");
    }
    
    public void doClear(){
         roleModel=new RoleModel();
    }
}
