/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.personalia;

import com.inkubator.exception.BussinessException;
import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.entity.BioData;
import com.inkubator.hrm.entity.BioKeahlian;
import com.inkubator.hrm.service.BioDataService;
import com.inkubator.hrm.service.BioKeahlianService;
import com.inkubator.hrm.web.model.BioKeahlianModel;
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
@ManagedBean(name = "bioKeahlianFormController")
@ViewScoped
public class BioKeahlianFormController extends BaseController {

    @ManagedProperty(value = "#{bioDataService}")
    private BioDataService bioDataService;
    @ManagedProperty(value = "#{bioKeahlianService}")
    private BioKeahlianService bioKeahlianService;
    private Long bioDataId;
    private BioKeahlian selected;
    private BioKeahlianModel model;
    private Boolean isEdit;

    @PreDestroy
    private void cleanAndExit() {
        model = null;
        selected = null;
        isEdit = null;
        bioDataId = null;
        bioDataService = null;
        bioKeahlianService = null;
    }

    @PostConstruct
    @Override
    public void initialization() {
        super.initialization();
        try {
            String biodataId = FacesUtil.getRequestParameter("bioDataId");
            bioDataId = Long.valueOf(biodataId);
            model = new BioKeahlianModel();
            isEdit = Boolean.FALSE;
            
            String bioKeahlianId = FacesUtil.getRequestParameter("bioKeahlianId");
            if (StringUtils.isNotEmpty(bioKeahlianId)) {
                BioKeahlian bioKeahlian = bioKeahlianService.getAllDataByPK(Long.parseLong(bioKeahlianId));
                if (bioKeahlian != null) {
                    model = getModelFromEntity(bioKeahlian);
                    isEdit = Boolean.TRUE;
                    bioDataId = bioKeahlian.getBiodata().getId();
                }
            }
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
    }
    
    public void doSave() {
        BioKeahlian bioKeahlian = getEntityFromViewModel(model);
        try {
            if (isEdit) {
                bioKeahlianService.update(bioKeahlian);
                RequestContext.getCurrentInstance().closeDialog(HRMConstant.UPDATE_CONDITION);
            } else {
                bioKeahlianService.save(bioKeahlian);
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
    
    private BioKeahlianModel getModelFromEntity(BioKeahlian entity) {
        BioKeahlianModel model = new BioKeahlianModel();
        model.setId(entity.getId());
        model.setBiodataId(entity.getBiodata().getId());
        model.setNamaKeahlian(entity.getName());
        model.setTingkatKeahlian(entity.getTingkatKeahlian());
        return model;
    }
    
    private BioKeahlian getEntityFromViewModel(BioKeahlianModel model) {
        BioKeahlian bioKeahlian = new BioKeahlian();
        if (model.getId() != null) {
            bioKeahlian.setId(model.getId());
        }
        bioKeahlian.setBiodata(new BioData(bioDataId));
        bioKeahlian.setName(model.getNamaKeahlian());
        bioKeahlian.setTingkatKeahlian(model.getTingkatKeahlian());
        return bioKeahlian;
    }

    public BioDataService getBioDataService() {
        return bioDataService;
    }

    public void setBioDataService(BioDataService bioDataService) {
        this.bioDataService = bioDataService;
    }

    public BioKeahlianService getBioKeahlianService() {
        return bioKeahlianService;
    }

    public void setBioKeahlianService(BioKeahlianService bioKeahlianService) {
        this.bioKeahlianService = bioKeahlianService;
    }

    public Long getBioDataId() {
        return bioDataId;
    }

    public void setBioDataId(Long bioDataId) {
        this.bioDataId = bioDataId;
    }

    public BioKeahlian getSelected() {
        return selected;
    }

    public void setSelected(BioKeahlian selected) {
        this.selected = selected;
    }

    public BioKeahlianModel getModel() {
        return model;
    }

    public void setModel(BioKeahlianModel model) {
        this.model = model;
    }

    public Boolean getIsEdit() {
        return isEdit;
    }

    public void setIsEdit(Boolean isEdit) {
        this.isEdit = isEdit;
    }
    
    
}
