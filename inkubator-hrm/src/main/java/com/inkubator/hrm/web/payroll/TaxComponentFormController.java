package com.inkubator.hrm.web.payroll;

import com.inkubator.exception.BussinessException;
import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.entity.TaxComponent;
import com.inkubator.hrm.service.TaxComponentService;
import com.inkubator.hrm.web.model.TaxComponentModel;
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
 * @author Taufik Hidayat
 */
@ManagedBean(name = "taxComponentFormController")
@ViewScoped
public class TaxComponentFormController extends BaseController {

    private TaxComponentModel taxComponentModel;
    private Boolean isUpdate;
    @ManagedProperty(value = "#{taxComponentService}")
    private TaxComponentService taxComponentService;

    @PostConstruct
    @Override
    public void initialization() {
        super.initialization();
        String param = FacesUtil.getRequestParameter("param");
        taxComponentModel = new TaxComponentModel();
        isUpdate = Boolean.FALSE;
        if (StringUtils.isNumeric(param)) {
            try {
                TaxComponent taxComponent = taxComponentService.getEntiyByPK(Long.parseLong(param));
                if (taxComponent != null) {
                    taxComponentModel.setId(taxComponent.getId());
                    taxComponentModel.setName(taxComponent.getName());
                    taxComponentModel.setDescription(taxComponent.getDescription());
                    isUpdate = Boolean.TRUE;
                }
            } catch (Exception e) {
                LOGGER.error("Error", e);
            }
        }
    }

    @PreDestroy
    public void cleanAndExit() {
        taxComponentService = null;
        taxComponentModel = null;
        isUpdate = null;
    }

    public TaxComponentModel getTaxComponentModel() {
        return taxComponentModel;
    }

    public void setTaxComponentModel(TaxComponentModel taxComponentModel) {
        this.taxComponentModel = taxComponentModel;
    }

    public Boolean getIsUpdate() {
        return isUpdate;
    }

    public void setIsUpdate(Boolean isUpdate) {
        this.isUpdate = isUpdate;
    }

    public void setTaxComponentService(TaxComponentService taxComponentService) {
        this.taxComponentService = taxComponentService;
    }

    public void doSave() {
        TaxComponent taxComponent = getEntityFromViewModel(taxComponentModel);
        try {
            if (isUpdate) {
                taxComponentService.update(taxComponent);
                RequestContext.getCurrentInstance().closeDialog(HRMConstant.UPDATE_CONDITION);
            } else {
                taxComponentService.save(taxComponent);
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

    private TaxComponent getEntityFromViewModel(TaxComponentModel taxComponentModel) {
        TaxComponent taxComponent = new TaxComponent();
        if (taxComponentModel.getId() != null) {
            taxComponent.setId(taxComponentModel.getId());
        }
        taxComponent.setName(taxComponentModel.getName());
        taxComponent.setDescription(taxComponentModel.getDescription());
        return taxComponent;
    }
}
