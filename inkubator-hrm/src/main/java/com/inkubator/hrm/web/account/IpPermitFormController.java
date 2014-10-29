/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.account;

import com.inkubator.exception.BussinessException;
import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.entity.IpPermit;
import com.inkubator.hrm.service.IpPermitService;
import com.inkubator.hrm.web.model.IpPermitModel;
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
@ManagedBean(name = "ipPermitFormController")
@ViewScoped
public class IpPermitFormController extends BaseController{
    @ManagedProperty(value = "#{ipPermitService}")
    private IpPermitService service;
    private IpPermit selected;
    private IpPermitModel model;
    private Boolean isUpdate;

    @PostConstruct
    @Override
    public void initialization() {
        super.initialization();
        try{
            String ipPermitId = FacesUtil.getRequestParameter("param");
            model = new IpPermitModel();
            isUpdate = Boolean.FALSE;
            if (StringUtils.isNotEmpty(ipPermitId)) {
                IpPermit ipPermit = service.getEntiyByPK(Long.parseLong(ipPermitId));
                if(ipPermitId != null){
                    model = getModelFromEntity(ipPermit);
                    isUpdate = Boolean.TRUE;
                }
            }
            
        }catch (Exception e){
            LOGGER.error("Error", e);
        }
    }
    
    @PreDestroy
    public void cleanAndExit() {
        service = null;
        selected = null;
        isUpdate = null;
        model = null;
    }
    
    public void doSave() {
        System.out.println("masuk dosave");
        IpPermit ipPermit = getEntityFromViewModel(model);
        try {
            if (isUpdate) {
                service.update(ipPermit);
                RequestContext.getCurrentInstance().closeDialog(HRMConstant.UPDATE_CONDITION);
            } else {
                service.save(ipPermit);
                RequestContext.getCurrentInstance().closeDialog(HRMConstant.SAVE_CONDITION);
            }
            cleanAndExit();
        } catch (BussinessException ex) { //data already exist(duplicate)
            LOGGER.error("Error", ex);
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_ERROR, "global.error", ex.getErrorKeyMessage(), FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
    }
    
    private IpPermitModel getModelFromEntity(IpPermit entity) {
        IpPermitModel model = new IpPermitModel();
        model.setId(entity.getId());
        model.setFromAddress1(String.valueOf(entity.getFromAddress1()));
        model.setFromAddress2(String.valueOf(entity.getFromAddress2()));
        model.setUntilAddress1(entity.getUntilAddress1());
        model.setUntilAddress2(entity.getUntilAddress2());
        model.setLokasi(entity.getLokasi());
        return model;
    }
    
    private IpPermit getEntityFromViewModel(IpPermitModel model) {
        IpPermit ipPermit = new IpPermit();
        if (model.getId() != null) {
            ipPermit.setId(model.getId());
        }
        Integer fromAddress1 = Integer.valueOf(StringUtils.replace(model.getFromAddress1(), ".", ""));
        System.out.println(fromAddress1);
        ipPermit.setFromAddress1(fromAddress1);
        ipPermit.setFromAddress2(fromAddress1);
        ipPermit.setUntilAddress1(model.getUntilAddress1());
        ipPermit.setUntilAddress2(model.getUntilAddress2());
        ipPermit.setLokasi(model.getLokasi());
        return ipPermit;
    }
    
    public IpPermitService getService() {
        return service;
    }

    public void setService(IpPermitService service) {
        this.service = service;
    }

    public IpPermit getSelected() {
        return selected;
    }

    public void setSelected(IpPermit selected) {
        this.selected = selected;
    }

    public IpPermitModel getModel() {
        return model;
    }

    public void setModel(IpPermitModel model) {
        this.model = model;
    }

    public Boolean getIsUpdate() {
        return isUpdate;
    }

    public void setIsUpdate(Boolean isUpdate) {
        this.isUpdate = isUpdate;
    }
}
