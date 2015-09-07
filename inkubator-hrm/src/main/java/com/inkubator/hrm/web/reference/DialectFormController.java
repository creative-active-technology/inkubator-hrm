package com.inkubator.hrm.web.reference;

import com.inkubator.exception.BussinessException;
import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.entity.Dialect;
import com.inkubator.hrm.entity.Race;
import com.inkubator.hrm.service.DialectService;
import com.inkubator.hrm.web.model.DialectModel;
import com.inkubator.hrm.web.model.RaceModel;
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
@ManagedBean(name = "dialectFormController")
@ViewScoped
public class DialectFormController extends BaseController {

    private DialectModel dialectModel;
    private Boolean isUpdate;
    @ManagedProperty(value = "#{dialectService}")
    private DialectService dialectService;

    @PostConstruct
    @Override
    public void initialization() {
        super.initialization();
        String param = FacesUtil.getRequestParameter("param");
        dialectModel = new DialectModel();
        isUpdate = Boolean.FALSE;
        if (StringUtils.isNumeric(param)) {
            try {
                Dialect dialect = dialectService.getEntiyByPK(Long.parseLong(param));
                if (dialect != null) {
                    dialectModel = getModelFromEntity(dialect);
                    isUpdate = Boolean.TRUE;
                }
            } catch (Exception e) {
                LOGGER.error("Error", e);
            }
        }
    }

    @PreDestroy
    public void cleanAndExit() {
        dialectService = null;
        dialectModel = null;
        isUpdate = null;
    }

    public void doReset() throws Exception{
    	if(isUpdate){
    		Dialect dialect = doSelectEntity(dialectModel.getId());
    		dialectModel = getModelFromEntity(dialect);
    	}else{
    		dialectModel = new DialectModel();
    	}
    }
    
    public Dialect doSelectEntity(Long id) throws Exception{
    	Dialect dialect = dialectService.getEntiyByPK(id);
    	return dialect;
    }
    
    public DialectModel getModelFromEntity(Dialect dialect){
    	DialectModel model = new DialectModel();
    	model.setId(dialect.getId());
    	model.setDialectCode(dialect.getDialectCode());
    	model.setDialectName(dialect.getDialectName());
    	model.setDescription(dialect.getDescription());
        return model;
    }
    
    public DialectModel getDialectModel() {
        return dialectModel;
    }

    public void setDialectModel(DialectModel dialectModel) {
        this.dialectModel = dialectModel;
    }

    public Boolean getIsUpdate() {
        return isUpdate;
    }

    public void setIsUpdate(Boolean isUpdate) {
        this.isUpdate = isUpdate;
    }

    public void setDialectService(DialectService dialectService) {
        this.dialectService = dialectService;
    }

    public void doSave() {
        Dialect dialect = getEntityFromViewModel(dialectModel);
        try {
            if (isUpdate) {
                dialectService.update(dialect);
                RequestContext.getCurrentInstance().closeDialog(HRMConstant.UPDATE_CONDITION);
            } else {
                dialectService.save(dialect);
                RequestContext.getCurrentInstance().closeDialog(HRMConstant.SAVE_CONDITION);
            }
            cleanAndExit();
        } catch (BussinessException ex) { 
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_ERROR, "global.error", ex.getErrorKeyMessage(), FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
    }

    private Dialect getEntityFromViewModel(DialectModel dialectModel) {
        Dialect dialect = new Dialect();
        if (dialectModel.getId() != null) {
            dialect.setId(dialectModel.getId());
        }
        dialect.setDialectCode(dialectModel.getDialectCode());
        dialect.setDialectName(dialectModel.getDialectName());
        dialect.setDescription(dialectModel.getDescription());
        return dialect;
    }
}
