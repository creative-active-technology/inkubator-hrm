package com.inkubator.hrm.web.reference;

import com.inkubator.exception.BussinessException;
import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.entity.Nationality;
import com.inkubator.hrm.service.NationalityService;
import com.inkubator.hrm.web.model.NationalityModel;
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
@ManagedBean(name = "nationalityFormController")
@ViewScoped
public class NationalityFormController extends BaseController {

    private NationalityModel nationalityModel;
    private Boolean isUpdate;
    @ManagedProperty(value = "#{nationalityService}")
    private NationalityService nationalityService;

    @PostConstruct
    @Override
    public void initialization() {
        super.initialization();
        String param = FacesUtil.getRequestParameter("param");
        nationalityModel = new NationalityModel();
        isUpdate = Boolean.FALSE;
        if (StringUtils.isNumeric(param)) {
            try {
                Nationality nationality = nationalityService.getEntiyByPK(Long.parseLong(param));
                if (nationality != null) {
                    nationalityModel.setId(nationality.getId());
                    nationalityModel.setNationalityCode(nationality.getNationalityCode());
                    nationalityModel.setNationalityName(nationality.getNationalityName());
                    nationalityModel.setDescription(nationality.getDescription());
                    isUpdate = Boolean.TRUE;
                }
            } catch (Exception e) {
                LOGGER.error("Error", e);
            }
        }
    }

    @PreDestroy
    public void cleanAndExit() {
        nationalityService = null;
        nationalityModel = null;
        isUpdate = null;
    }

    public NationalityModel getNationalityModel() {
        return nationalityModel;
    }

    public void setNationalityModel(NationalityModel nationalityModel) {
        this.nationalityModel = nationalityModel;
    }

    public Boolean getIsUpdate() {
        return isUpdate;
    }

    public void setIsUpdate(Boolean isUpdate) {
        this.isUpdate = isUpdate;
    }

    public void setNationalityService(NationalityService nationalityService) {
        this.nationalityService = nationalityService;
    }

    public void doSave() {
        Nationality nationality = getEntityFromViewModel(nationalityModel);
        try {
            if (isUpdate) {
                nationalityService.update(nationality);
                RequestContext.getCurrentInstance().closeDialog(HRMConstant.UPDATE_CONDITION);
            } else {
                nationalityService.save(nationality);
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

    private Nationality getEntityFromViewModel(NationalityModel nationalityModel) {
        Nationality nationality = new Nationality();
        if (nationalityModel.getId() != null) {
            nationality.setId(nationalityModel.getId());
        }
        nationality.setNationalityCode(nationalityModel.getNationalityCode());
        nationality.setNationalityName(nationalityModel.getNationalityName());
        nationality.setDescription(nationalityModel.getDescription());
        return nationality;
    }
}
