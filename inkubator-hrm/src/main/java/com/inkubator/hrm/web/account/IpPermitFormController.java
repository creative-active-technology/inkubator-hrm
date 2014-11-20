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
public class IpPermitFormController extends BaseController {

    @ManagedProperty(value = "#{ipPermitService}")
    private IpPermitService service;
    private IpPermit selected;
    private IpPermitModel model;
    private Boolean isUpdate;

    @PostConstruct
    @Override
    public void initialization() {
        super.initialization();
        try {
            String ipPermitId = FacesUtil.getRequestParameter("param");
            model = new IpPermitModel();
            isUpdate = Boolean.FALSE;
            if (StringUtils.isNotEmpty(ipPermitId)) {
                IpPermit ipPermit = service.getEntiyByPK(Long.parseLong(ipPermitId));
                if (ipPermitId != null) {
                    model = getModelFromEntity(ipPermit);
                    isUpdate = Boolean.TRUE;
                }
            }

        } catch (Exception e) {
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
        } catch (BussinessException ex) {
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_ERROR, "global.error", ex.getErrorKeyMessage(), FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
    }

    private IpPermitModel getModelFromEntity(IpPermit entity) {
        IpPermitModel model = new IpPermitModel();

        String ipPermitFrom = entity.getIpAddressFromView();
        String[] partFrom = ipPermitFrom.replace(".", "-").split("-");
        model.setId(entity.getId());
        model.setFromAddress11(Integer.valueOf(partFrom[0]));
        model.setFromAddress12(Integer.valueOf(partFrom[1]));
        model.setFromAddress13(Integer.valueOf(partFrom[2]));
        model.setFromAddress21(Integer.valueOf(partFrom[0]));
        model.setFromAddress22(Integer.valueOf(partFrom[1]));
        model.setFromAddress23(Integer.valueOf(partFrom[2]));
        model.setUntilAddress1(entity.getUntilAddress1());
        model.setUntilAddress2(entity.getUntilAddress2());
        model.setLokasi(entity.getLokasi());
        return model;
    }

    public void doChangeIpUntil() {
        model.setFromAddress21(model.getFromAddress11());
        model.setFromAddress22(model.getFromAddress12());
        model.setFromAddress23(model.getFromAddress13());
    }

    private IpPermit getEntityFromViewModel(IpPermitModel model) {
        IpPermit ipPermit = new IpPermit();
        if (model.getId() != null) {
            ipPermit.setId(model.getId());
        }
        String fromAddress1 = String.valueOf(model.getFromAddress11() + "" + model.getFromAddress12() + "" + model.getFromAddress13());
        String ipAddressFromView = String.valueOf(model.getFromAddress11() + "." + model.getFromAddress12() + "." + model.getFromAddress13() + "." + model.getUntilAddress1());
        String ipAddressUntilView = String.valueOf(model.getFromAddress21() + "." + model.getFromAddress22() + "." + model.getFromAddress23() + "." + model.getUntilAddress2());
        ipPermit.setFromAddress1(Integer.valueOf(fromAddress1));
        ipPermit.setFromAddress2(Integer.valueOf(fromAddress1));
        ipPermit.setIpAddressFromView(ipAddressFromView);
        ipPermit.setIpAddressUntilView(ipAddressUntilView);
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
