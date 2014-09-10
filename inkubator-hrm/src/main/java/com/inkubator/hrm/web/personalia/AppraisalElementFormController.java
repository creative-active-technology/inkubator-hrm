package com.inkubator.hrm.web.personalia;

import com.inkubator.exception.BussinessException;
import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.entity.AppraisalElement;
import com.inkubator.hrm.service.AppraisalElementService;
import com.inkubator.hrm.web.model.AppraisalElementModel;
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
@ManagedBean(name = "appraisalElementFormController")
@ViewScoped
public class AppraisalElementFormController extends BaseController {

    private AppraisalElementModel appraisalElementModel;
    private Boolean isUpdate;
    @ManagedProperty(value = "#{appraisalElementService}")
    private AppraisalElementService appraisalElementService;

    @PostConstruct
    @Override
    public void initialization() {
        super.initialization();
        String param = FacesUtil.getRequestParameter("param");
        appraisalElementModel = new AppraisalElementModel();
        isUpdate = Boolean.FALSE;
        if (StringUtils.isNumeric(param)) {
            try {
                AppraisalElement appraisalElement = appraisalElementService.getEntiyByPK(Long.parseLong(param));
                if (appraisalElement != null) {
                    appraisalElementModel.setId(appraisalElement.getId());
                    appraisalElementModel.setName(appraisalElement.getName());
                    appraisalElementModel.setDescription(appraisalElement.getDescription());
                    appraisalElementModel.setLeader(appraisalElement.getLeader());
                    appraisalElementModel.setOperator(appraisalElement.getOperator());
                    isUpdate = Boolean.TRUE;
                }
            } catch (Exception e) {
                LOGGER.error("Error", e);
            }
        }
    }

    @PreDestroy
    public void cleanAndExit() {
        appraisalElementService = null;
        appraisalElementModel = null;
        isUpdate = null;
    }

    public AppraisalElementModel getAppraisalElementModel() {
        return appraisalElementModel;
    }

    public void setAppraisalElementModel(AppraisalElementModel appraisalElementModel) {
        this.appraisalElementModel = appraisalElementModel;
    }

    public Boolean getIsUpdate() {
        return isUpdate;
    }

    public void setIsUpdate(Boolean isUpdate) {
        this.isUpdate = isUpdate;
    }

    public void setAppraisalElementService(AppraisalElementService appraisalElementService) {
        this.appraisalElementService = appraisalElementService;
    }

    public void doSave() {
        AppraisalElement appraisalElement = getEntityFromViewModel(appraisalElementModel);
        try {
            if (isUpdate) {
                appraisalElementService.update(appraisalElement);
                RequestContext.getCurrentInstance().closeDialog(HRMConstant.UPDATE_CONDITION);
            } else {
                appraisalElementService.save(appraisalElement);
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

    private AppraisalElement getEntityFromViewModel(AppraisalElementModel appraisalElementModel) {
        AppraisalElement appraisalElement = new AppraisalElement();
        if (appraisalElementModel.getId() != null) {
            appraisalElement.setId(appraisalElementModel.getId());
        }
        appraisalElement.setName(appraisalElementModel.getName());
        appraisalElement.setDescription(appraisalElementModel.getDescription());
        appraisalElement.setLeader(appraisalElementModel.getLeader());
        appraisalElement.setOperator(appraisalElementModel.getOperator());
        return appraisalElement;
    }
}
