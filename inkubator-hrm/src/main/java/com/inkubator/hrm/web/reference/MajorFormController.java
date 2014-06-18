package com.inkubator.hrm.web.reference;

import com.inkubator.exception.BussinessException;
import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.entity.Major;
import com.inkubator.hrm.service.MajorService;
import com.inkubator.hrm.web.model.MajorModel;
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
@ManagedBean(name = "majorFormController")
@ViewScoped
public class MajorFormController extends BaseController {

    private MajorModel majorModel;
    private Boolean isUpdate;
    @ManagedProperty(value = "#{majorService}")
    private MajorService majorService;

    @PostConstruct
    @Override
    public void initialization() {
        super.initialization();
        String param = FacesUtil.getRequestParameter("param");
        majorModel = new MajorModel();
        isUpdate = Boolean.FALSE;
        if (StringUtils.isNumeric(param)) {
            try {
                Major major = majorService.getEntiyByPK(Long.parseLong(param));
                if (major != null) {
                    majorModel.setId(major.getId());
                    majorModel.setMajorName(major.getMajorName());
                    majorModel.setDescription(major.getDescription());
                    isUpdate = Boolean.TRUE;
                }
            } catch (Exception e) {
                LOGGER.error("Error", e);
            }
        }
    }

    @PreDestroy
    public void cleanAndExit() {
        majorService = null;
        majorModel = null;
        isUpdate = null;
    }

    public MajorModel getMajorModel() {
        return majorModel;
    }

    public void setMajorModel(MajorModel majorModel) {
        this.majorModel = majorModel;
    }

    public Boolean getIsUpdate() {
        return isUpdate;
    }

    public void setIsUpdate(Boolean isUpdate) {
        this.isUpdate = isUpdate;
    }

    public void setMajorService(MajorService majorService) {
        this.majorService = majorService;
    }

    public void doSave() {
        Major major = getEntityFromViewModel(majorModel);
        try {
            if (isUpdate) {
                majorService.update(major);
                RequestContext.getCurrentInstance().closeDialog(HRMConstant.UPDATE_CONDITION);
            } else {
                majorService.save(major);
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

    private Major getEntityFromViewModel(MajorModel majorModel) {
        Major major = new Major();
        if (majorModel.getId() != null) {
            major.setId(majorModel.getId());
        }
        major.setMajorName(majorModel.getMajorName());
        major.setDescription(majorModel.getDescription());
        return major;
    }
}
