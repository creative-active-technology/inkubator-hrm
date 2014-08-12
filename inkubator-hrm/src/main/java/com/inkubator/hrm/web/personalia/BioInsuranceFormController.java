package com.inkubator.hrm.web.personalia;

import com.inkubator.hrm.web.reference.*;
import com.inkubator.exception.BussinessException;
import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.entity.BioData;
import com.inkubator.hrm.entity.BioInsurance;
import com.inkubator.hrm.service.BioInsuranceService;
import com.inkubator.hrm.web.model.BioInsuranceModel;
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
@ManagedBean(name = "bioInsuranceFormController")
@ViewScoped
public class BioInsuranceFormController extends BaseController {

    private BioInsuranceModel bioInsuranceModel;
    private Boolean isUpdate;
    @ManagedProperty(value = "#{bioInsuranceService}")
    private BioInsuranceService bioInsuranceService;

    @PostConstruct
    @Override
    public void initialization() {
        super.initialization();
        try {
            bioInsuranceModel = new BioInsuranceModel();
            isUpdate = Boolean.FALSE;
            String bioDataId = FacesUtil.getRequestParameter("bioDataId");
            bioInsuranceModel.setBioDataId(Long.parseLong(bioDataId));

            String bioInsuranceId = FacesUtil.getRequestParameter("bioInsuranceId");
            if (StringUtils.isNotEmpty(bioInsuranceId)) {
                BioInsurance bioInsurance = bioInsuranceService.getEntiyByPK(Long.parseLong(bioInsuranceId));
                if (bioInsuranceId != null) {
                    bioInsuranceModel = getModelFromEntity(bioInsurance);
                    isUpdate = Boolean.TRUE;
                }
            }
        } catch (Exception e) {
            LOGGER.error("Error", e);
        }
    }

    @PreDestroy
    public void cleanAndExit() {
        bioInsuranceService = null;
//        bioInsuranceModel = null;
        isUpdate = null;
    }

    public BioInsuranceModel getBioInsuranceModel() {
        return bioInsuranceModel;
    }

    public void setBioInsuranceModel(BioInsuranceModel bioInsuranceModel) {
        this.bioInsuranceModel = bioInsuranceModel;
    }

    public Boolean getIsUpdate() {
        return isUpdate;
    }

    public void setIsUpdate(Boolean isUpdate) {
        this.isUpdate = isUpdate;
    }

    public void setBioInsuranceService(BioInsuranceService bioInsuranceService) {
        this.bioInsuranceService = bioInsuranceService;
    }

    public void doSave() {
        BioInsurance bioInsurance = getEntityFromViewModel(bioInsuranceModel);
        try {
            if (isUpdate) {
                bioInsuranceService.update(bioInsurance);
                RequestContext.getCurrentInstance().closeDialog(HRMConstant.UPDATE_CONDITION);
            } else {
                bioInsuranceService.save(bioInsurance);
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

    private BioInsurance getEntityFromViewModel(BioInsuranceModel bioInsuranceModel) {
        BioInsurance bioInsurance = new BioInsurance();
        if (bioInsuranceModel.getId() != null) {
            bioInsurance.setId(bioInsuranceModel.getId());
        }
        bioInsurance.setBioData(new BioData(bioInsuranceModel.getBioDataId()));
        bioInsurance.setNoPolicy(bioInsuranceModel.getNoPolicy());
        bioInsurance.setInstance(bioInsuranceModel.getInstance());
        bioInsurance.setFacility(bioInsuranceModel.getFacility());
        bioInsurance.setDescription(bioInsuranceModel.getDescription());
        return bioInsurance;
    }

    private BioInsuranceModel getModelFromEntity(BioInsurance entity) {
        BioInsuranceModel bioInsuranceModel = new BioInsuranceModel();
        bioInsuranceModel.setId(entity.getId());
        bioInsuranceModel.setBioDataId(entity.getBioData().getId());
        bioInsuranceModel.setNoPolicy(entity.getNoPolicy());
        bioInsuranceModel.setInstance(entity.getInstance());
        bioInsuranceModel.setDescription(entity.getDescription());
        bioInsuranceModel.setFacility(entity.getFacility());
        return bioInsuranceModel;
    }
}
