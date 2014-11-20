package com.inkubator.hrm.web.organisation;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.apache.commons.lang3.StringUtils;
import org.primefaces.context.RequestContext;

import com.inkubator.exception.BussinessException;
import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.entity.Pangkat;
import com.inkubator.hrm.service.PangkatService;
import com.inkubator.hrm.web.model.PangkatModel;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesUtil;
import com.inkubator.webcore.util.MessagesResourceUtil;

/**
 *
 * @author rizkykojek
 */
@ManagedBean(name = "pangkatFormController")
@ViewScoped
public class PangkatFormController extends BaseController {

    private PangkatModel model;
    private Boolean isUpdate;
    @ManagedProperty(value = "#{pangkatService}")
    private PangkatService pangkatService;

    @PostConstruct
    @Override
    public void initialization() {
        super.initialization();
        String param = FacesUtil.getRequestParameter("param");
        model = new PangkatModel();
        isUpdate = Boolean.FALSE;
        if (StringUtils.isNumeric(param)) {
            try {
                Pangkat pangkat = pangkatService.getEntiyByPK(Long.parseLong(param));
                if (pangkat != null) {
                    getViewModelFromEntity(pangkat);
                    isUpdate = Boolean.TRUE;
                }
            } catch (Exception e) {
                LOGGER.error("Error", e);
            }
        }
    }

    @PreDestroy
    public void cleanAndExit() {
        pangkatService = null;
        model = null;
        isUpdate = null;
    }

	public PangkatModel getModel() {
		return model;
	}

	public void setModel(PangkatModel model) {
		this.model = model;
	}

	public Boolean getIsUpdate() {
		return isUpdate;
	}

	public void setIsUpdate(Boolean isUpdate) {
		this.isUpdate = isUpdate;
	}

	public void setPangkatService(PangkatService pangkatService) {
		this.pangkatService = pangkatService;
	}

	public void doSave() {
        Pangkat pangkat = getEntityFromViewModel(model);
        try {
            if (isUpdate) {
                pangkatService.update(pangkat);
                RequestContext.getCurrentInstance().closeDialog(HRMConstant.UPDATE_CONDITION);
            } else {
                pangkatService.save(pangkat);
                RequestContext.getCurrentInstance().closeDialog(HRMConstant.SAVE_CONDITION);
            }
            cleanAndExit();
        } catch (BussinessException ex) { 
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_ERROR, "global.error", ex.getErrorKeyMessage(), FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
    }

    private Pangkat getEntityFromViewModel(PangkatModel model) {
        Pangkat pangkat = new Pangkat();
        if (model.getId() != null) {
        	pangkat.setId(model.getId());
        }
        pangkat.setPangkatCode(model.getPangkatCode());
        pangkat.setPangkatName(model.getPangkatName());
//        pangkat.setLevel(model.getLevel());
        return pangkat;
    }
    
    private void getViewModelFromEntity(Pangkat pangkat){
    	model.setId(pangkat.getId());
    	model.setPangkatCode(pangkat.getPangkatCode());
    	model.setPangkatName(pangkat.getPangkatName());
//    	model.setLevel(pangkat.getLevel());
    }
}
